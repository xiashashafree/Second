

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains
import time,os
driver = webdriver.Chrome()
driver.get("https://www.baidu.com")

# driver.find_element_by_id("kw").send_keys("张东升")
# driver.find_element_by_id("su").submit()
#
# driver.get("https://127.0.0.1:88/biz/user-login.html")
# driver.implicitly_wait(10)
# driver.find_element_by_id("account").send_keys("admin")
# driver.find_element_by_id("account").send_keys(Keys.TAB)

##  组合键  Ctrl+A
driver.find_element_by_name("xx").send_keys(Keys.CONTROL,'a')

# driver.get("https://www.baidu.com")

driver.find_element_by_id("kw").send_keys("端午")
su1 = driver.find_element_by_id("su")
#右击
ActionChains(driver).context_click(su1).perform()
#双击
ActionChains(driver).double_click(su1).perform()



#将s1拖动到s2
# ActionChains(driver).drag_and_drop(s1,s2).perform()

#将鼠标移动到s1上
# ActionChains(driver).move_to_element(s1).perform()