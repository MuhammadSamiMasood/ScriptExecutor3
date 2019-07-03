package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.config.ExecutionConfig;
import com.ebricks.scriptexecutor.model.Data;
import com.ebricks.scriptexecutor.model.ExecutionFilesData;
import com.ebricks.scriptexecutor.model.ScriptInputData;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.ebricks.scriptexecutor.resource.ResultFolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EndExecutor extends StepExecutor {

    ScriptInputData scriptInputData;
    StepExecutorResponse stepExecutorResponse;
    public static Logger logger = LogManager.getLogger(EndExecutor.class);

    public EndExecutor(Step step){
        this.step = step;
    }

    @Override
    public void init(ScriptInputData scriptInputData) throws IOException {
        this.scriptInputData = scriptInputData;
        MobileDriver.getInstance().takeScreenshot(step.getScreen());
        MobileDriver.getInstance().getDom(step.getScreen());
    }

    @Override
    public Step execute() throws IOException, ParserConfigurationException, SAXException {

        stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setSuccess(true);
        stepExecutorResponse.setData(new Data());
        stepExecutorResponse.getData().setTestCaseResultId(scriptInputData.getData().getTestCaseResultId());
        stepExecutorResponse.getData().setExecutionFilesData(new ExecutionFilesData());
        List<Step> steps = new ArrayList<>();
        steps.add(step);
        stepExecutorResponse.getData().getExecutionFilesData().setSteps(steps);

        return stepExecutorResponse.getData().getExecutionFilesData().getSteps().get(0);
    }

    @Override
    public void end() throws IOException{
        // posting the data
        // -----------------------
        String url = ExecutionConfig.getInstance().getDASHBOARDURL() + "/api/execution/" + ExecutionConfig.getInstance().getRESULTID() + "/test-case/" + ExecutionConfig.getInstance().getTESTCASES().get(0).getId() + "/data";
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        // converting the response to JSON
        String testResult = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(stepExecutorResponse.getData().getExecutionFilesData());

        JSONObject testResultJson = new JSONObject();
        testResultJson.put("testCaseResultId", scriptInputData.getData().getTestCaseResultId());
        testResultJson.put("testCaseId", ExecutionConfig.getInstance().getTESTCASES().get(0).getId());
        testResultJson.put("testCaseName", ExecutionConfig.getInstance().getTESTCASES().get(0).getName());
        testResultJson.put("additionalInfo", testResult);

        builder.addTextBody("data", testResultJson.toString());

        // logging the data to be sent
        logger.info("data to be sent :: " + testResultJson.toString());

        // attaching the image file
        File image = new File(ResultFolder.getPath() + "screenshots/" + step.getScreen().getImage());
        builder.addBinaryBody("attachment", image);

        // attaching the dom file
        File dom = new File(ResultFolder.getPath() + "dom/" + step.getScreen().getDom());
        builder.addBinaryBody("dom", dom);

        // posting
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(builder.build());
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
        logger.info(closeableHttpResponse.getStatusLine().getStatusCode());
        if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
            logger.info("Successfully Posted");
        } else {
            logger.error("Error!! While Posting ");
        }
    }
}
