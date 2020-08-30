import logging
from datetime import datetime
from random import randrange

FILE_OUTPUT_DIRECTORY = 'tmp/generatedFiles'
FILE_PATH_SEPARATOR = '/'
FILE_NAME_PREFIX_DATEFORMAT = '%Y%m%dT%H%M%SZ'
FILE_NAME_SUFFIX = '.csv'
FILE_ENCODING = 'utf8'
FILE_MAX_NUMBER_STATEMENTS = 100
STATEMENT_MAX_AMOUNT = 100
STATEMENT_FORMAT = '%s\t%s\t%s\t%.2f\n'

class FileGeneratorException(Exception):
    def __init__(self, message, cause):
        self.__message = message
        self.__cause = cause

class MockedData:
    @staticmethod
    def get_account_details():
        return [('AD1200012030200359100100', '23115880Y', '€'),
                ('AE070331234567890123456', '60932451T', '$'),
                ('AL47212110090000000235698741', '77260122V', '£'),
                ('AT611904300234573201', '08591790W', '€'),
                ('AZ21NABZ00000000137010001944', '00077389V', '$'),
                ('BA391290079401028494', '53041404T', '£'),
                ('BE68539007547034', '23629019S', '€'),
                ('BG80BNBG96611020345678', '53634933Z', '$'),
                ('BH67BMAG00001299123456', '32351563Q', '£'),
                ('BR1800360305000010009795493C1', '78197959Y', '€'),
                ('BY13NBRB3600900000002Z00AB00', '00604067H', '$'),
                ('CH9300762011623852957', '47530724M', '£'),
                ('CR05015202001026284066', '23095386M', '€'),
                ('CY17002001280000001200527600', '22499939M', '$'),
                ('CZ6508000000192000145399', '28830330Z', '€'),
                ('DE89370400440532013000', '12906167Q', '$'),
                ('DK5000400440116243', '14520137F', '£'),
                ('DO28BAGR00000001212453611324', '94741525V', '€'),
                ('EE382200221020145685', '06042065B', '$'),
                ('ES9121000418450200051332', '13922031Q', '£'),
                ('FI2112345600000785', '38910556F', '€'),
                ('FO6264600001631634', '43665387W', '$'),
                ('FR1420041010050500013M02606', '21949994J', '£'),
                ('GB29NWBK60161331926819', '60264262F', '€'),
                ('GE29NB0000000101904917', '97695850S', '$'),
                ('GI75NWBK000000007099453', '98285680X', '£'),
                ('GL8964710001000206', '79323490D', '€'),
                ('GR1601101250000000012300695', '06122283M', '$'),
                ('GT82TRAJ01020000001210029690', '82737665H', '€'),
                ('HR1210010051863000160', '72927186M', '$'),
                ('HU42117730161111101800000000', '03437693K', '£'),
                ('IE29AIBK93115212345678', '20494127T', '€'),
                ('IL620108000000099999999', '74208841P', '$'),
                ('IQ98NBIQ850123456789012', '09936320K', '£'),
                ('IS140159260076545510730339', '42903896H', '€'),
                ('IT60X0542811101000000123456', '35042182A', '$'),
                ('JO94CBJO0010000000000131000302', '40393979E', '£'),
                ('KW81CBKU0000000000001234560101', '64845593Z', '€'),
                ('KZ86125KZT5004100100', '93078218R', '$'),
                ('LB62099900000001001901229114', '55835276Q', '£'),
                ('LC55HEMM000100010012001200023015', '65992999L', '€'),
                ('LI21088100002324013AA', '97295680E', '$'),
                ('LT121000011101001000', '95723665B', '€'),
                ('LU280019400644750000', '46514307G', '$'),
                ('LV80BANK0000435195001', '49610933W', '£'),
                ('MC5811222000010123456789030', '98323948Y', '€'),
                ('MD24AG000225100013104168', '97764560R', '$'),
                ('ME25505000012345678951', '20901081S', '£'),
                ('MK07250120000058984', '48776119L', '€'),
                ('MR1300020001010000123456753', '59242707C', '$'),
                ('MT84MALT011000012345MTLCAST001S', '48264930M', '£'),
                ('MU17BOMM0101101030300200000MUR', '82392826H', '€'),
                ('NL91ABNA0417164300', '12032051S', '$'),
                ('NO9386011117947', '47795066P', '£'),
                ('PK36SCBL0000001123456702', '88900404E', '€'),
                ('PL61109010140000071219812874', '96044731C', '$'),
                ('PS92PALS000000000400123456702', '78041934J', '€'),
                ('PT50000201231234567890154', '87245086Z', '$'),
                ('QA58DOHB00001234567890ABCDEFG', '32457884P', '£'),
                ('RO49AAAA1B31007593840000', '71571559K', '€'),
                ('RS35260005601001611379', '03995637P', '$'),
                ('SA0380000000608010167519', '70500969N', '£'),
                ('SC18SSCB11010000000000001497USD', '47607244G', '€'),
                ('SE4550000000058398257466', '37533078E', '$'),
                ('SI56263300012039086', '61487798N', '£'),
                ('SK3112000000198742637541', '55421987Z', '€'),
                ('SM86U0322509800000000270100', '15731332E', '$'),
                ('ST68000200010192194210112', '25942334J', '£'),
                ('SV62CENR00000000000000700025', '64296133R', '€'),
                ('TL380080012345678910157', '07014047J', '$'),
                ('TN5910006035183598478831', '83694400E', '€'),
                ('TR330006100519786457841326', '44133866V', '$'),
                ('UA213996220000026007233566001', '05175445P', '£'),
                ('VG96VPVG0000012345678901', '84743650N', '€'),
                ('XK051212012345678906', '08674012E', '$')]


class StatementsFileGenerator:
    @staticmethod
    def get_filename():
        return datetime.utcnow().strftime(FILE_NAME_PREFIX_DATEFORMAT) + FILE_NAME_SUFFIX

    @staticmethod
    def get_amount():
        return randrange(STATEMENT_MAX_AMOUNT * 100) / 100 + 0.01

    @staticmethod
    def generate_file_statements():
        statements = []
        account_details = MockedData.get_account_details()
        for num_statement in range(1, randrange(1, FILE_MAX_NUMBER_STATEMENTS + 1)):
            statement_data = account_details[randrange(len(account_details))]
            amount = StatementsFileGenerator.get_amount()
            statements.append(STATEMENT_FORMAT % (statement_data[0], statement_data[1], statement_data[2], amount))
        return statements

    @staticmethod
    def store_file(filepath, statements):
        try:
            with open(filepath, 'w', encoding=FILE_ENCODING) as file :
                logging.debug('Creating file %s', filepath)
                file.writelines(statements)
                logging.debug('File %s created', filepath)
        except OSError as er:
            raise FileGeneratorException('Error on writing the file %s' % filepath, er)

    @staticmethod
    def generate_file(file_output_directory):
        filename = StatementsFileGenerator.get_filename()
        generated_statements = StatementsFileGenerator.generate_file_statements()
        StatementsFileGenerator.store_file(file_output_directory + FILE_PATH_SEPARATOR + filename, generated_statements)
        return filename

