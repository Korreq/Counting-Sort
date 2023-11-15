import java.util.*;
import java.util.stream.IntStream;

public class SortBigNumbers {
    public void sort(Integer[] array){
        int lengthOfLongestNumber = getLengthOfLongestNumber(array), position = 0;

        while (position < lengthOfLongestNumber){
            int[] frequencyArray = createFrequencyArray(array, position);
            Integer[] tempArr = new Integer[array.length];
            Arrays.fill(tempArr, null);

            for(int i = array.length - 1; i >= 0; i--){
                int posNumber = (int) ((array[i]/Math.pow(10,position)) % 10), newPos = frequencyArray[posNumber] - 1;
                frequencyArray[posNumber]--;
                if(tempArr[newPos] == null) tempArr[newPos] = array[i];
            }
            array = tempArr;
            position++;
        }
        for (int number: array) { System.out.print(number+" "); }
    }
     private int getLengthOfLongestNumber(Integer[] array){
         int lengthOfLongestNumber = 0;
         for (int i: array) {
             int lengthOfNumber = String.valueOf(i).length();
             if(lengthOfLongestNumber < lengthOfNumber){ lengthOfLongestNumber = lengthOfNumber; }
         }
         return lengthOfLongestNumber;
     }
     private int[] createFrequencyArray(Integer[] array, int position){
        List<Integer> tempList = new ArrayList<>();
        int[] frequencyArray = new int[10];
        Arrays.fill(frequencyArray, 0);

        for(int number : array){ tempList.add((int) (number/Math.pow(10,position) % 10)); }
        tempList.forEach(number -> frequencyArray[number]++);
        IntStream.range(1, 10).forEach(i -> frequencyArray[i] += frequencyArray[i - 1]);
        return frequencyArray;
     }
}