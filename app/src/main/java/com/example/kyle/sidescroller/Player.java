package com.example.kyle.sidescroller;

import java.util.ArrayList;

/**
 * Created by Zunwen on 2016/5/9.
 */
public class Player {
    private int x;
    private int y;
    private boolean flying;
    private int direction;
    boolean alive = true;
    private final int PlayerHeight = 110;

    public final int runningspeed = 22;
    public final int bulletspeed = 15;
    public final int jumpmovingdistance = 15;
    final int jumpheight = 16;
    final int jumptimes = 14;
    final int modification = 30;


    public Player(int xx, int yy){
        x = (int)(xx*0.05);
        y = (int)(yy*0.05);
    }

    // bullet
    public Player(int xx, int yy, boolean input, int dir){
        x = xx;
        y = yy;
        flying = input;
        direction = dir;
        // 1 is right, -1 is left, 0 is up, -5 is down, -10 is down during jump
    }

    public int getPlayerHeight(){
        return PlayerHeight;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getBottomY(){
        return y + 120;
    }

    public void setX(int xx){
        if (xx >= 10) {
            x = xx;
        }
        else{
            x = 10;
        }
    }

    public void setY(int yy){
        if (y <= 1000) {
            y = yy;
        }
        else
        {
            y = 1000;
            alive = false;
        }
    }

    public boolean CheckAlive(){
        return alive;
    }

    public void Dead(){
        alive = false;
    }

    public boolean CheckFlying(){
        return flying;
    }

    public void SetFlying(boolean input){
        flying = input;
    }

    public int getDirection(){
        return direction;
    }

    public int GetGunpointX(int dir){
        if (dir == 1) {
            return x + 40;
        }
        else if (dir == -1){
            return x;
        }
        else if (dir == 0){
            return x + 25;
        }
        else if (dir == -5){
            return x + 60;
        }
        else if (dir == -6){
            return x;
        }
        else if (dir == -10){
            return x + 25;
        }
        return 0;
    }

    public int GetGunpointY(int dir){
        if (dir == 1 || dir == -1) {
            return y + 35;
        }
        else if (dir == 0){
            return y;
        }
        else if (dir == -5){
            return y + 80;
        }
        else if (dir == -6){
            return y + 80;
        }
        else if (dir == -10){
            return y + 80;
        }
        return 0;
    }

    public int CheckHorizontalSpeed(int dir, int JumpMovingFlag){
        int speed;
        if (dir != 0){
            speed = runningspeed;
        }
        else{
            if (JumpMovingFlag != 0){
                speed = jumpmovingdistance;
            }
            else{
                speed = (int)(jumpmovingdistance*0.7);
            }
        }
        return speed;
    }


}
