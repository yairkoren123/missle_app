package com.example.searchonmaps.DATA;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.searchonmaps.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Enter_data_text extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";

    EditText mEditText;


    public void save(String lac, LatLng latLng) {
        String text = lac;
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {



        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            Log.d("TAG", "load: FILE_NAME :" + FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text1;

            while ((text1 = br.readLine()) != null) {
                sb.append(text1).append("\n");
            }
            String wo = sb.toString();
            Log.d("pp", "load: "+ wo);
            String[] aar  = wo.split(" ");
            for (int i = 0; i < aar.length; i++) {
                Log.d("TAG3", "load: "+ aar[i]);
                String[] bbr = aar[i].split(",");
                for (int j = 0; j < bbr.length; j++) {
                    Log.d("TAG4", "load: "+ bbr[0]);

                }
            }
            Log.d("wow", "loa1d: " + aar[1]  + " sd " + aar.length);

            for (int i = 0; i < aar.length; i++) {
                Log.d("wow", "load: " + aar[1]);
            }
            Log.d("bees", "load: " + sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}