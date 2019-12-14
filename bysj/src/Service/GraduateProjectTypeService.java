//信管182 徐学印 201802104067
package Service;

import dao.GraduateProjectTypeDao;
import domain.GraduateProjectType;

import java.sql.SQLException;
import java.util.Collection;

public final class GraduateProjectTypeService {
    private static GraduateProjectTypeService graduateProjectTypeService = new GraduateProjectTypeService();
    private GraduateProjectTypeDao graduateProjectTypeDao = GraduateProjectTypeDao.getInstance();

    private GraduateProjectTypeService() {
    }

    public static GraduateProjectTypeService getInstance() {
        return graduateProjectTypeService;
    }

    //查找所有对象的方法
    public Collection<GraduateProjectType> findAll() throws SQLException {
        return graduateProjectTypeDao.findAll();
    }

    //根据id查找方法
    public GraduateProjectType find(Integer id) throws SQLException {
        return graduateProjectTypeDao.find(id);
    }

    //修改方法
    public void update(GraduateProjectType graduateProjectType) throws SQLException {
        graduateProjectTypeDao.update(graduateProjectType);
    }

    //添加方法
    public void add(GraduateProjectType graduateProjectType) throws SQLException {
        graduateProjectTypeDao.add(graduateProjectType);
    }

    //删除方法
    public void delete(Integer id) throws SQLException {
        GraduateProjectType graduateProjectType = this.find(id);
        this.delete(graduateProjectType);
    }

    //删除方法
    public void delete(GraduateProjectType graduateProjectType) throws SQLException {
        graduateProjectTypeDao.delete(graduateProjectType);
    }
}
