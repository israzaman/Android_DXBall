package my.game.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;


public class Bar extends Drawable{
	
	public static float length;
	public static float width;
	public static float left;
	public static float top;
	public static float right;
	public static float bottom;
	public static boolean firstTime=true;
	public static boolean changePosition=false;
	public float position=0;
	public float middlePos;
	float leftPos=2;
	float rightPos=2;
	
	public Bar(float w,float l,String color)
	{
       Bar.length = l;
       Bar.width=w;
       this.color=color;
	}

	@Override
	public void Draw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		if(firstTime)
		{
			left=canvas.getWidth()/2-(length/2);
			top=canvas.getHeight()-width;
			right=canvas.getWidth()/2+(length/2);
			bottom=canvas.getHeight()-10;
			firstTime=false;
		}
		middlePos=canvas.getWidth()/2;
		paint.setColor(Color.parseColor(color));
		paint.setStyle(Style.FILL);
		canvas.drawRect(left,top,right,bottom ,paint);
		if(changePosition)
		{
			if(right> position && left< position)
			{
				//canvas.drawRect(left,top,right,bottom ,paint);					//draw the rec again in the position touched
			}
			else if(position>=right && position > left)
			{
				//left=position-length;
				//right=position;
				//canvas.drawRect(left,top,right,bottom ,paint);
				right+=rightPos;
				left+=rightPos;
				//rightPos++;
			}
			else if(position<=left && position< right)
			{
				//left=position;
				//right=position+length;
				//canvas.drawRect(left,top,right,bottom ,paint);
				right-=leftPos;
				left-=leftPos;
				//leftPos--;
			}

		}

		
	}
	
	public void changeBarPos(float x)
	{
		changePosition=true;
		position=x;
	}
	
	public static float getLeft() 
	{
        return left;
    }

    public static float getRight() 
    {
        return right;
    }

    public static float getBottom() 
    {
        return bottom;
    }

    public static float getTop() 
    {
        return top;
    }


}
