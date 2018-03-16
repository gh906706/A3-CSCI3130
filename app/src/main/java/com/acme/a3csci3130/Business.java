package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GhadiKashgari on 2018-03-12.
 */

public class Business implements Serializable {
    public String businessNum;
    public String name;
    public String primeBusiness;
    public String address;
    public String province;
    public String ID;


    public Business() {

    }

    public Business(String ID,String businessNum, String name, String primeBusiness, String address, String province){
        this.ID = ID;
        this.businessNum = businessNum;
        this.name = name;
        this.address = address;
        this.primeBusiness = primeBusiness;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID",ID);
        result.put("businessNum", businessNum);
        result.put("name", name);
        result.put("primeBusiness", primeBusiness);
        result.put("address", address);
        result.put("province", province);
        /*if(address.length() == 0){
            result.put("address", address);
        }
        if(province.length() == 0){
            result.put("province", province);
        }*/
        return result;
    }
}
