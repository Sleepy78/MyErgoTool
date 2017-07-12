package ady.ergo.tool.ergotool;

public class Element {

    private boolean btnElem;
    private String textElem;
    private String hintElem;

    public boolean isBtnElem() {
        return btnElem;
    }
    public void setBtnElem(boolean btnElem) {
        this.btnElem = btnElem;
    }
    public String getTextElem() {
        return textElem;
    }
    public void setTextElem(String textElem) {
        this.textElem = textElem;
    }
    public String getHintElem() {
        return hintElem;
    }
    public void setHintElem(String hintElem) {
        this.hintElem = hintElem;
    }

    public Element(int elem){
        textElem = "E".concat(String.valueOf(elem));
        hintElem = "Element name";
    }
}

