package com.aryabhatta.enums;

public enum RuleRankEnum {
	ROYAL_LUSH (1),
	STRAIGHT_FLUSH (2),
	FOUR_OF_A_KIND (3),
	FULL_HOUSE (4),
	FLUSH (5),
	STRAIGHT (6),
	THREE_OF_A_KIND (7),
	TWO_PAIR (8),
	ONE_PAIR (9),
	HIGH_CARD (10);
	
	private int ruleRank;
	
	private RuleRankEnum(int ruleRank){
		this.ruleRank = ruleRank;
	}
	
	public int getRuleRank(){
		return this.ruleRank;
	}
}
