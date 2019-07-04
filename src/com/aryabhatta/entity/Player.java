package com.aryabhatta.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {
	private String playerName;
	private List<ArrayList<Card>> inputCards;
	private List<ArrayList<Card>> residualCards;
	private List<Hand> hands;
	
	private Map<String, ScoreResult> playerScoreMap;
	
	public Player (String playerName){
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public List<ArrayList<Card>> getInputCards() {
		return inputCards;
	}
	public void setInputCards(List<ArrayList<Card>> inputCards) {
		this.inputCards = inputCards;
	}
	public List<Hand> getHands() {
		return hands;
	}
	public void setHands(List<Hand> hands) {
		this.hands = hands;
	}
	public List<ArrayList<Card>> getResidualCards() {
		return residualCards;
	}
	public void setResidualCards(List<ArrayList<Card>> residualCards) {
		this.residualCards = residualCards;
	}

	public Map<String, ScoreResult> getPlayerScoreMap() {
		return playerScoreMap;
	}

	public void setPlayerScoreMap(Map<String, ScoreResult> playerScoreMap) {
		this.playerScoreMap = playerScoreMap;
	}
}