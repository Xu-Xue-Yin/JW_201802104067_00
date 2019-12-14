//信管182 徐学印 201802104067
package Service;

import dao.TeacherDao;
import domain.Teacher;

import java.sql.SQLException;
import java.util.Collection;

public final class TeacherService {
    private static TeacherDao teacherDao = TeacherDao.getInstance();
    private static TeacherService teacherService = new TeacherService();

    private TeacherService() {
    }

    public static TeacherService getInstance() {
        return teacherService;
    }

    //查找所有对象的方法
    public Collection<Teacher> findAll() throws SQLException {
        return teacherDao.findAll();
    }

    //根据id查找的方法
    public Teacher find(Integer id) throws SQLException {
        return teacherDao.find(id);
    }

    //修改方法
    public boolean update(Teacher teacher) throws SQLException {
        return teacherDao.update(teacher);
    }

    //添加方法
    public boolean add(Teacher teacher) throws SQLException {
        return teacherDao.add(teacher);
    }

    //删除方法
    public boolean delete(Integer id) throws SQLException {
        Teacher teacher = this.find(id);
        return teacherDao.delete(teacher);
    }

    //删除方法
    public boolean delete(Teacher teacher) throws SQLException {
        return teacherDao.delete(teacher);
    }
}
