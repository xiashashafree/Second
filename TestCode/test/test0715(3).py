from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
import time,os

####    Test of alert
driver = webdriver.Chrome()
filepath = ""
driver.get(filepath)
driver.find_element_by_link_text("tooltip").click()

### 打印弹出框的文字内容
print(driver.switch_to.alert().text)
driver.switch_to.alert().accept()

#### 在alert的输入框中输入文本

alert = driver.switch_to.alert()
alert.send_keys("盗墓笔记")


######test of  div 对话框
# <div
#<a  href =" "       >
#<p> </p>
#/div>

div = driver.find_element_by_class_name("model-footer")
div.find_element_by_class_name("btn")

