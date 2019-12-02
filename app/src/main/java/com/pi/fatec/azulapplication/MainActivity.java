package com.pi.fatec.azulapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SobreFragment.OnFragmentInteractionListener,
        CursosFragment.OnFragmentInteractionListener,
        CursosEsperaFragment.OnFragmentInteractionListener,
        CursosAndamentoFragment.OnFragmentInteractionListener,
        CursosConcluidoFragment.OnFragmentInteractionListener,
        PerfilFragment.OnFragmentInteractionListener{

    SharedPreferences shp;
    private boolean doubleBackToExitPressedOnce = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        //método para saber qual idioma foi selecionado
        lerIdioma();

        shp = this.getSharedPreferences("UserInfo", MODE_PRIVATE);

        String userid = shp.getString("UserId", "none");

        if (userid.equals("none") || userid.trim().equals("")) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        } else {
            SharedPreferences.Editor edit = shp.edit();
            edit.commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FrameLayout container = (FrameLayout) findViewById(R.id.fragment_container);
        lerIdioma();
        setSupportActionBar(toolbar);

        CursosFragment cursosFragment = new CursosFragment();
        cursosFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,cursosFragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, this.getText(R.string.voltar), Toast.LENGTH_SHORT).show();
        FrameLayout container = (FrameLayout) findViewById(R.id.fragment_container);
            container.removeAllViews();
            CursosFragment cursosFragment = new CursosFragment();
            cursosFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,cursosFragment).commit();
        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cursos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    FrameLayout container = (FrameLayout) findViewById(R.id.fragment_container);
    int id = item.getItemId();
        if (id == R.id.nav_menu_sobre) {
            container.removeAllViews();
            // Create a new Fragment to be placed in the activity layout
            SobreFragment sobreFragment = new SobreFragment();
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            sobreFragment.setArguments(getIntent().getExtras());
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, sobreFragment).commit();
            this.doubleBackToExitPressedOnce = false;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FrameLayout container = (FrameLayout) findViewById(R.id.fragment_container);
        this.doubleBackToExitPressedOnce = false;

        if (id == R.id.nav_cursos) {
            container.removeAllViews();
            CursosFragment cursosFragment = new CursosFragment();
            cursosFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,cursosFragment).commit();

        } else if (id == R.id.nav_menu_cursos_andamento) {
            container.removeAllViews();
            CursosAndamentoFragment cursosAndamentoFragment = new CursosAndamentoFragment();
            cursosAndamentoFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,cursosAndamentoFragment).commit();

        } else if (id == R.id.nav_menu_cursos_espera) {
            container.removeAllViews();
            CursosEsperaFragment cursosEsperaFragment = new CursosEsperaFragment();
            cursosEsperaFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,cursosEsperaFragment).commit();

        } else if (id == R.id.nav_menu_cursos_concluido) {
            container.removeAllViews();
            CursosConcluidoFragment cursosConcluidoFragment = new CursosConcluidoFragment();
            cursosConcluidoFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,cursosConcluidoFragment).commit();

        } else if (id == R.id.nav_menu_language){
            //intent para idioma
            Intent tela = new Intent(this, IdiomaActivity.class);
            startActivityForResult( tela, 1 );

        } else if (id == R.id.nav_menu_perfil){
            container.removeAllViews();
            PerfilFragment perfilFragment = new PerfilFragment();
            perfilFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,perfilFragment).commit();

        } else if (id == R.id.nav_menu_logout) {
            LogOut();
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void LogOut() {
        SharedPreferences.Editor edit = shp.edit();
        edit.putString("UserId", "");
        edit.commit();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
//métodos referentes aos idiomas
    @Override
    public void onResume()
    {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        recreate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            //Toast.makeText(getApplicationContext(), "Voltou da tela", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Erro : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void lerIdioma()
    {
        shp = this.getSharedPreferences("UserInfo", MODE_PRIVATE);
        String idioma = shp.getString("idioma", "pt");

        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(idioma);
        Locale.setDefault(locale);
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        //setContentView(R.layout.activity_main);
    }
    //fim dos métodos referentes aos idiomas
}