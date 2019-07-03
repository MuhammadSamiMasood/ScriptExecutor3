package com.ebricks.scriptexecutor.Service;

import com.ebricks.scriptexecutor.main.Driver;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TestCaseService {

    public static Logger logger = LogManager.getLogger(Driver.class);

    public static Boolean postEmpty(String url, String body) throws IOException{

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(body);
        httpPost.setEntity(entity);

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

        logger.info(closeableHttpResponse.getStatusLine().getStatusCode());
        if(closeableHttpResponse.getStatusLine().getStatusCode() == 200){
            return true;
        }
        else {
            return false;
        }
    }

    public static String post(String url, String body) throws IOException{

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(body);
        httpPost.setEntity(entity);

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
        return IOUtils.toString(closeableHttpResponse.getEntity().getContent());
    }

}
