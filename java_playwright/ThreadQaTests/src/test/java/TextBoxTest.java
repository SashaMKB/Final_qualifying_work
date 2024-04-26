import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextBoxTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    Faker faker = new Faker();

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        page = context.newPage();
        page.navigate("http://85.192.34.140:8081/");
        page.click("//h5[text()='Elements']");
        // Кликаем по элементу Text Box
        page.click("//span[text()='Text Box']");
    }

    @Test(invocationCount = 50)
    public void testTextBox() {

        // Заполняем поля случайными значениями
        String userNameValue = faker.name().username();
        String emailValue = faker.internet().emailAddress();
        String currentAddressValue = faker.address().fullAddress();
        String permanentAddressValue = faker.address().fullAddress();

        // Заполняем поля на странице
        page.fill("#userName", userNameValue);
        page.fill("#userEmail", emailValue);
        page.fill("#currentAddress", currentAddressValue);
        page.fill("#permanentAddress", permanentAddressValue);

        // Нажимаем кнопку submit
        page.click("#submit");

        // Очищаем поля
        page.fill("#userName", "");
        page.fill("#userEmail", "");
        page.fill("#currentAddress", "");
        page.fill("#permanentAddress", "");

        checkElementText(page, "#name", "Name:" + userNameValue);
        checkElementText(page, "#email", "Email:" + emailValue);
        checkElementText(page, "xpath=//p[@id='currentAddress']", "Current Address :" + currentAddressValue);
        checkElementText(page, "xpath=//p[@id='permanentAddress']", "Permananet Address :" + permanentAddressValue);
    }

    @AfterClass
    public void tearDown() {
        // Закрываем браузер
        browser.close();
        playwright.close();
    }

    public void checkElementText(Page page, String selector, String expectedText) {
        // Получаем текст из элемента
        String elementText = page.innerText(selector);

        // Проверяем, содержит ли элемент ожидаемый текст
        if (elementText.contains(expectedText)) {
            System.out.println("Элемент " + selector + " содержит ожидаемый текст: " + expectedText);
        } else {
            System.out.println("Ошибка: Элемент " + selector + " содержит текст: " + elementText + ", ожидалось: " + expectedText);
        }
    }
}
