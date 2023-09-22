package utils;

import android.content.Context;


import android.os.Environment;
import android.util.Log;
import net.sqlcipher.database.SQLiteDatabase.CursorFactory;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteDatabase;


/**
 * 配置数据库操作帮助类
 *
 * */
public class UniversityDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_UNIVERSITY_INFO = "UniversityInfo";
    public static final String TABLE_UNIVERSITY_LINES = "UniversityLines";
    public static String DATA_BASE_NAME = "UniversityInfo.db";
    private static final int VERSION = 1;
    private static UniversityDBHelper instance;
    private SQLiteDatabase mRDE=null;


    private static String PACKAGE_NAME = "com.wh.university"; //包名
    private static String DB_PATH =  "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME + "/databases/";






    public static UniversityDBHelper getInstance( Context context ) {
        if (instance == null) {
            net.sqlcipher.database.SQLiteDatabase.loadLibs(context);
            instance = new UniversityDBHelper( context );
        }
        return instance;
    }

    public UniversityDBHelper(Context context){
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUser(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateTableUser(db, oldVersion, newVersion);
    }



    public void createTableUser(SQLiteDatabase db){
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EXTRA_VOICE_INFO + " (_id INTEGER PRIMARY KEY AUTOINCREMENT"
//                + ",name TEXT NOT NULL, province TEXT ,city TEXT, county TEXT ," +
//                "address TEXT , introduction TEXT, is_985 TEXT, is_211 TEXT, soft_science_ranking TEXT, school_type TEXT, school_content TEXT, specialty TEXT, score_line2021 TEXT, score_line2020 TEXT, score_line2019 TEXT)" );
    }


    public void updateTableUser(SQLiteDatabase db, int oldVersion, int newVersion){
        if ( oldVersion != newVersion ){
//            db.execSQL( "DROP TABLE IF EXISTS " + TABLE_UNIVERSITY_INFO );
//            db.execSQL( "DROP TABLE IF EXISTS " + TABLE_UNIVERSITY_LINES );
            createTableUser(db);
        }
    }


}
