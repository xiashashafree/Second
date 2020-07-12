import math
import jirafs_matplotlib
import tableau

import copy
num=math.pow(2,1/2)
########## 读取lorenza.dat的内容
dataSet=[]
filename='lorenza.dat'
fr = open(filename)
for line in fr.readlines():
    data = (line.strip().split(" "))
    dataSet.append(float(data[0]))
# for i in dataSet:
#     print(i)
# print("#################################################")





def transform(list1,list2,index):
    for i in range(len(list2)):
        list1[i+index]=list2[i]





X=copy.deepcopy(dataSet)
A=[]
D=[]
for i in range(1,9):
    for j in range(int(len(X)/math.pow(2,i))):
        A.append((X[2*j]+X[2*j+1])/num)
        D.append((X[2*j]-X[2*j+1])/num)
    transform(X,A,0)
    transform(X,D,len(A)-1)
    A.clear()
    D.clear()

for i in range(len(X)):
    print(str(i)+":  "+str(X[i]))