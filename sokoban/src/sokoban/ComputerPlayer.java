/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.util.ArrayList;
import java.util.Collections;
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
    
    public ComputerPlayer(GameState MyState){
        display = new GameDisplay(MyState);
        initialPlayerStateX = MyState.playerCol;
        initialPlayerStateY = MyState.playerRow;
        
    }

    
    public List<GameState> Evaluate(GameState state){
    		
    	List<GameState> openList = new ArrayList<GameState>();
    	List<GameState> closedList = new ArrayList<GameState>();
    	List<GameState> path = new ArrayList<GameState>();
    	
    	GameState initialState = state;
    	GameState tempState = null;
    	
    	GameState lowestF;
    	List<GameState> LegalActions = null;
    	
    	//adds initial state to the openList
    	openList.add(state);
    	
    	while(!openList.isEmpty()){
    		lowestF = getOptimalNode(openList);  //lowest f scoring node in the openList;
    		//lowestF.printState();
    	//	break;
    		openList.remove(lowestF);
    		LegalActions = getLegalActions(lowestF);
    		
    	
    	
    		//set parents of successors
    		for(int i = 0; i < LegalActions.size(); i ++){
    			LegalActions.get(i).setParent(lowestF);
    			
    			
    		}
    		
    		for(int k = 0; k < LegalActions.size(); k++){
    			if(LegalActions.get(k).isGoalState()){
    				
    				//reconstruct path.
    				tempState = LegalActions.get(k);
    		
        			while(tempState != initialState){
        				path.add(tempState);
        				tempState.printState();
        				tempState = tempState.getParent();
        			}
        			
        			GameState curNode;
        			int posCounter = 0;
        	
        			
    				System.out.println("finished");
    				return reverseList(path);
    			}
    			
    			
    			
    			LegalActions.get(k).setG(lowestF.getG() + distanceBetween(LegalActions.get(k), lowestF));
    			LegalActions.get(k).setH(manhattanDistance(LegalActions.get(k)));
    			LegalActions.get(k).setF(LegalActions.get(k).getG() + LegalActions.get(k).getH());
    			
    			
    			if(openList.contains(LegalActions.get(k))){
    				
    				
    				
    			}
    			if(closedList.contains(LegalActions.get(k))){
    				
    				
    			}else{
    				openList.add(LegalActions.get(k));
    			}
    			closedList.add(lowestF);

    		}

    	}

    	return path;
 	
    }
/*    public void checkBlockPosition(GameState state){
    	state.
    	
    	
    	
    }
*/
	public List<GameState>reverseList(List<GameState> inState){
    	List<GameState> outState = new ArrayList();
    	
    	int count = inState.size() -1;
		for(int j = (count); j > -1; j--){
		
				outState.add(inState.get(j));
		}
    	
    	
    	
    	return outState;
    }
    
    

   
    public List<GameState> getLegalActions(GameState state){
    	//A list containing legal leaf-nodes from given state.
    	
    	List<GameState> LegalStates = new ArrayList<GameState>(); 
    	//LegalStates.add(state.moveLeft());
    	//LegalStates.add(state); // may need to remove
    	
    	
    	if(state.moveLeftLegal() == true){
    		LegalStates.add(state.moveLeft());
    	}
    	if(state.moveUpLegal() == true){
    		LegalStates.add(state.moveUp());
    	}
    	if(state.moveRightLegal() == true){
    		LegalStates.add(state.moveRight());
    	}
    	if(state.moveDownLegal() == true){
    		LegalStates.add(state.moveDown());
    	}
    		
    	
    	return LegalStates;
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

    	// your code goes here ...
    	return null;
    }
    
    public void showSolution(List<GameState> solution) {               
        for (GameState st : solution) {            
            display.updateState((GameState) st);
            try {
                Thread.sleep(5); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
       // GameState state = args.length==0?new GameState("src/levels/level6.txt"):new GameState(args[0]);    
        GameState state = args.length==0?new GameState("src/levels/level6.txt"):new GameState(args[0]);  
        long t1 = System.currentTimeMillis();
        ComputerPlayer player = new ComputerPlayer(state);  
        //List<GameState> solution = player.getSolution(); //OLD CODE
        List<GameState> mySolution = player.Evaluate(state);
        long t2 = System.currentTimeMillis();
        System.out.println("Time: " + (t2-t1));
        player.showSolution(mySolution);
    }
     
}
