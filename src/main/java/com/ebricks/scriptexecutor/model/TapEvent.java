package com.ebricks.scriptexecutor.model;

public class TapEvent extends Event {

    public TapEvent(){
        setType("tap");
    }

    private double startX;
    private double startY;

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

}
