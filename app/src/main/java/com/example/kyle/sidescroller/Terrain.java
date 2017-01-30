package com.example.kyle.sidescroller;

/**
 * Created by Kyle on 2016/5/15.
 */
public class Terrain {
    private int x;
    private int y;
    private final int LandWidth = 70;
    private boolean virtual = false;

    public Terrain(int xx, int yy){
        x = xx;
        y = yy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getLandWidth(){
        return LandWidth;
    }

    public void setX(int xx){
        x = xx;
    }

    public void setY(int yy){
        y = yy;
    }

    public void setVirtual(boolean V){
        virtual = V;
    }

    public boolean getVirtual(){
        return virtual;
    }
}
