// By: Michael Rzadki
//Student number: 10197068
//Net ID: 15mrer
//COMP 212 assignment 1
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Game_of_pig_final {
	
	public static void main(String[] args){
		//first one human score, 2nd element is the computer score.
		int[] scores = new int[2];
		int nextTurn = 0; //while this is 0, its the humans turn. While this is 1, its the computers.
		while(scores[0] < 100 && scores[1] < 100){ //while neither competitor has won
			if (nextTurn == 0){
				nextTurn = playerTurn(scores,0);
			}
			else{
				nextTurn = computerTurn(scores);
				System.out.println("-------------------------------");
			}
		}
	}//ends main
	
	public static int[] roll(){         //generates two random numbers between 1 and 6
		int[] diceValues = new int[2];  // array for the first dice value
	    Random rand= new Random ();     // generates the first random number
	    int temp= rand.nextInt(6) +1;   
	    diceValues[0]= temp;
	    Random rand1= new Random ();    // array for the second dice value
	    int temp2= rand1.nextInt(6) +1; // generates the second random number
	    diceValues[1]=temp2;
	    return diceValues;
	}//ends roll
	
	public static int playerTurn(int[] scores, int playerTurnScore){ 
		int[] dieVals = roll();                     //determines two random number for player
		playerTurnScore += dieVals[0] + dieVals[1]; //adds the two dice values together
		System.out.println("You have Rolled " + dieVals[0] + " and " + dieVals[1]);
	    if(dieVals[0] == 1 && dieVals[1] == 1){ 
	    	//if the player rolls two 1's
	    	scores[0] = 0;  //the player's total score is set to zero 
	    	return 1;       //players turn is over 
	    }// end if both values are 1
	    else if (dieVals[0] == 1 || dieVals[1]== 1){
	    	//if one of the dice rolled gives a value of 1
	    	playerTurnScore = 0;  //the player's turn score is set of zero
	    	return 1;             //the player's turn is zero
	    }// end if one value is 1
	    else if (dieVals[0]== dieVals[1]){
	    	//if both dice produce the same value
	    	System.out.println("You Have Rolled Doubles And Must Roll Again");
	    	playerTurn(scores, playerTurnScore); //the score is added to the player's turn score
	    }// ends roll again if numbers are the same
	    else{
	    	printOptions(scores, playerTurnScore); 
	    }
		return 1;
	}// ends playerTurn

    @SuppressWarnings("resource")
	public static void printOptions(int[] bothScores , int currentScore){
    	//tells the player all of the current and total scores 
    	System.out.println("Player Score = " + bothScores[0]);
    	System.out.println("Computer Score = " + bothScores[1]);
    	System.out.println("Your Turn Score = " + currentScore);
    	System.out.println("Enter A Number ");
    	System.out.println("Press 1 To Roll Again ");
    	System.out.println("Press 2 To End Your Turn ");
		Scanner reader = new Scanner(System.in);        //allows the user to input data
    	int playerChoice = reader.nextInt();
    	
    	if (playerChoice == 1){
    		playerTurn(bothScores, currentScore); //goes back to the beginning to player's turn
    	}
    	else if(playerChoice == 2){ 
    		scoreTracker(bothScores, currentScore, "Player"); //adds current score to total score
    	}
    	else{
    		System.out.println("Not A Valid Option.");
    		printOptions(bothScores,currentScore);
    	}
    } // printOptions
    
    public static void scoreTracker(int[] bothScores, int currentScore, String whosTurn){
    	//adds current score to total score for both player and computer
    	if(whosTurn == "Player"){  //determines if it is the players turn
    		bothScores[0] += currentScore;
    		if(bothScores[0] >= 100){      //checks to see if the player has won
    			System.out.println("Congraulations You Have Won");
    		}
        }
    	else{ //computer's turn
    		bothScores[1] += currentScore;
    		if(bothScores[1] >= 100){      //checks to see if the computer has won
    			System.out.println("Sorry the Computer Has Won Better Luck Next Time");
    		}
    	}
    } //end of scoreTracker
    
    public static int computerTurn(int[] scores){
    	System.out.println("-------------------------------");
    	int computerTurnScore = 0;
    	while(computerTurnScore < 20){  //computer will continue to roll until they get a score of 20 or higher
	    	int[] dieVals = roll();                       //determines two random number for computer
			computerTurnScore += dieVals[0] + dieVals[1]; //adds the two dice values together
			System.out.println("Computer has Rolled " + dieVals[0] + " and " + dieVals[1]);
			if(dieVals[0] == 1 && dieVals[1] == 1){
				//if the computer rolls two 1's
		    	scores[1] = 0;  //computer's total score is set to zero
		    	return 0;	    //goes to player's turn
		    }// end if both values are 1
		    else if (dieVals[0] == 1 || dieVals[1]== 1){
		    	//if one of the dice rolled gives a value of 1
		    	return 0;       //goes to player's turn
		    }// end if one value is 1
		    else{
		    	System.out.println("Computer Has Rolled Again"); //testing
		    }// rolls again if computerTurnScore is less than 20
    	}//ends while loop
    	//this is when you call scoreTracker, playerTurn(scores, 0);
    	scoreTracker(scores, computerTurnScore, "Computer"); //adds current score to total score
    	return 0;
    }// end computer turn
}//ends class

