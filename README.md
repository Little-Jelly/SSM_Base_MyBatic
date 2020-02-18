# SSM_Base_MyBatic


完成了myBatis的基础的增删改查

query： 根据条件查询一条数据
queryFuzzy： 根据条件，进行模糊查询多条数据
add： 插入一条数据
update： 更新一条数据
delete： 删除一条数据

涉及使用到的Bean：
org.apache.ibatis.io.Resource
java.io.InputStream
org.apache.ibatis.session.SqlSessionFactory
org.apache.ibatis.session.SqlSessionFactoryBuilder
org.apache.ibatis.session.SqlSession

设计使用到的Bean的方法：
Resources.getResourceAsStream(String a)
SqlSessionFactoryBuilder().build(InputStream inputStream)
SqlSessionFactory.openSession()
SqlSession.selectOne()
SqlSession.selectList()
SqlSession.insert()
SqlSession.update()
SqlSession.delete()
SqlSession.commit()
SqlSession.close()

封装一个MyBatisUtils类
内部的配置外在化：
    数据库的连接属性外在化
 在映射文件中使用resultMap元素