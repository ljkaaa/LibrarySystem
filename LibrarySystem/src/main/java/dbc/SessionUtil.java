package dbc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class SessionUtil {
    public static SqlSession getSqlSession(){
        SqlSession sqlSession=null;
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return sqlSession;
    }
}
