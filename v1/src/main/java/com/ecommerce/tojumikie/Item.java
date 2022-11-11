package com.ecommerce.tojumikie;

public class Item {
	private int invoiceno;
	private int customerid;
	private String itemcode;
	private int quantity;
	public Item() {
		super();
	}
	public Item(int invoiceno, int customerid, String itemcode, int quantity) {
		super();
		this.invoiceno = invoiceno;
		this.customerid = customerid;
		this.itemcode = itemcode;
		this.quantity = quantity;
	}
	public int getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(int invoiceno) {
		this.invoiceno = invoiceno;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
