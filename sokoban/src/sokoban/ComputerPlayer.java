/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steven
 */
public class ComputerPlayer {
    
    GameDisplay display;
    List frontier = new ArrayList<GameState>(); 
    
    
    public ComputerPlayer(GameState state){
        display = new GameDisplay(state);
        GameState tempState;
        int limit = 5;
        frontier = EvaluateFn(state, frontier);
        
     

    }
    public List<GameState> EvaluateFn(GameState state, List<GameState> frontier){
    	GameState stepState = null;
    	
    	while(state.isGoalState() == false){
    		stepState = state.moveUp();
    		frontier.add(stepState);
    		
    		state = stepState;
    		
    	}
    	
    	if(state.isGoalState()){
    		
    		System.out.println("FINISHED");
    	}
    	return frontier;
    	
	}
	
    //takes position and a goal
    public int manhattenDistance(GameState state){
    	//gets player position
    	int playerX = state.playerCol;    //this gets the player position on the x Axis
    	int playerY = state.playerRow; 	  //this gets the player position on the y Axis
    	
    	
    	
    	//this gets the goal state closest to the player. 
    	for(int K = 0; K < state.goalPositions.size(); K++){
    		//this holds the x axis position of the currently accessed goal
    		int tempX = state.goalPositions.get(K).col;
    		int tempY = state.goalPositions.get(K).row;
    		
    		int currentXDistance = playerX - tempX;
    		int currentYDistance = playerY - tempY;
    		
    		System.out.println("currentXDistance");
    		System.out.println("currentYDistance");
    		
    		
    		
    	}
    	
    	
    	
    	
    	return 0;
    }
    public List<GameState> getSolution(){
   
        // frontier.add(state.moveUp());
     //    frontier.add(state.moveLeft());
    	// your code goes here ...
    	return frontier;
    }
    
    public void showSolution(List<GameState> solution) {               
        for (GameState st : solution) {            
            display.updateState((GameState) st);
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        GameState state = args.length==0?new GameState("src/levels/level6.txt"):new GameState(args[0]);        
        long t1 = System.currentTimeMillis();
        ComputerPlayer player = new ComputerPlayer(state);  
        List<GameState> solution = player.getSolution();
        long t2 = System.currentTimeMillis();
        System.out.println("Time: " + (t2-t1));
        player.showSolution(solution);
    }
     
}
