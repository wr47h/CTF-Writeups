package com.readystatesoftware.sqliteasset;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.zip.ZipInputStream;

public class SQLiteAssetHelper extends SQLiteOpenHelper {
    private static final String ASSET_DB_PATH = "databases";
    private static final String TAG = SQLiteAssetHelper.class.getSimpleName();
    private String mAssetPath;
    private final Context mContext;
    private SQLiteDatabase mDatabase;
    private String mDatabasePath;
    private final CursorFactory mFactory;
    private int mForcedUpgradeVersion;
    private boolean mIsInitializing;
    private final String mName;
    private final int mNewVersion;
    private String mUpgradePathFormat;

    public static class SQLiteAssetException extends SQLiteException {
        public SQLiteAssetException() {
        }

        public SQLiteAssetException(String error) {
            super(error);
        }
    }

    public SQLiteAssetHelper(Context context, String name, String storageDirectory, CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mDatabase = null;
        this.mIsInitializing = false;
        this.mForcedUpgradeVersion = 0;
        if (version < 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Version must be >= 1, was ");
            sb.append(version);
            throw new IllegalArgumentException(sb.toString());
        } else if (name != null) {
            this.mContext = context;
            this.mName = name;
            this.mFactory = factory;
            this.mNewVersion = version;
            StringBuilder sb2 = new StringBuilder();
            String str = "databases/";
            sb2.append(str);
            sb2.append(name);
            this.mAssetPath = sb2.toString();
            if (storageDirectory != null) {
                this.mDatabasePath = storageDirectory;
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(context.getApplicationInfo().dataDir);
                sb3.append("/databases");
                this.mDatabasePath = sb3.toString();
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(name);
            sb4.append("_upgrade_%s-%s.sql");
            this.mUpgradePathFormat = sb4.toString();
        } else {
            throw new IllegalArgumentException("Database name cannot be null");
        }
    }

    public SQLiteAssetHelper(Context context, String name, CursorFactory factory, int version) {
        this(context, name, null, factory, version);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b1, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.database.sqlite.SQLiteDatabase getWritableDatabase() {
        /*
            r7 = this;
            monitor-enter(r7)
            android.database.sqlite.SQLiteDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x00d3 }
            if (r0 == 0) goto L_0x0019
            android.database.sqlite.SQLiteDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x00d3 }
            boolean r0 = r0.isOpen()     // Catch:{ all -> 0x00d3 }
            if (r0 == 0) goto L_0x0019
            android.database.sqlite.SQLiteDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x00d3 }
            boolean r0 = r0.isReadOnly()     // Catch:{ all -> 0x00d3 }
            if (r0 != 0) goto L_0x0019
            android.database.sqlite.SQLiteDatabase r0 = r7.mDatabase     // Catch:{ all -> 0x00d3 }
            monitor-exit(r7)
            return r0
        L_0x0019:
            boolean r0 = r7.mIsInitializing     // Catch:{ all -> 0x00d3 }
            if (r0 != 0) goto L_0x00cb
            r0 = 0
            r1 = 0
            r2 = 1
            r3 = 0
            r7.mIsInitializing = r2     // Catch:{ all -> 0x00b2 }
            android.database.sqlite.SQLiteDatabase r4 = r7.createOrOpenDatabase(r3)     // Catch:{ all -> 0x00b2 }
            r1 = r4
            int r4 = r1.getVersion()     // Catch:{ all -> 0x00b2 }
            if (r4 == 0) goto L_0x0041
            int r5 = r7.mForcedUpgradeVersion     // Catch:{ all -> 0x00b2 }
            if (r4 >= r5) goto L_0x0041
            android.database.sqlite.SQLiteDatabase r2 = r7.createOrOpenDatabase(r2)     // Catch:{ all -> 0x00b2 }
            r1 = r2
            int r2 = r7.mNewVersion     // Catch:{ all -> 0x00b2 }
            r1.setVersion(r2)     // Catch:{ all -> 0x00b2 }
            int r2 = r1.getVersion()     // Catch:{ all -> 0x00b2 }
            r4 = r2
        L_0x0041:
            int r2 = r7.mNewVersion     // Catch:{ all -> 0x00b2 }
            if (r4 == r2) goto L_0x0094
            r1.beginTransaction()     // Catch:{ all -> 0x00b2 }
            if (r4 != 0) goto L_0x004e
            r7.onCreate(r1)     // Catch:{ all -> 0x008f }
            goto L_0x0083
        L_0x004e:
            int r2 = r7.mNewVersion     // Catch:{ all -> 0x008f }
            if (r4 <= r2) goto L_0x007e
            java.lang.String r2 = TAG     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r5.<init>()     // Catch:{ all -> 0x008f }
            java.lang.String r6 = "Can't downgrade read-only database from version "
            r5.append(r6)     // Catch:{ all -> 0x008f }
            r5.append(r4)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = " to "
            r5.append(r6)     // Catch:{ all -> 0x008f }
            int r6 = r7.mNewVersion     // Catch:{ all -> 0x008f }
            r5.append(r6)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = ": "
            r5.append(r6)     // Catch:{ all -> 0x008f }
            java.lang.String r6 = r1.getPath()     // Catch:{ all -> 0x008f }
            r5.append(r6)     // Catch:{ all -> 0x008f }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x008f }
            android.util.Log.w(r2, r5)     // Catch:{ all -> 0x008f }
        L_0x007e:
            int r2 = r7.mNewVersion     // Catch:{ all -> 0x008f }
            r7.onUpgrade(r1, r4, r2)     // Catch:{ all -> 0x008f }
        L_0x0083:
            int r2 = r7.mNewVersion     // Catch:{ all -> 0x008f }
            r1.setVersion(r2)     // Catch:{ all -> 0x008f }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x008f }
            r1.endTransaction()     // Catch:{ all -> 0x00b2 }
            goto L_0x0094
        L_0x008f:
            r2 = move-exception
            r1.endTransaction()     // Catch:{ all -> 0x00b2 }
            throw r2     // Catch:{ all -> 0x00b2 }
        L_0x0094:
            r7.onOpen(r1)     // Catch:{ all -> 0x00b2 }
            r0 = 1
            r7.mIsInitializing = r3     // Catch:{ all -> 0x00d3 }
            if (r0 == 0) goto L_0x00ab
            android.database.sqlite.SQLiteDatabase r2 = r7.mDatabase     // Catch:{ all -> 0x00d3 }
            if (r2 == 0) goto L_0x00a8
            android.database.sqlite.SQLiteDatabase r2 = r7.mDatabase     // Catch:{ Exception -> 0x00a7 }
            r2.close()     // Catch:{ Exception -> 0x00a7 }
            goto L_0x00a8
        L_0x00a7:
            r2 = move-exception
        L_0x00a8:
            r7.mDatabase = r1     // Catch:{ all -> 0x00d3 }
            goto L_0x00b0
        L_0x00ab:
            if (r1 == 0) goto L_0x00b0
            r1.close()     // Catch:{ all -> 0x00d3 }
        L_0x00b0:
            monitor-exit(r7)
            return r1
        L_0x00b2:
            r2 = move-exception
            r7.mIsInitializing = r3     // Catch:{ all -> 0x00d3 }
            if (r0 == 0) goto L_0x00c5
            android.database.sqlite.SQLiteDatabase r3 = r7.mDatabase     // Catch:{ all -> 0x00d3 }
            if (r3 == 0) goto L_0x00c2
            android.database.sqlite.SQLiteDatabase r3 = r7.mDatabase     // Catch:{ Exception -> 0x00c1 }
            r3.close()     // Catch:{ Exception -> 0x00c1 }
            goto L_0x00c2
        L_0x00c1:
            r3 = move-exception
        L_0x00c2:
            r7.mDatabase = r1     // Catch:{ all -> 0x00d3 }
            goto L_0x00ca
        L_0x00c5:
            if (r1 == 0) goto L_0x00ca
            r1.close()     // Catch:{ all -> 0x00d3 }
        L_0x00ca:
            throw r2     // Catch:{ all -> 0x00d3 }
        L_0x00cb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00d3 }
            java.lang.String r1 = "getWritableDatabase called recursively"
            r0.<init>(r1)     // Catch:{ all -> 0x00d3 }
            throw r0     // Catch:{ all -> 0x00d3 }
        L_0x00d3:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.readystatesoftware.sqliteasset.SQLiteAssetHelper.getWritableDatabase():android.database.sqlite.SQLiteDatabase");
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase db;
        if (this.mDatabase != null && this.mDatabase.isOpen()) {
            return this.mDatabase;
        } else if (!this.mIsInitializing) {
            try {
                return getWritableDatabase();
            } catch (SQLiteException e) {
                if (this.mName != null) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Couldn't open ");
                    sb.append(this.mName);
                    sb.append(" for writing (will try read-only):");
                    Log.e(str, sb.toString(), e);
                    db = null;
                    this.mIsInitializing = true;
                    String path = this.mContext.getDatabasePath(this.mName).getPath();
                    db = SQLiteDatabase.openDatabase(path, this.mFactory, 1);
                    if (db.getVersion() == this.mNewVersion) {
                        onOpen(db);
                        String str2 = TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Opened ");
                        sb2.append(this.mName);
                        sb2.append(" in read-only mode");
                        Log.w(str2, sb2.toString());
                        this.mDatabase = db;
                        this.mIsInitializing = false;
                        if (!(db == null || db == db)) {
                            db.close();
                        }
                        return db;
                    }
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Can't upgrade read-only database from version ");
                    sb3.append(db.getVersion());
                    sb3.append(" to ");
                    sb3.append(this.mNewVersion);
                    sb3.append(": ");
                    sb3.append(path);
                    throw new SQLiteException(sb3.toString());
                }
                throw e;
            } catch (Throwable th) {
                this.mIsInitializing = false;
                if (!(db == null || db == this.mDatabase)) {
                    db.close();
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("getReadableDatabase called recursively");
        }
    }

    public synchronized void close() {
        if (this.mIsInitializing) {
            throw new IllegalStateException("Closed during initialization");
        } else if (this.mDatabase != null && this.mDatabase.isOpen()) {
            this.mDatabase.close();
            this.mDatabase = null;
        }
    }

    public final void onConfigure(SQLiteDatabase db) {
    }

    public final void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Upgrading database ");
        sb.append(this.mName);
        String str2 = " from version ";
        sb.append(str2);
        sb.append(oldVersion);
        String str3 = " to ";
        sb.append(str3);
        sb.append(newVersion);
        sb.append("...");
        Log.w(str, sb.toString());
        ArrayList<String> paths = new ArrayList<>();
        getUpgradeFilePaths(oldVersion, newVersion - 1, newVersion, paths);
        if (!paths.isEmpty()) {
            Collections.sort(paths, new VersionComparator());
            Iterator i$ = paths.iterator();
            while (i$.hasNext()) {
                String path = (String) i$.next();
                try {
                    String str4 = TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("processing upgrade: ");
                    sb2.append(path);
                    Log.w(str4, sb2.toString());
                    String sql = Utils.convertStreamToString(this.mContext.getAssets().open(path));
                    if (sql != null) {
                        for (String cmd : Utils.splitSqlScript(sql, ';')) {
                            if (cmd.trim().length() > 0) {
                                db.execSQL(cmd);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String str5 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Successfully upgraded database ");
            sb3.append(this.mName);
            sb3.append(str2);
            sb3.append(oldVersion);
            sb3.append(str3);
            sb3.append(newVersion);
            Log.w(str5, sb3.toString());
            return;
        }
        String str6 = TAG;
        StringBuilder sb4 = new StringBuilder();
        String str7 = "no upgrade script path from ";
        sb4.append(str7);
        sb4.append(oldVersion);
        sb4.append(str3);
        sb4.append(newVersion);
        Log.e(str6, sb4.toString());
        StringBuilder sb5 = new StringBuilder();
        sb5.append(str7);
        sb5.append(oldVersion);
        sb5.append(str3);
        sb5.append(newVersion);
        throw new SQLiteAssetException(sb5.toString());
    }

    public final void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Deprecated
    public void setForcedUpgradeVersion(int version) {
        setForcedUpgrade(version);
    }

    public void setForcedUpgrade(int version) {
        this.mForcedUpgradeVersion = version;
    }

    public void setForcedUpgrade() {
        setForcedUpgrade(this.mNewVersion);
    }

    private SQLiteDatabase createOrOpenDatabase(boolean force) throws SQLiteAssetException {
        SQLiteDatabase db = null;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDatabasePath);
        sb.append("/");
        sb.append(this.mName);
        if (new File(sb.toString()).exists()) {
            db = returnDatabase();
        }
        if (db != null) {
            if (force) {
                Log.w(TAG, "forcing database upgrade!");
                copyDatabaseFromAssets();
                db = returnDatabase();
            }
            return db;
        }
        copyDatabaseFromAssets();
        return returnDatabase();
    }

    private SQLiteDatabase returnDatabase() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mDatabasePath);
            sb.append("/");
            sb.append(this.mName);
            SQLiteDatabase db = SQLiteDatabase.openDatabase(sb.toString(), this.mFactory, 0);
            String str = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("successfully opened database ");
            sb2.append(this.mName);
            Log.i(str, sb2.toString());
            return db;
        } catch (SQLiteException e) {
            String str2 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("could not open database ");
            sb3.append(this.mName);
            sb3.append(" - ");
            sb3.append(e.getMessage());
            Log.w(str2, sb3.toString());
            return null;
        }
    }

    private void copyDatabaseFromAssets() throws SQLiteAssetException {
        InputStream is;
        Log.w(TAG, "copying database from assets...");
        String path = this.mAssetPath;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDatabasePath);
        String str = "/";
        sb.append(str);
        sb.append(this.mName);
        String dest = sb.toString();
        boolean isZip = false;
        try {
            is = this.mContext.getAssets().open(path);
        } catch (IOException e) {
            try {
                AssetManager assets = this.mContext.getAssets();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(path);
                sb2.append(".zip");
                is = assets.open(sb2.toString());
                isZip = true;
            } catch (IOException e2) {
                try {
                    AssetManager assets2 = this.mContext.getAssets();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(path);
                    sb3.append(".gz");
                    is = assets2.open(sb3.toString());
                } catch (IOException e3) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Missing ");
                    sb4.append(this.mAssetPath);
                    sb4.append(" file (or .zip, .gz archive) in assets, or target folder not writable");
                    SQLiteAssetException se = new SQLiteAssetException(sb4.toString());
                    se.setStackTrace(e3.getStackTrace());
                    throw se;
                }
            }
        }
        try {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(this.mDatabasePath);
            sb5.append(str);
            File f = new File(sb5.toString());
            if (!f.exists()) {
                f.mkdir();
            }
            if (isZip) {
                ZipInputStream zis = Utils.getFileFromZip(is);
                if (zis != null) {
                    Utils.writeExtractedFileToDisk(zis, new FileOutputStream(dest));
                } else {
                    throw new SQLiteAssetException("Archive is missing a SQLite database file");
                }
            } else {
                Utils.writeExtractedFileToDisk(is, new FileOutputStream(dest));
            }
            Log.w(TAG, "database copy complete");
        } catch (IOException e4) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Unable to write ");
            sb6.append(dest);
            sb6.append(" to data directory");
            SQLiteAssetException se2 = new SQLiteAssetException(sb6.toString());
            se2.setStackTrace(e4.getStackTrace());
            throw se2;
        }
    }

    private InputStream getUpgradeSQLStream(int oldVersion, int newVersion) {
        String path = String.format(this.mUpgradePathFormat, new Object[]{Integer.valueOf(oldVersion), Integer.valueOf(newVersion)});
        try {
            return this.mContext.getAssets().open(path);
        } catch (IOException e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("missing database upgrade script: ");
            sb.append(path);
            Log.w(str, sb.toString());
            return null;
        }
    }

    private void getUpgradeFilePaths(int baseVersion, int start, int end, ArrayList<String> paths) {
        int b;
        int a;
        if (getUpgradeSQLStream(start, end) != null) {
            paths.add(String.format(this.mUpgradePathFormat, new Object[]{Integer.valueOf(start), Integer.valueOf(end)}));
            a = start - 1;
            b = start;
        } else {
            a = start - 1;
            b = end;
        }
        if (a >= baseVersion) {
            getUpgradeFilePaths(baseVersion, a, b, paths);
        }
    }
}
