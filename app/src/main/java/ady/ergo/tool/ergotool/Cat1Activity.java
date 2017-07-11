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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class Cat1Activity extends AppCompatActivity {

    private Button btnC1versConf,btnC1versC1,btnC1versC2,btnC1versC3;

    private ArrayList<ToggleButton> listbtnElem;
    private ArrayList<EditText> listTextElem;

    private TextView titlecat1,tvActiveCat1Elem,tvActiveCat2Elem,tvActiveCat3Elem;

    private DataOutput dataoutput;
    private DataCatUn datacatun;
    private DataCatDeux datacatdeux;
    private DataCatTrois datacattrois;
    private DataCategory datacategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat1);
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
        finish();
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
        //setElementActivation(); //display element if it has to
    }
    private void onBtnAction() {
        saveBtnState();
        saveEditTextValue();
        loadTVActiveCat();

        if (dataoutput.isRunning()){
            dataoutput.updateOutputLine();
            String outputLine = dataoutput.getOutputLine();

            dataoutput.addFullFile(outputLine);
            dataoutput.cleanOutputLine();
            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Not running yet!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBtnC1versConf(View view) {
        Intent intent = new Intent(Cat1Activity.this, ChooseActivity.class);
        startActivity(intent);
    }
    public void onClickBtnC1versC2(View view) {
        Intent intent = new Intent(Cat1Activity.this, Cat2Activity.class);
        startActivity(intent);
    }
    public void onClickBtnC1versC3(View view) {
        Intent intent = new Intent(Cat1Activity.this, Cat3Activity.class);
        startActivity(intent);
    }

    private void saveBtnState() {
        //save btn state
        for(int i=1;i<=listbtnElem.size();i++){
            datacatun.setBtnElem(i,listbtnElem.get(i-1).isChecked());
        }
    }

    private void saveEditTextValue() {
        //Save text state
        for(int i=1;i<=listTextElem.size();i++){
            datacatun.setTextElem(i,listTextElem.get(i-1).getText().toString().replace(",","_"));
        }
    }

    private void loadState() {
        for(int i=1;i<=listbtnElem.size();i++){
            listbtnElem.get(i-1).setChecked(datacatun.getBtnElem(i));
        }

        for(int i=1;i<=listTextElem.size();i++){
            listTextElem.get(i-1).setText(datacatun.getTextElem(i));
            listTextElem.get(i-1).setHint(datacatun.getHintElem(i));
        }

        titlecat1.setText(datacategory.getTextCat1());

        btnC1versC1.setText(datacategory.getTextCat1());
        btnC1versC2.setText(datacategory.getTextCat2());
        btnC1versC3.setText(datacategory.getTextCat3());

    }

    private void loadTVActiveCat(){
        tvActiveCat1Elem.setText((datacategory.getTextTVCat1(datacatun)));
        tvActiveCat2Elem.setText((datacategory.getTextTVCat2(datacatdeux)));
        tvActiveCat3Elem.setText((datacategory.getTextTVCat3(datacattrois)));
    }

    private void enableEditTextModification(EditText editText){
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
    }

    private void savePreferences(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Cat1Activity.this);
        SharedPreferences.Editor editor = sharedPref.edit();
        for(int i=1 ; i<=15 ;i++){
            editor.putBoolean("Cat1Elem" + i + "TBTN", datacatun.getBtnElem(i));
            editor.putString("Cat1Elem" + i + "TextElem", datacatun.getTextElem(i));
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

        titlecat1 = (TextView)findViewById(R.id.titleCat1);

        btnC1versConf = (Button)findViewById(R.id.btnC1versConf);
        btnC1versC1 = (Button)findViewById(R.id.btnC1versC1);
        btnC1versC2 = (Button)findViewById(R.id.btnC1versC2);
        btnC1versC3 = (Button)findViewById(R.id.btnC1versC3);

        tvActiveCat1Elem = (TextView)findViewById(R.id.tvActiveCat1Elem);
        tvActiveCat2Elem = (TextView)findViewById(R.id.tvActiveCat2Elem);
        tvActiveCat3Elem = (TextView)findViewById(R.id.tvActiveCat3Elem);


        //test to remove
        /*LinearLayout hll;
        LinearLayout ll=(LinearLayout)findViewById(R.id.linearLayoutCat1);
        LinearLayout llElem1=(LinearLayout)findViewById(R.id.llElem1);
        hll = new LinearLayout(this);
        hll.setLayoutParams(llElem1.getLayoutParams());

        EditText edittext = new EditText(this);
        edittext.setLayoutParams(textElem15.getLayoutParams());
        edittext.setId(textElem15.getId()+1);
        ToggleButton tbtn = new ToggleButton(this);
        tbtn.setLayoutParams(btnElem15.getLayoutParams());
        tbtn.setId(btnElem15.getId()+1);

        hll.addView(edittext);
        hll.addView(tbtn);

        ll.addView(hll);*/

    }
}
