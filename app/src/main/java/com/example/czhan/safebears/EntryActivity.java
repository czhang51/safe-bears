package com.example.czhan.safebears;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.czhan.safebears.models.Crime;
import com.parse.ParseException;
import com.parse.SaveCallback;

//import android.view.Menu;
//import android.view.MenuItem;

public class EntryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] feetHeights={"3","4","5","6", "7"};
    String[] inchesHeights={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    String[] hairColors={"Black", "Brown", "Blonde", "Red", "Gray", "N/A - Bald", "Uncertain"};
    String[] eyeColors={"Amber", "Blue", "Brown", "Gray", "Green", "Hazel", "Red"};
    Button submitInfo;
    private EditText locationAddress;
    private EditText crimeDescription;
    private EditText time;
    private EditText age;
    private EditText weight;
    private RadioGroup genderOptions;
    private boolean genderPicked;
    private String gender;
    private String feet;
    private String inches;
    private String hair;
    private String eye;
    private Spinner feetSpinner;
    private Spinner inchesSpinner;
    private Spinner hairSpinner;
    private Spinner eyeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        setUpRadioGroupListener();
        setContentView(R.layout.activity_entry);
        setUpSpinners();
    }

    private void findViews() {
        locationAddress = findViewById(R.id.timeID);
        crimeDescription = findViewById(R.id.crimeDescription);
        time = findViewById(R.id.timeID);
        age = findViewById(R.id.AgeEstID);
        weight = findViewById(R.id.weightID);
        genderOptions = findViewById(R.id.GenderGroup);
        submitInfo = (Button) findViewById(R.id.submitButton);
        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCrime(view);
            }
        });
    }

    private void setUpSpinners() {
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        feetSpinner = (Spinner) findViewById(R.id.FeetID);
        inchesSpinner = (Spinner) findViewById(R.id.InchesID);
        hairSpinner = (Spinner) findViewById(R.id.HairID);
        eyeSpinner = (Spinner) findViewById(R.id.EyeID);
        //Creating the ArrayAdapter instance having the feet/inches height list
        ArrayAdapter feetAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,feetHeights);
        ArrayAdapter inchesAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,inchesHeights);
        ArrayAdapter hairAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,hairColors);
        ArrayAdapter eyeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,eyeColors);
        feetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inchesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eyeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        feetSpinner.setAdapter(feetAdapter);
        inchesSpinner.setAdapter(inchesAdapter);
        hairSpinner.setAdapter(hairAdapter);
        eyeSpinner.setAdapter(eyeAdapter);
        feetSpinner.setSelection(feetAdapter.getPosition(feet), true);
        inchesSpinner.setSelection(inchesAdapter.getPosition(inches), true);
        hairSpinner.setSelection(hairAdapter.getPosition(hair), true);
        eyeSpinner.setSelection(eyeAdapter.getPosition(eye), true);
        setUpSpinnerListeners();
    }

    private void setUpSpinnerListeners() {
        feetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                feet = feetHeights[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        inchesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                inches = inchesHeights[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        hairSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hair = hairColors[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        eyeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eye = eyeColors[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setUpRadioGroupListener() {
        genderOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                genderPicked = true;
                switch (checkedId) {
                    case R.id.male:
                        gender = "Male";
                        break;
                    case R.id.female:
                        gender = "Female";
                        break;
                    case R.id.unsure:
                        gender = "Other";
                        break;
                }
            }
        });
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
        final Crime newCrime = new Crime();
        newCrime.setLocation(locationAddress.getText().toString());
        newCrime.setDescription(crimeDescription.getText().toString());
        newCrime.setAge(Integer.parseInt(age.getText().toString()));
        newCrime.setTime(time.getText().toString());
        newCrime.setWeight(Integer.parseInt(weight.getText().toString()));
        newCrime.setGender(gender);
        newCrime.setHeightFeet(Integer.parseInt(feet));
        newCrime.setHeightInches(Integer.parseInt(inches));
        newCrime.setHair(hair);
        newCrime.setEye(eye);

        newCrime.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("EntryActivity", "Create crime success!");
                } else {
                    Log.e("EntryActivity", "Creating crime failed :(");
                }
            }
        });
        onBackPressed();
    }
}
