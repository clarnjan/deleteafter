package mk.finki.ukim.wp.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class addCoursePage extends AbstractPage{

    private WebElement name;
    private WebElement description;
    private WebElement manufacturer;
    private WebElement courseType;
    private WebElement submit;

    public addCoursePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static CoursePage addCourse(WebDriver driver, String name, String description, String manufacturer, String courseType){

        get(driver, "/add-form");
        addCoursePage addCoursePage = PageFactory.initElements(driver, addCoursePage.class);

        addCoursePage.name.sendKeys(name);
        addCoursePage.description.sendKeys(description);
        addCoursePage.manufacturer.click();
        addCoursePage.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
        addCoursePage.courseType.click();
        addCoursePage.courseType.findElement(By.xpath("//option[. = '" + courseType + "']")).click();

        System.out.println(driver.getCurrentUrl());

        addCoursePage.submit.click();

        return PageFactory.initElements(driver, CoursePage.class);

    }

}
