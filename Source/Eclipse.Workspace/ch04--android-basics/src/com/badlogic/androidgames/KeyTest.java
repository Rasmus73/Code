package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

public class KeyTest extends Activity implements OnKeyListener
{
    StringBuilder builder = new StringBuilder();
    TextView textView;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Press keys (if you have some)!");
        textView.setOnKeyListener(this);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
        setContentView(textView);
    }



    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event)
    {
    	builder.setLength(0);
        switch (event.getAction())
        {
	        case KeyEvent.ACTION_DOWN:
	            builder.append("down, ");
	            break;
	            
	        case KeyEvent.ACTION_UP:
	            builder.append("up, ");
	            break;
        }
        
        builder.append(event.getKeyCode());
        builder.append(", ");
        builder.append((char)event.getUnicodeChar());
        
        String text = builder.toString();
        Log.d("KeyTest", text);
        textView.setText(text);

        // This is weird: When running in debug the "return true" statement is called
        // when the event.getKeyCode() == KeyEvent.KEYCODE_BACK is true. Even after the
        // Log.d("KeyTest", "KEYCODE_BACK pressed."); line is run... ^^ ??!?
        // Maybe optimization? due to this code: 
        //if (event.dispatch(this, mAttachInfo != null ? mAttachInfo.mKeyDispatchState : null, this)) {
        //    return true;
        //} 
        // in function dispatchKeyEvent() in View class. Have not analyzed in depth though, so just a theory.
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        {
        	Log.d("KeyTest", "KEYCODE_BACK pressed.");
        	return false;
        }
        else
        {
        	Log.d("KeyTest", "other key pressed.");
        	return true;
        }
    }
}