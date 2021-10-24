# AI-at-a-glance

Please contact me via the bellow details for any further enquiries about this code, any other related project, a partnership, or a job opportunity

Contact Details:
	email           : jamilesphesihle99@gmail.com
	call number     : +27 68 5022 681
	whatsapp number : +27 76 482 7695

Profiles:
	LinkedIn  : https://www.linkedin.com/in/sphesihle-banele-jamile-372503214/
	GitHub    : https://github.com/SphesihleJamile
	Facebook  : https://www.facebook.com/profile.php?id=100072245011098
	Instagram : https://www.instagram.com/siphesihle____/
  
Okay, let's get to it
As you may have realized, this was my assignment, lol.
We were given a problem, where an agent has to enter a maze, and find a path to the end of the maze. There are multiple entry points and exit point, but, depending on which
  search algorithm that you use, it will have to find the most optimal path.
  
  The maze is a peace of land that has dry, 'D', and wet, 'W', lands. The agent can only move on dry lands. It can only perform 3 actions, which are straight, diagonally up,
  and diagonally down. The agent is not allowed to move directly up or down, and it is not allowed to take any action going backwards.
  Here is what a potential maze could look like:
    
    5
    7
    WWWWDDD
    DDWDDDD
    WWDDDDD
    DWWWDWW
    WDDDWWD
   
   I forgot to mention, the progam is supposed to read  the maze from an exterior text file. The first number at the top represents the number of rows that the maze will have, 
   and the second number represents the number of columns.
   All the remaining lines are the actual maze.
   
   All the 'D' characters on the first column would be the start states, and all the 'D' columns on the last column would be the end states. And all the characters within the 
   start and end nodes would represent the possible paths that the agent could take.
   
   So, you're asking yourself.... How does the program work? Let me first break down the classes.
   
NODE class
    This class represents the node, or the agent itself. It has the specifics as to how the agent/node records its movement and where did it come from. The agent also has the ability
    to reproduce, giving birth to children. No, no, there's no copulation. The node has to expand (reproduce) itself to produce children depending on the actions that are available for
    it the current position that it's in. The selected algorithm will the pick the best node, and continue going forward while storing the unvisited nodes in a frontier.
    Interesting right.
    If there are no possible actions going forward, the agent will not be able to reproduce, and will thus be forced to select the best available positions that are stored in the frontier.
    Here are the mothos in the class :
      > getRow()                - returns the row in which the agent is in, in the maze.
      > getCol()                - returns the column in which the agent is in, in the maze.
      > toString()              - returns a string that represents the current position of the agent. This is especially useful in the solution path.
      > equals(Node n)          - this method checks if any other node is equal to the current node.
      > Child(int action)       - this method is identical to the Result() method thats in the Problem class.
      > Expand(Problem problem) - this method expands the current node, to produce other nodes that lead to the goal state.
 
PROBLEM class
    Computer Science is all about solving problems. This class is the problems sanctuary. This is where the entire maze is stores, and all of its details. 
    When an object of this class is created, it requires 3 inputes :
      - The initial/start state (position)
      - The goal nodes/terminating states
      - The maze
    With these 3 inserted, you'll have available a multitude of methods or functions, some of which are as follows :
      > getProblem() - This method returns the maze iteself.
      > Result()     - This method returns a specific child of the current agent, when it takes a specific action.
      > GoalTest()   - This is a boolean method that returns true if the agents current position is the goal state, and false otherwise.
      > Action()     - Returns a list of actions that can be taken depending on the agents current position, and the available surrounding dry positions around the agent.
                     - This list of actions is very important because the NODE class uses this list to produce child nodes.
ALGORITHMS class
    Home sweet home. I dont know about you, but this was the hardest part to code. I'm still a beginner in this field of Computer Science, so I learn a lot of things through projects
    such as this one, and the others in my girhub account.
    This class represents all the search algorithms I used, for the agent to be able to find its way through the maze. Different search algorithms work in different way.
    Let's take a look at them :
      > Breadth_First_Search()   - This search algorithm starts at the start statem and expands itself to produce children. The whole process of expansion and which actions are possible
                  purely depend on the NODE and PROBLEM class. Search algorithms only solve 1 question, and that is.... "where do I go from here?".
                  The breadth first search, according to the tree diagram, and my own understanding, searches the probelm line by line. In this case, it will visit all the avaiable nodes 
                  in the frontier and expands them, and test if they are the goal nodes, before it moves forward. 
                  The downfall about this algorithm that it has a high time and space complexity, because it has to store a large number of nodes and process all of them before it moves forward.
      > Depth_First_Search()     - This search algorithmsm goes deep before it goes wide. Unlike the Breadth_First_Search algortihm that explores every node that goes forward, the Depth_First_Seacrch
                  explores 1 node, and it goes down to the child... It then expands that child, and goes down to one of its children. It uses a stack to do this, do it always explores the last node to
                  enter the stack. It will go straight down that route until it cannot expand anymore, or until it finds the goal state. If it dowan find the goal state, and it cannot expand, it visits
                  the next node in the frontier, and it goes down. It continues to do so until the goal state is found, or until it has run through the entire maze.
                  One of the downfalls of this search algorithm is that, (1) a solution is not guaranteed, and (2), the algorithm can be stuck in an infinit path or loop.
                  This algortithm has a very high space complexity and a high time complexity as compared to the other algorithms.
      > Best_First_Search()       - Best_First_Search is almost similar to Depth_First_Search, but it differs in that it does not go down an endless path. This algorithm expands itself
                  and produces its child nodes. It then picks the child node that has lowest path cost. A lower path cost just means that the node is relitively closer to get to according to come function.
                  The space and time complexity of the algorithm depends on the size of the maze.
      > A_Star_Search()           - This is the famous A* search, that almost made me hate coding this project. The difference between the A* search and the above search algorithms is that
                  the above algorithms are all part of what we call the Uninformed Search Algorithms (mean ing that they have no futher knowledge about the maze, or the problem at hand).
                  The A* algorithm is an Informed Search Algorithm that uses a Heuristic value to calculate which node it has o visit next. There is function that returns a value wich is the outcome of adding
                  the heuristic value and the path cost. The search algorithm then uses this value to decide which node it is to pick next.
                  My heauristic value is coded in the AStarSearchComparator class. It calculates the heuristic depending on which node is closer to the most goal nodes, and which od these nodes is closer
                  to the clostest goal node via a straight line distance. I know it sounds confusing, but it really isn't.
                  
Any other class that is in the project is used to help the Search Algorithms perform their functions properly, as these algorithms use datastructures such as Priority Queues and Stacks.

If you just want to run the program without reading the code, you can download the file named 218079316.jar. Place that file on your desktop, and also place the text file that has the maze on your desktop.
To run the ,jar file, open cmd and type the following:
    >cd Desktop
          Then wait...
    >java -jar 218079316.jar
          The program will then start and you will wach it do it's thing.
    
  
Open this code using the IntelliJ IDE. It will surely work on any other IDE that supports java, but it's preferrable that you use IntelliJ.

Stay Safe.
Happy Coding
  
