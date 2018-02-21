package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for a tree node. Feel free to modify this class as you see fit.
 */
public class Node {
    private final ProductType productType;
    private final List<Node> children = new ArrayList<>();

    /**
     * Constructor to crate Node
     * @param productType to create the Node
     */
    public Node(ProductType productType) { this.productType = productType; }

    /**
     * Method to add a child Node of the current Node
     * @param node to add
     */
    public void addChild(Node node) { children.add(node); }

    /**
     * Getter of the list of children of a Node
     * @return List of children Nodes
     */
    public List<Node> getChildren() { return children; }

    /**
     * Getter of the Produc type
     * @return ProductType product type
     */
    public ProductType getProductType() { return productType; }
}
