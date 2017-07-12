package ady.ergo.tool.ergotool;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

public class DataOutput {
    private String header;
    private String outputline;

    private ArrayList<String> fullFile;
    private boolean isRunning;
    private boolean isSentable;
    private long initTime;
    String delimitor = ";";

    public String getHeader() {return header;}
    public void setInitTime(long t) {initTime = t;}
    public long getInitTime() {return initTime;}

    public String getOutputLine() {return outputline;}
    public void setOutputLine(String out) {outputline = out;}

    public boolean isRunning() {return isRunning;}
    public void setIsRunning(boolean run) {isRunning = run;}

    public boolean isSentable() {return isSentable;}
    public void setIsSentable(boolean sentable) {isSentable = sentable;}

    public ArrayList<String> getFullFile() {return fullFile;}
    public void setFullFile(ArrayList<String> list) {
        this.fullFile = list;
    }

    public void addFullFile(String line) {fullFile.add(line);}

    private static final DataOutput holder = new DataOutput();
    public static DataOutput getInstance() {return holder;}
    public DataOutput(){
        fullFile = new ArrayList<String>();
    }

    private boolean updateHeader(){
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

        for(int i=1 ; i<=15 ; i++){
            setElemHeader(datacatun.getTextElem(i));
        }

        header+=" " + delimitor;   //Needed for blank in csv output

        //All about Cat2Activity
        header+=datacat.getTextCat2() + delimitor;

        for(int i=1 ; i<=15 ; i++){
            setElemHeader(datacatdeux.getTextElem(i));
        }

        header+=" " + delimitor;   //Needed for blank in csv output

        //All about Cat3Activity
        header+=datacat.getTextCat3() + delimitor;

        for(int i=1 ; i<=15 ; i++){
            setElemHeader(datacattrois.getTextElem(i));
        }

        header+=" " + delimitor;   //Needed for blank in csv output

        return true;
    }

    public String updateOutputLine(){
        DataCatUn datacatun = DataCatUn.getInstance();
        DataCatDeux datacatdeux = DataCatDeux.getInstance();
        DataCatTrois datacattrois = DataCatTrois.getInstance();

        outputline="";
        outputline+=getDelay() + delimitor;
        outputline+=getTimeHHMMSS(getDelay()) + delimitor;
        outputline+=getTime() + delimitor;
        outputline+="" + delimitor;//space for Cat1 columns

        for(int i=1;i<=15;i++)
        {
            outputline+=((datacatun.getBtnElem(i)) ? String.valueOf(i*0.5 +1).replace(".",",") : "") + delimitor;
        }

        /*setElemOutputLine(datacatun.getBtnElem(1),"1,5");
        setElemOutputLine(datacatun.getBtnElem(2),"2");
        setElemOutputLine(datacatun.getBtnElem(3),"2,5");
        setElemOutputLine(datacatun.getBtnElem(4),"3");
        setElemOutputLine(datacatun.getBtnElem(5),"3,5");
        setElemOutputLine(datacatun.getBtnElem(6),"4");
        setElemOutputLine(datacatun.getBtnElem(7),"4,5");
        setElemOutputLine(datacatun.getBtnElem(8),"5");
        setElemOutputLine(datacatun.getBtnElem(9),"5,5");
        setElemOutputLine(datacatun.getBtnElem(10),"6");
        setElemOutputLine(datacatun.getBtnElem(11),"6,5");
        setElemOutputLine(datacatun.getBtnElem(12),"7");
        setElemOutputLine(datacatun.getBtnElem(13),"7,5");
        setElemOutputLine(datacatun.getBtnElem(14),"8");
        setElemOutputLine(datacatun.getBtnElem(15),"8,5");*/

        outputline+="" + delimitor; //space for empty column
        outputline+="" + delimitor; //space for Cat2 columns

        for(int i=1;i<=15;i++)
        {
            outputline+=((datacatdeux.getBtnElem(i)) ? (i*5 +10) : "") + delimitor;
        }

        /*setElemOutputLine(datacatdeux.getBtnElem(1),"15");
        setElemOutputLine(datacatdeux.getBtnElem(2),"20");
        setElemOutputLine(datacatdeux.getBtnElem(3),"25");
        setElemOutputLine(datacatdeux.getBtnElem(4),"30");
        setElemOutputLine(datacatdeux.getBtnElem(5),"35");
        setElemOutputLine(datacatdeux.getBtnElem(6),"40");
        setElemOutputLine(datacatdeux.getBtnElem(7),"45");
        setElemOutputLine(datacatdeux.getBtnElem(8),"50");
        setElemOutputLine(datacatdeux.getBtnElem(9),"55");
        setElemOutputLine(datacatdeux.getBtnElem(10),"60");
        setElemOutputLine(datacatdeux.getBtnElem(11),"65");
        setElemOutputLine(datacatdeux.getBtnElem(12),"70");
        setElemOutputLine(datacatdeux.getBtnElem(13),"75");
        setElemOutputLine(datacatdeux.getBtnElem(14),"80");
        setElemOutputLine(datacatdeux.getBtnElem(15),"85");*/

        outputline+="" + delimitor; //space for empty column
        outputline+="" + delimitor; //space for Cat3 columns


        for(int i=1;i<=15;i++)
        {
            outputline+=((datacattrois.getBtnElem(i)) ? (i*50 +100) : "") + delimitor;
        }
        /*setElemOutputLine(datacattrois.getBtnElem(1),"150");
        setElemOutputLine(datacattrois.getBtnElem(2),"200");
        setElemOutputLine(datacattrois.getBtnElem(3),"250");
        setElemOutputLine(datacattrois.getBtnElem(4),"300");
        setElemOutputLine(datacattrois.getBtnElem(5),"350");
        setElemOutputLine(datacattrois.getBtnElem(6),"400");
        setElemOutputLine(datacattrois.getBtnElem(7),"450");
        setElemOutputLine(datacattrois.getBtnElem(8),"500");
        setElemOutputLine(datacattrois.getBtnElem(9),"550");
        setElemOutputLine(datacattrois.getBtnElem(10),"600");
        setElemOutputLine(datacattrois.getBtnElem(11),"650");
        setElemOutputLine(datacattrois.getBtnElem(12),"700");
        setElemOutputLine(datacattrois.getBtnElem(13),"750");
        setElemOutputLine(datacattrois.getBtnElem(14),"800");
        setElemOutputLine(datacattrois.getBtnElem(15),"850");*/

        return outputline;
    }

    public void cleanOutputLine(){outputline="";}
    public void cleanHeader(){header="";}
    public void clean(){
        cleanOutputLine();
        cleanHeader();
        if (!fullFile.isEmpty()) {
            fullFile.clear();
        }
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
        //if(elemToCheck!=null && !"".equals(elemToCheck)){
            header+=elemToCheck + delimitor;
        //}
    }

    public void setElemOutputLine(boolean elemToCheck, String weight){
        if(elemToCheck){
            outputline+=weight + delimitor;
        }
    }

    public boolean writeFile(Activity act) throws IOException{
        String FILENAME = "ErgoTool.csv";
        String outputLine = updateOutputLine();                  //update outputline one last time
        addFullFile(outputLine);             //and add to fullfile
        cleanOutputLine();
        updateHeader();
        String fulltext = getHeader() + "\n";


        for (int i = 0; i < fullFile.size(); i++) {
            fulltext += fullFile.get(i);
            fulltext += "\n";
        }

        FileOutputStream fos = null;
        try {
            fos = act.getApplicationContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(fulltext.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public String serialize(ArrayList<String> list){
        StringBuilder sb = new StringBuilder();
        for (String s : list)
        {
            sb.append(s);
            sb.append("\t");
        }
        return sb.toString();
    }

    public ArrayList deSerialize(String list){
        ArrayList<String> output = new ArrayList<String>(Arrays.asList(list.split(",")));
        return output;
    }
}
