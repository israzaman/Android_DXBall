package my.game.dxball;

import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class DxBallActivity extends Activity implements OnTouchListener{
	GameCanvas g;
	MediaPlayer mpBrick,mpGameOver,mpLife,mpLevelUp,mpComplete;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        g= new GameCanvas(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        g.setFocusableInTouchMode(true);
        g.setOnTouchListener(this);
        g.requestFocus();
        setContentView(g);

    }
    
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
    
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
    
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	protected InputStream getStage(String name)              //get the level file from assets folder
	{
		try 
		{
			return getAssets().open(name);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(g.GameOver)
		{
			g.background.clear();
			g.ballList.clear();
			g.barList.clear();
			GameCanvas.brickList.clear();
			GameCanvas.brick2List.clear();
			g.GameOver=false;
			GameCanvas.score=0;
			GameCanvas.life=3;
			g.LoadStage("level1.txt");
		}
		else if(g.winner)
		{
			g.background.clear();
			g.ballList.clear();
			g.barList.clear();
			GameCanvas.brickList.clear();
			GameCanvas.brick2List.clear();
			GameCanvas.score=0;
			GameCanvas.life=3;
			g.winner=false;
			g.LoadStage("level1.txt");
		}
		else
		{
			g.gameStart(event.getX());
		}
		return true;
	}
   
}