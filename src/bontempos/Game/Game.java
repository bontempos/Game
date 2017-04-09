package bontempos.Game;

import bontempos.Game.Act.*;
import processing.core.*;



/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class Game {


	// CLASS UPDATES
	int frame = -1; 								// game current frame ( -1 : init() )
	int gameFrameUpdateInterval = 1000; 			// default value 1 second;
	int stageClearInterval = 3000;


	//TIMERS
	Countdown gameFrameUpdateTimer, stageClearTimer;	// , scoreScreenTimer...


	// UPDATE STATS
	boolean run = true;								// game play running
	boolean gameOver = true;
	boolean gameClear = false;
	boolean stageClear = false;
	boolean paused = false; 						// paused by user
	boolean hold = false;	 						// paused by game
	boolean startNewGame = false;


	//STAGE
	Stage stage;
	int lastStage;


	//SCORE
	int score;
	int[] hiScores = new int[10];


	//PLAYER
	int playerHealth = 0; // -- setupInstructions


	//EXTERNAL FILE INSTRUCTIONS
	String[] setupInstructions; 


	//VERBOSE
	boolean verbose = false;



	PApplet papplet;

	int myVariable = 0;

	public final static String VERSION = "v 1.0";

	//--------------------------------------------------------------------------------------<   INIT  >

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the Library.
	 * 
	 * @example Hello
	 * @param theParent
	 */
	public Game(PApplet pApplet) {
		papplet = pApplet;
		AConstants.setPApplet(papplet);
		Action updateFrameEvent = new Action( this, "updateFrame");
		gameFrameUpdateTimer = new Countdown( gameFrameUpdateInterval, updateFrameEvent);
		gameFrameUpdateTimer.setRepeat(true);

		welcome();
	}

	public void init(){
		gameOver = true;
		hold = false;
		paused = false;
		score = 0;
		stage.setStage(0);
		if(verbose)System.out.println( papplet.millis() + ": Game initialized");
	}


	//--------------------------------------------------------------------------------------<   SETTERS  >

	public void setVerbose( boolean b ){
		verbose = b;
	}

	public void setGameFrameUpdateInterval (int interval){
		gameFrameUpdateInterval = interval;
		gameFrameUpdateTimer.interval = interval;
	}

	//--------------------------------------------------------------------------------------<   GETTERS  >

	//--------------------------------------------------------------------------------------<   METHODS  >


	private void welcome() {
		System.out.println("Game "+ version() +" by Anderson Sudario");
	}


	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}

	/**
	 *  game related timers starts
	 */
	public void start(){
		gameFrameUpdateTimer.start();
	}

	/**
	 * new game play settings
	 */
	public void newGamePlay(){
		//		    playerHealth = 0; //will be increased by game instructions on buildStage();
		//		    setStage(1);
		//		    score = 0;
		//		    userDefeated = false; //not sure if should be here or on "init()". Probably here.
		//		    buildStage(getStage());
		//		splash = new StageSplash(p,3000);
		//	    displaySplash = true;
		//	    gameOver = false;
		//	    frame = 0;
		//	    buildNewGame();
	}



	/**
	 * game is paused by user
	 */
	void gamePaused() {
		//splash = new GamePausedSplash(p,10000);
		//displaySplash = true;
		paused = true;
	}


	//--------------------------------------------------------------------------------------<   GAME UPDATE  >
	public void update() {
		AConstants.get().updateLists();
	}

	public void updateFrame() {
		if (frame == -1) {
			init();
		}else{
			if (run) {
				if (!paused && !hold) {
					//enemies.redistribuite(); // TODO connect this to RUN and all
					// other things related to the
					// game routine, including
					// display
					//for (Ent e : enemies.ents)
						//e.updateFrame(); // TODO connect this to RUN
				}
			}
		}
		frame++;
		if(verbose) System.out.println(papplet.millis() + ": " + ((paused)?"[PAUSED]":"") +"Game frame -> " + frame);
	}
}



