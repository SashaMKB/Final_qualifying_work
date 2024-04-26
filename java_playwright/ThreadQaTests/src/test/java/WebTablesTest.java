import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WebTablesTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    Faker faker = new Faker();
    Random random = new Random();

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        page = context.newPage();
        page.navigate("http://85.192.34.140:8081/");
        page.click("//h5[text()='Elements']");
        // Кликаем по элементу Text Box
        page.click("//span[text()='Web Tables']");
        page.selectOption("select[aria-label='rows per page']", "100");
    }

    @Test(invocationCount = 5)
    public void testWebTables() {

        page.click("#addNewRecordButton");

        // Заполняем поля случайными значениями
        String firstNameValue = faker.name().firstName();
        String secondNameValue = faker.name().lastName();
        String emailValue = faker.internet().emailAddress();
        String ageValue = String.valueOf(random.nextInt(75 - 20 + 1) + 20);
        String salaryValue = String.valueOf(random.nextInt(10000 - 2000 + 1) + 2000);
        String departmentValue = faker.job().position();

        // Заполняем поля на странице
        page.fill("#firstName", firstNameValue);
        page.fill("#lastName", secondNameValue);
        page.fill("#userEmail", emailValue);
        page.fill("#age", ageValue);
        page.fill("#salary", salaryValue);
        page.fill("#department", departmentValue);

        // Нажимаем кнопку submit
        page.click("#submit");
        List<String> elementsToCheck = new ArrayList<>();
        elementsToCheck.add(firstNameValue);
        elementsToCheck.add(secondNameValue);
        elementsToCheck.add(emailValue);
        elementsToCheck.add(ageValue);
        elementsToCheck.add(salaryValue);
        elementsToCheck.add(departmentValue);

        for (String element : elementsToCheck) {
            boolean isElementPresent = checkElementPresenceByXPath(page, "//div[text()='" + element + "']");
            if (isElementPresent) {
                System.out.println("Элемент " + element + " присутствует в таблице");
            } else {
                System.out.println("Элемент " + element + " отсутствует в таблице");
            }
        }
    }

    @AfterClass
    public void tearDown() {
        // Закрываем браузер
        browser.close();
        playwright.close();
    }

    public boolean checkElementPresenceByXPath(Page page, String xpathExpression) {
        // Ожидаем появления элемента с указанным XPath-выражением
        page.waitForSelector("xpath=" + xpathExpression, new Page.WaitForSelectorOptions().setTimeout(5000));

        // Если элемент найден, возвращаем true
        return true;
    }


}
