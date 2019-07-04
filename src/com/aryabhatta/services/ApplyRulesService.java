package com.aryabhatta.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aryabhatta.common.CommonUtils;
import com.aryabhatta.common.Constants;
import com.aryabhatta.entity.Card;
import com.aryabhatta.entity.Hand;
import com.aryabhatta.enums.RuleRankEnum;
import com.aryabhatta.rules.RulesUtils;
import com.aryabhatta.rules.RulesUtils2;

public class ApplyRulesService {
	CardsService cardsService = new CardsService();
	

	public Hand applyRulesAndCreateHand(List<ArrayList<Card>> cards, String handName) {
		Hand hand = new Hand();
		
		while (true){
			
			if (!handName.equals(Constants.FRONT_HAND)){
				if (RulesUtils2.hasARoyalFlush(cards)){
					hand = cardsService.createFlushHand(cards);
					hand.setHandComplete(Constants.TRUE);
					hand.setHandRank(RuleRankEnum.ROYAL_LUSH.getRuleRank());
					break;
				}
				if (RulesUtils2.hasAStraightFlush(cards)){
					hand = cardsService.createFlushHand(cards);
					hand.setHandComplete(Constants.TRUE);
					hand.setHandRank(RuleRankEnum.STRAIGHT_FLUSH.getRuleRank());
					break;
				}
				if (RulesUtils2.hasFourOfAKind(cards)){
					hand = cardsService.createNOfAKindHand(cards, 4);
					hand.setHandComplete(Constants.FALSE);
					hand.setHandRank(RuleRankEnum.FOUR_OF_A_KIND.getRuleRank());
					break;
				}
				if (RulesUtils2.hasFullHouse(cards)){
					hand = cardsService.createFullHouseHand(cards);
					hand.setHandComplete(Constants.TRUE);
					hand.setHandRank(RuleRankEnum.FULL_HOUSE.getRuleRank());
					break;
				}
				if (RulesUtils2.hasAFlush(cards)){
					hand = cardsService.createFlushHand(cards);
					hand.setHandComplete(Constants.TRUE);
					hand.setHandRank(RuleRankEnum.FLUSH.getRuleRank());
					break;
				}
				if (RulesUtils2.hasAStraight(cards)){
					hand = cardsService.createAStraightHand(cards);
					hand.setHandComplete(Constants.FALSE);
					hand.setHandRank(RuleRankEnum.STRAIGHT.getRuleRank());
					break;
				}
			}
			
			if (RulesUtils2.hasThreeOfAKind(cards)){
				hand = cardsService.createNOfAKindHand(cards, 3);
				hand.setHandComplete(Constants.FALSE);
				hand.setHandRank(RuleRankEnum.THREE_OF_A_KIND.getRuleRank());
				break;
			}
			if (RulesUtils2.hasTwoPairs(cards)){
				hand = cardsService.createTwoPairHand(cards);
				hand.setHandComplete(Constants.FALSE);
				hand.setHandRank(RuleRankEnum.TWO_PAIR.getRuleRank());
				break;
			}
			if (RulesUtils2.hasAPair(cards)){
				hand = cardsService.createNOfAKindHand(cards, 2);
				hand.setHandComplete(Constants.FALSE);
				hand.setHandRank(RuleRankEnum.ONE_PAIR.getRuleRank());
				break;
			}
			
			int n = handName.equals(Constants.FRONT_HAND) ? 3 : 5;
			hand = cardsService.createHighCardHand(cards, n);
			hand.setHandComplete(Constants.TRUE);
			hand.setHandRank(RuleRankEnum.HIGH_CARD.getRuleRank());
			break;
		}
		return hand;
	}


	public void completePlayerHand(List<ArrayList<Card>> residualCards, Hand hand, int deficit) {
		if (null == hand) {
			System.out.println("Populate hand first.");
			return;
		}
		if (null == residualCards || residualCards.isEmpty()) {
			System.out.println("No residual cards to populate hands.");
			return;
		}
		List<Card> addOnCards = null;
		List<Card> handCards = hand.getHandCards();

		addOnCards = cardsService.getTopNCardsFromList(residualCards, deficit);
		handCards.addAll(addOnCards);
		CommonUtils.removeCardsFromList(residualCards, addOnCards);
	}
}