package mapper;

import vo.Student;

import java.util.List;

public interface StudentMapper {
    int insert(Student student);
    int delete(int id);
    int update(Student student);
    Student queryById(int id);
    List<Student> queryByName(String name);
    List<Student> queryAllStudents();
}
