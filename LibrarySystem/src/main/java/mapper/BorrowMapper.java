package mapper;

import org.apache.ibatis.annotations.Param;
import vo.Borrow;

import java.util.Date;
import java.util.List;

public interface BorrowMapper {
    int insert(Borrow borrow);
    int delete(@Param("sId") int sId,@Param("barCode") int barCode);
    int update(Borrow borrow);
    Borrow queryOneRecord(@Param("sId") int sId,@Param("barCode") int barCode);
    List<Borrow> queryBySId(int SId);
    List<Borrow> queryRecordsByBorrowDate(@Param("borrowDate1")Date borrowDate1,@Param("borrowDate2")Date borrowDate2);
    List<Borrow> queryOvertimeRecords(Date date);
    List<Borrow> queryAllNotReturnBook();
    List<Borrow> queryAllBorrowRecords();
}
