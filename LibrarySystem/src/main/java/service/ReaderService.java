package service;

import dbc.SessionUtil;
import factory.DAOFactory;
import mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import vo.Book;
import vo.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReaderService {
    private SqlSession sqlSession;
    private StudentMapper studentDAO;
    public ReaderService(){
        sqlSession = SessionUtil.getSqlSession();
        studentDAO= DAOFactory.getStudentDAOInstance(sqlSession);
    }
    public boolean register(Student student) {
        if(studentDAO.queryById(student.getId())==null){
            studentDAO.insert(student);
            return true;
        }
        return false;
    }

    public boolean modify(Student student){
        if(studentDAO.update(student)>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Student getById(int id){
        return studentDAO.queryById(id);
    }

    public Map<String, Object> checkLogin(Student student)  {
        Map<String, Object> mapResult = new HashMap<>();
        try {
            Student foundStudent = this.studentDAO.queryById(student.getId());
            if (foundStudent == null) {
                mapResult.put("code", 2);
                mapResult.put("msg", "此学号未注册！");
            }
            else {
                if (!foundStudent.getPassword().equals(student.getPassword())) {
                    mapResult.put("code", 1);
                    mapResult.put("msg", "密码不正确！");
                }
                else {
                    student=this.studentDAO.queryById(student.getId());
                    mapResult.put("code", 0);
                    mapResult.put("msg", "登录成功！");
                }
            }
        }
        catch (Exception e) {
            mapResult.put("code", 3);
            mapResult.put("msg", e.getMessage());
        }
        return mapResult;
    }

    public boolean changeInfo(Student student){
        studentDAO.update(student);
        return true;
    }

    public int deleteReader(int sId){
        return studentDAO.delete(sId);
    }

}
