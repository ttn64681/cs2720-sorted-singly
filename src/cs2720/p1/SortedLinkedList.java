package cs2720.p1;

import java.lang.NullPointerException;

/**
 * A representation of a node in a linked list..
 */
public class SortedLinkedList {

    private NodeType head;
    private NodeType currentPos;

    /**
     * Constructor representing a Sorted Linked List object.
     */
    public SortedLinkedList() {
        this.head = null;
        this.currentPos = null;
    } // SortedLinkedList

    /**
     * Return the length of the linked list.
     *
     * @return count the number of nodes in the linked list
     */
    public int getLength() {
        int count = 0;
        if (this.head == null) {
            return 0;
        } // if

        // NodeType for parsing through list starting from beginning
        NodeType current = this.head;
        while (current != null) {
            current = current.next;
            count++;
        } // while

        return count;
    } // getLength

    /**
     * Inserts an item into the linked list in the correct ascending order.
     *
     * @param item the item to insert
     */
    public void insertItem(ItemType item) {
        if (item == null) {
            System.out.println("\nItem cannot be null");
        } // if

        // NodeType containing given item
        NodeType newest = new NodeType(item, null);
        // NodeType for parsing through list starting from beginning
        NodeType current = this.head;
        // NodeType for previous location of current node
        NodeType prev = null;

        // Special case when list is empty or head item is greater than newest item
        if (this.head == null || this.head.info.compareTo(item) == 1) {
            newest.next = head;
            this.head = newest;
            return;
        } else if (this.head.info.compareTo(item) == 0) {
            // Checks if head item is equal to newest item
            System.out.println("\nSorry. You cannot insert the duplicate item");
            return;
        } // if

        // loop through list until current node's item value is >= the inputted item's value
        // and the current node is not null
        while (current != null && current.info.compareTo(item) < 0) {
            prev = current;
            current = current.next;
        } // while

        // current node will either be > or = to item. if equal, print duplicate error,
        // if greater, place item behind current node
        if (current != null && current.info.compareTo(item) == 0) {
            System.out.println("\nSorry. You cannot insert the duplicate item");
        } else {
            // when greater
            newest.next = current;
            if (prev != null) {
                // Checks if you are NOT adding at beginning of list, where prev is null
                prev.next = newest;
            } // if
        } // if
    } // insertItem

    /**
     * Deletes an item from the linked list.
     *
     * @param item the item to delete
     */
    public void deleteItem(ItemType item) {
        if (item == null) {
            System.out.println("\nItem cannot be null");
        } else if (this.head == null) {
            System.out.println("\nYou cannot delete from an empty list");
        } // if

        // NodeType for parsing through list starting from beginning
        NodeType current = this.head;
        // NodeType for previous location of current node
        NodeType prev = null;

        if (this.head.info.getValue() == item.getValue()) {
            this.head = head.next;
            return;
        } // if

        // loop through list until either the item is found, in which case you delete it
        // or until the item is not found
        for (int i = 0; i < getLength(); i++) {
            if (current != null && current.info.getValue() != item.getValue()) {
                prev = current;
                current = current.next;
            } else if (current != null && current.info.getValue() == item.getValue()) {
                // item is found and current node is not null
                prev.next = current.next;
                return;
            } // if
        } // for
        System.out.println("\nItem not found");
    } // deleteItem

    /**
     * Searches the linked list that contains an item equal to
     * the parameter item and return its index.
     *
     * @param item the item to be searched
     * @return i + 1 the index of the item (the first index of the list is 1)
     */
    public int searchIndex(ItemType item) {
        if (item == null) {
            System.out.println("\nItem cannot be null");
        } else if (this.head == null) {
            System.out.println("\nList is empty");
            return -1;
        } // if

        // NodeType for parsing through list starting from beginning
        NodeType current = this.head;

        // loop through list until either the item is found, in which case print its index
        // or until the item is not found
        for (int i = 0; i < getLength(); i++) {
            if (current != null && current.info.getValue() != item.getValue()) {
                current = current.next;
            } else if (current != null && current.info.getValue() == item.getValue()) {
                // item is found and current node is not null
                return i + 1;
            } // if
        } // for

        System.out.println("\nItem not found");
        return -1; // some arbitrary non-existent index number
    } // searchIndex

    /**
     * Points to and returns the next node's item.
     *
     * @return currentPos.info the next node's item
     */
    public ItemType getNextItem() {
        if (this.head == null) {
            System.out.println("\nList is empty");
            return null;
        } // if

        if (this.currentPos == null) {
            this.currentPos = this.head;
        } else if (this.currentPos.next == null) {
            System.out.println("\nThe end of the list has been reached");
            return null;
        } else {
            this.currentPos = currentPos.next;
        } // if
        return this.currentPos.info;
    } // getNextItem

    /**
     * Resets current pointer to beginning of list.
     */
    public void resetList() {
        this.currentPos = this.head;
    } // resetList

    /**
     * Takes an original list and inputted list and sorts them into a newly merged list,
     * which is returned.
     *
     * @param otherList the other provided list
     * @return merged the merged list
     */
    public SortedLinkedList mergeList(SortedLinkedList otherList) {
        if (otherList == null) {
            System.out.println("\nProvided list cannot be null");
        } // if

        // NodeType for parsing through list starting from beginning
        NodeType current = this.head;
        // duplicate current list to merged list
        SortedLinkedList merged = new SortedLinkedList();
        while (current != null) {
            merged.insertItem(current.info);
            current = current.next;
        } // while

        // now add otherList to merged
        current = otherList.head; // set current to head of other list
        while (current != null) {
            merged.insertItem(current.info);
            current = current.next;
        } // while

        return merged;
    } // mergeList

    /**
     * Deletes alternate nodes (2nd, 4th, 6th, and so on) from the list.
     */
    public void deleteAltNodes() {
        if (this.head == null) {
            System.out.println("\nList cannot be null");
        } // if

        // NodeType for parsing through list starting from beginning
        NodeType current = this.head;
        // while current and next node are non-null, set next node of current two nodes ahead,
        // thereby cutting off the middle node, and then setting current to the next node
        while (current != null && current.next != null) {
            current.next = current.next.next;
            current = current.next;
        } // while
    } // deleteAltNodes

    /**
     * Finds the intersecting (common) value(s) between original and inputted list and returns
     * it/them in a new list.
     *
     * @param otherList the other provided list
     * @return intersected the merged list
     */
    public SortedLinkedList intersect(SortedLinkedList otherList) {
        if (otherList == null) {
            System.out.println("\nProvided list cannot be null");
        } // if

        // NodeType for parsing through list starting from beginning
        NodeType current = this.head;
        SortedLinkedList intersected = new SortedLinkedList();

        while (current != null) {
            if (otherList.searchIndex(current.info) != -1) {
                intersected.insertItem(current.info);
            } // if
            current = current.next;
        } // while

        return intersected;
    } // intersect

    /**
     * Helper method to print contents of list on one line.
     */
    public void printList() {
        if (this.head == null) {
            System.out.print("List is empty");
            return;
        } // if
        NodeType current = head;
        String list = "";
        for (int i = 0; i < getLength(); i++) {
            list = list + current.info.getValue() + " ";
            current = current.next;
        } // for
        System.out.print(list);
    } // printList


} // SortedLinkedList
