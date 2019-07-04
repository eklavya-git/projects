package com.aryabhatta.services;

import java.util.ArrayList;
import java.util.List;

import com.aryabhatta.common.CommonUtils;
import com.aryabhatta.common.Constants;
import com.aryabhatta.entity.Card;
import com.aryabhatta.entity.Player;

public class GameService {
	public List<Player> parseInputsAndCreatePlayers (String[] args){
		int playerNumber = 0;
		int counter = 0;
		String str = null;
		Player player = null;
		List<ArrayList<Card>> positionalCardsList = null;
		List<Player> players = new ArrayList<Player>(4);
		
		for (int i=0; i<args.length ; i++){
			
			if (counter == 0 || (counter > 10 && counter % 12 == 1)){
				
				if (null != player) {
					players.add(player);
				}
				
				if (null != player && null != positionalCardsList) {
					player.setInputCards(positionalCardsList);
				}
				
				System.out.println("Starting with allocations of cards to new Player");
				player = new Player (Constants.PLAYER+"_" + ++playerNumber);
				positionalCardsList = new ArrayList<ArrayList<Card>>(15);
				CommonUtils.initializeList(positionalCardsList);
			}
			
			str = args[i];
			Card card = new Card (str.substring(0, str.length()-1), str.substring(str.length()-1));
			CommonUtils.placeCardAtItsPositionInList (positionalCardsList,card);
			counter ++;
		}
		
		return players;
	}
	
	public void startGame (List<Player> players){
		PlayerService playerService = new PlayerService();
		for (Player player : players)
			playerService.populatePlayerDetails(player);
	}
}