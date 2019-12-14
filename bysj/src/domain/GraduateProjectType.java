//信管182 徐学印 201802104067
package domain;

import java.io.Serializable;

public final class GraduateProjectType implements Comparable<GraduateProjectType>,Serializable  {
	//声明变量
	private Integer id;
	private String description;
	private String no;
	private String remarks;
	//构造器
	public GraduateProjectType(Integer id, String description, String no, String remarks) {
		super();
		this.id = id;
		this.description = description;
		this.no = no;
		this.remarks = remarks;
	}
	//获得id description no remarks
	public Integer getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getNo() {
		return no;
	}
	public String getRemarks() {
		return remarks;
	}

	public String toString()
	{
	    final String TAB = "    ";
	    String retValue = "";
	    retValue = "GraduateProjectType ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "description = " + this.description + TAB
	        + "no = " + this.no + TAB
	        + "remarks = " + this.remarks + TAB
	        + " )";
	    return retValue;
	}

	@Override
	public int compareTo(GraduateProjectType o) {
		// TODO Auto-generated method stub
		return this.id-o.id;
	}
}
