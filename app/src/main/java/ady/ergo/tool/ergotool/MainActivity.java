package ady.ergo.tool.ergotool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static ady.ergo.tool.ergotool.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private DataOutput dataoutput;
    private DataCatUn datacatun;
    private DataCatDeux datacatdeux;
    private DataCatTrois datacattrois;
    private DataCategory datacategory;

    String filename = "ErgoTool.csv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(activity_main);
        DataCatUn dcatun = new DataCatUn();
        DataCatDeux dcatdeux = new DataCatDeux();
        DataCatTrois dcattrois = new DataCatTrois();
        DataCategory dcategory = new DataCategory();
        DataOutput doutput = new DataOutput();

        DataCategory.getInstance().initCategory();
        DataCatUn.getInstance().initDataCatUn();
        DataCatDeux.getInstance().initDataCatDeux();
        DataCatTrois.getInstance().initDataCatTrois();
        DataOutput.getInstance().initDataOutput();

    }

    @Override
    public void onPause() {
        super.onPause();
        /*SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isSentable", dataoutput.isSentable());
        editor.commit();*/
    }

    @Override
    public void onResume() {
        super.onResume();

        datacatun = DataCatUn.getInstance();
        datacatdeux = DataCatDeux.getInstance();
        datacattrois = DataCatTrois.getInstance();
        datacategory = DataCategory.getInstance();
        dataoutput = DataOutput.getInstance();

        if(!dataoutput.isRunning()){
            findViewById(R.id.btnStart).setBackgroundColor(Color.GREEN);
        }else{
            findViewById(R.id.btnStart).setBackgroundColor(Color.WHITE);
        }

        /*if(!dataoutput.isSentable()) {
            SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
            dataoutput.setIsSentable(sharedPref.getBoolean("isSentable", false));
        }*/
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
        boolean isSentable = dataoutput.isSentable();
        final boolean isRunning  = dataoutput.isRunning();
        if(isSentable) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Current activity will be lost! Are you sure you want to continue ?");
            b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    cleanFile();
                    if(!isRunning && dataoutput.updateHeader()) {
                        launchAnalysis();
                    }else {
                        if(isRunning)
                            Toast.makeText(getApplicationContext(), "Déjà lancé, aller dans CONFIGURATION", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "Finir de configurer avant de commencer", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            b.setNegativeButton("CANCEL", null);
            b.show();
        }else{
            if(!isRunning && dataoutput.updateHeader()) {
                launchAnalysis();
            }else {
                if(isRunning)
                    Toast.makeText(getApplicationContext(), "Déjà lancé, aller dans CONFIGURATION", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Finir de configurer avant de commencer", Toast.LENGTH_SHORT).show();
            }
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
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("All current logged analysis will be lost! Are you sure you want to clean? ");
            b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    cleanFile();
                }
            });
            b.setNegativeButton("CANCEL", null);
            b.show();
        }else{
            Toast.makeText(getApplicationContext(), "Rien à nettoyer!", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchAnalysis(){
        dataoutput.setInitTime(System.currentTimeMillis());
        String header = dataoutput.getHeader();
        dataoutput.initFullFile(header);
        //Toast.makeText(getApplicationContext(), dataoutput.getFullFile().get(0).toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "C'est parti !", Toast.LENGTH_SHORT).show();
        dataoutput.setIsRunning(true);
        dataoutput.setIsSentable(false);
        Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
        startActivity(intent);
    }

    private void cleanFile(){
        dataoutput.clean();
        getApplicationContext().deleteFile(filename);
        dataoutput.setIsSentable(false);
        Toast.makeText(getApplicationContext(), "Cleaning OK", Toast.LENGTH_SHORT).show();
    }

    public void sendMail() {
        File internalFile = getApplicationContext().getFileStreamPath(filename);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
        emailIntent.setType("message/rfc822");
        String to[] = new String[] { ( (EditText) findViewById(R.id.emailAddress) ).getText().toString() };
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
        //Uri path = Uri.fromFile(internalFile);
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
            Toast.makeText(MainActivity.this, "File not found !", Toast.LENGTH_SHORT).show();
            return "File not found!";
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

