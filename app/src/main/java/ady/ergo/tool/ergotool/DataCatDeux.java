package ady.ergo.tool.ergotool;

import java.util.ArrayList;

public class DataCatDeux {
    private ArrayList<Element> elemCatDeux;

    public boolean getBtnElem(int index){return elemCatDeux.get(index-1).isBtnElem();}
    public String getTextElem(int index) {return elemCatDeux.get(index-1).getTextElem();}
    public String getHintElem(int index) {return elemCatDeux.get(index-1).getHintElem();}
    public void setBtnElem(int index, boolean state) {this.elemCatDeux.get(index-1).setBtnElem(state);}
    public void setTextElem(int index, String text) {this.elemCatDeux.get(index-1).setTextElem(text);}

    private static final DataCatDeux holder = new DataCatDeux();
    public DataCatDeux(){
        elemCatDeux = new ArrayList<Element>();
    }
    public static DataCatDeux getInstance() {return holder;}

    public void initDataCatDeux(){
        for(int i=1;i<=15;i++){
            elemCatDeux.add(new Element(i));
        }
    }

    public void cleanDataCatDeux(){
        elemCatDeux.clear();
        for(int i=1;i<=15;i++){
            elemCatDeux.add(new Element(i));
        }
    }
}
