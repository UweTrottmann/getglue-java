package com.uwetrottmann.getglue.entities;

import com.google.gson.annotations.SerializedName;

public class GetGlueInteraction {

    public String id;
    public String action;
    public String timestamp;
    @SerializedName("object")
    public GetGlueObject _object;

}
