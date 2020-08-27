import unittest
from FTPFileUploader import FTPFileUploader, FTPException, FTP_SERVER_ENV_VARIABLE, FTP_USER_ENV_VARIABLE, FTP_PASSWORD_ENV_VARIABLE
from unittest.mock import patch
import logging


class TestFTPFileUploader(unittest.TestCase):
    def setUp(self):
        logger = logging.getLogger().setLevel(logging.DEBUG)

    def test_no_environment(self):
        with self.assertRaises(FTPException):
            self.uploader = FTPFileUploader()

    def test_connection(self):
        self.env = patch.dict('os.environ', {
            FTP_SERVER_ENV_VARIABLE: 'localhost',
            FTP_USER_ENV_VARIABLE: 'ftpUser',
            FTP_PASSWORD_ENV_VARIABLE: 'ftpPass'})
        with self.env:
            self.uploader = FTPFileUploader()
            self.uploader.connect()
            self.uploader.close()
            pass


if __name__ == '__main__':
    unittest.main()
