package model;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Model for a tree. Fill in the missing functionality and feel free to modify this class as you see
 * fit.
 */
public class Tree {
    private static final int MAX_DEPTH = 5;     // maximum depth of the tree to process
    private final Node root;                    // root of the tree
    private static Map<String, String[]> relationships;

    private Tree(Node root) { this.root = root; }

    /**
     * Constructs a tree. Consider passing a dictionary of nodes for easy lookup along with the
     * relationships. A good place to start is finding out which product type will be the root node
     * and writing a separate function for the actual building.
     *
     * @return Tree
     */
    public static Tree from(Map<String, String[]> relations) {
        relationships = relations;
        Tree tree = new Tree(getRootFromMap(relations));

        buildTree(tree.getRoot(), relations.get(tree.getRoot().getProductType().getId()),
                tree.root);

        return tree;
    }

    /**
     * Method to build a three called recursively
     * @param node to add more children nodes if any
     * @param children to add
     * @param root to determine the depth of the three
     */
    private static void buildTree(Node node, String[] children, Node root){
        if(children != null && getCurrentDepth(root) < MAX_DEPTH) {
            for (String child : children) {
                Node childNode = buildNode(child);
                node.addChild(childNode);
                buildTree(childNode, relationships.get(child), root);
            }
        }
    }

    /**
     * Method to determine the root of the three
     * @param map
     * @return Node root
     */
    private static Node getRootFromMap(Map<String, String[]> map){
        Map.Entry<String,String[]> entry = map.entrySet().iterator().next();
        return buildNode(entry.getKey());
    }

    /**
     * Method to create the nodes with only id to add to the three as children and
     * @param id of the relations
     * @return Node created
     */
    private static Node buildNode(String id){
        ProductType productType = new ProductType(id, "", "");
        Node node = new Node(productType);
        return node;
    }

    /**
     * Method to determine the current depth of the three
     * @param root of the three
     * @return integer level of three
     */
    private static int getCurrentDepth(Node root){
        int maxChildLevel = 0;
        for (Node child : root.getChildren()) {
            maxChildLevel = Math.max(maxChildLevel, getCurrentDepth(child));
        }
        return maxChildLevel + 1;
    }

    /**
     * Returns the Depth-First traversal of the tree
     *
     * @return String of the nodes order in DFT
     */
    public String dft(Node node) {
        String response = null;
        if(node != null){
            response = node.getProductType().getId() + " ";
        }
        if(node.getChildren() != null){
            for(Node child : node.getChildren()){
                response += dft(child);
            }
        }

        return response;
    }

    /**
     * Returns the Breadth-first traversal of the tree
     *
     * @return String of the nodes in BFT
     */
    public String bft(Node node) {
        String response = "";
        Queue<Node> queue = new LinkedList<Node>();

        if(node == null){
            return null;
        }

        queue.add(node);

        while (!queue.isEmpty()){
            Node currentNode = queue.remove();
            response += currentNode.getProductType().getId() + " ";
            if(currentNode.getChildren() != null) {
                for (Node child : currentNode.getChildren()){
                    queue.add(child);
                }
            }

        }

        return response;
    }

    /**
     * Returne the root of the three
     * @return Node root
     */
    public Node getRoot() { return root; }
}
