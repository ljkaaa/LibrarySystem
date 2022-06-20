package service;

import dbc.SessionUtil;
import factory.DAOFactory;
import mapper.BookMapper;
import org.apache.ibatis.session.SqlSession;
import vo.Book;

import java.util.List;

public class BookService {
    private SqlSession sqlSession;
    private BookMapper bookDAO;
    public BookService(){
        sqlSession = SessionUtil.getSqlSession();
        bookDAO= DAOFactory.getBookDAOInstance(sqlSession);
    }
    public int insert(Book book){
        if(bookDAO.queryByBarCode(book.getBarCode())==null) {
            return bookDAO.insert(book);
        }
        return 0;
    }

    public int delete(int barCode){
        return bookDAO.delete(barCode);
    }

    public int update(Book book){
        return bookDAO.update(book);
    }

    public Book queryByBarCode(int barCode){
        return bookDAO.queryByBarCode(barCode);
    }

    public List<Book> queryByName(String name){
        return bookDAO.queryByName(name);
    }

    public List<Book> queryAllBooks(){
        return bookDAO.queryAllBooks();
    }
}
