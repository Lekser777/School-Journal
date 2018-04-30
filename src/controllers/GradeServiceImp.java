package controllers;

import models.Grade;
import models.Journal;
import models.Student;
import java.util.List;

public class GradeServiceImp implements GradeService {
    private Grade grade;
    private static GradeServiceImp instance;

    private GradeServiceImp(Grade grade){
        this.grade=grade;
    }
    public static GradeServiceImp getInstance(Grade grade){
        if(instance==null){
            instance=new GradeServiceImp(grade);
        }
        if(instance.grade!=grade){
            instance.grade=grade;
        }
        return instance;
    }



    @Override
    public List<Student> findAllStuddents() {
        return grade.getStudents();
    }

    @Override
    public Student findStudentById(int id) {
        Student studentById=new Student();
        for (int i=0;i<grade.getStudents().size();i++){
            if (grade.getStudents().get(i).getId()==id){
                studentById=grade.getStudents().get(i);
            }
        }
        return studentById;
    }


    @Override
    public List<Journal> findAllJournal() {
        return grade.getJournals();
    }

    @Override
    public Journal findJournalBySubject(String subject) {
        Journal journalBySubject=new Journal();
        for (int i=0;i<grade.getJournals().size();i++){
            if (grade.getJournals().get(i).getSubject().equals(subject)){
                journalBySubject=grade.getJournals().get(i);
            }
        }
        return journalBySubject;
    }

    @Override
    public Journal findJournalById(int id) {
        Journal journalById=new Journal();
        for (int i=0;i<grade.getJournals().size();i++){
            if (grade.getJournals().get(i).getId()==id){
                journalById=grade.getJournals().get(i);
            }
        }
        return journalById;
    }

    @Override
    public void deleteJournalById(int id) {
        for (int i=0;i<grade.getJournals().size();i++){
            if (grade.getJournals().get(i).getId()==id){
                grade.getJournals().remove(grade.getJournals().get(i));
            }
        }
    }

    @Override
    public void addJournal(Journal journal) {
        grade.getJournals().add(journal);
    }
}
