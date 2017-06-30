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

public class LieuxActivity extends AppCompatActivity {
    private ToggleButton btnL1,btnL2,btnL3,btnL4,btnL5;
    private Button btnC1versConf,btnC1versC1,btnC1versC2,btnC1versC3;
    private EditText textL1,textL2,textL3,textL4,textL5;
    private TextView titlecat1;
    private DataOutput dataoutput;
    private DataLieux datalieux;
    private DataCategory datacategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lieux);
        initObjects();

        btnL1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnL2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnL3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnL4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {onBtnAction();}
        });
        btnL5.setOnClickListener(new View.OnClickListener() {
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
        datalieux = DataLieux.getInstance();
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

    public void onClickBtnC1versConf(View view) {
        Intent intent = new Intent(LieuxActivity.this, ChooseActivity.class);
        startActivity(intent);
    }
    public void onClickBtnC1versC2(View view) {
        Intent intent = new Intent(LieuxActivity.this, Cat2Activity.class);
        startActivity(intent);
    }
    public void onClickBtnC1versC3(View view) {
        Intent intent = new Intent(LieuxActivity.this, Cat3Activity.class);
        startActivity(intent);
    }

    private void initObjects() {
        btnL1 = (ToggleButton)findViewById(R.id.btnElem1);
        btnL2 = (ToggleButton)findViewById(R.id.btnElem2);
        btnL3 = (ToggleButton)findViewById(R.id.btnElem3);
        btnL4 = (ToggleButton)findViewById(R.id.btnElem4);
        btnL5 = (ToggleButton)findViewById(R.id.btnElem5);
        textL1 = (EditText)findViewById(R.id.etElem1);
        textL2 = (EditText)findViewById(R.id.etElem2);
        textL3 = (EditText)findViewById(R.id.etElem3);
        textL4 = (EditText)findViewById(R.id.etElem4);
        textL5 = (EditText)findViewById(R.id.etElem5);
        titlecat1 = (TextView)findViewById(R.id.titleCat2);

        btnC1versConf = (Button)findViewById(R.id.btnC1versConf);
        btnC1versC1 = (Button)findViewById(R.id.btnC1versC1);
        btnC1versC2 = (Button)findViewById(R.id.btnC1versC2);
        btnC1versC3 = (Button)findViewById(R.id.btnC1versC3);

    }

    private void saveBtnState() {
        //save btn state
        datalieux.setBtnL1(btnL1.isChecked());
        datalieux.setBtnL2(btnL2.isChecked());
        datalieux.setBtnL3(btnL3.isChecked());
        datalieux.setBtnL4(btnL4.isChecked());
        datalieux.setBtnL5(btnL5.isChecked());
    }
    private void saveEditTextValue() {
        //Save text state
        datalieux.setTextL1(textL1.getText().toString());
        datalieux.setTextL2(textL2.getText().toString());
        datalieux.setTextL3(textL3.getText().toString());
        datalieux.setTextL4(textL4.getText().toString());
        datalieux.setTextL5(textL5.getText().toString());
    }
    private void loadState() {
        btnL1.setChecked(datalieux.getBtnL1());
        btnL2.setChecked(datalieux.getBtnL2());
        btnL3.setChecked(datalieux.getBtnL3());
        btnL4.setChecked(datalieux.getBtnL4());
        btnL5.setChecked(datalieux.getBtnL5());
        textL1.setText(datalieux.getTextL1());
        textL2.setText(datalieux.getTextL2());
        textL3.setText(datalieux.getTextL3());
        textL4.setText(datalieux.getTextL4());
        textL5.setText(datalieux.getTextL5());

        titlecat1.setText(datacategory.getTextCat1());

        btnC1versC1.setText(datacategory.getTextCat1());
        btnC1versC2.setText(datacategory.getTextCat2());
        btnC1versC3.setText(datacategory.getTextCat3());

        if(dataoutput.isRunning()){
            disableEditTextModification(textL1);
            disableEditTextModification(textL2);
            disableEditTextModification(textL3);
            disableEditTextModification(textL4);
            disableEditTextModification(textL5);
        }else{
            enableEditTextModification(textL1);
            enableEditTextModification(textL2);
            enableEditTextModification(textL3);
            enableEditTextModification(textL4);
            enableEditTextModification(textL5);
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
