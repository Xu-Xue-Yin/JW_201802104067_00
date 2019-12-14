//信管182 徐学印 201802104067
package domain;

import java.io.Serializable;
public final class GraduateProjectCategory implements Comparable<GraduateProjectCategory>,Serializable{
	//声明变量
	private Integer id;
	private String description;
	private String no;
	private String remarks;
	//构造器
	public GraduateProjectCategory(Integer id, String description, String no, String remarks) {
		super();
		this.id = id;
		this.description = description;
		this.no = no;
		this.remarks = remarks;
	}
	//获取和设置id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	//获取和设置description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	//获取和设置no
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	//获取和设置remarks
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String toString()
	{
	    final String TAB = "    ";
	    String retValue = "";
	    retValue = "ProjectCategrory ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "description = " + this.description + TAB
	        + "no = " + this.no + TAB
	        + "remarks = " + this.remarks + TAB
	        + " )";
	    return retValue;
	}

	@Override
	public int compareTo(GraduateProjectCategory o) {
		// TODO Auto-generated method stub
		return this.id-o.getId();
	}
}
