package com.idexcel.sohamadminservice.dto;

public class LenderPatchDto {
	private String Id;
	private String status;
	public LenderPatchDto()
	{
		
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
