import pytest
from faker import Faker
from playwright.sync_api import sync_playwright


@pytest.fixture(scope="session")
def browser_page():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        page = browser.new_page()
        page.goto("http://85.192.34.140:8081/")
        page.click('text=Elements')
        page.click('text=Text Box')
        yield browser, page
        page.close()
        browser.close()


def check_element_text(page, selector, expected_text):
    page.wait_for_selector(selector)
    element_text = page.inner_text(selector)

    if element_text == expected_text:
        print(f"Элемент {selector} содержит ожидаемый текст: {expected_text}")
    else:
        print(f"Ошибка: Элемент {selector} содержит текст: {element_text}, ожидалось: {expected_text}")


@pytest.mark.parametrize("test_number", range(50))
def test_fill_text_box(browser_page, test_number):
    browser, page = browser_page
    fake = Faker()

    name = fake.name()
    email = fake.email()
    current_address = fake.street_address()
    permanent_address = fake.street_address()

    page.fill('#userName', name)
    page.fill('#userEmail', email)
    page.fill('#currentAddress', current_address)
    page.fill('#permanentAddress', permanent_address)

    page.click('#submit')

    page.fill('#userName', '')
    page.fill('#userEmail', '')
    page.fill('#currentAddress', '')
    page.fill('#permanentAddress', '')

    check_element_text(page, "#name", f"Name:{name}")
    check_element_text(page, "#email", f"Email:{email}")
    check_element_text(page, 'xpath=//p[@id="currentAddress"]', f"Current Address :{current_address}")
    check_element_text(page, 'xpath=//p[@id="permanentAddress"]', f"Permanent Address :{permanent_address}")
