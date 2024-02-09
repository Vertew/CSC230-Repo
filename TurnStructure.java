/**
 * This class represents the structure of a turn
 * 
 * @author Mae Hedmann-Highmore
 * @version 1.0
 */

public class TurnStructure {

	private int turnTime = 0;
	private int currentP;
	private InEffect currentEffects;
	private Hand currentHand;
	private Game2 game;
	private boolean doubleMove;

	/**
	 * This initialises a turn structure
	 * 
	 * @param currentP    A variable to denote who the current player is
	 * @param effects     The relevant instance of inEffect (the class that handles
	 *                    the tiles in effect)
	 * @param currentHand The hand of the current player
	 * @param currentGame The game instance
	 */
	public TurnStructure(int currentP, InEffect effects, Hand currentHand, Game2 currentGame) {
		// upkeep->draw->(possible place)->Action->Move->(possible second move)->END
		setCurrentP(currentP);
		setCurrentHand(currentHand);
		setEffects(effects);
		setGame(currentGame);

	}

	/**
	 * This takes the game instance and stores it
	 * 
	 * @param game The game instance
	 */
	private void setGame(Game2 game) {
		this.game = game;
	}

	/**
	 * Resets current player
	 * 
	 * @param player Current player
	 */
	private void setCurrentP(int player) {
		this.currentP = player;
		/*
		 * if(x){ System.out.println("p1 turn"); }else{ System.out.println("p2 turn"); }
		 */
	}

	/**
	 * Stores the relevant instance of inEffect
	 * 
	 * @param effect The instance of inEffect
	 */
	private void setEffects(InEffect effect) {
		this.currentEffects = effect;
	}

	/**
	 * Stores the current hand
	 * 
	 * @param current The hand object to store
	 */
	private void setCurrentHand(Hand current) {
		this.currentHand = current;
	}

	/**
	 * @return the integer representing the phase of the turn
	 */
	public int getTurnTime() {
		return turnTime;
	}

	/**
	 * Starts the turn and draws, then if the thing drawn was a floor, puts them in
	 * the phase where they must place it else put them in the action tile phase
	 * 
	 * @return drawn tile
	 */
	public Tile startTurn() {
		// System.out.println(turnTime);
		Tile drawn = null;
		if (turnTime == 0) {
			currentEffects.upkeep();
			// DRAW 1

			drawn = currentHand.draw();
			turnTime++;
			if (drawn instanceof Floor) {
				game.setCurrentFloor((Floor) drawn);
				turnTime = 1;
			} else {
				turnTime = 2;
			}
		}
		game.drawEverything();
		return drawn;
	}

	/**
	 * Allows the turn to continue when the tile is placed
	 */
	public void placeDone() {
		turnTime = 2;
		if (currentHand.isEmpty()) {
			actionDone();
		}
		game.drawEverything();
	}

	/**
	 * Implements Action tile effect or skips to continue the turn
	 */
	public void doubleMove() {
		this.doubleMove = true;
	}

	/**
	 * Resets board and hand after action is done
	 */
	public void actionDone() {
		turnTime = 3;
		game.drawEverything();
	}

	/**
	 * Is called when a player has moved or cannot move.
	 */
	public void moveDone() {

		turnTime = 4;
		if (!this.doubleMove) {
			game.turnOver();
		} else {
			actionDone();
			this.doubleMove = false;
		}
	}

}
