package doubleLinkedList;

import java.util.Stack;

import Stack.LinkedStack;
import Stack.SinglyLinkedList;

public class main {

	public static void main(String[] args) {
		
		DLList<Integer> list2 = new DLList<>() ;
		list2.add(5);
		list2.add(7);
		list2.add(1);
		list2.add(2);
		list2.add(4);

		list2.print();
		System.out.println("\nAfter Sort");
		list2.insertionSort();
		list2.print();
		
		list2.method2(list2);
		list2.OddEven();
		list2.print();
		
		
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>() ;
		list.addLast(-1);
		list.addLast(2);
		list.addLast(4);
		list.addLast(-5);
		list.addLast(20);
		list.addLast(-50);
		list.print();
		LinkedStack<Integer> stack = new LinkedStack<>() ;
		System.out.println("\nafter caling method");
		list.PostiveAndNigtive(list, stack);
		list.print();
		
		
				
	}

}
