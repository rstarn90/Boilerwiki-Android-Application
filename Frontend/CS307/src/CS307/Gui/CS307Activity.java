package CS307.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CS307Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Button button;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        button = (Button)findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				setContentView(R.layout.academic);
				
			}
		});
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFormResource(this,R.array.subject_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
    }
    /*
    public void onClick(View v)
    {
    	startActivity(new Intent(CS307Activity.this,ActivityToBeCalled.java));
    }
    */
}
public class MyOnItemSelectedListener implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent,
        View view, int pos, long id) {
      Toast.makeText(parent.getContext(), "Subject" +
          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView parent) {
      // Do nothing.
    }
}