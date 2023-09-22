package com.example.shopping.utils;

import android.graphics.Bitmap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class FileUtils {
    public static void saveText(String path,String txt){
        BufferedWriter os=null;
        try {
            os=new BufferedWriter(new FileWriter(path));
            os.write(txt);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (os!=null){
                try {
                    os.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static  String opentxt(String path){
        BufferedReader is=null;
        StringBuilder sb=new StringBuilder();

        try {
            is=new BufferedReader(new FileReader(path));
            String line=null;
            while ((line=is.readLine())!=null){
                sb.append(line);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static void saveImage(String path, Bitmap bitmap){
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
