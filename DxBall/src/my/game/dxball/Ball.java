package my.game.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.util.Log;


public class Ball extends Drawable{
	
	float x;
	float y;
	float dx=0;
	float dy=0;
	float speedX=0,speedY=0;
	public float radius;
	public static boolean firstTime=true;
	public static boolean onTouch=false;
	float ballMaxY,ballMinY,ballMaxX,ballMinX;
	Canvas canvas;
	Paint paint;
	GameCanvas g;
	float angle=60;
	float inc=0;
	
	public Ball(GameCanvas g,float x,float y, float r,String color) 
	{
		this.xCor=x;
        this.yCor = y;
        this.radius = r;
        this.color=color; 
        this.g=g;
	}

	@Override
	public void Draw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		if(firstTime)
		{
			x=canvas.getWidth()/2;
			y=canvas.getHeight()-Bar.width-radius;	
			ballMaxX=canvas.getWidth()-radius;
			ballMinX=ballMinY=radius;
			ballMaxY=canvas.getHeight()-radius;
			this.speedX = (float)(5 * Math.cos(Math.toRadians(angle)));
	        this.speedY = (float)(-5 * (float)Math.sin(Math.toRadians(angle)));
			firstTime=false;
			this.canvas=canvas;
			this.paint=paint;
		}
		paint.setColor(Color.parseColor(color));
		paint.setStyle(Style.FILL);
		canvas.drawCircle(x, y, radius, paint);
		if(onTouch)
		{
			x += speedX;
		    y += speedY;
			detectCollisionWithBrick();
			detectCollisionWithBar();
			detectCollisionWithWall();

		}
		
	}
	
	public void getMoveAngle() 
	{
		Log.d("angle", String.valueOf((float)Math.toDegrees(Math.atan2(-speedY, speedX))));
		//float angle= (float)Math.toDegrees(Math.atan2(-speedY, speedX));
		inc+=15;
		this.speedX = (float)(5 * Math.cos(Math.toRadians(angle+inc)));
        this.speedY = (float)(-5 * (float)Math.sin(Math.toRadians(angle+inc)));
	}
	
	public void detectCollisionWithWall()
	{
	    
		 if((x+radius)>=canvas.getWidth() || (x-radius)<=0)
		 {
			 speedX = -speedX;
			           
         }
         if( (y-radius)<=0)
         {
        	 speedY = -speedY;      
		 }
         
         if(y>=ballMaxY)
 		 {
 			g.Life();
 		 }
	    
	}
	
	public void detectCollisionWithBrick()
	{
	    for(int i=0;i<GameCanvas.brickList.size();i++) 
	    {
            if ((y-radius+speedY <= GameCanvas.brickList.get(i).getBottom()) && (y+radius+speedY >= GameCanvas.brickList.get(i).getTop()) && (x+radius-speedX >= GameCanvas.brickList.get(i).getLeft()) && (x-radius+speedX <= GameCanvas.brickList.get(i).getRight()) ) 
            {
            	g.dxballactivity.mpBrick=MediaPlayer.create(g.dxballactivity, R.raw.beep);
            	g.dxballactivity.mpBrick.start();
            	GameCanvas.score+=30;
            	GameCanvas.brickList.remove(i);
            	speedY = -speedY;
            }
        }
	    for(int i=0;i<GameCanvas.brick2List.size();i++) 
	    {
            if ((y-radius+speedY <= GameCanvas.brick2List.get(i).getBottom()) && (y+radius+speedY >= GameCanvas.brick2List.get(i).getTop()) && (x+radius-speedX >= GameCanvas.brick2List.get(i).getLeft()) && (x-radius-speedX<= GameCanvas.brick2List.get(i).getRight())) 
            {
            	if(GameCanvas.brick2List.get(i).color.equals("BLUE"))
            	{
            		g.dxballactivity.mpBrick.start();
            		GameCanvas.score+=20;
            		GameCanvas.brick2List.get(i).color="CYAN";
            	}
            	else
            	{
            		g.dxballactivity.mpBrick.start();
            		GameCanvas.score+=30;
            		GameCanvas.brick2List.remove(i);
            	}

            	speedY=-speedY;            	
            }
        }
	}
	
	public void detectCollisionWithBar()
	{
		if(((y+radius)==Bar.getTop()) && ((x+radius>=Bar.getLeft()) && (x-radius<=Bar.getRight()))) 
		{
			speedY = -speedY;
			//getMoveAngle();

		}

	}
}
