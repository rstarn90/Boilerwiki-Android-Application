package cs307.boilerwiki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Academic extends Activity {
	
	Button[] dropButtonList = new Button[11];
	ImageButton[] button=new ImageButton[9];
	Button[] buttonList = new Button[1];
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//ImageButton[] button=new ImageButton[12];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic);

/*        
        Spinner list = (Spinner) findViewById(R.id.button22);
        ArrayAdapter classAdapter = ArrayAdapter.createFromResource(
        		this, R.array.Class, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        		list.setAdapter(classAdapter);
        
        			
        		
       Spinner list2 = (Spinner) findViewById(R.id.button21);
       ArrayAdapter ratingAdapter = ArrayAdapter.createFromResource(
          		this, R.array.Rating, android.R.layout.simple_spinner_item);
       			ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           		list2.setAdapter(ratingAdapter);     

 */
        
        
        
        String[] mStrings = new String[] {"t1","t2","t3","t4","t5","t6","t7","t8","t9","t10"};
        
        ListView listView = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mStrings);
        listView.setAdapter(adapter);
        
        for(int i = 0; i < listView.getChildCount();i++)
        {
        	listView.getChildAt(i).setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View v) {
    			    startActivity(new Intent("android.intent.action.DESCRIPTION"));
    			}
    		});
        }
        
        
        dropButtonList[0] = ((Button) findViewById(R.id.button9)); // COURSES
        dropButtonList[1] = ((Button) findViewById(R.id.button10)); // PROFESSORS
        dropButtonList[2] = ((Button) findViewById(R.id.button11)); // BAR
        dropButtonList[3] = ((Button) findViewById(R.id.button12)); // HOOKAH
        dropButtonList[4] = ((Button) findViewById(R.id.button13)); // STUDENT_ORGANIZATIONS
        dropButtonList[5] = ((Button) findViewById(R.id.button14)); // ACTIVITIES
        dropButtonList[6] = ((Button) findViewById(R.id.button15)); // RESTAURANTS
        dropButtonList[7] = ((Button) findViewById(R.id.button16)); // DINING_COURT
        dropButtonList[8] = ((Button) findViewById(R.id.button17)); // Bar
        dropButtonList[9] = ((Button) findViewById(R.id.button18)); // ON campus
        dropButtonList[10] = ((Button) findViewById(R.id.button19)); // Off_Campus
        
       /* for(int i = 0; i<11; i++)
        {
        	dropButtonList[i].bringToFront();
        }*/
        
        buttonList[0] = (Button)findViewById(R.id.button8);
        buttonList[0].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				AutoCompleteTextView tempField = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
				Toast toast = Toast.makeText(getApplicationContext(), "Send query to Search for:"+tempField.getText(), 1000);
				toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 50);
				toast.show();
			    //startActivity(new Intent("android.intent.action.HOME"));
			}
		});

        button[6] = (ImageButton)findViewById(R.id.button7);
        button[6].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				clearButtons();
				
				button[6].setImageResource(R.drawable.bw_home_h);
				
			    startActivity(new Intent("android.intent.action.HOME"));
			}
		});
        button[5] = (ImageButton)findViewById(R.id.button6);
        button[5].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//startActivity(new Intent("android.intent.action.ACADEMIC"));
				
				clearButtons();
				
				button[5].setImageResource(R.drawable.bw_academic_h);
				
				Button b1 = ((Button) findViewById(R.id.button9));
				Button b2 = ((Button) findViewById(R.id.button10));
				HorizontalScrollView HSV1 = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
				
				b1.setAlpha(1);
				b2.setAlpha(1);
				b1.setX(96-HSV1.getScrollX());
				b2.setX(96-HSV1.getScrollX());
				
				b1.setEnabled(true);
				b2.setEnabled(true);
			}
		});
        button[4] = (ImageButton)findViewById(R.id.button5);
        button[4].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//startActivity(new Intent("android.intent.action.ENTERTAINMENT"));
				
				clearButtons();
				
				button[4].setImageResource(R.drawable.bw_entertainment_h);
				
				Button b1 = ((Button) findViewById(R.id.button11));
				Button b2 = ((Button) findViewById(R.id.button12));
				Button b3 = ((Button) findViewById(R.id.button13));
				Button b4 = ((Button) findViewById(R.id.button14));
				HorizontalScrollView HSV1 = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
				
				b1.setAlpha(1);
				b2.setAlpha(1);
				b3.setAlpha(1);
				b4.setAlpha(1);
				b1.setX(151-HSV1.getScrollX());
				b2.setX(151-HSV1.getScrollX());
				b3.setX(151-HSV1.getScrollX());
				b4.setX(151-HSV1.getScrollX());
				
				b1.setEnabled(true);
				b2.setEnabled(true);
				b3.setEnabled(true);
				b4.setEnabled(true);
			}
		});
        button[3] = (ImageButton)findViewById(R.id.button4);
        button[3].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//startActivity(new Intent("android.intent.action.FOOD"));
				
				clearButtons();
				
				button[3].setImageResource(R.drawable.bw_food_h);
				
				Button b1 = ((Button) findViewById(R.id.button15));
				Button b2 = ((Button) findViewById(R.id.button16));
				Button b3 = ((Button) findViewById(R.id.button17));
				HorizontalScrollView HSV1 = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
				
				b1.setAlpha(1);
				b2.setAlpha(1);
				b3.setAlpha(1);
				b1.setX(208-HSV1.getScrollX());
				b2.setX(208-HSV1.getScrollX());
				b3.setX(208-HSV1.getScrollX());
				
				b1.setEnabled(true);
				b2.setEnabled(true);
				b3.setEnabled(true);
			}
		});
        button[2] = (ImageButton)findViewById(R.id.button3);
        button[2].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//startActivity(new Intent("android.intent.action.HOUSING"));
				clearButtons();
				
				button[2].setImageResource(R.drawable.bw_housing_h);
				
				Button b1 = ((Button) findViewById(R.id.button18));
				Button b2 = ((Button) findViewById(R.id.button19));
				HorizontalScrollView HSV1 = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
				
				b1.setAlpha(1);
				b2.setAlpha(1);
				b1.setX(265-HSV1.getScrollX());
				b2.setX(265-HSV1.getScrollX());
				
				b1.setEnabled(true);
				b2.setEnabled(true);
			}
		});
        button[1] = (ImageButton)findViewById(R.id.button2);
        button[1].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				clearButtons();
				
				button[1].setImageResource(R.drawable.bw_myprofile_h);
				
				startActivity(new Intent("android.intent.action.MYPROFILE"));
			}
		});
        button[0] = (ImageButton)findViewById(R.id.button1);
        button[0].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				clearButtons();
				
				button[0].setImageResource(R.drawable.bw_news_h);
				
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
        dropButtonHandler(dropButtonList[8],"android.intent.action.BAR");
        dropButtonHandler(dropButtonList[9],"android.intent.action.ON_CAMPUS");
        dropButtonHandler(dropButtonList[10],"android.intent.action.OFF_CAMPUS");
    }
    
    public void dropButtonHandler(Button theButton, final String theIntent)
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