/*package com.example.ilysh.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    private Sprite player;
    private Sprite roofs;

    private int viewWidth;
    private int viewHeight;

    private int points = 0;
    private int bestScore = 0;

    private final int timerInterval = 30;

    public GameView(Context context) {
        super(context);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        int w = b.getWidth()/5;
        int h = b.getHeight()/3;
        Rect firstFrame = new Rect(0, 0, w, h);
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

        b = BitmapFactory.decodeResource(getResources(), R.drawable.settings);
        w = b.getWidth()/5;
        h = b.getHeight()/3;
        firstFrame = new Rect(4*w, 0, 5*w, h);

        roofs = new Sprite(2000, 250, -300, 0, firstFrame, b);

        for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--) {

                if (i ==0 && j == 4) {
                    continue;
                }

                if (i ==2 && j == 0) {
                    continue;
                }

                roofs.addFrame(new Rect(j*w, i*h, j*w+w, i*w+w));
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
        canvas.drawARGB(250, 127, 199, 255);
        player.draw(canvas);
        roofs.draw(canvas);

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.WHITE);
        canvas.drawText(points + "", viewWidth - 200, 70, p);
    }

    protected void update () {
        player.update(timerInterval);
        roofs.update(timerInterval);

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

        if (roofs.getX() < - roofs.getFrameWidth()) {
            teleportEnemy();
            points +=10;
        }

        if (roofs.intersect(player)) {
            teleportEnemy ();
            points -= 40;
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


    private void teleportEnemy () {
        roofs.setX(viewWidth + Math.random() * 500);
        roofs.setY(Math.random() * (viewHeight - roofs.getFrameHeight()));
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
*/








//сверху если что












/*
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {

    //Обработка косания по экрану
    public boolean onTouchEvent(MotionEvent event) {
        for (int i = sprites.size()-1; i >= 0; i--)
        {
            Sprite sprite = sprites.get(i);
            if (sprite.isCollition(event.getX(),event.getY()))
            {
                sprites.remove(sprite);
            }
        }
        return super.onTouchEvent(event);
    }

    private List<Sprite> sprites = new ArrayList<Sprite>();

    sprites.add(createSprite(R.drawable.playerninja));
    sprites.add(createSprite(R.drawable.someone));

    private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this,bmp);
    }


    //Загружаем спрайт
    private Bitmap bmp;

    //Поле рисования
    private SurfaceHolder holder;

    //объект класса GameView
    private GameManager gameLoopThread;

    //Объект класса Sprite
    private Sprite sprite;

    //Конструктор
    public GameView(Context context)
    {
        super(context);
        gameLoopThread = new GameManager(this);
        holder = getHolder();

        //Рисуем все наши объекты и все все все
        holder.addCallback(new SurfaceHolder.Callback()
        {
            // Уничтожение области рисования
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry)
                {
                    try
                    {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e)
                    {
                    }
                }
            }

            // Создание области рисования
            public void surfaceCreated(SurfaceHolder holder) {
                createSprites();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            // Изменение области рисования /
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height)
            {
            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.playerninja);
        sprite = new Sprite(this,bmp);
    }

    //Функция рисующая все спрайты и фон
    protected void onDraw(Canvas canvas)
    {
        for(Sprite sprite : sprites) {
            sprite.onDraw(canvas);
        }
    }
}
*/