#!/bin/bash

javac -d bin src/cs2720/p1/ItemType.java
javac -d bin -cp bin src/cs2720/p1/NodeType.java
javac -d bin -cp bin src/cs2720/p1/SortedLinkedList.java
javac -d bin -cp bin src/cs2720/p1/LinkedListDriver.java

java -cp bin cs2720.p1.LinkedListDriver resources/input.txt
