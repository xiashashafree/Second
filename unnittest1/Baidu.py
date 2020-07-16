import unittest
from selenium import webdriver
import time

class Baidu(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.base_url = "http://www.baidu.com/"

    def test_baidu(self):
        driver = self.driver
        driver.get(self.base_url)
        driver.implicitly_wait(3)
        driver.find_element_by_id("kw").send_keys("朱一龙")
        driver.find_element_by_id("su").click()
        time.sleep(3)
    def tearDown(self):
        self.driver.quit()



if __name__ == "__main__":
    unittest.main()