package com.aryabhatta.entity;

public class Card {
	
	private String value;
	private String suit;
	public String getValue() {
		return value;
	}
	public void setValue(String number) {
		this.value = number;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	@Override
	public String toString(){
		return "Card: " + value + " of " + suit;
	}
	
	@Override
	public boolean equals(Object arg0) {

		if (this == arg0) return true;
		
		if (!(arg0 instanceof Card)) return false;
		
		Card card = (Card) arg0;
		
		/* A Card object will be equal iff both the number and 
			suit are equal for the two objects being compared.
		*/
		return value.equals(card.getValue())
				&& suit.equals(card.getSuit());
	}
	public Card(String number, String suit) {
		super();
		this.value = number;
		this.suit = suit;
	}
}