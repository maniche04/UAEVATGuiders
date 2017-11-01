package com.nagisons.uaevatguide;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class LawActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private String law;
    private JSONObject lawJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("UAE Vat Law");
        this.parseLaw();
    }

    public void parseLaw() {
        this.loadLaw();
        
    }

    public void loadLaw() {
        try {
            InputStream is = getAssets().open("vatlaw.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            law = new String(buffer, "UTF-8");
            try {
                this.lawJson = new JSONObject(this.law);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
