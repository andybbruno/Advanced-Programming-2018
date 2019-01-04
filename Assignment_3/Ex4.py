import logging
import os
import csv
import urllib.request
import re
from subprocess import call

# this class is used to wrap the rows within the CSV


class Record:
    def __init__(self, filename,  testfiles, command):
        self.filename = filename
        self.testfiles = testfiles
        self.command = command


logging.basicConfig(level=logging.INFO)

URL = "http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/03/Test"
regex = r"package+.+[;]"


def validateURL(URL):
    """
    This function check if an URL is correct
        :param URL: a web address
    """
    res = urllib.request.urlparse(URL).scheme
    if res == 'http':
        pass
    else:
        raise Exception


def download_tests(root, URL="http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/03/Test"):
    """
    Download test files for each Python, Haskell or Java file within root folder 
    and place those test files in the proper directory.
        :param root: the folder containing those source code
        :param URL="http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/03/Test": an URL to download such test files
    """

    logging.info(30 * '=' + " download_tests " + 30 * '=' + "\n\n")

    test_filename = "AP_TestRegistry.csv"
    URL_file = URL + "/" + test_filename

    record_list = []

    try:
        validateURL(URL)

        # Download the csv from "URL", save it in a temporary directory and get the path to it
        file, _ = urllib.request.urlretrieve(URL_file)

        # read the file
        csv_reader = csv.reader(open(file, mode='r'))

        # remove first row
        next(csv_reader)

        for row in csv_reader:
            # if the row is not empty
            if row:
                # column 0 is the "filename" field.
                # Note that "strip()" is used to remove whitespaces
                filename = str(row[0]).strip()

                # column 1 is the "testfiles" field.
                # In addiction to "strip()", there's a "split()" to create the list of filename.
                testfiles = str(row[1]).strip().split(":")

                # column 2 is the "command" field.
                # Here, I remove whitespaces and quotes.
                command = str(row[2]).strip().replace("\"", '')

                record_list.append(Record(filename, testfiles, command))

        # for each row in the CSV
        for record in record_list:

            found = False

            # The method walk() generates the file names in a directory tree
            # by walking the tree either top-down (DEFAULT) or bottom-up.
            for subdir, dirs, files in os.walk(root):
                for file in files:
                    # if there is a file called the same as the current CSV row
                    if file == record.filename:
                        found = True
                        # for each testfile, download it and place it in the right directory
                        for test in record.testfiles:
                            URL_testfile = URL + "/" + test
                            path_testfile = subdir + "/" + test
                            cmd_dir = subdir

                            # if the current file is Java
                            if test.endswith(".java"):
                                # open it to check if there is a package declaration
                                abs_file_path = os.path.join(subdir, file)
                                text = open(abs_file_path, "r").read()

                                # if there is a package declaration
                                if len(re.findall(regex, text)) > 0:
                                    # to get the current directory, I split the current dir using "/"
                                    # and then I take the last element
                                    # i.e. "/User/Andrea/Folder" ---> "Folder"
                                    current_dir = subdir.split("/").pop()

                                    # Once found the name of the current directory, to get the parent dir
                                    # I simply remove it from its own path, like a subtraction
                                    # i.e. "/User/Andrea/Folder" - "/Folder" = "/User/Andrea/"
                                    parent_path = subdir.split(
                                        "/" + current_dir)[0]

                                    # correct the path, in order to put test files into the parent directory
                                    path_testfile = parent_path + "/" + test

                                    # to execute tests correctly I must set 'parent_path' as the test directory
                                    cmd_dir = parent_path

                                # download the test file and place it in the correct place
                                download(URL_testfile, path_testfile)

                            # if the current file is a Haskell or a Python script or something else
                            else:
                                # download the test file and place it in its directory
                                download(URL_testfile, path_testfile)

                        cmd_list = record.command.split(";")
                        runtest(cmd_list, cmd_dir, file)
            if not found:
                logging.warning(40 * '>')
                logging.warning(40 * '<' + "\n")
                logging.error("\t" + record.filename + " ----> NOT FOUND!!\n")
                logging.warning(40 * '>')
                logging.warning(40 * '<' + "\n\n")
    except:
        logging.error("SOMETHING WENT WRONG!\n")

    logging.info(30 * '=' + " download_tests " + 30 * '=' + "\n\n")


def download(URL, path):
    """
    download the file in the given URL and put it into the given path
        :param URL: an URL to download such test files
        :param path: an aboslute path to put this file in
    """

    urllib.request.urlretrieve(URL, path)
    logging.info("\nDOWNLOADED : \t" + URL + "\nIN : \t\t" + path + "\n")


def runtest(commands, path, file=""):
    """
    Run a list tests by executing a list of command in a separate shell
        :param commands: a list of commands
        :param path: a path in which those commands should be run
        :param file: used to log
    """

    logging.warning("********* START TEST : " + file + " *********")
    # execute commands
    for cmd in commands:
        call(cmd, shell=True, cwd=path)
    logging.warning("*********  END TEST : " + file + "  *********\n\n")
