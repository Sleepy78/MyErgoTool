package ady.ergo.tool.ergotool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static ady.ergo.tool.ergotool.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private DataOutput dataoutput;
    private DataLieux datalieux;
    private DataCatDeux datacatdeux;
    private DataCatTrois datacattrois;
    private DataCategory datacategory;

    String filename = "ErgoTool.csv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(activity_main);
        DataLieux dlieux = new DataLieux();
        DataCatDeux dcatdeux = new DataCatDeux();
        DataCatTrois dcattrois = new DataCatTrois();
        DataCategory dcategory = new DataCategory();
        DataOutput doutput = new DataOutput();

        DataCategory.getInstance().initCategory();
        DataLieux.getInstance().initDataLieux();
        DataCatDeux.getInstance().initDataCatDeux();
        DataCatTrois.getInstance().initDataCatTrois();
        DataOutput.getInstance().initDataOutput();
        //Toast.makeText(getApplicationContext(), String.valueOf(dataoutput.getFullFile().isEmpty()), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();

        datalieux = DataLieux.getInstance();
        datacatdeux = DataCatDeux.getInstance();
        datacattrois = DataCatTrois.getInstance();
        datacategory = DataCategory.getInstance();
        dataoutput = DataOutput.getInstance();

        if(!dataoutput.isRunning()){
            findViewById(R.id.btnStart).setBackgroundColor(Color.GREEN);
        }else{
            findViewById(R.id.btnStart).setBackgroundColor(Color.WHITE);
        }
    }

    public void onClickBtnConf(View view) {
        Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
        startActivity(intent);
    }

    public void onClickDynamicTry(View view) {
        Intent intent = new Intent(MainActivity.this, DynamicTry.class);
        startActivity(intent);
    }

    public void onClickBtnStart(View view) {
        String header;

        boolean isRunning = dataoutput.isRunning();
        if(!isRunning && dataoutput.updateHeader()) {
            dataoutput.setInitTime(System.currentTimeMillis());
            header = dataoutput.getHeader();
            dataoutput.initFullFile(header);
            //Toast.makeText(getApplicationContext(), dataoutput.getFullFile().get(0).toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "C'est parti !", Toast.LENGTH_SHORT).show();
            dataoutput.setIsRunning(true);
            dataoutput.setIsSentable(false);
            Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
            startActivity(intent);
        }else {
            if(isRunning)
                Toast.makeText(getApplicationContext(), "Déjà lancé, aller dans CONFIGURATION", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Finir de configurer avant de commencer", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickBtnSend(View view) {
        boolean isSentable = dataoutput.isSentable();
        if(isSentable) {

            sendMail();

        }else{
            Toast.makeText(getApplicationContext(), "Rien à envoyer!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBtnClean(View view) {
        boolean isSentable = dataoutput.isSentable();
        if(isSentable) {
            dataoutput.clean();
            getApplicationContext().deleteFile(filename);
            dataoutput.setIsSentable(false);
        }else{
            Toast.makeText(getApplicationContext(), "Rien à nettoyer!", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendMail() {
        File internalFile = getApplicationContext().getFileStreamPath(filename);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
        emailIntent.setType("message/rfc822");
        String to[] = new String[] { ( (EditText) findViewById(R.id.emailAddress) ).getText().toString() };
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
        Uri path = Uri.fromFile(internalFile);
        //emailIntent.putExtra(Intent.EXTRA_STREAM, path);
        emailIntent.putExtra(Intent.EXTRA_TEXT, readFile());
// the mail subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ergotool output file");
        try {
            startActivity(Intent.createChooser(emailIntent, "Dear You, you will find attached the file you sent"));
            Toast.makeText(MainActivity.this, readFile(), Toast.LENGTH_SHORT).show();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public String readFile(){
        FileInputStream fis = null;
        try {
            fis = getApplicationContext().openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            //Toast.makeText(ChooseActivity.this, sb, Toast.LENGTH_SHORT).show();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

