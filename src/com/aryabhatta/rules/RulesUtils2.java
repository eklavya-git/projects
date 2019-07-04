package com.aryabhatta.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aryabhatta.common.CommonUtils;
import com.aryabhatta.entity.Card;

public class RulesUtils2 {

	public static boolean hasAPair(List<ArrayList<Card>> cards){
		boolean hasAPair = false;
		
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (cards.get(i).size() == 2) {
				hasAPair = true;
				break;
			}
		}
		return hasAPair;
	}
	
	public static boolean hasThreeOfAKind(List<ArrayList<Card>> cards){
		boolean hasThreeOfAKind = false;
		
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (cards.get(i).size() == 3) {
				hasThreeOfAKind = true;
				break;
			}
		}
		return hasThreeOfAKind;
	}
	
	public static boolean hasFourOfAKind(List<ArrayList<Card>> cards){
		boolean hasFourOfAKind = false;
		
		for (int i = 14; i > 0; i--){
			if (cards.get(i).size() == 4) {
				hasFourOfAKind = true;
				break;
			}
		}
		return hasFourOfAKind;
	}
	
	public static boolean hasTwoPairs(List<ArrayList<Card>> cards){
		boolean hasTwoPairs = false;
		int counter = 0;
				
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (cards.get(i).size() == 2) {
				if ( ++ counter == 2){
					hasTwoPairs = true;
					break;
				}
			}
		}
		return hasTwoPairs;
	}
	

	/* Straight === 5 cards in sequence of no particular suit.
	 * e.g. 10D, 9H, 8H, 7C, 6S
	*/
	public static boolean hasAStraight(List<ArrayList<Card>> cards){
		boolean hasAStraight = false;
		int counter = 0;
		
		for (int i=cards.size() -1 ; i > 0 ; i--){
			if (cards.get(i).size() > 0) {
				if ( ++ counter == 5){
					hasAStraight = true;
					break;
				}
			}else{
				counter = 0;
			}
		}
		
		return hasAStraight;
	}
	
	/* Straight === 5 cards in sequence of no particular suit.
	 * e.g. 10D, 9H, 8H, 7C, 6S
	*/
	public static boolean hasAStraight(int [] cardsPositionArray){
		boolean hasAStraight = false;
		int counter = 0;
		
		for (int i=cardsPositionArray.length -1 ; i > 0 ; i--){
			if (cardsPositionArray[i] > 0) {
				if ( ++ counter == 5){
					hasAStraight = true;
					break;
				}
			}else{
				counter = 0;
			}
		}
		return hasAStraight;
	}
	
	/* Flush === 5 cards of the same suit. Not in sequence.
	 * e.g. 10D, 2D, 8D, AD, QD
	*/
	public static boolean hasAFlush(List<ArrayList<Card>> cards){
		boolean hasAFlush = false;
		Map<String, Integer> cardsPerSuitMap = null;
		
		List<Card> flatCardList = 
				cards.stream()
		        .flatMap(x -> x.stream())
		        .collect(Collectors.toList());
		
		cardsPerSuitMap = CommonUtils.arrangeCardsPerSuit(flatCardList);
		
		for (String suit : cardsPerSuitMap.keySet()){
			if (cardsPerSuitMap.get(suit) >= 5) {
				hasAFlush = true;
			}
		}
		
		return hasAFlush;
	}

	/* Flush === 5 cards of the same suit AND in sequence.
	 * e.g. 10D, 2D, 8D, AD, QD
	*/
	public static boolean hasAStraightFlush(List<ArrayList<Card>> cards){
		boolean hasAStraightFlush = false;
		List<Card> flatList = 
				cards.stream()
			        .flatMap(List::stream)
			        .collect(Collectors.toList());
		Map<String, int []> cardsPerSuitAndInOrderMap = null;
		
		cardsPerSuitAndInOrderMap = CommonUtils.arrangeCardsPerSuitAndInOrder(flatList);
		
		for (String suit : cardsPerSuitAndInOrderMap.keySet()){
			if (hasAStraight(cardsPerSuitAndInOrderMap.get(suit))) {
				hasAStraightFlush = true;
			}
		}
		return hasAStraightFlush;
	}
	
	/* Royal Flush === A, K, Q, J, 10 of the same suit.
	*/
	public static boolean hasARoyalFlush(List<ArrayList<Card>> cards){
		boolean hasARoyalFlush = false;
		List<Card> flatList = CommonUtils.getFlatListFromJaggedList(cards);
		Map<String, int []> cardsPerSuitAndInOrderMap = null;
		
		cardsPerSuitAndInOrderMap = CommonUtils.arrangeCardsPerSuitAndInOrder(flatList);
		
		for (String suit : cardsPerSuitAndInOrderMap.keySet()){
			if (hasARoyalStraight(cardsPerSuitAndInOrderMap.get(suit))) {
				hasARoyalFlush = true;
			}
		}
		return hasARoyalFlush;
	}

	/* Royal Straight === A, K, Q, J, 10.
	*/
	public static boolean hasARoyalStraight(int[] cardsPositionArray) {
		boolean hasARoyalStraight = false;
		int counter = 0;
		
		for (int i=cardsPositionArray.length -1 ; i > 0 ; i--){
			if (cardsPositionArray[i] > 0) {
				if ( ++ counter == 5){
					hasARoyalStraight = true;
					break;
				}
			}else{
				break;
			}
		}
		return hasARoyalStraight;
	}
	

	/*
	 * A pair and a triplet.
	 * e.g. 6S, 6D, 6C, KH, KC
	*/
	public static boolean hasFullHouse(List<ArrayList<Card>> cards){
		return hasAPair(cards) && hasThreeOfAKind(cards);
	}
}