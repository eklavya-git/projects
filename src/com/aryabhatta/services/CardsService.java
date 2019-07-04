package com.aryabhatta.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aryabhatta.common.CommonUtils;
import com.aryabhatta.common.Constants;
import com.aryabhatta.entity.Card;
import com.aryabhatta.entity.Hand;
import com.aryabhatta.rules.RulesUtils;

public class CardsService {

	/*public Hand createPairHand(List<ArrayList<Card>> cards) {
		Hand hand = new Hand();
		List<Card> handCards = null;
		
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (!cards.get(i).isEmpty() && cards.get(i).size() == 2) {
				handCards = cards.get(i);
				break;
			}
		}
		hand.setHandCards(handCards);
		return hand;
	}*/

	public Hand createTwoPairHand(List<ArrayList<Card>> cards) {
		Hand hand = new Hand();
		List<Card> handCards = new ArrayList<>(4);
		int counter = 0;
		
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (null != cards.get(i) && cards.get(i).size() == 2) {
				handCards.addAll(cards.get(i));
				if (++ counter == 2){
					break;
				}
			}
		}
		hand.setHandCards(handCards);
		return hand;
	}

	/*public Hand createThreeOfAKindHand(List<ArrayList<Card>> cards) {
		Hand hand = new Hand();
		
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (!cards.get(i).isEmpty() && cards.get(i).size() == 3) {
				hand.setHandCards(cards.get(i));
				break;
			}
		}
		return hand;
	}*/

	public Hand createAStraightHand(List<ArrayList<Card>> cards) {
		Hand hand = new Hand();
		int counter = 0;
		List<Card> handCards = new ArrayList<>(5);
		
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (!cards.get(i).isEmpty() && cards.get(i).size() > 0) {
				handCards.add(cards.get(i).get(0));
				if ( ++ counter == 5){
					hand.setHandCards(handCards);
					break;
				}
			}else{
				counter = 0;
				handCards.clear();
			}
		}
		
		return hand;
	}
	
	private void deleteCardFromList (int start, int end, List<ArrayList<Card>> cards){
		String cardValue = null;
		for (int i=start; i<end; i++){
			cardValue = cards.get(i).get(0).getValue();
			
			//Remove 'A' from 1st location as well.
			if (cardValue.equals(Constants.ACE)){
				cards.get(1).remove(0);
			}
			cards.get(i).remove(0);
		}
	}
	
	private void deleteCardFromList (List<Card> cardsToBeRemoved, List<ArrayList<Card>> cards){
		
		List<Card> flatList = CommonUtils.getFlatListFromJaggedList(cards);
		
		flatList.removeAll(cardsToBeRemoved);
		/*String cardValue = null;
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (!cards.get(i).isEmpty()) {
				for (int j=0; j<cards.get(i).size(); j++){
					if (cards.get(i).get(j).equals(Constants.SPADE)) 
					else if (cards.get(i).get(j).getSuit().equals(Constants.DIAMOND)) 
					else if (cards.get(i).get(j).getSuit().equals(Constants.HEART)) 
					else if (cards.get(i).get(j).getSuit().equals(Constants.CLUB)) 
				}
			}
		}*/
	}

	public Hand createFlushHand(List<ArrayList<Card>> cards) {
		Hand hand = new Hand();
		List<Card> flushList = createFlushList (cards);
		hand.setHandCards(flushList);
		return hand;
	}
	
	private List<Card> createFlushList (List<ArrayList<Card>> cards){
		List<Card> allCardsOfSuit = null;
		List<Card> flatList = CommonUtils.getFlatListFromJaggedList(cards);
		Map<String, List<Card>> cardsPerSuitMap = CommonUtils.getCardsPerSuitMap(flatList);
		
		for (List<Card> cardList : cardsPerSuitMap.values()){
			allCardsOfSuit = CommonUtils.effectiveListWithNoNulls(cardList);
			if (allCardsOfSuit.size() >= 5) {
				break;
			}
		}
		return CommonUtils.getTopNCardsFromList (allCardsOfSuit, 5);
	}

	public Hand createFullHouseHand(List<ArrayList<Card>> cards) {
		Hand hand = new Hand();
		List<Card> fullHouseList = new ArrayList<Card>();
		
		List<Card> threeOfAKindList = createNOfAKindHand(cards, 3).getHandCards();
		List<Card> pairList = createNOfAKindHand(cards, 2).getHandCards();
		
		fullHouseList.addAll(threeOfAKindList);
		fullHouseList.addAll(pairList);
		hand.setHandCards(fullHouseList);
		
		return hand;
	}

	public Hand createNOfAKindHand(List<ArrayList<Card>> cards, int n) {
		Hand hand = new Hand();
		List<Card> handCards = null;
		
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (!cards.get(i).isEmpty() && cards.get(i).size() == n) {
				handCards = cards.get(i);
				break;
			}
		}
		hand.setHandCards(handCards);
		return hand;
	}

	public Hand createHighCardHand(List<ArrayList<Card>> cards, int n) {
		Hand hand = new Hand();
		List<Card> flatList = CommonUtils.getFlatListFromJaggedList(cards);
		List<Card> handCards = CommonUtils.getTopNCardsFromList (flatList, n);
		
		hand.setHandCards(handCards);
		return hand;
	}
	
	public List<Card> getTopNCardsFromList(List<ArrayList<Card>> cards, int n) {
		List<Card> flatList = CommonUtils.getFlatListFromJaggedList(cards);
		List<Card> topCardsList = CommonUtils.getTopNCardsFromList (flatList, n);
		return topCardsList;
	}
}