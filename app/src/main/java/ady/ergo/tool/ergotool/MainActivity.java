package ady.ergo.tool.ergotool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
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

        datacatun = DataCatUn.getInstance();
        datacatdeux = DataCatDeux.getInstance();
        datacattrois = DataCatTrois.getInstance();
        datacategory = DataCategory.getInstance();
        dataoutput = DataOutput.getInstance();

        loadPreferences();

    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();

    }

    @Override
    public void onResume() {
        super.onResume();

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

    public void onClickBtnStart(View view) {
        boolean isSentable = dataoutput.isSentable();
        final boolean isRunning  = dataoutput.isRunning();
        if(isSentable) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setMessage("Current activity will be lost! Are you sure you want to continue ?");
            b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    cleanFile();
                    if(!isRunning) {
                        launchAnalysis();
                    }else {
                        Toast.makeText(getApplicationContext(), "Déjà lancé, aller dans CONFIGURATION", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            b.setNegativeButton("CANCEL", null);
            b.show();
        }else{
            if(!isRunning) {
                launchAnalysis();
            }else {
                Toast.makeText(getApplicationContext(), "Déjà lancé, aller dans CONFIGURATION", Toast.LENGTH_SHORT).show();
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
            b.setMessage("All current logged analysis will be lost! Are you sure you want to clean? ");
            b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    cleanFile();
                }
            });
            b.setNegativeButton("CANCEL", null);
            b.show();
        }
        cleanElements();
        Toast.makeText(getApplicationContext(), "Cleaning OK !", Toast.LENGTH_SHORT).show();
    }

    private void savePreferences(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isSentable", dataoutput.isSentable());
        editor.putBoolean("isRunning", dataoutput.isRunning());
        editor.putString("emailAddress", ( (EditText) findViewById(R.id.emailAddress) ).getText().toString().replace(",","_"));

        editor.putString("NameCat1", datacategory.getTextCat1());
        editor.putString("NameCat2", datacategory.getTextCat2());
        editor.putString("NameCat3", datacategory.getTextCat3());

        editor.putLong("InitTime", dataoutput.getInitTime());
        //editor.putString("AnalysisMemory", serialize(dataoutput.getFullFile()));   //tocheck

        editor.commit();
    }

    private void loadPreferences(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        if(sharedPref.contains("isSentable")) {
            dataoutput.setIsSentable(sharedPref.getBoolean("isSentable", false));
        }
        if(sharedPref.contains("isRunning")) {
            dataoutput.setIsRunning(sharedPref.getBoolean("isRunning", false));
        }
        if(sharedPref.contains("isPaused")) {
            dataoutput.setIsPaused(sharedPref.getBoolean("isPaused", false));
        }
        if(sharedPref.contains("emailAddress")) {
            ((EditText) findViewById(R.id.emailAddress)).setText(sharedPref.getString("emailAddress", ""));
        }
        if(sharedPref.contains("NameCat1")) {
            datacategory.setTextCat1(sharedPref.getString("NameCat1", ""));
        }
        if(sharedPref.contains("NameCat2")) {
            datacategory.setTextCat2(sharedPref.getString("NameCat2", ""));
        }
        if(sharedPref.contains("NameCat3")) {
            datacategory.setTextCat3(sharedPref.getString("NameCat3", ""));
        }
        if(sharedPref.contains("InitTime")) {
            dataoutput.setInitTime(sharedPref.getLong("InitTime", System.currentTimeMillis()));
        }
        if(sharedPref.contains("delay")) {
            dataoutput.setDelay(sharedPref.getLong("delay", 0));
        }
        for(int i=1 ; i<=15 ; i++){
            if(sharedPref.contains("Cat1Elem" + i + "TBTN")) {
                datacatun.setBtnElem(i,sharedPref.getBoolean("Cat1Elem" + i + "TBTN", false));
            }
            if(sharedPref.contains("Cat1Elem" + i + "TextElem")) {
                datacatun.setTextElem(i,sharedPref.getString("Cat1Elem" + i + "TextElem", ""));
            }
        }
        for(int i=1 ; i<=15 ; i++){
            if(sharedPref.contains("Cat2Elem" + i + "TBTN")) {
                datacatdeux.setBtnElem(i,sharedPref.getBoolean("Cat2Elem" + i + "TBTN", false));
            }
            if(sharedPref.contains("Cat2Elem" + i + "TextElem")) {
                datacatdeux.setTextElem(i,sharedPref.getString("Cat2Elem" + i + "TextElem", ""));
            }
        }
        for(int i=1 ; i<=15 ; i++){
            if(sharedPref.contains("Cat3Elem" + i + "TBTN")) {
                datacattrois.setBtnElem(i,sharedPref.getBoolean("Cat3Elem" + i + "TBTN", false));
            }
            if(sharedPref.contains("Cat3Elem" + i + "TextElem")) {
                datacattrois.setTextElem(i,sharedPref.getString("Cat3Elem" + i + "TextElem", ""));
            }
        }

        if(sharedPref.contains("AnalysisMemory")) {
            dataoutput.setFullFile(dataoutput.deSerialize(sharedPref.getString("AnalysisMemory","")));  //tocheck
        }
    }

    private void launchAnalysis(){
        dataoutput.setInitTime(System.currentTimeMillis());

        dataoutput.setIsRunning(true);
        dataoutput.setIsPaused(false);
        dataoutput.setIsSentable(false);
        Toast.makeText(getApplicationContext(), "C'est parti !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
        startActivity(intent);
    }

    private void cleanFile(){
        dataoutput.clean();
        getApplicationContext().deleteFile(filename);
        dataoutput.setIsSentable(false);
    }

    private void cleanElements(){
        datacatun.cleanDataCatUn();
        datacatdeux.cleanDataCatDeux();
        datacattrois.cleanDataCatTrois();
    }
    
    public void sendMail() {
        //File internalFile = getApplicationContext().getFileStreamPath(filename);

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

