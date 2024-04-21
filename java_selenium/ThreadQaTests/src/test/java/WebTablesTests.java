import Pages.WebTablesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.Helper;

public class WebTablesTests extends BaseTest {

    private WebTablesPage webTablesPage;

    @BeforeClass
    public void setUp() {
        super.setUp();
        webTablesPage = new WebTablesPage(driver);
        webTablesPage.goToWebTablesPage();
    }

    @Test(dataProvider = "testData")
    public void fillNewNuman(String firstName, String lastName, String userEmail, String age, String salary, String department) {
        webTablesPage.addHumanData(firstName, lastName, userEmail, age, salary, department);
        Assert.assertTrue(webTablesPage.confirmRecord(firstName, lastName, userEmail, age, salary, department));
    }

    @AfterClass
    public void tearDown() {
        super.close();
    }

    @DataProvider(name = "testData")
    public Object[][] generateTestData() {
        Object[][] testData = new Object[50][6];
        for (int i = 0; i < 50; i++) {
            String firstName = Helper.generateRandomString(8);
            String lastName = Helper.generateRandomString(12);
            String userEmail = Helper.generateRandomEmail();
            String age = String.valueOf(Helper.generateRandomNumber(2));
            String salary = String.valueOf(Helper.generateRandomNumber(4));
            String department = Helper.generateRandomString(20);
            testData[i] = new Object[]{firstName, lastName, userEmail, age, salary, department};
        }
        return testData;
    }

}
