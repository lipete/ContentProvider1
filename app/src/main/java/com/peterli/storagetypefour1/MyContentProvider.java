package com.peterli.storagetypefour1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

public class MyContentProvider extends ContentProvider {
    Context mContext;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public static final String AUTOHORITY = "com.peter.myprovider";
    // 设置ContentProvider的唯一标识

    public static final int Student_Code = 1;
    public static final int Teacher_Code = 2;
    // UriMatcher类使用:在ContentProvider 中注册URI
    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        mMatcher.addURI(AUTOHORITY, "Student", Student_Code);
        mMatcher.addURI(AUTOHORITY, "Teacher", Teacher_Code);
        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(@NonNull Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        String table = getTableName(uri);
        // 向该表添加数据
        sqLiteDatabase.insert(table, null, values);
        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.getContentResolver().notifyChange(uri, null);

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);
        return uri;
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        dbHelper = new DBHelper(getContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();

        sqLiteDatabase.execSQL("delete from Student");
        sqLiteDatabase.execSQL("insert into Student(name,age) values(\"小明\",17)");
        sqLiteDatabase.execSQL("insert into Student(name,age) values(\"小红\",18)");

        sqLiteDatabase.execSQL("delete from Teacher");
        sqLiteDatabase.execSQL("insert into Teacher(name,age) values(\"张老师\",54)");
        sqLiteDatabase.execSQL("insert into Teacher(name,age) values(\"王老师\",47)");
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        String table = getTableName(uri);

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        // 查询数据
        return sqLiteDatabase.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (mMatcher.match(uri)) {
            case Student_Code:
                tableName = "Student";
                break;
            case Teacher_Code:
                tableName = "Teacher";
                break;
        }
        return tableName;
    }
}

