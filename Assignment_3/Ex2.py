import logging
import os

logging.basicConfig(level=logging.INFO)

EXT = (".jar", ".hs", ".py")


def collect_sources(root, source):
    logging.info("####  collect_sources ####\n\n")
    try:
        # Create a list of path
        path_list = []

        # The method walk() generates the file names in a directory tree
        # by walking the tree either top-down (DEFAULT) or bottom-up.
        for subdir, dirs, files in os.walk(root):
            for file in files:
                # endswith() returns True if the string ends with one of the specified extensions
                if file.endswith(EXT):
                    # first create the abs_path
                    abs_path = os.path.join(subdir, file)
                    # then to obtrain a relative path, I remove "root" from the absolute one
                    rel_path = abs_path.split(root + "/")[1]
                    # save all these path and add "\n" to format text
                    path_list.append(str(rel_path) + "\n")

        # if there is at least one file, I create the txt file
        # otherwise there's no need
        if len(path_list) > 0:
            # Create the absolute path for the output file
            abs_source = os.path.join(root, source)

            # Then I open that file
            source_file = open(abs_source, "w")

            # The method writelines() writes a sequence of strings to the file.
            source_file.writelines(path_list)
            logging.info("FILE SUCCESSFULLY CREATED!\n")
        else:
            logging.info("NO FILES FOUND WITH THESE EXTENSIONS " + str(EXT) + "\n")
    except:
        logging.info("SOMETHING WENT WRONG!\n")
