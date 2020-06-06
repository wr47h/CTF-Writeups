package com.ctf.bsides;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class secret extends AppCompatActivity {
    public static void main(String[] args) {
        int[] array = {95, 53, 51, 99, 48, 110, 100, 95, 108, 48, 118, 101, 125};
        StringBuffer sb = new StringBuffer();
        for (int i : array) {
            sb.append((char) i);
        }
        System.out.println(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0045R.layout.activity_secret);
    }
}
