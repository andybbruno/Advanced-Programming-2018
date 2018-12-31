from Ex1 import raj2jar
from Ex2 import collect_sources
from Ex3 import rebuild_packages

abs_path = '/Users/andrea/Desktop/TEST'


raj2jar(abs_path)
collect_sources(abs_path, "src.txt")
rebuild_packages(abs_path)
