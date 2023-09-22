package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteDatabase;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import model.UniversityInfo;
import model.UniversityLines;

public class UniversityDBManager {

    public static final String IS_READED_EXTRA_SOUND_DATA = "is_readed_extra_sound_data";

    private static final String TAG = "UniversityDBManager";
    private static final String EXCEPTION = "exception";

    private UniversityDBHelper mDBHelper = null;
    private static UniversityDBManager instance = null;
    private Cursor cursor;
    String password="123456name";
     public UniversityDBManager(){

     }

    public static UniversityDBManager getInstance(Context context) {
        if (instance == null) {
            instance = new UniversityDBManager(context.getApplicationContext());
        }
        return instance;
    }

    private UniversityDBManager(Context context) {
        mDBHelper = UniversityDBHelper.getInstance(context);
        if (!PreferencesUtils.getBoolean(context, IS_READED_EXTRA_SOUND_DATA, false)) {
//            readExcelToDB(context);

        }
    }



    private void readExcelToDB(Context context) {
        try {
            InputStream is = context.getAssets().open("shandong.xls");
            Workbook book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            UniversityInfo info = null;
            for (int i = 1; i < Rows; ++i) {
                String name = (sheet.getCell(0, i)).getContents();
                String province = (sheet.getCell(1, i)).getContents();
                String city = (sheet.getCell(2, i)).getContents();
                String county = (sheet.getCell(3, i)).getContents();
                String address = (sheet.getCell(4, i)).getContents();
                String introduction = (sheet.getCell(5, i)).getContents();
                String is_985 = (sheet.getCell(6, i)).getContents();
                String is_211 = (sheet.getCell(7, i)).getContents();
                String soft_science_ranking = (sheet.getCell(8, i)).getContents();
                String school_type = (sheet.getCell(9, i)).getContents();
                String school_content = (sheet.getCell(10, i)).getContents();
                String specialty = (sheet.getCell(11, i)).getContents();
                String score_line2021 = (sheet.getCell(12, i)).getContents();
                String score_line2020 = (sheet.getCell(13, i)).getContents();
                String score_line2019 = (sheet.getCell(14, i)).getContents();

                info = new UniversityInfo(name, province,  city,  county,  address,  introduction,  is_985,  is_211,  soft_science_ranking,  school_type,  school_content,  specialty,  score_line2021,  score_line2020,  score_line2019);
                saveInfoToDataBase(info);
            }
            book.close();
            Log.d("zsj_excel","excel读取完成");
            PreferencesUtils.putBoolean(context, IS_READED_EXTRA_SOUND_DATA, true);
        } catch (Exception e) {
            Log.e(TAG, EXCEPTION, e);
            PreferencesUtils.putBoolean(context, IS_READED_EXTRA_SOUND_DATA, false);
        }
    }

    private void saveInfoToDataBase(UniversityInfo info) {
        if (mDBHelper == null) {
            return;
        }
        SQLiteDatabase db = mDBHelper.getWritableDatabase(password);
        try {
            ContentValues values = new ContentValues();
            values.put("name", info.getName());
            values.put("province", info.getProvince());
            values.put("city", info.getCity());
            values.put("county", info.getCounty());
            values.put("address", info.getAddress());
            values.put("introduction", info.getIntroduction());
            values.put("is_985", info.getIs_985());
            values.put("is_211", info.getIs_211());
            values.put("soft_science_ranking", info.getSoft_science_ranking());
            values.put("school_type", info.getSchool_type());
            values.put("school_content", info.getSchool_content());
            values.put("specialty", info.getSpecialty());
            values.put("score_line2021", info.getScore_line2021());
            values.put("score_line2020", info.getScore_line2020());
            values.put("score_line2019", info.getScore_line2019());
            db.insert(UniversityDBHelper.TABLE_UNIVERSITY_INFO, null, values);
        } catch (SQLiteException e) {
            Log.e(TAG, EXCEPTION, e);
        } catch (Exception e){
            Log.e(TAG, EXCEPTION, e);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


    public UniversityLines getUniversityLinesbyName(String Uname) {
        UniversityLines universityLines = null;
        if (mDBHelper == null) {
            return universityLines;
        }
        SQLiteDatabase db = mDBHelper.getReadableDatabase(password);

        if (db == null) {
            return universityLines;
        }
        cursor = db.rawQuery("select * from UniversityLines where 学校名称 = ?", new String[] {Uname});
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String wenke = cursor.getString((int) cursor.getColumnIndex("文科"));
                    String like = cursor.getString((int) cursor.getColumnIndex("理科"));
                    universityLines = new UniversityLines(Uname,wenke,like);
                } while (cursor.moveToNext());
            }
            else {
                universityLines = new UniversityLines(Uname,"{'文科': [[], [], []]}","{'文科': [[], [], []]}");

            }
        } catch (SQLiteException e) {
            Log.e(TAG, EXCEPTION, e);
        }  catch (Exception e){
            Log.e(TAG, EXCEPTION, e);
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
            if (db != null) {
                db.close();
            }
        }
        return universityLines;
    }



    public List<UniversityInfo> queryByName(String Uname) {
        List<UniversityInfo> list=new ArrayList<>();
        SQLiteDatabase db = mDBHelper.getReadableDatabase(password);
        Cursor cursor = db.query("UniversityInfo", null, "name like ?", new String[]{"%"+Uname+"%"}, null, null, null);

        while (cursor.moveToNext()){
            String name = cursor.getString((int) cursor.getColumnIndex("name"));
            String province = cursor.getString((int) cursor.getColumnIndex("province"));
            String city = cursor.getString((int) cursor.getColumnIndex("city"));
            String county = cursor.getString((int) cursor.getColumnIndex("county"));
            String address = cursor.getString((int) cursor.getColumnIndex("address"));
            String introduction = cursor.getString((int) cursor.getColumnIndex("introduction"));
            String is_985 = cursor.getString((int) cursor.getColumnIndex("is_985"));
            String is_211 = cursor.getString((int) cursor.getColumnIndex("is_211"));
            String soft_science_ranking1 = cursor.getString((int) cursor.getColumnIndex("soft_science_ranking"));
            String school_type = cursor.getString((int) cursor.getColumnIndex("school_type"));
            String school_content = cursor.getString((int) cursor.getColumnIndex("school_content"));
            String specialty = cursor.getString((int) cursor.getColumnIndex("specialty"));
            String score_line2021 = cursor.getString((int) cursor.getColumnIndex("score_line2021"));
            String score_line2020 = cursor.getString((int) cursor.getColumnIndex("score_line2020"));
            String score_line2019 = cursor.getString((int) cursor.getColumnIndex("score_line2019"));
            UniversityInfo user = new UniversityInfo(name, province,  city,  county,  address,  introduction,  is_985,  is_211,  soft_science_ranking1,  school_type,  school_content,  specialty,  score_line2021,  score_line2020,  score_line2019);
            list.add(user);
        }
        return list;
    }



    public List<UniversityInfo> queryPopularByCity(String Uname) {
        List<UniversityInfo> list=new ArrayList<>();
        SQLiteDatabase db = mDBHelper.getReadableDatabase(password);
        Cursor cursor=db.query("UniversityInfo",null,"city=?",new String[]{Uname},null,null,null,"1,6");
        while (cursor.moveToNext()){
            String name = cursor.getString((int) cursor.getColumnIndex("name"));
            String province = cursor.getString((int) cursor.getColumnIndex("province"));
            String city = cursor.getString((int) cursor.getColumnIndex("city"));
            String county = cursor.getString((int) cursor.getColumnIndex("county"));
            String address = cursor.getString((int) cursor.getColumnIndex("address"));
            String introduction = cursor.getString((int) cursor.getColumnIndex("introduction"));
            String is_985 = cursor.getString((int) cursor.getColumnIndex("is_985"));
            String is_211 = cursor.getString((int) cursor.getColumnIndex("is_211"));
            String soft_science_ranking1 = cursor.getString((int) cursor.getColumnIndex("soft_science_ranking"));
            String school_type = cursor.getString((int) cursor.getColumnIndex("school_type"));
            String school_content = cursor.getString((int) cursor.getColumnIndex("school_content"));
            String specialty = cursor.getString((int) cursor.getColumnIndex("specialty"));
            String score_line2021 = cursor.getString((int) cursor.getColumnIndex("score_line2021"));
            String score_line2020 = cursor.getString((int) cursor.getColumnIndex("score_line2020"));
            String score_line2019 = cursor.getString((int) cursor.getColumnIndex("score_line2019"));
            UniversityInfo user = new UniversityInfo(name, province,  city,  county,  address,  introduction,  is_985,  is_211,  soft_science_ranking1,  school_type,  school_content,  specialty,  score_line2021,  score_line2020,  score_line2019);
            list.add(user);
        }
        return list;
    }

    public List<UniversityInfo> queryByCity(String Uname) {
        List<UniversityInfo> list=new ArrayList<>();
        SQLiteDatabase db = mDBHelper.getReadableDatabase(password);
        Cursor cursor=db.query("UniversityInfo",null,"city=?",new String[]{Uname},null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString((int) cursor.getColumnIndex("name"));
            String province = cursor.getString((int) cursor.getColumnIndex("province"));
            String city = cursor.getString((int) cursor.getColumnIndex("city"));
            String county = cursor.getString((int) cursor.getColumnIndex("county"));
            String address = cursor.getString((int) cursor.getColumnIndex("address"));
            String introduction = cursor.getString((int) cursor.getColumnIndex("introduction"));
            String is_985 = cursor.getString((int) cursor.getColumnIndex("is_985"));
            String is_211 = cursor.getString((int) cursor.getColumnIndex("is_211"));
            String soft_science_ranking1 = cursor.getString((int) cursor.getColumnIndex("soft_science_ranking"));
            String school_type = cursor.getString((int) cursor.getColumnIndex("school_type"));
            String school_content = cursor.getString((int) cursor.getColumnIndex("school_content"));
            String specialty = cursor.getString((int) cursor.getColumnIndex("specialty"));
            String score_line2021 = cursor.getString((int) cursor.getColumnIndex("score_line2021"));
            String score_line2020 = cursor.getString((int) cursor.getColumnIndex("score_line2020"));
            String score_line2019 = cursor.getString((int) cursor.getColumnIndex("score_line2019"));
            UniversityInfo user = new UniversityInfo(name, province,  city,  county,  address,  introduction,  is_985,  is_211,  soft_science_ranking1,  school_type,  school_content,  specialty,  score_line2021,  score_line2020,  score_line2019);
            list.add(user);
        }
        return list;
    }



    public UniversityInfo getUniversityInfobyId(Integer contentStr) {
        UniversityInfo info = null;
        if (mDBHelper == null) {
            return info;
        }
        SQLiteDatabase db = mDBHelper.getReadableDatabase(password);

        if (db == null) {
            return info;
        }
        cursor = db.rawQuery("select * from UniversityInfo where _id = ?", new String[] { String.valueOf(contentStr) });
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String name = cursor.getString((int) cursor.getColumnIndex("name"));
                    String province = cursor.getString((int) cursor.getColumnIndex("province"));
                    String city = cursor.getString((int) cursor.getColumnIndex("city"));
                    String county = cursor.getString((int) cursor.getColumnIndex("county"));
                    String address = cursor.getString((int) cursor.getColumnIndex("address"));
                    String introduction = cursor.getString((int) cursor.getColumnIndex("introduction"));
                    String is_985 = cursor.getString((int) cursor.getColumnIndex("is_985"));
                    String is_211 = cursor.getString((int) cursor.getColumnIndex("is_211"));
                    String soft_science_ranking = cursor.getString((int) cursor.getColumnIndex("soft_science_ranking"));
                    String school_type = cursor.getString((int) cursor.getColumnIndex("school_type"));
                    String school_content = cursor.getString((int) cursor.getColumnIndex("school_content"));
                    String specialty = cursor.getString((int) cursor.getColumnIndex("specialty"));
                    String score_line2021 = cursor.getString((int) cursor.getColumnIndex("score_line2021"));
                    String score_line2020 = cursor.getString((int) cursor.getColumnIndex("score_line2020"));
                    String score_line2019 = cursor.getString((int) cursor.getColumnIndex("score_line2019"));
                    info = new UniversityInfo(name, province,  city,  county,  address,  introduction,  is_985,  is_211,  soft_science_ranking,  school_type,  school_content,  specialty,  score_line2021,  score_line2020,  score_line2019);
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            Log.e(TAG, EXCEPTION, e);
        }  catch (Exception e){
            Log.e(TAG, EXCEPTION, e);
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
            if (db != null) {
                db.close();
            }
        }
        return info;
    }


}

