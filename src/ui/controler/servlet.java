package ui.controler;
import model.db.DatabaseStudent;
import model.domain.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebServlet("/servlet")
public class servlet extends HttpServlet {

    DatabaseStudent databaseStudent = new DatabaseStudent();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        giverOfTasks(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        giverOfTasks(request,response);
    }

    private void giverOfTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String task = "home";
        if (request.getParameter("task") != null){
            task = request.getParameter("task");
        }

        String a = "";
        switch (task){
            case "home":
                a = home(request,response);
                break;
            case "overview":
                a = overview(request,response);
                break;
            case "find":
                a = find(request,response);
                break;
            case "add":
                a = add(request,response);
                break;
            case "delete":
                a = delete(request,response);
                break;
        }
        request.getRequestDispatcher(a).forward(request, response);
    }

    private String home (HttpServletRequest request, HttpServletResponse response){
        return "index.html";
    }
    private String overview (HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("db",databaseStudent.getStudents());
        return "studentInfoOverzicht.jsp";
    }
    private String find (HttpServletRequest request, HttpServletResponse response){
        String naam = request.getParameter("naam");
        String achternaam = request.getParameter("achternaam");

        String abc = databaseStudent.getleeftijdEnRichting(achternaam,naam);
        if (abc.trim().isEmpty()){
            abc = "er is geen informatie over deze student probeer een anderen student";
        }
        request.setAttribute("TheOneString", abc);
        return ("studentGet.jsp");
    }
    private String add(HttpServletRequest request , HttpServletResponse response){
        String voornaam = request.getParameter("naam");
        String achternaam = request.getParameter("achternaam");
        String studie = request.getParameter("studie");
        String leeftijd = request.getParameter("leeftijd");

        Student student = new Student(achternaam,voornaam,studie,Integer.parseInt(leeftijd));
        databaseStudent.addToDb(student);

        request.setAttribute("db",databaseStudent.getStudents());
        return "studentInfoOverzicht.jsp";
    }

    private String delete(HttpServletRequest request, HttpServletResponse response){
        Student student = databaseStudent.getStudentOnNaam(request.getParameter("naam"));
        databaseStudent.deleteDatabase(student);
        request.setAttribute("db",databaseStudent.getStudents());
        String zin = "de student " + student.getNaam() + " is deleted";
        request.setAttribute("zin", zin);
        return "deleted.jsp";
    }
}
