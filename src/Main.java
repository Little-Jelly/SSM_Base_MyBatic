import com.yyf.mybatis.associate.oneToOne.POJO.Person;
import com.yyf.mybatis.core.utils.MyBatisUtils;
import com.yyf.mybatis.example.query.POJO.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args){
        System.out.println("hello, world");
    }

    /**
     * 根据id查询表数据
     * 查询结果是一条数据
     * res: successfully
     * @throws IOException
     * related base package: com.yyf.mybatis/example/query
     */
    @Test
    public void test_findCustomerByIdTest() throws IOException {
        String resource = "com/yyf/mybatis/example/query/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Customer customer = sqlSession.selectOne("com.yyf.mybatis.example.query.mapper.CustomerMapper.findCustomerId", 1);
        System.out.println(customer.toString());
        sqlSession.close();
    }

    /**
     * 使用模糊查询查询表数据
     * 查询结果是多条数据
     * res:successfully
     * @throws IOException
     * related base package: com/yyf/mybatis/example/queryFuzzy/
     */
    @Test
    public void test_findCustomerId_fuzzy() throws IOException{
        String resource = "com/yyf/mybatis/example/queryFuzzy/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<com.yyf.mybatis.example.queryFuzzy.POJO.Customer> customers = sqlSession.selectList("com.yyf.mybatis.example.queryFuzzy.mapper.CustomerMapper.findCustomerId_fuzzy", "j");
        for(com.yyf.mybatis.example.queryFuzzy.POJO.Customer customer : customers ){
            System.out.println(customer);
        }
        sqlSession.close();
    }

    /**
     * 向数据库表中插入一条数据
     *
     * @throws IOException
     * related base package: com/yyf/mybatis/example/add/
     */
    @Test
    public void test_addCustomer() throws IOException{
        String resource = "com/yyf/mybatis/example/add/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        com.yyf.mybatis.example.add.POJO.Customer customer = new com.yyf.mybatis.example.add.POJO.Customer();
//        customer.setId(4);
        customer.setUsername("peter");
        customer.setJobs("student");
        customer.setPhone("13333533092");
        System.out.println(customer);
        int rows = sqlSession.insert("com.yyf.mybatis.example.add.mapper.CustomerMapper.addCustomer",customer);
        if (rows > 0) {
            System.out.println("您成功插入了："+rows+"条数据！");
        }else {
            System.out.println("执行插入操作失败！！！");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 向数据库表中更新一条数据
     *
     * @throws IOException
     * related base package: com/yyf/mybatis/example/update
     */
    @Test
    public void test_updateCustomer() throws IOException{
        String resource = "com/yyf/mybatis/example/update/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession =sqlSessionFactory.openSession();
        com.yyf.mybatis.example.update.POJO.Customer customer = new com.yyf.mybatis.example.update.POJO.Customer();
        customer.setId(7);
        customer.setUsername("rose");
        customer.setJobs("programmer");
        customer.setPhone("13111111144");
        int rows = sqlSession.update("com.yyf.mybatis.example.update.mapper.CustomerMapper.updateCustomer", customer);
        if(rows > 0){
            System.out.println("您成功修改了" + rows + "条数据！");
        }else {
            System.out.println("执行修改操作失败！！！");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除数据库表中的一条数据
     *
     * @throws IOException
     * related base package: com/yyf/mybatis/example/delete
     */
    @Test
    public void test_deleteCustomer() throws IOException {
        String resource = "com/yyf/mybatis/example/delete/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rows = sqlSession.delete("com.yyf.mybatis.example.delete.mapper.CustomerMapper.deleteCustomer",4);
        if(rows > 0){
            System.out.println("您成功删除了" + rows + "条数据！");
        }else {
            System.out.println("执行删除操作失败！！！");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     *  查询一个数据库表中的所有信息
     *
     * related base package: com/yyf/mybatis/core
     */
    @Test
    public void test_core_findAllUser(){
        SqlSession sqlSession = MyBatisUtils.getSession();
        List<com.yyf.mybatis.core.POJO.User> list = sqlSession.selectList("com.yyf.mybatis.core.mapper.UserMapper.findAllUser");
        for( com.yyf.mybatis.core.POJO.User user : list){
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * mybatis的映射关系：一对一映射
     *
     * related base package: com/yyf/mybatis/associate/oneToOne
     */
    @Test
    public void test_oneToOne(){
        SqlSession sqlSession = com.yyf.mybatis.associate.oneToOne.utils.MyBatisUtils.getSession();
        com.yyf.mybatis.associate.oneToOne.POJO.Person person = sqlSession.selectOne("com.yyf.mybatis.associate.oneToOne.mapper.PersonMapper.findPersonById",2);
        System.out.println(person);
        sqlSession.close();
    }

    /**
     * mybatis的映射关系：一对多映射
     *
     * related base package: com/yyf/mybatis/associate/oneToMulti
     */
    @Test
    public void test_oneToMulti(){
        SqlSession sqlSession = com.yyf.mybatis.associate.oneToMulti.utils.MyBatisUtils.getSession();
        com.yyf.mybatis.associate.oneToMulti.POJO.User user = sqlSession.selectOne("com.yyf.mybatis.associate.oneToMulti.mapper.UserMapper.findUserWithOrders", 1);
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * mybatis的映射关系：多对多映射
     *
     * related base package: com/yyf/mybatis/associate/multiToMulti
     */
    @Test
    public void test_multiToMulti(){
        SqlSession sqlSession = com.yyf.mybatis.associate.multiToMulti.utils.MyBatisUtils.getSession();
        com.yyf.mybatis.associate.multiToMulti.POJO.Orders orders = sqlSession.selectOne("com.yyf.mybatis.associate.multiToMulti.mapper.OrdersMapper.findOrdersWithProduct", 1);
        System.out.println(orders);
        sqlSession.close();
    }
}
