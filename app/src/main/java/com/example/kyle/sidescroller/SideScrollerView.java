package com.example.kyle.sidescroller;

/**
 * Created by Zunwen on 2016/5/9.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class SideScrollerView extends SurfaceView implements SurfaceHolder.Callback {
    int HEIGHT;
    int WIDTH;
    private SurfaceHolder holder;
    // 148*232 (30*46) each bitmap, upshoot is 148*288 (30*58), downshoot is 212*232 (42*46)
    // jump is 135*132 (30*29) each
    // zoom is around 600%
    // enemy1 157*202 (36*46) 600%
    // player bullet 50*50 (10*10) 600%
    // Map Land 190*95 (30*15) 600%

    private Bitmap standupright = BitmapFactory.decodeResource(getResources(), R.drawable.standupright);
    private Bitmap standupleft = BitmapFactory.decodeResource(getResources(), R.drawable.standupleft);
    private Bitmap rightrun1 = BitmapFactory.decodeResource(getResources(), R.drawable.rightrun1);
    private Bitmap rightrun2 = BitmapFactory.decodeResource(getResources(), R.drawable.rightrun2);
    private Bitmap rightrun3 = BitmapFactory.decodeResource(getResources(), R.drawable.rightrun3);
    private Bitmap rightrun4 = BitmapFactory.decodeResource(getResources(), R.drawable.rightrun4);
    private Bitmap rightrun5 = BitmapFactory.decodeResource(getResources(), R.drawable.rightrun5);
    private Bitmap leftrun1 = BitmapFactory.decodeResource(getResources(), R.drawable.leftrun1);
    private Bitmap leftrun2 = BitmapFactory.decodeResource(getResources(), R.drawable.leftrun2);
    private Bitmap leftrun3 = BitmapFactory.decodeResource(getResources(), R.drawable.leftrun3);
    private Bitmap leftrun4 = BitmapFactory.decodeResource(getResources(), R.drawable.leftrun4);
    private Bitmap leftrun5 = BitmapFactory.decodeResource(getResources(), R.drawable.leftrun5);
    private Bitmap upright = BitmapFactory.decodeResource(getResources(), R.drawable.upshootright);
    private Bitmap upleft = BitmapFactory.decodeResource(getResources(), R.drawable.upshootleft);
    private Bitmap downright = BitmapFactory.decodeResource(getResources(), R.drawable.downright);
    private Bitmap downleft = BitmapFactory.decodeResource(getResources(), R.drawable.downleft);
    private Bitmap instrument = BitmapFactory.decodeResource(getResources(), R.drawable.operation);
    private Bitmap leftjump1 = BitmapFactory.decodeResource(getResources(), R.drawable.leftjump1);
    private Bitmap leftjump2 = BitmapFactory.decodeResource(getResources(), R.drawable.leftjump2);
    private Bitmap leftjump3 = BitmapFactory.decodeResource(getResources(), R.drawable.leftjump3);
    private Bitmap leftjump4 = BitmapFactory.decodeResource(getResources(), R.drawable.leftjump4);
    private Bitmap rightjump1 = BitmapFactory.decodeResource(getResources(), R.drawable.rightjump1);
    private Bitmap rightjump2 = BitmapFactory.decodeResource(getResources(), R.drawable.rightjump2);
    private Bitmap rightjump3 = BitmapFactory.decodeResource(getResources(), R.drawable.rightjump3);
    private Bitmap rightjump4 = BitmapFactory.decodeResource(getResources(), R.drawable.rightjump4);
    private Bitmap enemy1left = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1left);
    private Bitmap enemy1leftup = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1leftup);
    private Bitmap enemy1leftdown = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1leftdown);
    private Bitmap enemy1rightup = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1rightup);
    private Bitmap enemy1rightdown = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1rightdown);
    private Bitmap enemy1right = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1right);
    private Bitmap PlayerBullet1 = BitmapFactory.decodeResource(getResources(), R.drawable.playerbullet);
    private Bitmap land = BitmapFactory.decodeResource(getResources(), R.drawable.land);
    private Bitmap EnemyBullet = BitmapFactory.decodeResource(getResources(), R.drawable.enemybullet1);
    private Bitmap enemy2leftrun1 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2lefttrun1);
    private Bitmap enemy2leftrun2 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2lefttrun2);
    private Bitmap enemy2leftrun3 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2lefttrun3);
    private Bitmap enemy2leftrun4 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2lefttrun4);
    private Bitmap enemy2leftrun5 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2lefttrun5);
    private Bitmap enemy2leftrun6 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2lefttrun6);
    private Bitmap enemy2rightrun1 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2rightrun1);
    private Bitmap enemy2rightrun2 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2rightrun2);
    private Bitmap enemy2rightrun3 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2rightrun3);
    private Bitmap enemy2rightrun4 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2rightrun4);
    private Bitmap enemy2rightrun5 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2rightrun5);
    private Bitmap enemy2rightrun6 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2rightrun6);
    private Bitmap Item = BitmapFactory.decodeResource(getResources(), R.drawable.ufo);
    private Bitmap FireItem = BitmapFactory.decodeResource(getResources(), R.drawable.ufo3);
    private Bitmap LifeItem = BitmapFactory.decodeResource(getResources(), R.drawable.ufo2);

    Draw D;
    boolean running = true;

    public SideScrollerView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        holder = getHolder();
        HEIGHT = getHeight();
        WIDTH = getWidth();
    }

    SideScrollerThread st;
    boolean firsttime = true;
    int dir = 1;
    int predir = 0;
    int WaitCount = 0;
    final int WaitFrame = 4;
    Paint BlackBack = new Paint();
    Level level;
    int LevelNumber = 1;
    boolean NewLife;
    boolean FreeFall;
    final int LifeCounterHeight = 30;
    final int ScoreX = 150;
    final int LevelNumberX = 400;
    final int AmmoX = 560;
    int score = 0;
    int Ammo = 60;
    int LifeCounter = 10;
    final int AllLevels = 4;

    @Override
    protected void onDraw(Canvas c) {
        if (firsttime) {
            D = new Draw(WIDTH, HEIGHT, rightrun1, rightrun2, rightrun3, rightrun4, rightrun5,
                    leftrun1, leftrun2, leftrun3, leftrun4, leftrun5, leftjump1, leftjump2, leftjump3, leftjump4,
                    rightjump1, rightjump2, rightjump3, rightjump4);
            level = new Level(LevelNumber, WIDTH, HEIGHT);
            level.buildLevel(LevelNumber, c, D.Lands, D.Enemy1s, D.Enemy2s);
            firsttime = false;
        }

        c.drawColor(Color.WHITE);
        BlackBack.setColor(Color.BLACK);
        BlackBack.setTextSize(40);
        c.drawText("Life: " + LifeCounter, 0, LifeCounterHeight, BlackBack);
        c.drawText("Score: " + score, ScoreX, LifeCounterHeight, BlackBack);
        c.drawText("Level: " + LevelNumber, LevelNumberX, LifeCounterHeight, BlackBack);
        c.drawText("Ammo: " + Ammo, AmmoX, LifeCounterHeight, BlackBack);
        if (level.getPassCounter() - level.getCounter() < 50){
            c.drawText("Approaching End!", WIDTH/10*4, HEIGHT/10, BlackBack);
        }
        // Black background
        //c.drawRect(0, 788, WIDTH, HEIGHT, BlackBack);

        level.DrawLevel(c, land, D.Lands, D.player, dir, jumpmovingflag, enemy1left, enemy1right, enemy1leftup, enemy1leftdown, enemy1rightup, enemy1rightdown, D.Enemy1s, D.Enemy2s, D.EnemyBullets, EnemyBullet,
                enemy2rightrun1, enemy2rightrun2, enemy2rightrun3, enemy2rightrun4, enemy2rightrun5, enemy2rightrun6,
                enemy2leftrun1, enemy2leftrun2, enemy2leftrun3, enemy2leftrun4, enemy2leftrun5, enemy2leftrun6, D.Items, Item,
                FireItem, LifeItem, D.PlayerBullets);
        dir = D.drawcharacter(c, standupright, standupleft, upright, upleft, downright, downleft, dir, predir, jumpmovingflag, prejumpmovingflag, HoldFlag);
        D.draw1(c, instrument);
        D.draw2(c);
        D.DrawPlayerBullets(c, PlayerBullet1);
        FreeFall = D.Gravity(dir);
        score += CheckImpact(D.player, D.PlayerBullets, D.EnemyBullets, D.Enemy1s, D.Enemy2s, D.Items, dir);
        NewLife = D.NewLife(D.player.CheckAlive());
        D.Clear(D.PlayerBullets, D.EnemyBullets, D.Enemy1s, D.Enemy2s, D.Items);

        // Check if the game end because of no life
        if (LifeCounter == 0){
            this.running = false;
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            c.drawPaint(paint);
            BlackBack.setTextSize(100);
            c.drawText("GAME OVER!", (int)(WIDTH*0.35), HEIGHT/2, BlackBack);
        }

        // Check if pass the current level
        if (level.getCounter() == level.getPassCounter()){
            LevelNumber++;
            level = null;
            firsttime = true;
            Ammo = 60;
        }

        // Reset PreJumpMovingFlag
        if (dir != 0){
            prejumpmovingflag = 0;
        }
        // Control shoot frequency
        if (WaitCount != 0) {
            WaitCount++;
        }
        if (WaitCount == WaitFrame){
            WaitCount = 0;
        }

        // Make a new life;
        if (NewLife){
            LifeCounter--;
            Ammo = 60;
            dir = 1;
            prejumpmovingflag = 0;
            WaitCount = 0;
            D.PlayerBullets.clear();
            D.EnemyBullets.clear();
            level.ResetFireCounter();
            level.EnemyCounter = 0;
        }

        // Check if All level clear
        if (LevelNumber == AllLevels){
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            c.drawPaint(paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(150);
            c.drawText("All 3 Levels Cleard!", WIDTH/10, HEIGHT / 2, paint);
            this.running = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        HEIGHT = getHeight();
        WIDTH = getWidth();
        Canvas c = holder.lockCanvas(null);
        onDraw(c);
        holder.unlockCanvasAndPost(c);
        st = new SideScrollerThread(getHolder(), this);
        st.setRunning(true);
        st.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        st.interrupt();
    }

    /*
    AtomicBoolean actionDownFlag = new AtomicBoolean(true);

    Thread movementThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (actionDownFlag.get())
            {

            }
        }
    });*/

    // these x and y used as motion event position
    float x;
    float y;
    int jumpmovingflag;
    int prejumpmovingflag;
    boolean firsttimeflag;
    boolean HoldFlag = true;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int touchrange = 35;

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            x = e.getX();
            y = e.getY();

            if (x >= D.ins.leftbuttoncenterX() - touchrange && x <= D.ins.leftbuttoncenterX() + touchrange
                    && y >= D.ins.leftbuttoncenterY() - touchrange && y <= D.ins.leftbuttoncenterY() + touchrange) {
                //move left
                dir = MovingLeft(dir);
                return true;
            } else if (x >= D.ins.rightbuttoncenterX() - touchrange && x <= D.ins.rightbuttoncenterX() + touchrange
                    && y >= D.ins.rightbuttoncenterY() - touchrange && y <= D.ins.rightbuttoncenterY() + touchrange) {
                //move right
                dir = MovingRight(dir);
                return true;
            }
            else if(x>=D.ins.upbuttoncenterX()-touchrange && x<=D.ins.upbuttoncenterX()+touchrange
                    && y>=D.ins.upbuttoncenterY()-touchrange && y<=D.ins.upbuttoncenterY()+touchrange)
            {
                //point up
                if (!FreeFall) {
                    dir = pointup(dir);
                }
                return true;
            }
            else if(e.getX()>=D.ins.downbuttoncenterX()-touchrange && e.getX()<=D.ins.downbuttoncenterX()+touchrange
                    && e.getY()>=D.ins.downbuttoncenterY()-touchrange && e.getY()<=D.ins.downbuttoncenterY()+touchrange)
            {
                // Hit the dirt
                if (!FreeFall) {
                    dir = HitTheDirt(dir);
                }
                return true;
            }
            else if(x>=D.insGreen.getX()-D.insGreen.getR() && x<=D.insGreen.getX()+D.insGreen.getR()
                    && y>=D.insGreen.getY()-D.insGreen.getR() && y<=D.insGreen.getY()+D.insGreen.getR()) {
                // straight jump
                if (dir != 0 && !FreeFall) {
                    jumpmovingflag = 0;
                    predir = dir;
                    dir = 0;
                }
                return true;
            }
            else if(x>=D.insYellow.getX()-D.insYellow.getR() && x<=D.insYellow.getX()+D.insYellow.getR()
                    && y>=D.insYellow.getY()-D.insYellow.getR() && y<=D.insYellow.getY()+D.insYellow.getR())
            {
                // Hold Posture Button
                HoldFlag = !HoldFlag;
                if (!HoldFlag){
                    if (dir > 0){
                        dir = 1;
                    }
                    else{
                        dir = -1;
                    }
                }
            }
            else if(x>=D.insBlue.getX()-D.insBlue.getR() && x<=D.insBlue.getX()+D.insBlue.getR()
                    && y>=D.insBlue.getY()-D.insBlue.getR() && y<=D.insBlue.getY()+D.insBlue.getR())
            {
                // adjust posture if running
                if (WaitCount == 0 && Ammo > 0) {
                    if (dir != 0 && !FreeFall) {
                        if (dir == 2) {
                            dir = 1;
                        } else if (dir == -2) {
                            dir = -1;
                        }
                    }
                    D.Fire(dir, predir);
                    WaitCount++;
                    Ammo--;
                }
            }
            /*
            else if(x>=D.insRed.getX()-D.insRed.getR() && x<=D.insRed.getX()+D.insRed.getR()
                    && y>=D.insRed.getY()-D.insRed.getR() && y<=D.insRed.getY()+D.insRed.getR())
            {
                running = !running;
            }*/
        }
        else if (e.getAction() == MotionEvent.ACTION_UP) {
            dir = release(dir, 1);
            return true;
        }
        else if (e.getAction() == MotionEvent.ACTION_MOVE) {
            x = e.getX();
            y = e.getY();

                if (x >= D.ins.leftbuttoncenterX() - touchrange && x <= D.ins.leftbuttoncenterX() + touchrange
                        && y >= D.ins.leftbuttoncenterY() - touchrange && y <= D.ins.leftbuttoncenterY() + touchrange) {
                    //move left
                    dir = MovingLeft(dir);
                    return true;
                } else if (x >= D.ins.rightbuttoncenterX() - touchrange && x <= D.ins.rightbuttoncenterX() + touchrange
                        && y >= D.ins.rightbuttoncenterY() - touchrange && y <= D.ins.rightbuttoncenterY() + touchrange) {
                    //move right
                    dir = MovingRight(dir);
                    return true;
                } else if (e.getX() >= D.ins.upbuttoncenterX() - touchrange && e.getX() <= D.ins.upbuttoncenterX() + touchrange
                        && e.getY() >= D.ins.upbuttoncenterY() - touchrange && e.getY() <= D.ins.upbuttoncenterY() + touchrange) {
                    //point up
                    if (!FreeFall) {
                        dir = pointup(dir);
                    }
                    return true;
                } else if (e.getX() >= D.ins.downbuttoncenterX() - touchrange && e.getX() <= D.ins.downbuttoncenterX() + touchrange
                        && e.getY() >= D.ins.downbuttoncenterY() - touchrange && e.getY() <= D.ins.downbuttoncenterY() + touchrange) {
                    // Hit the dirt
                    if (!FreeFall) {
                        dir = HitTheDirt(dir);
                    }
                    return true;
                } else {
                    // stand up
                    dir = release(dir, 2);
                    return true;
                }
        }
        return false;
    }

    public int pointup(int dir){
        if (dir != 0) {
            if (dir > 0) {
                return 3;
            } else {
                return -3;
            }
        }
        else{
            if (firsttimeflag) {
                prejumpmovingflag = jumpmovingflag;
                jumpmovingflag = 0;
            }
            firsttimeflag = false;
        }
        return 0;
    }

    public int HitTheDirt(int dir){
        if (dir != 0) {
            if (dir > 0) {
                return 4;
            } else {
                return -4;
            }
        }
        else{
            if (firsttimeflag) {
                prejumpmovingflag = jumpmovingflag;
                jumpmovingflag = 0;
            }
            firsttimeflag = false;
        }
        return 0;
    }

    public int MovingLeft(int dir){
        if (dir != 0) {
            return -2;
        }
        else {
            firsttimeflag = true;
            if (predir > 0) {
                predir = -predir;
            }
            jumpmovingflag = -2;
            return 0;
        }
    }

    public int MovingRight(int dir){
        if (dir != 0) {
            return 2;
        }
        else{
            firsttimeflag = true;
            if (predir < 0) {
                predir = -predir;
            }
            jumpmovingflag = 2;
            return 0;
        }
    }

    public int release(int dir, int flag){
        if (dir == 0){
            if (flag == 1) {
                if (firsttimeflag) {
                    prejumpmovingflag = jumpmovingflag;
                    jumpmovingflag = 0;
                }
                firsttimeflag = false;
            }
            else if (flag == 2){
                if (firsttimeflag) {
                    prejumpmovingflag = jumpmovingflag;
                    jumpmovingflag = 0;
                }
                firsttimeflag = false;
            }
        }
        else if (dir > 0)
        {
            if (!HoldFlag) {
                return 1;
            }
            else{
                if (dir == 2){
                    return 1;
                }
            }
        }
        else if (dir < 0)
        {
            if (!HoldFlag) {
                return -1;
            }
            else{
                if (dir == -2){
                    return -1;
                }
            }
        }
        return dir;
    }

    // Check if a bullet hit something
    public int CheckImpact(Player player, ArrayList<Player> PlayerBullet, ArrayList<Bullet> EnemyBullets, ArrayList<Enemy> Enemy1s,
                           ArrayList<Enemy> Enemy2s, ArrayList<Item> Items, int dir)
    {
        int i, j;
        int ScoreCounter = 0;
        int x, y, xx, yy;
        final int CheckRangeX = 70;
        final int CheckRangeY = 120;
        final int Modification = 90;
        final int CheckRangeY2 = 30;

        // check if player hit a enemy or an item
        for (i = 0; i < PlayerBullet.size(); i++){
            x = PlayerBullet.get(i).getX();
            y = PlayerBullet.get(i).getY();
            if (x != 10 && y != 1000) {
                for (j = 0; j < Enemy1s.size(); j++) {
                    xx = Enemy1s.get(j).getX();
                    yy = Enemy1s.get(j).getY();
                    if (x > xx && x < xx + CheckRangeX && y > yy && y < yy + CheckRangeY) {
                        Enemy1s.remove(j);
                        ScoreCounter+=2;
                        j--;
                        PlayerBullet.remove(i);
                        i--;
                    }
                }
            }
            else{
                PlayerBullet.remove(i);
                i--;
            }


            if (x != 10 && y != 1000) {
                for (j = 0; j < Items.size(); j++) {
                    int xxx, yyy;
                    int modi1 = 35;
                    int modi2 = 10;
                    xxx = x + modi1;
                    yyy = y + modi2;
                    xx = Items.get(j).getX();
                    yy = Items.get(j).getY();
                    if (Items.get(j).getStatus() == 1) {
                        if (x > xx && x < xx + CheckRangeX && y > yy && y < yy + CheckRangeY) {
                            if (Math.random() > 0.5) {
                                Items.get(j).setStatus(2);
                            } else {
                                Items.get(j).setStatus(3);
                            }
                            j--;
                            PlayerBullet.remove(i);
                            i--;
                        } else if (xxx > xx && xxx < xx + CheckRangeX && yyy > yy && yyy < yy + CheckRangeY) {
                            if (Math.random() > 0.5) {
                                Items.get(j).setStatus(2);
                            } else {
                                Items.get(j).setStatus(3);
                            }
                            j--;
                            PlayerBullet.remove(i);
                            i--;
                        }
                    }
                }
            }
            else{
                PlayerBullet.remove(i);
                i--;
            }
        }

        for (i = 0; i < PlayerBullet.size(); i++){
            x = PlayerBullet.get(i).getX();
            y = PlayerBullet.get(i).getY();
            if (x != 10 && y != 1000) {
                for (j = 0; j < Enemy2s.size(); j++) {
                    xx = Enemy2s.get(j).getX();
                    yy = Enemy2s.get(j).getY();
                    if (x > xx && x < xx + CheckRangeX && y > yy && y < yy + CheckRangeY) {
                        Enemy2s.remove(j);
                        ScoreCounter+=1;
                        j--;
                        PlayerBullet.remove(i);
                        i--;
                    }
                }
            }
            else{
                PlayerBullet.remove(i);
                i--;
            }
        }

        /*
        for (i = 0; i < Enemy1s.size(); i++){
            if (!Enemy1s.get(i).CheckAlive()){
                Enemy1s.remove(i);
                i--;
            }
        }

        for (i = 0; i < PlayerBullet.size(); i++){
            if (!PlayerBullet.get(i).CheckFlying()){
                PlayerBullet.remove(i);
                i--;
            }
        }*/

        // Check if the player get an item
        for (i = 0; i < Items.size(); i++){
            int xxx, yyy, s;
            final int modi1 = 30;
            final int modi2 = 60;
            x = Items.get(i).getX();
            y = Items.get(i).getY();
            xx = player.getX();
            yy = player.getY();
            xxx = x + modi1;
            yyy = y + modi2;
            s = Items.get(i).getStatus();
            if (s == 2 || s == 3) {
                if (x > xx && x < xx + CheckRangeX && y > yy && y < yy + CheckRangeY) {
                    if (s == 2){
                        Ammo+= 15;
                    }
                    else{
                        LifeCounter+= 1;
                    }
                    Items.remove(i);
                    i--;
                } else if (xxx > xx && xxx < xx + CheckRangeX && yyy > yy && yyy < yy + CheckRangeY) {
                    if (s == 2){
                        Ammo+= 15;
                    }
                    else{
                        LifeCounter+= 1;
                    }
                    Items.remove(i);
                    i--;
                }
            }
        }

        // check if enemy bullet hit the player
        for (i = 0; i < EnemyBullets.size(); i++) {
            x = EnemyBullets.get(i).getX();
            y = EnemyBullets.get(i).getY();
            xx = player.getX();
            yy = player.getY();
            if (dir != 4 && dir != -4) {
                if (x > xx && x < xx + CheckRangeX && y > yy && y < yy + CheckRangeY) {
                    EnemyBullets.remove(i);
                    i--;
                    player.Dead();
                }
            }
            else if (dir != 0) {
                if (x > xx && x < xx + CheckRangeX && y > yy + Modification && y < yy + Modification + CheckRangeY2) {
                    EnemyBullets.remove(i);
                    i--;
                    player.Dead();
                }
            }
            else if (dir == 0){
                if (x > xx && x < xx + CheckRangeX && y > yy && y < yy+ CheckRangeX) {
                    EnemyBullets.remove(i);
                    i--;
                    player.Dead();
                }
            }
        }

        // check if any enemy person hit the player
        for (i = 0; i < Enemy1s.size(); i++){
            int xxx, yyy;
            final int modi1 = 30;
            final int modi2 = 60;
            x = Enemy1s.get(i).getX();
            y = Enemy1s.get(i).getY();
            xx = player.getX();
            yy = player.getY();
            xxx = x + modi1;
            yyy = y + modi2;
            if (x > xx && x < xx + CheckRangeX && y > yy && y < yy + CheckRangeY){
                player.Dead();
            }
            else if(xxx > xx && xxx < xx + CheckRangeX && yyy > yy && yyy < yy + CheckRangeY){
                player.Dead();
            }
        }

        for (i = 0; i < Enemy2s.size(); i++){
            int xxx, yyy;
            final int modi1 = 30;
            final int modi2 = 60;
            x = Enemy2s.get(i).getX();
            y = Enemy2s.get(i).getY();
            xx = player.getX();
            yy = player.getY();
            xxx = x + modi1;
            yyy = y + modi2;
            if (x > xx && x < xx + CheckRangeX && y > yy && y < yy + CheckRangeY){
                player.Dead();
            }
            else if(xxx > xx && xxx < xx + CheckRangeX && yyy > yy && yyy < yy + CheckRangeY){
                player.Dead();
            }
        }
        return ScoreCounter*50;
    }
}
