package view;

import controllers.*;
import models.Journal;
import models.Mark;
import models.Student;

import java.util.*;

public class Test {
    private static void showAllJournals(List<Journal> journals){
        for (Journal journal : journals) {
            System.out.println(journal.toRealString());
        }
    }
    private static void showAllMarks(List<Mark> marks){
        for (Mark mark : marks) {
            System.out.println(mark.toRealString());
        }

    }
    private static void showAllStudents(List<Student> students){
        for (Student student : students) {
            System.out.println(student.toRealString());
        }

    }
    private static void consoleBranch(GradeService gs){
        Scanner sc1=new Scanner(System.in);
        int n=-1;
        IdGenerator ids=IdGenerator.getInstance();
        while (n != 0) {
            System.out.println("------------------------------------------------");
            System.out.println("Выберете действие :");
            System.out.println("1 - Вывести всех студентов");
            System.out.println("2 - Вывести все журналы");
            System.out.println("3 - Показать журнал по предмету");
            System.out.println("4 - Добавить журнал");
            System.out.println("5 - Удалить журнал");
            System.out.println("6 - Начать работу с журналом");
            System.out.println("0 - Назад");
            System.out.println("------------------------------------------------");
            n = sc1.nextInt();
            switch (n) {
                case 1:
                    List<Student> students = gs.findAllStuddents();
                    showAllStudents(students);
                    break;
                case 2:
                    List<Journal> journals = gs.findAllJournal();
                    showAllJournals(journals);
                    break;
                case 3:
                    String subject = sc1.next();
                    Journal journal = gs.findJournalBySubject(subject);
                    System.out.println(journal.toRealString());
                    break;
                case 4:
                    Journal journalToAdd = new Journal();
                    journalToAdd.setId(ids.getJournal_ID());
                    System.out.println("Введите для какого предмета создается новый журнал");
                    journalToAdd.setSubject(sc1.next());
                    gs.addJournal(journalToAdd);
                    break;
                case 5:
                    List<Journal> journalsa = gs.findAllJournal();
                    showAllJournals(journalsa);
                    System.out.println("Введите ID журнала для удаления");
                    gs.deleteJournalById(sc1.nextInt());
                    break;
                case 6:
                    List<Journal> journalsb = gs.findAllJournal();
                    showAllJournals(journalsb);
                    System.out.println("Введите ID журнала для редактирования");
                    int n1 = sc1.nextInt();
                    JournalService js = JournalServiceImp.getInstance(gs.findJournalById(n1));
                    System.out.println("Вы выбрали " + gs.findJournalById(n1).toRealString());
                    int n2 = -1;
                    while (n2 != 0) {
                        System.out.println("------------------------------------------------");
                        System.out.println("Выберете действие :");
                        System.out.println("1 - Вывести все оценки из данного журнала");
                        System.out.println("2 - Вевести все оценки определенного студента из данного журнала");
                        System.out.println("3 - Вывести оценку студента на определенное число из данного журнала");
                        System.out.println("4 - Добавить оценку в данный журнал");
                        System.out.println("5 - Удалить оценку из данного журнала");
                        System.out.println("6 - Редактировать оценку из данного журнала");
                        System.out.println("0 - Назад ");
                        System.out.println("------------------------------------------------");
                        n2 = sc1.nextInt();
                        switch (n2) {
                            case 1:
                                List<Mark> marks = js.findAllStudentsMarks();
                                showAllMarks(marks);
                                break;
                            case 2:
                                System.out.println("Введите имя студента ");
                                String fn = sc1.next();
                                System.out.println("Введите фамилию студента ");
                                String ln = sc1.next();
                                List<Mark> marksOfStudent = js.findAllStudentMarks(fn, ln);
                                showAllMarks(marksOfStudent);
                                break;
                            case 3:
                                System.out.println("Введите имя студента ");
                                String fn1 = sc1.next();
                                System.out.println("Введите фамилию студента ");
                                String ln1 = sc1.next();
                                System.out.println("Введите дату ");
                                String date = sc1.next();
                                System.out.println(js.findStudentMarkByDate(fn1, ln1, date).toRealString());
                                break;
                            case 4:
                                showAllStudents(gs.findAllStuddents());
                                System.out.println("Введите Id студента, которому хотите добавить оценку ");
                                Student student = gs.findStudentById(sc1.nextInt());
                                int markid = ids.getMark_ID();
                                System.out.println("Введите дату оценки");
                                String date1 = sc1.next();
                                System.out.println("Введите саму оценку");
                                int value = sc1.nextInt();
                                Mark markToAdd = new Mark();
                                markToAdd.setStudent(student);
                                markToAdd.setId(markid);
                                markToAdd.setDate(date1);
                                markToAdd.setValue(value);
                                js.addMark(markToAdd);
                                break;
                            case 5:
                                showAllMarks(js.findAllStudentsMarks());
                                System.out.println("Введите Id оценки для удаления");
                                js.deleteMarkById(sc1.nextInt());
                                break;
                            case 6:
                                showAllMarks(js.findAllStudentsMarks());
                                System.out.println("Введите Id оценки для редактирования");
                                int markid1 = sc1.nextInt();
                                showAllStudents(gs.findAllStuddents());
                                System.out.println("Введите Id студента, которому хотите присвоить оценку ");
                                Student student1 = gs.findStudentById(sc1.nextInt());
                                System.out.println("Введите дату оценки");
                                String date11 = sc1.next();
                                System.out.println("Введите саму оценку");
                                int value1 = sc1.nextInt();
                                js.ifCanEditMark(markid1, student1, date11, value1);
                                break;
                            case 0:
                                break;
                        }
                    }
                    break;
                case 0:
                    ids.saveChange();
                    break;
            }

        }
    }
    public static void main(String[]args){

        FileManager fm=FileManager.getInstance();
        fm.loadAll();

        System.out.println("------------------------------------------------");
        System.out.println("Добро пожаловать.");
        Scanner sc=new Scanner(System.in);
        try {
        int n=-1;
        GradeService gs;
        while (n!=0) {
            System.out.println("Выберете класс с которым вы хотите начать работу");
            System.out.println("------------------------------------------------");
            System.out.println("1 - 10А");
            System.out.println("2 - 10Б");
            System.out.println("0 - Завершить работу");
            System.out.println("------------------------------------------------");
            n = sc.nextInt();
            switch (n) {
                case 1:
                    gs = GradeServiceImp.getInstance(fm.getGrades().get(0));
                    System.out.println("Вы выбрали 10A класс");
                    consoleBranch(gs);
                    break;
                case 2:
                    gs = GradeServiceImp.getInstance(fm.getGrades().get(1));
                    System.out.println("Вы выбрали 10Б класс");
                    consoleBranch(gs);
                    break;
                case 0:
                    fm.saveAll(fm.getGrades());
                    break;
                default:
                    System.out.println("Вы не сделали выбор. Система автоматически продолжает работу с 10A классом");
                    gs = GradeServiceImp.getInstance(fm.getGrades().get(0));
                    consoleBranch(gs);
                    break;
            }
        }


        }catch (InputMismatchException e){
            System.out.println("Вы вводите данные не верно");
        }
    }


}
