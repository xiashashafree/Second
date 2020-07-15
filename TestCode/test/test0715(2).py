from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
import time,os
driver = webdriver.Chrome()
filepath = ""
driver.get(filepath)
driver.find_element_by_link_text("link1").click()
elem = driver.find_element_by_link_text("A")
###鼠标移动到elem
ActionChains(driver).move_to_element(elem)



lists = driver.find_elements_by_tag_name("option")

##通过循环

for list in lists:
    if list.get_attribute('value') == "10.69":
        list.click()

##通过下标
lists[2].click()

##通过xpath定位
driver.find_element_by_xpath("//*@[value = '8.34']")
