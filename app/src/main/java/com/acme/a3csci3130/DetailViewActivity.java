package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * The detail view activity allows a user to change a contact's information by entering new
 * information into the edit text fields and pressing the update button, or delete a contact by
 * pressing the delete button
 */

public class DetailViewActivity extends Activity {

    private EditText nameField, businessNumberField, primaryBusinessField, addressField,
            provinceField;
    Contact receivedPersonInfo;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        receivedPersonInfo = (Contact) getIntent().getSerializableExtra("contact");
        database           = FirebaseDatabase.getInstance().getReference("contact");

        nameField            = (EditText) findViewById(R.id.nameFieldDV);
        businessNumberField  = (EditText) findViewById(R.id.businessNumberFieldDV);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusinessFieldDV);
        addressField         = (EditText) findViewById(R.id.addressFieldDV);
        provinceField        = (EditText) findViewById((R.id.provinceFieldDV));

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            businessNumberField.setText(receivedPersonInfo.businessNumber);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.provinceTerritory);
        }
    }

    /**
     * Updates the contact information on firebase with whatever the user put into the edit texts
     * @param v
     */
    public void updateContact(View v){

        String uid             = receivedPersonInfo.uid;
        String name            = nameField.getText().toString();
        String businessNumber  = businessNumberField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address         = addressField.getText().toString();
        String province        = provinceField.getText().toString();

        Contact newContact = new Contact(uid, name, businessNumber, primaryBusiness,
                                         address, province);

        database.child(uid).setValue(newContact);

        goToMain();
    }
    /**
     * Erases the selected contact from the firebase database
     * @param v
     */
    public void eraseContact(View v) {

        String uid = receivedPersonInfo.uid;
        database.child(uid).removeValue();

        goToMain();

    }
    /**
     * Takes the user back to the Main Activity
     */
    public void goToMain(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
