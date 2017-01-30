package com.example.kyle.sidescroller;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Kyle on 2016/5/9.
 */
public class instrument {
    private final int x;
    private final int y;
    private final Paint buttoncolor = new Paint();
    private int r;
    private final double buttonY = 0.88;

    public instrument(int width, int height){
        // instrument position is constant during the whole game
        // once set up, not changeable
        x = (int)(width*0.01);
        y = (int)(height*0.75);
    }

    public instrument(int xx, int yy, int r, int color){
        if (color == 2) {
            x = (int) (xx * 0.84);
            y = (int) (yy * buttonY);
        }
        else if (color == 3){
            x = (int) (xx * 0.74);
            y = (int) (yy * buttonY);
        }
        else if (color == 4){
            x = (int) (xx * 0.64);
            y = (int) (yy * buttonY);
        }
        else{
            x = (int) (xx * 0.94);
            y = (int) (yy * buttonY);
        }
        // 1 for red, 2 for blue, 3 for green, 4 for yellow
        if (color == 1) {
            buttoncolor.setColor(Color.RED);
        }
        else if (color == 2){
            buttoncolor.setColor(Color.RED);
        }
        else if (color == 3){
            buttoncolor.setColor(Color.GREEN);
        }
        else if (color == 4){
            buttoncolor.setColor(Color.YELLOW);
        }

        this.r = r;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getR(){
        return r;
    }

    public Paint getColor(){
        return buttoncolor;
    }

    public int leftbuttoncenterX()
    {
        return x + 70;
    }

    public int leftbuttoncenterY()
    {
        return y + 130;
    }

    public int rightbuttoncenterX()
    {
        return x + 220;
    }

    public int rightbuttoncenterY()
    {
        return y + 130;
    }

    public int upbuttoncenterX()
    {
        return x + 145;
    }

    public int upbuttoncenterY()
    {
        return y + 60;
    }

    public int downbuttoncenterX()
    {
        return x + 145;
    }

    public int downbuttoncenterY()
    {
        return y + 200;
    }
}
