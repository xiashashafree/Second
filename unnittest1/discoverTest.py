import unittest,csv
import time,os,sys

#手工添加案例到套件

def createsuite():
    discover = unittest.defaultTestLoader.discover('../',pattern='Tcase.py',top_level_dir=None)
    print(discover)
    return discover

if __name__ == "__main__":
    suite=createsuite()
    runner = unittest.TextTestRunner(verbosity=2)
    runner.run(suite)