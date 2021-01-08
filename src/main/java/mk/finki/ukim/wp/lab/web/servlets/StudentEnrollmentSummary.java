package mk.finki.ukim.wp.lab.web.servlets;

import mk.finki.ukim.wp.lab.model.Course;
import mk.finki.ukim.wp.lab.model.Grade;
import mk.finki.ukim.wp.lab.service.Impl.CourseServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "studentenrolment",urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {
    private  final SpringTemplateEngine springTemplateEngine;
    private final CourseServiceImpl courseService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseServiceImpl courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String username=req.getParameter("studentUsername");
        Long courseId= Long.parseLong(String.valueOf(req.getSession().getAttribute("courseId")));
        Course course=courseService.addStudentInCourse(username,courseId);
        List<Grade> gradeList=courseService.getStudentGrades(courseId);
//        Course c=courseService.listAll().stream().filter(x->x.getCourseId().equals(courseId)).findFirst().get();
        context.setVariable("studenti",courseService.listStudentsByCourse(courseId));
        context.setVariable("kurs",course);
        context.setVariable("grades",gradeList);
        resp.setContentType("text/html");
        this.springTemplateEngine.process("studentsInCourse.html",context,resp.getWriter());

    }
}
