package cs2720.p1;

import java.util.Scanner;
import java.io.File;

import java.io.FileNotFoundException;

/**
 * Driver for SortedLinkedList Application.
 */
public class LinkedListDriver {

    public static void main(String[] args) {
        // Checks if file is invalid
        if (args.length != 1) {
            System.out.println("Unable to open file or file not present");
            return;
        } // if

        String file = args[0];
        SortedLinkedList list = new SortedLinkedList();
        // Parse and add file contents to new list
        try {
            Scanner input = new Scanner(new File(file));
            while (input.hasNextInt()) {
                int value = input.nextInt();
                list.insertItem(new ItemType(value));
            } // while
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } // try

        // print command list
        LinkedListDriver.printCommands();

        // Maps each command to an action
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n\nEnter a command: ");
            String command = scanner.next();

            if (command.equals("i")) {
                System.out.print("\nEnter a number to insert: ");
                int num = scanner.nextInt();
                System.out.println("\nOriginal list: ");
                list.printList();
                list.insertItem(new ItemType(num));
                System.out.println("\nNew list: ");
                list.printList();

            } else if (command.equals("d")) {
                System.out.print("\nEnter a number to delete: ");
                int num = scanner.nextInt();
                System.out.println("\nOriginal list: ");
                list.printList();
                list.deleteItem(new ItemType(num));
                System.out.println("\nNew list: ");
                list.printList();

            } else if (command.equals("s")) {
                System.out.print("\nEnter a number to search: ");
                int num = scanner.nextInt();
                // if an item is not found for any reason, return
                if (list.searchIndex(new ItemType(num)) == -1) {
                    System.out.println("");
                } else {
                    System.out.print("\nItem is found at index " +
                        list.searchIndex(new ItemType(num)));
                } // if

            } else if (command.equals("n")) {
                ItemType nextItem = list.getNextItem();
                if (nextItem != null) {
                    System.out.println("\n" + nextItem.getValue());
                } // if

            } else if (command.equals("r")) {
                list.resetList();
                System.out.println("\nIterator is reset");

            } else if (command.equals("a")) {
                System.out.println("\nOriginal list: ");
                list.printList();
                list.deleteAltNodes();
                System.out.println("\nModified list: ");
                list.printList();

            } else if (command.equals("m")) {
                SortedLinkedList otherList = new SortedLinkedList();
                System.out.print("\nEnter the length of the new list: ");
                int length = scanner.nextInt();
                System.out.print("\nEnter the numbers: ");
                scanner.nextLine();
                String[] numbers = scanner.nextLine().split(" ");
                for (int i = 0; i < length; i++) {
                    int num = Integer.parseInt(numbers[i]);
                    otherList.insertItem(new ItemType(num));
                } // for
                System.out.println("\nThe list 1: ");
                list.printList();
                System.out.println("\nThe list 2: ");
                otherList.printList();
                System.out.println("Merged list: ");
                list.mergeList(otherList).printList();

            } else if (command.equals("t")) {
                SortedLinkedList otherList = new SortedLinkedList();
                System.out.print("\nEnter the length of the new list: ");
                int length = scanner.nextInt();
                System.out.print("\nEnter the numbers: ");
                scanner.nextLine();
                String[] numbers = scanner.nextLine().split(" ");
                for (int i = 0; i < length; i++) {
                    int num = Integer.parseInt(numbers[i]);
                    otherList.insertItem(new ItemType(num));
                } // for
                System.out.println("\nThe list 1: ");
                list.printList();
                System.out.println("\nThe list 2: ");
                otherList.printList();
                System.out.print("Intersection of lists: ");
                list.intersect(otherList).printList();

            } else if (command.equals("p")) {
                System.out.println("\nThe list is: ");
                list.printList();

            } else if (command.equals("l")) {
                System.out.println("\nThe Length of the list is " + list.getLength());

            } else if (command.equals("q")) {
                System.out.println("\nExiting the program...");
                break;

            } else {
                System.out.println("\nInvalid command try again: ");

            } // if
        } // while

        // Close scanner class
        scanner.close();
    } // main

    /**
     * Helper methods that prints list of commands for user.
     */
    private static void printCommands() {
        System.out.println("Commands:");
        System.out.println("(i) - Insert value");
        System.out.println("(d) - Delete value");
        System.out.println("(s) - Search value");
        System.out.println("(n) - Print next iterator value");
        System.out.println("(r) - Reset iterator");
        System.out.println("(a) - Delete alternate nodes");
        System.out.println("(m) - Merge lists");
        System.out.println("(t) - Find intersection");
        System.out.println("(p) - Print list");
        System.out.println("(l) - Print length");
        System.out.println("(q) - Quit program");
    } // printCommands()

    /**
     * Tester method testing different operations.
     */
    private void tester() {
        SortedLinkedList list = new SortedLinkedList();
        list.printList();
        list.insertItem(new ItemType(1));
        list.printList();
        list.insertItem(new ItemType(6));
        list.printList();
        list.insertItem(new ItemType(2));
        list.printList();
        list.insertItem(new ItemType(4));
        list.printList();
        list.insertItem(new ItemType(3));
        list.printList();
        list.insertItem(new ItemType(6));
        list.printList();
        list.insertItem(new ItemType(10));
        list.printList();

        System.out.println(list.searchIndex(new ItemType(4)));

        list.deleteItem(new ItemType(1));
        list.printList();
        list.deleteItem(new ItemType(4));
        list.printList();
        list.deleteItem(new ItemType(4));
        list.printList();
        list.deleteItem(new ItemType(3));
        list.printList();

        list.searchIndex(new ItemType(4));
        System.out.println(list.searchIndex(new ItemType(6)));

        SortedLinkedList list2 = new SortedLinkedList();
        list2.insertItem(new ItemType(3));
        list2.insertItem(new ItemType(5));
        list2.insertItem(new ItemType(7));
        list2.insertItem(new ItemType(10));
        list2.insertItem(new ItemType(6));

        list.mergeList(list2);

        list.intersect(list2);

        list2.printList();
        list2.deleteAltNodes();
        list2.printList();
    } // tester

} // LinkedListDriver
