package com.aryabhatta.enums;
public enum CardNumberEnum {
	A (1),
	K (13),
	Q (12),
	J (11),
	Ten (10),
	Nine (9),
	Eight (8),
	SEVEN (7),
	SIX (6),
	FIVE (5),
	FOUR (4),
	Three (3),
	Two (2);
	
	private int cardValue;
	
	private CardNumberEnum(int cardValue){
		this.cardValue = cardValue;
	}
	
	public int getCardValue(){
		return this.cardValue;
	}
}
