package com.cg.oms.beans;

import java.util.Date;

public class Card {

	private String card_number;//format 1111-1111-1111-1111
	private Date expiry_date;// format MM/YY
	private int cvv_number;
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	public int getCvv_number() {
		return cvv_number;
	}
	public void setCvv_number(int cvv_number) {
		this.cvv_number = cvv_number;
	}
	@Override
	public String toString() {
		return "Card [card_number=" + card_number + ", expiry_date=" + expiry_date + ", cvv_number=" + cvv_number + "]";
	}
	
	
}
