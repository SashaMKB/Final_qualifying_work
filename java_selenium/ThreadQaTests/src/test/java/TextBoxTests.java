import Pages.TextBoxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.PropertyReader;

import java.time.Duration;

public class TextBoxTests extends BaseTest {

    TextBoxPage page;


    @Test
    public void fillParametersInTextBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(PropertyReader.getProperty("base_url"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[text()='Elements']")));
        driver.findElement(By.xpath("//h5[text()='Elements']")).click();
        sleep(10000);
    }
}
