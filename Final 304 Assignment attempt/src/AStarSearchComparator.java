import java.util.ArrayList;
import java.util.Comparator;

public class AStarSearchComparator implements Comparator<Node>{

    char[][] maze;
    ArrayList<Node> goalStates;

    public AStarSearchComparator(Problem problem)
    {
        this.maze = problem.problem;
        this.goalStates = problem.goalNodes;
    }

    public Node FindClosestGoalNode(Node currentNode)
    {
        if(goalStates.size() < 0)
            return null;
        else if(goalStates.size() == 1)
            return goalStates.get(0);
        else
        {
            Node temp = goalStates.get(0);
            //calculate the x value of temp, and it's distance from the current node
            //if another node has a smaller distance, that that node is closer
            for(int x = 1; x < goalStates.size(); x++)
            {
                if((Math.abs(currentNode.getRow()- temp.getRow()) + Math.abs(currentNode.getCol()- temp.getCol())) >
                        (Math.abs(currentNode.getRow()-goalStates.get(x).getRow()) + Math.abs(currentNode.getCol() - goalStates.get(x).getCol())))
                {// f = |x1 - x2| + |y1 - y2| -> This number is always positive. The lower the value of f, the closer the goal node it.
                    //Now I have to add the condition that the goal node must have at least 1 Dry spot behind it. We will do this by a reverse action
                    // Reverse actions :
                    //      Diagonally Down -> (x+1),(y-1)
                    //      Back            -> x, y-1
                    //      Diagonally Up   -> (x-1), (y-1)
                    //The ReverseAction method will return true if there is at 1 Dry spot before the goal state, else false
                    //It will use the rows to calculate. If the dry spot it below and I'm coming from the top, it won't consider it
                    temp = goalStates.get(x);
                }

            }
            return temp;
        }
    }

    public int Heuristic(Node node)
    {
        int totalPathCost = node.pathCost + FindClosestGoalNode(node).pathCost;
        int heuristicValue = totalPathCost + (Math.abs(node.getRow() - FindClosestGoalNode(node).getRow()) + Math.abs(node.getCol() - FindClosestGoalNode(node).getCol()));
        return heuristicValue;
    }

    @Override
    public int compare(Node node, Node t1) {
        if(Heuristic(node) < Heuristic(t1))
            return -1;
        else if(Heuristic(node) > Heuristic(t1))
            return 1;
        else
            return 0;
    }
}
