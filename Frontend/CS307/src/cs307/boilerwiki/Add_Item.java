package cs307.boilerwiki;

//import spinner.example.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_Item extends Activity {
	
	Button[] dropButtonList = new Button[11];
	ImageButton[] button=new ImageButton[9];
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	Button[] buttonList = new Button[1];
    	   	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        
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
        
        Spinner list = (Spinner) findViewById(R.id.button10);
        ArrayAdapter additemAdapter = ArrayAdapter.createFromResource(
        		this, R.array.Academic_list, android.R.layout.simple_spinner_item);
        		additemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        		list.setAdapter(additemAdapter);
/*        
       list.setOnItemSelectedListener(new OnItemSelectedListener() {
    	   public void onItemSelected(AdapterView<?> parentView, View view, int pos, long id){
    		   startActivity(new Intent("android.intent.action.DESCRIPTION"));
    	   }
    	   public void onNothingSelected(AdapterView<?> parentView){
    		   
    	   }
	});
 */       
        
       Spinner list2 = (Spinner) findViewById(R.id.button11);
       ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(
          		this, R.array.Entertainment_list, android.R.layout.simple_spinner_item);
       			categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           		list2.setAdapter(categoryAdapter);
           		
       Spinner list3 = (Spinner) findViewById(R.id.button12);
       ArrayAdapter classAdapter = ArrayAdapter.createFromResource(
                this, R.array.Food_list, android.R.layout.simple_spinner_item);
       classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                list3.setAdapter(classAdapter);           		
       
/*       Spinner list4 = (Spinner) findViewById(R.id.button3);
       ArrayAdapter housingAdapter = ArrayAdapter.createFromResource(
                this, R.array.Housing_list, android.R.layout.simple_spinner_item);
       			housingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                list4.setAdapter(housingAdapter);
                
  */              
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