# carManage
这个项目采用idea springboot thymeleaf layui aop ssm pagehelper Oracle做的一个粗糙的小项目  
要部署这个项目先要在Oracle中添加数据表，然后在application.yml上修改数据库连接，然后大概就可以愉快的玩耍了  
如果要用mysql的话，可以将application.yml中所有涉及到Oracle的部分（善用搜索功能）给改成mysql，然后还要把mapper.xml中的语句改成适合mysql的语句；或者也可以先建好表
，使用mybatis-generator自动生成，然后将相关的功能添加上即可
。改了不成功则肯定不是我的问题