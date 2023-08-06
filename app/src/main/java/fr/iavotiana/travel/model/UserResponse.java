package fr.iavotiana.travel.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("UserModel")
    private  UserModel UserModel;
    @SerializedName("token")
    private String token;


    public UserModel getUserModel() {
        return UserModel;
    }

    public void setUserModel(UserModel userModel) {
        UserModel = userModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
