package ady.ergo.tool.ergotool;

import android.content.Intent;
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

public class Cat1Activity extends AppCompatActivity {
    private ToggleButton btnElem1, btnElem2, btnElem3, btnElem4, btnElem5;
    private ToggleButton btnElem6, btnElem7, btnElem8, btnElem9, btnElem10;
    private ToggleButton btnElem11, btnElem12, btnElem13, btnElem14, btnElem15;
    private Button btnC1versConf,btnC1versC1,btnC1versC2,btnC1versC3;
    private EditText textElem1, textElem2, textElem3, textElem4, textElem5;
    private EditText textElem6, textElem7, textElem8, textElem9, textElem10;
    private EditText textElem11, textElem12, textElem13, textElem14, textElem15;
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

        btnElem1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });

        btnElem6.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem7.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem8.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem9.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem10.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });

        btnElem11.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem12.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem13.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem14.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnElem15.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });

    }

    @Override
    public void onPause() {
        super.onPause();

        saveBtnState();
        saveEditTextValue();

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
        setElementActivation(); //display element if it has to
    }
    private void onBtnAction() {
        saveBtnState();
        saveEditTextValue();

        dataoutput.updateOutputLine();
        String outputLine = dataoutput.getOutputLine();
        if (dataoutput.isRunning()){
            dataoutput.addFullFile(outputLine);
            Toast.makeText(getApplicationContext(), outputLine, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Not running yet", Toast.LENGTH_SHORT).show();
        }
        dataoutput.cleanOutputLine();
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

    private void initObjects() {
        btnElem1 = (ToggleButton)findViewById(R.id.btnElem1);
        btnElem2 = (ToggleButton)findViewById(R.id.btnElem2);
        btnElem3 = (ToggleButton)findViewById(R.id.btnElem3);
        btnElem4 = (ToggleButton)findViewById(R.id.btnElem4);
        btnElem5 = (ToggleButton)findViewById(R.id.btnElem5);
        textElem1 = (EditText)findViewById(R.id.etElem1);
        textElem2 = (EditText)findViewById(R.id.etElem2);
        textElem3 = (EditText)findViewById(R.id.etElem3);
        textElem4 = (EditText)findViewById(R.id.etElem4);
        textElem5 = (EditText)findViewById(R.id.etElem5);

        btnElem6 = (ToggleButton)findViewById(R.id.btnElem6);
        btnElem7 = (ToggleButton)findViewById(R.id.btnElem7);
        btnElem8 = (ToggleButton)findViewById(R.id.btnElem8);
        btnElem9 = (ToggleButton)findViewById(R.id.btnElem9);
        btnElem10 = (ToggleButton)findViewById(R.id.btnElem10);
        textElem6 = (EditText)findViewById(R.id.etElem6);
        textElem7 = (EditText)findViewById(R.id.etElem7);
        textElem8 = (EditText)findViewById(R.id.etElem8);
        textElem9 = (EditText)findViewById(R.id.etElem9);
        textElem10 = (EditText)findViewById(R.id.etElem10);

        btnElem11 = (ToggleButton)findViewById(R.id.btnElem11);
        btnElem12 = (ToggleButton)findViewById(R.id.btnElem12);
        btnElem13 = (ToggleButton)findViewById(R.id.btnElem13);
        btnElem14 = (ToggleButton)findViewById(R.id.btnElem14);
        btnElem15 = (ToggleButton)findViewById(R.id.btnElem15);
        textElem11 = (EditText)findViewById(R.id.etElem11);
        textElem12 = (EditText)findViewById(R.id.etElem12);
        textElem13 = (EditText)findViewById(R.id.etElem13);
        textElem14 = (EditText)findViewById(R.id.etElem14);
        textElem15 = (EditText)findViewById(R.id.etElem15);

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

    private void saveBtnState() {
        //save btn state
        datacatun.setBtnElem1(btnElem1.isChecked());
        datacatun.setBtnElem2(btnElem2.isChecked());
        datacatun.setBtnElem3(btnElem3.isChecked());
        datacatun.setBtnElem4(btnElem4.isChecked());
        datacatun.setBtnElem5(btnElem5.isChecked());
        datacatun.setBtnElem6(btnElem6.isChecked());
        datacatun.setBtnElem7(btnElem7.isChecked());
        datacatun.setBtnElem8(btnElem8.isChecked());
        datacatun.setBtnElem9(btnElem9.isChecked());
        datacatun.setBtnElem10(btnElem10.isChecked());
        datacatun.setBtnElem11(btnElem11.isChecked());
        datacatun.setBtnElem12(btnElem12.isChecked());
        datacatun.setBtnElem13(btnElem13.isChecked());
        datacatun.setBtnElem14(btnElem14.isChecked());
        datacatun.setBtnElem15(btnElem15.isChecked());
    }
    private void saveEditTextValue() {
        //Save text state
        datacatun.setTextElem1(textElem1.getText().toString());
        datacatun.setTextElem2(textElem2.getText().toString());
        datacatun.setTextElem3(textElem3.getText().toString());
        datacatun.setTextElem4(textElem4.getText().toString());
        datacatun.setTextElem5(textElem5.getText().toString());
        datacatun.setTextElem6(textElem6.getText().toString());
        datacatun.setTextElem7(textElem7.getText().toString());
        datacatun.setTextElem8(textElem8.getText().toString());
        datacatun.setTextElem9(textElem9.getText().toString());
        datacatun.setTextElem10(textElem10.getText().toString());
        datacatun.setTextElem11(textElem11.getText().toString());
        datacatun.setTextElem12(textElem12.getText().toString());
        datacatun.setTextElem13(textElem13.getText().toString());
        datacatun.setTextElem14(textElem14.getText().toString());
        datacatun.setTextElem15(textElem15.getText().toString());
    }
    private void loadState() {
        btnElem1.setChecked(datacatun.getBtnElem1());
        btnElem2.setChecked(datacatun.getBtnElem2());
        btnElem3.setChecked(datacatun.getBtnElem3());
        btnElem4.setChecked(datacatun.getBtnElem4());
        btnElem5.setChecked(datacatun.getBtnElem5());
        textElem1.setHint(datacatun.getHintElem1());
        textElem2.setHint(datacatun.getHintElem2());
        textElem3.setHint(datacatun.getHintElem3());
        textElem4.setHint(datacatun.getHintElem4());
        textElem5.setHint(datacatun.getHintElem5());
        textElem1.setText(datacatun.getTextElem1());
        textElem2.setText(datacatun.getTextElem2());
        textElem3.setText(datacatun.getTextElem3());
        textElem4.setText(datacatun.getTextElem4());
        textElem5.setText(datacatun.getTextElem5());

        btnElem6.setChecked(datacatun.getBtnElem6());
        btnElem7.setChecked(datacatun.getBtnElem7());
        btnElem8.setChecked(datacatun.getBtnElem8());
        btnElem9.setChecked(datacatun.getBtnElem9());
        btnElem10.setChecked(datacatun.getBtnElem10());
        textElem6.setHint(datacatun.getHintElem6());
        textElem7.setHint(datacatun.getHintElem7());
        textElem8.setHint(datacatun.getHintElem8());
        textElem9.setHint(datacatun.getHintElem9());
        textElem10.setHint(datacatun.getHintElem10());
        textElem6.setText(datacatun.getTextElem6());
        textElem7.setText(datacatun.getTextElem7());
        textElem8.setText(datacatun.getTextElem8());
        textElem9.setText(datacatun.getTextElem9());
        textElem10.setText(datacatun.getTextElem10());

        btnElem11.setChecked(datacatun.getBtnElem11());
        btnElem12.setChecked(datacatun.getBtnElem12());
        btnElem13.setChecked(datacatun.getBtnElem13());
        btnElem14.setChecked(datacatun.getBtnElem14());
        btnElem15.setChecked(datacatun.getBtnElem15());
        textElem11.setHint(datacatun.getHintElem11());
        textElem12.setHint(datacatun.getHintElem12());
        textElem13.setHint(datacatun.getHintElem13());
        textElem14.setHint(datacatun.getHintElem14());
        textElem15.setHint(datacatun.getHintElem15());
        textElem11.setText(datacatun.getTextElem11());
        textElem12.setText(datacatun.getTextElem12());
        textElem13.setText(datacatun.getTextElem13());
        textElem14.setText(datacatun.getTextElem14());
        textElem15.setText(datacatun.getTextElem15());

        titlecat1.setText(datacategory.getTextCat1());

        btnC1versC1.setText(datacategory.getTextCat1());
        btnC1versC2.setText(datacategory.getTextCat2());
        btnC1versC3.setText(datacategory.getTextCat3());

        if(dataoutput.isRunning()){
            disableEditTextModification(textElem1);
            disableEditTextModification(textElem2);
            disableEditTextModification(textElem3);
            disableEditTextModification(textElem4);
            disableEditTextModification(textElem5);
            disableEditTextModification(textElem6);
            disableEditTextModification(textElem7);
            disableEditTextModification(textElem8);
            disableEditTextModification(textElem9);
            disableEditTextModification(textElem10);
            disableEditTextModification(textElem11);
            disableEditTextModification(textElem12);
            disableEditTextModification(textElem13);
            disableEditTextModification(textElem14);
            disableEditTextModification(textElem15);
        }else{
            enableEditTextModification(textElem1);
            enableEditTextModification(textElem2);
            enableEditTextModification(textElem3);
            enableEditTextModification(textElem4);
            enableEditTextModification(textElem5);
            enableEditTextModification(textElem6);
            enableEditTextModification(textElem7);
            enableEditTextModification(textElem8);
            enableEditTextModification(textElem9);
            enableEditTextModification(textElem10);
            enableEditTextModification(textElem11);
            enableEditTextModification(textElem12);
            enableEditTextModification(textElem13);
            enableEditTextModification(textElem14);
            enableEditTextModification(textElem15);
        }
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

    private void setElementActivation() {
        if(dataoutput.isRunning()){
            setDisplay(textElem1, btnElem1);
            setDisplay(textElem2, btnElem2);
            setDisplay(textElem3, btnElem3);
            setDisplay(textElem4, btnElem4);
            setDisplay(textElem5, btnElem5);
            setDisplay(textElem6, btnElem6);
            setDisplay(textElem7, btnElem7);
            setDisplay(textElem8, btnElem8);
            setDisplay(textElem9, btnElem9);
            setDisplay(textElem10, btnElem10);
            setDisplay(textElem11, btnElem11);
            setDisplay(textElem12, btnElem12);
            setDisplay(textElem13, btnElem13);
            setDisplay(textElem14, btnElem14);
            setDisplay(textElem15, btnElem15);
        }else{
            forceVisible(textElem1, btnElem1);
            forceVisible(textElem2, btnElem2);
            forceVisible(textElem3, btnElem3);
            forceVisible(textElem4, btnElem4);
            forceVisible(textElem5, btnElem5);
            forceVisible(textElem6, btnElem6);
            forceVisible(textElem7, btnElem7);
            forceVisible(textElem8, btnElem8);
            forceVisible(textElem9, btnElem9);
            forceVisible(textElem10, btnElem10);
            forceVisible(textElem11, btnElem11);
            forceVisible(textElem12, btnElem12);
            forceVisible(textElem13, btnElem13);
            forceVisible(textElem14, btnElem14);
            forceVisible(textElem15, btnElem15);
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
}