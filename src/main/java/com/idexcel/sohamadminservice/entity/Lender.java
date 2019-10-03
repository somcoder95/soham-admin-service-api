package com.idexcel.sohamadminservice.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="lenders")
public class Lender {

@Id	
private String id;
private String name;
private Address address;
private PrimaryContact primaryContact;
private String status;
private String createdBy;

private Date createdDate;
private String updatedBy;
private Date updatedDate;

public Lender()
{   
}
public Lender(String id, String name, Address address, PrimaryContact primaryContact, String status, String createdBy,
		Date createdDate, String updatedBy, Date updatedDate) {
	
	this.id = id;
	this.name = name;
	this.address = address;
	this.primaryContact = primaryContact;
	this.status = status;
	this.createdBy = createdBy;
	this.createdDate = createdDate;
	this.updatedBy = updatedBy;
	this.updatedDate = updatedDate;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

public PrimaryContact getPrimaryContact() {
	return primaryContact;
}

public void setPrimaryContact(PrimaryContact primaryContact) {
	this.primaryContact = primaryContact;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}

public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

public String getUpdatedBy() {
	return updatedBy;
}

public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}

public Date getUpdatedDate() {
	return updatedDate;
}

public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
}




}
