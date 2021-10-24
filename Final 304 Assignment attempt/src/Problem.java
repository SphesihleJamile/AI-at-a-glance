import java.util.ArrayList;

public class Problem {

    Node initial_Node;
    ArrayList<Node> goalNodes;
    char[][] problem;

    public final int UP = 0;
    public final int STRAIGHT = 1;
    public final int DOWN = 2;

    public Problem(Node initial, ArrayList<Node> goalNodes, char[][] problem)
    {
        this.initial_Node = initial;
        this.goalNodes = goalNodes;
        this.problem = problem;
    }

    public char[][] getProblem(){ return problem; }

    public Node Result(Node state, int action)//we will return a node that will be the outcome of the provided action
    {
        Node child = null;
        switch (action)
        {
            case UP:
                child = new Node((state.getRow()-1), (state.getCol()+1), state, UP, 2);
                break;
            case STRAIGHT:
                child = new Node((state.getRow()), (state.getCol()+1), state, STRAIGHT, 1);
                break;
            case DOWN:
                child = new Node((state.getRow()+1), (state.getCol()+1), state, DOWN, 2);
                break;
        }
        return state;
    }

    public boolean GoalTest(Node state)
    {
        for(Node node : goalNodes)
        {
            if(node.equals(state)) return true;
        }
        return false;
    }

    public ArrayList<Integer> Actions(Node state)
    {
        ArrayList<Integer> possibleActions = new ArrayList<Integer>();//stores the possible actions that can be made from this node

        if(state.getRow() == 0)
        {
            //current possible actions are STRAIGHT and DOWN
            if(problem[state.getRow()][state.getCol() + 1] == 'D')
                possibleActions.add(STRAIGHT);
            if(problem[state.getRow() + 1][state.getCol() + 1] == 'D')
                possibleActions.add(DOWN);
        }
        else if(state.getRow() == problem.length-1)
        {
            //current possible actions are STRAIGHT and UP
            if(problem[state.getRow()-1][state.getCol() + 1] == 'D')
                possibleActions.add(UP);
            if(problem[state.getRow()][state.getCol() + 1] == 'D')
                possibleActions.add(STRAIGHT);
        }
        else if ((state.getRow() > 0 & state.getRow() < problem.length-1))
        {
            //All possible actions are available
            if(problem[state.getRow() - 1][state.getCol() + 1] == 'D')
                possibleActions.add(UP);
            if(problem[state.getRow()][state.getCol() + 1] == 'D')
                possibleActions.add(STRAIGHT);
            if(problem[state.getRow() + 1][state.getCol() + 1] == 'D')
                possibleActions.add(DOWN);
        }
        else possibleActions = null;//this is because there are no possible actions that can be taken
        //return the set of all possible actions that can be taken by the current node going forward
        return possibleActions;
    }
}
