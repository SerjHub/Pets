package com.example.catsanddogs.sdk;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtil {

    public static void writeToFile(Context context, String path, String data) {
        if (context == null) return;

        try {
            FileOutputStream fileOutputStream = context.openFileOutput(path, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (IOException e) {
            Log.e("FileException", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(Context context, String path) {
        String s="";
        try {

            FileInputStream fileIn = context.getApplicationContext().openFileInput(path);
            InputStreamReader reader = new InputStreamReader(fileIn);
            BufferedReader buffer = new BufferedReader(reader);

            StringBuilder builder = new StringBuilder();

            while(( s = buffer.readLine()) != null ) {
                builder.append(s);
                builder.append( '\n' );
            }
            reader.close();
            return builder.toString();

        } catch (Exception e) {
            Log.e("FileException", "File read failed: " + e.toString());
        }
        return s;
    }
}
