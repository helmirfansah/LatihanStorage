package com.helmirfansah.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalStorageActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namafile.txt";
    Button buatFile, ubahFile, bacaFile, deleteFile;
    TextView textBaca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        buatFile = findViewById(R.id.buttonBuatFile);
        ubahFile = findViewById(R.id.buttonUbahFile);
        bacaFile = findViewById(R.id.buttonBacaFile);
        deleteFile = findViewById(R.id.buttonHapusFile);
        textBaca = findViewById(R.id.textBaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        deleteFile.setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button (Back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("External Storage");
        int color = ContextCompat.getColor(this, R.color.white);
        toolbar.setTitleTextColor(color);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // This will close the activity and go back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void buatFile() {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "externalfile.txt");
            if (file.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(file);
                String data = "Ini adalah data yang akan ditulis ke file.";
                fos.write(data.getBytes());
                fos.close();
                Toast.makeText(this, "File berhasil dibuat dan ditulis!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal membuat file atau file sudah ada!", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ubahFile(){
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "externalfile.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            fis.close();

            // Modifikasi data
            String dataSebelumnya = sb.toString();
            String dataBaru = dataSebelumnya + "Data baru yang ditambahkan.";

            // Tulis data baru ke file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(dataBaru.getBytes());
            fos.close();

            Toast.makeText(this, "Data berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void bacaFile(){
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "externalfile.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            fis.close();
            String data = sb.toString();
            Toast.makeText(this, "Data dari file: " + data, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void hapusFile(){
        String namaFile = "externalfile.txt";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), namaFile);
        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(this, "File berhasil dihapus!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal menghapus file.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File tidak ditemukan.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        jalankanPerintah(v.getId());
    }

    public void jalankanPerintah(int id) {
        if (id == R.id.buttonBuatFile) {
            buatFile();
        } else if (id == R.id.buttonBacaFile) {
            bacaFile();
        } else if (id == R.id.buttonUbahFile) {
            ubahFile();
        } else if (id == R.id.buttonHapusFile) {
            hapusFile();
        }
    }


}