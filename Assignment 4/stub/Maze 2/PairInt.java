// Chetan Goyal
// CWID: 20005334

package Maze;

public class PairInt {

    //data field
    private int x;
    private int y;

    //constructor
    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;

    }

    //getter method for x
    public int getX() {
        return x;
    }

    //getter method for y
    public int getY() {
        return y;
    }

    //setter method for x
    public void setX(int x) {
        this.x = x;
    }

    // setter method for y
    public void setY(int y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object p) {
        if ( p == null) {
            return false;
        }

        PairInt pairInt = (PairInt) p;
        return (this.x == pairInt.x && this.y == pairInt.y);
    }

    @Override
    public String toString() {

        return "(" + x + ", " + y + ")";
    }

    public PairInt copy() {
        return new PairInt(x, y);
    }
}