package com.ebricks.scriptexecutor.config;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "TYPE",
        "ORIENTATION"
})
public class ExecutionMode {

    @JsonProperty("TYPE")
    private String tYPE;
    @JsonProperty("ORIENTATION")
    private String oRIENTATION;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("TYPE")
    public String getTYPE() {
        return tYPE;
    }

    @JsonProperty("TYPE")
    public void setTYPE(String tYPE) {
        this.tYPE = tYPE;
    }

    @JsonProperty("ORIENTATION")
    public String getORIENTATION() {
        return oRIENTATION;
    }

    @JsonProperty("ORIENTATION")
    public void setORIENTATION(String oRIENTATION) {
        this.oRIENTATION = oRIENTATION;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}