package controllers;

import java.io.*;

public class IdGenerator {
    private int journal_ID;
    private int mark_ID;
    private static IdGenerator instance;

    private IdGenerator(){
        try{
            String fromfile="";
            FileInputStream fis=new FileInputStream("id's.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            while(fis.available()>0){
               fromfile=br.readLine();
            }
            System.out.println(fromfile);
           journal_ID=Integer.parseInt(fromfile.split("/")[0]);
           mark_ID=Integer.parseInt(fromfile.split("/")[1]);
           br.close();
        }catch (IOException e){
            System.out.println("Чтение файла не произошло");

        }

    }
    public static IdGenerator getInstance(){
        if(instance==null){
            instance=new IdGenerator();
        }
        return instance;

    }
    public void saveChange(){
        try{

            FileOutputStream fos=new FileOutputStream("id's.txt");
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(journal_ID+"/"+mark_ID);
            bw.close();
        }catch (IOException e){
            System.out.println("Запись файла не произошла");

        }

    }

    public int getJournal_ID() {
        this.journal_ID++;
        return journal_ID;
    }

    public int getMark_ID() {
        this.mark_ID++;
        return mark_ID;
    }
}
