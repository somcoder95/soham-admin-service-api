package com.idexcel.sohamadminservice.dto;

public class LenderPostDto {
	private String name;
	private addressDto address;
	private primaryContactDto primaryContact;
	
	public LenderPostDto()
	{
		
	}
	
	public LenderPostDto(String name, addressDto address, primaryContactDto primaryContact) {
		
		this.name = name;
		this.address = address;
		this.primaryContact = primaryContact;
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

	
	

}
