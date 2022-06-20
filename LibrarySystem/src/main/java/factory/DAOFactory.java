package factory;

import mapper.BookMapper;
import mapper.BorrowMapper;
import mapper.ManagerMapper;
import mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;

public class DAOFactory {
    public static BookMapper getBookDAOInstance(SqlSession sqlSession){
        return sqlSession.getMapper(BookMapper.class);
    }

    public static BorrowMapper getBorrowDAOInstance(SqlSession sqlSession){
        return sqlSession.getMapper(BorrowMapper.class);
    }
    public static StudentMapper getStudentDAOInstance(SqlSession sqlSession){
        return sqlSession.getMapper(StudentMapper.class);
    }
    public static ManagerMapper getManagerDAOInstance(SqlSession sqlSession){
        return sqlSession.getMapper(ManagerMapper.class);
    }
}
