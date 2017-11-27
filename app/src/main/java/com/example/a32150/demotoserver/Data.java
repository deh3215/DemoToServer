package com.example.a32150.demotoserver;

/**
 * Created by 32150 on 2017/11/27.
 */

public class Data {
    String address;
    String phone;

    public Data()   {
        this("地球", "0912345678");

    }

    public Data(String address, String phone)   {
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
