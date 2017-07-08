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

public class Cat3Activity extends AppCompatActivity {

    private ToggleButton btnElem1,btnElem2,btnElem3,btnElem4,btnElem5;
    private Button btnC3versConf,btnC3versC1,btnC3versC2,btnC3versC3;
    private EditText textElem1,textElem2,textElem3,textElem4,textElem5;
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
        setElementActivation();
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

    public void onClickBtnC3versConf(View view) {
        Intent intent = new Intent(Cat3Activity.this, ChooseActivity.class);
        startActivity(intent);
    }
    public void onClickBtnC3versC1(View view) {
        Intent intent = new Intent(Cat3Activity.this, LieuxActivity.class);
        startActivity(intent);
    }
    public void onClickBtnC3versC2(View view) {
        Intent intent = new Intent(Cat3Activity.this, Cat2Activity.class);
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
        titlecat3 = (TextView)findViewById(R.id.titleCat3);

        btnC3versConf = (Button)findViewById(R.id.btnC3versConf);
        btnC3versC1 = (Button)findViewById(R.id.btnC3versC1);
        btnC3versC2 = (Button)findViewById(R.id.btnC3versC2);
        btnC3versC3 = (Button)findViewById(R.id.btnC3versC3);

        tvActiveCat1Elem = (TextView)findViewById(R.id.tvActiveCat1Elem);
        tvActiveCat2Elem = (TextView)findViewById(R.id.tvActiveCat2Elem);
        tvActiveCat3Elem = (TextView)findViewById(R.id.tvActiveCat3Elem);
    }

    private void saveBtnState() {
        //save btn state
        datacattrois.setBtnElem1(btnElem1.isChecked());
        datacattrois.setBtnElem2(btnElem2.isChecked());
        datacattrois.setBtnElem3(btnElem3.isChecked());
        datacattrois.setBtnElem4(btnElem4.isChecked());
        datacattrois.setBtnElem5(btnElem5.isChecked());
    }
    private void saveEditTextValue() {
        //Save text state
        datacattrois.setTextElem1(textElem1.getText().toString());
        datacattrois.setTextElem2(textElem2.getText().toString());
        datacattrois.setTextElem3(textElem3.getText().toString());
        datacattrois.setTextElem4(textElem4.getText().toString());
        datacattrois.setTextElem5(textElem5.getText().toString());
    }


    private void loadState() {
        btnElem1.setChecked(datacattrois.getBtnElem1());
        btnElem2.setChecked(datacattrois.getBtnElem2());
        btnElem3.setChecked(datacattrois.getBtnElem3());
        btnElem4.setChecked(datacattrois.getBtnElem4());
        btnElem5.setChecked(datacattrois.getBtnElem5());
        textElem1.setHint(datacattrois.getHintElem1());
        textElem2.setHint(datacattrois.getHintElem2());
        textElem3.setHint(datacattrois.getHintElem3());
        textElem4.setHint(datacattrois.getHintElem4());
        textElem5.setHint(datacattrois.getHintElem5());

        textElem1.setText(datacattrois.getTextElem1());
        textElem2.setText(datacattrois.getTextElem2());
        textElem3.setText(datacattrois.getTextElem3());
        textElem4.setText(datacattrois.getTextElem4());
        textElem5.setText(datacattrois.getTextElem5());

        titlecat3.setText(datacategory.getTextCat3());

        btnC3versC1.setText(datacategory.getTextCat1());
        btnC3versC2.setText(datacategory.getTextCat2());
        btnC3versC3.setText(datacategory.getTextCat3());

        if(dataoutput.isRunning()){
            disableEditTextModification(textElem1);
            disableEditTextModification(textElem2);
            disableEditTextModification(textElem3);
            disableEditTextModification(textElem4);
            disableEditTextModification(textElem5);
        }else{
            enableEditTextModification(textElem1);
            enableEditTextModification(textElem2);
            enableEditTextModification(textElem3);
            enableEditTextModification(textElem4);
            enableEditTextModification(textElem5);
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
            setDisplay(textElem1,btnElem1);
            setDisplay(textElem2,btnElem2);
            setDisplay(textElem3,btnElem3);
            setDisplay(textElem4,btnElem4);
            setDisplay(textElem5,btnElem5);
        }else{
            forceVisible(textElem1,btnElem1);
            forceVisible(textElem2,btnElem2);
            forceVisible(textElem3,btnElem3);
            forceVisible(textElem4,btnElem4);
            forceVisible(textElem5,btnElem5);
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
