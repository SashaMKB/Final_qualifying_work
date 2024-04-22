from time import sleep

import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By

from page.textbox_page import TextBoxPage


@pytest.fixture(scope="session")
def browser():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()


@pytest.fixture(scope="session")
def navigate_to_textbox(browser):
    page = TextBoxPage(browser)
    page.go_to_text_box_page()
    yield page

