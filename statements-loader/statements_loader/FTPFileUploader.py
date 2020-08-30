from ftplib import FTP, all_errors
from os import environ
import logging

FTP_SERVER_ENV_VARIABLE = 'FTP_SERVER'
FTP_USER_ENV_VARIABLE = 'FTP_USER'
FTP_PASSWORD_ENV_VARIABLE = 'FTP_PASSWORD'


class FTPException(Exception):
    def __init__(self, message, cause):
        self.__message = message
        self.__cause = cause


class FTPFileUploader:
    def __init__(self):
        self.load_environment()

    def load_environment(self):
        try:
            self.__host = environ[FTP_SERVER_ENV_VARIABLE]
            self.__user = environ[FTP_USER_ENV_VARIABLE]
            self.__password = environ[FTP_PASSWORD_ENV_VARIABLE]
            logging.debug('Loaded environment host %s user %s and password', self.__host, self.__user)
        except KeyError as cause:
            raise FTPException('Error retrieving env variable', cause)

    def connect(self):
        try:
            self.__ftp = FTP(self.__host, self.__user, self.__password)
            logging.info('Connected to FTP server at %s', self.__host)
        except all_errors as cause:
            raise FTPException('Error connecting to FTP', cause)

    def upload_text_file(self, local_file_base_path, filename):
        local_file_path = local_file_base_path + '/' + filename
        logging.debug('Uploading file %s to FTP server' % local_file_path)
        try:
            with open(local_file_path, 'rb') as file:
                ftpResponse = self.__ftp.storlines('STOR %s' % filename, file)
                if not ftpResponse.startswith('226'):
                    raise FTPException('Upload of file %s failed: reponse %s' % (local_file_path, ftpResponse), None)
        except all_errors as cause:
            raise FTPException('Error uploading file %s' % local_file_path, cause)

    def close(self):
        self.__ftp.quit()
        logging.info('Closed FTP connection at %s', self.__host)
