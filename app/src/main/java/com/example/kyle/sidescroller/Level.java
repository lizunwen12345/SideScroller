package com.example.kyle.sidescroller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.net.BindException;
import java.util.ArrayList;

/**
 * Created by Zunwen on 2016/5/9.
 */
public class Level {
    public final int number;
    public final int LandDistance = 78;
    public final int LandJumpHeight = 160;
    private int WIDTH;
    private int HEIGHT;
    int LevelLeftPoint = 0;
    int LevelUpPoint = 0;
    final int BottomLand;
    private int counter = 1;
    int FireCounter;
    final int FireFrequency = 33;
    double AppearPossibility;
    final double ItemPossibility = 0.75;
    Paint Text = new Paint();
    int EnemyCounter = 0;
    final int EnemyWaitCounter = 100;
    int ItemCounter = 0;
    final int ItemStep = 30;
    int PassCounter;
    boolean ItemAppear;
    final int FirstSize = 10;
    boolean first[] = new boolean[FirstSize];
    int distance = 0;
    int WholeDistance;

    public Level(int n, int WIDTH, int HEIGHT)
    {
        number = n;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        BottomLand = (int) (HEIGHT * 0.72 - 4);
        for (int i = 0; i < FirstSize; i++){
            first[i]=true;
        }
        if (n == 1){
            AppearPossibility = 0.997;
            PassCounter = 400;
        }
        else if (n == 2){
            AppearPossibility = 0.996;
            PassCounter = 430;
        }
        else if (n == 3){
            AppearPossibility = 0.995;
            PassCounter = 480;
        }
        WholeDistance = PassCounter*22;
    }

    public int getCounter(){
        return counter;
    }

    public void ResetCounter(){
        counter = 0;
    }

    public int getPassCounter(){
        return PassCounter;
    }

    public void ResetFireCounter(){
        FireCounter = 0;
    }

    public void buildLevel (int number, Canvas c, ArrayList<Terrain> Lands, ArrayList<Enemy> Enemy1s, ArrayList<Enemy> Enemy2s)
    {
        // build everything including items and enemy for level 1
        if (number == 1) {
            AddLandLine(0, 30, Lands, 0, BottomLand);
            AddLandLine(30, 5, Lands, (int) (WIDTH * 0.3), BottomLand - LandJumpHeight);
            AddLandLine(35, 5, Lands, (int) (WIDTH * 0.3), BottomLand - 2*LandJumpHeight);
            AddLandLine(40, 5, Lands, (int) (WIDTH * 0.6), BottomLand + LandJumpHeight);
            AddLandLine(45, 30, Lands, 33*LandDistance, BottomLand);
            AddLandLine(75, 30, Lands, 66*LandDistance, BottomLand);
            AddLandLine(105, 5, Lands, (int)(WIDTH*1.4), BottomLand - 2*LandJumpHeight);
            AddLandLine(120, 5, Lands, 98*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(125, 5, Lands, 105*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(130, 20, Lands, 112*LandDistance, BottomLand-3*LandJumpHeight);
            AddLandLine(150, 10, Lands, 107*LandDistance, BottomLand+LandJumpHeight);
            AddEnemy1(0, 1, Enemy1s, (int)(WIDTH*0.35), BottomLand-LandJumpHeight, -3, 0);
            AddEnemy1(1, 1, Enemy1s, (int)(WIDTH*0.35), BottomLand-2*LandJumpHeight, -3, 0);
            AddEnemy1(2, 1, Enemy1s, (int)(WIDTH*0.65), BottomLand+LandJumpHeight, -2, 0);
            AddEnemy1(3, 1, Enemy1s, (int)(WIDTH*1.5), BottomLand, -1, 0);
            AddEnemy1(4, 1, Enemy1s, (int)(WIDTH*1.5), BottomLand - 2*LandJumpHeight, -3, 0);
            AddEnemy1(5, 1, Enemy1s, 67*LandDistance, BottomLand, -1, 0);
            AddEnemy1(6, 1, Enemy1s, 95*LandDistance, BottomLand, -1, 0);
            AddEnemy1(7, 1, Enemy1s, 98*LandDistance, BottomLand-LandJumpHeight, -3, 0);
            AddEnemy1(8, 1, Enemy1s, 105*LandDistance, BottomLand-2*LandJumpHeight, -3, 0);
            AddEnemy1(9, 1, Enemy1s, 112*LandDistance, BottomLand-3*LandJumpHeight, -3, 0);
            AddEnemy1(10, 1, Enemy1s, 102*LandDistance, BottomLand-LandJumpHeight, -1, 0);
            AddEnemy1(11, 1, Enemy1s, 109*LandDistance, BottomLand-2*LandJumpHeight, -1, 0);
            AddEnemy1(12, 1, Enemy1s, 116*LandDistance, BottomLand-3*LandJumpHeight, -1, 0);
            AddEnemy1(13, 2, Enemy1s, 108*LandDistance, BottomLand+LandJumpHeight, -2, 6*LandDistance);
        }
        else if (number == 2){
            AddLandLine(0, 30, Lands, 0, BottomLand);
            AddLandLine(30, 5, Lands, 32*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(35, 5, Lands, 39*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(40, 5, Lands, 46*LandDistance, BottomLand-3*LandJumpHeight);
            AddLandLine(45, 5, Lands, 53*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(50, 5, Lands, 60*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(55, 20, Lands, 67*LandDistance, BottomLand);
            AddLandLine(75, 5, Lands, 89*LandDistance, BottomLand+LandJumpHeight);
            AddLandLine(80, 5, Lands, 96*LandDistance, BottomLand);
            AddLandLine(85, 5, Lands, 103*LandDistance, BottomLand+LandJumpHeight);
            AddLandLine(90, 25, Lands, 110*LandDistance, BottomLand);
            AddVLandLine(115, 15, Lands, 89*LandDistance, BottomLand-2*LandJumpHeight);
            AddEnemy1(0, 4, Enemy1s, 90*LandDistance, BottomLand-2*LandJumpHeight, -3, 4*LandDistance);
            AddEnemy1(4, 3, Enemy1s, 92*LandDistance, BottomLand-2*LandJumpHeight, 3, 4*LandDistance);
            AddEnemy1(7, 1, Enemy1s, 29*LandDistance, BottomLand, -1, 0);
            AddEnemy1(8, 1, Enemy1s, 36*LandDistance, BottomLand-LandJumpHeight, -1, 0);
            AddEnemy1(9, 1, Enemy1s, 43*LandDistance, BottomLand-2*LandJumpHeight, -1, 0);
            AddEnemy1(10, 1, Enemy1s, 50*LandDistance, BottomLand-3*LandJumpHeight, -1, 0);
            AddEnemy1(11, 1, Enemy1s, 53*LandDistance, BottomLand-2*LandJumpHeight, -1, 0);
            AddEnemy1(12, 1, Enemy1s, 60*LandDistance, BottomLand-LandJumpHeight, -2, 0);
            AddEnemy1(13, 1, Enemy1s, 67*LandDistance, BottomLand, -2, 0);
        }
        else if (number == 3){
            AddLandLine(0, 15, Lands, 0, BottomLand-3*LandJumpHeight);
            AddLandLine(15, 5, Lands, 17*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(20, 5, Lands, 23*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(25, 5, Lands, 30*LandDistance, BottomLand);
            AddLandLine(30, 3, Lands, 10*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(35, 3, Lands, 17*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(40, 3, Lands, 24*LandDistance, BottomLand);
            AddLandLine(45, 5, Lands, 37*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(50, 5, Lands, 43*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(55, 20, Lands, 50*LandDistance, BottomLand-3*LandJumpHeight);
            AddLandLine(75, 10, Lands, 65*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(85, 20, Lands, 60*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(105, 30, Lands, 65*LandDistance, BottomLand);
            AddLandLine(135, 5, Lands, 90*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(140, 5, Lands, 90*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(145, 5, Lands, 90*LandDistance, BottomLand-3*LandJumpHeight);
            AddLandLine(150, 15, Lands, 105*LandDistance, BottomLand);
            AddLandLine(165, 3, Lands, 122*LandDistance, BottomLand-LandJumpHeight);
            AddLandLine(170, 3, Lands, 127*LandDistance, BottomLand-2*LandJumpHeight);
            AddLandLine(175, 10, Lands, 132*LandDistance, BottomLand-3*LandJumpHeight);
            AddEnemy1(0, 1, Enemy1s, 10*LandDistance, BottomLand-2*LandJumpHeight, 1, 0);
            AddEnemy1(1, 1, Enemy1s, 17*LandDistance, BottomLand-LandJumpHeight, 1, 0);
            AddEnemy1(2, 1, Enemy1s, 24*LandDistance, BottomLand, 1, 0);
            AddEnemy1(3, 1, Enemy1s, 12*LandDistance, BottomLand-2*LandJumpHeight, -2, 0);
            AddEnemy1(4, 1, Enemy1s, 19*LandDistance, BottomLand-LandJumpHeight, -2, 0);
            AddEnemy1(5, 1, Enemy1s, 26*LandDistance, BottomLand, -2, 0);
            AddEnemy1(6, 1, Enemy1s, 37*LandDistance, BottomLand-LandJumpHeight, -3, 0);
            AddEnemy1(7, 1, Enemy1s, 43*LandDistance, BottomLand-2*LandJumpHeight, -3, 0);
            AddEnemy1(8, 1, Enemy1s, 50*LandDistance, BottomLand-3*LandJumpHeight, -3, 0);
            AddEnemy1(9, 4, Enemy1s, 66*LandDistance, BottomLand, -2, 3*LandDistance);
            //AddEnemy1(13, 1, Enemy1s, 74*LandDistance, BottomLand-2*LandJumpHeight, -1, 0);
            AddEnemy1(14, 1, Enemy1s, 79*LandDistance, BottomLand-LandJumpHeight, -1, 0);
            AddEnemy1(15, 1, Enemy1s, 91*LandDistance, BottomLand-LandJumpHeight, -1, 0);
            AddEnemy1(16, 1, Enemy1s, 91*LandDistance, BottomLand-2*LandJumpHeight, -1, 0);
            AddEnemy1(17, 1, Enemy1s, 91*LandDistance, BottomLand-3*LandJumpHeight, -1, 0);
        }
    }

    // draw level
    public void DrawLevel(Canvas c, Bitmap land, ArrayList<Terrain> Lands, Player player,
                          int dir, int JumpMovingFlag, Bitmap Enemy1left, Bitmap Enemy1right, Bitmap Enemy1leftup,
                          Bitmap Enemy1leftdown, Bitmap Enemy1rightup, Bitmap Enemy1rightdown, ArrayList<Enemy> Enemy1s,
                          ArrayList<Enemy> Enemy2s, ArrayList<Bullet> EnemyBullets, Bitmap EnemyBullet,
                          Bitmap rightrun1, Bitmap rightrun2, Bitmap rightrun3, Bitmap rightrun4, Bitmap rightrun5,
                          Bitmap rightrun6, Bitmap leftrun1, Bitmap leftrun2, Bitmap leftrun3, Bitmap leftrun4,
                          Bitmap leftrun5, Bitmap leftrun6, ArrayList<Item> Items, Bitmap Item, Bitmap FireItem,
                          Bitmap LifeItem, ArrayList<Player> PlayerBullets)
    {
        int speed;
        boolean enemy2appear;
        Text.setTextSize(40);
        Text.setColor(Color.BLACK);

        //c.drawText(""+counter, WIDTH/3, HEIGHT/4, Text);
        //c.drawText(""+distance, WIDTH/5, HEIGHT/4, Text);
        if (number == 1){
            // scroller rolling
            enemy2appear = Appear();
            if (enemy2appear || EnemyCounter != 0){
                if (EnemyCounter != EnemyWaitCounter) {
                    c.drawText("Warning! Enemy Coming!", WIDTH / 2, HEIGHT / 5, Text);
                    EnemyCounter++;
                }
                else {
                    AddEnemy2(Enemy2s, 0, BottomLand, 1);
                    AddEnemy2(Enemy2s, WIDTH - 50, BottomLand, -1);
                    EnemyCounter = 0;
                }
            }
            if (ItemCounter != 0){
                AddItem(Items);
            }
            if (player.getX()<=LevelLeftPoint+(int)(0.36*WIDTH)) {
                DisplayLandLine(c, land, Lands);
                DisplayEnemy1s(c, Enemy1left, Enemy1right, Enemy1leftup, Enemy1leftdown, Enemy1rightup, Enemy1rightdown, Enemy1s, FireCounter, EnemyBullets);
                DisplayEnemyBullets(c, EnemyBullet, EnemyBullets);
                DisplayEnemy2s(c, rightrun1, rightrun2, rightrun3, rightrun4, rightrun5, rightrun6, Enemy2s,
                        leftrun1, leftrun2, leftrun3, leftrun4, leftrun5, leftrun6);
                DisplayItems(c, Items, Item, FireItem, LifeItem);
            }
            else{
                speed = player.CheckHorizontalSpeed(dir, JumpMovingFlag);
                ScreenRolling(Lands, Enemy1s, Enemy2s, Items, player, EnemyBullets, PlayerBullets, speed);
                DisplayLandLine(c, land, Lands);
                DisplayEnemy1s(c, Enemy1left, Enemy1right, Enemy1leftup, Enemy1leftdown, Enemy1rightup, Enemy1rightdown, Enemy1s, FireCounter, EnemyBullets);
                DisplayEnemyBullets(c, EnemyBullet, EnemyBullets);
                DisplayEnemy2s(c, rightrun1, rightrun2, rightrun3, rightrun4, rightrun5, rightrun6, Enemy2s,
                        leftrun1, leftrun2, leftrun3, leftrun4, leftrun5, leftrun6);
                DisplayItems(c, Items, Item, FireItem, LifeItem);
            }
            FireCounter = FireCounterReset(FireCounter);
        }
        else if (number == 2){
            if (distance > 1300 && distance < 1350 && first[0]){
                AddEnemy2(Enemy2s, (int)(WIDTH*0.7), BottomLand-2*LandJumpHeight, -1);
                AddEnemy2(Enemy2s, (int)(WIDTH*0.76), BottomLand-2*LandJumpHeight, -1);
                first[0] = false;
            }
            if (distance > 2100 && distance < 2150 && first[1]){
                AddEnemy2(Enemy2s, (int)(WIDTH*0.6), BottomLand-2*LandJumpHeight, -1);
                AddEnemy2(Enemy2s, (int)(WIDTH*0.66), BottomLand-2*LandJumpHeight, -1);
                first[1] = false;
            }
            if (distance > 2600 && distance < 2650 && first[2]){
                AddEnemy2(Enemy2s, (int)(WIDTH*0.6), BottomLand-3*LandJumpHeight, -1);
                AddEnemy2(Enemy2s, (int)(WIDTH*0.66), BottomLand-3*LandJumpHeight, -1);
                first[2] = false;
            }
            if (distance > 3750 && distance < 3800 && first[3]){
                AddEnemy2(Enemy2s, (int)(WIDTH*0.07), BottomLand-3*LandJumpHeight, 1);
                first[3] = false;
            }
            if (distance > 4200 && distance < 4250 && first[4]){
                AddEnemy2(Enemy2s, (int)(WIDTH*0.07), BottomLand-2*LandJumpHeight, 1);
                first[4] = false;
            }

            enemy2appear = Appear();
            if (enemy2appear || EnemyCounter != 0){
                if (EnemyCounter != EnemyWaitCounter) {
                    c.drawText("Warning! Enemy Coming!", WIDTH / 2, HEIGHT / 5, Text);
                    EnemyCounter++;
                }
                else {
                    AddEnemy2(Enemy2s, 0, BottomLand, 1);
                    AddEnemy2(Enemy2s, WIDTH - 50, BottomLand, -1);
                    EnemyCounter = 0;
                }
            }
            if (ItemCounter != 0){
                AddItem(Items);
            }
            if (player.getX()<=LevelLeftPoint+(int)(0.36*WIDTH)) {
                DisplayLandLine(c, land, Lands);
                DisplayEnemy1s(c, Enemy1left, Enemy1right, Enemy1leftup, Enemy1leftdown, Enemy1rightup, Enemy1rightdown, Enemy1s, FireCounter, EnemyBullets);
                DisplayEnemyBullets(c, EnemyBullet, EnemyBullets);
                DisplayEnemy2s(c, rightrun1, rightrun2, rightrun3, rightrun4, rightrun5, rightrun6, Enemy2s,
                        leftrun1, leftrun2, leftrun3, leftrun4, leftrun5, leftrun6);
                DisplayItems(c, Items, Item, FireItem, LifeItem);
            }
            else{
                speed = player.CheckHorizontalSpeed(dir, JumpMovingFlag);
                ScreenRolling(Lands, Enemy1s, Enemy2s, Items, player, EnemyBullets, PlayerBullets, speed);
                DisplayLandLine(c, land, Lands);
                DisplayEnemy1s(c, Enemy1left, Enemy1right, Enemy1leftup, Enemy1leftdown, Enemy1rightup, Enemy1rightdown, Enemy1s, FireCounter, EnemyBullets);
                DisplayEnemyBullets(c, EnemyBullet, EnemyBullets);
                DisplayEnemy2s(c, rightrun1, rightrun2, rightrun3, rightrun4, rightrun5, rightrun6, Enemy2s,
                        leftrun1, leftrun2, leftrun3, leftrun4, leftrun5, leftrun6);
                DisplayItems(c, Items, Item, FireItem, LifeItem);
            }
            FireCounter = FireCounterReset(FireCounter);
        }
        else if (number == 3){
            if (distance > 8500 && distance < 8550 && first[0]){
                AddEnemy2(Enemy2s, (int)(WIDTH*0.64), BottomLand-LandJumpHeight, -1);
                AddEnemy2(Enemy2s, (int)(WIDTH*0.8), BottomLand-2*LandJumpHeight, -1);
                AddEnemy2(Enemy2s, (int)(WIDTH*0.99), BottomLand-3*LandJumpHeight, -1);
                first[0] = false;
            }
            if (distance > 7700 && distance < 7750 && first[1]){
                AddEnemy1(18, 3, Enemy1s, (int)(WIDTH*0.63), BottomLand, -1, 2*LandDistance);
                first[1] = false;
            }
            // scroller rolling
            enemy2appear = Appear();
            if (enemy2appear || EnemyCounter != 0){
                if (EnemyCounter != EnemyWaitCounter) {
                    c.drawText("Warning! Enemy Coming!", WIDTH / 2, HEIGHT / 5, Text);
                    EnemyCounter++;
                }
                else {
                    AddEnemy2(Enemy2s, 0, BottomLand-3*LandJumpHeight, 1);
                    AddEnemy2(Enemy2s, WIDTH - 50, BottomLand-3*LandJumpHeight, -1);
                    EnemyCounter = 0;
                }
            }
            if (ItemCounter != 0){
                AddItem(Items);
            }
            if (player.getX()<=LevelLeftPoint+(int)(0.36*WIDTH)) {
                DisplayLandLine(c, land, Lands);
                DisplayEnemy1s(c, Enemy1left, Enemy1right, Enemy1leftup, Enemy1leftdown, Enemy1rightup, Enemy1rightdown, Enemy1s, FireCounter, EnemyBullets);
                DisplayEnemyBullets(c, EnemyBullet, EnemyBullets);
                DisplayEnemy2s(c, rightrun1, rightrun2, rightrun3, rightrun4, rightrun5, rightrun6, Enemy2s,
                        leftrun1, leftrun2, leftrun3, leftrun4, leftrun5, leftrun6);
                DisplayItems(c, Items, Item, FireItem, LifeItem);
            }
            else{
                speed = player.CheckHorizontalSpeed(dir, JumpMovingFlag);
                ScreenRolling(Lands, Enemy1s, Enemy2s, Items, player, EnemyBullets, PlayerBullets, speed);
                DisplayLandLine(c, land, Lands);
                DisplayEnemy1s(c, Enemy1left, Enemy1right, Enemy1leftup, Enemy1leftdown, Enemy1rightup, Enemy1rightdown, Enemy1s, FireCounter, EnemyBullets);
                DisplayEnemyBullets(c, EnemyBullet, EnemyBullets);
                DisplayEnemy2s(c, rightrun1, rightrun2, rightrun3, rightrun4, rightrun5, rightrun6, Enemy2s,
                        leftrun1, leftrun2, leftrun3, leftrun4, leftrun5, leftrun6);
                DisplayItems(c, Items, Item, FireItem, LifeItem);
            }
            FireCounter = FireCounterReset(FireCounter);
        }
    }

    // Screen rolling
    public void ScreenRolling(ArrayList<Terrain> Lands, ArrayList<Enemy> Enemy1s, ArrayList<Enemy> Enemy2s,
                              ArrayList<Item> Items, Player player, ArrayList<Bullet> EnemyBullets,
                              ArrayList<Player> PlayerBullets, int speed){
        int i;
        if (PassCounter - counter > 30) {
            for (i = 0; i < Lands.size(); i++) {
                Lands.get(i).setX(Lands.get(i).getX() - speed);
            }
            for (i = 0; i < Enemy1s.size(); i++) {
                Enemy1s.get(i).setX(Enemy1s.get(i).getX() - speed);
            }
            for (i = 0; i < Enemy2s.size(); i++) {
                Enemy2s.get(i).setX(Enemy2s.get(i).getX() - speed);
            }
            for (i = 0; i < Items.size(); i++) {
                Items.get(i).setX(Items.get(i).getX() - speed);
            }
            for (i = 0; i < EnemyBullets.size(); i++) {
                EnemyBullets.get(i).setX(EnemyBullets.get(i).getX() - speed);
            }
            for (i = 0; i < PlayerBullets.size(); i++) {
                PlayerBullets.get(i).setX(PlayerBullets.get(i).getX() - speed);
            }
            player.setX(player.getX() - speed);
            counter++;
            distance = distance + speed;
            if (counter%ItemStep == 0){
                ItemAppear = ItemAppear();
                if (ItemAppear)
                {
                    ItemCounter++;
                }
            }
        }
        else
        {
            if (player.getX() > WIDTH){
                PassCounter = counter;
            }
        }
    }


    // Add land line
    public void AddLandLine(int cn, int n, ArrayList<Terrain> Lands,
    int x, int y)
    {
        int i, j;
        j = 0;
        for (i = cn; i < cn + n; i++) {
            Lands.add(new Terrain(x + j * LandDistance, y));
            j++;
        }
    }

    public void AddVLandLine(int cn, int n, ArrayList<Terrain> Lands,
                            int x, int y)
    {
        int i, j;
        j = 0;
        for (i = cn; i < cn + n; i++) {
            Lands.add(new Terrain(x + j * LandDistance, y));
            j++;
        }

        for (i = cn; i < cn + n; i++) {
            Lands.get(i).setVirtual(true);
        }
    }

    // display land line
    public void DisplayLandLine(Canvas c, Bitmap land, ArrayList<Terrain> Lands){
        int i;
        for (i = 0; i < Lands.size(); i++) {
            c.drawBitmap(land, Lands.get(i).getX(), Lands.get(i).getY(), null);
        }
    }

    // Add enemy 1
    public void AddEnemy1(int cn, int n, ArrayList<Enemy> Enemy1s, int x, int y, int dir, int gap){
        int i, j;
        j = 0;
        for (i = cn; i < cn + n; i++) {
            Enemy1s.add(new Enemy(x + j*gap, y, dir));
            j++;
        }
    }

    // Add enemy 2
    public void AddEnemy2(ArrayList<Enemy> Enemy2s, int x, int y, int dir){
        Enemy2s.add(new Enemy(x, y, dir));
    }

    // display enemy 2
    public void DisplayEnemy2s(Canvas c, Bitmap rightrun1, Bitmap rightrun2,
                               Bitmap rightrun3, Bitmap rightrun4, Bitmap rightrun5,
                               Bitmap rightrun6, ArrayList<Enemy> Enemy2s, Bitmap leftrun1,
                               Bitmap leftrun2, Bitmap leftrun3, Bitmap leftrun4, Bitmap leftrun5,
                               Bitmap leftrun6)
    {
        int i, x, y, s, n, d;
        for (i = 0; i < Enemy2s.size(); i++){
            x = Enemy2s.get(i).getX();
            y = Enemy2s.get(i).getY();
            s = Enemy2s.get(i).EnemyMovingSpeed;
            n = Enemy2s.get(i).getN();
            d = Enemy2s.get(i).getDir();
            if (d > 0) {
                if (n == 0) {
                    c.drawBitmap(rightrun1, x, y, null);
                    Enemy2s.get(i).setN(1);
                } else if (n == 1) {
                    c.drawBitmap(rightrun2, x, y, null);
                    Enemy2s.get(i).setN(2);
                } else if (n == 2) {
                    c.drawBitmap(rightrun3, x, y, null);
                    Enemy2s.get(i).setN(3);
                } else if (n == 3) {
                    c.drawBitmap(rightrun4, x, y, null);
                    Enemy2s.get(i).setN(4);
                } else if (n == 4) {
                    c.drawBitmap(rightrun5, x, y, null);
                    Enemy2s.get(i).setN(5);
                } else if (n == 5) {
                    c.drawBitmap(rightrun6, x, y, null);
                    Enemy2s.get(i).setN(0);
                }
                Enemy2s.get(i).setX(x + s);
            }
            else{
                if (n == 0) {
                    c.drawBitmap(leftrun1, x, y, null);
                    Enemy2s.get(i).setN(1);
                } else if (n == 1) {
                    c.drawBitmap(leftrun2, x, y, null);
                    Enemy2s.get(i).setN(2);
                } else if (n == 2) {
                    c.drawBitmap(leftrun3, x, y, null);
                    Enemy2s.get(i).setN(3);
                } else if (n == 3) {
                    c.drawBitmap(leftrun4, x, y, null);
                    Enemy2s.get(i).setN(4);
                } else if (n == 4) {
                    c.drawBitmap(leftrun5, x, y, null);
                    Enemy2s.get(i).setN(5);
                } else if (n == 5) {
                    c.drawBitmap(leftrun6, x, y, null);
                    Enemy2s.get(i).setN(0);
                }
                Enemy2s.get(i).setX(x - s);
            }
        }
    }

    // display enemy 1
    public void DisplayEnemy1s(Canvas c, Bitmap Enemy1left, Bitmap Enemy1right,
                               Bitmap Enemy1leftup, Bitmap Enemy1leftdown,
                               Bitmap Enemy1rightup, Bitmap Enemy1rightdown,
                               ArrayList<Enemy> Enemy1s, int FireCounter,
                               ArrayList<Bullet> EnemyBullets)
    {
        int i, d;
        for (i = 0; i < Enemy1s.size(); i++) {
            d = Enemy1s.get(i).getDir();
            if (d == -1) {
                c.drawBitmap(Enemy1left, Enemy1s.get(i).getX(), Enemy1s.get(i).getY(), null);
            }
            else if (d == 1){
                c.drawBitmap(Enemy1right, Enemy1s.get(i).getX(), Enemy1s.get(i).getY(), null);
            }
            else if (d == -2){
                c.drawBitmap(Enemy1leftup, Enemy1s.get(i).getX(), Enemy1s.get(i).getY(), null);
            }
            else if (d == 2){
                c.drawBitmap(Enemy1rightup, Enemy1s.get(i).getX(), Enemy1s.get(i).getY(), null);
            }
            else if (d == -3){
                c.drawBitmap(Enemy1leftdown, Enemy1s.get(i).getX(), Enemy1s.get(i).getY(), null);
            }
            else if (d == 3){
                c.drawBitmap(Enemy1rightdown, Enemy1s.get(i).getX(), Enemy1s.get(i).getY(), null);
            }
            if (FireCounter == FireFrequency) {
                Enemy1sFire(Enemy1s, EnemyBullets);
            }
        }
    }

    // Fire Counter increment and reset
    public int FireCounterReset(int FireCounter){
        if (FireCounter == FireFrequency){
            return 0;
        }
        else
        {
            return ++FireCounter;
        }
    }

    // All enemy1s Fire
    public void Enemy1sFire(ArrayList<Enemy> Enemy1s, ArrayList<Bullet> EnemyBullets){
        int i;
        int x, y, d;
        for (i = 0; i < Enemy1s.size(); i++) {
            x = Enemy1s.get(i).getGunPointX();
            y = Enemy1s.get(i).getGunPointY();
            d = Enemy1s.get(i).getDir();
            EnemyBullets.add(new Bullet(x, y, d));
        }
    }

    // Display Enemy Bullets
    public void DisplayEnemyBullets(Canvas c, Bitmap Bullet, ArrayList<Bullet> EnemyBullets){
        int i;
        int speed;
        int d;
        for (i = 0; i < EnemyBullets.size(); i++){
            speed = EnemyBullets.get(i).BulletSpeed;
            d = EnemyBullets.get(i).getDir();
            c.drawBitmap(Bullet, EnemyBullets.get(i).getX(), EnemyBullets.get(i).getY(), null);
            if (d == -1) {
                EnemyBullets.get(i).setX(EnemyBullets.get(i).getX() - speed);
            }
            else if (d == 1){
                EnemyBullets.get(i).setX(EnemyBullets.get(i).getX() + speed);
            }
            else if (d == -2){
                EnemyBullets.get(i).setX(EnemyBullets.get(i).getX() - (int)(0.7*speed));
                EnemyBullets.get(i).setY(EnemyBullets.get(i).getY() - (int)(0.7*speed));
            }
            else if (d == -3){
                EnemyBullets.get(i).setX(EnemyBullets.get(i).getX() - (int)(0.7*speed));
                EnemyBullets.get(i).setY(EnemyBullets.get(i).getY() + (int)(0.7*speed));
            }
            else if (d == 2){
                EnemyBullets.get(i).setX(EnemyBullets.get(i).getX() + (int)(0.7*speed));
                EnemyBullets.get(i).setY(EnemyBullets.get(i).getY() - (int)(0.7*speed));
            }
            else if (d == 3){
                EnemyBullets.get(i).setX(EnemyBullets.get(i).getX() + speed);
                EnemyBullets.get(i).setY(EnemyBullets.get(i).getY() + speed);
            }
        }
    }

    // Enemy 2 Appear Function
    public boolean Appear(){
        if (Math.random()>AppearPossibility)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Item Appear Function
    public boolean ItemAppear(){
        if (Math.random()>ItemPossibility){
            return true;
        }
        else{
            return false;
        }
    }

    // Add a item
    public void AddItem(ArrayList<Item> Items){
        if (ItemCounter > 0) {
            Items.add(new Item(0, (int) (HEIGHT * 0.1)));
            ItemCounter--;
        }
    }

    // display Item
    public void DisplayItems(Canvas c, ArrayList<Item> Items, Bitmap Item, Bitmap FireItem, Bitmap LifeItem)
    {
        int i, n, sX, sY, s;

        for (i = 0; i < Items.size(); i++){
            sX = Items.get(i).ItemSppedX;
            sY = Items.get(i).ItemSpeedY;
            n = Items.get(i).getN();
            s = Items.get(i).getStatus();
            if (s == 1) {
                c.drawBitmap(Item, Items.get(i).getX(), Items.get(i).getY(), null);
                if (n < 3) {
                    Items.get(i).setX(Items.get(i).getX() + sX);
                    Items.get(i).setY(Items.get(i).getY() + sY);
                    Items.get(i).setN(n + 1);
                } else if (n >= 3 && n < 6) {
                    Items.get(i).setX(Items.get(i).getX() + sX);
                    Items.get(i).setY(Items.get(i).getY() - sY);
                    if (n != 5) {
                        Items.get(i).setN(n + 1);
                    } else {
                        Items.get(i).setN(0);
                    }
                }
            }
            else if (s == 2){
                c.drawBitmap(FireItem, Items.get(i).getX(), Items.get(i).getY(), null);
            }
            else if (s == 3){
                c.drawBitmap(LifeItem, Items.get(i).getX(), Items.get(i).getY(), null);
            }
        }
    }

}

