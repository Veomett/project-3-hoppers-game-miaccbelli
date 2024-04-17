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
    public void printFrogs(){
            /***
             * TODO: implement this printFrogs
             * See the Project Description on Canvas to see how it should look.
             */
    }
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
