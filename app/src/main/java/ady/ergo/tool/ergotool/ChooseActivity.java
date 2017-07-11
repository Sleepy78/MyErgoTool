package ady.ergo.tool.ergotool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseActivity extends AppCompatActivity {
    private Button btnCat1,btnCat2,btnCat3;
    private Button btnEditCat1,btnEditCat2,btnEditCat3;
    private DataOutput dataoutput;
    private DataCatUn datacatun;
    private DataCatDeux datacatdeux;
    private DataCatTrois datacattrois;
    private DataCategory datacategory;
    private TextView tvCat1,tvCat2,tvCat3;
    String filename = "ErgoTool.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        initObjects();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataoutput = DataOutput.getInstance();
        datacatun = DataCatUn.getInstance();
        datacatdeux = DataCatDeux.getInstance();
        datacattrois = DataCatTrois.getInstance();
        datacategory = DataCategory.getInstance();

        setStopColor();
        loadBtnText();
        loadTVCat();
        setEditBtnActivation(); //hide edit button if running
    }

    @Override
    public void onPause() {
        super.onPause();
        saveBtnText();
        savePreferences();
    }

    private void setEditBtnActivation() {
        if(dataoutput.isRunning()){
            btnEditCat1.setVisibility(View.GONE);
            btnEditCat2.setVisibility(View.GONE);
            btnEditCat3.setVisibility(View.GONE);
        }else{
            btnEditCat1.setVisibility(View.VISIBLE);
            btnEditCat2.setVisibility(View.VISIBLE);
            btnEditCat3.setVisibility(View.VISIBLE);
        }
    }

    public void onclickBtnCat1(View view) {
        Intent intent = new Intent(ChooseActivity.this, Cat1Activity.class);
        startActivity(intent);
    }

    public void onclickBtnCat2(View view) {
        Intent intent = new Intent(ChooseActivity.this, Cat2Activity.class);
        startActivity(intent);
    }

    public void onclickBtnCat3(View view) {
        Intent intent = new Intent(ChooseActivity.this, Cat3Activity.class);
        startActivity(intent);
    }

    public void onclickBtnStop(View view) {
        boolean isRunning = dataoutput.isRunning();
        if(isRunning) {

            try {
                dataoutput.writeFile(ChooseActivity.this);
            } catch (FileNotFoundException e) {
                Toast.makeText(ChooseActivity.this, "File not found!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(ChooseActivity.this, "Cannot write in file!", Toast.LENGTH_SHORT).show();
            }
            dataoutput.setIsRunning(false);
            dataoutput.setIsSentable(true);
            setStopColor();

            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Tu dois d'abord lancer l'analyse", Toast.LENGTH_SHORT).show();
        }
    }

    public void onclickBtnEditCat1(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Change name");
        final EditText input = new EditText(this);
        input.setText(datacategory.getTextCat1());
        b.setView(input);
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                datacategory.setTextCat1(input.getText().toString().replace(",","_"));
                loadBtnText();
            }
        });
        b.setNegativeButton("CANCEL", null);
        b.show();
    }

    public void onclickBtnEditCat2(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Change name");
        final EditText input = new EditText(this);
        input.setText(datacategory.getTextCat2());
        b.setView(input);
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                datacategory.setTextCat2(input.getText().toString().replace(",","_"));
                loadBtnText();
            }
        });
        b.setNegativeButton("CANCEL", null);
        b.show();
    }

    public void onclickBtnEditCat3(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Change name");
        final EditText input = new EditText(this);
        input.setText(datacategory.getTextCat3());
        b.setView(input);
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                datacategory.setTextCat3(input.getText().toString().replace(",","_"));
                loadBtnText();
            }
        });
        b.setNegativeButton("CANCEL", null);
        b.show();
    }

    private void setStopColor() {
        //Change stop color depending on isRunning state
        if(dataoutput.isRunning()){
            findViewById(R.id.btnStop).setBackgroundColor(Color.RED);
        }else{
            findViewById(R.id.btnStop).setBackgroundColor(Color.WHITE);
        }
    }


    /*private boolean writeFile(){
        //dataoutput.initFullFile(header);

        String outputLine = dataoutput.updateOutputLine();                  //update outputline one last time
        dataoutput.addFullFile(outputLine);             //and add to fullfile
        dataoutput.cleanOutputLine();

        String fulltext = dataoutput.getHeader() + "\n";

        ArrayList outFile = dataoutput.getFullFile();
        for (int i = 0; i < outFile.size(); i++) {
            fulltext += outFile.get(i);
            fulltext += "\n";
        }

        FileOutputStream fos = null;
        try {
            fos = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(fulltext.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            Toast.makeText(ChooseActivity.this, "File not found!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(ChooseActivity.this, "Cannot write in file!", Toast.LENGTH_SHORT).show();
        }

        return false;
    }*/

    private void initObjects() {
        btnCat1 = (Button)findViewById(R.id.btnCat1);
        btnCat2 = (Button)findViewById(R.id.btnCat2);
        btnCat3 = (Button)findViewById(R.id.btnCat3);
        btnEditCat1 = (Button)findViewById(R.id.btnEditCat1);
        btnEditCat2 = (Button)findViewById(R.id.btnEditCat2);
        btnEditCat3 = (Button)findViewById(R.id.btnEditCat3);
        tvCat1 = (TextView)findViewById(R.id.tvCat1);
        tvCat2 = (TextView)findViewById(R.id.tvCat2);
        tvCat3 = (TextView)findViewById(R.id.tvCat3);
    }

    private void saveBtnText() {
        //save btn state
        datacategory.setTextCat1(btnCat1.getText().toString());
        datacategory.setTextCat2(btnCat2.getText().toString());
        datacategory.setTextCat3(btnCat3.getText().toString());
    }
    private void loadBtnText(){
        btnCat1.setText(datacategory.getTextCat1());
        btnCat2.setText(datacategory.getTextCat2());
        btnCat3.setText(datacategory.getTextCat3());
    }

    private void loadTVCat(){
        tvCat1.setText(datacategory.getTextTVCat1(datacatun));
        tvCat2.setText(datacategory.getTextTVCat2(datacatdeux));
        tvCat3.setText(datacategory.getTextTVCat3(datacattrois));
    }

    private void savePreferences(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ChooseActivity.this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("NameCat1", datacategory.getTextCat1());
        editor.putString("NameCat2", datacategory.getTextCat2());
        editor.putString("NameCat3", datacategory.getTextCat3());

        editor.commit();
    }
}
