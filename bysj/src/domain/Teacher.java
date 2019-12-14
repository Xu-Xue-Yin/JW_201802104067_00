//信管182 徐学印 201802104067
package domain;

import java.io.Serializable;

public final class Teacher implements Comparable<Teacher>,Serializable {
	//声明变量
	private Integer id;
	private String name;
	private String no;
	//构造器
	public Teacher(Integer id, String name, String no) {
		this(name);
		this.id = id;
		this.no = no;
	}
	public Teacher(String name) {
		super();
		this.name = name;
	}
	//获取和设置id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	//获取和设置name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//获取和设置no
	public void setNo(String no) {
		this.no = no;
	}
	public String getNo() {
		return no;
	}
	@Override
	public int compareTo(Teacher o) {
		// TODO Auto-generated method stub
		return this.id-o.getId();
	}

	public String toString()
	{
	    final String TAB = "    ";
	    String retValue = "";
	    retValue = "Teacher ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "name = " + this.name + TAB
	        + " )";
	    return retValue;
	}
}
