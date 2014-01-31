package com.example.sidemenu;

public class ProductTable {
	public int idno;
	public String productname="";
	public String productprice="";
	public byte[] productimage;
	
	public int getIdno()
	{
		return idno;
	}
	public void setIdno(int id)
	{
		 this.idno=id;
	}
}
