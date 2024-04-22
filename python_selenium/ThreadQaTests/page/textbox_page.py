from selenium.webdriver.common.by import By

from page.base_page import BasePage


class TextBoxPage(BasePage):

    def __init__(self, driver):
        super().__init__(driver)
        self.name = self._find_element(By.ID, "userName")
        self.email = self._find_element(By.ID, "userEmail")
        self.currentAddress = self._find_element(By.ID, "currentAddress")
        self.permanentAddress = self._find_element(By.ID, "permanentAddress")
        self.submitButton = self._find_element(By.ID, "submit")
        self.nameOutput = self._find_element(By.ID, "name")
        self.emailOutput = self._find_element(By.ID, "email")
        self.currentAddressOutput = self._find_element(By.XPATH, "//p[@id='currentAddress']")
        self.permanentAddressOutput = self._find_element(By.XPATH, "//p[@id='permanentAddress']")

    def go_to_text_box_page(self):
        self.driver.get(self.base_url)
        self._find_element(By.XPATH, "//h5[text()='Elements']").click()
        self._find_element(By.XPATH, "//span[text()='Text Box']").click()

    def fill_fields(self, name, email, current_address, permanent_address):
        self.name.send_keys(name)
        self.email.send_keys(email)
        self.currentAddress.send_keys(current_address)
        self.permanentAddress.send_keys(permanent_address)
        self.submitButton.click()
        self.name.clear()
        self.email.clear()
        self.currentAddress.clear()
        self.permanentAddress.clear()

    def confirm_record(self, expected_name, expected_email, expected_current_address, expected_permanent_address):
        return (self.nameOutput.text == "Name:" + expected_name and
                self.emailOutput.text == "Email:" + expected_email and
                self.currentAddressOutput.text == "Current Address :" + expected_current_address and
                self.permanentAddressOutput.text == "Permanent Address :" + expected_permanent_address)
