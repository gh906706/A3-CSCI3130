
package com.acme.a3csci3130;

import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import java.util.ArrayList;

public class DetailViewActivity extends Activity {

    private EditText nameField, addressField, businessNumField, provinceField, primeBusinessField;
    Business receivedBusinessInfo;

    private MyApplicationData appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appData = (MyApplicationData)getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business) getIntent().getSerializableExtra("Business");

        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        businessNumField = (EditText) findViewById(R.id.businessNum);
        provinceField = (EditText) findViewById(R.id.province);
        primeBusinessField = (EditText) findViewById(R.id.primeBusiness);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            businessNumField.setText(receivedBusinessInfo.businessNum);
            primeBusinessField.setText(receivedBusinessInfo.primeBusiness);
            if (receivedBusinessInfo.province != null){
                provinceField.setText(receivedBusinessInfo.province);
            }
            if (receivedBusinessInfo.address != null){
                addressField.setText(receivedBusinessInfo.address);
            }
        }
    }

    public void updateBusiness(View v){
        //TODO: Update contact funcionality
        appData = (MyApplicationData)getApplication();
        String businessID = appData.firebaseReference.push().getKey();
        String businessNum = businessNumField.getText().toString();
        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String primeBusiness = primeBusinessField.getText().toString();
        String province = provinceField.getText().toString();

        Business business = new Business(businessID, businessNum, name, primeBusiness, address,province);

        appData.firebaseReference.child(receivedBusinessInfo.ID).child("name").setValue(name);
        appData.firebaseReference.child(receivedBusinessInfo.ID).child("number").setValue(businessNum);
        appData.firebaseReference.child(receivedBusinessInfo.ID).child("business").setValue(business);
        appData.firebaseReference.child(receivedBusinessInfo.ID).child("address").setValue(address);
        appData.firebaseReference.child(receivedBusinessInfo.ID).child("province").setValue(province);
        finish();
        /*if (rulesChecker(business)){
            //appData.firebaseReference.child(businessID).setValue(business);
            appData.firebaseReference.child(receivedBusinessInfo.ID).child("name").setValue(name);
            appData.firebaseReference.child(receivedBusinessInfo.ID).child("number").setValue(businessNum);
            appData.firebaseReference.child(receivedBusinessInfo.ID).child("business").setValue(business);
            appData.firebaseReference.child(receivedBusinessInfo.ID).child("address").setValue(address);
            appData.firebaseReference.child(receivedBusinessInfo.ID).child("province").setValue(province);
            finish();
        }
        else{

            //let user know information is incorrect.
            Context context = getApplicationContext();
            CharSequence text = "Invalid input when updating Business!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }*/
    }

    public void eraseBusiness(View v)
    {
        //TODO: Erase contact functionality
        appData = (MyApplicationData)getApplication();
        if (receivedBusinessInfo!= null){
            appData.firebaseReference.child(receivedBusinessInfo.ID).removeValue();
        }
        finish();
    }

   /* public void readBusiness(View v){
        appData = (MyApplicationData)getApplication();
        if (receivedBusinessInfo!= null){
            appData.firebaseReference.child(receivedBusinessInfo.toString());
        }
        finish();
    }

    public void createBusiness(View v){
        appData = (MyApplicationData)getApplication();
        String businessID = appData.firebaseReference.push().getKey();
        String businessNum = businessNumField.getText().toString();
        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String primeBusiness = primeBusinessField.getText().toString();
        String province = provinceField.getText().toString();

        Business business = new Business(businessID,businessNum,name,primeBusiness,address,province);
        if (rulesChecker(business)){
            appData.firebaseReference.push().setValue(business);
        }
        finish();
    }*/

    //A method that returns false if any of the specified rules is not satisfied (and vice versa):
    public boolean rulesChecker(Business b){

        ArrayList<String> primeB = new ArrayList<String>();
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