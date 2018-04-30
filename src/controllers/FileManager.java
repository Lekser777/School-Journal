package controllers;

import models.Grade;
import models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private List<Grade> grades;
    private static FileManager instance;

    private FileManager(){
        grades=new ArrayList<>();
    }
    public static FileManager getInstance(){
        if(instance==null){
            instance=new FileManager();
        }
        return instance;

    }

    private void fillWithStarterItems(){
        List<Student>students1=new ArrayList<>();

        Student s1=new Student();
        s1.setId(1);
        s1.setFirstName("Ivan");
        s1.setLastName("Ivanov");

        Student s2=new Student();
        s2.setId(2);
        s2.setFirstName("Vladimir");
        s2.setLastName("Ivanov");

        Student s3=new Student();
        s3.setId(3);
        s3.setFirstName("Genadii");
        s3.setLastName("Sidorov");

        Student s4=new Student();
        s4.setId(4);
        s4.setFirstName("Pavel");
        s4.setLastName("Gankov");

        students1.add(s1);
        students1.add(s2);
        students1.add(s3);
        students1.add(s4);

        List<Student>students2=new ArrayList<>();

        Student s11=new Student();
        s11.setId(5);
        s11.setFirstName("Petr");
        s11.setLastName("Petrov");

        Student s12=new Student();
        s12.setId(6);
        s12.setFirstName("Masria");
        s12.setLastName("Ivanova");

        Student s13=new Student();
        s13.setId(7);
        s13.setFirstName("Ekaterina");
        s13.setLastName("Stepanova");

        Student s14=new Student();
        s14.setId(8);
        s14.setFirstName("Alexandr");
        s14.setLastName("Volskii");

        students2.add(s11);
        students2.add(s12);
        students2.add(s13);
        students2.add(s14);

        Grade g1=new Grade();
        g1.setId("10А");
        g1.setStudents(students1);

        Grade g2=new Grade();
        g2.setId("10Б");
        g2.setStudents(students2);

        grades.add(g1);
        grades.add(g2);

        saveAll(grades);


    }
    private void load(){
        try {
            FileInputStream fis =new FileInputStream("grades.txt");
            ObjectInputStream ois=new ObjectInputStream(fis);
            while (fis.available()>0) {
                Grade grade=(Grade) ois.readObject();
                grades.add(grade);
            }
            /*
            for(int i=0;i<grades.size();i++){
                System.out.println(grades.get(i).getId());
            }*/

            System.out.println("Успешное чтение из файла");
            ois.close();


        }catch (Exception e){
            System.out.println("Ошибка при чтении из файла");
        }

    }
    private boolean isEmpty(){
        boolean isempty=true;
        try {
            FileInputStream fis=new FileInputStream("grades.txt");
            if(fis.available()>0) {
                isempty = false;
            }
        }catch (IOException e){
            System.out.println("Ошибка чтения");

        }
       return isempty;
    }
    public void loadAll(){
        if(isEmpty()){
            fillWithStarterItems();
            load();
        }else{
            load();
        }
    }
    public void saveAll(List<Grade>grades){
        try {
            FileOutputStream fos =new FileOutputStream("grades.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            for (Grade grade : grades) {
                oos.writeObject(grade);
            }
            oos.flush();
            oos.close();
            System.out.println("Запись завершена");

        }catch (IOException e){
            System.out.println("Ошибка при записи в файл");
        }

    }
    public List<Grade> getGrades(){
        return grades;
    }
}
