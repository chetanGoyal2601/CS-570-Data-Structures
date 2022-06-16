//Chetan Goyal
import java.util.*;
public class IDLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    private class Node<E> {
        E data;
        private Node<E> next;
        private Node<E> prev;

        //Constructor for Node class that creates a node holding element
        Node(E elem) {
            this.data = elem;
            this.next = null;
            this.prev = null;
        }

        //Constructor for Node class that creates a node holding element, with next as next and prev as prev
        Node(E elem, Node<E> prev, Node<E> next) {
            this.data = elem;
            this.next = next;
            this.prev = prev;
        }

    }
    
    //Constructor for IDDList to create an empty double linked list
    public IDLList() {
        head = null;
        tail = null;
        size = 0;
        indices = new ArrayList<>();
    }

    //Method to add element at position index
    public boolean add(int index, E elem) {
        //throwing exception if index is invalid for the DLL
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Incorrect Index!");
        }

        if (index == 0) {
            this.add(elem);
        }

        else if (index == size) {
            this.append(elem);
        }

        else {
            Node<E> prev_node = indices.get(index - 1);
            Node<E> curr_node = indices.get(index);
            Node<E> new_node = new Node<E>(elem, curr_node.prev, curr_node);
            curr_node.prev = new_node;
            prev_node.next = new_node;
            indices.add(index, new_node);
            size++;
        }

        return true;
    }

    //Method to add an element at the head
    public boolean add(E elem) {
        if (size == 0) {
            Node<E> new_node = new Node<E>(elem);
            head = new_node;
            tail = head;
            indices.add(head);
        }

        else {
            Node<E> new_node = new Node<E>(elem, null, head);
            head.prev = new_node;
            head = new_node;
            indices.add(0, new_node);
        }

        size++;
        return true;
    }

    //Method to append an element at the end of linked list
    public boolean append(E elem) {
        if (size == 0) {
            Node<E> new_node = new Node<E>(elem);
            head = new_node;
            tail = head;
            indices.add(head);
        }

        else {
            Node<E> new_node = new Node<E>(elem, tail, null);
            tail.next = new_node;
            tail = new_node;
            indices.add(new_node);
        }

        size++;
        return true;
    }

    //Method to get the data of a node using index
    public E get(int index) {
        //throwing exception if index is invalid for the DLL
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Incorrect Index!");
        }

        return indices.get(index).data;
    }
    
    //Method to get the data at the head node
    public E getHead() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("No data in the the doubly linked list is present!");
        }
        return head.data;
    }

    //Method to get the data at the tail node
    public E getLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("No data in the the doubly linked list is present!");
        }
        return tail.data;
    }

    //Method to get the size of the DLL
    public int size() {
        return size;
    }

    //Method to remove the head node
    public E remove() {
        //throwing exception if no node is there in the DLL to remove
        if (size == 0) {
            throw new IndexOutOfBoundsException("No node present in the DLL!");
        }

        Node<E> node_to_remove = head;
        head = node_to_remove.next;

        if (size == 1) {
            tail = head;
        }

        else {
            head.prev = null;
        }

        indices.remove(0);
        size--;
        return node_to_remove.data;
    }

    //Method to remove the tail node
    public E removeLast() {
        //throwing exception if no node is present the DLL
        if (size == 0) {
            throw new IndexOutOfBoundsException("No node present in the DLL!");
        }

        Node<E> node_to_remove = tail;
        tail = node_to_remove.prev;

        if (size == 1) {
            head = tail;
        }

        else {
            tail.next = null;
        }

        indices.remove(size-1);
        size--;
        return node_to_remove.data;
    }

    //Method to remove a node at a given index
    public E removeAt(int index) {
        //throwing exception if index is invalid for the DLL
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Incorrect Index entered!");
        }

        if (index == 0) {
            return this.remove();
        }

        else if (index == size - 1) {
            return this.removeLast();
        }

        else {
            Node<E> node_to_remove = indices.get(index);
            node_to_remove.prev.next = node_to_remove.next;
            node_to_remove.next.prev = node_to_remove.prev;
            indices.remove(index);
            size--;
            return node_to_remove.data;
        }
    }

    //Method to remove the first occurence of an element, return false if no such element is present
    public boolean remove(E elem) {
        for (int i = 0; i < size; i++) {
            if (elem == indices.get(i).data) {
                if (i == 0) {
                    this.remove();
                }

                else if (i == size - 1) {
                    this.removeLast();
                }

                else {
                    this.removeAt(i);
                }

                return true;
            }
        }

        return false;
    }
    
    //Method to convert and return the linked list as a string
    public String toString() {
        String linkedData = "";
        for (int i = 0; i < size; i++) {
            linkedData += indices.get(i).data;
            linkedData += " ";
        }
        //A linked list like this: 1 -> 2 -> 3 will returned as "1 2 3"
        return linkedData;
    }    

}