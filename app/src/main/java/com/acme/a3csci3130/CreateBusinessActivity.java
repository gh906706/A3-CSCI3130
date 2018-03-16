package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import java.util.ArrayList;
import android.content.Context;

public class CreateBusinessActivity extends Activity{
    Button submitButton;
    private EditText nameField, businessNumField, primeBusinessField, addressField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        businessNumField = (EditText) findViewById(R.id.businessNum);
        primeBusinessField = (EditText) findViewById(R.id.primeBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);
    }

    public void submitInfoButton(View v){
        String businessID = appState.firebaseReference.push().getKey();
        String businessNum = businessNumField.getText().toString();
        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String primeBusiness = primeBusinessField.getText().toString();
        String province = provinceField.getText().toString();

        Business business = new Business(businessID, businessNum, name, primeBusiness, address,province);

        appState.firebaseReference.child(businessID).setValue(business);
        finish();

        /*if (rulesChecker(business)){
            appState.firebaseReference.child(businessID).setValue(business);
            finish();
        }
        else{
            finish();
        }*/
    }

    //A method that returns false if any of the specified rules is not satisfied (and vice versa):
    public boolean rulesChecker(Business b){

        ArrayList <String> primeB = new ArrayList<String>();
        ArrayList <String> provinces = new ArrayList <String>();
        primeB.add("Fisher");
        primeB.add("Distributor");
        primeB.add("Processor");
        primeB.add("Fish Monger");

        provinces.add("AB");
        provinces.add("BC");
        provinces.add("MB");
        provinces.add("NB");
        provinces.add("NL");
        provinces.add("NS");
        provinces.add("NT");
        provinces.add("NU");
        provinces.add("ON");
        provinces.add("PE");
        provinces.add("QC");
        provinces.add("SK");
        provinces.add("YT");
        provinces.add("");

        if (b.businessNum.length() != 9){
            return false;
        }
        if (b.name.length() < 2  || b.name.length() > 48){
            return false;
        }
        if (b.address.length() > 50){
            return false;
        }
        if (!primeB.contains(b.primeBusiness)){
            return false;
        }

        if (!provinces.contains(b.province)){
            return false;
        }
        else {
            return true;
        }
    }
}