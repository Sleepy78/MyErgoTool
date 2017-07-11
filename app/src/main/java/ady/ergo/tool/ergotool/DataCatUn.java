package ady.ergo.tool.ergotool;

import java.util.ArrayList;

public class DataCatUn {
    private ArrayList<Element> elemCatUn;

    public boolean getBtnElem(int index){return elemCatUn.get(index-1).isBtnElem();}
    public String getTextElem(int index) {return elemCatUn.get(index-1).getTextElem();}
    public String getHintElem(int index) {return elemCatUn.get(index-1).getHintElem();}
    public void setBtnElem(int index, boolean state) {this.elemCatUn.get(index-1).setBtnElem(state);}
    public void setTextElem(int index, String text) {this.elemCatUn.get(index-1).setTextElem(text);}

    private static final DataCatUn holder = new DataCatUn();
    public DataCatUn(){
        elemCatUn = new ArrayList<Element>();
    }
    public static DataCatUn getInstance() {return holder;}

    public void initDataCatUn(){
        for(int i=1;i<=15;i++){
            elemCatUn.add(new Element(i));
        }
    }
}
