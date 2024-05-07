import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
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
        this.startingArrangement = frog;
        this.predecessorMap = new HashMap<>();
        this.frogNeighbors = new HashMap<>();
        this.distFromStarting = new HashMap<>();
        createGraph(frog);
    }

    /***
     * createGraph(): uses a BFS search method to explore all possible frog arrangements from the
     * starting arrangement, and using the predecessor map, it keeps track of which arrangement came
     * before each new arrangement, frog neighbors tells us which frog arrangements are
     * connected/neighbors, and we call isWinningState from the frogArrangement class to
     * determine if there is 1 frog left and adds this new arrangement to the winningArrangements queue
     *
     * @param frog - the starting frog arrangement
     */
    protected void createGraph(FrogArrangement frog) {
        FrogArrangement curFrogs = startingArrangement; // lines 94-98 were given in class
        Queue<FrogArrangement> curQueue = new ArrayDeque<>();
        frogNeighbors.put(curFrogs, curQueue); // adding currFrogs into frog neighbors
        Queue<FrogArrangement> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(curFrogs); // all setting us up to do a bfs

        if (curFrogs.isWinningState()) { // checks if given startingArrangement is already a winning state
            winningArrangements.offer(curFrogs); // then adds to winningArrangements
        }

        while (!bfsQueue.isEmpty()) {
            FrogArrangement current = bfsQueue.poll(); // removing form queue

            // iterating over all possible hops in the curr frogArrangement
            for (int[] from : FrogArrangement.hopOptions.keySet()) { // lines 108-110 were given in class
                for (int[] over : FrogArrangement.hopOptions.get(from).keySet()) {
                    int[] to = FrogArrangement.hopOptions.get(from).get(over);

                    // can we do that hop on curFrogs? (call canHop() method from FrogArrangement)
                    if (current.canHop(from, over, to)) {
                        FrogArrangement newFrogs = current.hop(from, over, to); // making new arrangement to show hop

                        if (!predecessorMap.containsKey(newFrogs)) { // if newFrogs has not been seen before (must check if newFrogs is a key in the predecessor Map)
                            bfsQueue.add(newFrogs); // add new frogs to bfsQueue
                            predecessorMap.put(newFrogs, current); // mark predecessor
                            if (newFrogs.isWinningState()) {
                                winningArrangements.offer(newFrogs); // adding newFrogs to the winnningArrangement queue
                            }
                            frogNeighbors.put(newFrogs, new ArrayDeque<>()); // making new Queue in the neighborMap, corresponding to key newFrogs
                        }
                        // add newFrogs as neighbor of curFrogs
                        frogNeighbors.get(current).offer(newFrogs);
                        // add curFrogs as neighbor of newFrogs
                        frogNeighbors.get(newFrogs).offer(current);
                    }
                }
            }
        }
    }

    /***
     * printSolutions(): prints all possible solutions and iterates through the winning arrangements
     * to print all the steps to reach a winning arrangement from the starting arrangement (prints
     * using a stack where the moves are in reverse order (LIFO))
     * @refer - https://www.educative.io/answers/how-to-use-the-stack-class-in-java for stack usage
     */
    public void printSolutions(){
        if(winningArrangements.isEmpty()) { // if there are no winning arrangements
            System.out.println("No winning arrangements found.");
            return;
        }

        Stack<FrogArrangement> movesStack = new Stack<>(); // creating a stack to store & print the moves/steps
        System.out.println("Number of different ending states: " + winningArrangements.size() +".");

        for(FrogArrangement winningArrangement : winningArrangements) {
            System.out.println("This arrangement: ");
            winningArrangement.printFrogs();

            int distance = distFromStarting.get(winningArrangement);
            System.out.println("Has distance " + distance + " from the starting position.");

            // getting all the moves from the predecessor map and putting into our stack
            FrogArrangement curArrangement = winningArrangement;
            while(predecessorMap.containsKey(curArrangement)) { // while num of frog arrangements not null
                FrogArrangement predecessor = predecessorMap.get(curArrangement);
                movesStack.push(predecessor);

                curArrangement = predecessor;
            }

            while(!movesStack.isEmpty()) {
                // printing the top of stack and popping it off to move to the move (producing LIFO system)
                System.out.println(movesStack.pop());
            }

            System.out.println("----------");

        }

    }

    /***
     * areAdjacent(): tells us whether two frog arrangements are adjacent to each other by 1 hop or position
     * @param frogs1 - one frog arrangement in the comparison
     * @param frogs2 - the other frog arrangement to compare with frogs1
     * @return - true if frogs are adjacent to each other, false otherwise
     */
    public boolean areAdjacent(FrogArrangement frogs1, FrogArrangement frogs2){
        if (!frogNeighbors.containsKey(frogs1) || !frogNeighbors.containsKey(frogs2)) {
            return false; // false if 1 or both of the frog arrangements are not in the graph
        }
        Queue<FrogArrangement> neighbors = frogNeighbors.get(frogs1); // grabbing neighbors of frogs1

        if (neighbors!= null) {
            return neighbors.contains(frogs2); // true when the frogs1 and frogs2 are neighbors
        }
        return false;

    } // USE .EQUALS() - suggested by prof

    /***
     * loadFrogs(): loads frog arrangements from file, calculates the number of winning
     * arrangements using a frogGraph, and stores/returns this info in a deque
     * @param frogFilename - filename of the file with the frog arrangements
     * @return - a deque containing the number of winning arrangements for each frog arrangement
     * TODO: DON'T TOUCH!
     */
    public static ArrayDeque<Integer> loadFrogs(String frogFilename) {
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

    /***
     * intValue(): converts the string value of a frog position into a integer (either 0 or 1)
     * @param s - the string value of the frog position (0 = empty, else = frog present)
     * @return - 0 if the frog position is empty with a "0", or 1 if s has another value showing it's occupied by a frog
     * TODO: DON'T TOUCH!
     */
    private static int intValue(String s){
        if (s.equals("0")){
            return 0;
        }
        else{
            return 1;
        }
    }
}

