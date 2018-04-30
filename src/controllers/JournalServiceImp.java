package controllers;

import models.Journal;
import models.Mark;
import models.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class JournalServiceImp implements JournalService {
    private Journal journal;
    private static JournalServiceImp instance;

    private JournalServiceImp(Journal journal){
        this.journal=journal;
    }
    public static JournalServiceImp getInstance(Journal journal){
        if(instance==null) instance = new JournalServiceImp(journal);
        if(instance.journal!=journal){
            instance.journal=journal;
        }
        return instance;
    }

    @Override
    public List<Mark> findAllStudentMarks(String firstname,String lastname) {
        List<Mark> studentMarks=new ArrayList<>();
        for (int i=0;i<journal.getMarks().size();i++){
            if (journal.getMarks().get(i).getStudent().getFirstName().equals(firstname)&&
                    journal.getMarks().get(i).getStudent().getLastName().equals(lastname)){
                studentMarks.add( journal.getMarks().get(i));
            }
        }
        return studentMarks;
    }

    @Override
    public List<Mark> findAllStudentsMarks() {
        return journal.getMarks();
    }

    @Override
    public Mark findStudentMarkByDate(String firstname, String lastname, String date) {
        Mark studentMarkByDate=new Mark();
        for (int i=0;i<journal.getMarks().size();i++){
            if (journal.getMarks().get(i).getStudent().getFirstName().equals(firstname)&&
                    journal.getMarks().get(i).getStudent().getLastName().equals(lastname)&&
                        journal.getMarks().get(i).getDate().equals(date)){
                studentMarkByDate=journal.getMarks().get(i);
            }
        }
        return studentMarkByDate;
    }

    @Override
    public void deleteMarkById(int id) {
        for (int i=0;i<journal.getMarks().size();i++){
            if (journal.getMarks().get(i).getId()==id){
                try {
                FileOutputStream fis=new FileOutputStream("loger.txt",true);
                OutputStreamWriter ost=new OutputStreamWriter(fis);
                ost.write(" Удалена оценка "+ journal.getMarks().get(i).getValue() +" для студента "+ journal.getMarks().get(i).getStudent().getFirstName()+" "+ journal.getMarks().get(i).getStudent().getLastName()+". Дата добавления : "+ Calendar.getInstance().getTime());
                ost.flush();
                ost.close();
            }catch (IOException e){
                System.out.println("Логирование не произведено");
            }
                journal.getMarks().remove(journal.getMarks().get(i));

            }
        }

    }

    @Override
    public void addMark(Mark mark) {
        journal.getMarks().add(mark);
        try {
            FileOutputStream fis=new FileOutputStream("loger.txt",true);
            OutputStreamWriter ost=new OutputStreamWriter(fis);
            ost.write(" Добавлена оценка "+mark.getValue() +" для студента "+mark.getStudent().getFirstName()+" "+mark.getStudent().getLastName()+". Дата добавления : "+ Calendar.getInstance().getTime());
            ost.flush();
            ost.close();
        }catch (IOException e){
            System.out.println("Логирование не произведено");
        }

    }

    @Override
    public void ifCanEditMark(int id, Student student, String date, int value) {
        for (int i=0;i<journal.getMarks().size();i++){
            if (journal.getMarks().get(i).getId()==id){
                journal.getMarks().get(i).setDate(date);
                journal.getMarks().get(i).setValue(value);
                journal.getMarks().get(i).setStudent(student);
                try {
                    FileOutputStream fis=new FileOutputStream("loger.txt",true);
                    OutputStreamWriter ost=new OutputStreamWriter(fis);
                    ost.write(" Изменена оценка "+ journal.getMarks().get(i).getValue() +" для студента "+ journal.getMarks().get(i).getStudent().getFirstName()+" "+ journal.getMarks().get(i).getStudent().getLastName()+". Дата добавления : "+ Calendar.getInstance().getTime());
                    ost.flush();
                    ost.close();
                }catch (IOException e){
                    System.out.println("Логирование не произведено");
                }
            }
        }
    }
}
