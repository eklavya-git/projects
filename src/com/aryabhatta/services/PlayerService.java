package com.aryabhatta.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aryabhatta.common.CommonUtils;
import com.aryabhatta.common.Constants;
import com.aryabhatta.entity.Card;
import com.aryabhatta.entity.Hand;
import com.aryabhatta.entity.Player;

public class PlayerService {
	
	ApplyRulesService rulesService = new ApplyRulesService();
	
	public void populatePlayerDetails (Player player){
		List<ArrayList<Card>> playerOriginalList = player.getInputCards();
		List<ArrayList<Card>> residualCardList = new ArrayList<ArrayList<Card>>(15);
		CommonUtils.initializeList(residualCardList);
		Collections.copy(residualCardList, playerOriginalList);
		player.setResidualCards(residualCardList);
		
		populatePlayerHands(player);
	}
	
	public void populatePlayerHands(Player player){
		
		List<Hand> hands = new ArrayList<Hand> (3);
		
		Hand backHand = createHand(player, Constants.BACK_HAND);
		Hand middleHand = createHand(player, Constants.MIDDLE_HAND);
		Hand frontHand = createHand(player, Constants.FRONT_HAND);
		
		hands.add(backHand); hands.add(middleHand); hands.add(frontHand);
		hands = populateIncompleteHands(hands, player.getResidualCards());
		
		player.setHands(hands);
	}
	
	private List<Hand> populateIncompleteHands(List<Hand> hands, List<ArrayList<Card>> residualList) {
		int completeHandSize = 0;
		int handSize = 0;
		int deficit = completeHandSize - handSize;
		for (Hand hand : hands){
			completeHandSize = hand.getHandName().equals(Constants.FRONT_HAND) ? 3 : 5;
			handSize = hand.getHandCards().size();
			deficit = completeHandSize - handSize;
			
			if (deficit > 0){
				rulesService.completePlayerHand(residualList, hand, deficit);
//				updateResidualCardsList (residualList, hand);
			}
		}
		return hands;
	}

	private Hand createHand (Player player, String handName){
		Hand hand = rulesService.applyRulesAndCreateHand (player.getResidualCards(), handName);
		hand.setHandName(handName);
		
		player.setResidualCards(updateResidualCardsList (player.getResidualCards(), hand));
		
		return hand;
	}
	
	private List<ArrayList<Card>> updateResidualCardsList (List<ArrayList<Card>> residualList, Hand hand){
		residualList = CommonUtils.removeCardsFromList(residualList, hand.getHandCards());
		return residualList;
	}
}