import logging
import os

logging.basicConfig(level=logging.INFO)

EXTENSIONS = (".jar", ".hs", ".py")


def collect_sources(root, source):
    try:
        # Create the absolute path for the output file
        abs_source = os.path.join(root, source)

        # Then I open that file
        source_file = open(abs_source, "w")

        # Create a list of path
        path_list = []

        # The method walk() generates the file names in a directory tree
        # by walking the tree either top-down (DEFAULT) or bottom-up.
        for subdir, dirs, files in os.walk(root):
            for file in files:
                # endswith() returns True if the string ends with one of the specified extensions
                if file.endswith(EXTENSIONS):
                    # first create the abs_path
                    abs_path = os.path.join(subdir, file)
                    # then to obtrain a relative path, I remove "root" from the absolute one
                    rel_path = abs_path.split(root + "/")[1]
                    # save all these path and add "\n" to format text
                    path_list.append(str(rel_path) + "\n")

        # The method writelines() writes a sequence of strings to the file.
        source_file.writelines(path_list)
        logging.info("\nFILE SUCCESSFULLY CREATED!\n")
    except:
        logging.info("\nSOMETHING WENT WRONG!\n")
