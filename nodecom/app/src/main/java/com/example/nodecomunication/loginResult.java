package com.example.nodecomunication;

import com.google.gson.annotations.SerializedName;

public class loginResult  {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
