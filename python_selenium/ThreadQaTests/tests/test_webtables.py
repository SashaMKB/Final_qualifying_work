import pytest

from page.webtables_page import WebTablesPage
from utils.helper import generate_random_string, generate_random_number, generate_random_email


class TestWebTables:

    @pytest.mark.parametrize("test_run", range(5))
    def test_fill_parameters_in_web_tables(self, browser, test_run):
        self.webTablesPage = WebTablesPage(browser)
        self.webTablesPage.go_to_web_tables_page()
        first_name = generate_random_string(8)
        second_name = generate_random_string(12)
        email = generate_random_email()
        salary = generate_random_number(4)
        age = generate_random_number(2)
        department = generate_random_string(10)
        self.webTablesPage.fill_fields(first_name, second_name, email, str(age), str(salary), department)
        assert self.webTablesPage.confirm_record(first_name, second_name, email, str(age), str(salary), department)
