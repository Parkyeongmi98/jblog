package com.douzone.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String id;
	private Long length;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", id=" + id + ", length=" + length + "]";
	}

}
