package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public class WebTablesPage extends BasePage {

    @FindBy(id = "addNewRecordButton")
    private WebElement addNewRecordButton;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "age")
    private WebElement age;

    @FindBy(id = "salary")
    private WebElement salary;

    @FindBy(id = "department")
    private WebElement department;

    @FindBy(id = "submit")
    private WebElement submit;


    public WebTablesPage(WebDriver driver) {
        super(driver);
    }

    public void goToWebTablesPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(PropertyReader.getProperty("base_url"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[text()='Elements']")));
        driver.findElement(By.xpath("//h5[text()='Elements']")).click();
        driver.findElement(By.xpath("//span[text()='Web Tables']")).click();
        Select select = new Select(driver.findElement(By.xpath("//select[@aria-label='rows per page']")));
        select.selectByValue("100");
    }

    public void addHumanData(String firstName, String lastName, String userEmail, String age, String salary, String department) {
        addNewRecordButton.click();
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.userEmail.sendKeys(userEmail);
        this.age.sendKeys(age);
        this.salary.sendKeys(salary);
        this.department.sendKeys(department);
        submit.click();
    }

    public boolean confirmRecord(String firstName, String lastName, String userEmail, String age, String salary, String department) {
        return driver.findElement(By.xpath("//div[text()='" + firstName + "']")) != null &&
                driver.findElement(By.xpath("//div[text()='" + lastName + "']")) != null &&
                driver.findElement(By.xpath("//div[text()='" + userEmail + "']")) != null &&
                driver.findElement(By.xpath("//div[text()='" + age + "']")) != null &&
                driver.findElement(By.xpath("//div[text()='" + salary + "']")) != null &&
                driver.findElement(By.xpath("//div[text()='" + department + "']")) != null;
    }

}
