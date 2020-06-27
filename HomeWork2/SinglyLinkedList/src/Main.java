// this is just a main class to test the functionality of the
//printEverySecondElement function that is in the SinglyLinked List
class Main {
    public static void main (String[] args){
        SinglyLinkedList<Character> list = new SinglyLinkedList<>();
        list.addLast('s');
        list.addLast('l');
        list.addLast('d');
        list.addLast('q');
        list.addLast('f');
        list.addLast('m');
        list.printEverySecondElement(list.returnHead());

    }
}
