package com.ebricks.scriptexecutor.model.uda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class BaseResult {

    @JsonIgnore
    private List<Integer> position = null;
    @JsonIgnore
    private List<Integer> size = null;
    @JsonIgnore
    private String value;
    @JsonIgnore
    private Boolean display;

    @JsonProperty("position")
    public List<Integer> getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(List<Integer> position) {
        this.position = position;
    }

    @JsonProperty("size")
    public List<Integer> getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(List<Integer> size) {
        this.size = size;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("display")
    public Boolean getDisplay() {
        return display;
    }

    @JsonProperty("display")
    public void setDisplay(Boolean display) {
        this.display = display;
    }

}
