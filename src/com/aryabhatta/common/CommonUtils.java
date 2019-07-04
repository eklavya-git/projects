package com.aryabhatta.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aryabhatta.entity.Card;

public class CommonUtils {

	public static int getIndex (String c){
		int value = 0;
		switch (c){
			case "1": value = 1; break;
			case "2": value = 2; break;
			case "3": value = 3; break;
			case "4": value = 4; break;
			case "5": value = 5; break;
			case "6": value = 6; break;
			case "7": value = 7; break;
			case "8": value = 8; break;
			case "9": value = 9; break;
			case "10": value = 10; break;
			case "J": value = 11; break;
			case "Q": value = 12; break;
			case "K": value = 13; break;
			case "A": value = 14; break;
		}
		return value;
	}
	
	public static int [] orderCardsInAnArray (List<Card> playerInputCards){
		int [] allCardNumbers = new int [15];
		int cardNumber = 0;
		String cardValue = null;
		
		for (int i=0; i <= playerInputCards.size() - 1 ; i++){
			cardValue = playerInputCards.get(i).getValue();
			
			if ("A".equals(cardValue)){
				allCardNumbers[1] = ++ allCardNumbers[1];
			}
			cardNumber = CommonUtils.getIndex(playerInputCards.get(i).getValue());
			allCardNumbers[cardNumber] = ++ allCardNumbers[cardNumber];
		}
		return allCardNumbers;
	}
	
	public static Map<String, int []> arrangeCardsPerSuitAndInOrder (List<Card> playerInputCards){
		Map<String, int []> cardsPerSuitAndInOrderMap = new HashMap <>();
		int [] cardsPositionArray = null;
		int cardNumber = 0;
		String cardValue = null;
		String cardSuit = null;
		
		for (int i=0; i <= playerInputCards.size() - 1 ; i++){
			cardSuit = playerInputCards.get(i).getSuit();
			cardValue = playerInputCards.get(i).getValue();
			cardNumber = CommonUtils.getIndex(cardValue);
			
			//Needs to be corrected. It adds additional one for a duplicate ACE card.
			if (cardsPerSuitAndInOrderMap.containsKey(cardSuit)){
				cardsPerSuitAndInOrderMap.get(cardSuit)[cardNumber] ++;
			}else{
				cardsPositionArray = new int [15];
				cardsPerSuitAndInOrderMap.put(cardSuit, placeCardAtItsPositionInArray(cardValue,cardsPositionArray));
			}
		}
		printMapKeyValues (cardsPerSuitAndInOrderMap);
		return cardsPerSuitAndInOrderMap;
	}
	
	public static Map<String, Integer> arrangeCardsPerSuit (List<Card> playerInputCards){
		Map<String, Integer> cardsPerSuitMap = new HashMap <>();
		int suitCardsCount = 0;
		String cardSuit = null;
		
		for (int i=0; i <= playerInputCards.size() - 1 ; i++){
			cardSuit = playerInputCards.get(i).getSuit();
			
			if (cardsPerSuitMap.containsKey(cardSuit)){
				suitCardsCount = cardsPerSuitMap.get(cardSuit);
				cardsPerSuitMap.put(cardSuit,  ++ suitCardsCount);
			}else{
				cardsPerSuitMap.put(cardSuit, 1);
			}
		}
		return cardsPerSuitMap;
	}
	
	public static Map<String, List<Card>> getCardsPerSuitMap (List<Card> flatList){
		Map<String, List<Card>> cardsPerSuitMap = new HashMap <>();
		List<Card> suitCards = null;
		String cardSuit = null;
		
		for (int i=0; i < flatList.size(); i++){
			cardSuit = flatList.get(i).getSuit();
			
			if (cardsPerSuitMap.containsKey(cardSuit)){
				suitCards = cardsPerSuitMap.get(cardSuit);
			}else{
				suitCards = new ArrayList<Card>();
			}
			placeCardAtItsPositionInFlatList (suitCards, flatList.get(i));
			cardsPerSuitMap.put(cardSuit, suitCards);
		}
		return cardsPerSuitMap;
	}
	
	public static List<Card> effectiveListWithNoNulls (List<Card> input){
		List<Card> cardsWithoutNull = new ArrayList<Card>();
		
		for (Card card : input){
			if (null != card) cardsWithoutNull.add(card);
		}
		
		return cardsWithoutNull;
	}
	
	public static int [] placeCardAtItsPositionInArray (String cardValue, int [] allCardNumbers){
		
		int cardNumber = CommonUtils.getIndex(cardValue);

		/* Make additional entry for A at 1st location
		 * A can be treated as both the highest card and lowest card.
		 */	
		if ("A".equals(cardValue)){
			allCardNumbers[1] = ++ allCardNumbers[1];
		}
		
		allCardNumbers[cardNumber] = ++ allCardNumbers[cardNumber];
		return allCardNumbers;
	}
	
	public static List<ArrayList<Card>> placeCardAtItsPositionInList (List<ArrayList<Card>> positionalCardsList, Card card){
		String cardValue = card.getValue();
		int cardNumber = CommonUtils.getIndex(cardValue);
		ArrayList <Card> cards = null;
		
		if (Constants.ACE.equals(cardValue)){
			cards = positionalCardsList.get(1);
			cards.add(card);
		}
		cards = positionalCardsList.get(cardNumber);
		cards.add(card); 

		return positionalCardsList;
	}
	
	public static List<Card> placeCardAtItsPositionInFlatList (List<Card> cardsList, Card card){
		if (cardsList.isEmpty()){
			CommonUtils.initializeFlatCardList(cardsList,15);
		}
		String cardValue = card.getValue();
		int cardNumber = CommonUtils.getIndex(cardValue);
		
		if (Constants.ACE.equals(cardValue)){
			cardsList.remove(1);
			cardsList.add(1,card);
		}
		cardsList.remove(cardNumber);
		cardsList.add(cardNumber, card); 
		
		return cardsList;
	}
	
	public static List<ArrayList<Card>> placeCardAtItsPositionInList (List<Card> cardsList){
		String cardValue = null;
		int cardNumber= 0;
		List<ArrayList <Card>> cards = new ArrayList<ArrayList<Card>>();
		initializeList(cards);
		
		for (Card card : cardsList){
			cardValue = card.getValue();
			cardNumber = CommonUtils.getIndex(cardValue);
			
			if (Constants.ACE.equals(cardValue)){
				cards.get(1).add(card);
			}
			cards.get(cardNumber).add(card);
		}

		return cards;
	}
	
	public static void printMapKeyValues (Map<String, int []> cardsPerSuitMap){
		cardsPerSuitMap.forEach((key, value) -> {
		    System.out.println("Key : " + key + " Value : " + Arrays.toString(value));
		});
	}
	

	public static List<ArrayList<Card>> initializeList (List<ArrayList<Card>> positionalCardsList){
		for (int i=0; i<15; i++){
			positionalCardsList.add(new ArrayList<>());
		}
		return positionalCardsList;
	}
	
	public static List<Card> initializeFlatCardList (List<Card> flatList, int listSize){
		for (int i=0; i<listSize; i++){
			flatList.add(null);
		}
		return flatList;
	}
	
	public static List<Card> getTopNCardsFromList (List<Card> cards, Integer n){
		
		List<Card> topNCardsList = new ArrayList<>();
		
		if (null == cards){
			return cards;
		}
		
		for (int i= cards.size() -1; i>=0; i--){
			if (null != cards.get(i) && topNCardsList.size() < n){
				topNCardsList.add(cards.get(i));
			}
		}
		return topNCardsList;
	}
	
	public static <T> List<T> getFlatListFromJaggedList (List<ArrayList<T>> jaggedList){
		List<T> flatList = 
				jaggedList.stream()
			        .flatMap(List::stream)
			        .collect(Collectors.toList());
		return flatList;
	}
	

	public static List<ArrayList<Card>> removeCardsFromList (List<ArrayList<Card>> sourceList, List<Card> cardsToBeRemoved){
		
		if (null == sourceList || sourceList.isEmpty()){
			System.out.println("Source List empty.");
			return sourceList;
		}
		
		if (null == cardsToBeRemoved || cardsToBeRemoved.isEmpty()){
			System.out.println("Hand List empty. Nothing to be removed from source List.");
			return sourceList;
		}
		
		List<Card> flatList = getFlatListFromJaggedList (sourceList);
		flatList.removeAll(cardsToBeRemoved);
		
		List<ArrayList<Card>> residualList = placeCardAtItsPositionInList (flatList);
		
		/*for (int k=0; k<cardsToBeRemoved.size(); k++)
			for (int i= sourceList.size() - 1; i>=0; i--){
				if (! sourceList.isEmpty()){
					for (int j=0; j<sourceList.get(i).size(); j++){
						if (cardsToBeRemoved.get(k).equals(sourceList.get(i).get(j))){
							if (cardsToBeRemoved.get(k).getValue().equals(Constants.ACE)){
								sourceList.get(1).remove(cardsToBeRemoved.get(k));
							}
							sourceList.get(i).remove(j);
							break;
						}
					}
				}
			}*/
		return residualList;
		}
}