package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("testCaseResultId")
    private String testCaseResultId;
    @JsonProperty("executionFilesData")
    private ExecutionFilesData executionFilesData;

    @JsonProperty("testCaseResultId")
    public String getTestCaseResultId() {
        return testCaseResultId;
    }

    @JsonProperty("testCaseResultId")
    public void setTestCaseResultId(String testCaseResultId) {
        this.testCaseResultId = testCaseResultId;
    }

    @JsonProperty("executionFilesData")
    public ExecutionFilesData getExecutionFilesData() {
        return executionFilesData;
    }

    @JsonProperty("executionFilesData")
    public void setExecutionFilesData(ExecutionFilesData executionFilesData) {
        this.executionFilesData = executionFilesData;
    }
}