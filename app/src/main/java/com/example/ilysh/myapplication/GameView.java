package com.example.ilysh.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;


public class GameView extends View{

    private Sprite player;
    private Sprite roof;


    private int viewWidth;
    private int viewHeight;

    private int points = 0;


    private final int timerInterval = 30;

    public GameView(Context context) {
        super(context);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.playerninja);

        // TODO поменяй спрайт

        int w = b.getWidth()/5;
        int h = b.getHeight()/1;

        Rect firstFrame = new Rect(0, 0, w, h);
        /*
        player = new Sprite(10, 0, 0, 100, firstFrame, b);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i ==0 && j == 0) {
                    continue;
                }
                if (i ==2 && j == 3) {
                    continue;
                }
                player.addFrame(new Rect(j*w, i*h, j*w+w, i*w+w));
            }
        }

        */

        //если что вверху оригинал
        player = new Sprite(10, 100, 0, 100, firstFrame, b);

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                if (i ==0 && j == 0) {
                    continue;
                }
                if (i ==0 && j == 4) {
                    continue;
                }
                player.addFrame(new Rect(j*w, i*h, j*w+w, i*w+w));
            }
        }


        b = BitmapFactory.decodeResource(getResources(), R.drawable.buttontohome);
        w = b.getWidth()/5;
        h = b.getHeight()/3;
        firstFrame = new Rect(4*w, 0, 5*w, h);

        roof = new Sprite(2000, 250, -300, 0, firstFrame, b);

        for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--) {

                if (i ==0 && j == 4) {
                    continue;
                }

                if (i ==2 && j == 0) {
                    continue;
                }

                roof.addFrame(new Rect(j*w, i*h, j*w+w, i*w+w));
            }
        }


        Timer t = new Timer();
        t.start();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawARGB(250, 127, 199, 255);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.playerninja);
        canvas.drawBitmap(b, 0, 0, null);
        player.draw(canvas);
        roof.draw(canvas);

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.WHITE);
        canvas.drawText(points + "", viewWidth - 200, 70, p);
    }

    /*
    protected void update () {
        player.update(timerInterval);
        roof.update(timerInterval);

        if (player.getY() + player.getFrameHeight() > viewHeight) {
            player.setY(viewHeight - player.getFrameHeight());
            player.setVy(-player.getVy());
            points--;
        }
        else if (player.getY() < 0) {
            player.setY(0);
            player.setVy(-player.getVy());
            points--;
        }

        if (roof.getX() < - roof.getFrameWidth()) {
            teleportEnemy();
            points +=10;
        }

        if (roof.intersect(player)) {
            teleportEnemy ();
            points -= 40;
        }


        invalidate();
    }
    */

    //если что оригинал сверху

    protected void update () {
        player.update(timerInterval);
        roof.update(timerInterval);

        if (roof.intersect(player)) {
            teleportRoof ();
            points += 1;
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN)  {

            if (event.getY() < player.getBoundingBoxRect().top) {
                player.setVy(-100);
                points--;
            }
            else if (event.getY() > (player.getBoundingBoxRect().bottom)) {
                player.setVy(100);
                points--;
            }
        }

        return true;
    }


    private void teleportRoof () {
        roof.setX(viewWidth + Math.random() * 500);
        roof.setY(Math.random() * (viewHeight - roof.getFrameHeight()));
    }

    class Timer extends CountDownTimer {

        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            update ();
        }

        @Override
        public void onFinish() {

        }
    }
}