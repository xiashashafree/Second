from selenium import webdriver
import time
driver = webdriver.Chrome()
driver.set_window_size(400,400)
driver.get("https://www.baidu.com")

driver.find_element_by_id("kw").send_keys("张东升")
driver.find_element_by_id("su").submit()
time.sleep(2)
##向后
driver.back()
time.sleep(3)

##向前
driver.forward()
time.sleep(3)

#滚动到最底端
js = "var q=document.documentElement.scrollTop=10000"
driver.execute_script(js)
time.sleep(3)

##滚动到最上端
js = "var q=document.documentElement.scrollTop=0"
driver.execute_script(js)

##向右滚动200，向下滚动200
js = "window.scrollTo(200,200);"
driver.execute_script(js)
time.sleep(3)
driver.quit()