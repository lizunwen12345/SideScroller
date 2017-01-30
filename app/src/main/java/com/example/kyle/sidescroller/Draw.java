package com.example.kyle.sidescroller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zunwen on 2016/5/9.
 */
public class Draw {
    Player player;
    instrument ins;
    instrument insRed;
    instrument insBlue;
    instrument insGreen;
    instrument insYellow;
    private int screenheight;
    private int screenwidth;
    private Bitmap right1;
    private Bitmap right2;
    private Bitmap right3;
    private Bitmap right4;
    private Bitmap right5;
    private Bitmap left1;
    private Bitmap left2;
    private Bitmap left3;
    private Bitmap left4;
    private Bitmap left5;
    private Bitmap rightjump1;
    private Bitmap rightjump2;
    private Bitmap rightjump3;
    private Bitmap rightjump4;
    private Bitmap leftjump1;
    private Bitmap leftjump2;
    private Bitmap leftjump3;
    private Bitmap leftjump4;
    ArrayList<Player> PlayerBullets = new ArrayList<Player>();
    ArrayList<Bullet> EnemyBullets = new ArrayList<>();
    ArrayList<Terrain> Lands = new ArrayList<>();
    ArrayList<Enemy> Enemy1s = new ArrayList<>();
    ArrayList<Enemy> Enemy2s = new ArrayList<>();
    ArrayList<Item> Items = new ArrayList<>();
    Paint BlackBack = new Paint();

    public Draw(int width, int height, Bitmap right1, Bitmap right2, Bitmap right3, Bitmap right4,
    Bitmap right5, Bitmap left1, Bitmap left2, Bitmap left3, Bitmap left4, Bitmap left5,
    Bitmap lj1, Bitmap lj2, Bitmap lj3, Bitmap lj4, Bitmap rj1, Bitmap rj2, Bitmap rj3, Bitmap rj4)
    {
        final int ButtonRadious = 66;
        screenwidth = width;
        screenheight = height;
        ins = new instrument(width, height);
        insRed = new instrument(width, height, ButtonRadious, 1);
        insBlue = new instrument(width, height, ButtonRadious, 2);
        insGreen = new instrument(width, height, ButtonRadious, 3);
        insYellow = new instrument(width, height, ButtonRadious, 4);
        player = new Player(width, height);
        this.right1 = right1;
        this.right2 = right2;
        this.right3 = right3;
        this.right4 = right4;
        this.right5 = right5;
        this.left1 = left1;
        this.left2 = left2;
        this.left3 = left3;
        this.left4 = left4;
        this.left5 = left5;
        this.leftjump1 = lj1;
        this.leftjump2 = lj2;
        this.leftjump3 = lj3;
        this.leftjump4 = lj4;
        this.rightjump1 = rj1;
        this.rightjump2 = rj2;
        this.rightjump3 = rj3;
        this.rightjump4 = rj4;

        //test part
        //testenemy = new Enemy(width, height);
    }

    // draw instrument 1
    public void draw1(Canvas c, Bitmap inst){
        c.drawBitmap(inst, ins.getX(), ins.getY(), null);
    }

    // draw instrument 2
    public void draw2(Canvas c){
        //c.drawCircle(insRed.getX(), insRed.getY(), insRed.getR(), insRed.getColor());
        BlackBack.setColor(Color.BLACK);
        BlackBack.setTextSize(40);
        int mod1 = 30, mod2 = 40, mod3 = 45, mod4 = 10;
        c.drawCircle(insBlue.getX(), insBlue.getY(), insBlue.getR(), insBlue.getColor());
        c.drawText("Fire", insBlue.getX()-mod1, insBlue.getY()+mod4, BlackBack);
        c.drawCircle(insGreen.getX(), insGreen.getY(), insGreen.getR(), insGreen.getColor());
        c.drawText("Jump", insGreen.getX()-mod3, insGreen.getY()+mod4, BlackBack);
        c.drawCircle(insYellow.getX(), insYellow.getY(), insYellow.getR(), insYellow.getColor());
        c.drawText("Hold", insYellow.getX()-mod2, insYellow.getY()+mod4, BlackBack);
    }

    // draw player bullets
    public void DrawPlayerBullets(Canvas c, Bitmap Bullet){
        int i, dir;
        final int runningspeed = 22;
        for (i = 0; i < PlayerBullets.size(); i++){
            dir = PlayerBullets.get(i).getDirection();
            if (dir == 1) {
                PlayerBullets.get(i).setX(PlayerBullets.get(i).getX() + 2 * runningspeed);
                c.drawBitmap(Bullet, PlayerBullets.get(i).getX(), PlayerBullets.get(i).getY(), null);
            }
            else if (dir == -1){
                PlayerBullets.get(i).setX(PlayerBullets.get(i).getX() - 2 * runningspeed);
                c.drawBitmap(Bullet, PlayerBullets.get(i).getX(), PlayerBullets.get(i).getY(), null);
            }
            else if (dir == 0){
                PlayerBullets.get(i).setY(PlayerBullets.get(i).getY() - 2 * runningspeed);
                c.drawBitmap(Bullet, PlayerBullets.get(i).getX(), PlayerBullets.get(i).getY(), null);
            }
            else if (dir == -5){
                PlayerBullets.get(i).setX(PlayerBullets.get(i).getX() + 2 * runningspeed);
                c.drawBitmap(Bullet, PlayerBullets.get(i).getX(), PlayerBullets.get(i).getY(), null);
            }
            else if (dir == -6){
                PlayerBullets.get(i).setX(PlayerBullets.get(i).getX() - 2 * runningspeed);
                c.drawBitmap(Bullet, PlayerBullets.get(i).getX(), PlayerBullets.get(i).getY(), null);
            }
            else if (dir == -10){
                PlayerBullets.get(i).setY(PlayerBullets.get(i).getY() + 2 * runningspeed);
                c.drawBitmap(Bullet, PlayerBullets.get(i).getX(), PlayerBullets.get(i).getY(), null);
            }
        }
    }

    // Fire
    public void Fire(int dir, int predir){
        if (dir == 1 || dir == 2) {
            PlayerBullets.add(new Player(player.GetGunpointX(1), player.GetGunpointY(1), true, 1));
        }
        else if (dir == -1 || dir == -2){
            PlayerBullets.add(new Player(player.GetGunpointX(-1), player.GetGunpointY(-1), true, -1));
        }
        else if (dir == 3 || dir == -3){
            PlayerBullets.add(new Player(player.GetGunpointX(0), player.GetGunpointY(0), true, 0));
        }
        else if (dir == 4){
            PlayerBullets.add(new Player(player.GetGunpointX(-5), player.GetGunpointY(-5), true, -5));
        }
        else if (dir == -4){
            PlayerBullets.add(new Player(player.GetGunpointX(-6), player.GetGunpointY(-6), true, -6));
        }
        else if (dir == 0){
            if (predir == 1){
                PlayerBullets.add(new Player(player.GetGunpointX(1), player.GetGunpointY(1), true, 1));
            }
            else if (predir == -1)
            {
                PlayerBullets.add(new Player(player.GetGunpointX(-1), player.GetGunpointY(-1), true, -1));
            }
            else if (predir == 3 || predir == -3){
                PlayerBullets.add(new Player(player.GetGunpointX(0), player.GetGunpointY(0), true, 0));
            }
            else if (predir == 4 || predir == -4){
                PlayerBullets.add(new Player(player.GetGunpointX(-10), player.GetGunpointY(-10), true, -10));
            }
        }
    }

    int n = 1;
    int jumpcount = 0;

    // draw contra character
    public int drawcharacter(Canvas c, Bitmap character, Bitmap character2, Bitmap upright, Bitmap upleft,
                              Bitmap rightdown, Bitmap leftdown, int direction, int predirection, int jumpmovingflag,
                             int prejumpmovingflag, boolean HoldFlag)
    {
        // 0 is straight jump
        if (direction == 0){
            boolean GravityCheck;
            if (jumpcount < player.jumptimes) {
                player.setY(player.getY() - player.jumpheight);
            }
            else if (jumpcount >= player.jumptimes){
                GravityCheck = Gravity(direction);
                if (GravityCheck)
                // still in air
                {
                    player.setY(player.getY() + player.jumpheight);
                }
                // jump end
                else{
                    if (jumpmovingflag < 0) {
                        return -2;
                    }
                    else if(jumpmovingflag > 0){
                        return 2;
                    }
                    else{
                        if (!HoldFlag) {
                            if (predirection > 0) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                        else
                        {
                            return  predirection;
                        }
                    }
                }
            }

            if (jumpmovingflag < 0) {
                player.setX(player.getX() - player.jumpmovingdistance);
                leftjump(c, n);
            }
            else if (jumpmovingflag > 0)
            {
                player.setX(player.getX() + player.jumpmovingdistance);
                straightjump(c, n);
            }
            else
            {
                if (prejumpmovingflag < 0){
                    player.setX(player.getX() - (int)(player.jumpmovingdistance*0.7));
                    leftjump(c, n);
                }
                else if (prejumpmovingflag > 0){
                    player.setX(player.getX() + (int)(player.jumpmovingdistance*0.7));
                    straightjump(c, n);
                }
                else{
                    if (predirection > 0) {
                        straightjump(c, n);
                    }
                    else if (predirection < 0){
                        leftjump(c, n);
                    }
                }
            }
            jumpcount++;
            if (n != 4){
                n++;
            }
            else{
                n = 1;
            }
        }
        // 1 is standupright
        else if (direction == 1) {
            JumpReset();
            c.drawBitmap(character, player.getX(), player.getY(), null);
        }
        // -1 is standupleft
        else if (direction == -1){
            JumpReset();
            c.drawBitmap(character2, player.getX(), player.getY(), null);
        }
        // 3 is upright
        if (direction == 3){
            JumpReset();
            c.drawBitmap(upright, player.getX() - 10, player.getY() - 30, null);
        }
        // -3 is upleft
        if (direction == -3){
            JumpReset();
            c.drawBitmap(upleft, player.getX() + 10, player.getY() - 30, null);
        }
        // 4 is rightdown
        if (direction == 4){
            JumpReset();
            c.drawBitmap(rightdown, player.getX(), player.getY(), null);
        }
        // -4 is leftdown
        if (direction == -4){
            JumpReset();
            c.drawBitmap(leftdown, player.getX(), player.getY(), null);
        }
        // -2 is moving left
        else if (direction == -2){
            player.setX(player.getX() - player.runningspeed);
            leftrun(c, n);
            if (n != 6){
                n++;
            }
            else{
                n = 1;
            }
        }
        // 2 is moving right
        else if (direction == 2){
            player.setX(player.getX() + player.runningspeed);
            rightrun(c, n);
            if (n != 6){
                n++;
            }
            else{
                n = 1;
            }
        }
        return direction;
    }

    // right run animation
    public void rightrun(Canvas c, int n){
        if (n == 1){
            c.drawBitmap(right1, player.getX(), player.getY(), null);
        }
        else if (n == 2){
            c.drawBitmap(right2, player.getX(), player.getY(), null);
        }
        else if (n == 3){
            c.drawBitmap(right3, player.getX(), player.getY(), null);
        }
        else if (n == 4){
            c.drawBitmap(right4, player.getX(), player.getY(), null);
        }
        else if (n == 5){
            c.drawBitmap(right5, player.getX(), player.getY(), null);
        }
        else if (n == 6){
            c.drawBitmap(right3, player.getX(), player.getY(), null);
        }
    }

    // left run animation
    public void leftrun(Canvas c, int n){
        if (n == 1){
            c.drawBitmap(left1, player.getX(), player.getY(), null);
        }
        else if (n == 2){
            c.drawBitmap(left2, player.getX(), player.getY(), null);
        }
        else if (n == 3){
            c.drawBitmap(left3, player.getX(), player.getY(), null);
        }
        else if (n == 4){
            c.drawBitmap(left4, player.getX(), player.getY(), null);
        }
        else if (n == 5){
            c.drawBitmap(left5, player.getX(), player.getY(), null);
        }
        else if (n == 6){
            c.drawBitmap(left3, player.getX(), player.getY(), null);
        }
    }

    // straight jump animation
    public void straightjump(Canvas c, int n) {
        if (n == 1) {
            c.drawBitmap(rightjump1, player.getX(), player.getY()+player.modification, null);
        } else if (n == 2) {
            c.drawBitmap(rightjump2, player.getX(), player.getY()+player.modification, null);
        } else if (n == 3) {
            c.drawBitmap(rightjump3, player.getX(), player.getY()+player.modification, null);
        } else if (n == 4) {
            c.drawBitmap(rightjump4, player.getX(), player.getY()+player.modification, null);
        }
    }

    // left jump animation
    public void leftjump(Canvas c, int n){
        if (n == 1) {
            c.drawBitmap(leftjump1, player.getX(), player.getY()+player.modification, null);
        } else if (n == 2) {
            c.drawBitmap(leftjump2, player.getX(), player.getY()+player.modification, null);
        } else if (n == 3) {
            c.drawBitmap(leftjump3, player.getX(), player.getY()+player.modification, null);
        } else if (n == 4) {
            c.drawBitmap(leftjump4, player.getX(), player.getY()+player.modification, null);
        }
    }

    // jump reset
    public void JumpReset(){
        n = 1;
        jumpcount = 0;
    }

    // Gravity true means on air, false means on land
    public boolean Gravity(int dir){
        int i, j;
        int Lx, Ly;
        int ILyy[] = new int [15];
        int ELyy[] = new int[15];
        int Lyy[] = new int[15];
        int Px, Py;
        final int CheckRange = 35;
        final int DropSpeeding = 30;
        Px = player.getX();
        Py = player.getBottomY();
        final int modification = 60;

        // Check for Items
        int Ix, Iy, k, s;
        boolean OnLand;
        for (k = 0; k < Items.size(); k++) {
            OnLand = false;
            j = 0;
            Ix = Items.get(k).getX();
            Iy = Items.get(k).getBottomY();
            s = Items.get(k).getStatus();
            if (s != 1) {
                for (i = 0; i < Lands.size(); i++) {
                    Lx = Lands.get(i).getX();
                    Ly = Lands.get(i).getY();
                    if (Ix > Lx - modification && Ix < Lx + Lands.get(i).getLandWidth()) {
                        ILyy[j] = Ly;
                        j++;
                    }
                }

                for (i = 0; i < j; i++) {
                    if (Iy > ILyy[i] && Iy < ILyy[i] + CheckRange) {
                        OnLand = true;
                        break;
                    }
                }
                if (!OnLand) {
                    Items.get(k).setY(Items.get(k).getY() + DropSpeeding);
                }
            }
        }

        // Check for enemy2s
        int Ex, Ey;
        for (k = 0; k < Enemy2s.size(); k++) {
            OnLand = false;
            j = 0;
            Ex = Enemy2s.get(k).getX();
            Ey = Enemy2s.get(k).getBottomY();
            for (i = 0; i < Lands.size(); i++) {
                Lx = Lands.get(i).getX();
                Ly = Lands.get(i).getY();
                if (Ex > Lx - modification && Ex < Lx + Lands.get(i).getLandWidth()) {
                    ELyy[j] = Ly;
                    j++;
                }
            }

            for (i = 0; i < j; i++) {
                if (Ey > ELyy[i] && Ey < ELyy[i] + CheckRange) {
                    OnLand = true;
                    break;
                }
            }
            if (!OnLand) {
                Enemy2s.get(k).setY(Enemy2s.get(k).getY() + DropSpeeding);
            }
        }

        // Check for player
        if (dir != 0) {
            j = 0;
            for (i = 0; i < Lands.size(); i++) {
                if (!Lands.get(i).getVirtual()) {
                    Lx = Lands.get(i).getX();
                    Ly = Lands.get(i).getY();
                    if (Px > Lx - modification && Px < Lx + Lands.get(i).getLandWidth()) {
                        Lyy[j] = Ly;
                        j++;
                    }
                }
            }

            for (i = 0; i < j; i++) {
                if (Py > Lyy[i] && Py < Lyy[i] + CheckRange) {
                    player.setY(Lyy[i] - player.getPlayerHeight());
                    return false;
                }
            }
            player.setY(player.getY() + DropSpeeding);
            return true;
        }
        else{
            if (jumpcount < player.jumptimes) {
                return true;
            }
            else if (jumpcount >= player.jumptimes){
                j = 0;
                for (i = 0; i < Lands.size(); i++) {
                    if (!Lands.get(i).getVirtual()) {
                        Lx = Lands.get(i).getX();
                        Ly = Lands.get(i).getY();
                        if (Px > Lx - modification && Px < Lx + Lands.get(i).getLandWidth() + modification) {
                            Lyy[j] = Ly;
                            j++;
                        }
                    }
                }

                for (i = 0; i < j; i++) {
                    if (Py > Lyy[i] && Py < Lyy[i] + CheckRange) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    // Check if a bullet hit something
    /*
    public int CheckImpact(ArrayList<Player> PlayerBullet, ArrayList<Bullet> EnemyBullets, ArrayList<Enemy> Enemy1s,
                            ArrayList<Enemy> Enemy2s, ArrayList<Item> Items, int dir)
    {
        int i, j;
        int ScoreCounter = 0;
        int x, y, xx, yy, Ix, Iy;
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
                    xx = Items.get(j).getX();
                    yy = Items.get(j).getY();
                    if (x > xx && x < xx + CheckRangeX && y > yy && y < yy + CheckRangeY) {
                        if (Math.random() > 0.5) {
                            Items.get(j).setStatus(2);
                        }
                        else{
                            Items.get(j).setStatus(3);
                        }
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
        /*
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
    }*/

    // Make a new life if dead;
    public boolean NewLife(boolean alive){
        if (!alive){
            player = null;
            player = new Player(screenwidth, screenheight);
            return true;
        }
        return false;
    }

    // Clear out of screen bullets
    public void Clear(ArrayList<Player> PlayerBullet, ArrayList<Bullet> EnemyBullets,
                      ArrayList<Enemy> Enemy1s, ArrayList<Enemy> Enemy2s,
                      ArrayList<Item> Items){
        int i, x, y;
        boolean OutOfScreen;
        for (i = 0; i < PlayerBullet.size(); i++){
            x = PlayerBullet.get(i).getX();
            y = PlayerBullet.get(i).getY();
            OutOfScreen = CheckOutOfScreen(x, y);
            if (OutOfScreen){
                PlayerBullet.remove(i);
                i--;
            }
        }

        for (i = 0; i < EnemyBullets.size(); i++){
            x = EnemyBullets.get(i).getX();
            y = EnemyBullets.get(i).getY();
            OutOfScreen = CheckOutOfScreen(x, y);
            if (OutOfScreen){
                EnemyBullets.remove(i);
                i--;
            }
        }

        /*
        for (i = 0; i < Enemy1s.size(); i++){
            x = Enemy1s.get(i).getX();
            y = Enemy1s.get(i).getY();
            OutOfScreen = CheckOutOfScreen(x, y);
            if (OutOfScreen){
                Enemy1s.remove(i);
                i--;
            }
        }
        */

        for (i = 0; i < Enemy2s.size(); i++){
            x = Enemy2s.get(i).getX();
            y = Enemy2s.get(i).getY();
            OutOfScreen = CheckOutOfScreen(x, y);
            if (OutOfScreen){
                Enemy2s.remove(i);
                i--;
            }
        }

        for (i = 0; i < Items.size(); i++){
            x = Items.get(i).getX();
            y = Items.get(i).getY();
            if ( x > screenwidth || y < 0 || y > screenheight) {
                OutOfScreen = true;
            }
            else{
                OutOfScreen = false;
            }
            if (OutOfScreen){
                Items.remove(i);
                i--;
            }
        }
    }

    // Check if a item is still in screen
    public boolean CheckOutOfScreen(int x, int y){
        int modi = 50;
        if (x < 0 || x > screenwidth - modi|| y < 0 || y > screenheight) {
            return true;
        }
        else{
            return false;
        }
    }
}
