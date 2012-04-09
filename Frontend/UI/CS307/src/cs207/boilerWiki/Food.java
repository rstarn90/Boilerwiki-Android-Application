package cs207.boilerWiki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Food extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Button[] button=new Button[7];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        button[6] = (Button)findViewById(R.id.button7);
        button[6].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			    startActivity(new Intent("android.intent.action.HOME"));
			}
		});
        button[5] = (Button)findViewById(R.id.button6);
        button[5].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.ACADEMIC"));
			}
		});
        button[4] = (Button)findViewById(R.id.button5);
        button[4].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.ENTERTAINMENT"));
			}
		});
        button[3] = (Button)findViewById(R.id.button4);
        button[3].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.FOOD"));
			}
		});
        button[2] = (Button)findViewById(R.id.button3);
        button[2].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.HOUSING"));
			}
		});
        button[1] = (Button)findViewById(R.id.button2);
        button[1].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.MYPROFILE"));
			}
		});
        button[0] = (Button)findViewById(R.id.button1);
        button[0].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.NEWS"));
			}
		});
    }
}