package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, businessNumberField, primaryBusinessField, addressField,
            provinceField;
    private MyApplicationData appState;

    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        database = FirebaseDatabase.getInstance().getReference("contact");

        submitButton         = (Button) findViewById(R.id.createButton);
        nameField            = (EditText) findViewById(R.id.nameField);
        businessNumberField  = (EditText) findViewById(R.id.businessNumberField);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusinessField);
        addressField         = (EditText) findViewById(R.id.addressField);
        provinceField        = (EditText) findViewById(R.id.provinceField);
    }

    /**
     * Creates a Contact object using information that was entered into the edit texts on this page
     * and pushes this object to the firebase database provided that the name, business number, and
     * primary business fields are not empty
     * @param v
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String contactID = database.push().getKey();

        String name = nameField.getText().toString();
        String businessNumber = businessNumberField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();

        Contact contact = new Contact(contactID, name, businessNumber, primaryBusiness,
                                     address, province);

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(businessNumber) &&
                !TextUtils.isEmpty(primaryBusiness)) {

            database.child(contactID).setValue(contact);

            finish();

        } else{

            Toast.makeText(this, "Please note that Name, Business Number, and Primary" +
                    "Business are required fields.", Toast.LENGTH_LONG).show();
        }

        //finish();

    }
}
