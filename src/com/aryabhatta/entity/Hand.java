package com.aryabhatta.entity;

import java.util.List;

public class Hand {
	private String handName;
	private List<Card> handCards;
	private boolean isHandComplete;
	private int handRank;
	
	public String getHandName() {
		return handName;
	}
	public void setHandName(String handName) {
		this.handName = handName;
	}
	public List<Card> getHandCards() {
		return handCards;
	}
	public void setHandCards(List<Card> handCards) {
		this.handCards = handCards;
	}
	public boolean isHandComplete() {
		return isHandComplete;
	}
	public void setHandComplete(boolean isHandComplete) {
		this.isHandComplete = isHandComplete;
	}
	public int getHandRank() {
		return handRank;
	}
	public void setHandRank(int handRank) {
		this.handRank = handRank;
	}
}