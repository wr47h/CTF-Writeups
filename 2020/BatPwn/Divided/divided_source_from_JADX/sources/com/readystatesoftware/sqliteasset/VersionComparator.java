package com.readystatesoftware.sqliteasset;

import android.util.Log;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper.SQLiteAssetException;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class VersionComparator implements Comparator<String> {
    private static final String TAG = SQLiteAssetHelper.class.getSimpleName();
    private Pattern pattern = Pattern.compile(".*_upgrade_([0-9]+)-([0-9]+).*");

    VersionComparator() {
    }

    public int compare(String file0, String file1) {
        Matcher m0 = this.pattern.matcher(file0);
        Matcher m1 = this.pattern.matcher(file1);
        String str = "Invalid upgrade script file";
        String str2 = "could not parse upgrade script file: ";
        if (!m0.matches()) {
            String str3 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(file0);
            Log.w(str3, sb.toString());
            throw new SQLiteAssetException(str);
        } else if (m1.matches()) {
            int i = 1;
            int v0_from = Integer.valueOf(m0.group(1)).intValue();
            int v1_from = Integer.valueOf(m1.group(1)).intValue();
            int v0_to = Integer.valueOf(m0.group(2)).intValue();
            int v1_to = Integer.valueOf(m1.group(2)).intValue();
            if (v0_from != v1_from) {
                if (v0_from < v1_from) {
                    i = -1;
                }
                return i;
            } else if (v0_to == v1_to) {
                return 0;
            } else {
                if (v0_to < v1_to) {
                    i = -1;
                }
                return i;
            }
        } else {
            String str4 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(file1);
            Log.w(str4, sb2.toString());
            throw new SQLiteAssetException(str);
        }
    }
}
