from selenium.webdriver.common.by import By

from page.base_page import BasePage


class TextBoxPage(BasePage):

    def __init__(self, driver):
        super().__init__(driver)

    def go_to_text_box_page(self):
        self.driver.get("http://85.192.34.140:8081/")
        self._find_element(By.XPATH, "//h5[text()='Elements']").click()
        self._find_element(By.XPATH, "//span[text()='Text Box']").click()

    def fill_fields(self, name, email, current_address, permanent_address):
        self._find_element(By.ID, "userName").send_keys(name)
        self._find_element(By.ID, "userEmail").send_keys(email)
        self._find_element(By.ID, "currentAddress").send_keys(current_address)
        self._find_element(By.ID, "permanentAddress").send_keys(permanent_address)
        self._find_element(By.ID, "submit").click()
        self._find_element(By.ID, "userName").clear()
        self._find_element(By.ID, "userEmail").clear()
        self._find_element(By.ID, "currentAddress").clear()
        self._find_element(By.ID, "permanentAddress").clear()

    def confirm_record(self, expected_name, expected_email, expected_current_address, expected_permanent_address):
        return (self._find_element(By.ID, "name").text == "Name:" + expected_name and
                self._find_element(By.ID, "email").text == "Email:" + expected_email and
                self._find_element(By.XPATH,
                                   "//p[@id='currentAddress']").text == "Current Address :"
                + expected_current_address and self._find_element(By.XPATH,
                                                                  "//p[@id='permanentAddress']").text
                == "Permananet Address :" + expected_permanent_address)
