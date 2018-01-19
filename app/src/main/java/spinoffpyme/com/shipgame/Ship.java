package spinoffpyme.com.shipgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by tomas on 19/01/2018.
 */

public class Ship {
    private int x,y=0;
    private GameView gameView;
    private Bitmap bmp;
    private int width,height=0;
    private int xSpeed;

    public Ship(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width=gameView.getWidth();
        this.height=gameView.getHeight();

        x=width/2;
        y=height-bmp.getHeight();
    }

    public void onDraw(Canvas canvas){
        canvas.drawBitmap(bmp,x,y,null);
    }

    public void movLeft() {
        x+=5;
    }

    public void moveRight() {
        x+=-5;
    }
}
