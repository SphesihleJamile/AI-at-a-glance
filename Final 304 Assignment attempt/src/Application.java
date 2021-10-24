import java.util.*;
import java.io.*;
import java.lang.*;

public class Application {

    public static void printMaze(char[][] statespace)
    {
        for(int row = 0; row<statespace.length; row++)
        {
            for(int col = 0; col<statespace[row].length; col++)
                System.out.print(statespace[row][col]);
            System.out.println();
        }
    }

    public static void printStartStates(ArrayList<Node> startStates)
    {
        for(Node node : startStates)
        {
            System.out.println(node.toString());
        }
    }

    public static void printGoalStates(ArrayList<Node> goalStates)
    {
        for(Node node : goalStates)
            System.out.println(node);
    }

    public static void main(String[] args) throws IOException {
        Scanner readInput = new Scanner(System.in);
        System.out.print("Enter a file : ");
        String fileName = readInput.next().trim();

        //We are now reading the file
        File file = new File(fileName);

        try
        {
            readInput = new Scanner(file);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            return;
        }

        //The data about the maze is taken from the file and the maze is created, if the file is read. Else, and exception will be thrown
        int rows = readInput.nextInt();//Integer.parseInt(readInput.nextLine());
        int cols = readInput.nextInt();//Integer.parseInt(readInput.nextLine());
        char[][] maze = new char[rows][cols];
        ArrayList<Node> goalNodes = new ArrayList<>();
        ArrayList<Node> startStates = new ArrayList<>();

        //We are now loading our maze into our 2 dimensional array
        int traverseRows = 0;
        readInput.nextLine();
        while (readInput.hasNextLine())//while there are still lines in the txt file
        {
            String line = readInput.nextLine();//the variable 'line' stores the entire row

            for(int i = 0; i < line.length(); i++)
            {

                maze[traverseRows][i] = line.charAt(i);
                if(i == line.length()-1)//This is the last column, where the goal node could reside
                {
                    if(line.charAt(i) == 'D')//If this is the goal node
                        goalNodes.add(new Node(traverseRows, (line.length()-1)));
                }
                if(i == 0)//initial nodes
                {
                    if(line.charAt(i) == 'D')
                        startStates.add(new Node(traverseRows, 0));
                }
            }
            traverseRows++;
        }

        System.out.println();

        int numberOfStartStates = startStates.size();
        if(numberOfStartStates > 0)
        {
            for(int i = 0; i<numberOfStartStates; i++)
            {
                //Loops through each start state/ If there aren't any start states, then a message will be displayed.
                Problem problem = new Problem(startStates.get(i), goalNodes, maze);
                Algorithms algorithms = new Algorithms();
                Vector<Node> solutionPath;//Stores the solution path that will result while the program is trying to find the goal state
                //My Best-First Search has a problem

                Scanner read = new Scanner(System.in);
                System.out.println("Bread-First Search---> BFS");
                System.out.println("Depth-First Search---> DFS");
                System.out.println("Best-First Search----> BBFS");
                System.out.println("A* Search---> A");
                System.out.println();
                System.out.println();
                System.out.print("Select an algorithm using the 3/4 letter abbriviation that is provided :");
                String algo = read.next();

                if(algo.equalsIgnoreCase("BFS"))
                {
                    solutionPath = algorithms.BreadthFirstSearch(problem);
                }else if(algo.equalsIgnoreCase("BBFS"))
                {
                    solutionPath = algorithms.BestFirstSearch(problem, new BestFirstSearchComparator());
                }else if(algo.equalsIgnoreCase("DFS"))
                {
                    solutionPath = algorithms.DepthFirstSearch(problem);
                }else if(algo.equalsIgnoreCase("A"))
                {
                    solutionPath = algorithms.AStarSearch(problem, new AStarSearchComparator(problem));
                }else{
                    System.out.println("The selected algorithm does not exist");
                    return;
                }

                for(int i = 0; i < solutionPath.size(); i++)
                {
                    if(solutionPath.get(i) == null)
                    {
                        System.out.println(" -> Goal State was not found");
                        if(i <= numberOfStartStates-2)
                        {
                            System.out.println("Do you want to try another route? (y/n)");
                            Scanner scannn = new Scanner(System.in);
                            string answwer = scannn.next();
                            if(answwer.equalsIgnoreCase(y)) continue;
                            else break;
                        }
                        return;
                    }
                    System.out.print(solutionPath.get(i));
                    break;
                }
            }
        }else System.out.println("There aren't any start states... Program Terminated....");



    }
}
