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
        DataLieux datalieux = DataLieux.getInstance();
        DataCatDeux datacatdeux = DataCatDeux.getInstance();
        DataCatTrois datacattrois = DataCatTrois.getInstance();
        DataCategory datacat = DataCategory.getInstance();
        String delimitor = ";";
        header="";

        header+="TimeDdelay in ms" + delimitor;
        header+="Time HH:MM:SS" + delimitor;
        header+="Time Absolute" + delimitor;

        //All about Cat1
        header+=datacat.getTextCat1() + delimitor;

        header+=datalieux.getTextL1() + delimitor;
        header+=datalieux.getTextL2() + delimitor;
        header+=datalieux.getTextL3() + delimitor;
        header+=datalieux.getTextL4() + delimitor;
        header+=datalieux.getTextL5() + delimitor;

        header+=" " + delimitor;   //Needed for blank in csv output

        //All about Cat2Activity
        header+=datacat.getTextCat2() + delimitor;

        header+=datacatdeux.getTextElem1() + delimitor;
        header+=datacatdeux.getTextElem2() + delimitor;
        header+=datacatdeux.getTextElem3() + delimitor;
        header+=datacatdeux.getTextElem4() + delimitor;
        header+=datacatdeux.getTextElem5() + delimitor;

        header+=" " + delimitor;   //Needed for blank in csv output

        //All about Cat3Activity
        header+=datacat.getTextCat3() + delimitor;

        header+=datacattrois.getTextElem1() + delimitor;
        header+=datacattrois.getTextElem2() + delimitor;
        header+=datacattrois.getTextElem3() + delimitor;
        header+=datacattrois.getTextElem4() + delimitor;
        header+=datacattrois.getTextElem5() + delimitor;

        header+=" " + delimitor;   //Needed for blank in csv output

        if(!header.contains(delimitor + "null" + delimitor) && !header.contains(delimitor + delimitor)){return true;}
        return false;
    }

    public String updateOutputLine(){
        DataLieux datalieux = DataLieux.getInstance();
        DataCatDeux datacatdeux = DataCatDeux.getInstance();
        DataCatTrois datacattrois = DataCatTrois.getInstance();
        //DataCategory datacat = DataCategory.getInstance();
        String delimitor = ";";
        outputline="";
        outputline+=getDelay() + delimitor;
        outputline+=getTimeHHMMSS(getDelay()) + delimitor;
        outputline+=getTime() + delimitor;
        outputline+="" + delimitor;//space for Cat1 columns

        outputline+=(datalieux.getBtnL1() ? "1,5" : "") + delimitor;
        outputline+=(datalieux.getBtnL2() ? "2" : "") + delimitor;
        outputline+=(datalieux.getBtnL3() ? "2,5" : "") + delimitor;
        outputline+=(datalieux.getBtnL4() ? "3" : "") + delimitor;
        outputline+=(datalieux.getBtnL5() ? "3,5" : "") + delimitor;

        outputline+="" + delimitor; //space for empty column
        outputline+="" + delimitor; //space for Cat2 columns

        outputline+=(datacatdeux.getBtnElem1() ? "15" : "") + delimitor;
        outputline+=(datacatdeux.getBtnElem2() ? "20" : "") + delimitor;
        outputline+=(datacatdeux.getBtnElem3() ? "25" : "") + delimitor;
        outputline+=(datacatdeux.getBtnElem4() ? "30" : "") + delimitor;
        outputline+=(datacatdeux.getBtnElem5() ? "35" : "") + delimitor;

        outputline+="" + delimitor; //space for empty column
        outputline+="" + delimitor; //space for Cat3 columns

        outputline+=(datacattrois.getBtnElem1() ? "150" : "") + delimitor;
        outputline+=(datacattrois.getBtnElem2() ? "200" : "") + delimitor;
        outputline+=(datacattrois.getBtnElem3() ? "250" : "") + delimitor;
        outputline+=(datacattrois.getBtnElem4() ? "300" : "") + delimitor;
        outputline+=(datacattrois.getBtnElem5() ? "350" : "") + delimitor;

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

}
