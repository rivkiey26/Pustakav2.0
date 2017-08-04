package com.rifki.pustakav20;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rifki.pustakav20.Adapter.AdapterItems;
import com.rifki.pustakav20.Adapter.NewBookAdapter;
import com.rifki.pustakav20.App.SessionManager;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView lst_item,bookitem;
    private LinearLayoutManager linearLayoutManager;
    private ItemObjects itemObjects;
    private AdapterItems adapterItems;
    private NewBookAdapter newBookAdapter;
    private SearchView search;
    private SessionManager session;
    TextView txt_email,txt_profile;
    private static String urlData = "http://kebudayaan.kemdikbud.go.id/mobile-pustaka/pustakadummy/pustaka/data.php";
    LinearLayout dynamicContent,bottonNavBar;
//    private static String urlData = "http://rivkiey.com/pustaka/data.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dynamicContent = (LinearLayout)  findViewById(R.id.dynamicContent);
        bottonNavBar= (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_main, null);
        dynamicContent.addView(wizard);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //get the reference of RadioGroup.

        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton rb=(RadioButton)findViewById(R.id.home);
        NavigationView navhead = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navhead.getHeaderView(0);

        // Change the corresponding icon and text color on nav button click.
//        txt_profil = (TextView) findViewById(R.id.txt_profile);
//        txt_email = (TextView) findViewById(R.id.txt_email);
        SessionManager session = new SessionManager(this);
//        txt_profil.setText(session.getDataNAMA().toString());
//        txt_email.setText(session.getDataEMAIL().toString());
        session = new SessionManager(getApplicationContext());
        txt_profile = (TextView) headerView.findViewById(R.id.txt_profile);
        txt_profile.setText(session.getDataNAMA().toString());
        txt_email = (TextView) headerView.findViewById(R.id.txt_email);
        txt_email.setText(session.getDataEMAIL().toString());
        lst_item = (RecyclerView) findViewById(R.id.lstItem);
        linearLayoutManager = new LinearLayoutManager(this);
        lst_item.setLayoutManager(linearLayoutManager);
        AmbilData(urlData);



//        SharedPreferences prefs = getSharedPreferences("PustakaLogin", MODE_PRIVATE);
//        String restoredText = prefs.getString("dataUID", null);


        System.out.println(session.getDataUID());

    }



    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh, menu);
        search = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        search.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide action item
                menu.findItem(R.id.action_refresh).setVisible(false);

            }
        });

        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                menu.findItem(R.id.action_refresh).setVisible(true);
                return false;
            }
        });


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                query=query.replaceAll(" ","+");
                AmbilData("http://kebudayaan.kemdikbud.go.id/mobile-pustaka/pustakadummy/pustaka/search.php?cari=" +query);
//                AmbilData("http://rivkiey.com/pustaka/search.php?cari=" + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText=newText.replaceAll(" ","+");
                AmbilData("http://kebudayaan.kemdikbud.go.id/mobile-pustaka/pustakadummy/pustaka/search.php?cari=" + newText);
//                AmbilData("http://rivkiey.com/pustaka/search.php?cari=" + newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                item.setVisible(false);
            case R.id.action_refresh:
                AmbilData(urlData);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AmbilData(String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                itemObjects = gson.fromJson(response, ItemObjects.class);
                adapterItems = new AdapterItems(getApplicationContext(), itemObjects.buku);
                lst_item.setAdapter(adapterItems);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), " Tidak ada Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            // Handle the camera action
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
         else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this,GalleryActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

