package my.game.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Drawable {
	
	public float xCor;
	public float yCor;
	public String color;
	
	public abstract void Draw(Canvas canvas,Paint paint);

}
