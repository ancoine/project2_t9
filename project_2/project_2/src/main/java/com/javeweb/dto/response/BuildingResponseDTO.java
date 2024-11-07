package com.javeweb.dto.response;

public class BuildingResponseDTO {
private Long id;
private String name;
private String address;
private  String numberOfBasement;
private String managername;
private Long managerphonenumber;
private Double floorarea;
private Double vacantArea;      
private Double rentalArea;    
private Long rentPrice;        
private String serviceFee;  
private String brokerageFee;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getNumberOfBasement() {
	return numberOfBasement;
}
public void setNumberOfBasement(String numberOfBasement) {
	this.numberOfBasement = numberOfBasement;
}
public String getManagername() {
	return managername;
}
public void setManagername(String managername) {
	this.managername = managername;
}
public Long getManagerphonenumber() {
	return managerphonenumber;
}
public void setManagerphonenumber(Long managerphonenumber) {
	this.managerphonenumber = managerphonenumber;
}
public Double getFloorarea() {
	return floorarea;
}
public void setFloorarea(Double floorarea) {
	this.floorarea = floorarea;
}
public Double getVacantArea() {
	return vacantArea;
}
public void setVacantArea(Double vacantArea) {
	this.vacantArea = vacantArea;
}
public Double getRentalArea() {
	return rentalArea;
}
public void setRentalArea(Double rentalArea) {
	this.rentalArea = rentalArea;
}
public Long getRentPrice() {
	return rentPrice;
}
public void setRentPrice(Long rentPrice) {
	this.rentPrice = rentPrice;
}
public String getServiceFee() {
	return serviceFee;
}
public void setServiceFee(String serviceFee) {
	this.serviceFee = serviceFee;
}
public String getBrokerageFee() {
	return brokerageFee;
}
public void setBrokerageFee(String string) {
	this.brokerageFee = string;
}


}
