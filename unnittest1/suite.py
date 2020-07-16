import unittest,csv

import os,sys
import time
import tcase



def createsuite():
    #创建测试套件
    suite = unittest.TestSuite()

    #添加测试用例到测试套件  unittest包下的TestSuite中的测试用例
    suite.addTest(tcase.Baidu1("test_baidusearch"))
    suite.addTest(tcase.Baidu1("test_hao"))
    return suite



if __name__ == "__main__":
    suite = createsuite()
    runner = unittest.TextTestRunner(verbosity=2)
    runner.run(suite)

