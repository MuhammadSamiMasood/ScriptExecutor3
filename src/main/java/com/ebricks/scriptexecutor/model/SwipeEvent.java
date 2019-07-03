package com.ebricks.scriptexecutor.model;

public class SwipeEvent extends Event {

    public SwipeEvent() {
        setType("swipe");
    }

    private int endY;
    private int endX;
    private int startY;
    private int startX;
    private int touchDuration;

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getTouchDuration() {
        return touchDuration;
    }

    public void setTouchDuration(int touchDuration) {
        this.touchDuration = touchDuration;
    }
}
