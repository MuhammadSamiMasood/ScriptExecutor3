package com.ebricks.scriptexecutor.config;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "EXECUTION_DATE",
        "BUILD",
        "EXECUTION_TYPE",
        "WALK",
        "EXECUTION_MODE",
        "DEVICE",
        "RESULT_ID",
        "PROJECT_ID",
        "USER_NAME",
        "PASSWORD",
        "UNINSTALL_AFTER_EXECUTION",
        "DASHBOARD_URL",
        "NODE_URL",
        "TESTDATA_SCRIPTS",
        "USERID",
        "APP_INFO",
        "TESTCASES",
        "APPIUM_PORT",
        "WDA_LOCAL_PORT",
        "EXECUTION"
})
public class ExecutionConfig {


    public static String EXECUTION_ID;

    @JsonProperty("EXECUTION_DATE")
    private Integer eXECUTIONDATE;
    @JsonProperty("BUILD")
    private Build bUILD;
    @JsonProperty("EXECUTION_TYPE")
    private Integer eXECUTIONTYPE;
    @JsonProperty("WALK")
    private Walk wALK;
    @JsonProperty("EXECUTION_MODE")
    private ExecutionMode eXECUTIONMODE;
    @JsonProperty("DEVICE")
    private Device dEVICE;
    @JsonProperty("RESULT_ID")
    private String rESULTID;
    @JsonProperty("PROJECT_ID")
    private String pROJECTID;
    @JsonProperty("USER_NAME")
    private String uSERNAME;
    @JsonProperty("PASSWORD")
    private String pASSWORD;
    @JsonProperty("UNINSTALL_AFTER_EXECUTION")
    private Boolean uNINSTALLAFTEREXECUTION;
    @JsonProperty("DASHBOARD_URL")
    private String dASHBOARDURL;
    @JsonProperty("NODE_URL")
    private String nODEURL;
    @JsonProperty("TESTDATA_SCRIPTS")
    private List<Object> tESTDATASCRIPTS = null;
    @JsonProperty("USERID")
    private String uSERID;
    @JsonProperty("APP_INFO")
    private Object aPPINFO;
    @JsonProperty("TESTCASES")
    private List<TestCase> tESTCASES = null;
    @JsonProperty("APPIUM_PORT")
    private Integer aPPIUMPORT;
    @JsonProperty("WDA_LOCAL_PORT")
    private Integer wDALOCALPORT;
    @JsonProperty("EXECUTION")
    private Execution eXECUTION;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    //------------------Singleton pattern code start-------------------//

    private static ExecutionConfig instance;

    private ExecutionConfig() {
    }

    public static ExecutionConfig getInstance() throws IOException {
        if (instance == null) {
            String basePath = System.getProperty("user.dir");
            System.out.println("Base path: " + basePath);
            instance = new ObjectMapper().readValue(new File(basePath + "/executions/" + ExecutionConfig.EXECUTION_ID + "/execution-config.json"), ExecutionConfig.class);
        }
        return instance;
    }

    //------------------Singleton pattern code end---------------//


    @JsonProperty("EXECUTION_DATE")
    public Integer getEXECUTIONDATE() {
        return eXECUTIONDATE;
    }

    @JsonProperty("EXECUTION_DATE")
    public void setEXECUTIONDATE(Integer eXECUTIONDATE) {
        this.eXECUTIONDATE = eXECUTIONDATE;
    }

    @JsonProperty("BUILD")
    public Build getBUILD() {
        return bUILD;
    }

    @JsonProperty("BUILD")
    public void setBUILD(Build bUILD) {
        this.bUILD = bUILD;
    }

    @JsonProperty("EXECUTION_TYPE")
    public Integer getEXECUTIONTYPE() {
        return eXECUTIONTYPE;
    }

    @JsonProperty("EXECUTION_TYPE")
    public void setEXECUTIONTYPE(Integer eXECUTIONTYPE) {
        this.eXECUTIONTYPE = eXECUTIONTYPE;
    }

    @JsonProperty("WALK")
    public Walk getWALK() {
        return wALK;
    }

    @JsonProperty("WALK")
    public void setWALK(Walk wALK) {
        this.wALK = wALK;
    }

    @JsonProperty("EXECUTION_MODE")
    public ExecutionMode getEXECUTIONMODE() {
        return eXECUTIONMODE;
    }

    @JsonProperty("EXECUTION_MODE")
    public void setEXECUTIONMODE(ExecutionMode eXECUTIONMODE) {
        this.eXECUTIONMODE = eXECUTIONMODE;
    }

    @JsonProperty("DEVICE")
    public Device getDEVICE() {
        return dEVICE;
    }

    @JsonProperty("DEVICE")
    public void setDEVICE(Device dEVICE) {
        this.dEVICE = dEVICE;
    }

    @JsonProperty("RESULT_ID")
    public String getRESULTID() {
        return rESULTID;
    }

    @JsonProperty("RESULT_ID")
    public void setRESULTID(String rESULTID) {
        this.rESULTID = rESULTID;
    }

    @JsonProperty("PROJECT_ID")
    public String getPROJECTID() {
        return pROJECTID;
    }

    @JsonProperty("PROJECT_ID")
    public void setPROJECTID(String pROJECTID) {
        this.pROJECTID = pROJECTID;
    }

    @JsonProperty("USER_NAME")
    public String getUSERNAME() {
        return uSERNAME;
    }

    @JsonProperty("USER_NAME")
    public void setUSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    @JsonProperty("PASSWORD")
    public String getPASSWORD() {
        return pASSWORD;
    }

    @JsonProperty("PASSWORD")
    public void setPASSWORD(String pASSWORD) {
        this.pASSWORD = pASSWORD;
    }

    @JsonProperty("UNINSTALL_AFTER_EXECUTION")
    public Boolean getUNINSTALLAFTEREXECUTION() {
        return uNINSTALLAFTEREXECUTION;
    }

    @JsonProperty("UNINSTALL_AFTER_EXECUTION")
    public void setUNINSTALLAFTEREXECUTION(Boolean uNINSTALLAFTEREXECUTION) {
        this.uNINSTALLAFTEREXECUTION = uNINSTALLAFTEREXECUTION;
    }

    @JsonProperty("DASHBOARD_URL")
    public String getDASHBOARDURL() {
        return dASHBOARDURL;
    }

    @JsonProperty("DASHBOARD_URL")
    public void setDASHBOARDURL(String dASHBOARDURL) {
        this.dASHBOARDURL = dASHBOARDURL;
    }

    @JsonProperty("NODE_URL")
    public String getNODEURL() {
        return nODEURL;
    }

    @JsonProperty("NODE_URL")
    public void setNODEURL(String nODEURL) {
        this.nODEURL = nODEURL;
    }

    @JsonProperty("TESTDATA_SCRIPTS")
    public List<Object> getTESTDATASCRIPTS() {
        return tESTDATASCRIPTS;
    }

    @JsonProperty("TESTDATA_SCRIPTS")
    public void setTESTDATASCRIPTS(List<Object> tESTDATASCRIPTS) {
        this.tESTDATASCRIPTS = tESTDATASCRIPTS;
    }

    @JsonProperty("USERID")
    public String getUSERID() {
        return uSERID;
    }

    @JsonProperty("USERID")
    public void setUSERID(String uSERID) {
        this.uSERID = uSERID;
    }

    @JsonProperty("APP_INFO")
    public Object getAPPINFO() {
        return aPPINFO;
    }

    @JsonProperty("APP_INFO")
    public void setAPPINFO(Object aPPINFO) {
        this.aPPINFO = aPPINFO;
    }

    @JsonProperty("TESTCASES")
    public List<TestCase> getTESTCASES() {
        return tESTCASES;
    }

    @JsonProperty("TESTCASES")
    public void setTESTCASES(List<TestCase> tESTCASES) {
        this.tESTCASES = tESTCASES;
    }

    @JsonProperty("APPIUM_PORT")
    public Integer getAPPIUMPORT() {
        return aPPIUMPORT;
    }

    @JsonProperty("APPIUM_PORT")
    public void setAPPIUMPORT(Integer aPPIUMPORT) {
        this.aPPIUMPORT = aPPIUMPORT;
    }

    @JsonProperty("WDA_LOCAL_PORT")
    public Integer getWDALOCALPORT() {
        return wDALOCALPORT;
    }

    @JsonProperty("WDA_LOCAL_PORT")
    public void setWDALOCALPORT(Integer wDALOCALPORT) {
        this.wDALOCALPORT = wDALOCALPORT;
    }

    @JsonProperty("EXECUTION")
    public Execution getEXECUTION() {
        return eXECUTION;
    }

    @JsonProperty("EXECUTION")
    public void setEXECUTION(Execution eXECUTION) {
        this.eXECUTION = eXECUTION;
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
