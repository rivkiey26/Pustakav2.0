package com.rifki.pustakav20;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

public class SemuaPustaka extends BaseActivity {
    private RecyclerView lst_item,bookitem;
    private LinearLayoutManager linearLayoutManager;
    private ItemObjects itemObjects;
    private AdapterItems adapterItems;
    private NewBookAdapter newBookAdapter;
    private SearchView search;
    private static String urlData = "http://kebudayaan.kemdikbud.go.id/mobile-pustaka/pustakadummy/pustaka/data.php";
    LinearLayout dynamicContent,bottonNavBar;
//    private static String urlData = "http://rivkiey.com/pustaka/data.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/

        dynamicContent = (LinearLayout)  findViewById(R.id.dynamicContent);
        bottonNavBar= (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_semua, null);
        dynamicContent.addView(wizard);

        bookitem = (RecyclerView) findViewById(R.id.BookItem);
        lst_item = (RecyclerView) findViewById(R.id.lstItem);
        linearLayoutManager = new LinearLayoutManager(this);
        lst_item.setLayoutManager(linearLayoutManager);
        AmbilData(urlData);
        AmbilDataBuku(urlData);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
// calculate height of RecyclerView based on child count
        params.height=400;
// set height of RecyclerView
        bookitem.setLayoutParams(params);

        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(SemuaPustaka.this, LinearLayoutManager.VERTICAL, false);
        lst_item.setLayoutManager(verticalLayoutmanager);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        vertical_recycler_view.setLayoutManager(mLayoutManager);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(SemuaPustaka.this, LinearLayoutManager.HORIZONTAL, false);
        bookitem.setLayoutManager(horizontalLayoutManagaer);


//        SharedPreferences prefs = getSharedPreferences("PustakaLogin", MODE_PRIVATE);
//        String restoredText = prefs.getString("dataUID", null);
        SessionManager session = new SessionManager(this);
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

    private void AmbilDataBuku (String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                itemObjects = gson.fromJson(response, ItemObjects.class);
                newBookAdapter = new NewBookAdapter(getApplicationContext(), itemObjects.buku);
                bookitem.setAdapter(newBookAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), " Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}

