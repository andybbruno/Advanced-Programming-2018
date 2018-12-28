import logging
import os

logging.basicConfig(level=logging.INFO)

FROM_EXTEN = ".raj"
TO_EXTEN = ".jar"


def raj2jar(path):

    errors = 0
    success = 0
    index = 0

    # The method walk() generates the file names in a directory tree
    # by walking the tree either top-down (DEFAULT) or bottom-up.
    for subdir, dirs, files in os.walk(path):
        for file in files:
            index += 1

            # endswith() returns True if the string ends with the specified suffix
            if file.endswith(FROM_EXTEN):

                # splitext() splits a path in root [0]  and extension [1].
                # basically, I'm removing the extension
                file_no_exten = os.path.splitext(file)[0]

                # os.path.join => Join two or more pathname components, inserting '/' as needed
                from_path = os.path.join(subdir, file)
                to_path = os.path.join(subdir, file_no_exten + TO_EXTEN)

                try:
                    os.rename(from_path, to_path)
                    success += 1
                    logging.info("\nSUCCESSFULLY UPDATED" + from_path)
                except:
                    errors += 1
                    logging.info("\nERROR WHILE RENAMING " + from_path)

    
    # Final message
    logging.info("\nraj2jar ended with error rate = " +
                 str(errors / index) + "%")
