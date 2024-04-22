from selenium.webdriver.chrome.webdriver import ChromiumDriver
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait


class BasePage:

    def __init__(self, driver) -> None:
        self.driver: ChromiumDriver = driver
        self.wait = WebDriverWait(self.driver, 5)
        self.base_url = "http://85.192.34.140:8081"

    def _find_element(self, by, locator):
        self.wait.until(EC.visibility_of_element_located((by, locator)))
        return self.driver.find_element(by, locator)

    def _find_elements(self, by, locator):
        self.wait.until(EC.visibility_of_element_located((by, locator)))
        return self.driver.find_elements(by, locator)

    def _click(self, by, locator, timeout=10):
        self.wait.until(EC.visibility_of_element_located((by, locator)))
        self._find_element(by, locator).click()
