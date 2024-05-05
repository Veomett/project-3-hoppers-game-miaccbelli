import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FrogGraph {
    public static void main(String[] args) {
        /***
         * You don't need to do anything here; this main method is purely
         * to help you debug.  Note that the testFrogs below is the
         * example given in the Project Description on Canvas.
         */
        int[][] testFrogs = new int[][]{{0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}};

        int[][] testFrogs_a = new int[][]{{0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0}};
        int[][] testFrogs_b = new int[][]{{1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        FrogArrangement myFrogs = new FrogArrangement(testFrogs);
        FrogArrangement myFrogs_a = new FrogArrangement(testFrogs_a);
        FrogArrangement myFrogs_b = new FrogArrangement(testFrogs_b);
        FrogGraph myGraph4 = new FrogGraph(myFrogs);
        myGraph4.printSolutions();
        System.out.println("This should say true: " + myGraph4.areAdjacent(myFrogs_a, myFrogs_b));

        ArrayDeque<Integer> solsArray = loadFrogs("Frog_inputs.csv");
        System.out.println("The following line should be 2 6 1 1 5 0 0 0 1:");
        while(!solsArray.isEmpty()){
            int curInt = solsArray.remove();
            System.out.print(curInt + " ");
        }
    }
    FrogArrangement startingArrangement;
    // This will be the starting arrangement from which you create your graph
    /***
     * You must use the predecessorMap below.
     * If Arrangement2 was "discovered" by an edge from Arrangement1,
     * then Arrangement 1 is the predecessor of Arrangement 2.
     * Using this predecessorMap will help you re-create the steps
     * to a winning arrangement.
     */
    HashMap<FrogArrangement, FrogArrangement> predecessorMap;
    /***
     * frogNeighbors corresponds to the adjacency list representation for this graph
     * frogNeighbors.get(someFrogs) should return a list (a queue) of frog arrangements
     * that someFrogs are adjacent to.  Remember that this is an undirected graph, so if
     * frogNeighbors.get(someFrogs) contains otherFrogs, then frogNeighbors.get(otherFrogs)
     * should contain someFrogs
     */
    HashMap<FrogArrangement, Queue<FrogArrangement>> frogNeighbors;
    // this is an adjacency list
    // if a frog not a key in frogNeighbors, then it hasn't been visited yet
    /***
     * You don't *need* to have distFromStarting, but it doesn't hurt, and may be helpful
     * in debugging.  It should give the distance that a frog arrangement is from the startingArrangement.
     * Here, distance is the minimum number of edges traversed from the startingArrangement
     * to the frog arrangement.
     */
    HashMap<FrogArrangement, Integer> distFromStarting;
    /***
     * winningArrangements should contain all of the winning arrangements (with exactly 1 frog)
     * that can be reached by the startingArrangement
     */
    Queue<FrogArrangement> winningArrangements = new ArrayDeque<>();

    FrogGraph(FrogArrangement frog) {
        /***
         * TODO: Complete this constructor
         * The parameter frog is the startingArrangement for this graph.
         * This constructor must call createGraph, which will populate:
         * predecessorMap, frogNeighbors, distFromStarting (if used), and winningArrangements
         */
    }
    protected void createGraph(FrogArrangement frog){ // remember! this is a BFS search method
        FrogArrangement curFrogs = startingArrangement;
        Queue<FrogArrangement> curQueue = new ArrayDeque<>();
        frogNeighbors.put(curFrogs, curQueue); // adding currFrogs into frog neighbors
        Queue<FrogArrangement> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(curFrogs); // all setting us up to do a bfs

        // do the following in a while loop (while the bfsQueue is not empty):
        // remove from queue and do the following:
        for (int[] from : FrogArrangement.hopOptions.keySet()){
            for (int[] over : FrogArrangement.hopOptions.get(from).keySet()){
                int[] to = FrogArrangement.hopOptions.get(from).get(over);
                // can we do that hop on curFrogs? (call canHop() method from FrogArrangement)
                // if yes (it can hop): create newFrogs by doing the hop
                // if newFrogs has not been seen before (aka must check if newFrogs is a key in the predecessor Map)
                    // add it to bfsQueue
                    // add curFrogs as its predecessor
                    // check to see if it is a winning state (if so, add to winning Arrangements)
                    // create Queue in the neighborMap, corresponding to key newFrogs
                // add newFrogs as neighbor of curFrogs
                // add curFrogs as neighbor of newFrogs
            }
        }

        /***
         * TODO: implement this method.
         * The FrogArrangement frog is the startingArrangement.
         * This method must populate all of :
         * predecessorMap, frogNeighbors, and winningArrangements
         * It will also populate distFromStarting if you decide to use it
         */
    }
    public void printSolutions(){
        /***
         * TODO: implement this method.
         * See the project description on Canvas to see how the print should look.
         * MIGHT WANT TO USE A STACK TO PRINT THE STEPS/MOVES BACKWARDS FROM THE WINNING ARRANGEMENT TO
         * THE STARTING ARRANGEMENT
         * USING THE PREDECESSOR OF THE WINNING ARRANGEMENT TO THE PREVIOUS STEP AND THEN ITS PREVIOUS STEP, ETC
         * PRINT FROM THE STACK (LIFO)
         */
    }
    public boolean areAdjacent(FrogArrangement frogs1, FrogArrangement frogs2){
        /***
         * TODO: implement this method.
         * Should return true if you can get to the arrangement frogs1 from frogs2
         * with a single hop OR if you can get to the arrangement frogs2 from frogs1
         * with a single hop.  In other words, remember that this is an undirected graph.
         * This method isn't explicitly needed to create the graph, but it is generally
         * part of the graph ADT, and it will help you get partial credit if you don't
         * fully complete the project
         */
        return false;
    }
    public static ArrayDeque<Integer> loadFrogs(String frogFilename){
        /***
         * TODO: Describe this method with javadoc style documentation, but don't change
         * This method is just here so that you can do additional debugging checks.
         * Please do look at it and explain what it does with javadoc style documentation.
         */
        ArrayDeque<Integer> numOfSolutionsArray = new ArrayDeque<>();
        try {
            FileInputStream inStream = new FileInputStream(frogFilename);
            Scanner scanner = new Scanner(inStream);
            while(scanner.hasNextLine()) {
                int count = 0;
                int[][] curFrogs = new int[5][];
                while (count < 5 && scanner.hasNextLine()) {
                    String curLine = scanner.nextLine();
                    String[] clean = curLine.trim().split(",");
                    if (!(clean.length == 5)) {
                        System.out.println("Something is wrong with the input file");
                        return numOfSolutionsArray;
                    } else {
                        curFrogs[count] = new int[]{intValue(clean[0]), intValue(clean[1]), intValue(clean[2]), intValue(clean[3]), intValue(clean[4])};
                    }
                    count += 1;
                }
                if (count !=5){
                    System.out.println("Somehow we didn't get 5 lines");
                    return numOfSolutionsArray;
                }
                else{
                    FrogArrangement curArrangement = new FrogArrangement(curFrogs);
                    FrogGraph curGraph = new FrogGraph(curArrangement);
                    numOfSolutionsArray.add(curGraph.winningArrangements.size());
                }
            }
            return numOfSolutionsArray;
        } catch (IOException var9) {
            var9.printStackTrace();
            return numOfSolutionsArray;
        }
    }
    private static int intValue(String s){
        /***
         * TODO: do not change this method, but explain what this method does using javadoc style documentation
         */
        if (s.equals("0")){
            return 0;
        }
        else{
            return 1;
        }
    }
}
