package models;

import java.io.Serializable;

public class Mark implements Serializable {
    private int id;
    private Student student;
    private String date;
    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public String toRealString(){
        return "Id :"+id+" студент "+student.getFirstName()+" "+student.getLastName()+" оценка : "+value+" дата : "+date;
    }
}
