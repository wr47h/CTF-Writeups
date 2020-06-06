package com.readystatesoftware.sqliteasset;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class Utils {
    private static final String TAG = SQLiteAssetHelper.class.getSimpleName();

    Utils() {
    }

    public static List<String> splitSqlScript(String script, char delim) {
        List<String> statements = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inLiteral = false;
        char[] content = script.toCharArray();
        for (int i = 0; i < script.length(); i++) {
            if (content[i] == '\"') {
                inLiteral = !inLiteral;
            }
            if (content[i] != delim || inLiteral) {
                sb.append(content[i]);
            } else if (sb.length() > 0) {
                statements.add(sb.toString().trim());
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            statements.add(sb.toString().trim());
        }
        return statements;
    }

    public static void writeExtractedFileToDisk(InputStream in, OutputStream outs) throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int read = in.read(buffer);
            int length = read;
            if (read > 0) {
                outs.write(buffer, 0, length);
            } else {
                outs.flush();
                outs.close();
                in.close();
                return;
            }
        }
    }

    public static ZipInputStream getFileFromZip(InputStream zipFileStream) throws IOException {
        ZipInputStream zis = new ZipInputStream(zipFileStream);
        ZipEntry nextEntry = zis.getNextEntry();
        ZipEntry ze = nextEntry;
        if (nextEntry == null) {
            return null;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("extracting file: '");
        sb.append(ze.getName());
        sb.append("'...");
        Log.w(str, sb.toString());
        return zis;
    }

    public static String convertStreamToString(InputStream is) {
        return new Scanner(is).useDelimiter("\\A").next();
    }
}
