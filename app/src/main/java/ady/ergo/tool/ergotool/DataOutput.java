package ady.ergo.tool.ergotool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class DataOutput {
    private String header;
    private String outputline;
    private ArrayList fullFile;
    private boolean isRunning;
    private boolean isSentable;
    private long initTime;
    String delimitor = ";";

    public String getHeader() {return header;}
    public void setInitTime(long t) {initTime = t;}

    public String getOutputLine() {return outputline;}
    public void setOutputLine(String out) {outputline = out;}

    public boolean isRunning() {return isRunning;}
    public void setIsRunning(boolean run) {isRunning = run;}

    public boolean isSentable() {return isSentable;}
    public void setIsSentable(boolean sentable) {isSentable = sentable;}

    public ArrayList getFullFile() {return fullFile;}
    public void initFullFile(String header) {
        fullFile = new ArrayList();
        fullFile.add(header);
    }
    public void addFullFile(String line) {fullFile.add(line);}


    private static final DataOutput holder = new DataOutput();
    public static DataOutput getInstance() {return holder;}

    public boolean updateHeader(){
        DataCatUn datacatun = DataCatUn.getInstance();
        DataCatDeux datacatdeux = DataCatDeux.getInstance();
        DataCatTrois datacattrois = DataCatTrois.getInstance();
        DataCategory datacat = DataCategory.getInstance();
        header="";

        header+="TimeDdelay in ms" + delimitor;
        header+="Time HH:MM:SS" + delimitor;
        header+="Time Absolute" + delimitor;

        //All about Cat1
        header+=datacat.getTextCat1() + delimitor;

        setElemHeader(datacatun.getTextElem1());
        setElemHeader(datacatun.getTextElem2());
        setElemHeader(datacatun.getTextElem3());
        setElemHeader(datacatun.getTextElem4());
        setElemHeader(datacatun.getTextElem5());
        setElemHeader(datacatun.getTextElem6());
        setElemHeader(datacatun.getTextElem7());
        setElemHeader(datacatun.getTextElem8());
        setElemHeader(datacatun.getTextElem9());
        setElemHeader(datacatun.getTextElem10());
        setElemHeader(datacatun.getTextElem11());
        setElemHeader(datacatun.getTextElem12());
        setElemHeader(datacatun.getTextElem13());
        setElemHeader(datacatun.getTextElem14());
        setElemHeader(datacatun.getTextElem15());

        header+=" " + delimitor;   //Needed for blank in csv output

        //All about Cat2Activity
        header+=datacat.getTextCat2() + delimitor;

        setElemHeader(datacatdeux.getTextElem1());
        setElemHeader(datacatdeux.getTextElem2());
        setElemHeader(datacatdeux.getTextElem3());
        setElemHeader(datacatdeux.getTextElem4());
        setElemHeader(datacatdeux.getTextElem5());

        header+=" " + delimitor;   //Needed for blank in csv output

        //All about Cat3Activity
        header+=datacat.getTextCat3() + delimitor;

        setElemHeader(datacattrois.getTextElem1());
        setElemHeader(datacattrois.getTextElem2());
        setElemHeader(datacattrois.getTextElem3());
        setElemHeader(datacattrois.getTextElem4());
        setElemHeader(datacattrois.getTextElem5());

        header+=" " + delimitor;   //Needed for blank in csv output

        return true;
    }

    public String updateOutputLine(){
        DataCatUn datacatun = DataCatUn.getInstance();
        DataCatDeux datacatdeux = DataCatDeux.getInstance();
        DataCatTrois datacattrois = DataCatTrois.getInstance();
        //DataCategory datacat = DataCategory.getInstance();
        outputline="";
        outputline+=getDelay() + delimitor;
        outputline+=getTimeHHMMSS(getDelay()) + delimitor;
        outputline+=getTime() + delimitor;
        outputline+="" + delimitor;//space for Cat1 columns

        setElemOutputLine(datacatun.getBtnElem1(),"1,5");
        setElemOutputLine(datacatun.getBtnElem2(),"2");
        setElemOutputLine(datacatun.getBtnElem3(),"2,5");
        setElemOutputLine(datacatun.getBtnElem4(),"3");
        setElemOutputLine(datacatun.getBtnElem5(),"3,5");
        setElemOutputLine(datacatun.getBtnElem6(),"4");
        setElemOutputLine(datacatun.getBtnElem7(),"4,5");
        setElemOutputLine(datacatun.getBtnElem8(),"5");
        setElemOutputLine(datacatun.getBtnElem9(),"5,5");
        setElemOutputLine(datacatun.getBtnElem10(),"6");
        setElemOutputLine(datacatun.getBtnElem11(),"6,5");
        setElemOutputLine(datacatun.getBtnElem12(),"7");
        setElemOutputLine(datacatun.getBtnElem13(),"7,5");
        setElemOutputLine(datacatun.getBtnElem14(),"8");
        setElemOutputLine(datacatun.getBtnElem15(),"8,5");

        outputline+="" + delimitor; //space for empty column
        outputline+="" + delimitor; //space for Cat2 columns

        setElemOutputLine(datacatdeux.getBtnElem1(),"15");
        setElemOutputLine(datacatdeux.getBtnElem2(),"20");
        setElemOutputLine(datacatdeux.getBtnElem3(),"25");
        setElemOutputLine(datacatdeux.getBtnElem4(),"30");
        setElemOutputLine(datacatdeux.getBtnElem5(),"35");

        outputline+="" + delimitor; //space for empty column
        outputline+="" + delimitor; //space for Cat3 columns

        setElemOutputLine(datacattrois.getBtnElem1(),"150");
        setElemOutputLine(datacattrois.getBtnElem2(),"200");
        setElemOutputLine(datacattrois.getBtnElem3(),"250");
        setElemOutputLine(datacattrois.getBtnElem4(),"300");
        setElemOutputLine(datacattrois.getBtnElem5(),"350");

        return outputline;
    }

    public void cleanOutputLine(){outputline="";}
    public void cleanHeader(){header="";}
    public void clean(){
        cleanOutputLine();
        cleanHeader();
        fullFile.clear();
    }

    public void initDataOutput(){
        header = "";
        outputline = "";
        isRunning = false;
        isSentable = false;
    }

    private String getTime(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            return dateFormat.format(new Date());
    }
    private long getDelay(){
        return System.currentTimeMillis() - initTime;
    }
    private String getTimeHHMMSS(long time){
        int seconds = (int) (time / 1000) % 60 ;
        int minutes = (int) ((time / (1000*60)) % 60);
        int hours   = (int) ((time / (1000*60*60)) % 24);
        return hours + ":" + minutes + ":" + seconds;
    }

    public void setElemHeader(String elemToCheck){
        if(elemToCheck!=null && !"".equals(elemToCheck)){
            header+=elemToCheck + delimitor;
        }
    }

    public void setElemOutputLine(boolean elemToCheck, String weight){
        if(elemToCheck){
            outputline+=weight + delimitor;
        }
    }

}
