package service;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import vo.Book;
import vo.Borrow;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileService {
    public void writeBookToExcel(String fileName) throws Exception {
        BookService bookService=new BookService();
        List<Book> list=bookService.queryAllBooks();
        WritableWorkbook book= Workbook.createWorkbook(new File(fileName));
        WritableSheet sheet= book.createSheet("图书信息",0);
        String[] title ={"条形码","商品名称","作者","出版商","出版日期","在馆数量"};
        for(int i=0;i<title.length;i++){
            Label label=new Label(i,0,title[i]);
            sheet.addCell(label);
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        int len=list.size();
        for(int i=0;i<len;i++){
            Label label=new Label(0,i+1,Integer.toString(list.get(i).getBarCode()));
            sheet.addCell(label);
            label=new Label(1,i+1,list.get(i).getName());
            sheet.addCell(label);
            label=new Label(2,i+1,list.get(i).getAuthor());
            sheet.addCell(label);
            label=new Label(3,i+1,list.get(i).getPublisher());
            sheet.addCell(label);
            label=new Label(4,i+1,dateFormat.format(list.get(i).getPublishDate()));
            sheet.addCell(label);
            label=new Label(5,i+1,Integer.toString(list.get(i).getCount()));
            sheet.addCell(label);
        }
        book.write();
        book.close();
    }

    public void writeBorrowToExcel(String fileName) throws Exception {
        BorrowService borrowService=new BorrowService();
        List<Borrow> list=borrowService.queryAllBorrowRecords();
        WritableWorkbook book= Workbook.createWorkbook(new File(fileName));
        WritableSheet sheet= book.createSheet("借阅信息",0);
        String[] title ={"学号","条形码","借阅日期","归还日期"};
        for(int i=0;i<title.length;i++){
            Label label=new Label(i,0,title[i]);
            sheet.addCell(label);
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        int len=list.size();
        for(int i=0;i<len;i++){
            Label label=new Label(0,i+1,Integer.toString(list.get(i).getsId()));
            sheet.addCell(label);
            label=new Label(1,i+1,Integer.toString(list.get(i).getBarCode()));
            sheet.addCell(label);
            label=new Label(2,i+1, dateFormat.format(list.get(i).getBorrowDate()));
            sheet.addCell(label);
            label=new Label(3,i+1,dateFormat.format(list.get(i).getReturnDate()));
            sheet.addCell(label);
            label=new Label(4,i+1,list.get(i).getIsReturn());
            sheet.addCell(label);
        }
        book.write();
        book.close();
    }
}
