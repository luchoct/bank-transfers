import unittest
from statements_loader.StatementsFileGenerator import StatementsFileGenerator, MockedData, \
    FILE_PATH_SEPARATOR, FILE_NAME_SUFFIX, FILE_NAME_PREFIX_DATEFORMAT, STATEMENT_MAX_AMOUNT
from datetime import datetime
from os.path import isfile
import os

TEST_FILE_OUTPUT_DIRECTORY = 'out/runTests/unit'

class TestStatementsFileGenerator(unittest.TestCase):
    def setUp(self):
        if not os.path.exists(TEST_FILE_OUTPUT_DIRECTORY):
            os.makedirs(TEST_FILE_OUTPUT_DIRECTORY)

    def test_get_filename(self):
        filename = StatementsFileGenerator.get_filename()
        self.assertTrue(filename.endswith(FILE_NAME_SUFFIX))
        prefix = filename.replace(FILE_NAME_SUFFIX, '')
        datetime.strptime(prefix, FILE_NAME_PREFIX_DATEFORMAT)
        pass

    def test_get_amount(self):
        for i in range(1000):
            amount = StatementsFileGenerator.get_amount()
            self.assertGreater(amount, 0)
            self.assertLessEqual(amount, STATEMENT_MAX_AMOUNT)
        pass

    def test_generate_file_statements_return_data_based_on_mocked_account_details(self):
        account_details = MockedData.get_account_details()
        for statement in StatementsFileGenerator.generate_file_statements():
            parts = statement.split('\t')
            self.assertTrue((parts[0], parts[1], parts[2]) in account_details)
        pass

    def test_store_file(self):
        filepath = TEST_FILE_OUTPUT_DIRECTORY + '/unitTest.csv'
        StatementsFileGenerator.store_file(filepath, ['statement1\n', 'statement2\n'])
        self.assertTrue(isfile(filepath))

    def test_generate_file(self):
        account_details = MockedData.get_account_details()
        filename = StatementsFileGenerator.generate_file(TEST_FILE_OUTPUT_DIRECTORY)
        filepath = TEST_FILE_OUTPUT_DIRECTORY + FILE_PATH_SEPARATOR + filename
        self.assertTrue(isfile(filepath))
        with open(filepath, 'r', encoding='utf8') as file :
            lines = file.readlines()
            for statement in lines:
                parts = statement.split('\t')
                self.assertTrue((parts[0], parts[1], parts[2]) in account_details)


if __name__ == '__n__':
    unittest.main()
