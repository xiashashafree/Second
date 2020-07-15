from selenium import webdriver
import time,os

##  多层框架定位元素
## 默认页面中嵌套了 frame1
##frame1中嵌套了frame2
driver = webdriver.Chrome()
filepath=""
driver.get(filepath)
driver.switch_to_frame("f1")
driver.switch_to_frame("f2")
driver.find_element_by_id("kw").send_keys("hhh")
driver.find_element_by_id("su").submit()



## 让页面跳转回原来的默认页面
driver.switch_to_default_content()

