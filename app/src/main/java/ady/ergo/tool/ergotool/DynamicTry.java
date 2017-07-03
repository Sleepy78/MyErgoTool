package ady.ergo.tool.ergotool;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class DynamicTry extends AppCompatActivity {
    private RelativeLayout rl;
    private ArrayList<Button> buttons,buttons2;
    private ArrayList<ToggleButton> togglebuttons,togglebuttons2;
    private ArrayList<EditText> edittexts,edittexts2;
    private ArrayList<RelativeLayout.LayoutParams> params;
    private ArrayList<LinearLayout> ll2;

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_try);

        //initObjects();
        initObjects2();
    }
    public View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            //Toast.makeText(getApplicationContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
            ll.removeView(ll2.get(id));

        }
    };

    private void initObjects2() {
        ll = (LinearLayout)findViewById(R.id.linearLayout);

        buttons2 = new ArrayList<Button>();
        togglebuttons2 = new ArrayList<ToggleButton>();
        edittexts2 = new ArrayList<EditText>();
        ll2 = new ArrayList<LinearLayout>();

        edittexts2.add(new EditText(this));
        edittexts2.get(0).setLayoutParams(new LinearLayout.LayoutParams(700, LinearLayout.LayoutParams.WRAP_CONTENT));
        edittexts2.get(0).setId(0);
        edittexts2.get(0).setText("new1");

        buttons2.add(new Button(this));
        buttons2.get(0).setLayoutParams(new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT));
        buttons2.get(0).setId(0);
        buttons2.get(0).setText("-");
        buttons2.get(0).setOnClickListener(listener);

        togglebuttons2.add(new ToggleButton(this));
        togglebuttons2.get(0).setLayoutParams(new LinearLayout.LayoutParams(250, LinearLayout.LayoutParams.WRAP_CONTENT));
        togglebuttons2.get(0).setId(0);


        ll2.add(new LinearLayout(this));
        ll2.get(0).setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //ll2.setWeightSum(6f);
        ll2.get(0).setLayoutParams(LLParams);

        ll2.get(0).addView(edittexts2.get(0));
        ll2.get(0).addView(togglebuttons2.get(0));
        ll2.get(0).addView(buttons2.get(0));
        ll.addView(ll2.get(0));


        edittexts2.add(new EditText(this));
        edittexts2.get(1).setLayoutParams(new LinearLayout.LayoutParams(700, LinearLayout.LayoutParams.WRAP_CONTENT));
        edittexts2.get(1).setId(1);
        edittexts2.get(1).setText("new2");

        buttons2.add(new Button(this));
        buttons2.get(1).setLayoutParams(new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT));
        buttons2.get(1).setId(1);
        buttons2.get(1).setText("-");
        buttons2.get(1).setOnClickListener(listener);

        togglebuttons2.add(new ToggleButton(this));
        togglebuttons2.get(1).setLayoutParams(new LinearLayout.LayoutParams(250, LinearLayout.LayoutParams.WRAP_CONTENT));
        togglebuttons2.get(1).setId(1);

        ll2.add(new LinearLayout(this));
        ll2.get(1).setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams LLParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //ll2.setWeightSum(6f);
        ll2.get(1).setLayoutParams(LLParams2);

        ll2.get(1).addView(edittexts2.get(1));
        ll2.get(1).addView(togglebuttons2.get(1));
        ll2.get(1).addView(buttons2.get(1));
        ll.addView(ll2.get(1));

        //ll.removeView(ll2);


        //ll.removeView(edittexts2.get(0));
    }

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
