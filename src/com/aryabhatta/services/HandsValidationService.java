package com.aryabhatta.services;

import java.util.List;

import com.aryabhatta.entity.Hand;

public class HandsValidationService {

	public boolean areHandsMisSet (List<Hand> hands){
		boolean areHandsMisSet = false;
		//Hands are always arranged in the order: BackHand , MiddleHand, FrontHand
		if (hands.get(0).getHandRank() > hands.get(1).getHandRank() 
				&& hands.get(1).getHandRank() > hands.get(2).getHandRank()){
			areHandsMisSet = true;
		}
		return areHandsMisSet;
	}
}
