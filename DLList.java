package doubleLinkedList;


public class DLList<T> {

	private Node<T> head;
	private Node<T> last;

	private int size;

	public DLList() {

		head = null;
		last = null;
		size = 0;
	}
	//Remove all elements

	public void clear() {
		head = last = null;
		size = 0;
	}
	// access methods

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(T data) {
		Node<T> temp = new Node<>(data, null, null);
		if (head == null) {
			head = last = temp;
		} else {
			last.next = temp;
			temp.prev = last;
			last = temp;
		}
		size++;
	}

	/**
	 * add "element" at specific position
	 */
	public void add(T data, int index) {
		if (index > size || index < 0) {
			System.out.println("Index out of bounds");
			return;
		}

		Node<T> temp = new Node<>(data, null, null);
		if (index == 0) { 	// Add at the front or empty list
			if (head != null) {
				temp.next = head;
				head.prev = temp;
				head = temp;
			} else // If list is empty 
			{
				last = head = temp;
			}
		} else { 		// Add at the middle 
			Node<T> curr = head;
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}

			temp.next = curr.next;
			temp.prev = curr;
			if (curr.next != null) {
				curr.next.prev = temp;
			}
			curr.next = temp;

			if (last == curr) // Add at the end 
			{
				last = last.next;
			}
		}
		size++;
	}
	public void addLast(T e) {
		addBetween(e, last.getPrev(), last);
	}

	private void addBetween(T e , Node<T> preecessor, Node<T> successor) {

		Node<T> newest = new  Node<T>(e, preecessor , successor) ;
		preecessor.setNext(newest);
		successor.setPrev(newest);
		size++ ;
	}


	/**
	 * Remove and return the removed data
	 */
	public T removeLast() {
		if(isEmpty())
			return null ;
		return remove(last.getPrev()) ;
	}
	public T remove(Node<T> node) {
		Node<T> preecessor = node.getPrev() ;
		Node<T> successor = node.getNext();
		preecessor.setNext(successor);
		successor.setPrev(preecessor);
		size -- ;
		return node.getData() ;



		//		node.getNext().setPrev(node.getPrev());
		//		node.getPrev().setNext(node.getNext());
		//		size-- ;
		//		return node.getElement() ;

	}
	public T remove(int index) {
		if (index >= size || index < 0) {
			System.out.println("Index out of bounds");
			return null;
		}
		T data;
		if (index == 0) { // remove front element 
			data = head.data;
			head = head.next;
			head.prev = null;
			if (head == null) // become empty list
			{
				last = null;
			}
		} else {
			Node<T> curr = head;
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}

			data = curr.next.data;
			if (last == curr.next) // remove last element 
			{
				last = curr;
			}
			curr.next = curr.next.next; // Remove from list
			if (curr.next != null) {
				curr.next.prev = curr;
			}
		}
		size--;// Decrement count
		return data; // Return value
	}

	public T getValue(int index) {
		Node<T> curr = head;
		if (index < 0 || index >= size) {
			System.out.println("Index out of range!");
			return null;
		} else {

			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}

		}
		return curr.data;
	}

	public void setValue(T data, int index) {
		Node<T> curr = head;
		if (index < 0 || index >= size) {
			System.out.println("Index out of range!");
		} else {

			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
			curr.data = data;
		}
	}
	public void print() {
		if(isEmpty())
			return ;

		Node curr = head ;

		while (curr != null) {
			System.out.print(curr.data+" ");
			curr = curr.getNext();
		}
	}


	public boolean testPivot(Node pivot) {

		if(isEmpty() || size <= 2)
			return true ;

		Node curr = pivot.getPrev() ;

		while (curr != null) {
			if((int)curr.data > (int)pivot.data)
				return false ;
			curr=curr.getPrev() ;
		}
		curr = pivot.getNext() ;
		while (curr != null) {
			if((int)curr.data < (int)pivot.data)
				return false ;
			curr=curr.getNext() ;
		}
		return true ;
	}
	public void method1(DLList<Integer> list) {
		//to check if the list is empty 
		if(isEmpty())
			return ;

		// temp queue
		LinkedQueue<Integer> que = new LinkedQueue<>() ;


		Node curr = head ;

		//store data into queue
		while (curr != null) {
			for (int i = 0; i < (int)curr.getData(); i++) {
				que.enqueue((int)curr.data);
			}
			curr = curr.getNext() ;
		}
		list.clear();
		//return data into origin list
		while (!que.isEmpty()) {
			list.add(que.dequeue());
		}    	
	}

	public void method2(DLList<Integer> list) {
		//to check if the list is empty 
		if(list.isEmpty())
			return ;

		Node curr = head ;
		int x ;
		while (!list.isEmpty()) {
			for (int i = 0; i < list.size; i++) {
				x = list.remove((int)curr.getData()) ;
				for (int j = 0; j < x; j++) {
					list.add(x);
				}

			}
			curr = curr.getNext();
		}    	
	}
	public void OddEven() {

		if(isEmpty()) {
			System.out.println("the list is empty");
			return ;
		}
		int n = size ;
		Node curr = head ;

		for (int i = 0; i < n; i++) {
			T x = removeLast() ;
			if((int)curr.data % 2 == 0) {
				addLast(x);
			}else 
				curr = curr.getNext() ;
		}
		curr = curr.getNext() ;
	}

	public void move(int start, int end, int pos) {

		if(isEmpty())
			return ;
		if(pos > size || pos < 0) {
			return ;
		}
		Node first = head ;
		Node Last = head ;
		Node postion = head ;

		for (int i = 0; i <start; i++) {
			first = first.getNext() ;
		}
		for (int i = 0; i < end; i++) {
			Last = Last.getNext() ;
		}
		for (int i = 0; i < pos; i++) {
			postion = postion.getNext() ;
		}

		first.getPrev().setPrev(Last.getNext());
		Last.getNext().setPrev(first.getPrev());
		Node perpos = postion.getPrev() ;
		perpos.setNext(first);
		first.setPrev(perpos);


	}


	public void swap2(Node<T> first, Node<T> second) {

		Node<T> firstNext = first.getNext ();
		Node<T> firstPrev = first.getPrev();


		first.setNext (second.getNext ());
		first.getNext ().setPrev(first);

		second.setNext(firstNext);
		second.getNext () .setPrev (second);

		first.setPrev(second. getPrev());


		second.setPrev(firstPrev);


	}
	public void swapWithPrevios(int index) {

		if(isEmpty()) return ;

		if(index >= size && index < 0) {
			System.out.println("index of of bound");
			return ;
		}
		Node curr = head ;
		for (int i = 0; i < index; i++) {
			curr = curr.getNext() ;
		}
		int temp = (int)curr.getData() ;
		curr.setData(curr.getPrev().getData());
		curr.getPrev().setData(temp);
	}
	public void selectionSort() {
		
		Node pass = head ;
		
		for (int i = 0; i < size; i++) {
			Node min = pass ;
			Node curr = min.getNext() ;
			for (int j = i+1; j < size ; j++) {
				if((int)min.getData() > (int)curr.getData())
					min = min.getNext() ;
				curr = curr.getNext() ;
			}
			int temp = (int)min.getData() ;
			min.setData(pass.getData());
			pass.setData(temp);
			pass = pass.getNext() ;
		}
	}
	public void insertionSort() {
		
		Node curr = head;
		int temp ;
		for (int i = 0; i < size; i++) {
			curr = curr.getNext() ;
			 temp = (int)curr.getData() ;
			Node j = curr ;
			
			while (j != head && (int)j.getPrev().getData() > temp) {
				j.setData(j.getPrev().getData());
				j = j.getPrev() ;
			}
			j.setData(temp);
		}
	}

}
