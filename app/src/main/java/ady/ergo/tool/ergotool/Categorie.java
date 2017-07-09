package ady.ergo.tool.ergotool;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;



public class Categorie {
    private Activity activity;
    private ArrayList<ElementTry> elements;
    private LinearLayout verticalLL;
    private Button addElement;

    public Categorie(Activity act) {
        activity = act;
        this.elements =  new ArrayList<ElementTry>();

        addElement = (Button) activity.findViewById(R.id.btnAddElement);
        addElement.setOnClickListener(listenerAddAnElement);
        verticalLL = (LinearLayout)activity.findViewById(R.id.linearLayout);

        //create a first element
        elements.add(new ElementTry(activity));

        //Add first element to linearlayout
        verticalLL.addView(elements.get(elements.size()-1).getHorizontalLL());
    }

    public View.OnClickListener listenerAddAnElement = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            elements.add(new ElementTry(activity));
            verticalLL.addView(elements.get(elements.size()-1).getHorizontalLL());
        }
    };
}
