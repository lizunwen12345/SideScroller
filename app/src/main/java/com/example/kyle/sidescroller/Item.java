package com.example.kyle.sidescroller;

/**
 * Created by Kyle on 2016/5/19.
 */
public class Item {
    private int x;
    private int y;
    private int n = 0;
    public final int ItemSppedX = 15;
    public final int ItemSpeedY = 10;
    private int status = 1;

    public Item(int xx, int yy){
        x = xx;
        y = yy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getBottomY(){
        return y + 50;
    }

    public void setX(int xx){
        x = xx;
    }

    public void setY(int yy){
        y = yy;
    }

    public int getN(){
        return n;
    }

    public void setN(int nn){
        n = nn;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int s){
        status = s;
    }


}
