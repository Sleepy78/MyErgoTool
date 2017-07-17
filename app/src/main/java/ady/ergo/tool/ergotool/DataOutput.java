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
    private boolean isPaused;
    private boolean isSentable;
    private long initTime;
    private long delay;
    String delimitor = ";";

    public String getHeader() {return header;}
    public void setInitTime(long t) {initTime = t;}
    public long getInitTime() {return initTime;}

    public void setDelay(long t) {delay = t;}
    public long getDelay() {return delay;}

    public String getOutputLine() {return outputline;}
    public void setOutputLine(String out) {outputline = out;}

    public boolean isRunning() {return isRunning;}
    public void setIsRunning(boolean run) {isRunning = run;}

    public boolean isPaused() {return isPaused;}
    public void setIsPaused(boolean pause) {isPaused = pause;}

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

        //header+="TimeDdelay in ms" + delimitor;
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

        delay+=getDelayInMs();

        outputline="";
        //outputline+=getDelay() + delimitor;
        outputline+=getTimeHHMMSS(delay) + delimitor;
        outputline+=getTime() + delimitor;
        outputline+="" + delimitor;//space for Cat1 columns

        for(int i=1;i<=15;i++)
        {
            outputline+=((datacatun.getBtnElem(i)) ? String.valueOf(i*0.5 +1).replace(".",",") : "") + delimitor;
        }

        outputline+="" + delimitor; //space for empty column
        outputline+="" + delimitor; //space for Cat2 columns

        for(int i=1;i<=15;i++)
        {
            outputline+=((datacatdeux.getBtnElem(i)) ? (i*5 +10) : "") + delimitor;
        }

        outputline+="" + delimitor; //space for empty column
        outputline+="" + delimitor; //space for Cat3 columns


        for(int i=1;i<=15;i++)
        {
            outputline+=((datacattrois.getBtnElem(i)) ? (i*50 +100) : "") + delimitor;
        }

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
        delay=0;
    }

    public void initDataOutput(){
        header = "";
        outputline = "";
        isRunning = false;
        isPaused = false;
        isSentable = false;
        delay=0;
    }

    private String getTime(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            return dateFormat.format(new Date());
    }

    private long getDelayInMs(){
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

/*    public void setElemOutputLine(boolean elemToCheck, String weight){
        if(elemToCheck){
            outputline+=weight + delimitor;
        }
    }*/

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
