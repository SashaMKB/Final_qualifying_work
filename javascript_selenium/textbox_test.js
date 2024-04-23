const { Builder, By } = require('selenium-webdriver');
const Chance = require('chance');

const chance = new Chance();

async function fillTextBox(driver, userName, userEmail, currentAddress, permanentAddress) {
    // Вводим имя пользователя
    const userNameField = await driver.findElement(By.id('userName'));
    await userNameField.sendKeys(userName);

    // Вводим email пользователя
    const userEmailField = await driver.findElement(By.id('userEmail'));
    await userEmailField.sendKeys(userEmail);

    // Вводим текущий адрес
    const currentAddressField = await driver.findElement(By.id('currentAddress'));
    await currentAddressField.sendKeys(currentAddress);

    // Вводим постоянный адрес
    const permanentAddressField = await driver.findElement(By.id('permanentAddress'));
    await permanentAddressField.sendKeys(permanentAddress);

    // Кликаем по кнопке Submit
    const submitButton = await driver.findElement(By.id('submit'));
    await submitButton.click();
}

async function clearTextBoxes(driver) {
    // Очищаем поля ввода
    const userNameField = await driver.findElement(By.id('userName'));
    await userNameField.clear();

    const userEmailField = await driver.findElement(By.id('userEmail'));
    await userEmailField.clear();

    const currentAddressField = await driver.findElement(By.id('currentAddress'));
    await currentAddressField.clear();

    const permanentAddressField = await driver.findElement(By.id('permanentAddress'));
    await permanentAddressField.clear();
}

async function textBoxTest(driver) {
        const userName = chance.name();
        const userEmail = chance.email();
        const currentAddress = chance.address();
        const permanentAddress = chance.address();
        await fillTextBox(driver, userName, userEmail, currentAddress, permanentAddress);
        await clearTextBoxes(driver);
        await isTextBoxFilledCorrectly(driver, userName, userEmail, currentAddress, permanentAddress);
    
};

async function isTextBoxFilledCorrectly(driver, expectedUserName, expectedUserEmail, expectedCurrentAddress, expectedPermanentAddress) {
  try {
      // Находим все элементы с ожидаемыми атрибутами
      const nameElement = await driver.findElement(By.id("name"));
      const emailElement = await driver.findElement(By.id("email"));
      const currentAddressElement = await driver.findElement(By.xpath("//p[@id='currentAddress']"));
      const permanentAddressElement = await driver.findElement(By.xpath("//p[@id='permanentAddress']"));
      
      // Получаем текстовое содержимое элементов
      const actualUserName = await nameElement.getText();
      const actualUserEmail = await emailElement.getText();
      const actualCurrentAddress = await currentAddressElement.getText();
      const actualPermanentAddress = await permanentAddressElement.getText();
      
      // Сравниваем фактические значения с ожидаемыми
      const userNameMatch = actualUserName === ("Name:" + expectedUserName);
      const userEmailMatch = actualUserEmail === ("Email:" + expectedUserEmail);
      const currentAddressMatch = actualCurrentAddress ===("Current Address :"+expectedCurrentAddress);
      const permanentAddressMatch = actualPermanentAddress === ("Permananet Address :"+expectedPermanentAddress);
      
      // Проверяем, все ли атрибуты заполнены корректно
      if (userNameMatch && userEmailMatch && currentAddressMatch && permanentAddressMatch) {
          console.log("All fields are filled correctly.");
          return true;
      } else {
          console.log("One or more fields are not filled correctly:");
          if (!userNameMatch) console.log("Expected UserName:", expectedUserName, "Actual:", actualUserName);
          if (!userEmailMatch) console.log("Expected UserEmail:", expectedUserEmail, "Actual:", actualUserEmail);
          if (!currentAddressMatch) console.log("Expected CurrentAddress:", expectedCurrentAddress, "Actual:", actualCurrentAddress);
          if (!permanentAddressMatch) console.log("Expected PermanentAddress:", expectedPermanentAddress, "Actual:", actualPermanentAddress);
          return false;
      }
  } catch (error) {
      console.error("Error occurred while checking text box fields:", error);
      return false;
  }
}

async function runExampleMultipleTimes() {
  console.time('Время выполнения метода');

  let driver = await new Builder().forBrowser('chrome').build();
  await driver.manage().setTimeouts({ implicit: 10000 }); 
  await driver.get('http://85.192.34.140:8081/');
  await driver.findElement(By.xpath("//h5[text()='Elements']")).click();
  await driver.findElement(By.xpath("//span[text()='Text Box']")).click();
  const numberOfTimes = 50;
  for (let i = 0; i < numberOfTimes; i++) {
      console.log(`Executing example ${i + 1}`);
      await textBoxTest(driver);
  }
  driver.quit()

  console.timeEnd('Время выполнения метода');
}

runExampleMultipleTimes();