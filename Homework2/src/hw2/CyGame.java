package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author YOUR_NAME_HERE
 */
public class CyGame {
	/**
	 * Do nothing square type.
	 */
	public static final int DO_NOTHING = 0;
	/**
	 * Pass go square type.
	 */
	public static final int PASS_GO = 1;
	/**
	 * Cyclone square type.
	 */
	public static final int CYCLONE = 2;
	/**
	 * Pay the other player square type.
	 */
	public static final int PAY_PLAYER = 3;
	/**
	 * Get an extra turn square type.
	 */
	public static final int EXTRA_TURN = 4;
	/**
	 * Jump forward square type.
	 */
	public static final int JUMP_FORWARD = 5;
	/**
	 * Stuck square type.
	 */
	public static final int STUCK = 6;
	/**
	 * Points awarded when landing on or passing over go.
	 */
	public static final int PASS_GO_PRIZE = 200;
	/**
	 * The amount payed to the other player per unit when landing on a
	 * PAY_PLAYER square.
	 */
	public static final int PAYMENT_PER_UNIT = 20;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;
	/**
	 * The cost of one unit.player1Square = player1Square % numSquares;
	 */
	public static final int UNIT_COST = 50;
	
	
	// TODO: EVERTHING ELSE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.
	
	//Variables
	/**
	 * The number of squares on the board
	 */
	private int numSquares;
	
	/**
	 * Stores which player whose turn it is 
	 */
	private int currentPlayer;
	
	/**
	 * The square that player 1 is on
	 */
	private int player1Square;
	
	/**
	 * The square that player 2 is on
	 */
	private int player2Square;
	
	/**
	 * Stores how much money player 1 has
	 */
	private int player1Money;
	
	/**
	 * Stores how much money player 2 has
	 */
	private int player2Money;
	
	/**
	 * Stores how many units player 1 owns
	 */
	private int player1Unit;
	
	/**
	 * Stores how many units player 2 owns
	 */
	private int player2Unit;
	
	/**
	 * 
	 */
	private boolean player1Stuck;
	/**
	 * 
	 */
	private boolean player2Stuck;
	
	/**
	 * 
	 */
	private boolean player1ExtraTurn;
	
	/**
	 * 
	 */
	private boolean player2ExtraTurn;
	
	
	//Constructor(s)
	/**
	 * 
	 * @param numSquares
	 * @param startingMoney
	 * This is a constructor that have a given number of squares and a given amount of money at the start of the game
	 */
	public CyGame(int numSquares, int startingMoney) {
		this.numSquares = numSquares;
		player1Money = startingMoney;
		player2Money = startingMoney;
		player1Unit = 1;
		player2Unit = 2;
		currentPlayer = 1;
		
	}
	
	
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public int getPlayer1Money() {
		return player1Money;
	}
	
	public int getPlayer2Money() {
		return player2Money;
	}
	
	public int getPlayer1Square() {
		return player1Square;
	}
	
	public int getPlayer2Square(){
		return player2Square;
	}
	
	public int getPlayer1Units() {
		return player1Unit;
	}
	
	public int getPlayer2Units() {
		return player2Unit;
	}
	/**
	 * 
	 * @param thisSquare
	 * @return
	 */
	public int getSquareType(int thisSquare) {
		if(thisSquare == 0) {
			return PASS_GO;
		}
		else if(thisSquare == numSquares-1) {
			return CYCLONE;
		}
		else if(thisSquare % 5 == 0) {
			return PAY_PLAYER;
		}
		else if(thisSquare % 7 == 0 || thisSquare % 11 == 0) {
			return EXTRA_TURN;
		}
		else if(thisSquare %3 == 0) {
			return STUCK;
		}
		else if(thisSquare % 2 ==0) {
			return JUMP_FORWARD;
		}
		else {
			return DO_NOTHING;
		}
	}
	
	public void roll(int value) {
		if(currentPlayer == 1) {
			if(player1Stuck == false) {
				player1Square += value;
				
				if(player1Square >= numSquares) {
					player1Square = player1Square % numSquares;
					player1Money += PASS_GO_PRIZE;
				}
				
				player1ExtraTurn = false;
				if(getSquareType(player1Square) == 1) {
					player1Money += PASS_GO_PRIZE;
				}
				if(getSquareType(player1Square) == 2) {
					player1Square = player2Square;
				}
				if(getSquareType(player1Square) == 3) {
					player1Money -= player2Unit * PAYMENT_PER_UNIT;
					player2Money += player2Unit * PAYMENT_PER_UNIT;
				}
				if(getSquareType(player1Square) == 4) {
					player1ExtraTurn = true;
				}
				
				if(getSquareType(player1Square) == 5) {
					player1Square += 4;
					if(player1Square <= 3) {
						player1Money += PASS_GO_PRIZE;
					}
				}
				if(getSquareType(player1Square) == 6) {
					player1Stuck = true;
				}
			}
		}
		else if(currentPlayer == 2) {
			if(player2Stuck == false) {

				player2Square += value;
				if(player2Square >= numSquares) {
					player2Square = player2Square % numSquares;
					player2Money += PASS_GO_PRIZE;	
				}
				player2ExtraTurn = false;
				if(getSquareType(player2Square) == 1) {
					player2Money += PASS_GO_PRIZE;
				}
				if(getSquareType(player2Square) == 2) {
					player2Square = player1Square;
				}
				if(getSquareType(player2Square) == 3) {
					player2Money -= player1Unit * PAYMENT_PER_UNIT;
					player1Money += player1Unit * PAYMENT_PER_UNIT;
				}
				if(getSquareType(player2Square) == 4) {
					player2ExtraTurn = true;
					
					
				}
				if(getSquareType(player2Square) == 5) {
					player2Square += 4;
					if(player2Square <= 3) {
						player2Money += PASS_GO_PRIZE;
					}
				}
				if(getSquareType(player2Square) == 6) {
					player2Stuck = true;
				}
			}
		}
		endTurn();

	}
	
	public void sellUnit() {
		
	}
	
	public void buyUnit() {
		
	}
	
	public void endTurn() {
		if(currentPlayer == 1 && player1ExtraTurn == false) {
			currentPlayer = 2;
		}
		else if(currentPlayer == 2 && player2ExtraTurn == false){
			currentPlayer = 1;
		}
	}
	/**
	 * Returns a boolean value of weather or not the game is over. The game always ends in one of two ways.
	 * 
	 * 1. if a player has more money than the amount required to win. This is denoted by the MONEY_TO_WIN constant.
	 * 
	 * 2. A player has negative money. This will cause said player to lose the game.
	 * @return
	 */
	public boolean isGameEnded() {
		if( (player1Money >= MONEY_TO_WIN) || (player1Money < 0) ) {
			return true;
		}
		if( (player2Money >= MONEY_TO_WIN) || (player2Money < 0) ) {
			return true;
		}
		
		return false;
	}
	
	

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, 0, $0) Player 2: (0, 0, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it
	 * is. The numbers (0, 0, $0) indicate which square the player is on, how
	 * many units the player has, and how much money the player has
	 * respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
			player1Turn = "*";
		} else {
			player2Turn = "*";
		}
		return String.format(fmt,
				player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(),
				player2Turn, getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
	}
}
