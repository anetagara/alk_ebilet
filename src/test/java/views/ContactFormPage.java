package views;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactFormPage {
    private WebDriver driver;
    @FindBy(xpath = "//h1[@class='pt-0 d-none d-sm-block']")
    private WebElement contactForm;

    public ContactFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getContactFormText() {
       return contactForm.getText();
    }
}