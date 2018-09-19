package com.zalinius.utilities;

public class Position {
    private double x, y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void move(double newX, double newY){
        this.x = newX;
        this.y = newY;
    }
    
    public double x() {
    	return x;
    }
    
    public double y() {
    	return y;
    }

}
