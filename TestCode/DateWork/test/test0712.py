from selenium import webdriver
import time
driver = webdriver.Chrome()
driver.get("https://www.baidu.com")
# driver.find_element_by_id("kw").send_keys("朱一龙")
# driver.find_element_by_id("su").click()

time.sleep(3)
driver.find_element_by_id("kw").clear()
driver.find_element_by_id("kw").send_keys("重启")
# driver.find_element_by_id("su").click()
driver.find_element_by_id("su").submit()
context = driver.find_element_by_link_text("新闻").text
print(context)
print(driver.current_url)
driver.find_element_by_id("kw").send_keys("乃万")
driver.find_element_by_id("su").click()
driver.implicitly_wait(4) ##智能等待，直到页面加载完毕
driver.find_element_by_link_text(u"乃万_百度百科").click()
time.sleep(3)
url = driver.current_url
print(url)
driver.maximize_window()

##设置浏览器的宽和高
driver.set_window_size(400,400)
time.sleep(3)
driver.quit()