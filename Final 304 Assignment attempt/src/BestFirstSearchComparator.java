import java.util.Comparator;

public class BestFirstSearchComparator implements Comparator<Node>{

    @Override
    public int compare(Node node, Node t1) {
        if(node.pathCost == t1.pathCost)
            return 0;
        else if(node.pathCost < t1.pathCost)
            return  -1;
        else
            return 1;
    }
}
