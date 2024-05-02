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
                /***
                 * TODO: Finish this HashMap
                 * Once you have finished initializing, it will contain
                 * *all* of the correct hop options.  Please see the
                 * Project Description on Canvas for additional details.
                 */
            }});

        put(new int[]{0, 2}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{0, 4}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{1, 1}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{1, 3}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{2, 0}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{2, 2}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{2, 4}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{3, 1}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{3, 3}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{4, 0}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{4, 2}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});

        put(new int[]{4, 4}, new HashMap<int[], int[]>() {
            {
                put(new int[]{0, 2}, new int[]{0, 4});
                put(new int[]{2, 0}, new int[]{4, 0});
                put(new int[]{1, 1}, new int[]{2, 2});
            }});
    }});
    int[][] frogs;

    FrogArrangement(int[][] inputFrogs){
            /***
             * TODO: Implement this constructor
             * inputFrogs will be the frogs for this FrogArrangement
             * Make sure you do a deep copy, not a shallow copy!
             */
    }


    /***
     * TODO: implement additional methods of your choosing
     * As stated in the project description, I had the following additional methods:
     * canHop, hop, isWinningState
     * You choose the methods you want and the signatures that you want for your methods.
     */

    public boolean canHop(int[] from, int[] over, int[] to){
        /***
         * need to check 3 things:
         * if there is a frog that u can hop over:
         * are there frogs in the from and over position?
         * AND are there NO frogs in the to position?
         */
    }

    public FrogArrangement (int[] from, int[] over, int[] to){ // is this the hop() method?
        // construct the new frog arrangement, jumping from 'from' over 'over' and to 'to'
        // use constructor, return the resulting arrangement
    }


    public boolean isWinningState(){
        // check if there is only one frog left
        // can do this by summing all the entries of the adjacency list
        // if it equals 1, then it is a winning arrangement
        // if anything else, then it is not a winning arrangement
    }

    public void printFrogs(){
            /***
             * TODO: implement this printFrogs
             * See the Project Description on Canvas to see how it should look.
             */
    }


    /***
     * TODO: DO NOT TOUCH THESE, ONLY ADD JAVADOC NOTES FOR EACH METHOD
     */
    private int calculateHash(int[][] f){
            /***
             * TODO: DO NOT TOUCH THIS METHOD!
             * But please do briefly describe what it is doing using javadoc documentation.
             */
        int hash = 0;
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                hash = (int) (hash + f[i][j]*Math.pow(2, (i*5+j)));
            }
        }
        return hash;
    }
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
    @Override
    public int hashCode() {
        /***
         * TODO: DO NOT TOUCH THIS METHOD!
         * But please do briefly describe what it is doing using javadoc documentation.
         */
        return calculateHash(this.frogs);
    }
}
