package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, businessNumberField, primaryBusinessField, addressField,
            provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.nameField);
        businessNumberField = (EditText) findViewById(R.id.businessNumberField);
        addressField = (EditText) findViewById(R.id.addressField);
        provinceField = (EditText) findViewById(R.id.provinceField);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String contactID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String businessNumber = businessNumberField.getText().toString();
        String address = addressField.getText().toString();
        String province = addressField.getText().toString();
        Contact person = new Contact(contactID, name, businessNumber, address, province);

        appState.firebaseReference.child(contactID).setValue(person);

        finish();

    }
}
