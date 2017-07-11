package ady.ergo.tool.ergotool;

public class DataCategory {
    private String textCat1;
    private String textCat2;
    private String textCat3;

    private String textTVCat1;
    private String textTVCat2;
    private String textTVCat3;

    public String getTextCat1() {return textCat1;}
    public String getTextCat2() {return textCat2;}
    public String getTextCat3() {return textCat3;}

    public void setTextCat1(String text) {this.textCat1 = text;}
    public void setTextCat2(String text) {this.textCat2 = text;}
    public void setTextCat3(String text) {this.textCat3 = text;}

    public String getTextTVCat1(DataCatUn dcatun) {evalCat1(dcatun); return textTVCat1;}
    public String getTextTVCat2(DataCatDeux dcatdeux) {evalCat2(dcatdeux); return textTVCat2;}
    public String getTextTVCat3(DataCatTrois dcattrois) {evalCat3(dcattrois);return textTVCat3;}

    public void setTextTVCat1(String text) {this.textTVCat1 = text;}
    public void setTextTVCat2(String text) {this.textTVCat2 = text;}
    public void setTextTVCat3(String text) {this.textTVCat3 = text;}

    private static final DataCategory holder = new DataCategory();
    public static DataCategory getInstance() {return holder;}

    public void initCategory(){
        textCat1="Cat1";
        textCat2="Cat2";
        textCat3="Cat3";
        textTVCat1="";
        textTVCat2="";
        textTVCat3="";
    }

    private void evalCat1(DataCatUn datacatun) {
        textTVCat1="";
        if ( datacatun.getBtnElem(1) ){
            textTVCat1+=datacatun.getTextElem(1) + "   ";
        }
        if ( datacatun.getBtnElem(2) ){
            textTVCat1+=datacatun.getTextElem(2) + "   ";
        }
        if ( datacatun.getBtnElem(3) ){
            textTVCat1+=datacatun.getTextElem(3) + "   ";
        }
        if ( datacatun.getBtnElem(4) ){
            textTVCat1+=datacatun.getTextElem(4) + "   ";
        }
        if ( datacatun.getBtnElem(5) ){
            textTVCat1+=datacatun.getTextElem(5) + "   ";
        }
        if ( datacatun.getBtnElem(6) ){
            textTVCat1+=datacatun.getTextElem(6) + "   ";
        }
        if ( datacatun.getBtnElem(7) ){
            textTVCat1+=datacatun.getTextElem(7) + "   ";
        }
        if ( datacatun.getBtnElem(8) ){
            textTVCat1+=datacatun.getTextElem(8) + "   ";
        }
        if ( datacatun.getBtnElem(9) ){
            textTVCat1+=datacatun.getTextElem(9) + "   ";
        }
        if ( datacatun.getBtnElem(10) ){
            textTVCat1+=datacatun.getTextElem(10) + "   ";
        }
        if ( datacatun.getBtnElem(11) ){
            textTVCat1+=datacatun.getTextElem(11) + "   ";
        }
        if ( datacatun.getBtnElem(12) ){
            textTVCat1+=datacatun.getTextElem(12) + "   ";
        }
        if ( datacatun.getBtnElem(13) ){
            textTVCat1+=datacatun.getTextElem(13) + "   ";
        }
        if ( datacatun.getBtnElem(14) ){
            textTVCat1+=datacatun.getTextElem(14) + "   ";
        }
        if ( datacatun.getBtnElem(15) ){
            textTVCat1+=datacatun.getTextElem(15) + "   ";
        }
        textTVCat1.trim();
    }

    private void evalCat2(DataCatDeux datacatdeux) {
        textTVCat2="";
        if ( datacatdeux.getBtnElem(1) ){
            textTVCat2+=datacatdeux.getTextElem(1) + "   ";
        }
        if ( datacatdeux.getBtnElem(2) ){
            textTVCat2+=datacatdeux.getTextElem(2) + "   ";
        }
        if ( datacatdeux.getBtnElem(3) ){
            textTVCat2+=datacatdeux.getTextElem(3) + "   ";
        }
        if ( datacatdeux.getBtnElem(4) ){
            textTVCat2+=datacatdeux.getTextElem(4) + "   ";
        }
        if ( datacatdeux.getBtnElem(5) ){
            textTVCat2+=datacatdeux.getTextElem(5) + "   ";
        }
        if ( datacatdeux.getBtnElem(6) ){
            textTVCat2+=datacatdeux.getTextElem(6) + "   ";
        }
        if ( datacatdeux.getBtnElem(7) ){
            textTVCat2+=datacatdeux.getTextElem(7) + "   ";
        }
        if ( datacatdeux.getBtnElem(8) ){
            textTVCat2+=datacatdeux.getTextElem(8) + "   ";
        }
        if ( datacatdeux.getBtnElem(9) ){
            textTVCat2+=datacatdeux.getTextElem(9) + "   ";
        }
        if ( datacatdeux.getBtnElem(10) ){
            textTVCat2+=datacatdeux.getTextElem(10) + "   ";
        }
        if ( datacatdeux.getBtnElem(11) ){
            textTVCat2+=datacatdeux.getTextElem(11) + "   ";
        }
        if ( datacatdeux.getBtnElem(12) ){
            textTVCat2+=datacatdeux.getTextElem(12) + "   ";
        }
        if ( datacatdeux.getBtnElem(13) ){
            textTVCat2+=datacatdeux.getTextElem(13) + "   ";
        }
        if ( datacatdeux.getBtnElem(14) ){
            textTVCat2+=datacatdeux.getTextElem(14) + "   ";
        }
        if ( datacatdeux.getBtnElem(15) ){
            textTVCat2+=datacatdeux.getTextElem(15) + "   ";
        }
        textTVCat2.trim();
    }
    private void evalCat3(DataCatTrois datacattrois) {
        textTVCat3="";
        if ( datacattrois.getBtnElem(1) ){
            textTVCat3+=datacattrois.getTextElem(1) + "   ";
        }
        if ( datacattrois.getBtnElem(2) ){
            textTVCat3+=datacattrois.getTextElem(2) + "   ";
        }
        if ( datacattrois.getBtnElem(3) ){
            textTVCat3+=datacattrois.getTextElem(3) + "   ";
        }
        if ( datacattrois.getBtnElem(4) ){
            textTVCat3+=datacattrois.getTextElem(4) + "   ";
        }
        if ( datacattrois.getBtnElem(5) ){
            textTVCat3+=datacattrois.getTextElem(5) + "   ";
        }
        if ( datacattrois.getBtnElem(6) ){
            textTVCat3+=datacattrois.getTextElem(6) + "   ";
        }
        if ( datacattrois.getBtnElem(7) ){
            textTVCat3+=datacattrois.getTextElem(7) + "   ";
        }
        if ( datacattrois.getBtnElem(8) ){
            textTVCat3+=datacattrois.getTextElem(8) + "   ";
        }
        if ( datacattrois.getBtnElem(9) ){
            textTVCat3+=datacattrois.getTextElem(9) + "   ";
        }
        if ( datacattrois.getBtnElem(10) ){
            textTVCat3+=datacattrois.getTextElem(10) + "   ";
        }
        if ( datacattrois.getBtnElem(11) ){
            textTVCat3+=datacattrois.getTextElem(11) + "   ";
        }
        if ( datacattrois.getBtnElem(12) ){
            textTVCat3+=datacattrois.getTextElem(12) + "   ";
        }
        if ( datacattrois.getBtnElem(13) ){
            textTVCat3+=datacattrois.getTextElem(13) + "   ";
        }
        if ( datacattrois.getBtnElem(14) ){
            textTVCat3+=datacattrois.getTextElem(14) + "   ";
        }
        if ( datacattrois.getBtnElem(15) ){
            textTVCat3+=datacattrois.getTextElem(15) + "   ";
        }
        textTVCat3.trim();
    }
}
