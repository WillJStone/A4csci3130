package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public  String businessNumber;
    public  String address;
    public  String provinceTerritory;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid, String name, String businessNumber, String address,
                   String provinceTerritory){
        this.uid               = uid;
        this.name              = name;
        this.businessNumber    = businessNumber;
        this.address           = address;
        this.provinceTerritory = provinceTerritory;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("businessNumber", businessNumber);
        result.put("address", address);
        result.put("provinceTerritory", provinceTerritory);

        return result;
    }
}
