package mk.finki.ukim.wp.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage extends AbstractPage{

    private WebElement username;
    private WebElement password;
    private WebElement submit;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static LoginPage openLogin(WebDriver driver){
        get(driver, "/login");

        System.out.println(driver.getCurrentUrl());
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

        return PageFactory.initElements(driver, LoginPage.class);
    }

    public static CoursePage doLogin(WebDriver driver, LoginPage loginPage, String username, String password){

        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);

        loginPage.submit.click();
        System.out.println(driver.getCurrentUrl());

        CoursePage coursePage = PageFactory.initElements(driver, CoursePage.class);

        return PageFactory.initElements(driver, CoursePage.class);

    }

    public static LoginPage logout(WebDriver driver){
        get(driver, "/logout");
        return PageFactory.initElements(driver, LoginPage.class);
    }


}
