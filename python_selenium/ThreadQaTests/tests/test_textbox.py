import pytest

from page.textbox_page import TextBoxPage


class TestTextBox:

    @pytest.mark.web
    def test_fill_parameters_in_text_box(self, browser):
        self.textBoxPage = TextBoxPage(browser)
        self.textBoxPage.go_to_text_box_page()
        name = 'name'
        email = 'emai@example.com'
        current_address = 'address'
        permanent_address = 'address'
        self.textBoxPage.fill_fields(name, email, current_address, permanent_address)
        assert self.textBoxPage.confirm_record(name, email, current_address, permanent_address)
