package ady.ergo.tool.ergotool;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Cat3Activity extends AppCompatActivity {

    private Button btnC3versConf,btnC3versC1,btnC3versC2,btnC3versC3;

    private ArrayList<ToggleButton> listbtnElem;
    private ArrayList<EditText> listTextElem;

    private TextView titlecat3,tvActiveCat1Elem,tvActiveCat2Elem,tvActiveCat3Elem;

    private DataOutput dataoutput;
    private DataCatUn datacatun;
    private DataCatDeux datacatdeux;
    private DataCatTrois datacattrois;
    private DataCategory datacategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat3);
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

    public void onClickBtnC3versConf(View view) {
        Intent intent = new Intent(Cat3Activity.this, ChooseActivity.class);
        startActivity(intent);
        finish();
    }
    public void onClickBtnC3versC1(View view) {
        Intent intent = new Intent(Cat3Activity.this, Cat1Activity.class);
        startActivity(intent);
        finish();
    }
    public void onClickBtnC3versC2(View view) {
        Intent intent = new Intent(Cat3Activity.this, Cat2Activity.class);
        startActivity(intent);
        finish();
    }

    private void saveBtnState() {
        //save btn state
        for(int i=1;i<=listbtnElem.size();i++){
            datacattrois.setBtnElem(i,listbtnElem.get(i-1).isChecked());
        }
    }

    private void saveEditTextValue() {
        //Save text state
        for(int i=1;i<=listTextElem.size();i++){
            datacattrois.setTextElem(i,listTextElem.get(i-1).getText().toString().replace(",","_"));
        }
    }

    private void loadState() {
        for(int i=1;i<=listbtnElem.size();i++){
            listbtnElem.get(i-1).setChecked(datacattrois.getBtnElem(i));
        }

        for(int i=1;i<=listTextElem.size();i++){
            listTextElem.get(i-1).setText(datacattrois.getTextElem(i));
            listTextElem.get(i-1).setHint(datacattrois.getHintElem(i));
        }

        titlecat3.setText(datacategory.getTextCat3());

        btnC3versC1.setText(datacategory.getTextCat1());
        btnC3versC2.setText(datacategory.getTextCat2());
        btnC3versC3.setText(datacategory.getTextCat3());

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
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Cat3Activity.this);
        SharedPreferences.Editor editor = sharedPref.edit();
        for(int i=1 ; i<=15 ; i++){
            editor.putBoolean("Cat3Elem" + i + "TBTN", datacattrois.getBtnElem(i));
            editor.putString("Cat3Elem" + i + "TextElem", datacattrois.getTextElem(i));
        }
        editor.putString("AnalysisMemory", dataoutput.serialize(dataoutput.getFullFile()));   //tocheck
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

        titlecat3 = (TextView)findViewById(R.id.titleCat3);

        btnC3versConf = (Button)findViewById(R.id.btnC3versConf);
        btnC3versC1 = (Button)findViewById(R.id.btnC3versC1);
        btnC3versC2 = (Button)findViewById(R.id.btnC3versC2);
        btnC3versC3 = (Button)findViewById(R.id.btnC3versC3);

        tvActiveCat1Elem = (TextView)findViewById(R.id.tvActiveCat1Elem);
        tvActiveCat2Elem = (TextView)findViewById(R.id.tvActiveCat2Elem);
        tvActiveCat3Elem = (TextView)findViewById(R.id.tvActiveCat3Elem);
    }
}
