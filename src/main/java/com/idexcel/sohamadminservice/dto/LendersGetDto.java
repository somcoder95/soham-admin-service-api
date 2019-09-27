package com.idexcel.sohamadminservice.dto;

public class LendersGetDto {
private String Id;
private String name;
private String status;
public LendersGetDto()
{
	
}
public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
