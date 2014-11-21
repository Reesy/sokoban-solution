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
    
    public ComputerPlayer(MyGameState MyState){
        display = new GameDisplay(MyState);
        initialPlayerStateX = MyState.playerCol;
        initialPlayerStateY = MyState.playerRow;
        
    }
/*    public List<GameState> EvaluateFn(GameState state){

    	
    	List graph = new ArrayList<GameState>();
    	graph.add(state);
  
    	
    	List frontier = new ArrayList<GameState>(); 
    	frontier.add(state); // include the initial state
    
    	
    	
    	//closed list
    	List closedList = new ArrayList<GameState>();
    	
    	List M = new ArrayList<GameState>();
    
    	
    	GameState test;
    	
    	
    	int G = 0;
    	int F = 0;
    	int H = 0;
    	
    	GameState n = null;

         
        while(frontier.isEmpty() == false){

        	
        	n = (GameState) frontier.get(0);
        	frontier.remove(n);
        	closedList.add(n);
        	
        	
        	
        	if(n.isGoalState()){
 
        		//n.printState();
        		return closedList;   //this needs modifying
        		//break;
        		
        	}
        	
        	//	int lowestF = manhattanDistance(frontier.get(0)) + initialtoNodeDistance(frontier.get(0));
        	
        	int lowestFValue = 0;
        	GameState lowestFValNode = null;
        	//M = explored nodes
        	
        	//for each neighbour(legal neighbour)
        	//expand nodes
        	M = getLegalActions(n);			//neighbours      legalActions.get(i) = neighbour
        	for(int i = 0; i < M.size(); i++){
        		//add every (legal) explored node to the frontier
        		if(frontier.contains(M.get(i))){
        			continue;
        		}
        		
        		frontier.add(M.get(i));
        		
        		if(!graph.contains(M.get(i))){		//installs leafs
        			graph.add(M.get(i));
        		}
        		
        		
        		//for each explored node work out the cost from currentNode (n);
        		G = initialtoNodeDistance((GameState)M.get(i));
        		
        		//for each work out the heuristic value
        		H = manhattanDistance((GameState)M.get(i));
        		
        		//calculate f_value for all explored nodes
        		F = G + H;
        		
        		//find the lowestF
        		if(i == 0){
        			lowestFValue = F;
        			lowestFValNode = (GameState) M.get(0);
        		}else if(F < lowestFValue){
        			lowestFValue = F;
        			lowestFValNode = (GameState) M.get(0);
        		}
        	}
        	//add lowestFValNode to frontier
        	frontier.add(lowestFValNode);
        	closedList.remove(lowestFValNode);
        	
        }
        
        
        
    	return frontier;
    	
	}
    
    
    */
    
/*    public List<GameState> EvaluateFn2(GameState state){
    	
    	List openList = new ArrayList<GameState>();
    	openList.add(state);
    	
    	//creates a graph with the initial state
    	List graph = new ArrayList<GameState>();
    	List graph_g_score = new ArrayList<Integer>();
    	graph.add(state);
    	graph_g_score.add(0);
    	
    	
    	List closedList = new ArrayList<GameState>();
    	
    	List neibours = new ArrayList<GameState>();
    	List<GameState> parentNodesOfNeibours = new ArrayList<GameState>();
    	List<Integer> g_scores = new ArrayList<>();
    	
    	int currentGScore;
    	
    	GameState current;
    	
    	//this may need to be changed to while we haven't found goal state.
    	while(openList.isEmpty() == false){
    		//getOptimalNode uses manhattan distance
    		current = getOptimalNode(openList);
    		if(current.isGoalState()){
    			current.printState();
    			System.out.println("done");
    		//	System.out.println("size: " + parentNodesOfNeibours.size());
    			System.out.println("Open size: " + openList.size());
    			System.out.println("Closed size: " + closedList.size());
    			System.out.println("graph_g_score size: " + graph_g_score.size());
    			System.out.println("graph size: " + graph.size());
    			
    		//	return openList;
    			return parentNodesOfNeibours;
    		}else{
    			closedList.add(current);
    			openList.remove(current);   //this may not work
    			
    			neibours = getLegalActions(current);
    			
    			currentGScore = initialtoNodeDistance(current);
    			
    			//sets an array containing parent nodes for each;
    			//sets g Scored for each
    
    		
    			for(int k = 0; k < neibours.size(); k ++){
    				
    				
    				if(closedList.contains(neibours.get(k)) && currentGScore < initialtoNodeDistance((GameState) neibours.get(k))){
    					g_scores.add(k, currentGScore);
    					parentNodesOfNeibours.add(k, current);
    					//update the neibours
    					//change neibours parent to our current node;
    					
    				}else if(openList.contains(neibours.get(k)) && currentGScore < initialtoNodeDistance((GameState) neibours.get(k))){
    					g_scores.add(k, currentGScore);
    					parentNodesOfNeibours.add(k, current);
    					//update the neibours
    					//change neibours parent to our current node;
    					
    				}else{
    					openList.add(neibours.get(k));
    					g_scores.add(k, initialtoNodeDistance((GameState) neibours.get(k)));
    					
    				}
    			
    				
    				
    				
    				
    				
    			}
    			
    			
    			
    			
    			
    		}
    		
    		
    		
    		
    	}
    	
    	
    	
    	
    	
    	return null;
    }
    
    
    
    */
    
    public List<MyGameState> EvaluateFn3(MyGameState state){
    		
    	List<MyGameState> openList = new ArrayList<MyGameState>();
    	List<MyGameState> closedList = new ArrayList<MyGameState>();
    	
    	MyGameState lowestF = null;
    	List<MyGameState> LegalActions = null;
    	
    	//adds initial state to the openList
    	openList.add(state);
    	
    	while(!openList.isEmpty()){
    		lowestF = getOptimalNode(openList);  //lowest f scoring node in the openList;
    		//lowestF.printState();
    	//	break;
    		openList.remove(lowestF);
    		LegalActions = getLegalActions(lowestF);
    		state.
    		
    		//set parents of successors
    		for(int i = 0; i < LegalActions.size(); i ++){
    			LegalActions.get(i).setParent(lowestF);
    		}
    		
    		for(int k = 0; k < LegalActions.size(); k++){
    			if(LegalActions.get(k).isGoalState()){
    				System.out.println("finished");
    				return openList;
    			}
    			
    			
    			
    			LegalActions.get(k).setG(lowestF.getG() + distanceBetween(LegalActions.get(k), lowestF));
    			LegalActions.get(k).setH(manhattanDistance(LegalActions.get(k)));
    			LegalActions.get(k).setF(LegalActions.get(k).getG() + LegalActions.get(k).getH());
    			
    			
    			
    		}
    		
    		
    		
    		
    
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	return openList;
    	
    	
    	
    }
    
    
    
    
    
    
    
/*	if(closedList.contains(M.get(i))){
		continue;
	}
	temp_g_score = initialtoNodeDistance(n) + distanceBetween(n, (GameState) M.get(i)); //this may be bugged.
	
	if(!frontier.contains(M.get(i)) || temp_g_score < g_score){
		
		
		g_score = temp_g_score;
		f_score = initialtoNodeDistance((GameState) M.get(i)) + manhattanDistance((GameState) M.get(i));
		
		if(!frontier.contains(M.get(i))){
			frontier.add(M.get(i));
		}
		
	}		*/
   
    public List<GameState> getLegalActions(MyGameState state){
    	//A list containing legal leaf-nodes from given state.
    	
    	List<GameState> LegalStates = new ArrayList<GameState>(); 
    	LegalStates.add(state.moveLeft());
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
    //this is used to return the most optimal leaf-node branching from the given node/state.
/*    public MyGameState getOptimalLeaf(MyGameState state){
    	List<MyGameState> legalNodes = getLegalActions(state);
    	int h = 0;
    	int g = 0;
    	
    	int lowestF = manhattanDistance(legalNodes.get(0)) + initialtoNodeDistance(legalNodes.get(0));
    	//sets state with lowest F to the initial node in the legalNodes List
    	MyGameState lowestConnectedNode = legalNodes.get(0);
    	
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
    }*/
    
    
    //this is used to get the most optimal node given a list.
    public MyGameState getOptimalNode(List<MyGameState> frontier){
    	
    	int h = 0;
    	int g = 0;
    	
    	
    	int lowestF = manhattanDistance(frontier.get(0)) + initialtoNodeDistance(frontier.get(0));
    	MyGameState lowestConnectedNode = frontier.get(0);
    	
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
    public int initialtoNodeDistance(MyGameState stateN){
    	
    	int currentPX = stateN.playerCol;
    	int currentPY = stateN.playerRow;
    	
    	int currentXDist = Math.abs(currentPX - initialPlayerStateX);
    	int currentYDist = Math.abs(currentPY - initialPlayerStateY);
    	
    	return currentXDist + currentYDist;
    }
    
    public int distanceBetween(MyGameState fromState, MyGameState toState){
    	
    	int fromStateX = fromState.playerCol;
    	int fromStateY = fromState.playerRow;
    	
    	int toStateX = toState.playerCol;
    	int toStateY = toState.playerRow;
    	
    	int XDist = Math.abs( toStateX - fromStateX );
    	int YDist = Math.abs(toStateY - fromStateY );
    	
    	
    	return XDist + YDist;
    	
    	
    	
    	
    }
    //takes position and a goal returns the number of moves to the nearest goal position.
    public int manhattanDistance(MyGameState state){
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
   
    public List<MyGameState> getSolution(){

    	// your code goes here ...
    	return null;
    }
    
    public void showSolution(List<MyGameState> solution) {               
        for (MyGameState st : solution) {            
            display.updateState((MyGameState) st);
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
       // GameState state = args.length==0?new GameState("src/levels/level6.txt"):new GameState(args[0]);    
        MyGameState state = args.length==0?new MyGameState("src/levels/level6.txt"):new MyGameState(args[0]);  
        long t1 = System.currentTimeMillis();
        ComputerPlayer player = new ComputerPlayer(state);  
        //List<GameState> solution = player.getSolution(); //OLD CODE
        List<MyGameState> mySolution = player.EvaluateFn3(state);
        long t2 = System.currentTimeMillis();
        System.out.println("Time: " + (t2-t1));
        player.showSolution(mySolution);
    }
     
}
