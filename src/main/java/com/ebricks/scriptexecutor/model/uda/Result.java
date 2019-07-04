package com.ebricks.scriptexecutor.model.uda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

    @JsonProperty("success")
    @JsonIgnore
    private Boolean success;
    @JsonProperty("status")
    @JsonIgnore
    private String status;
    @JsonProperty("value")
    @JsonIgnore
    private String value;
    @JsonProperty("base")
    @JsonIgnore
    private BaseResult base;
    @JsonProperty("result")
    @JsonIgnore
    private BaseResult result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BaseResult getBase() {
        return base;
    }

    public void setBase(BaseResult base) {
        this.base = base;
    }

    public BaseResult getResult() {
        return result;
    }

    public void setResult(BaseResult result) {
        this.result = result;
    }

}
