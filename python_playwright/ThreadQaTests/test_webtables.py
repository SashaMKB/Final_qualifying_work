from random import randint

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
        page.click('text=Web Tables')
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


@pytest.mark.parametrize("test_number", range(5))
def test_fill_text_box(browser_page, test_number):
    browser, page = browser_page
    fake = Faker()

    page.click('#addNewRecordButton')

    first_name = fake.name()
    last_name = fake.name()
    email = fake.email()
    age = randint(20, 75)
    salary = randint(2000, 10000)
    department = fake.company()

    page.fill('#firstName', first_name)
    page.fill('#lastName', last_name)
    page.fill('#userEmail', email)
    page.fill('#age', str(age))
    page.fill('#salary', str(salary))
    page.fill('#department', department)

    page.click('#submit')

    first_name_element = page.wait_for_selector(f'//div[normalize-space(text())="{first_name}"]')
    last_name_element = page.wait_for_selector(f'//div[normalize-space(text())="{last_name}"]')
    email_element = page.wait_for_selector(f'//div[normalize-space(text())="{email}"]')
    age_element = page.wait_for_selector(f'//div[normalize-space(text())="{str(age)}"]')
    salary_element = page.wait_for_selector(f'//div[normalize-space(text())="{str(salary)}"]')
    department_element = page.wait_for_selector(f'//div[normalize-space(text())="{department}"]')

    assert first_name_element.is_visible()
    assert last_name_element.is_visible()
    assert email_element.is_visible()
    assert age_element.is_visible()
    assert salary_element.is_visible()
    assert department_element.is_visible()
