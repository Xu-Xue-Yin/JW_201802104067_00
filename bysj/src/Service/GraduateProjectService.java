//信管182 徐学印 201802104067
package Service;

import dao.GraduateProjectDao;
import domain.GraduateProject;

import java.sql.SQLException;
import java.util.Collection;

public final class GraduateProjectService {
    private GraduateProjectDao graduateProjectDao = GraduateProjectDao.getInstance();
    private static GraduateProjectService graduateProjectService = new GraduateProjectService();

    private GraduateProjectService() {
    }

    public static GraduateProjectService getInstance() {
        return graduateProjectService;
    }

    //查找所有对象的方法
    public Collection<GraduateProject> findAll() throws SQLException {
        return graduateProjectDao.findAll();
    }

    //根据teacher_id查找方法
    public Collection<GraduateProject> findAllByTeacher(int teacherID) throws SQLException {
        return graduateProjectDao.findAllByTeacher(teacherID);
    }

    //添加方法
    public void add(GraduateProject project) throws SQLException {
        graduateProjectDao.add(project);
    }

    //修改方法
    public void update(GraduateProject project) throws SQLException {
        graduateProjectDao.update(project);
    }

    //根据id查找方法
    public GraduateProject find(Integer id) throws SQLException {
        return graduateProjectDao.find(id);
    }

    //删除方法
    public void delete(int id) throws SQLException {
        GraduateProject graduateProject = graduateProjectDao.find(id);
        graduateProjectDao.delete(graduateProject);
    }

    //删除方法
    public void delete(GraduateProject graduateProject) {
        graduateProjectDao.delete(graduateProject);
    }
}
