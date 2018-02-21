package model;

/**
 * Model for a Product Type. Feel free to modify this class as you see fit.
 */
public class ProductType {
    public final String id;
    public final String name;
    public final String label;

    /**
     * Constructor to create ProductType
     * @param id
     * @param name
     * @param label
     */
    public ProductType(String id, String name, String label) {
        this.id = id;
        this.name = name;
        this.label = label;
    }

    /**
     * Getter of the id
     * @return String id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter of the name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of the label
     * @return String label
     */
    public String getLabel() {
        return label;
    }
}
