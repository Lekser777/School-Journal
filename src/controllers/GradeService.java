package controllers;

import models.Journal;
import models.Student;

import java.util.List;

public interface GradeService {
    public List<Student> findAllStuddents();
    public Student findStudentById(int id);
    public List<Journal> findAllJournal();
    public Journal findJournalBySubject(String subject);
    public Journal findJournalById(int id);
    public void deleteJournalById(int id);
    public void addJournal(Journal journal);
}
