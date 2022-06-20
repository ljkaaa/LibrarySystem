package driver;

import service.*;
import vo.Book;
import vo.Borrow;
import vo.Manager;
import vo.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryDriver {

    private static BookService bookService=new BookService();
    private static BorrowService borrowService=new BorrowService();
    private static ManagerService managerService=new ManagerService();
    private static ReaderService readerService=new ReaderService();
    private static FileService fileService=new FileService();
    private static Manager manager=new Manager();
    private static Student student=new Student();
    private static Scanner scan=new Scanner(System.in);
    private static String role;

    public static Student inputStudent(){
        System.out.print("请输入学号:");
        int id= scan.nextInt();
        System.out.print("请输入姓名:");
        String name= scan.next();
        System.out.print("请输入性别:");
        String gender= scan.next();
        System.out.print("请输入年龄:");
        int age= scan.nextInt();
        System.out.print("请输入学院:");
        String institute= scan.next();
        System.out.print("请输入电话:");
        String phone=scan.next();
        System.out.print("请输入密码:");
        String password=scan.next();
        return new Student(id,name,gender,age,institute,phone,password);
    }

    public static Book inputBook() throws ParseException {
        System.out.print("请输入条形码:");
        int barCode= scan.nextInt();
        System.out.print("请输入书名:");
        String name= scan.next();
        System.out.print("请输入作者:");
        String author= scan.next();
        System.out.print("请输入出版商:");
        String publisher=scan.next();
        System.out.print("请输入出版日期(yyyy-mm-dd):");
        String dateStr= scan.next();
        System.out.print("请输入数量:");
        int count= scan.nextInt();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date =new Date();
        date.setTime(format.parse(dateStr).getTime());
        return new Book(barCode,name,author,publisher,date,count);
    }

    public static boolean register(){
        student=inputStudent();
        return readerService.register(student);
    }

    public static boolean logIn() {
        System.out.println("欢迎使用武汉纺织大学图书管理系统，请登陆");
        System.out.print("请输入身份(学生，管理员):");
        role=scan.next();
        Map<String, Object> map;
        for(int i=1;i<=3;i++){
            System.out.print("请输入ID号:");
            int userId=scan.nextInt();
            System.out.print("请输入密码:");
            String password=scan.next();
            if("学生".equals(role)) {
                student.setId(userId);
                student.setPassword(password);
                map=readerService.checkLogin(student);
            }
            else{
                manager.setId(userId);
                manager.setPassword(password);
                map=managerService.checkLogin(manager);
            }
            if(map.get("code").equals(0)) {
                if(role.equals("学生")) {
                    student = readerService.getById(student.getId());
                }
                else{
                    manager=managerService.getById(manager.getId());
                }
                System.out.println(map.get("msg"));
                return true;
            }
            else if(map.get("code").equals(2)){
                System.out.println("请注册");
                if(register()){
                    System.out.println("注册成功!");
                    i=0;
                }
                else{
                    System.out.println("注册失败!");
                }
            }
            else {
                System.out.println(map.get("msg"));
            }
        }
        return false;
    }

    public static void studentOperate(){
        int choice=0;
        while(choice!=6) {
            System.out.println("------------------------------");
            System.out.println("1.查询图书  2.借阅图书  3.归还图书");
            System.out.println("4.查询借阅记录  5.修改密码  6.退出");
            System.out.println("------------------------------");
            System.out.print("请输入选择:");
            choice= scan.nextInt();
            switch (choice){
                case 1:
                    System.out.println("请输入图书名称:");
                    String name=scan.next();
                    List<Book> books=bookService.queryByName(name);
                    System.out.println("条形码\t书名\t作者\t出版商\t出版日期\t在馆数量");
                    for(Book book:books){
                        System.out.println(book.toString());
                    }
                    break;
                case 2:
                    System.out.println("请输入图书名称:");
                    String name1=scan.next();
                    List<Book> books1=bookService.queryByName(name1);
                    System.out.println("条形码\t书名\t作者\t出版商\t出版日期\t在馆数量");
                    for(Book book:books1){
                        System.out.println(book.toString());
                    }
                    System.out.print("请输入借阅书籍条形码:");
                    int barCode=scan.nextInt();
                    Book book=bookService.queryByBarCode(barCode);


                    if(book==null){
                        System.out.println("无此图书");
                        break;
                    }
                    System.out.println("请输入借阅天数");
                    int days=scan.nextInt();
                    Date date1=new Date(System.currentTimeMillis());
                    Date date2=new Date();
                    date2.setTime(date1.getTime()+86400000L*days);
                    if(borrowService.borrowBook(student,book,date1,date2)){
                        System.out.println("借阅成功");
                    }
                    else{
                        System.out.println("借阅失败");
                    }
                    break;
                case 3:
                    System.out.print("请输入归还图书的条形码:");
                    int barCode1=scan.nextInt();
                    Book book1=bookService.queryByBarCode(barCode1);
                    if(book1==null){
                        System.out.println("条形码输入错误");
                        break;
                    }
                    if(borrowService.returnBook(student,book1)){
                        System.out.println("归还成功");
                    }
                    else{
                        System.out.println("归还失败");
                    }
                    break;
                case 4:
                    System.out.println("以下为您的全部借阅记录:");
                    List<Borrow> borrowList=borrowService.queryByStudentId(student.getId());
                    System.out.println("学号\t条形码\t借阅日期\t归还日期\t是否归还");
                    for(Borrow borrow:borrowList){
                        System.out.println(borrow.toString());
                    }
                    break;
                case 5:
                    System.out.print("请输入您的新密码:");
                    String newPassword=scan.next();
                    student.setPassword(newPassword);
                    if(readerService.changeInfo(student)) {
                        System.out.println("修改成功!");
                    }
                    else{
                        System.out.println("修改失败!");
                    }
                    break;
                case 6:
                    System.out.println("您确认退出页面吗?（yes/no）:");
                    String op=scan.next();
                    if("no".equals(op)){
                        choice=0;
                    }
                    else{
                        return;
                    }
                    break;
                default:break;
            }
        }
    }

    private static void managerOperate() throws Exception {
        int choice=0;
        while(choice!=11) {
            System.out.println("------------------------------");
            System.out.println("1.图书增加  2.图书删除  3.图书修改");
            System.out.println("4.读者增加  5.读者删除  6.读者修改");
            System.out.println("7.查询借阅  8.借阅统计  11.退出");
            System.out.println("9.导出书籍至Excel  10.导出借阅信息至Excel");
            System.out.println("------------------------------");
            System.out.println("请输入选择:");
            choice= scan.nextInt();
            switch (choice){
                case 1:
                    Book book=inputBook();
                    if(bookService.insert(book)>0){
                        System.out.println("增加成功!");
                    }
                    else{
                        System.out.println("增加失败!");
                    }
                    break;
                case 2:
                    System.out.print("请示入条形码:");
                    int barCode1=scan.nextInt();
                    if(bookService.delete(barCode1)>0){
                        System.out.println("删除成功");
                    }
                    else{
                        System.out.println("删除失败");
                    }
                    break;
                case 3:
                    Book book1=inputBook();
                    if(bookService.update(book1)>0){
                        System.out.println("修改成功!");
                    }
                    else{
                        System.out.println("修改失败");
                    }
                    break;
                case 4:
                    if(register()){
                        System.out.println("增加成功");
                    }
                    else{
                        System.out.println("增加失败");
                    }
                    break;
                case 5:
                    System.out.println("输入学生学号");
                    int sId=scan.nextInt();
                    if(readerService.deleteReader(sId)>0){
                        System.out.println("删除成功");
                    }
                    else{
                        System.out.println("删除失败");
                    }
                    break;
                case 6:
                    Student student1=inputStudent();
                    if(readerService.modify(student1)){
                        System.out.println("修改成功");
                    }
                    else{
                        System.out.println("修改失败");
                    }
                    break;
                case 7:
                    System.out.println("选择查询选项:1.学号  2.全部");
                    int op= scan.nextInt();
                    List<Borrow> borrowList;
                    if(op==1){
                        System.out.println("请输入学号");
                        int id= scan.nextInt();
                        borrowList=borrowService.queryByStudentId(id);
                    }
                    else{
                        borrowList=borrowService.queryAllBorrowRecords();
                    }
                    System.out.println("一共有"+borrowList.size()+"条记录");
                    System.out.println("学号\t条形码\t借阅日期\t归还日期\t是否归还");
                    for(Borrow borrow:borrowList){
                        System.out.println(borrow.toString());
                    }
                    break;
                case 8:
                    System.out.println("选择统计选项:1.所有未归还图书  2.超过归还时间未归还");
                    int op1=scan.nextInt();
                    List<Borrow> borrows;
                    if(op1==1){
                        borrows=borrowService.queryAllNotReturnBook();
                    }
                    else{
                        Date date=new Date(System.currentTimeMillis());
                        borrows=borrowService.queryOvertimeRecords(date);
                    }
                    System.out.println("一共有"+borrows.size()+"条记录");
                    System.out.println("学号\t条形码\t借阅日期\t归还日期\t是否归还");
                    for(Borrow borrow:borrows){
                        System.out.println(borrow.toString());
                    }
                    break;
                case 9:
                    fileService.writeBookToExcel("src/main/resources/excel/book.xls");
                    break;
                case 10:
                    fileService.writeBorrowToExcel("src/main/resources/excel/borrowInfo.xls");
                    break;
                case 11:
                    System.out.println("您确认退出页面吗?（yes/no）:");
                    String op2=scan.next();
                    if("no".equals(op2)){
                        choice=0;
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if(!logIn()){
            return;
        }
        int choice=0;
        while(choice!=2) {
            System.out.println("---欢迎光临武汉纺织大学图书管理系统---");
            System.out.println("    1.进入个人页面  2.退出系统");
            System.out.println("--------------------------------");
            System.out.print("请输入选择:");
            choice= scan.nextInt();
            switch (choice){
                case 1:
                    if(role.equals("学生")) {
                        studentOperate();
                    }
                    else{
                        managerOperate();
                    }
                    break;
                case 2:
                    System.out.println("您确认退出系统吗?（yes/no）:");
                    String op=scan.next();
                    if("no".equals(op)){
                        choice=0;
                    }
                    break;
                default:break;
            }
        }
    }

}
