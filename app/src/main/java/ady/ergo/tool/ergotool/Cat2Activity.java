package ady.ergo.tool.ergotool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class Cat2Activity extends AppCompatActivity {

    private Button btnC2versConf,btnC2versC1,btnC2versC2,btnC2versC3;

    private ArrayList<ToggleButton> listbtnElem;
    private ArrayList<EditText> listTextElem;

    private TextView titlecat2,tvActiveCat1Elem,tvActiveCat2Elem,tvActiveCat3Elem;

    private DataOutput dataoutput;
    private DataCatUn datacatun;
    private DataCatDeux datacatdeux;
    private DataCatTrois datacattrois;
    private DataCategory datacategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat2);
        initObjects();

        for(int i=1;i<=listbtnElem.size();i++){
            listbtnElem.get(i-1).setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {onBtnAction();}
            });
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        saveBtnState();
        saveEditTextValue();
        savePreferences();

    }

    @Override
    public void onResume() {
        super.onResume();
        dataoutput = DataOutput.getInstance();
        datacatun = DataCatUn.getInstance();
        datacatdeux = DataCatDeux.getInstance();
        datacattrois = DataCatTrois.getInstance();
        datacategory = DataCategory.getInstance();

        setPauseColor();
        loadState();
        loadTVActiveCat();
        //setElementActivation();
    }

    private void onBtnAction() {
        saveBtnState();
        saveEditTextValue();
        loadTVActiveCat();

        if (dataoutput.isRunning() && !dataoutput.isPaused()){
            dataoutput.updateOutputLine();
            String outputLine = dataoutput.getOutputLine();

            dataoutput.addFullFile(outputLine);
            dataoutput.cleanOutputLine();
            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Not running !", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBtnC2versConf(View view) {
        Intent intent = new Intent(Cat2Activity.this, ChooseActivity.class);
        startActivity(intent);
        finish();
    }
    public void onClickBtnC2versC1(View view) {
        Intent intent = new Intent(Cat2Activity.this, Cat1Activity.class);
        startActivity(intent);
        finish();
    }
    public void onClickBtnC2versC3(View view) {
        Intent intent = new Intent(Cat2Activity.this, Cat3Activity.class);
        startActivity(intent);
        finish();
    }

    public void onclickBtnPause(View view) {
        boolean isRunning = dataoutput.isRunning();
        boolean isPaused = dataoutput.isPaused();
        if(isRunning) {
            if(!isPaused) {
                dataoutput.setIsPaused(true);
                Toast.makeText(Cat2Activity.this, "Analysis paused!", Toast.LENGTH_SHORT).show();
            }else{
                dataoutput.setIsPaused(false);
                dataoutput.setInitTime(System.currentTimeMillis());
                dataoutput.updateOutputLine();
                String outputLine = dataoutput.getOutputLine();
                dataoutput.addFullFile(outputLine);
                dataoutput.cleanOutputLine();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                Toast.makeText(Cat2Activity.this, "Analysis restarted!", Toast.LENGTH_SHORT).show();
            }
            setPauseColor();
        }else{
            Toast.makeText(getApplicationContext(), "Analysis must be launched first.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setPauseColor() {
        if(dataoutput.isPaused()){
            findViewById(R.id.btnPause).setBackgroundColor(Color.GREEN);
            ( (Button) findViewById(R.id.btnPause) ).setText("PLAY");
        }else{
            findViewById(R.id.btnPause).setBackgroundColor(Color.RED);
            ( (Button) findViewById(R.id.btnPause) ).setText("PAUSE");
        }
        if(!dataoutput.isRunning()){
            findViewById(R.id.btnPause).setVisibility(View.GONE);
        }else{
            findViewById(R.id.btnPause).setVisibility(View.VISIBLE);
        }
    }

    private void saveBtnState() {
        //save btn state
        for(int i=1;i<=listbtnElem.size();i++){
            datacatdeux.setBtnElem(i,listbtnElem.get(i-1).isChecked());
        }
    }

    private void saveEditTextValue() {
        //Save text state
        for(int i=1;i<=listTextElem.size();i++){
            datacatdeux.setTextElem(i,listTextElem.get(i-1).getText().toString().replace(",","_"));
        }
    }

    private void loadState() {
        for(int i=1;i<=listbtnElem.size();i++){
            listbtnElem.get(i-1).setChecked(datacatdeux.getBtnElem(i));
        }

        for(int i=1;i<=listTextElem.size();i++){
            listTextElem.get(i-1).setText(datacatdeux.getTextElem(i));
            listTextElem.get(i-1).setHint(datacatdeux.getHintElem(i));
        }

        titlecat2.setText(datacategory.getTextCat2());

        btnC2versC1.setText(datacategory.getTextCat1());
        btnC2versC2.setText(datacategory.getTextCat2());
        btnC2versC3.setText(datacategory.getTextCat3());

    }

    private void loadTVActiveCat(){
        tvActiveCat1Elem.setText((datacategory.getTextTVCat1(datacatun)));
        tvActiveCat2Elem.setText((datacategory.getTextTVCat2(datacatdeux)));
        tvActiveCat3Elem.setText((datacategory.getTextTVCat3(datacattrois)));
    }

/*    private void enableEditTextModification(EditText editText){
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setFocusable(true);
    }
    private void disableEditTextModification(EditText editText){
        editText.setInputType(InputType.TYPE_NULL);
        editText.setFocusable(false);
    }

    public void setElementActivation() {
        if(dataoutput.isRunning()){
            for(int i=1;i<=listTextElem.size();i++){
                setDisplay(listTextElem.get(i-1), listbtnElem.get(i-1));
            }
        }else{
            for(int i=1;i<=listTextElem.size();i++){
                forceVisible(listTextElem.get(i-1), listbtnElem.get(i-1));
            }
        }
    }

    private void setDisplay(EditText et, ToggleButton tbtn){
        if("".equals(et.getText().toString())){
            et.setVisibility(View.GONE);
            tbtn.setVisibility(View.GONE);
        }
    }

    private void forceVisible(EditText et, ToggleButton tbtn){
        et.setVisibility(View.VISIBLE);
        tbtn.setVisibility(View.VISIBLE);
    }*/

    private void savePreferences(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Cat2Activity.this);
        SharedPreferences.Editor editor = sharedPref.edit();
        for(int i=1 ; i<=15 ; i++){
            editor.putBoolean("Cat2Elem" + i + "TBTN", datacatdeux.getBtnElem(i));
            editor.putString("Cat2Elem" + i + "TextElem", datacatdeux.getTextElem(i));
        }
        editor.putString("AnalysisMemory", dataoutput.serialize(dataoutput.getFullFile()));
        editor.putBoolean("isPaused", dataoutput.isPaused());

        editor.commit();
    }

    private void initObjects() {
        listbtnElem = new ArrayList<ToggleButton>();
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem1) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem2) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem3) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem4) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem5) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem6) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem7) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem8) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem9) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem10) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem11) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem12) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem13) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem14) );
        listbtnElem.add( (ToggleButton)findViewById(R.id.btnElem15) );

        listTextElem = new ArrayList<EditText>();
        listTextElem.add( (EditText)findViewById(R.id.etElem1) );
        listTextElem.add( (EditText)findViewById(R.id.etElem2) );
        listTextElem.add( (EditText)findViewById(R.id.etElem3) );
        listTextElem.add( (EditText)findViewById(R.id.etElem4) );
        listTextElem.add( (EditText)findViewById(R.id.etElem5) );
        listTextElem.add( (EditText)findViewById(R.id.etElem6) );
        listTextElem.add( (EditText)findViewById(R.id.etElem7) );
        listTextElem.add( (EditText)findViewById(R.id.etElem8) );
        listTextElem.add( (EditText)findViewById(R.id.etElem9) );
        listTextElem.add( (EditText)findViewById(R.id.etElem10) );
        listTextElem.add( (EditText)findViewById(R.id.etElem11) );
        listTextElem.add( (EditText)findViewById(R.id.etElem12) );
        listTextElem.add( (EditText)findViewById(R.id.etElem13) );
        listTextElem.add( (EditText)findViewById(R.id.etElem14) );
        listTextElem.add( (EditText)findViewById(R.id.etElem15) );

        for(int i=1;i<=listTextElem.size();i++){
            listTextElem.get(i-1).setSelectAllOnFocus(true);
        }

        titlecat2 = (TextView)findViewById(R.id.titleCat2);

        btnC2versConf = (Button)findViewById(R.id.btnC2versConf);
        btnC2versC1 = (Button)findViewById(R.id.btnC2versC1);
        btnC2versC2 = (Button)findViewById(R.id.btnC2versC2);
        btnC2versC3 = (Button)findViewById(R.id.btnC2versC3);

        tvActiveCat1Elem = (TextView)findViewById(R.id.tvActiveCat1Elem);
        tvActiveCat2Elem = (TextView)findViewById(R.id.tvActiveCat2Elem);
        tvActiveCat3Elem = (TextView)findViewById(R.id.tvActiveCat3Elem);
    }
}
