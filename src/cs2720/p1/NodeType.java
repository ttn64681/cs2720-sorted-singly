package cs2720.p1;

/**
 * A representation of a node in a linked list..
 */
public class NodeType {

    public ItemType info;
    public NodeType next;

    /**
     * Constructor representing a NodeType object.
     *
     * @param info the info a NodeType stores
     * @param next the next node of the current node
     */
    public NodeType(ItemType info, NodeType next) {
        this.info = info;
        this.next = next;
    } // NodeType

} // NodeType
