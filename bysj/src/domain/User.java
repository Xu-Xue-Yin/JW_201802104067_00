//信管182 徐学印 201802104067
package domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Comparable<User>, Serializable {
    //声明变量
    private Integer id;
    private String username;
    private String password;
    private Date loginTime;
    private Teacher teacher;

    //构造器
    public User() {
    }

    public User(Integer id, String username, String password, Date loginTime,
                Teacher teacher) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.loginTime = loginTime;
        this.teacher = teacher;
    }

    public User(String username, String password, Date loginTime, Teacher teacher) {
        super();
        this.username = username;
        this.password = password;
        this.loginTime = loginTime;
        this.teacher = teacher;
    }

    //获取和设置id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //获取设置username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //获取和设置password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //获取和设置loginTime
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    //获取和设置teacher
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String toString() {
        final String TAB = "    ";
        String retValue = "";
        retValue = "Login ( "
                + super.toString() + TAB
                + "id = " + this.id + TAB
                + "username = " + this.username + TAB
                + "password = " + this.password + TAB
                + "teacher = " + this.teacher + TAB
                + " )";
        return retValue;
    }

    @Override
    public int compareTo(User o) {
        // TODO Auto-generated method stub
        return this.id - o.id;
    }
}
