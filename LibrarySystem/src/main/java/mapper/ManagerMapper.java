package mapper;

import vo.Manager;
import vo.Student;

import java.util.List;

public interface ManagerMapper {
    int insert(Manager manager);
    int delete(int id);
    int update(Manager manager);
    Manager queryById(int id);
    List<Manager> queryByName(String name);
    List<Manager> queryAllManagers();
}
