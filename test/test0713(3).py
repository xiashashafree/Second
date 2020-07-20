from selenium import webdriver
import os
import time
driver = webdriver.Chrome()


####上传文件操作
file_path = 'file:///'+os.path.abspath("c:/jkiodj/sdjkslqdm")
driver.get(file_path)
time.sleep(3)
driver.find_element_by_name("file").send_keys("C:/dkfj")
time.sleep(3)

inputs = driver.find_elements_by_tag_name("input")
for i in inputs:
    if i.get_attribute('type') == "checkbox":
        i.click()

driver.quit()




