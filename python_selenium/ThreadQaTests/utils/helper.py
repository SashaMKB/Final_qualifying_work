import random
import string


def generate_random_string(length):
    characters = string.ascii_letters + string.digits
    return ''.join(random.choice(characters) for _ in range(length))


def generate_random_email():
    return generate_random_string(10) + "@example.com"


def generate_random_number(number_of_digits):
    min_num = 10 ** (number_of_digits - 1)
    max_num = (10 ** number_of_digits) - 1
    return random.randint(min_num, max_num)


def generate_random_address():
    return generate_random_string(20) + " " + generate_random_string(10) + " St."
