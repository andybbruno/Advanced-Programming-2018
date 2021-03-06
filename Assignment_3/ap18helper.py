from Ex1 import raj2jar
from Ex2 import collect_sources
from Ex3 import rebuild_packages
from Ex4 import download_tests
import os
import logging


loop = True
os.system('clear')  # For Linux/OS X
os.system('cls')  # For Windows


def wait():
    """
    Pause the execution by requiring an input from the user
    """
    print("\n\n" + "Press any key to continue...")
    input()


def errormessage(message):
    """
    Given a message, this function will print the given text with some error decorations
        :param message: a string message
    """

    logging.error("\n" + 67 * "*" + "\n" + 20 * ">" +
                  "\t" + message + "\n" + 67 * "*")
    wait()


def print_menu():
    """
    A simple menu function
    """

    os.system('clear')  # For Linux/OS X
    os.system('cls')  # For Windows
    print(30 * "-", "MENU", 30 * "-")
    print("1. raj2jar ")
    print("2. collect_sources ")
    print("3. rebuild_packages ")
    print("4. download_tests ")
    print("\n0. Exit")
    print(67 * "-")


if __name__ == "__main__":
    while loop:
        print_menu()
        choice = input("Enter your choice [0-4]: ")

        if choice == '1':
            print("\n>>>> raj2jar")
            root = input("Insert a root folder: ")

            if os.path.exists(root):
                raj2jar(root)
                wait()
            else:
                errormessage("The folder does not exist!")

        elif choice == '2':
            print("\n>>>> collect_sources")
            root = input("Insert a root folder: ")
            source = input("Insert a name of a text file: ")

            if os.path.exists(root):
                collect_sources(root, source)
                wait()
            else:
                errormessage("The folder does not exist!")

        elif choice == '3':
            print("\n>>>> rebuild_packages")
            root = input("Insert a root folder: ")

            if os.path.exists(root):
                rebuild_packages(root)
                wait()
            else:
                errormessage("The folder does not exist!")

        elif choice == '4':
            print("\n>>>> download_tests")
            root = input("Insert a root folder: ")
            URL = input("Insert an URL (optional): ")

            if os.path.exists(root):
                if URL:
                    download_tests(root, URL)
                    wait()
                else:
                    download_tests(root)
                    wait()
            else:
                errormessage("The folder does not exist!")

        elif choice == '0':
            print("\n\nBye Bye!\n\n")
            loop = False
        else:
            errormessage("WRONG SELECTION!")
