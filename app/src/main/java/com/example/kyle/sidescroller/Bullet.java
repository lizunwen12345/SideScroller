package com.example.kyle.sidescroller;

/**
 * Created by Kyle on 2016/5/17.
 */
public class Bullet {
    private int x;
    private int y;
    boolean flying = true;
    public final int BulletSpeed = 26;
    int dir;
    // same as enemy

    public Bullet(int xx, int yy, int dir){
        x = xx;
        y = yy;
        this.dir = dir;
    }

    public int getDir(){
        return  dir;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int xx){
        x = xx;
    }

    public void setY(int yy){
        y = yy;
    }
}
