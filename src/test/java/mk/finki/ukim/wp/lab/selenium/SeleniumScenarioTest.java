package mk.finki.ukim.wp.lab.selenium;

import mk.finki.ukim.wp.lab.model.Course;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.service.Impl.CourseServiceImpl;
import mk.finki.ukim.wp.lab.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;
    @Autowired
    CourseServiceImpl courseService;

    private HtmlUnitDriver driver;

    private static User regularUser;
    private static User adminUser;
    private static boolean dataInitialized = false;
    private static Course b1;
    private static Course b2;


    @BeforeEach
    private void setup(){

        this.driver = new HtmlUnitDriver(true);
        initData();

    }

    @AfterEach
    public void destroy(){
        if (this.driver != null){
            driver.close();
        }
    }


    private void initData(){
        if (!dataInitialized){
//            b1 = courseService.save("1","b1","b1").get();

            regularUser = userService.register("katerina", "katerina", "katerina");
            adminUser = userService.register("admin", "admin", "admin");

            dataInitialized = true;

        }
    }

    @Test
    public void testScenario(){

        CoursePage coursePage = CoursePage.to(this.driver);
        coursePage.assertElements(0, 0, 0);

        LoginPage loginPage = LoginPage.openLogin(this.driver);

        coursePage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");
        coursePage.assertElements(2, 2, 1);

    }

    @Test
    public void testAddingNewCourse(){

        LoginPage loginPage = LoginPage.openLogin(this.driver);

        CoursePage coursePage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");

        coursePage.assertElements(2, 2, 1);

//        coursePage = addCoursePage.addCourse(driver, "b3", "b3", m2.getName(), TYPE.OVAL.name());

        coursePage.assertElements(3, 3, 1);

        System.out.println(driver.getCurrentUrl());

    }


}
