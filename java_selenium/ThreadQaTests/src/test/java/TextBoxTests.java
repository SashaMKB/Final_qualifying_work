import Pages.TextBoxPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.Helper;

public class TextBoxTests extends BaseTest {

    private TextBoxPage textBoxPage;

    @BeforeClass
    public void setUp() {
        super.setUp();
        textBoxPage = new TextBoxPage(driver);
        textBoxPage.goToTextBoxPage();
    }

    @Test(dataProvider = "testData")
    public void fillParametersInTextBox(String name, String email, String currentAddress, String permanentAddress) {
        textBoxPage.fillFields(name, email, currentAddress, permanentAddress);
        Assert.assertTrue(textBoxPage.confirmRecord(name, email, currentAddress, permanentAddress));
    }

    @AfterClass
    public void tearDown() {
        super.close();
    }

    @DataProvider(name = "testData")
    public Object[][] generateTestData() {
        Object[][] testData = new Object[10][4];
        for (int i = 0; i < 10; i++) {
            String name = Helper.generateRandomString(10);
            String email = Helper.generateRandomEmail();
            String currentAddress = Helper.generateRandomAddress();
            String permanentAddress = Helper.generateRandomAddress();
            testData[i] = new Object[]{name, email, currentAddress, permanentAddress};
        }
        return testData;
    }
}
