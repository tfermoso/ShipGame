package spinoffpyme.com.shipgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by tomas on 19/01/2018.
 */

public class GameView extends SurfaceView {
    private GameLoopThread gameLoop;
    private SurfaceHolder holder;
    private Ship ship;

    public GameView(Context context) {
        super(context);
        holder=getHolder();
        gameLoop=new GameLoopThread(this);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                ship=createShip();
                gameLoop.setRunning(true);
                gameLoop.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry=true;
                gameLoop.setRunning(false);
                while(retry){
                    try {
                        gameLoop.join();
                        retry=false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    public Ship createShip(){
        Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.drawable.ship);
        ship=new Ship(this,bmp);
        return ship;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        Bitmap left=BitmapFactory.decodeResource(getResources(),R.drawable.left);
        Bitmap right=BitmapFactory.decodeResource(getResources(),R.drawable.right);
        canvas.drawBitmap(left,0,getHeight()/2,null);
        canvas.drawBitmap(right,getWidth()-right.getWidth(),getHeight()/2,null);
        ship.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        if(x>0 && x<150 && y<(getHeight()/2 +150) && y >getHeight()/2){
            ship.movLeft();
        }else if(x>(getWidth()-150)&& x<getWidth()&& y<(getHeight()/2 +150) && y >getHeight()/2){
            ship.moveRight();
        }


        return true;
    }
}
