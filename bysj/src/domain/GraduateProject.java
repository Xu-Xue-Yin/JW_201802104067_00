//信管182 徐学印 201802104067
package domain;

import java.io.Serializable;

public final class GraduateProject implements Serializable,Comparable<GraduateProject>{
	//声明变量
	private Integer id;
	private String title;
	private GraduateProjectCategory graduateProjectCategory;
	private GraduateProjectType graduateProjectType;
	private Teacher teacher;
	//构造器
	public GraduateProject() {
		super();
	}
	public GraduateProject(int id, String title, GraduateProjectCategory graduateProjectCategory,
						   GraduateProjectType graduateProjectType, Teacher teacher) {
		this(title, graduateProjectCategory, graduateProjectType,teacher);
		this.id = id;
	}

	public GraduateProject(String title, GraduateProjectCategory graduateProjectCategory,
						   GraduateProjectType graduateProjectType, Teacher teacher) {
		super();
		this.title = title;
		this.graduateProjectCategory = graduateProjectCategory;
		this.graduateProjectType = graduateProjectType;
		this.teacher = teacher;
	}
	//获取和设置id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	//获取和设置title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	//获取和设置graduateProjectCategory
	public GraduateProjectCategory getGraduateProjectCategory() {
		return graduateProjectCategory;
	}
	public void setGraduateProjectCategory(GraduateProjectCategory graduateProjectCategory) {
		this.graduateProjectCategory = graduateProjectCategory;
	}
	//获取和设置graduateProjectType
	public GraduateProjectType getGraduateProjectType() {
		return graduateProjectType;
	}
	public void setGraduateProjectType(GraduateProjectType graduateProjectType) {
		this.graduateProjectType = graduateProjectType;
	}
	//获取和设置teacher
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "GraduateProject ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "title = " + this.title + TAB
	        + "projectCategrory = " + this.graduateProjectCategory + TAB
	        + "graduateProjectType = " + this.graduateProjectType + TAB
	        + "teacher = " + this.teacher + TAB
	        + " )";
	
	    return retValue;
	}

	@Override
	public int compareTo(GraduateProject o) {
		// TODO Auto-generated method stub
		return this.id-o.getId();
	}
}
