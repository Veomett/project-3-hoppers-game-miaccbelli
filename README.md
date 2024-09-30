# ThinkFun Hoppers Game Solver

### ABOUT THIS PROJECT
#### This was my final project for the course Data Structures & Algorithms. Some of the base code and tests were given. However, the following tasks I completed:
####    1. Developed a FrogArrangement class to represent game states in the Hoppers game using a 2D array to track frog positions.
####    2. Implemented FrogGraph class to model game moves as an undirected graph, with each node representing a board arrangement and edges representing valid frog hops.
####    3. Utilized Abstract Data Types including Queues and Stacks to display game state transitions, validate possible moves, and backtrack solutions.
####    4. Designed methods to check for winning conditions, simulate frog hops, and determine all possible solutions from a starting configuration.

### THE GOAL
#### To generate all possible solutions to any given board arrangement of ThinkFun's Hoppers Game and then display every move required to reach the winning solution.

### CHALLENGES & OTHER COMMENTS
#### By far, for me, the FrogGraph class was where I hit a lot of speed bumps in this project. I'm so relieved that I scheduled this project so well and gave myself plenty of time to debug -- I started 2 weeks before it was due and spent nearly 2 whole days debugging and reworking the FrogGraph class. The createGraph() method took a lot of time due to strategizing how to incorporate the BFS method into finding all possible arrangements and then keeping track of the current arrangement using the predecessor map to find the next move, and finally adding those arrangements post each move to the queue to print. 

### OTHER
#### Date Project Completed: 2024
#### Tools Used: Java (via IntelliJ)
