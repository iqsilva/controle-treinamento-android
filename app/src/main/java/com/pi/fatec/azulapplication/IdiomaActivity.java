package com.pi.fatec.azulapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class IdiomaActivity extends AppCompatActivity {
    SharedPreferences shp;

    public final Integer valor = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idioma);
    }

    public void sair(View v)
    {
        try
        {
            setResult(Activity.RESULT_OK, null );
            finish();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Erro : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void setLocalePT (View v){
        //Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale("pt");
        Locale.setDefault(locale);

        android.content.res.Configuration config = new android.content.res.Configuration();

        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // recupera (ou cria) uma instância editável do arquivo de preferencia do Android, pelo seu nome/chave (no caso "pref")
        shp = this.getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = shp.edit();




        // armazenar o valor em preferências
        edit.putString("idioma", "pt");

        // Salvar as alterações em SharedPreferences
        edit.commit(); // gravar alterações

        recreate();
    }

    public void setLocaleEN (View v){
        // Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale("en");
        Locale.setDefault(locale);

        android.content.res.Configuration config = new android.content.res.Configuration();

        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // recupera (ou cria) uma instância editável do arquivo de preferencia do Android, pelo seu nome/chave (no caso "pref")
        SharedPreferences.Editor editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();

        // armazenar o valor em preferências
        editor.putString("idioma", "en");  // Salvando string

        // Salvar as alterações em SharedPreferences
        editor.commit(); // gravar alterações

        recreate();

        // Toast.makeText(getApplicationContext(), "Mudou Idioma: en", Toast.LENGTH_SHORT).show();
    }

    public void setLocaleFR (View v){
        // Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale("fr");
        Locale.setDefault(locale);

        android.content.res.Configuration config = new android.content.res.Configuration();

        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // recupera (ou cria) uma instância editável do arquivo de preferencia do Android, pelo seu nome/chave (no caso "pref")
        SharedPreferences.Editor editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();

        // armazenar o valor em preferências
        editor.putString("idioma", "fr");  // Salvando string

        // Salvar as alterações em SharedPreferences
        editor.commit(); // gravar alterações

        recreate();

        // Toast.makeText(getApplicationContext(), "Mudou Idioma: en", Toast.LENGTH_SHORT).show();
    }

    public void setLocaleES (View v){
        // Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale("es");
        Locale.setDefault(locale);

        android.content.res.Configuration config = new android.content.res.Configuration();

        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // recupera (ou cria) uma instância editável do arquivo de preferencia do Android, pelo seu nome/chave (no caso "pref")
        SharedPreferences.Editor editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();

        // armazenar o valor em preferências
        editor.putString("idioma", "es");  // Salvando string

        // Salvar as alterações em SharedPreferences
        editor.commit(); // gravar alterações

        recreate();

        // Toast.makeText(getApplicationContext(), "Mudou Idioma: en", Toast.LENGTH_SHORT).show();
    }
    public void setLocaleDE (View v){
        // Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale("de");
        Locale.setDefault(locale);

        android.content.res.Configuration config = new android.content.res.Configuration();

        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // recupera (ou cria) uma instância editável do arquivo de preferencia do Android, pelo seu nome/chave (no caso "pref")
        SharedPreferences.Editor editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();

        // armazenar o valor em preferências
        editor.putString("idioma", "de");  // Salvando string

        // Salvar as alterações em SharedPreferences
        editor.commit(); // gravar alterações

        recreate();

        // Toast.makeText(getApplicationContext(), "Mudou Idioma: en", Toast.LENGTH_SHORT).show();
    }
    public void setLocaleRU (View v){
        // Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale("ru");
        Locale.setDefault(locale);

        android.content.res.Configuration config = new android.content.res.Configuration();

        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // recupera (ou cria) uma instância editável do arquivo de preferencia do Android, pelo seu nome/chave (no caso "pref")
        SharedPreferences.Editor editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();

        // armazenar o valor em preferências
        editor.putString("idioma", "ru");  // Salvando string

        // Salvar as alterações em SharedPreferences
        editor.commit(); // gravar alterações

        recreate();

        // Toast.makeText(getApplicationContext(), "Mudou Idioma: en", Toast.LENGTH_SHORT).show();
    }
}
