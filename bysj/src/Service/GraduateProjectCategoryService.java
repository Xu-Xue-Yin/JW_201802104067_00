//信管182 徐学印 201802104067
package Service;

import dao.GraduateProjectCategoryDao;
import domain.GraduateProjectCategory;

import java.sql.SQLException;
import java.util.Collection;

public final class GraduateProjectCategoryService {
    private GraduateProjectCategoryDao graduateProjectCategoryDao = GraduateProjectCategoryDao.getInstance();
    private static GraduateProjectCategoryService graduateProjectCategoryService = new GraduateProjectCategoryService();

    private GraduateProjectCategoryService() {
    }

    public synchronized static GraduateProjectCategoryService getInstance() {
        return graduateProjectCategoryService;
    }

    //查找所有对象方法
    public Collection<GraduateProjectCategory> findAll() throws SQLException {
        return graduateProjectCategoryDao.findAll();
    }

    //根据id查找对象的方法
    public GraduateProjectCategory find(Integer id) throws SQLException {
        return graduateProjectCategoryDao.find(id);
    }

    //修改方法
    public void update(GraduateProjectCategory graduateProjectCategory) throws SQLException {
        graduateProjectCategoryDao.update(graduateProjectCategory);
    }

    //添加方法
    public void add(GraduateProjectCategory graduateProjectCategory) throws SQLException {
        graduateProjectCategoryDao.add(graduateProjectCategory);
    }

    //删除方法
    public void delete(Integer id) throws SQLException {
        GraduateProjectCategory graduateProjectCategory = this.find(id);
        this.delete(graduateProjectCategory);
    }

    //删除方法
    public void delete(GraduateProjectCategory graduateProjectCategory) throws SQLException {
        graduateProjectCategoryDao.delete(graduateProjectCategory);
    }
}
