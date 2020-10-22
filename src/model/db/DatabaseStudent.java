package model.db;

import model.domain.Student;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;

public class DatabaseStudent {
    private ArrayList<Student> students;

    public DatabaseStudent(){
        Student greetje = new Student("Jongen", "Greetje", "Toegepaste Informatica", 23);
        Student kristien = new Student("Melaerts", "Kristien", "Chemie", 21);
        Student elke = new Student("Steegmans", "Elke", "Vroedkunde", 16);
        Student jan = new Student("Van Hee", "Jan", "Verpleegkunde", 18);
        students = new ArrayList<>();
        addToDb(new Student("engels","yago","informatica",19));
        students.add(greetje);
        students.add(kristien);
        students.add(elke);
        students.add(jan);
    }

    public void addToDb(Student student){
        if (student == null)throw new IllegalArgumentException("er is een probleem met de student");
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getleeftijdEnRichting(String naam , String voormaan){
        if (naam.trim().isEmpty() || naam == null)throw new IllegalArgumentException("er is een probleem met de student");
        if (voormaan.trim().isEmpty() || voormaan == null)throw new IllegalArgumentException("er is een probleem met de student");
        String uut = "";
        for (int i = 0 ; i < students.size() ; i++){
            if (students.get(i).getNaam().equals(naam) && students.get(i).getVoornaam().equals(voormaan)){
                uut = students.get(i).leeftijdEnRichting();
            }
        }
        return uut;
    }

    public Student getStudentOnNaam(String naam){
        System.out.println(naam);
        Student student = new Student("null","null","null",5);
        for (int i = 0 ; i < students.size(); i++){
            if (students.get(i).getNaam().equals(naam)){
                student = new Student(students.get(i).getNaam(),students.get(i).getVoornaam(),students.get(i).getStudierichting(),students.get(i).getLeeftijd());
            }
        }
        return student;
    }

    public void deleteDatabase(Student student){
        if (!student.getNaam().equals("null")){
            students.remove(student);
        }
    }
}
