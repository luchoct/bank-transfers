from statements_loader.StatementsFileGenerator import StatementsFileGenerator, FileGeneratorException
from statements_loader.FTPFileUploader import FTPFileUploader, FTPException
import logging
from time import sleep
import os

FILE_OUTPUT_DIRECTORY = 'out'
DELAY = 60

def main():
    logging.basicConfig(level=os.environ.get("LOGLEVEL", "WARNING"))

    uploader = FTPFileUploader()
    while True:
        try:
            filename = StatementsFileGenerator.generate_file(FILE_OUTPUT_DIRECTORY)
            logging.info('Generated file %s at %s', filename, FILE_OUTPUT_DIRECTORY)
            uploader.connect()
            uploader.upload_text_file(FILE_OUTPUT_DIRECTORY, filename)
            logging.info('Uploaded file %s', filename)
            uploader.close()
        except FTPException:
            logging.exception("Exception on loading statements")
        except FileGeneratorException:
            logging.exception("Exception on loading statements")
        finally:
            sleep(DELAY)


main()
