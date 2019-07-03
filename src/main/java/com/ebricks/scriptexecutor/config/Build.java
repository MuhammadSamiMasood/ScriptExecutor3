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
        "FILE_NAME",
        "NAME",
        "PACKAGE",
        "VERSION",
        "VERSION_LONG",
        "UNINSTALL_BUILD"
})
public class Build {

    @JsonProperty("FILE_NAME")
    private String fILENAME;
    @JsonProperty("NAME")
    private String nAME;
    @JsonProperty("PACKAGE")
    private String pACKAGE;
    @JsonProperty("VERSION")
    private String vERSION;
    @JsonProperty("VERSION_LONG")
    private String vERSIONLONG;
    @JsonProperty("UNINSTALL_BUILD")
    private Boolean uNINSTALLBUILD;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("FILE_NAME")
    public String getFILENAME() {
        return fILENAME;
    }

    @JsonProperty("FILE_NAME")
    public void setFILENAME(String fILENAME) {
        this.fILENAME = fILENAME;
    }

    @JsonProperty("NAME")
    public String getNAME() {
        return nAME;
    }

    @JsonProperty("NAME")
    public void setNAME(String nAME) {
        this.nAME = nAME;
    }

    @JsonProperty("PACKAGE")
    public String getPACKAGE() {
        return pACKAGE;
    }

    @JsonProperty("PACKAGE")
    public void setPACKAGE(String pACKAGE) {
        this.pACKAGE = pACKAGE;
    }

    @JsonProperty("VERSION")
    public String getVERSION() {
        return vERSION;
    }

    @JsonProperty("VERSION")
    public void setVERSION(String vERSION) {
        this.vERSION = vERSION;
    }

    @JsonProperty("VERSION_LONG")
    public String getVERSIONLONG() {
        return vERSIONLONG;
    }

    @JsonProperty("VERSION_LONG")
    public void setVERSIONLONG(String vERSIONLONG) {
        this.vERSIONLONG = vERSIONLONG;
    }

    @JsonProperty("UNINSTALL_BUILD")
    public Boolean getUNINSTALLBUILD() {
        return uNINSTALLBUILD;
    }

    @JsonProperty("UNINSTALL_BUILD")
    public void setUNINSTALLBUILD(Boolean uNINSTALLBUILD) {
        this.uNINSTALLBUILD = uNINSTALLBUILD;
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
