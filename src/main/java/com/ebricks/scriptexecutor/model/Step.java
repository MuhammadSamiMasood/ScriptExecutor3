package com.ebricks.scriptexecutor.model;

import com.ebricks.scriptexecutor.model.uda.Uda;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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
    private List<Uda> uda = null;

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

    public List<Uda> getUda() {
        return uda;
    }

    public void setUda(List<Uda> uda) {
        this.uda = uda;
    }
}
