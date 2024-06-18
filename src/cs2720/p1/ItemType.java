package cs2720.p1;

import java.lang.NullPointerException;

/**
 * A representation of an integer item.
 */
public class ItemType {

    private int value;

    /**
     * Constructor to intiialize ItemType object.
     *
     * @param num the number to set the value to
     */
    public ItemType(int num) {
        this.value = num;
    } // ItemType

    /**
     * Compares the value of an item with the current object's value, returning
     * -1 if value of the current object is less than value in item, 0 if equal
     * and 1 if greater.
     *
     * @param item the item to compare its value with the current object's value
     * @return num the number representing the comparison (-1, 0, 1)
     */
    public int compareTo(ItemType item) {
        // checks if item is null
        if (item == null) {
            throw new NullPointerException("Current object's value cannot be null");
        } // if

        if (this.getValue() < item.getValue()) {
            return -1;
        } else if (this.getValue() > item.getValue()) {
            return 1;
        } else {
            return 0;
        } // if
    } // compareTo

    /**
     * Returns the value of instance variable.
     * @return val the value of the the instance variable
     */
    public int getValue() {
        return this.value;
    } // getValue

} // ItemType
