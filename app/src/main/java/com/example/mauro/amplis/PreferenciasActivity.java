package com.example.mauro.amplis;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.widget.TextView;
import android.widget.Toolbar;

public class PreferenciasActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenciasFragment()).commit();
/*

        Toolbar tool = findViewById(R.id.appbar);
        TextView vFrag = (TextView) findViewById(R.id.vFrag);

        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(PreferenciasActivity.this);
        switch(prefs.getString("color", "RO")) {
            case "VE":
                tool.setTitleTextColor(Color.parseColor("#4CAF50"));
                vFrag.setTextColor(Color.parseColor("#4CAF50"));
                break;
            case "AM":
                tool.setTitleTextColor(Color.parseColor("#FFEB3B"));
                vFrag.setTextColor(Color.parseColor("#FFEB3B"));
                break;
            default:
                tool.setTitleTextColor(Color.parseColor("#FF0000"));
                vFrag.setTextColor(Color.parseColor("#FF0000"));

        }
*/


    }
}
