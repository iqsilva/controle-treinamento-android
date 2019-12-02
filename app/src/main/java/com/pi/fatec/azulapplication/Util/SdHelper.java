package com.pi.fatec.azulapplication.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Andre Neves on 12/06/2017.
 */

public class SdHelper {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public SdHelper(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void logOut(){
        editor.putString("UserCod", "none");
        editor.commit();
    }

    public void addUserID(String userid){
        editor.putString("UserCod", userid);
        editor.commit();
    }

    public String getUserUsername(){
        return prefs.getString("UserId",null);
    }
    public int getUserID(){
        String id =  prefs.getString("UserCod",null);
        return  Integer.valueOf(id);
    }


}
