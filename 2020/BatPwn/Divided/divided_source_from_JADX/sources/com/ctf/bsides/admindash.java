package com.ctf.bsides;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class admindash extends AppCompatActivity {

    /* renamed from: c */
    Cursor f4c = null;
    String supersecretkey = "0000011111000001";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0045R.layout.activity_admindash);
        ((Button) findViewById(C0045R.C0047id.button01)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                bsidesdb myDbHelper = new bsidesdb(admindash.this);
                try {
                    myDbHelper.createDataBase();
                    try {
                        myDbHelper.openDataBase();
                        Toast.makeText(admindash.this, "Successfully Imported", 0).show();
                        admindash.this.f4c = myDbHelper.query("credentials", null, null, null, null, null, null);
                        if (admindash.this.f4c.moveToFirst()) {
                            do {
                                admindash admindash = admindash.this;
                                StringBuilder sb = new StringBuilder();
                                sb.append("Username: ");
                                sb.append(admindash.this.f4c.getString(0));
                                sb.append("\nPassword: ");
                                sb.append(admindash.this.f4c.getString(1));
                                sb.append("\nFlag: ");
                                sb.append(admindash.this.f4c.getString(2));
                                sb.append("\n");
                                Toast.makeText(admindash, sb.toString(), 1).show();
                            } while (admindash.this.f4c.moveToNext());
                        }
                    } catch (SQLException sqle) {
                        throw sqle;
                    }
                } catch (IOException e) {
                    throw new Error("Unable to create database");
                }
            }
        });
    }
}
