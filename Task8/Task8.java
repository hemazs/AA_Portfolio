
import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class MergeSort{
	
	private static final int MAX_THREADS = 4;
	
    //New Thread class with constructors 
    private static class SortThreads extends Thread{
		SortThreads(Integer[] array, int begin, int end){
			super(()->{
				MergeSort.mergeSort(array, begin, end);
			});
			this.start();
		}
	}
	
	// Perform Threaded merge sort
	public static void threadedSort(Integer[] array){
		long time = System.currentTimeMillis();
		final int length = array.length;
        // The workload per thread is the total number of elements divided by the core count (4)
		// if the number of elements divides equally into the core count then distrubute the elements accoordingly
		// if a remainder is present then assume the core count is (corecount - 1) and distrubute it among them and the remaineder
        // will be assigned to the extra core
		boolean exact = length%MAX_THREADS == 0;
		int maxlim = exact? length/MAX_THREADS: length/(MAX_THREADS-1);
        // if only one thread is needed for the work then assign all the work to 1 thread
		maxlim = maxlim < MAX_THREADS? MAX_THREADS : maxlim;
		final ArrayList<SortThreads> threads = new ArrayList<>();
        // create threads and assign the indexes they will be working on
		for(int i=0; i < length; i+=maxlim){
			int beg = i;
			int remain = (length)-i;
			int end = remain < maxlim? i+(remain-1): i+(maxlim-1);
			final SortThreads t = new SortThreads(array, beg, end);
			threads.add(t);
		}
		for(Thread t: threads){
			try{
				// We need all chunks to be sorted to continue so we wait for all threads to finish 
				t.join();
			} catch(InterruptedException ignored){}
		}
		
        // Merge takes 2 parts and then merges them into on and continues to do so with the result into the next part
        // until the end
		
		for(int i=0; i < length; i+=maxlim){
			int mid = i == 0? 0 : i-1;
			int remain = (length)-i;
			int end = remain < maxlim? i+(remain-1): i+(maxlim-1);
			merge(array, 0, mid, end);
		}
		time = System.currentTimeMillis() - time;
		System.out.println("Time spent for multi-threaded recursive merge sort: "+ (float) time / 1000 + "seconds");
	}

    //Mergesort recursive function
    public static void mergeSort(Integer[] array, int begin, int end){
		if (begin<end){
			int mid = (begin+end)/2;
			mergeSort(array, begin, mid);
			mergeSort(array, mid+1, end);
			merge(array, begin, mid, end);
		}
	}
	
	// 2-way merge
	public static void merge(Integer[] array, int begin, int mid, int end){
		Integer[] temp = new Integer[(end-begin)+1];
		
		int i = begin, j = mid+1;
		int k = 0;

        // add elements from whichever half is lower until no more comparisions can be made
		while(i<=mid && j<=end){
			if (array[i] <= array[j]){
				temp[k] = array[i];
				i+=1;
			}else{
				temp[k] = array[j];
				j+=1;
			}
			k+=1;
		}

		// add remaining elements from first half
		while(i<=mid){
			temp[k] = array[i];
			i+=1; k+=1;
		}
		
		// add remaining elements from second half
		while(j<=end){
			temp[k] = array[j];
			j+=1; k+=1;
		}

		for(i=begin, k=0; i<=end; i++,k++){
			array[i] = temp[k];
		}
	}
}

class RandomInput{

    


	
	private static Random random = new Random();
	private static final int size = random.nextInt(100);
	private static final Integer list[] = new Integer[size];
	
	static {
	for(int i=0; i<size; i++){
		
		list[i] = random.nextInt(size+(size-1))-(size-1);
	}
	}
	
	public static void main(String[] args){

        // int n;
        // Scanner sc=new Scanner(System.in);  
        // System.out.print("Enter the number of elements you want to store: ");  
        // //reading the number of elements from the that we want to enter  
        // n=sc.nextInt();  
        // //creates an array in the memory of length 10  
        // Integer[] arr = new Integer[100];  
        // System.out.println("Enter the elements of the array: ");  
        // for(int i=0; i<n; i++)  
        // {  
        // //reading array elements from the user   
        // arr[i]=sc.nextInt();  
        // }

	System.out.print("Input = [");
	for (Integer each: list)
		System.out.print(each+", ");
	System.out.print("] \n" +"Input.length = " + list.length + '\n');

	Integer[] arr = Arrays.copyOf(list, list.length);
	MergeSort.threadedSort(arr);
	System.out.print("Output = [");
	for (Integer each: arr)
		System.out.print(each+", ");
	System.out.print("]\n");
	}
}
