package com.pi.fatec.azulapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pi.fatec.azulapplication.Util.ConnectionClass;
import com.pi.fatec.azulapplication.Util.SdHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    ConnectionClass connectionClass;
    EditText edtuserid, edtpass;
    Button btnlogin;
    ProgressBar pbbar;
    SharedPreferences shp;
    SdHelper sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        connectionClass = new ConnectionClass();
        edtuserid = (EditText) findViewById(R.id.edtuserid);
        edtpass = (EditText) findViewById(R.id.edtpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);

        shp = this.getSharedPreferences("UserInfo", MODE_PRIVATE);
        sd = new SdHelper(this);

        String userid = shp.getString("UserId", "none");


        if (userid.equals("none") || userid.trim().equals("")) {

        } else {

            /*Intent cursosActivity = new Intent(this,MainActivity.class);
            startActivity(cursosActivity);*/

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();

        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin doLogin = new DoLogin();
                doLogin.execute("");
            }
        });
    }

    /*public void openCursosActivity(View login){
        Intent cursosActivity = new Intent(this,MainActivity.class);
        startActivity(cursosActivity);
    }*/

    public class DoLogin extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;
        String userid = edtuserid.getText().toString();
        String password = edtpass.getText().toString();

        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            if (isSuccess) {

                SharedPreferences.Editor edit = shp.edit();
                edit.putString("UserId", userid);
                edit.commit();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(LoginActivity.this, r, Toast.LENGTH_SHORT).show();
            }
        }
        private void toast(String mensagem){
            Toast.makeText(getApplicationContext(), mensagem,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            if (userid.trim().equals("") || password.trim().equals(""))
                z = "Please enter User Id and Password";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = getApplicationContext().getString(R.string.erroLoginVazio);
                    } else {
                        String query = "select Cod_Func from Funcionario where Nome_Usuario='"
                                + userid + "' and Senha='" + password + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            sd.addUserID(rs.getObject(1).toString());
                            isSuccess = true;
                        } else {
                            z = getApplicationContext().getString(R.string.erroLogin);
                            isSuccess = false;
                        }
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions";
                }
            }
            return z;
        }
    }
}
