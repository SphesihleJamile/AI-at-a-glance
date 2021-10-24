import java.util.*;

public class Algorithms {
    //Completed, Works Perfectly
    public Vector<Node> BestFirstSearch(Problem problem, BestFirstSearchComparator comparator) //comparator represents our function that we apply to each node to find the best node
    {
        Node startNode = problem.initial_Node; //The initial node from our problem
        Queue<Node> frontier = new PriorityQueue<>(comparator); //A Priority Queue ordered by F, F is the comparator that sorts nodes by their pathCost
        //Since we have created our frontier, we now have to add our child nodes from the initial node to our frontier

        for(Node node : startNode.Expand(problem))
        {
            frontier.add(node);
        }
        //Our frontier is now no longer empty
        Vector<Node> reached = new Vector<>();
        if(problem.GoalTest(startNode))//if the start node is the goal node
        {
            reached.add(startNode);
            return reached;
        }
        reached.add(startNode);//We now add the start node to reached since it has been expanded
        while (!frontier.isEmpty()) //while our frontier is not empty. If it's empty and we still haven't found the goal node, then it means that we have been unsuccessful
        {
            Node currentNode = frontier.remove();
            if(problem.GoalTest(currentNode))  //return the current node if it a goal node, else continue with the algorithm
            {
                reached.add(currentNode);
                return reached;
            }
            for(Node child : currentNode.Expand(problem)) // expand the current node to produce it's children, then add these children to the frontier, if they're not the goal node
            {
                Node s = child;
                frontier.add(s);
                //if(!reached.contains(s))
                //{
                //if(!frontier.contains(s))//We're avoiding redundancy
                //        frontier.add(s);
                //}
            }
            reached.add(currentNode);
        }
        //If we get to this point, then it means that we did not find the goal node, thus, the algorithm has failed.
        //We will return null to indicate a failure
        reached.add(null);
        return reached;
    }
    //Completed, Works Perfectly
    public Vector<Node> BreadthFirstSearch(Problem problem)
    {
        Node startNode = problem.initial_Node;
        Queue<Node> frontier = new PriorityQueue<>(new BasicComparator());
        Vector<Node> reached = new Vector<>();//This is where we are going to store our solution path for our algorithm
        if (problem.GoalTest(startNode))
        {//if the start node is the goal node, then return a vector that only includes the start node
            reached.add(startNode);
            return reached;
        }
        //Since we have created our frontier, we now have to add our child nodes from the initial node to our frontier
        for(Node node : startNode.Expand(problem))
        {
            frontier.add(node);
        }
        reached.add(startNode);//we are adding the start node to reached because we have already expanded it
        while (!frontier.isEmpty())//while the frontier is not empty, then perform the following actions
        {
            Node n = frontier.remove();
            for(Node child : n.Expand(problem))
            {
                if(problem.GoalTest(child))
                {
                    reached.add(child);
                    return reached;
                }
                if(!reached.contains(child))//We're avoiding redundancy
                {
                    frontier.add(child);
                }
            }
            reached.add(n);
        }
        reached.add(null);
        return reached;
    }
    //Completed, Works Perfectly
    public Vector<Node> DepthFirstSearch(Problem problem)
    {
        Node startNode = problem.initial_Node;
        Stack<Node> frontier = new Stack<>();
        Vector<Node> reached = new Vector<Node>();
        if(problem.GoalTest(startNode))//if the start node is the goal node, add it to reached, and then return reached
        {
            reached.add(startNode);
            return reached;
        }
        //now we will be adding all the children of start node to the frontier
        for(Node child : startNode.Expand(problem))
        {
            frontier.push(child);//add each child to the end of the stack
        }
        frontier.push(startNode);
        //now we will add the start node to reached since it has been expanded
        while (!frontier.isEmpty())//if the frontier is not empty, perform the following statements
        {
            Node node = frontier.pop();//Remove the last node from the stack and add it to the variable node
            if(problem.GoalTest(node))//if this current node is the goal node, then add it to reached and return reached
            {
                reached.add(node);//add the node to reached since it is the goal node, and then return reached
                return reached;
            }
            //since it is not the goal node, expand the node and add its children to the frontier
            for(Node child : node.Expand(problem))
            {
                if(!frontier.contains(child) && node.Expand(problem).size()>0)//We're avoiding redundancy
                    frontier.push(child);//Add each child node to the end of the stack
            }
            //we then add the node to reached since it has been expanded
            reached.add(node); //add node to reached since it has been expanded
        }
        //If we get to this point it means that there is no goal node, this add null to reached and return reached
        reached.add(null);
        return reached;
    }
    //Completed,...
    public  Vector<Node> AStarSearch(Problem problem, AStarSearchComparator f)
    {
        Node startNode = problem.initial_Node;
        Queue<Node> frontier = new PriorityQueue<>(f);
        Vector<Node> reached = new Vector<>();//This is where we are going to store our solution path for our algorithm
        if (problem.GoalTest(startNode))
        {//if the start node is the goal node, then return a vector that only includes the start node
            reached.add(startNode);
            return reached;
        }
        //Since we have created our frontier, we now have to add our child nodes from the initial node to our frontier
        for(Node node : startNode.Expand(problem))
        {
            frontier.add(node);
        }
        reached.add(startNode);//we are adding the start node to reached because we have already expanded it
        while (!frontier.isEmpty())//while the frontier is not empty, then perform the following actions
        {
            Node n = frontier.remove();
            for(Node child : n.Expand(problem))
            {
                if(problem.GoalTest(child))
                {
                    reached.add(child);
                    return reached;
                }
                if(!reached.contains(child))//We're avoiding redundancy
                {
                    frontier.add(child);
                }
            }
            reached.add(n);
        }
        reached.add(null);
        return reached;
    }
}
