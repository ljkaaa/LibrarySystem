package service;

import dbc.SessionUtil;
import factory.DAOFactory;
import mapper.ManagerMapper;
import org.apache.ibatis.session.SqlSession;
import vo.Manager;

import java.util.HashMap;
import java.util.Map;

public class ManagerService {
    private SqlSession sqlSession;
    private ManagerMapper managerDAO;
    public ManagerService(){
        sqlSession = SessionUtil.getSqlSession();
        managerDAO = DAOFactory.getManagerDAOInstance(sqlSession);
    }
    public boolean register(Manager manager) {
        if(managerDAO.queryById(manager.getId())==null){
            managerDAO.insert(manager);
            return true;
        }
        return false;
    }

    public Manager getById(int id){
        return managerDAO.queryById(id);
    }

    public Map<String, Object> checkLogin(Manager manager)  {
        Map<String, Object> mapResult = new HashMap<>();
        try {
            Manager foundManager = managerDAO.queryById(manager.getId());
            if (foundManager == null) {
                mapResult.put("code", 1);
                mapResult.put("msg", "用户名不存在！");
            }
            else {
                if (!foundManager.getPassword().equals(manager.getPassword())) {
                    mapResult.put("code", 1);
                    mapResult.put("msg", "密码不正确！");
                }
                else {
                    mapResult.put("code", 0);
                    mapResult.put("msg", "登录成功！");
                }
            }
        }
        catch (Exception e) {
            mapResult.put("code", 2);
            mapResult.put("msg", e.getMessage());
        }
        return mapResult;
    }
}
