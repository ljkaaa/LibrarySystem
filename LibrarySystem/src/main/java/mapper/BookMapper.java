package mapper;

import vo.Book;

import java.util.List;

public interface BookMapper {
    int insert(Book book);
    int delete(int barCode);
    int update(Book book);
    Book queryByBarCode(int barCode);
    List<Book> queryByName(String name);
    List<Book> queryAllBooks();
}
