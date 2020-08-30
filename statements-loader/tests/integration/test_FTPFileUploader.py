import unittest
from statements_loader.FTPFileUploader import FTPFileUploader, FTPException, FTP_SERVER_ENV_VARIABLE, \
    FTP_USER_ENV_VARIABLE, FTP_PASSWORD_ENV_VARIABLE
from unittest.mock import patch
import logging

LOCAL_FILE_BASE_PATH = './tests/resources'
FILE_NAME = 'integrationTest.txt'

ENVIRONMENT_VARIABLES = {
    FTP_SERVER_ENV_VARIABLE: 'localhost',
    FTP_USER_ENV_VARIABLE: 'ftpUser',
    FTP_PASSWORD_ENV_VARIABLE: 'ftpPass'}

class TestFTPFileUploader(unittest.TestCase):
    def setUp(self):
        logger = logging.getLogger().setLevel(logging.DEBUG)

    def test_no_environment(self):
        with self.assertRaises(FTPException):
            uploader = FTPFileUploader()

    def test_connection(self):
        self.env = patch.dict('os.environ', ENVIRONMENT_VARIABLES)
        with self.env:
            uploader = FTPFileUploader()
            try:
                uploader.connect()
            finally:
                uploader.close()
            pass

    def set_passive_mode_before_connection(self):
        self.env = patch.dict('os.environ', ENVIRONMENT_VARIABLES)
        with self.env:
            uploader = FTPFileUploader()
            with self.assertRaises(FTPException):
                uploader.set_passive_mode(False)

    def set_passive_mode_after_connection(self):
        self.env = patch.dict('os.environ', ENVIRONMENT_VARIABLES)
        with self.env:
            uploader = FTPFileUploader()
            try:
                uploader.connect()
                with self.assertRaises(FTPException):
                    uploader.set_passive_mode(False)
            finally:
                uploader.close()

    def remove_file_silently(self, ftpClient, filename):
        try:
            ftpClient.delete(filename)
        except:
            pass

    def test_upload_file(self):
        self.env = patch.dict('os.environ', ENVIRONMENT_VARIABLES)
        with self.env:
            uploader = FTPFileUploader()
            uploader.connect()

            self.remove_file_silently(uploader._FTPFileUploader__ftp, FILE_NAME)
            try:
                uploader.upload_text_file(LOCAL_FILE_BASE_PATH, FILE_NAME)
                self.assertIsNotNone(uploader._FTPFileUploader__ftp.size(FILE_NAME))
            finally:
                self.remove_file_silently(uploader._FTPFileUploader__ftp, FILE_NAME)
                uploader.close()


if __name__ == '__main__':
    unittest.main()
