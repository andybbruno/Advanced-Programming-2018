import logging
import os

# regular expressions
import re


logging.basicConfig(level=logging.INFO)


# regular expression to retrieve the package name
# P.S. tested on regex101.com
regex = r"package+.+[;]"


def rebuild_packages(root):

    logging.info("####  rebuild_packages ####\n\n")

    to_rename = []

    # The method walk() generates the file names in a directory tree
    # by walking the tree either top-down (DEFAULT) or bottom-up.
    for subdir, dirs, files in os.walk(root):
        for file in files:
            # endswith() returns True if the string ends with one of the specified extensions
            if file.endswith(".java"):
                # create the abs_path of the file
                abs_file_path = os.path.join(subdir, file)

                # find the relative path of the folder in which the file is
                # rel_dir_path = abs_file_path.split(root + "/")[1].split("/")[0]

                current_dir = subdir.split("/").pop()

                try:
                    # open the file
                    java_file = open(abs_file_path, "r")

                    # read text
                    text = java_file.read()

                    # find all occurrencies of my regular expression
                    matches = re.findall(r"package+.+[;]", text)

                    # if there is any match
                    if len(matches) > 0:
                        # since in Java every class belongs to only one package
                        # I'm sure that the first match is the package name declaration
                        res = matches[0]

                        # remove the semicolumn symbol
                        package_name = res.split("package ")[1].split(";")[0]

                        # if the current directory is equal to the package name
                        if current_dir == package_name:
                            logging.info(abs_file_path + " => NOTHING TO DO\n")

                        # elif there is a subdirectory called with the package name
                        elif package_name in dirs:
                            filename = subdir + '/' + package_name + '/' + file

                            # I've created a list with the files to be renamed because
                            # if I create a folder at this point, the method "os.walk()" will find this
                            # folder at the next step and will check again the package name,
                            # creating a folder in a folder, that is not correct.
                            to_rename.append((abs_file_path, filename))

                        # otherwise try to create a new folder and move this file into the correct folder
                        else:
                            # define the new directory to be created
                            directory = subdir + '/' + package_name

                            # a safe way to create folder
                            if not os.path.exists(directory):
                                os.makedirs(directory)

                            # the new path of this file
                            new_filename = directory + '/' + file

                            # to move a file I simply rename the old path with
                            # the new one just created up here.
                            os.rename(abs_file_path, new_filename)
                            logging.info(abs_file_path +
                                         " => MOVED IN A NEW DIRECTORY\n")

                    # if there isn't any match between my regex and the text
                    # it means that the java file does not contain any package declaration
                    else:
                        logging.info(abs_file_path + " => NO PACKAGE FOUND\n")

                except:
                    logging.info("ERROR @ " + abs_file_path + "\n")

    # for each tuple into the list "to_rename"
    for file in to_rename:
        try:
            # to move a file I simply rename it
            os.rename(file[0], file[1])

            logging.info(
                file[0] + " => CORRECTLY MOVED TO => " + file[1] + "\n")
        except:
            logging.info("ERROR WHILE RENAMING => " +
                         file[0] + " IN " + file[1] + "\n")
