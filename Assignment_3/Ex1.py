import logging
import os

logging.basicConfig(level=logging.INFO)

FROM_EXTEN = ".raj"
TO_EXTEN = ".jar"


def raj2jar(path):
    logging.info("####  raj2jar ####\n\n")

    to_rename = []

    # The method walk() generates the file names in a directory tree
    # by walking the tree either top-down (DEFAULT) or bottom-up.
    for subdir, dirs, files in os.walk(path):
        for file in files:

            # endswith() returns True if the string ends with the specified suffix
            if file.endswith(FROM_EXTEN):

                # splitext() splits a path in root [0]  and extension [1].
                # basically, I'm removing the extension
                file_no_exten = os.path.splitext(file)[0]

                # os.path.join => Join two or more pathname components, inserting '/' as needed
                from_path = os.path.join(subdir, file)
                to_path = os.path.join(subdir, file_no_exten + TO_EXTEN)

                to_rename.append((from_path, to_path))


    if len(to_rename) > 0:
        # for each tuple into the list "to_rename"
        for file in to_rename:
            try:
                # to move a file I simply rename it
                os.rename(file[0], file[1])
                logging.info(
                    file[0] + " => SUCCESSFULLY UPDATED TO => " + file[1] + "\n")
            except:
                logging.info("ERROR WHILE RENAMING => " +
                            file[0] + " IN " + file[1] + "\n")
    else:
        logging.info("NOTHING TO RENAME\n")