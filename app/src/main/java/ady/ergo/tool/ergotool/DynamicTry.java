package ady.ergo.tool.ergotool;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class DynamicTry extends AppCompatActivity {
    private RelativeLayout rl;
    private ArrayList<Button> buttons;
    private ArrayList<ToggleButton> togglebuttons;
    private ArrayList<EditText> edittexts;
    private ArrayList<RelativeLayout.LayoutParams> params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_try);

        initObjects();
    }
    public View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            //Toast.makeText(getApplicationContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
            rl.removeView(v);
            rl.removeView(edittexts.get(id - 1));
            rl.removeView(togglebuttons.get(id - 1));
        }
    };
    private void initObjects() {
        rl = (RelativeLayout)findViewById(R.id.relativeLayout);

        buttons = new ArrayList<Button>();
        togglebuttons = new ArrayList<ToggleButton>();
        edittexts = new ArrayList<EditText>();
        params = new ArrayList<RelativeLayout.LayoutParams>();

        //new set
        params.add(new RelativeLayout.LayoutParams(700, RelativeLayout.LayoutParams.WRAP_CONTENT));
        params.add(new RelativeLayout.LayoutParams(100, RelativeLayout.LayoutParams.WRAP_CONTENT));
        params.add(new RelativeLayout.LayoutParams(250, RelativeLayout.LayoutParams.WRAP_CONTENT));

        edittexts.add(new EditText(this));
        params.get(0).addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        edittexts.get(0).setId(0);
        edittexts.get(0).setText("new1");

        buttons.add(new Button(this));
        params.get(1).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        buttons.get(0).setId(1);
        buttons.get(0).setText("-");
        buttons.get(0).setOnClickListener(listener);

        togglebuttons.add(new ToggleButton(this));
        params.get(2).addRule(RelativeLayout.LEFT_OF, buttons.get(0).getId());
        togglebuttons.get(0).setId(2);

        rl.addView(edittexts.get(0),params.get(0));
        rl.addView(togglebuttons.get(0),params.get(2));
        rl.addView(buttons.get(0),params.get(1));

        //new set
        params.add(new RelativeLayout.LayoutParams(700, RelativeLayout.LayoutParams.WRAP_CONTENT));
        params.add(new RelativeLayout.LayoutParams(100, RelativeLayout.LayoutParams.WRAP_CONTENT));
        params.add(new RelativeLayout.LayoutParams(250, RelativeLayout.LayoutParams.WRAP_CONTENT));

        edittexts.add(new EditText(this));
        params.get(3).addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.get(3).addRule(RelativeLayout.BELOW, buttons.get(0).getId());
        edittexts.get(1).setId(3);
        edittexts.get(1).setText("new2");

        buttons.add(new Button(this));
        params.get(4).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.get(4).addRule(RelativeLayout.BELOW, buttons.get(0).getId());
        buttons.get(1).setId(4);
        buttons.get(1).setText("-");

        togglebuttons.add(new ToggleButton(this));
        params.get(5).addRule(RelativeLayout.LEFT_OF, buttons.get(1).getId());
        params.get(5).addRule(RelativeLayout.BELOW, buttons.get(0).getId());
        togglebuttons.get(1).setId(5);

        rl.addView(edittexts.get(1),params.get(3));
        rl.addView(togglebuttons.get(1),params.get(5));
        rl.addView(buttons.get(1),params.get(4));
    }
}
