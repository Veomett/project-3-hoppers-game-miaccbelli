import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FrogArrangement {
    static Map<int[], Map<int[], int[]>> hopOptions = Collections.unmodifiableMap(new HashMap<int[], Map<int[], int[]>>() {{
        put(new int[]{0, 0}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{0, 2}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 0}, new int[]{0, 2});
                put(new int[]{0, 4}, new int[]{0, 2});
                put(new int[]{2, 2}, new int[]{4, 2});
            }});

        put(new int[]{0, 4}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{1, 1}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 0}, new int[]{0, 2});
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{2, 2});
                put(new int[]{2, 2}, new int[]{2, 4});
                put(new int[]{1, 3}, new int[]{2, 2});
                put(new int[]{3, 1}, new int[]{2, 2});
                put(new int[]{3, 3}, new int[]{2, 2});
                put(new int[]{2, 4}, new int[]{3, 3});
                put(new int[]{4, 0}, new int[]{3, 1});
                put(new int[]{4, 2}, new int[]{3, 3});
                put(new int[]{4, 4}, new int[]{3, 3});
            }});

        put(new int[]{1, 3}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 2}, new int[]{4, 2});
                put(new int[]{2, 4}, new int[]{4, 4});
            }});

        put(new int[]{2, 0}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 0}, new int[]{2, 0});
                put(new int[]{0, 2}, new int[]{2, 2});
                put(new int[]{2, 2}, new int[]{2, 4});
            }});

        put(new int[]{2, 2}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 0}, new int[]{2, 0});
                put(new int[]{0, 2}, new int[]{2, 4});
                put(new int[]{0, 4}, new int[]{2, 4});
                put(new int[]{1, 1}, new int[]{0, 0});
                put(new int[]{1, 3}, new int[]{0, 4});
                put(new int[]{3, 1}, new int[]{4, 0});
                put(new int[]{3, 3}, new int[]{4, 4});
                put(new int[]{4, 2}, new int[]{2, 2});
            }});

        put(new int[]{2, 4}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 2}, new int[]{4, 2});
                put(new int[]{2, 4}, new int[]{4, 4});
            }});

        put(new int[]{3, 1}, new HashMap<int[], int[]>() {
            {
                put(new int[]{1, 1}, new int[]{3, 1});
                put(new int[]{1, 3}, new int[]{3, 3});
                put(new int[]{3, 3}, new int[]{3, 1});
            }});

        put(new int[]{3, 3}, new HashMap<int[], int[]>() {
            {
                put(new int[]{1, 1}, new int[]{3, 1});
                put(new int[]{1, 3}, new int[]{3, 3});
                put(new int[]{3, 1}, new int[]{3, 3});
            }});

        put(new int[]{4, 0}, new HashMap<int[], int[]>() {
            {
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{2, 2}, new int[]{4, 2});
            }});

        put(new int[]{4, 2}, new HashMap<int[], int[]>() {
            {
                put(new int[]{2, 2}, new int[]{4, 2});
                put(new int[]{2, 4}, new int[]{4, 4});
                put(new int[]{4, 0}, new int[]{4, 2});
            }});

        put(new int[]{4, 4}, new HashMap<int[], int[]>() {
            {
                put(new int[]{2, 2}, new int[]{4, 2});
                put(new int[]{2, 4}, new int[]{4, 4});
            }});
    }});
    int[][] frogs;

    /***
     * FrogArrangement(): creates a new FrogArrangement object using the given frogs configuration (inputFrogs)
     * @param inputFrogs - initial configuration of the frogs in the 5x5 2D array that represents the
     *                     board/position of the frogs (1 = frog, 0 = empty space)
     */
    FrogArrangement(int[][] inputFrogs){
        frogs = new int[inputFrogs.length][inputFrogs[0].length]; // inputFrogs arr = the frogs arr for this arrangement

        // deep copy from inputFrogs arr >> into frogs arr
        for(int i = 0; i < inputFrogs.length; i++) {
            for(int j = 0; j < inputFrogs[i].length; j++){
                frogs[i][j] = inputFrogs[i][j];
            }
        }
    }


    /***
     * canHop(): boolean function that checks if a frog can hop from one position to the next using the
     * following checks: 1) if there is a position of the frog to hop from 'from' to 'over',
     * 2) if there are frogs in the from and over positions, 3) if the 'to' position is empty so the frog can land the hop
     * @param from - the position of the frog intending to hop
     * @param over - the position that the frog will hop over
     * @param to - the destination position where the frog will land the hop
     * @return false when a condition is not met (can't hop), true when all conditions are met and a frog can hop
     */
    public boolean canHop(int[] from, int[] over, int[] to){
        // checks if a key corresponds to the 'from' position and if that key's value contains the 'over' position as another key
        if (!hopOptions.containsKey(from) || !hopOptions.get(from).containsKey(over)) {
            return false; // false if there is no position to hop over a frog
        }
        // checks if from and over positions are empty to ensure there is a frog that the cur frog can hop over
        if (frogs[from[0]][from[1]] != 1 || frogs[over[0]][over[1]] != 1) {
            return false; // false if there are no frogs in either the from or over
        }
        // checks if the 'to' position where the current frog intends to hop is empty
        if (frogs[to[0]][to[1]] != 0) {
            return false; // false if a frog is in the destination to position that prevents the hop
        }
        return true;
    }

    /***
     * hop(): creates a new FrogArrangement object that represents the setup/board positioning
     * after a frog has made a hop from one position to another
     * @param from - the position of the frog intending to hop
     * @param over - the position that the frog will hop over
     * @param to - the destination position where the frog will land the hop
     * @return - the new FrogArrangement object that's the resulting arrangement after the hop
     */
    public FrogArrangement hop(int[] from, int[] over, int[] to){
        // deep copy of frogs >> newFrogs
        int[][] newFrogs = new int[frogs.length][frogs[0].length];
        for(int i = 0; i < frogs.length; i++) {
            for(int j = 0; j <frogs[i].length; j++) {
                newFrogs[i][j] = frogs[i][j];
            }
        }

        // updating positions to show hop
        newFrogs[from[0]][from[1]] = 0; // removing frog from 'from' position
        newFrogs[over[0]][over[1]] = 0; // removing the frog at the 'over' position (that we jumped over)
        newFrogs[to[0]][to[1]] = 1; // placing frog at the 'to' position where it landed

        return new FrogArrangement(newFrogs);
    }

    /**
     * isWinningState(): checks there is only 1 frog on the board but summing all the
     * entries of the frogs array and making sure there is only one to ensure it is a
     * winning arrangement
     * @return - true if there is only 1 frog, false otherwise
     */
    public boolean isWinningState(){
        int totalFrogs = 0;
        for(int i = 0; i < frogs.length; i++) {
            for(int j = 0; j < frogs[i].length; j++) {
                if(frogs[i][j] == 1) { // increment when position has a frog (being 1)
                    totalFrogs++;
                }
            }
        }
        return totalFrogs == 1; // returns true when total only == 1
    }

    /***
     * printFrogs(): prints out the current arrangement (5x5 2D array) of frogs on the board
     */
    public void printFrogs(){
        for(int i = 0; i < frogs.length; i++) { // i = rows, j = colums
            System.out.print("["); // opening bracket
            for(int j = 0; j < frogs[i].length; j++) {
                System.out.print(frogs[i][j]); // printing the value (1 or 0) at current position
                if(j < frogs[i].length - 1) {
                    System.out.print(","); // printing comma after each value except the last value
                }
            }
            System.out.println("]"); // closing bracket
        }
    }


    /***
     * TODO: DO NOT TOUCH THESE, ONLY ADD JAVADOC NOTES FOR EACH METHOD
     *
     */
    private int calculateHash(int[][] f){
        int hash = 0;
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                hash = (int) (hash + f[i][j]*Math.pow(2, (i*5+j)));
            }
        }
        return hash;
    }

    /***
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
            /***
             * TODO: DO NOT TOUCH THIS METHOD!
             * But please do briefly describe what it is doing using javadoc documentation.
             */
        if (this == null)
            return false;
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FrogArrangement that = (FrogArrangement) o;
        int thatHash = calculateHash(that.frogs);
        int thisHash = calculateHash(this.frogs);
        return (thisHash == thatHash);
    }

    /***
     *
     * @return
     */
    @Override
    public int hashCode() {
        /***
         * TODO: DO NOT TOUCH THIS METHOD!
         * But please do briefly describe what it is doing using javadoc documentation.
         */
        return calculateHash(this.frogs);
    }
}
