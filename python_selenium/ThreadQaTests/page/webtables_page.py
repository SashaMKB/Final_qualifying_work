from selenium.common import NoSuchElementException
from selenium.webdriver.common.by import By

from page.base_page import BasePage


class WebTablesPage(BasePage):

    def __init__(self, driver):
        super().__init__(driver)

    def go_to_web_tables_page(self):
        self.driver.get("http://85.192.34.140:8081/")
        self._find_element(By.XPATH, "//h5[text()='Elements']").click()
        self._find_element(By.XPATH, "//span[text()='Web Tables']").click()

    def fill_fields(self, first_name, last_name, email, age, salary, department):
        self._find_element(By.ID, "addNewRecordButton").click()
        self._find_element(By.ID, "firstName").send_keys(first_name)
        self._find_element(By.ID, "lastName").send_keys(last_name)
        self._find_element(By.ID, "userEmail").send_keys(email)
        self._find_element(By.ID, "age").send_keys(age)
        self._find_element(By.ID, "salary").send_keys(salary)
        self._find_element(By.ID, "department").send_keys(department)
        self._find_element(By.ID, "submit").click()

    def confirm_record(self, first_name, last_name, email, age, salary, department):
        try:
            self._find_element(By.XPATH, "//div[text()='" + first_name + "']")
            self._find_element(By.XPATH, "//div[text()='" + last_name + "']")
            self._find_element(By.XPATH, "//div[text()='" + email + "']")
            self._find_element(By.XPATH, "//div[text()='" + age + "']")
            self._find_element(By.XPATH, "//div[text()='" + salary + "']")
            self._find_element(By.XPATH, "//div[text()='" + department + "']")
            return True
        except NoSuchElementException:
            return False
