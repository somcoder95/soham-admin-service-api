package com.idexcel.sohamadminservice.dto;

public class LenderPutDto {
	private String Id;
	private String name;
	private addressDto address;
	private primaryContactDto primaryContact;
	private String status;
	public LenderPutDto()
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
	public addressDto getAddress() {
		return address;
	}
	public void setAddress(addressDto address) {
		this.address = address;
	}
	public primaryContactDto getPrimaryContact() {
		return primaryContact;
	}
	public void setPrimaryContact(primaryContactDto primaryContact) {
		this.primaryContact = primaryContact;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
