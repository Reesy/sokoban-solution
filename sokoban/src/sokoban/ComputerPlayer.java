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
    
	/*
	 * 
	 * Please note that in this I use the words node and state interchangeably.
	 * 
	 */
	
	
    GameDisplay display;
    int initialPlayerStateX;
    int initialPlayerStateY;
    
    public ComputerPlayer(GameState state){
        display = new GameDisplay(state);
        initialPlayerStateX = state.playerCol;
        initialPlayerStateY = state.playerRow;
        
    }
    public List<GameState> EvaluateFn(GameState state){
    	
        //  GameState tempState;
        //  int limit = 5;
        //  frontier = EvaluateFn(state, frontier);
    	//openList
    	List frontier = new ArrayList<GameState>(); 
    	frontier.add(state); // include the initial state
    	//closes list
    	List closedList = new ArrayList<GameState>();
    	
    	List legalActions = new ArrayList<GameState>();
    	
    	int g_score = 0;
    	
    	int f_score = g_score + manhattanDistance(state);
    	
    	int temp_g_score;
    	GameState current = null;
    	
    	
   //int f_score 
    	//initialise frontier
    //	frontier.add(state);

          
         
        while(frontier.isEmpty() == false){
        	//frontier.size();
        	//gets last element
        	current = getOptimalNode(frontier);
        	
        	if(current.isGoalState()){
        		System.out.println("finished");
        		
        	}
        	
        	frontier.remove(current);
        	closedList.add(current);
        	//for each neighbour(legal neighbour)
        	
        	legalActions = getLegalActions(current);
        		for(int i = 0; i < legalActions.size(); i++){
        			if(closedList.contains(legalActions.get(i))){
        				continue;
        			}
        			temp_g_score = initialtoNodeDistance(current) + distanceBetween(current, (GameState) legalActions.get(i)); //this may be bugged.
        			
        			
        			
        			
        		}		
        	
        	//next
        	
        	
        	
        	closedList.add(frontier.get(0));
        	GameState currentState = (GameState) frontier.get(0);    //might be bugged!
        	frontier.remove(0);
        	
        
        	
       /* 	if(state.isGoalState()){
        		return frontier;
          	}else{
          		//this checks the Currently active node for the most optimum state. 
          		frontier.add(getOptimalLeaf(currentState));*/
          		
          		
          		
          		
          		
          		
          	//	for(int legalSearch = 0; legalSearch < getLegalActions(state).size(); legalSearch++){
          		//	
          		//	
          		//	
          	//	}
          		
          		
          		
          		
          		
          		
          	}  	
        }
    	return frontier;
    	
	}
    
   
    public List<GameState> getLegalActions(GameState state){
    	//A list containing legal leaf-nodes from given state.
    	
    	List<GameState> LegalStates = null; 
    	
    	
    	
    	if(state.moveLeftLegal() == true){
    		LegalStates.add(state.moveLeft());
    	}
    	if(state.moveUpLegal() == true){
    		LegalStates.add(state.moveUp());
    	}
    	if(state.moveRightLegal() == true){
    		LegalStates.add(state.moveUp());
    	}
    	if(state.moveDownLegal() == true){
    		LegalStates.add(state.moveDown());
    	}
    		
    	
    	
    	
    	
    	
    	
    	return LegalStates;
    }
    //this is used to return the most optimal leaf-node branching from the given node/state.
    public GameState getOptimalLeaf(GameState state){
    	List<GameState> legalNodes = getLegalActions(state);
    	int h = 0;
    	int g = 0;
    	
    	int lowestF = manhattanDistance(legalNodes.get(0)) + initialtoNodeDistance(legalNodes.get(0));
    	//sets state with lowest F to the initial node in the legalNodes List
    	GameState lowestConnectedNode = legalNodes.get(0);
    	
    	for(int i = 0; i < legalNodes.size(); i++){
    		/// h(n) estimation to go from the n to a goal state
    		h = manhattanDistance(legalNodes.get((i)));
    		/// g(n) cost of the path from the initial state to node n (assuming each step equates to a cost of 1)
    		g = initialtoNodeDistance(legalNodes.get(i));
    		
    		if(h + g < lowestF){
    			lowestF = h + g;
    			lowestConnectedNode = legalNodes.get(i);
    			
    		}
    		
    	}
    	return lowestConnectedNode;
    }
    
    
    //this is used to get the most optimal node given a list.
    public GameState getOptimalNode(List<GameState> frontier){
    	
    	int h = 0;
    	int g = 0;
    	
    	
    	int lowestF = manhattanDistance(frontier.get(0)) + initialtoNodeDistance(frontier.get(0));
    	GameState lowestConnectedNode = frontier.get(0);
    	
    	for(int i = 0; i < frontier.size(); i++){
    		/// h(n) estimation to go from the n to a goal state
    		h = manhattanDistance(frontier.get((i)));
    		/// g(n) cost of the path from the initial state to node n (assuming each step equates to a cost of 1)
    		g = initialtoNodeDistance(frontier.get(i));
    		
    		if(h + g < lowestF){
    			lowestF = h + g;
    			lowestConnectedNode = frontier.get(i);
    			
    		}
    		
    	}
    	return lowestConnectedNode;
    	
    	
    
    }
    
    
    //this is extremely inefficient and may be removed when i work out how to do this.
    public int initialtoNodeDistance(GameState stateN){
    	
    	int currentPX = stateN.playerCol;
    	int currentPY = stateN.playerRow;
    	
    	int currentXDist = Math.abs(currentPX - initialPlayerStateX);
    	int currentYDist = Math.abs(currentPY - initialPlayerStateY);
    	
    	return currentXDist + currentYDist;
    }
    
    public int distanceBetween(GameState fromState, GameState toState){
    	
    	int fromStateX = fromState.playerCol;
    	int fromStateY = fromState.playerRow;
    	
    	int toStateX = toState.playerCol;
    	int toStateY = toState.playerRow;
    	
    	int XDist = Math.abs( toStateX - fromStateX );
    	int YDist = Math.abs(toStateY - fromStateY );
    	
    	
    	return XDist + YDist;
    	
    	
    	
    	
    }
    //takes position and a goal returns the number of moves to the nearest goal position.
    public int manhattanDistance(GameState state){
    	//gets player position
    	int playerX = state.playerCol;    //this gets the player position on the x Axis
    	int playerY = state.playerRow; 	  //this gets the player position on the y Axis

    	int manhattendist = 0;
        
    	//this loop returns the Manhattan distance towards the closest goal position.
    	for(int K = 0; K < state.goalPositions.size(); K++){
    		//x and y Co-ords of currently accessed goal.
    		int tempX = state.goalPositions.get(K).col;
    		int tempY = state.goalPositions.get(K).row;
    		
    		//this holds the x axis position of the currently accessed goal
    		int currentXDistance = Math.abs(playerX - tempX);
    		int currentYDistance = Math.abs(playerY - tempY);
    		
    		int tempManhattenDistance =  currentXDistance + currentYDistance;
    				
    		//checks for first goal position for initialisation purposes.
    		if(K == 0){
    			//this is used to store the manhattendistance of the first goal.
    			manhattendist = tempManhattenDistance;
    			
    		//if the manhattendistance for the currently checked goal is less than the one already stored.
    		}else if(tempManhattenDistance < manhattendist){
    			//assign the new shorter distance as the lowest manhatten distance.
    			manhattendist = tempManhattenDistance;
    			
    		}
    	}
    	
    	return manhattendist;
    }
   
    public List<GameState> getSolution(){
   
        // frontier.add(state.moveUp());
     //    frontier.add(state.moveLeft());
    	// your code goes here ...
    	return null;
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
