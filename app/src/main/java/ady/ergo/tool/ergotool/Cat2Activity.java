package ady.ergo.tool.ergotool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Cat2Activity extends AppCompatActivity {
    private ToggleButton btnElem1,btnElem2,btnElem3,btnElem4,btnElem5;
    private ToggleButton btnElem6, btnElem7, btnElem8, btnElem9, btnElem10;
    private ToggleButton btnElem11, btnElem12, btnElem13, btnElem14, btnElem15;
    private Button btnC2versConf,btnC2versC1,btnC2versC2,btnC2versC3;
    private EditText textElem1,textElem2,textElem3,textElem4,textElem5;
    private EditText textElem6, textElem7, textElem8, textElem9, textElem10;
    private EditText textElem11, textElem12, textElem13, textElem14, textElem15;
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
        //setElementActivation();
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

    public void onClickBtnC2versConf(View view) {
        Intent intent = new Intent(Cat2Activity.this, ChooseActivity.class);
        startActivity(intent);
    }
    public void onClickBtnC2versC1(View view) {
        Intent intent = new Intent(Cat2Activity.this, Cat1Activity.class);
        startActivity(intent);
    }
    public void onClickBtnC2versC3(View view) {
        Intent intent = new Intent(Cat2Activity.this, Cat3Activity.class);
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

        titlecat2 = (TextView)findViewById(R.id.titleCat2);

        btnC2versConf = (Button)findViewById(R.id.btnC2versConf);
        btnC2versC1 = (Button)findViewById(R.id.btnC2versC1);
        btnC2versC2 = (Button)findViewById(R.id.btnC2versC2);
        btnC2versC3 = (Button)findViewById(R.id.btnC2versC3);

        tvActiveCat1Elem = (TextView)findViewById(R.id.tvActiveCat1Elem);
        tvActiveCat2Elem = (TextView)findViewById(R.id.tvActiveCat2Elem);
        tvActiveCat3Elem = (TextView)findViewById(R.id.tvActiveCat3Elem);
    }

    private void saveBtnState() {
        //save btn state
        datacatdeux.setBtnElem1(btnElem1.isChecked());
        datacatdeux.setBtnElem2(btnElem2.isChecked());
        datacatdeux.setBtnElem3(btnElem3.isChecked());
        datacatdeux.setBtnElem4(btnElem4.isChecked());
        datacatdeux.setBtnElem5(btnElem5.isChecked());
        datacatdeux.setBtnElem6(btnElem6.isChecked());
        datacatdeux.setBtnElem7(btnElem7.isChecked());
        datacatdeux.setBtnElem8(btnElem8.isChecked());
        datacatdeux.setBtnElem9(btnElem9.isChecked());
        datacatdeux.setBtnElem10(btnElem10.isChecked());
        datacatdeux.setBtnElem11(btnElem11.isChecked());
        datacatdeux.setBtnElem12(btnElem12.isChecked());
        datacatdeux.setBtnElem13(btnElem13.isChecked());
        datacatdeux.setBtnElem14(btnElem14.isChecked());
        datacatdeux.setBtnElem15(btnElem15.isChecked());
    }
    private void saveEditTextValue() {
        //Save text state
        datacatdeux.setTextElem1(textElem1.getText().toString());
        datacatdeux.setTextElem2(textElem2.getText().toString());
        datacatdeux.setTextElem3(textElem3.getText().toString());
        datacatdeux.setTextElem4(textElem4.getText().toString());
        datacatdeux.setTextElem5(textElem5.getText().toString());
        datacatdeux.setTextElem6(textElem6.getText().toString());
        datacatdeux.setTextElem7(textElem7.getText().toString());
        datacatdeux.setTextElem8(textElem8.getText().toString());
        datacatdeux.setTextElem9(textElem9.getText().toString());
        datacatdeux.setTextElem10(textElem10.getText().toString());
        datacatdeux.setTextElem11(textElem11.getText().toString());
        datacatdeux.setTextElem12(textElem12.getText().toString());
        datacatdeux.setTextElem13(textElem13.getText().toString());
        datacatdeux.setTextElem14(textElem14.getText().toString());
        datacatdeux.setTextElem15(textElem15.getText().toString());
    }

    private void loadState() {
        btnElem1.setChecked(datacatdeux.getBtnElem1());
        btnElem2.setChecked(datacatdeux.getBtnElem2());
        btnElem3.setChecked(datacatdeux.getBtnElem3());
        btnElem4.setChecked(datacatdeux.getBtnElem4());
        btnElem5.setChecked(datacatdeux.getBtnElem5());
        textElem1.setHint(datacatdeux.getHintElem1());
        textElem2.setHint(datacatdeux.getHintElem2());
        textElem3.setHint(datacatdeux.getHintElem3());
        textElem4.setHint(datacatdeux.getHintElem4());
        textElem5.setHint(datacatdeux.getHintElem5());
        textElem1.setText(datacatdeux.getTextElem1());
        textElem2.setText(datacatdeux.getTextElem2());
        textElem3.setText(datacatdeux.getTextElem3());
        textElem4.setText(datacatdeux.getTextElem4());
        textElem5.setText(datacatdeux.getTextElem5());

        btnElem6.setChecked(datacatdeux.getBtnElem6());
        btnElem7.setChecked(datacatdeux.getBtnElem7());
        btnElem8.setChecked(datacatdeux.getBtnElem8());
        btnElem9.setChecked(datacatdeux.getBtnElem9());
        btnElem10.setChecked(datacatdeux.getBtnElem10());
        textElem6.setHint(datacatdeux.getHintElem6());
        textElem7.setHint(datacatdeux.getHintElem7());
        textElem8.setHint(datacatdeux.getHintElem8());
        textElem9.setHint(datacatdeux.getHintElem9());
        textElem10.setHint(datacatdeux.getHintElem10());
        textElem6.setText(datacatdeux.getTextElem6());
        textElem7.setText(datacatdeux.getTextElem7());
        textElem8.setText(datacatdeux.getTextElem8());
        textElem9.setText(datacatdeux.getTextElem9());
        textElem10.setText(datacatdeux.getTextElem10());

        btnElem11.setChecked(datacatdeux.getBtnElem11());
        btnElem12.setChecked(datacatdeux.getBtnElem12());
        btnElem13.setChecked(datacatdeux.getBtnElem13());
        btnElem14.setChecked(datacatdeux.getBtnElem14());
        btnElem15.setChecked(datacatdeux.getBtnElem15());
        textElem11.setHint(datacatdeux.getHintElem11());
        textElem12.setHint(datacatdeux.getHintElem12());
        textElem13.setHint(datacatdeux.getHintElem13());
        textElem14.setHint(datacatdeux.getHintElem14());
        textElem15.setHint(datacatdeux.getHintElem15());
        textElem11.setText(datacatdeux.getTextElem11());
        textElem12.setText(datacatdeux.getTextElem12());
        textElem13.setText(datacatdeux.getTextElem13());
        textElem14.setText(datacatdeux.getTextElem14());
        textElem15.setText(datacatdeux.getTextElem15());

        titlecat2.setText(datacategory.getTextCat2());

        btnC2versC1.setText(datacategory.getTextCat1());
        btnC2versC2.setText(datacategory.getTextCat2());
        btnC2versC3.setText(datacategory.getTextCat3());

        /*if(dataoutput.isRunning()){
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
        }*/
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
