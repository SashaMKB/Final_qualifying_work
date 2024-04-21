package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public class TextBoxPage extends BasePage {

    @FindBy(id = "userName")
    private WebElement name;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(id = "currentAddress")
    private WebElement currentAddress;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddress;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "name")
    private WebElement nameOutput;

    @FindBy(id = "email")
    private WebElement emailOutput;

    @FindBy(xpath = "//p[@id='currentAddress']")
    private WebElement currentAddressOutput;

    @FindBy(xpath = "//p[@id='permanentAddress']")
    private WebElement permanentAddressOutput;

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    public void goToTextBoxPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(PropertyReader.getProperty("base_url"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[text()='Elements']")));
        driver.findElement(By.xpath("//h5[text()='Elements']")).click();
        driver.findElement(By.xpath("//span[text()='Text Box']")).click();
    }

    public void fillFields(String name, String email, String currentAddress, String permanentAddress) {
        this.name.sendKeys(name);
        this.email.sendKeys(email);
        this.currentAddress.sendKeys(currentAddress);
        this.permanentAddress.sendKeys(permanentAddress);
        submitButton.click();
        this.name.clear();
        this.email.clear();
        this.currentAddress.clear();
        this.permanentAddress.clear();
    }


    public boolean confirmRecord(String expectedName, String expectedEmail, String expectedCurrentAddress, String expectedPermanentAddress) {
//        System.out.println("Expected: " + "Name:" + expectedName + " " + "Email:" + expectedEmail + " " + "Current Address :" + "Permananet Address :" + expectedPermanentAddress + " " + expectedPermanentAddress);
//        System.out.println("Received: " + nameOutput.getText() + " " + emailOutput.getText() + " " + currentAddressOutput.getText() + " " + permanentAddressOutput.getText());
        return nameOutput.getText().equals("Name:" + expectedName) &&
                emailOutput.getText().equals("Email:" + expectedEmail) &&
                currentAddressOutput.getText().equals("Current Address :" + expectedCurrentAddress) &&
                permanentAddressOutput.getText().equals("Permananet Address :" + expectedPermanentAddress);
    }
}
