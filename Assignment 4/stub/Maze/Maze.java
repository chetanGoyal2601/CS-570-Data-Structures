

package Maze;
import java.util.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
    	int rowCount = maze.getNRows()-1;
    	int colCount = maze.getNCols()-1;
    	if(x < 0 || y < 0 || x > rowCount || y > colCount) {
    	return false;
    	}
    	else if(maze.getColor(x,y)== BACKGROUND || maze.getColor(x, y) == TEMPORARY) {
    		return false;
    	}
    	else if(x == rowCount && y == colCount) { 
    		maze.recolor(x, y, PATH);
    		return true;
        }
    	else if (maze.getColor(x, y) == NON_BACKGROUND){
			maze.recolor(x, y, TEMPORARY);
			if (this.findMazePath(x+1,y) || this.findMazePath(x-1,y) || this.findMazePath(x,y+1) || this.findMazePath(x,y-1) ) {
				maze.recolor(x, y, PATH);
				return true;
			}
		}
		return this.findMazePath(x+1,y) || this.findMazePath(x-1,y)  || this.findMazePath(x,y+1) || this.findMazePath(x,y-1);
    }
    
    
    
    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	int rowCount = maze.getNRows()-1;
    	int colCount = maze.getNCols()-1;
    	if(x > 0 || y > 0 || x > rowCount || y > colCount || (!maze.getColor(x,y).equals(NON_BACKGROUND))) {
        	return;
        } 
        else if(x == rowCount && y == colCount) {
        	ArrayList<PairInt> curList = new ArrayList<PairInt>(trace);
        	result.add(curList);
        	trace.pop();
        	maze.recolor(x, y, NON_BACKGROUND);
        	return;
    	} else {
        	trace.push(new PairInt(x,y));
        	maze.recolor(x, y,PATH);
    		findMazePathStackBased(x+1, y, result, trace);
    		findMazePathStackBased(x, y+1, result, trace);
    		findMazePathStackBased(x-1, y, result, trace);
    		findMazePathStackBased(x, y-1, result, trace);
    		maze.recolor(x, y, NON_BACKGROUND);
    	}
    }
    
    public ArrayList <ArrayList <PairInt>> findAllMazePaths (int x, int y){
      	 ArrayList <ArrayList <PairInt>> result = new ArrayList<>();
      	 Stack <PairInt> trace = new Stack<>();
      	 findMazePathStackBased(0, 0, result, trace);
      	 return result;
       }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList<ArrayList<PairInt>> finalRes = findAllMazePaths(x, y);
    	int minInd = 0;
    	int sizeArr = finalRes.size();
		int arr[] = new int[sizeArr];
		for (int i = 0; i < sizeArr; i++) {
			arr[i] = finalRes.get(i).size();
		}
		int min = arr[0];
		int arrLen = arr.length;
		for (int i = 1; i < arrLen; i++) {
		    if (arr[i] < min) {
		      min = arr[i];
		      minInd = i;
		    }
		}
		return finalRes.get(minInd);
    }
    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
