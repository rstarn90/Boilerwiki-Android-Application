package cs307.boilerwiki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class Entertainment extends Activity {
	
	Button[] dropButtonList = new Button[11];
	ImageButton[] button=new ImageButton[9];
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	Button[] buttonList = new Button[1];
    	   	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment);
        
        dropButtonList[0] = ((Button) findViewById(R.id.button9));
        dropButtonList[1] = ((Button) findViewById(R.id.button10));
        dropButtonList[2] = ((Button) findViewById(R.id.button11));
        dropButtonList[3] = ((Button) findViewById(R.id.button12));
        dropButtonList[4] = ((Button) findViewById(R.id.button13));
        dropButtonList[5] = ((Button) findViewById(R.id.button14));
        dropButtonList[6] = ((Button) findViewById(R.id.button15));
        dropButtonList[7] = ((Button) findViewById(R.id.button16));
        dropButtonList[8] = ((Button) findViewById(R.id.button17));
        dropButtonList[9] = ((Button) findViewById(R.id.button18));
        dropButtonList[10] = ((Button) findViewById(R.id.button19));
        
        for(int i = 0; i < 11; i++)
        {
        	dropButtonList[i].bringToFront();
        }
        
        String[] mStrings = new String[] {"t1","t2","t3","t4","t5","t6","t7","t8","t9","t10"};
        
        ListView listView = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mStrings);
        listView.setAdapter(adapter);
        
        button[6] = (ImageButton)findViewById(R.id.button7);
        button[6].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			    startActivity(new Intent("android.intent.action.HOME"));
			}
		});
        button[5] = (ImageButton)findViewById(R.id.button6);
        button[5].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.ACADEMIC"));
			}
		});
        button[4] = (ImageButton)findViewById(R.id.button5);
        button[4].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.ENTERTAINMENT"));
			}
		});
        button[3] = (ImageButton)findViewById(R.id.button4);
        button[3].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.FOOD"));
			}
		});
        button[2] = (ImageButton)findViewById(R.id.button3);
        button[2].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.HOUSING"));
			}
		});
        button[1] = (ImageButton)findViewById(R.id.button2);
        button[1].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.MYPROFILE"));
			}
		});
        button[0] = (ImageButton)findViewById(R.id.button1);
        button[0].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.NEWS"));
			}
		});
        dropButtonHandler(dropButtonList[0],"android.intent.action.ACADEMIC");
        dropButtonHandler(dropButtonList[1],"android.intent.action.ACADEMIC");
        dropButtonHandler(dropButtonList[2],"android.intent.action.BAR");
        dropButtonHandler(dropButtonList[3],"android.intent.action.HOOKAH");
        dropButtonHandler(dropButtonList[4],"android.intent.action.STUDENT_ORGANIZATIONS");
        dropButtonHandler(dropButtonList[5],"android.intent.action.ACTIVITIES");
        dropButtonHandler(dropButtonList[6],"android.intent.action.FOOD");
        dropButtonHandler(dropButtonList[7],"android.intent.action.DINING_COURT");
        dropButtonHandler(dropButtonList[9],"android.intent.action.ON_CAMPUS");
        dropButtonHandler(dropButtonList[10],"android.intent.action.OFF_CAMPUS");
    }
    
    public void dropButtonHandler(final Button theButton, final String theIntent)
    {
    	theButton.setOnClickListener(new View.OnClickListener(){
    		public void onClick(View v){
    			clearButtons();
    			startActivity(new Intent(theIntent));
    		}
    	});
    }
    
    public void clearButtons()
    {
    	button[0].setImageResource(R.drawable.bw_news);
    	button[1].setImageResource(R.drawable.bw_myprofile);
    	button[2].setImageResource(R.drawable.bw_housing);
    	button[3].setImageResource(R.drawable.bw_food);
    	button[4].setImageResource(R.drawable.bw_entertainment);
    	button[5].setImageResource(R.drawable.bw_academic);
    	button[6].setImageResource(R.drawable.bw_home);
    	    	
    	for(int i = 0; i < 11; i++)
    	{
    		dropButtonList[i].setAlpha(0);
    		dropButtonList[i].setEnabled(false);
    	}
    }
}