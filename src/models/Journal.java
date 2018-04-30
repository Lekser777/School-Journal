package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Journal implements Serializable {
    private int id;
    private String subject;
    private List<Mark> marks;
    public Journal(){
        marks=new ArrayList<>();
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
    public String toRealString(){
        return "Id журнала = "+id+" по предмету: "+subject;
    }
}
