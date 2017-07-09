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

    public String getTextTVCat1(DataCatUn dlieux) {evalCat1(dlieux); return textTVCat1;}
    public String getTextTVCat2(DataCatDeux dcatdeux) {evalCat2(dcatdeux); return textTVCat2;}
    public String getTextTVCat3(DataCatTrois dcattrois) {evalCat3(dcattrois);return textTVCat3;}

    public void setTextTVCat1(String text) {this.textTVCat1 = text;}
    public void setTextTVCat2(String text) {this.textTVCat2 = text;}
    public void setTextTVCat3(String text) {this.textTVCat3 = text;}

    private static final DataCategory holder = new DataCategory();
    public static DataCategory getInstance() {return holder;}

    public void initCategory(){
        textCat1="Cat1111";
        textCat2="Cat2222";
        textCat3="Cat3333";
        textTVCat1="";
        textTVCat2="";
        textTVCat3="";
    }

    private void evalCat1(DataCatUn datacatun) {
        textTVCat1="";
        if ( datacatun.getBtnElem1() ){
            textTVCat1+=datacatun.getTextElem1() + "   ";
        }
        if ( datacatun.getBtnElem2() ){
            textTVCat1+=datacatun.getTextElem2() + "   ";
        }
        if ( datacatun.getBtnElem3() ){
            textTVCat1+=datacatun.getTextElem3() + "   ";
        }
        if ( datacatun.getBtnElem4() ){
            textTVCat1+=datacatun.getTextElem4() + "   ";
        }
        if ( datacatun.getBtnElem5() ){
            textTVCat1+=datacatun.getTextElem5() + "   ";
        }
        if ( datacatun.getBtnElem6() ){
            textTVCat1+=datacatun.getTextElem6() + "   ";
        }
        if ( datacatun.getBtnElem7() ){
            textTVCat1+=datacatun.getTextElem7() + "   ";
        }
        if ( datacatun.getBtnElem8() ){
            textTVCat1+=datacatun.getTextElem8() + "   ";
        }
        if ( datacatun.getBtnElem9() ){
            textTVCat1+=datacatun.getTextElem9() + "   ";
        }
        if ( datacatun.getBtnElem10() ){
            textTVCat1+=datacatun.getTextElem10() + "   ";
        }
        if ( datacatun.getBtnElem11() ){
            textTVCat1+=datacatun.getTextElem11() + "   ";
        }
        if ( datacatun.getBtnElem12() ){
            textTVCat1+=datacatun.getTextElem12() + "   ";
        }
        if ( datacatun.getBtnElem13() ){
            textTVCat1+=datacatun.getTextElem13() + "   ";
        }
        if ( datacatun.getBtnElem14() ){
            textTVCat1+=datacatun.getTextElem14() + "   ";
        }
        if ( datacatun.getBtnElem15() ){
            textTVCat1+=datacatun.getTextElem15() + "   ";
        }
        textTVCat1.trim();
    }

    private void evalCat2(DataCatDeux datacatdeux) {
        textTVCat2="";
        if ( datacatdeux.getBtnElem1() ){
            textTVCat2+=datacatdeux.getTextElem1() + "   ";
        }
        if ( datacatdeux.getBtnElem2() ){
            textTVCat2+=datacatdeux.getTextElem2() + "   ";
        }
        if ( datacatdeux.getBtnElem3() ){
            textTVCat2+=datacatdeux.getTextElem3() + "   ";
        }
        if ( datacatdeux.getBtnElem4() ){
            textTVCat2+=datacatdeux.getTextElem4() + "   ";
        }
        if ( datacatdeux.getBtnElem5() ){
            textTVCat2+=datacatdeux.getTextElem5() + "   ";
        }
        if ( datacatdeux.getBtnElem6() ){
            textTVCat2+=datacatdeux.getTextElem6() + "   ";
        }
        if ( datacatdeux.getBtnElem7() ){
            textTVCat2+=datacatdeux.getTextElem7() + "   ";
        }
        if ( datacatdeux.getBtnElem8() ){
            textTVCat2+=datacatdeux.getTextElem8() + "   ";
        }
        if ( datacatdeux.getBtnElem9() ){
            textTVCat2+=datacatdeux.getTextElem9() + "   ";
        }
        if ( datacatdeux.getBtnElem10() ){
            textTVCat2+=datacatdeux.getTextElem10() + "   ";
        }
        if ( datacatdeux.getBtnElem11() ){
            textTVCat2+=datacatdeux.getTextElem11() + "   ";
        }
        if ( datacatdeux.getBtnElem12() ){
            textTVCat2+=datacatdeux.getTextElem12() + "   ";
        }
        if ( datacatdeux.getBtnElem13() ){
            textTVCat2+=datacatdeux.getTextElem13() + "   ";
        }
        if ( datacatdeux.getBtnElem14() ){
            textTVCat2+=datacatdeux.getTextElem14() + "   ";
        }
        if ( datacatdeux.getBtnElem15() ){
            textTVCat2+=datacatdeux.getTextElem15() + "   ";
        }
        textTVCat2.trim();
    }
    private void evalCat3(DataCatTrois datacattrois) {
        textTVCat3="";
        if ( datacattrois.getBtnElem1() ){
            textTVCat3+=datacattrois.getTextElem1() + "   ";
        }
        if ( datacattrois.getBtnElem2() ){
            textTVCat3+=datacattrois.getTextElem2() + "   ";
        }
        if ( datacattrois.getBtnElem3() ){
            textTVCat3+=datacattrois.getTextElem3() + "   ";
        }
        if ( datacattrois.getBtnElem4() ){
            textTVCat3+=datacattrois.getTextElem4() + "   ";
        }
        if ( datacattrois.getBtnElem5() ){
            textTVCat3+=datacattrois.getTextElem5() + "   ";
        }
        if ( datacattrois.getBtnElem6() ){
            textTVCat3+=datacattrois.getTextElem6() + "   ";
        }
        if ( datacattrois.getBtnElem7() ){
            textTVCat3+=datacattrois.getTextElem7() + "   ";
        }
        if ( datacattrois.getBtnElem8() ){
            textTVCat3+=datacattrois.getTextElem8() + "   ";
        }
        if ( datacattrois.getBtnElem9() ){
            textTVCat3+=datacattrois.getTextElem9() + "   ";
        }
        if ( datacattrois.getBtnElem10() ){
            textTVCat3+=datacattrois.getTextElem10() + "   ";
        }
        if ( datacattrois.getBtnElem11() ){
            textTVCat3+=datacattrois.getTextElem11() + "   ";
        }
        if ( datacattrois.getBtnElem12() ){
            textTVCat3+=datacattrois.getTextElem12() + "   ";
        }
        if ( datacattrois.getBtnElem13() ){
            textTVCat3+=datacattrois.getTextElem13() + "   ";
        }
        if ( datacattrois.getBtnElem14() ){
            textTVCat3+=datacattrois.getTextElem14() + "   ";
        }
        if ( datacattrois.getBtnElem15() ){
            textTVCat3+=datacattrois.getTextElem15() + "   ";
        }
        textTVCat3.trim();
    }
}
