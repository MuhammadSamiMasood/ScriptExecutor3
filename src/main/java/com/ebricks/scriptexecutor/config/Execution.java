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
        "nodePath",
        "appiumPath",
        "adbPath",
        "buildToolsPath",
        "xcrunPath",
        "xcodebuildPath",
        "fbsimctlPath",
        "mobiledeviceToolPath",
        "iosdeployToolPath",
        "visionServiceUrl",
        "imageCompareServiceUrl",
        "proxyServiceUrl",
        "domUtilityServiceUrl",
        "visionServiceEnabled",
        "imageCompareServiceEnabled",
        "domServiceEnabled",
        "proxyServiceEnabled",
        "anacondaEnvName",
        "anacondaActivatePath",
        "autoHandleIOSSystemDialog",
        "pythonPath",
        "textClassifierEnabled",
        "vaVisualizerEnabled",
        "customWDAPath"
})
public class Execution {

    @JsonProperty("nodePath")
    private String nodePath;
    @JsonProperty("appiumPath")
    private String appiumPath;
    @JsonProperty("adbPath")
    private String adbPath;
    @JsonProperty("buildToolsPath")
    private String buildToolsPath;
    @JsonProperty("xcrunPath")
    private String xcrunPath;
    @JsonProperty("xcodebuildPath")
    private String xcodebuildPath;
    @JsonProperty("fbsimctlPath")
    private String fbsimctlPath;
    @JsonProperty("mobiledeviceToolPath")
    private String mobiledeviceToolPath;
    @JsonProperty("iosdeployToolPath")
    private String iosdeployToolPath;
    @JsonProperty("visionServiceUrl")
    private String visionServiceUrl;
    @JsonProperty("imageCompareServiceUrl")
    private String imageCompareServiceUrl;
    @JsonProperty("proxyServiceUrl")
    private String proxyServiceUrl;
    @JsonProperty("domUtilityServiceUrl")
    private String domUtilityServiceUrl;
    @JsonProperty("visionServiceEnabled")
    private Boolean visionServiceEnabled;
    @JsonProperty("imageCompareServiceEnabled")
    private Boolean imageCompareServiceEnabled;
    @JsonProperty("domServiceEnabled")
    private Boolean domServiceEnabled;
    @JsonProperty("proxyServiceEnabled")
    private Boolean proxyServiceEnabled;
    @JsonProperty("anacondaEnvName")
    private String anacondaEnvName;
    @JsonProperty("anacondaActivatePath")
    private String anacondaActivatePath;
    @JsonProperty("autoHandleIOSSystemDialog")
    private Boolean autoHandleIOSSystemDialog;
    @JsonProperty("pythonPath")
    private String pythonPath;
    @JsonProperty("textClassifierEnabled")
    private Boolean textClassifierEnabled;
    @JsonProperty("vaVisualizerEnabled")
    private Boolean vaVisualizerEnabled;
    @JsonProperty("customWDAPath")
    private String customWDAPath;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("nodePath")
    public String getNodePath() {
        return nodePath;
    }

    @JsonProperty("nodePath")
    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    @JsonProperty("appiumPath")
    public String getAppiumPath() {
        return appiumPath;
    }

    @JsonProperty("appiumPath")
    public void setAppiumPath(String appiumPath) {
        this.appiumPath = appiumPath;
    }

    @JsonProperty("adbPath")
    public String getAdbPath() {
        return adbPath;
    }

    @JsonProperty("adbPath")
    public void setAdbPath(String adbPath) {
        this.adbPath = adbPath;
    }

    @JsonProperty("buildToolsPath")
    public String getBuildToolsPath() {
        return buildToolsPath;
    }

    @JsonProperty("buildToolsPath")
    public void setBuildToolsPath(String buildToolsPath) {
        this.buildToolsPath = buildToolsPath;
    }

    @JsonProperty("xcrunPath")
    public String getXcrunPath() {
        return xcrunPath;
    }

    @JsonProperty("xcrunPath")
    public void setXcrunPath(String xcrunPath) {
        this.xcrunPath = xcrunPath;
    }

    @JsonProperty("xcodebuildPath")
    public String getXcodebuildPath() {
        return xcodebuildPath;
    }

    @JsonProperty("xcodebuildPath")
    public void setXcodebuildPath(String xcodebuildPath) {
        this.xcodebuildPath = xcodebuildPath;
    }

    @JsonProperty("fbsimctlPath")
    public String getFbsimctlPath() {
        return fbsimctlPath;
    }

    @JsonProperty("fbsimctlPath")
    public void setFbsimctlPath(String fbsimctlPath) {
        this.fbsimctlPath = fbsimctlPath;
    }

    @JsonProperty("mobiledeviceToolPath")
    public String getMobiledeviceToolPath() {
        return mobiledeviceToolPath;
    }

    @JsonProperty("mobiledeviceToolPath")
    public void setMobiledeviceToolPath(String mobiledeviceToolPath) {
        this.mobiledeviceToolPath = mobiledeviceToolPath;
    }

    @JsonProperty("iosdeployToolPath")
    public String getIosdeployToolPath() {
        return iosdeployToolPath;
    }

    @JsonProperty("iosdeployToolPath")
    public void setIosdeployToolPath(String iosdeployToolPath) {
        this.iosdeployToolPath = iosdeployToolPath;
    }

    @JsonProperty("visionServiceUrl")
    public String getVisionServiceUrl() {
        return visionServiceUrl;
    }

    @JsonProperty("visionServiceUrl")
    public void setVisionServiceUrl(String visionServiceUrl) {
        this.visionServiceUrl = visionServiceUrl;
    }

    @JsonProperty("imageCompareServiceUrl")
    public String getImageCompareServiceUrl() {
        return imageCompareServiceUrl;
    }

    @JsonProperty("imageCompareServiceUrl")
    public void setImageCompareServiceUrl(String imageCompareServiceUrl) {
        this.imageCompareServiceUrl = imageCompareServiceUrl;
    }

    @JsonProperty("proxyServiceUrl")
    public String getProxyServiceUrl() {
        return proxyServiceUrl;
    }

    @JsonProperty("proxyServiceUrl")
    public void setProxyServiceUrl(String proxyServiceUrl) {
        this.proxyServiceUrl = proxyServiceUrl;
    }

    @JsonProperty("domUtilityServiceUrl")
    public String getDomUtilityServiceUrl() {
        return domUtilityServiceUrl;
    }

    @JsonProperty("domUtilityServiceUrl")
    public void setDomUtilityServiceUrl(String domUtilityServiceUrl) {
        this.domUtilityServiceUrl = domUtilityServiceUrl;
    }

    @JsonProperty("visionServiceEnabled")
    public Boolean getVisionServiceEnabled() {
        return visionServiceEnabled;
    }

    @JsonProperty("visionServiceEnabled")
    public void setVisionServiceEnabled(Boolean visionServiceEnabled) {
        this.visionServiceEnabled = visionServiceEnabled;
    }

    @JsonProperty("imageCompareServiceEnabled")
    public Boolean getImageCompareServiceEnabled() {
        return imageCompareServiceEnabled;
    }

    @JsonProperty("imageCompareServiceEnabled")
    public void setImageCompareServiceEnabled(Boolean imageCompareServiceEnabled) {
        this.imageCompareServiceEnabled = imageCompareServiceEnabled;
    }

    @JsonProperty("domServiceEnabled")
    public Boolean getDomServiceEnabled() {
        return domServiceEnabled;
    }

    @JsonProperty("domServiceEnabled")
    public void setDomServiceEnabled(Boolean domServiceEnabled) {
        this.domServiceEnabled = domServiceEnabled;
    }

    @JsonProperty("proxyServiceEnabled")
    public Boolean getProxyServiceEnabled() {
        return proxyServiceEnabled;
    }

    @JsonProperty("proxyServiceEnabled")
    public void setProxyServiceEnabled(Boolean proxyServiceEnabled) {
        this.proxyServiceEnabled = proxyServiceEnabled;
    }

    @JsonProperty("anacondaEnvName")
    public String getAnacondaEnvName() {
        return anacondaEnvName;
    }

    @JsonProperty("anacondaEnvName")
    public void setAnacondaEnvName(String anacondaEnvName) {
        this.anacondaEnvName = anacondaEnvName;
    }

    @JsonProperty("anacondaActivatePath")
    public String getAnacondaActivatePath() {
        return anacondaActivatePath;
    }

    @JsonProperty("anacondaActivatePath")
    public void setAnacondaActivatePath(String anacondaActivatePath) {
        this.anacondaActivatePath = anacondaActivatePath;
    }

    @JsonProperty("autoHandleIOSSystemDialog")
    public Boolean getAutoHandleIOSSystemDialog() {
        return autoHandleIOSSystemDialog;
    }

    @JsonProperty("autoHandleIOSSystemDialog")
    public void setAutoHandleIOSSystemDialog(Boolean autoHandleIOSSystemDialog) {
        this.autoHandleIOSSystemDialog = autoHandleIOSSystemDialog;
    }

    @JsonProperty("pythonPath")
    public String getPythonPath() {
        return pythonPath;
    }

    @JsonProperty("pythonPath")
    public void setPythonPath(String pythonPath) {
        this.pythonPath = pythonPath;
    }

    @JsonProperty("textClassifierEnabled")
    public Boolean getTextClassifierEnabled() {
        return textClassifierEnabled;
    }

    @JsonProperty("textClassifierEnabled")
    public void setTextClassifierEnabled(Boolean textClassifierEnabled) {
        this.textClassifierEnabled = textClassifierEnabled;
    }

    @JsonProperty("vaVisualizerEnabled")
    public Boolean getVaVisualizerEnabled() {
        return vaVisualizerEnabled;
    }

    @JsonProperty("vaVisualizerEnabled")
    public void setVaVisualizerEnabled(Boolean vaVisualizerEnabled) {
        this.vaVisualizerEnabled = vaVisualizerEnabled;
    }

    @JsonProperty("customWDAPath")
    public String getCustomWDAPath() {
        return customWDAPath;
    }

    @JsonProperty("customWDAPath")
    public void setCustomWDAPath(String customWDAPath) {
        this.customWDAPath = customWDAPath;
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
