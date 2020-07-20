import unittest,csv
import time,os,sys
import HTMLTestRunner
#手工添加案例到套件

def createsuite():
    discover = unittest.defaultTestLoader.discover('../',pattern='Tcase.py',top_level_dir=None)
    print(discover)
    return discover

if __name__ == "__main__":
    curpath = sys.path[0]
    now = time.strftime("%Y-%m-%d-%H %M %S",time.localtime(time.time()));
    if not os.path.exists(curpath+'/resultreport'):
        os.makedirs(curpath+'/resultreport')
    filename = curpath+'/resultreport/'+now+'resultreport.html'
    with open(filename,'wb') as fp:
        runner=HTMLTestRunner.HTMLTestRunner(stream=fp,title=u'测试报告',description='用例执行情况',verbosity=2)

        suite=createsuite()

        runner.run(suite)