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
    private Button btnC2versConf,btnC2versC1,btnC2versC3;
    private EditText textElem1,textElem2,textElem3,textElem4,textElem5;
    private TextView titlecat2;
    private DataOutput dataoutput;
    //private DataLieux datalieux;
    private DataCatDeux datacatdeux;
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
        datacatdeux = DataCatDeux.getInstance();
        datacategory = DataCategory.getInstance();
        loadState();
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

    public void onClickBtnC2versConf(View view) {
        Intent intent = new Intent(Cat2Activity.this, ChooseActivity.class);
        startActivity(intent);
    }
    public void onClickBtnC2versC1(View view) {
        Intent intent = new Intent(Cat2Activity.this, LieuxActivity.class);
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
        titlecat2 = (TextView)findViewById(R.id.titleCat2);

        btnC2versConf = (Button)findViewById(R.id.btnC2versConf);
        btnC2versC1 = (Button)findViewById(R.id.btnC2versC1);
        btnC2versC3 = (Button)findViewById(R.id.btnC2versC3);

    }

    private void saveBtnState() {
        //save btn state
        datacatdeux.setBtnElem1(btnElem1.isChecked());
        datacatdeux.setBtnElem2(btnElem2.isChecked());
        datacatdeux.setBtnElem3(btnElem3.isChecked());
        datacatdeux.setBtnElem4(btnElem4.isChecked());
        datacatdeux.setBtnElem5(btnElem5.isChecked());
    }
    private void saveEditTextValue() {
        //Save text state
        datacatdeux.setTextElem1(textElem1.getText().toString());
        datacatdeux.setTextElem2(textElem2.getText().toString());
        datacatdeux.setTextElem3(textElem3.getText().toString());
        datacatdeux.setTextElem4(textElem4.getText().toString());
        datacatdeux.setTextElem5(textElem5.getText().toString());
    }


    private void loadState() {
        btnElem1.setChecked(datacatdeux.getBtnElem1());
        btnElem2.setChecked(datacatdeux.getBtnElem2());
        btnElem3.setChecked(datacatdeux.getBtnElem3());
        btnElem4.setChecked(datacatdeux.getBtnElem4());
        btnElem5.setChecked(datacatdeux.getBtnElem5());
        textElem1.setText(datacatdeux.getTextElem1());
        textElem2.setText(datacatdeux.getTextElem2());
        textElem3.setText(datacatdeux.getTextElem3());
        textElem4.setText(datacatdeux.getTextElem4());
        textElem5.setText(datacatdeux.getTextElem5());

        titlecat2.setText(datacategory.getTextCat2());

        btnC2versC1.setText(datacategory.getTextCat1());
        btnC2versC3.setText(datacategory.getTextCat3());

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

    private void enableEditTextModification(EditText editText){
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setFocusable(true);
    }
    private void disableEditTextModification(EditText editText){
        editText.setInputType(InputType.TYPE_NULL);
        editText.setFocusable(false);
    }
}