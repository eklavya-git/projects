package com.aryabhatta.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.aryabhatta.common.CommonUtils;
import com.aryabhatta.entity.Card;
import com.aryabhatta.entity.Player;
import com.aryabhatta.rules.RulesUtils;
import com.aryabhatta.rules.RulesUtils2;
import com.aryabhatta.services.GameService;
import com.aryabhatta.services.PlayerService;


public class ChinesePokerMain {

	public static void main(String[] args) {
		
		List<Player> players = null;
		
		//Unit Testing input.
		/*String[] strs = {"AH", "KH", "QH", "JH", "10H", "9H", "10D", "8H", "7D", "2C", "2D", "7H", "5C", "AH", "KH", 
				"QH", "JH", "3H", "9H", "3D", "2H", "JD", "7C", "2D", "7H", "5C"};*/
		
		//Read from Console Code.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String lines;
		String[] strs = null;
		try {
			lines = br.readLine();
			strs = lines.trim().split(";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		GameService game = new GameService();
		players = game.parseInputsAndCreatePlayers(strs);
		game.startGame(players);

		///For Unit testing only.
		//String[] inputs = {"AH", "KH", "QH", "JH", "10H", "9H", "10D", "8H", "7D", "2C", "2D", "7H", "5C"};
	}
	
	public static List<ArrayList<Card>> parseAndStoreCardDetails(String[] input){
		List<ArrayList<Card>> playerInputCards = new ArrayList<ArrayList<Card>>(13);
		return playerInputCards;
	}
}