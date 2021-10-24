import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Node {

    private int row, col; //represents the current state of the node
    Node parent;
    int action;
    int pathCost = 0;

    public Node(int row, int col, Node parent, int action, int pathCost)
    {
        this.row = row;
        this.col = col;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
    }

    public Node(int row, int col)
    {
        this.row = row; this.col = col;
        this.parent = null;
        this.action = -1;
        this.pathCost = 0;
    }

    public int getRow() {return row;}
    public int getCol() {return  col;}

    @Override
    public String toString() //A string representation of a node. This will be used when printing the solution path
    {
        return "("+row+","+col+")";
    }

    public boolean equals(Node n)
    {
        if(n == null)
            return false;
        else return (this.getRow() == n.getRow()) & (this.getCol() == n.getCol());
    }

    public Node Child(int action)
    {
        Node childNode = null;
        switch (action)
        {
            case 0://action : UP
                childNode = new Node((this.getRow() - 1), (this.getCol() + 1), this, 0, 2);
                break;
            case  1://action : STRAIGHT
                childNode = new Node(this.getRow(), (this.getCol() + 1), this, 1, 1);
                break;
            case  2://action : up
                childNode = new Node((this.getRow() + 1), (this.getCol() + 1), this, 2, 2);
        }
        return  childNode;
    }

    public ArrayList<Node> Expand(Problem problem)
    {
        ArrayList<Node> childNodes = new ArrayList<>();
        if(problem.Actions(this) != null)
        {
            for (int action : problem.Actions(this)) { //Adds expanded nodes to childNodes
                childNodes.add(this.Child(action));
            }
        }
        else return null;
        return childNodes; //returns the childNodes as an array of these expanded children
    }
}
