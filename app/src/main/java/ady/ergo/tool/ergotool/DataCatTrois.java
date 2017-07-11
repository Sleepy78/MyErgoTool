package ady.ergo.tool.ergotool;

import java.util.ArrayList;

public class DataCatTrois {
    private ArrayList<Element> elemCatTrois;

    public boolean getBtnElem(int index){return elemCatTrois.get(index-1).isBtnElem();}
    public String getTextElem(int index) {return elemCatTrois.get(index-1).getTextElem();}
    public String getHintElem(int index) {return elemCatTrois.get(index-1).getHintElem();}
    public void setBtnElem(int index, boolean state) {this.elemCatTrois.get(index-1).setBtnElem(state);}
    public void setTextElem(int index, String text) {this.elemCatTrois.get(index-1).setTextElem(text);}

    private static final DataCatTrois holder = new DataCatTrois();
    public DataCatTrois(){
        elemCatTrois = new ArrayList<Element>();
    }
    public static DataCatTrois getInstance() {return holder;}

    public void initDataCatTrois(){
        for(int i=1;i<=15;i++){
            elemCatTrois.add(new Element(i));
        }
    }
}

