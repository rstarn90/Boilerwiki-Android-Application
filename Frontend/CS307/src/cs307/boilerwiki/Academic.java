package cs307.boilerwiki;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
        final Context context = this;
        setContentView(R.layout.academic);
        
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

				final CharSequence[] items = {"Professors", "Classes","Books"};
				final String[] intentList = {"android.intent.action.PROFESSOR","android.intent.action.CLASSES","android.intent.action.BOOKS"};
				
				popUpBuilder("Academics",context,items,intentList);
				
				button[5].setImageResource(R.drawable.bw_academic_h);
			}
		});
        button[4] = (ImageButton)findViewById(R.id.button5);
        button[4].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//startActivity(new Intent("android.intent.action.ENTERTAINMENT"));
				
				clearButtons();
				
				final CharSequence[] items = {"Bars","Hookah","Student Orgs","Activities"};
				final String[] intentList = {"android.intent.action.BAR","android.intent.action.HOOKAH","android.intent.action.STUDENT_ORGANIZATIONS","android.intent.action.ACTIVITIES"};
				
				
				popUpBuilder("Entertainment",context,items,intentList);
				
				button[4].setImageResource(R.drawable.bw_entertainment_h);
			}
		});
        button[3] = (ImageButton)findViewById(R.id.button4);
        button[3].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//startActivity(new Intent("android.intent.action.FOOD"));
				
				clearButtons();
				
				final CharSequence[] items = {"Restaurants","Dining Courts","Bars"};
				final String[] intentList = {"android.intent.action.FOOD","android.intent.action.DINING_COURT","android.intent.action.BAR"};
				
				popUpBuilder("Food",context,items,intentList);
				
				button[3].setImageResource(R.drawable.bw_food_h);
			}
		});
        button[2] = (ImageButton)findViewById(R.id.button3);
        button[2].setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//startActivity(new Intent("android.intent.action.HOUSING"));
				clearButtons();
				
				final CharSequence[] items = {"Off Campus","On Campus"};
				final String[] intentList = {"android.intent.action.OFF_CAMPUS","android.intent.action.ON_CAMPUS"};
								
				popUpBuilder("Housing",context,items,intentList);
				
				button[2].setImageResource(R.drawable.bw_housing_h);
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
    }
    
    public void popUpBuilder(String title, Context context, CharSequence[] items,final String[] intent)
    {
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		
    	// set title
		alertDialogBuilder.setTitle(title);
 
		// set dialog message
		alertDialogBuilder.setItems(items, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int item) {
				    System.out.println(intent[item]);
					startActivity(new Intent(intent[item]));
			    }
			});
 
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
 
		// show it
		alertDialog.show();
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
    }
}