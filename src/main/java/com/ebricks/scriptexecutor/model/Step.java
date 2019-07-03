package com.ebricks.scriptexecutor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

    @JsonProperty("stepId")
    int id;
    @JsonProperty("element")
    private UIElement uiElement;
    private Event event;
    private Screen screen;
    private String domUrl;
    private Result result;

    public UIElement getUiElement() {
        return uiElement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("element")
    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public String getDomUrl() {
        return domUrl;
    }

    public void setDomUrl(String domUrl) {
        this.domUrl = domUrl;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
