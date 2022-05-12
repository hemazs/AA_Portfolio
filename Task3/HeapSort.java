public class HeapSort {
	public void sort(int arr[])
	{
		int n = arr.length;

		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);

			//extracting elements from the heap one by one
			for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// call max heapify on the reduced heap
			heapify(arr, i, 0);
		}
	}


	void heapify(int arr[], int n, int i)
	{
		int largest = i; 
		int left = 2 * i + 1; 
		int right = 2 * i + 2; 

		// if left child > root
		if (left < n && arr[left] > arr[largest])
			largest = left;

		// if right child is > largest so far
		if (right < n && arr[right] > arr[largest])
			largest = right;

		// if root is not the largest
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// heapify subtree recursivle
			heapify(arr, n, largest);
		}
	}

	//print tree
	static void printArray(int arr[])
	{
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	
	public static void main(String args[])
	{
		int arr[] = { 90, 87, 65, 11, 23, 45, 21};
		int n = arr.length;

		HeapSort ob = new HeapSort();
		ob.sort(arr);

		System.out.println("Sorted array is");
		printArray(arr);
	}
}
