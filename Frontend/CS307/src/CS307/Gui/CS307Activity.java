package CS307.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CS307Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Button button;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui);
        button = (Button)findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				setContentView(R.layout.academic);
				
			}
		});
        
    }
    /*
    public void onClick(View v)
    {
    	startActivity(new Intent(CS307Activity.this,ActivityToBeCalled.java));
    }
    */
}