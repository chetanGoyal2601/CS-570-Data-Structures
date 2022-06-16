//Chetan Goyal
//CWID: 20005334
package Maze;
import java.lang.module.FindException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
        // current block is out of bound of grid or is a part of background - will return false
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || (!maze.getColor(x, y).equals(NON_BACKGROUND))) {
            return false;

        // current block = exit - will return true since valid path is found
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;

        // no exit at current block then we set the current block to the colour of the path
        } else {
            maze.recolor(x, y, PATH);

            // neighbouring blocks of current block = exit - will return true
            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y + 1) || findMazePath(x, y - 1)) {
                return true;

            // recolour current block to temporary
            } else {
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
        if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || (!maze.getColor(x, y).equals(NON_BACKGROUND))) {
            return;
        }

        else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y)); 
            ArrayList<PairInt> cur = new ArrayList<>(trace); 
            result.add(cur);
            trace.pop(); 
            maze.recolor(x, y, NON_BACKGROUND); 
            return;
        }

        else {
            trace.push(new PairInt(x, y)); 
            maze.recolor(x, y, PATH);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }
    }

    // for searching all paths to resolve the maze problem
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);

        //to return an empty list if size is zero
        if(result.size() == 0) {
   		    ArrayList<PairInt> aux = new ArrayList<PairInt>();
   		    result.add(aux);
   	    }

        return result;
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        ArrayList<ArrayList<PairInt>> pathsTotal = findAllMazePaths(x, y);
        int[] countArray = new int[pathsTotal.size()];

        // make an array for all sizes of the pair containing stacks
        for (int i = 0; i < pathsTotal.size(); i++) {
            countArray[i] = pathsTotal.get(i).size();
        }

        int min = countArray[0];
        int minIndex = 0;

        // find the index for the minimum path, which will be determined by smallest size
        for (int i = 1; i < countArray.length; i++) {
            if (countArray[i] < min) {
                min = countArray[i];
                minIndex = i;
            }
        }

        return pathsTotal.get(minIndex);
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
