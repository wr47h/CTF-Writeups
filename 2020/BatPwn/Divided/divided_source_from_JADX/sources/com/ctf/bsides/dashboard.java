package com.ctf.bsides;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class dashboard extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0045R.layout.activity_dashboard);
        Button btn2 = (Button) findViewById(C0045R.C0047id.secretbtn);
        ((Button) findViewById(C0045R.C0047id.gallerybtn)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dashboard.this.startActivity(new Intent(dashboard.this, gallery.class));
            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dashboard.this.startActivity(new Intent(dashboard.this, secret.class));
            }
        });
    }
}
