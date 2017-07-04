package ady.ergo.tool.ergotool;

public class DataLieux {
    private boolean btnL1;
    private boolean btnL2;
    private boolean btnL3;
    private boolean btnL4;
    private boolean btnL5;
    private String textL1;
    private String textL2;
    private String textL3;
    private String textL4;
    private String textL5;
    private String hintElem1;
    private String hintElem2;
    private String hintElem3;
    private String hintElem4;
    private String hintElem5;

    public boolean getBtnL1() {return btnL1;}
    public boolean getBtnL2() {return btnL2;}
    public boolean getBtnL3() {return btnL3;}
    public boolean getBtnL4() {return btnL4;}
    public boolean getBtnL5() {return btnL5;}
    public String getTextL1() {return textL1;}
    public String getTextL2() {return textL2;}
    public String getTextL3() {return textL3;}
    public String getTextL4() {return textL4;}
    public String getTextL5() {return textL5;}
    public String getHintElem1() {return hintElem1;}
    public String getHintElem2() {return hintElem2;}
    public String getHintElem3() {return hintElem3;}
    public String getHintElem4() {return hintElem4;}
    public String getHintElem5() {return hintElem5;}

    public void setBtnL1(boolean state) {this.btnL1 = state;}
    public void setBtnL2(boolean state) {this.btnL2 = state;}
    public void setBtnL3(boolean state) {this.btnL3 = state;}
    public void setBtnL4(boolean state) {this.btnL4 = state;}
    public void setBtnL5(boolean state) {this.btnL5 = state;}
    public void setTextL1(String text) {this.textL1 = text;}
    public void setTextL2(String text) {this.textL2 = text;}
    public void setTextL3(String text) {this.textL3 = text;}
    public void setTextL4(String text) {this.textL4 = text;}
    public void setTextL5(String text) {this.textL5 = text;}

    private static final DataLieux holder = new DataLieux();
    public static DataLieux getInstance() {return holder;}

    public void initDataLieuxHint(){
        hintElem1 = "Elem1Cat1";
        hintElem2 = "Elem2Cat1";
        hintElem3 = "Elem3Cat1";
        hintElem4 = "Elem4Cat1";
        hintElem5 = "Elem5Cat1";
    }
}
