package ady.ergo.tool.ergotool;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class ElementTry {

    private LinearLayout horizontalLL;
    private Button btn;
    private ToggleButton tbtn;
    private EditText edittext;

    public ElementTry(Activity activity) {
        horizontalLL = new LinearLayout(activity);
        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        horizontalLL.setLayoutParams(LLParams);

        btn = new Button(activity);
        btn.setLayoutParams(new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT));
        btn.setText(String.valueOf(btn.getId()));
        btn.setOnClickListener(listener);

        tbtn = new ToggleButton(activity);
        tbtn.setLayoutParams(new LinearLayout.LayoutParams(250, LinearLayout.LayoutParams.WRAP_CONTENT));

        edittext = new EditText(activity);
        edittext.setLayoutParams(new LinearLayout.LayoutParams(700, LinearLayout.LayoutParams.WRAP_CONTENT));
        edittext.setHint("Enter element");

        horizontalLL.addView(edittext);
        horizontalLL.addView(tbtn);
        horizontalLL.addView(btn);
    }

    public View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            ((LinearLayout)horizontalLL.getParent()).removeView(horizontalLL);
        }
    };

    public LinearLayout getHorizontalLL() {        return horizontalLL;    }
    public Button getBtn() {        return btn;    }
    public ToggleButton getTbtn() {        return tbtn;    }
    public EditText getEdittext() {        return edittext;    }
}
