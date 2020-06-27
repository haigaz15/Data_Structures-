// Main class to run bubble sort on a dubbly linked List
class Main{
    public static void main(String[] args){
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(9);
        list.addLast(9);
        list.addLast(3);
        list.addLast(-1);
        list.addLast(0);
        bubbleSort(list.getHeader());
    }
    // The function bubble sorts the doubly linked list
    // it swaps the nodes
    public static void bubbleSort(DoublyLinkedList.Node<Integer> node){
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        DoublyLinkedList.Node<Integer> currentNode;
        DoublyLinkedList.Node<Integer> currentNode2;
        boolean flag = true;
        while(flag){
            flag = false;
            Integer firstElement;
            Integer secondElement;
            currentNode = node.getNext();
            currentNode2 = node.getNext().getNext();
         while(true){ // loops till it reaches a null element.
                firstElement = currentNode.getElement();
                secondElement = currentNode2.getElement();
                 if(firstElement == null || secondElement == null) { // the condition ends the loop if either of them are null
                     break;
                 }
                if(firstElement > secondElement){
                DoublyLinkedList.Node<Integer> tempCurrentPrev = currentNode.getPrev(); // saving the reference of the current node's previous
                DoublyLinkedList.Node<Integer> tempCurrent2Next = currentNode2.getNext(); // saving the reference for the current node2's next nodecurrentNode.setNext(tempCurrent2Next);
                // ---- swapping code for the nodes ---- //
                    // Setting currentNode's references to match the currentNode2's references.
                currentNode.setNext(tempCurrent2Next);
                currentNode.setPrev(currentNode2);
                currentNode.getNext().setPrev(currentNode);
                  // Setting currentNode2's references to match the currentNode's references.
                currentNode2.setNext(currentNode);
                currentNode2.setPrev(tempCurrentPrev);
                currentNode2.getPrev().setNext(currentNode2);
                  // just swapping the two nodes after the references been set.
                currentNode = currentNode2;
                currentNode2 = currentNode;
                flag = true;
                // ---- end of the swapping ---- //
                }
                currentNode = currentNode.getNext(); // advancing the nodes.
                currentNode2 = currentNode2.getNext();
            }
        }
        // just a small block of code to print the values of the code after the bubble sort.
        DoublyLinkedList.Node<Integer> currentNode3 = node.getNext();
        while(true){
            if(currentNode3 == null || currentNode3.getNext() == null)
                break;
            System.out.print(" " + currentNode3.getElement());
            currentNode3 = currentNode3.getNext();
        }
    }
}