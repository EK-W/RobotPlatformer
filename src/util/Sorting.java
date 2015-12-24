package util;
import java.util.List;


public class Sorting {
	
	public static <T> void quickSort(T[] array, SortableValue<T> value){
		quickSortRecursive(array, 0, array.length, value);
	}
	
	public static <T> void quickSort(List<T> list, SortableValue<T> value){
		quickSortRecursive(list, 0, list.size(), value);
	}
	
	private static <T> void quickSortRecursive(T[] array, int start, int end, SortableValue<T> value){
		if(end - start <= 1){
			return;
		}
		double pivotVal = value.getValue(array[start]);
		int less = start + 1;
		int equal = start + 1;
		int greater = end - 1;
		while(true){
			if(equal > greater){
				//the pointers have converged
				swap(array, start, greater);
				break;
			}
			//This if statement is separate from the third one because the second if statement might change the array
			//in such a way that would make the third if statement be true, and that saves time
			if(value.getValue(array[equal]) > pivotVal && value.getValue(array[greater]) <= pivotVal){
				swap(array, equal, greater);
			}
			if(value.getValue(array[equal]) < pivotVal && value.getValue(array[less]) == pivotVal){
				swap(array, less, equal);
			}
//			if(value.getValue(array[equal]) > pivotVal && value.getValue(array[greater]) < pivotVal){
//				swap(array, equal, greater);
//			}
			
			if(value.getValue(array[less]) < pivotVal){
				less++;
			}
			if(value.getValue(array[equal]) <= pivotVal){
				equal++;
			}
			if(value.getValue(array[greater]) > pivotVal){
				greater --;
			}
		}
		quickSortRecursive(array, start, less, value);
		quickSortRecursive(array, greater + 1, end, value);
	}

	private static <T> void quickSortRecursive(List<T> list, int start, int end, SortableValue<T> value){
		if(end - start <= 1){
			return;
		}
		double pivotVal = value.getValue(list.get(start));
		int less = start + 1;
		int equal = start + 1;
		int greater = end - 1;
		while(true){
			if(equal > greater){
				//the pointers have converged
				swap(list, start, greater);
				break;
			}
			//This if statement is separate from the third one because the second if statement might change the array
			//in such a way that would make the third if statement be true, and that saves time
			if(value.getValue(list.get(equal)) > pivotVal && value.getValue(list.get(greater)) <= pivotVal){
				swap(list, equal, greater);
			}
			if(value.getValue(list.get(equal)) < pivotVal && value.getValue(list.get(less)) == pivotVal){
				swap(list, less, equal);
			}
//			if(value.getValue(array[equal]) > pivotVal && value.getValue(array[greater]) < pivotVal){
//				swap(array, equal, greater);
//			}
			
			if(value.getValue(list.get(less)) < pivotVal){
				less++;
			}
			if(value.getValue(list.get(equal)) <= pivotVal){
				equal++;
			}
			if(value.getValue(list.get(greater)) > pivotVal){
				greater --;
			}
		}
		quickSortRecursive(list, start, less, value);
		quickSortRecursive(list, greater + 1, end, value);
	}
	
	public static <T> void insertionSort(T[] array, SortableValue<T> value){
		for(int i = 1; i < array.length; i++){
			if(value.getValue(array[i]) < value.getValue(array[i - 1])){
				T temp = array[i];
				for(int j = i - 1; true; j--){
					if(j == -1 || value.getValue(temp) >= value.getValue(array[j])){
						array[j + 1] = temp;
						break;
					} else {
						array[j + 1] = array[j];
					}
				}
			}
		}
	}
	
	public static <T> void insertionSort(List<T> list, SortableValue<T> value){
		for(int i = 1; i < list.size(); i++){
			if(value.getValue(list.get(i)) < value.getValue(list.get(i - 1))){
				T temp = list.get(i);
				for(int j = i - 1; true; j--){
					if(j == -1 || value.getValue(temp) >= value.getValue(list.get(j))){
						list.set(j + 1, temp);
						break;
					} else {
						list.set(j + 1, list.get(j));
					}
				}
			}
		}
	}
	
	private static void swap(Object[] array, int index1, int index2){
		Object temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	private static <T> void swap(List<T> list, int index1, int index2){
		T temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}
	
	public interface SortableValue<E> {
		double getValue(E from);
	}
}
