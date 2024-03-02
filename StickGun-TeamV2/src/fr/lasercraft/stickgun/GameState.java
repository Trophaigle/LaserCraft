package fr.lasercraft.stickgun;

public enum GameState {

	//STATUS DE JEU
	
LOBBY(true), GAME(false), FINISH(false);
//WAIT      //JEU        //FIN
	
	
   private boolean canJoin;
	
   private static GameState currentState;
  
    GameState(boolean canJoin){
    this.canJoin = canJoin;
    }
    
    public boolean canJoin(){ 
    	return canJoin;
    	
    }
    
    public static void setState(GameState state){
    	GameState.currentState = state;
    	
    }
    public static boolean isState(GameState state){
    	return GameState.currentState == state;
    	
    }
    public static GameState getState(){
    	return currentState;
    	
    }
    
    /** CHECK IF IS STATE GAME  */
	public static boolean isGame(){
		return currentState == GameState.GAME;
	}
	
	
}
