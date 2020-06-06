package com.ctf.bsides;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public void dash(View view) {
        startActivity(new Intent(this, dashboard.class));
    }

    public void addash(View view) {
        startActivity(new Intent(this, admindash.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0045R.layout.activity_main);
    }
}
