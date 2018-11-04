package com.example.czhan.safebears;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

//import android.view.Menu;
//import android.view.MenuItem;

public class EntryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] feetHeights={"3","4","5","6", "7"};
    String[] inchesHeights={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    String[] hairColors={"Black", "Brown", "Blonde", "Red", "Gray", "N/A - Bald", "Uncertain"};
    String[] eyeColors={"Amber", "Blue", "Brown", "Gray", "Green", "Hazel", "Red"};
    Button submitInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.FeetID);
        Spinner spin2 = (Spinner) findViewById(R.id.InchesID);
        Spinner spin3 = (Spinner) findViewById(R.id.HairID);
        Spinner spin4 = (Spinner) findViewById(R.id.EyeID);
        submitInfo = (Button) findViewById(R.id.submitButton);
        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCrime(view);
            }
        });
        spin.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        spin3.setOnItemSelectedListener(this);
        spin4.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the feet/inches height list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,feetHeights);
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,inchesHeights);
        ArrayAdapter aa3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,hairColors);
        ArrayAdapter aa4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,eyeColors);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin2.setAdapter(aa2);
        spin3.setAdapter(aa3);
        spin4.setAdapter(aa4);
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    // TODO Auto-generated method stub

    }

    public void createNewCrime (View view) {

        onBackPressed();
    }
}
