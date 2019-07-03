package com.ebricks.scriptexecutor.executor;

import com.ebricks.scriptexecutor.model.*;
import com.ebricks.scriptexecutor.status.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StepExecutorResponse {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("data")
    private Data data;

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }
}
