import unittest
from statements_loader.FTPFileUploader import FTPFileUploader, FTPException, FTP_SERVER_ENV_VARIABLE, \
    FTP_USER_ENV_VARIABLE, FTP_PASSWORD_ENV_VARIABLE
from unittest.mock import patch
import logging

LOCAL_FILE_BASE_PATH = './tests/resources'
FILE_NAME = 'integrationTest.txt'

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

    def remove_file_silently(self, ftpClient, filename):
        try:
            ftpClient.delete(filename)
        except:
            pass

    def test_upload_file(self):
        self.env = patch.dict('os.environ', {
            FTP_SERVER_ENV_VARIABLE: 'localhost',
            FTP_USER_ENV_VARIABLE: 'ftpUser',
            FTP_PASSWORD_ENV_VARIABLE: 'ftpPass'})
        with self.env:
            self.uploader = FTPFileUploader()
            self.uploader.connect()

            self.remove_file_silently(self.uploader._FTPFileUploader__ftp, FILE_NAME)
            try:
                self.uploader.upload_text_file(LOCAL_FILE_BASE_PATH, FILE_NAME)
                self.assertIsNotNone(self.uploader._FTPFileUploader__ftp.size(FILE_NAME))
            finally:
                self.remove_file_silently(self.uploader._FTPFileUploader__ftp, FILE_NAME)
                self.uploader.close()


if __name__ == '__main__':
    unittest.main()