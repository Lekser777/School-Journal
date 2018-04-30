package controllers;

import models.Mark;
import models.Student;

import java.sql.Date;
import java.util.List;

public interface JournalService {
    public List<Mark> findAllStudentsMarks();
    public List<Mark> findAllStudentMarks(String firstname,String lastname);
    public Mark findStudentMarkByDate(String firstname, String lastname, String date);
    public void deleteMarkById(int id);
    public void addMark(Mark mark);
    public void ifCanEditMark(int id, Student student, String date, int value);

}
