package ady.ergo.tool.ergotool;

public class DataCatUn {
    private boolean btnElem1;
    private boolean btnElem2;
    private boolean btnElem3;
    private boolean btnElem4;
    private boolean btnElem5;
    private String textElem1;
    private String textElem2;
    private String textElem3;
    private String textElem4;
    private String textElem5;
    private String hintElem1;
    private String hintElem2;
    private String hintElem3;
    private String hintElem4;
    private String hintElem5;

    public boolean getBtnElem1() {return btnElem1;}
    public boolean getBtnElem2() {return btnElem2;}
    public boolean getBtnElem3() {return btnElem3;}
    public boolean getBtnElem4() {return btnElem4;}
    public boolean getBtnElem5() {return btnElem5;}
    public String getTextElem1() {return textElem1;}
    public String getTextElem2() {return textElem2;}
    public String getTextElem3() {return textElem3;}
    public String getTextElem4() {return textElem4;}
    public String getTextElem5() {return textElem5;}
    public String getHintElem1() {return hintElem1;}
    public String getHintElem2() {return hintElem2;}
    public String getHintElem3() {return hintElem3;}
    public String getHintElem4() {return hintElem4;}
    public String getHintElem5() {return hintElem5;}

    public void setBtnElem1(boolean state) {this.btnElem1 = state;}
    public void setBtnElem2(boolean state) {this.btnElem2 = state;}
    public void setBtnElem3(boolean state) {this.btnElem3 = state;}
    public void setBtnElem4(boolean state) {this.btnElem4 = state;}
    public void setBtnElem5(boolean state) {this.btnElem5 = state;}
    public void setTextElem1(String text) {this.textElem1 = text;}
    public void setTextElem2(String text) {this.textElem2 = text;}
    public void setTextElem3(String text) {this.textElem3 = text;}
    public void setTextElem4(String text) {this.textElem4 = text;}
    public void setTextElem5(String text) {this.textElem5 = text;}

    private static final DataCatUn holder = new DataCatUn();
    public static DataCatUn getInstance() {return holder;}

    public void initDataLieux(){
        textElem1 = "";
        textElem2 = "";
        textElem3 = "";
        textElem4 = "";
        textElem5 = "";
        hintElem1 = "Elem1Cat1";
        hintElem2 = "Elem2Cat1";
        hintElem3 = "Elem3Cat1";
        hintElem4 = "Elem4Cat1";
        hintElem5 = "Elem5Cat1";
    }
}
