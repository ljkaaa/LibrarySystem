import factory.DAOFactory;
import dbc.SessionUtil;
import mapper.BorrowMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import vo.Borrow;

import java.util.Date;
import java.util.List;


public class testt {

    @Test
    public void test(){
        SqlSession sqlSession = SessionUtil.getSqlSession();
        //BookMapper bookmapper = sqlSession.getMapper(BookMapper.class);
        Date date1=new Date(System.currentTimeMillis());
        Date date2=new Date(date1.getTime()+ 86400000L *30);
        BorrowMapper borrowMapper= DAOFactory.getBorrowDAOInstance(sqlSession);
        //borrowMapper.insert(new Borrow(1233,321,date1,date2,"是"));
        //borrowMapper.delete(123,321);
        List<Borrow> borrowList=borrowMapper.queryAllBorrowRecords();
        for (Borrow borrow: borrowList){
            System.out.println(borrow.toString());
        }
    }
}
