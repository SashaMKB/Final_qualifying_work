import pytest

from page.textbox_page import TextBoxPage
from utils.helper import generate_random_string, generate_random_email, generate_random_address


class TestTextBox:

    @pytest.mark.parametrize("test_run", range(10))
    def test_fill_parameters_in_text_box(self, browser, test_run):
        self.textBoxPage = TextBoxPage(browser)
        self.textBoxPage.go_to_text_box_page()
        name = generate_random_string(10)
        email = generate_random_email()
        current_address = generate_random_address()
        permanent_address = generate_random_address()
        self.textBoxPage.fill_fields(name, email, current_address, permanent_address)
        assert self.textBoxPage.confirm_record(name, email, current_address, permanent_address)
