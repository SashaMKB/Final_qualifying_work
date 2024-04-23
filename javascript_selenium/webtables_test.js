const { Builder, By } = require('selenium-webdriver');
const Chance = require('chance');

const chance = new Chance();
const departments = ['IT', 'Finance', 'Marketing', 'HR', 'Operations'];

async function fillWebTable(driver, firstName, lastName, age, email,salary,department) {

    const addNewRecordButton = await driver.findElement(By.id('addNewRecordButton'));
    await addNewRecordButton.click();
   
    const firstNameField = await driver.findElement(By.id('firstName'));
    await firstNameField.sendKeys(firstName);

    const lastNameField = await driver.findElement(By.id('lastName'));
    await lastNameField.sendKeys(lastName);
    
    const ageField = await driver.findElement(By.id('age'));
    await ageField.sendKeys(age);
    
    const emailField = await driver.findElement(By.id('userEmail'));
    await emailField.sendKeys(email);

    const salaryField = await driver.findElement(By.id('salary'));
    await salaryField.sendKeys(salary);

    const departmentField = await driver.findElement(By.id('department'));
    await departmentField.sendKeys(department);

    // Кликаем по кнопке Submit
    const submitButton = await driver.findElement(By.id('submit'));
    await submitButton.click();
}


async function webTablesTest(driver) {
        const firstName = chance.name();
        const lastName = chance.name();
        const age = chance.age();
        const email = chance.email();
        const salary = Math.floor(Math.random() * (5000 - 1000 + 1)) + 1000;
        const department = departments[Math.floor(Math.random() * departments.length)];
        await fillWebTable(driver, firstName, lastName, age, email,salary,department);
        await isWebTablesFilledCorrectly(driver, firstName, lastName, age, email,salary,department);
};

async function isWebTablesFilledCorrectly(driver, firstName, lastName, age, email,salary,department) {
  try {
      // Находим все элементы с ожидаемыми атрибутами
      const firstNameElement = await driver.findElement(By.xpath("//div[text()='"+firstName+"']"));
      const lastNameElement = await driver.findElement(By.xpath("//div[text()='"+lastName+"']"));
      const ageElement = await driver.findElement(By.xpath("//div[text()='"+age+"']"));
      const emailElement = await driver.findElement(By.xpath("//div[text()='"+email+"']"));
      const salaryElement = await driver.findElement(By.xpath("//div[text()='"+salary+"']"));
      const departmentElement = await driver.findElement(By.xpath("//div[text()='"+department+"']"));
      
      // Проверяем, все ли атрибуты заполнены корректно
      if (firstNameElement != null && lastNameElement != null && ageElement != null && emailElement != null && salaryElement != null && departmentElement != null) {
          console.log("All fields are filled correctly.");
          return true;
      } else {
          console.log("One or more fields are not filled correctly:");
          return false;
      }
  } catch (error) {
      console.error("Error occurred while checking web tabls fields:", error);
      return false;
  }
}

async function runExampleMultipleTimes() {
  console.time('Время выполнения метода');

  let driver = await new Builder().forBrowser('chrome').build();
  await driver.manage().setTimeouts({ implicit: 10000 }); 
  await driver.get('http://85.192.34.140:8081/');
  await driver.findElement(By.xpath("//h5[text()='Elements']")).click();
  await driver.findElement(By.xpath("//span[text()='Web Tables']")).click();
  const numberOfTimes = 5;
  for (let i = 0; i < numberOfTimes; i++) {
      console.log(`Executing example ${i + 1}`);
      await webTablesTest(driver);
  }
  driver.quit()

  console.timeEnd('Время выполнения метода');
}

runExampleMultipleTimes();