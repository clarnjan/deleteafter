package mk.finki.ukim.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CoursePage extends AbstractPage{

    @FindBy(css = ".delete-button")
    private List<WebElement> deleteButtons;

    @FindBy(css = ".edit-button")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-button")
    private List<WebElement> addButton;

    public CoursePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static CoursePage to(WebDriver webDriver){

        get(webDriver, "/courses");
        return PageFactory.initElements(webDriver, CoursePage.class);

    }

    public void assertElements(int numberOfDeleteButtons, int numberOfEditButtons, int numberOfAddButtons){

        Assert.assertEquals("delete buttons do not match!", numberOfDeleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit buttons do not match!", numberOfEditButtons, this.getEditButtons().size());
        Assert.assertEquals("add button is visible!", numberOfAddButtons, this.getAddButton().size());

    }



}
