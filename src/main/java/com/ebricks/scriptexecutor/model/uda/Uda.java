package com.ebricks.scriptexecutor.model.uda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Uda {



    @JsonProperty("node")
    @JsonIgnore
    private int node;
    @JsonProperty("type")
    @JsonIgnore
    private String type;
    @JsonProperty("value")
    @JsonIgnore
    private String value;
    @JsonProperty("title")
    @JsonIgnore
    private String title;
    @JsonProperty("elementType")
    @JsonIgnore
    private String elementType;
    @JsonProperty("result")
    @JsonIgnore
    private Result result;
    @JsonProperty("udaId")
    @JsonIgnore
    private Integer udaId;
    @JsonProperty("name")
    @JsonIgnore
    private String name;
    @JsonProperty("activity")
    @JsonIgnore
    private String activity;
    @JsonProperty("_id")
    @JsonIgnore
    private String _id;
    @JsonProperty("dynamicVars")
    @JsonIgnore
    private List<Object> dynamicVars = null;

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Integer getUdaId() {
        return udaId;
    }

    public void setUdaId(Integer udaId) {
        this.udaId = udaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Object> getDynamicVars() {
        return dynamicVars;
    }

    public void setDynamicVars(List<Object> dynamicVars) {
        this.dynamicVars = dynamicVars;
    }
}
