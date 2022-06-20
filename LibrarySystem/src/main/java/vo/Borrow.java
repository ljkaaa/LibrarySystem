package vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Borrow {
    int sId;
    int barCode;
    Date borrowDate;
    Date returnDate;
    String isReturn;
    public Borrow() {
    }

    public Borrow(int sId, int barCode, Date borrowDate, Date returnDate,String isReturn) {
        this.sId = sId;
        this.barCode = barCode;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturn=isReturn;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    @Override
    public String toString() {
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        return ""+sId+"\t"+barCode+"\t"+d.format(borrowDate)+"\t"+d.format(returnDate)+"\t"+isReturn;
    }
}
