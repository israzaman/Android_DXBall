package my.game.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Brick extends Drawable{
	
	float right;
	float top;
	float left;
	float bottom;
	
	public Brick(float left,float top,float right,float bottom,String color)
	{
		this.left=left;
		this.right=right;
		this.top=top;
		this.bottom=bottom;
		this.color=color;
	}

	@Override
	public void Draw(Canvas canvas, Paint paint){
		// TODO Auto-generated method stub
		paint.setColor(Color.parseColor(color));
		paint.setStyle(Style.FILL);
		canvas.drawRect(left,top,right,bottom, paint);
		
		paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(left,top,right,bottom, paint);
		
	}
	
	public float getLeft() 
	{
        return left;
    }

    public float getRight() 
    {
        return right;
    }

    public float getBottom() 
    {
        return bottom;
    }

    public float getTop() 
    {
        return top;
    }

}
