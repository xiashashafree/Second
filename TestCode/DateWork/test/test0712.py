from selenium import webdriver
import time
driver = webdriver.Chrome()
driver.get("https://www.baidu.com")
driver.maximize_window()
# driver.find_element_by_id("kw").send_keys("白夜追凶")
# driver.find_element_by_id("su").click()
# driver.find_element_by_link_text("hao123").click()
#
# driver.find_element_by_partial_link_text("新").click()
# driver.find_element_by_tag_name("input").send_keys("")


driver.find_element_by_xpath("//*[@id='kw']").send_keys("重启")
driver.find_element_by_xpath("//*[@id='su']").click()
time.sleep(3)
driver.quit()