//Chetan Goyal
public class IDLListTest {
    public static void main(String[] args) {
        
        // create an empty indexed double linked list, data type is Integer
        IDLList<Integer> myList = new IDLList();

        // add element 10 at the head, 10 becomes the first element
        // result: 10
        myList.add(10);
        System.out.println("The first element of the list is: " + myList.toString() + "\n");
        myList.add(3);
        myList.add(2);
        myList.add(11);

        // add element 5 at the head, 5 becomes the first element
        // result: 5 11 2 3 10
        myList.add(0,5);
        System.out.println("After add 5 at the head, the list becomes " + myList.toString() + "\n");

        // add element 8 at index 1
        // result: 5 8 11 2 3 10
        myList.add(1,8);
        System.out.println("After add 8 at index 1, the list becomes " + myList.toString() + "\n");


        // append element 30 at the end of the list
        // result: 5 8 11 2 3 10 30
        myList.append(30);
        System.out.println("After append, the list is : " + myList.toString() + "\n");

        // returns the object at position index 1 from the head
        // result: 8
        System.out.println("Current list is: " + myList.toString());
        System.out.println("Get element of position 1 in list : " + myList.get(1) + "\n");

        // return the object at the head
        // result: 5
        System.out.println("Current list is: " + myList.toString());
        System.out.println("Get head object in list: " + myList.getHead() + "\n");

        // return the object at the tail
        // result: 30
        System.out.println("Current list is: " + myList.toString());
        System.out.println("Get tail object in list: " + myList.getLast() + "\n");

        // return the list size
        System.out.println("The current list size is: " + myList.size() + "\n");

        // remove and return element at the head
        System.out.println("The list will now become: " + myList.toString());
        System.out.println("The removed head element is: " + myList.remove());
        System.out.println("The current list after remove method becomes: " + myList.toString() + "\n");

        // remove and return the element at the tail
        System.out.println("The list will now become: " + myList.toString());
        System.out.println("The removed tail element is: " + myList.removeLast());
        System.out.println("The current list after remove method becomes: " + myList.toString() + "\n");

        // add two elements 15
        // current list: 8 11 15 2 3 10 15 
        myList.append(15);
        myList.add(2, 15);
        System.out.println("The list will now become: " + myList.toString());

        // remove the first occurrence of 15
        // result: 8 11 2 3 10 15 
        myList.remove(15);
        System.out.println("After removed the first occurrence of 15, now the list is: "
                + myList.toString());

    }
}