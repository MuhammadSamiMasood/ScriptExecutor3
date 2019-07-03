package com.ebricks.scriptexecutor.processor;

import com.ebricks.scriptexecutor.Service.TestCaseService;
import com.ebricks.scriptexecutor.config.ExecutionConfig;
import com.ebricks.scriptexecutor.config.TestCase;
import com.ebricks.scriptexecutor.executor.ExecutorFactory;
import com.ebricks.scriptexecutor.executor.StepExecutor;
import com.ebricks.scriptexecutor.executor.StepExecutorResponse;
import com.ebricks.scriptexecutor.model.Data;
import com.ebricks.scriptexecutor.model.ExecutionFilesData;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import com.ebricks.scriptexecutor.resource.ResultFolder;
import com.ebricks.scriptexecutor.resource.TestCasesFolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebricks.scriptexecutor.model.ScriptInputData;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ScriptProcessor {


    private ScriptInputData scriptInputData;
    private ObjectMapper objectMapper;
    public static Logger logger = LogManager.getLogger(ScriptProcessor.class);
    StepExecutorResponse stepExecutorResponse;

    public void init() throws IOException {

        //setting path for test cases
        String testCaseId = ExecutionConfig.getInstance().getTESTCASES().get(0).getId();
        String testCaseDirPath = "executions/" + ExecutionConfig.EXECUTION_ID + "/testcases/" + testCaseId + "/";
        TestCasesFolder.setPath(testCaseDirPath);

        //settubg path for output
        String outputPath = "executions/" + ExecutionConfig.EXECUTION_ID + "/output/";
        ResultFolder.setPath(outputPath);

        //requesting the url
        Boolean urlWorking = TestCaseService.postEmpty(ExecutionConfig.getInstance().getDASHBOARDURL() + "/api/execution/" + ExecutionConfig.getInstance().getRESULTID() + "/start", "");
        if (urlWorking) {
            String response = TestCaseService.post(ExecutionConfig.getInstance().getDASHBOARDURL() + "/api/execution/" + ExecutionConfig.getInstance().getRESULTID() + "/test-case/" + ExecutionConfig.getInstance().getTESTCASES().get(0).getId() + "/start", "");
            File testCaseDir = new File(TestCasesFolder.getPath());
            testCaseDir.mkdirs();
            //FileUtils.write(new File("executions/" + ExecutionConfig.EXECUTION_ID + "/testcases/" + testCaseId + "/data.json"), response);
            FileUtils.write(new File(TestCasesFolder.getPath() + "data.json"), response);
        }

        String fileData = FileUtils.readFileToString(new File(TestCasesFolder.getPath() + "data.json"));

        objectMapper = new ObjectMapper();
        scriptInputData = objectMapper.readValue(fileData, ScriptInputData.class);


        // creating images directory

        String imageDirPath = TestCasesFolder.getPath() + "images";
        File imageDir = new File(imageDirPath);
        imageDir.mkdir();

        // creating dom directory

        String domDirPath = TestCasesFolder.getPath() + "dom";
        File domDir = new File((domDirPath));
        domDir.mkdir();

        // downloading dom and images of testcase

        for (Step step : scriptInputData.getData().getExecutionFilesData().getSteps()) {

            // downloading image
            InputStream in = new URL(step.getScreen().getFullImageUrl()).openStream();
            Files.copy(in, Paths.get(imageDirPath + "/" + step.getScreen().getImage()));

            // downloading dom file
            URL url = new URL(step.getDomUrl());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String dom = "", temp;
            while ((temp = bufferedReader.readLine()) != null) {
                dom = dom + temp;
            }
            bufferedReader.close();
            FileUtils.write(new File(domDirPath + "/" + step.getScreen().getDom()), dom);

        }

    }

    public void process() throws IOException, SAXException, ParserConfigurationException, InterruptedException {

        ExecutorFactory executorFactory = new ExecutorFactory();
        stepExecutorResponse = new StepExecutorResponse();
        stepExecutorResponse.setData(new Data());
        stepExecutorResponse.getData().setTestCaseResultId(scriptInputData.getData().getTestCaseResultId());
        stepExecutorResponse.getData().setExecutionFilesData(new ExecutionFilesData());
        stepExecutorResponse.getData().getExecutionFilesData().setSteps(new ArrayList<>());

        System.out.println(scriptInputData.getData().getExecutionFilesData().getSteps().size());

        for (int i = 0; i < scriptInputData.getData().getExecutionFilesData().getSteps().size(); i++) {

            StepExecutor stepExecutor = executorFactory.getStepExecutor(scriptInputData.getData().getExecutionFilesData().getSteps().get(i));

            if (i > 0) {
                long sleepTime = scriptInputData.getData().getExecutionFilesData().getSteps().get(i).getEvent().getEventTime() - scriptInputData.getData().getExecutionFilesData().getSteps().get(i - 1).getEvent().getEventTime();
                if (sleepTime < 50000)
                    Thread.sleep(sleepTime);
                else
                    Thread.sleep(50000);
            }
            stepExecutor.init(scriptInputData);
            Step step = stepExecutor.execute();
            stepExecutor.end();

            if (step == null) {
                stepExecutorResponse.setSuccess(false);
                return;
            }

            stepExecutorResponse.getData().getExecutionFilesData().getSteps().add(step);
        }
        stepExecutorResponse.setSuccess(true);
    }


    public void end() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ResultFolder.getPath() + "/response.json"), stepExecutorResponse);

        // test case complete
        String postURL1 = ExecutionConfig.getInstance().getDASHBOARDURL() + "/api/execution/" + ExecutionConfig.getInstance().getRESULTID() + "/test-case/" + ExecutionConfig.getInstance().getTESTCASES().get(0).getId() + "/complete";
        CloseableHttpClient closeableHttpClient1 = HttpClientBuilder.create().build();
        HttpPost httpPost1 = new HttpPost(postURL1);
        CloseableHttpResponse closeableHttpResponse1 = closeableHttpClient1.execute(httpPost1);
        System.out.println(closeableHttpResponse1.getStatusLine().getStatusCode());
        if (closeableHttpResponse1.getStatusLine().getStatusCode() == 200) {
            logger.info("Test case successfully completed");
        } else {
            logger.error("Error!! While Hitting URL ");
        }

        // execution complete
        String postURL2 = ExecutionConfig.getInstance().getDASHBOARDURL() + "/api/execution/" + ExecutionConfig.getInstance().getRESULTID() + "/complete";
        CloseableHttpClient closeableHttpClient2 = HttpClientBuilder.create().build();
        HttpPost httpPost2 = new HttpPost(postURL2);
        CloseableHttpResponse closeableHttpResponse2 = closeableHttpClient2.execute(httpPost2);
        System.out.println(closeableHttpResponse2.getStatusLine().getStatusCode());
        if (closeableHttpResponse2.getStatusLine().getStatusCode() == 200) {
            logger.info("Execution successfully completed");
        } else {
            logger.error("Error!! While Hitting URL ");
        }

        // quiting mobile driver
        MobileDriver.getInstance().quit();
    }
}
