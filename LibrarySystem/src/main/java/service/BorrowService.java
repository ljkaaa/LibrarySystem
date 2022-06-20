package service;

import dbc.SessionUtil;
import factory.DAOFactory;
import mapper.BorrowMapper;
import mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import vo.Book;
import vo.Borrow;
import vo.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowService {
    private SqlSession sqlSession;
    private BorrowMapper borrowDAO;
    private BookService bookService;
    public BorrowService(){
        sqlSession = SessionUtil.getSqlSession();
        borrowDAO= DAOFactory.getBorrowDAOInstance(sqlSession);
        bookService=new BookService();
    }

    public boolean borrowBook(Student student, Book book, Date borrowDate, Date returnDate){
        int cnt=book.getCount();
        if(cnt>0) {
            Borrow borrow = new Borrow(student.getId(), book.getBarCode(), borrowDate, returnDate, "否");
            if(borrowDAO.insert(borrow)<=0){
                return false;
            }
            book.setCount(cnt - 1);
            if(bookService.update(book)<=0){
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean returnBook(Student student,Book book){
        Borrow borrow=borrowDAO.queryOneRecord(student.getId(),book.getBarCode());
        if(borrow.getIsReturn().equals("是")){
            return false;
        }
        borrow.setIsReturn("是");
        book.setCount(book.getCount()+1);
        if(bookService.update(book)>0&&borrowDAO.update(borrow)>0){
            return true;
        }
        else{
            return false;
        }
    }

    public List<Borrow> queryByStudentId(int id){
        return borrowDAO.queryBySId(id);
    }

    public List<Borrow> queryAllBorrowRecords(){
        return borrowDAO.queryAllBorrowRecords();
    }

    public List<Borrow> queryOvertimeRecords(Date date){
        return borrowDAO.queryOvertimeRecords(date);
    }

    public List<Borrow> queryAllNotReturnBook(){
        return borrowDAO.queryAllNotReturnBook();
    }



}
