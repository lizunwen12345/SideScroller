package com.example.kyle.sidescroller;

/**
 * Created by Zunwen on 2016/5/9.
 */
public class Enemy {
    private int x;
    private int y;
    public final int Enemy1Height = 110;
    public final int EnemyMovingSpeed = 22;
    boolean alive = true;

    // -1 is stand left, -2 is left up, -3 is left down
    // 1 is stand right, 2 is right up, 3 is right down
    private int dir;
    private int n;

    public Enemy(int xx, int yy){
        x = xx;
        y = yy;
    }

    public Enemy(int xx, int yy, int dir){
        x = xx;
        y = yy;
        this.dir = dir;
    }

    public int getN(){
        return n;
    }

    public void setN(int n){
        this.n = n;
    }

    public boolean CheckAlive(){
        return alive;
    }

    public void Dead(){
        alive = false;
    }

    public int getX(){
        return x;
    }

    public int getCenterX(){
        return x;
    }

    public int getCenterY(){
        return y;
    }

    public int getBottomY(){
        return y + 20;
    }

    public int getY(){
        return y - Enemy1Height;
    }

    public void setX(int xx){
        x = xx;
    }

    public void setY(int yy){
        y = yy + Enemy1Height;
    }

    public int getGunPointX(){
        if (dir < 0) {
            return x;
        }
        else{
            if (dir == 2) {
                return x + 50;
            }
            else{
                return x + 80;
            }
        }
    }

    public int getGunPointY(){
        if (dir == -1 || dir == 1) {
            return y - Enemy1Height + 20;
        }
        else if (dir == -2 || dir == 2){
            return y - Enemy1Height;
        }
        else if (dir == -3 || dir == 3){
            return y - Enemy1Height + 75;
        }
        return  0;
    }

    public int getDir(){
        return dir;
    }

}
