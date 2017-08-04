package com.rifki.pustakav20;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rifki.pustakav20.App.SessionManager;

/**
 * Created by USER on 5/6/2017.
 */

public class ProfileActivity  extends BaseActivity {
    LinearLayout dynamicContent, bottonNavBar;
    TextView username, email;
    Button btnlogout;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/


        dynamicContent = (LinearLayout) findViewById(R.id.dynamicContent);
        bottonNavBar = (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_profile, null);
        dynamicContent.addView(wizard);
        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);
        btnlogout = (Button) findViewById(R.id.btn_logout);
        session = new SessionManager(getApplicationContext());
        //get the reference of RadioGroup.

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.profile);

        btnlogout.setOnClickListener(logout);

        SessionManager session = new SessionManager(this);
//        System.out.println(session.getDataEMAIL());
//        if(session.getDataEMAIL() != null){
            username.setText("Nama :  "+session.getDataNAMA().toString());
            email.setText("Email :  " +session.getDataEMAIL().toString());
//        }
    }

    private View.OnClickListener logout = new View.OnClickListener() {
        public void onClick(View v) {
            session.logoutUser();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
