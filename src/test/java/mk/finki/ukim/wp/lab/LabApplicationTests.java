package mk.finki.ukim.wp.lab;

import mk.finki.ukim.wp.lab.service.Impl.CourseServiceImpl;
import mk.finki.ukim.wp.lab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {

    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    CourseServiceImpl courseService;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        initData();
    }

    private void initData(){
        if (!dataInitialized){

            String user = "user";
            String admin = "admin";

            userService.register(user, "password", "password");
            userService.register(admin, "password", "password");

            dataInitialized = true;

        }
    }


    @Test
    public void getCourses() throws Exception {
        MockHttpServletRequestBuilder courseRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(courseRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("listCourses"));

    }

    @Test
    void contextLoads() {
    }

}

