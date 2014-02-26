import java.util.Arrays;
import java.util.Random;
import java.util.Date;
import java.io.*;


public class Quicky{
  public static void main(String[] args)throws IOException {
    File file = new File("crimeandpunishment/all.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    String line = null;
    String[] words = new String[220000];
    int j = 0;
    while( (line = br.readLine())!= null ){
            // \\s+ means any number of whitespaces between tokens
        String [] tokens = line.split("\\s+");

        for(int i = 0; i < tokens.length; i++, j++){
          words[j] = tokens[i];
        }
    }
    // a little hackery shmuckery to fill the array
    for(int i = j; i < 220000; i++){
      words[i] = "null";
    }

//     String[] words = {"Dostoevsky", "was", "the", "son", "of", "a", "doctor", "His", "parents", "were", "very", "hard-working",
// "and", "deeply", "religious", "people"};

    //System.out.println("before sort: "+toString(words));

      Date start = new Date();
      //quicksort_other(words, 0, words.length - 1);  
      quicksort(words, 0, words.length - 1); 
      //mergeSort(words); 
      //bubblesort(words); // total disaster. I don't even feel like waiting fo rit to finish!
      //Arrays.sort(words); 
      Date end = new Date();
      //System.out.println(toString(words));
      System.out.println("time:" +(end.getTime()-start.getTime()));

      Date start2 = new Date();
      //quicksort_other(words, 0, words.length - 1);  
      quicksort(words, 0, words.length - 1); 
      //mergeSort(words); 
      //insertionSort(words);
      //Arrays.sort(words); 
      Date end2 = new Date();
      //System.out.println("sorted words:" +toString(words));
      System.out.println("presorted three time:" +(end2.getTime()-start2.getTime()));
        
  }

  private static final int CUTOFF = 10;
    
    /**
     * Internal quicksort method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * @param a an array of Comparable items.
     * @param low the left-most index of the subarray.
     * @param high the right-most index of the subarray.
     */
  private static void quicksort_other( Comparable[] a, int low, int high ) {
      if( low + CUTOFF > high )
          insertionSort( a, low, high );
      else {
          // Sort low, middle, high
          int middle = ( low + high ) / 2;
          if( a[ middle ].compareTo( a[ low ] ) < 0 )
              exch( a, low, middle );
          if( a[ high ].compareTo( a[ low ] ) < 0 )
              exch( a, low, high );
          if( a[ high ].compareTo( a[ middle ] ) < 0 )
              exch( a, middle, high );
          
          // Place pivot at position high - 1
          exch( a, middle, high - 1 );
          Comparable pivot = a[ high - 1 ];
          
          // Begin partitioning
          int i, j;
          for( i = low, j = high - 1; ; ) {
              while( a[ ++i ].compareTo( pivot ) < 0 )
                  ;
              while( pivot.compareTo( a[ --j ] ) < 0 )
                  ;
              if( i >= j )
                  break;
              exch( a, i, j );
          }
          
          // Restore pivot
          exch( a, i, high - 1 );
          
          quicksort_other( a, low, i - 1 );    // Sort small elements
          quicksort_other( a, i + 1, high );   // Sort large elements
      }
  }

  /**
   * Internal insertion sort routine for subarrays
   * that is used by quicksort.
   * @param a an array of Comparable items.
   * @param low the left-most index of the subarray.
   * @param n the number of items to sort.
   */
  private static void insertionSort( Comparable[] a, int low, int high ) {
      for( int p = low + 1; p <= high; p++ ) {
          Comparable tmp = a[ p ];
          int j;
          
          for( j = p; j > low && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
              a[ j ] = a[ j - 1 ];
          a[ j ] = tmp;
      }
  }

// by using the Comparable[] I can sort any data structure thqt implements comparable (has a compareTo method)

  public static void insertionSortReverse(Comparable[] a){  // high to low
     int j;                  // the number of items sorted so far
     Comparable key;                // the item to be inserted
     int i;  
     // Start with 1 (not 0)
     for (j = 1; j < a.length; j++) {
           key = a[j];
           for(i = j - 1; (i >= 0) && (a[i].compareTo(key) < 0); i--){ //a[ i ] < key
                 a[i+1] = a[i];
          }
         a[i+1] = key;    // Put the key in its proper location
     }
  }


 /**
   * Mergesort algorithm.
   * @param a an array of Comparable items.
   */
  public static void mergeSort( Comparable [ ] a ) {
      Comparable [ ] tmpArray = new Comparable[ a.length ];
      mergeSort( a, tmpArray, 0, a.length - 1 );
  }
  
  /**
   * Internal method that makes recursive calls.
   * @param a an array of Comparable items.
   * @param tmpArray an array to place the merged result.
   * @param left the left-most index of the subarray.
   * @param right the right-most index of the subarray.
   */
  private static void mergeSort( Comparable [ ] a, Comparable [ ] tmpArray,
          int left, int right ) {
      if( left < right ) {
          int center = ( left + right ) / 2;
          mergeSort( a, tmpArray, left, center );
          mergeSort( a, tmpArray, center + 1, right );
          merge( a, tmpArray, left, center + 1, right );
      }
  }
  
  /**
   * Internal method that merges two sorted halves of a subarray.
   * @param a an array of Comparable items.
   * @param tmpArray an array to place the merged result.
   * @param leftPos the left-most index of the subarray.
   * @param rightPos the index of the start of the second half.
   * @param rightEnd the right-most index of the subarray.
   */
  private static void merge( Comparable [ ] a, Comparable [ ] tmpArray,
      int leftPos, int rightPos, int rightEnd ) {
      int leftEnd = rightPos - 1;
      int tmpPos = leftPos;
      int numElements = rightEnd - leftPos + 1;
      
      // Main loop
      while( leftPos <= leftEnd && rightPos <= rightEnd )
          if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
              tmpArray[ tmpPos++ ] = a[ leftPos++ ];
          else
              tmpArray[ tmpPos++ ] = a[ rightPos++ ];
      
      while( leftPos <= leftEnd )    // Copy rest of first half
          tmpArray[ tmpPos++ ] = a[ leftPos++ ];
      
      while( rightPos <= rightEnd )  // Copy rest of right half
          tmpArray[ tmpPos++ ] = a[ rightPos++ ];
      
      // Copy tmpArray back
      for( int i = 0; i < numElements; i++, rightEnd-- )
          a[ rightEnd ] = tmpArray[ rightEnd ];
  }
 
  
  public static void exch(Object[] a, int i, int j){
      Object temp = a[i];
          a[i] = a[j];
          a[j] = temp;
  }

  public static void quicksort(Comparable[] a, int low, int high) {
    if( low + CUTOFF > high )
            insertionSort( a, low, high );
    else if (low < high) {
       int middle = ( low + high ) / 2;

      if( a[ middle ].compareTo( a[ low ] ) < 0 )
          exch( a, low, middle );

      if( a[ high ].compareTo( a[ low ] ) < 0 )
          exch( a, low, high );

      if( a[ high ].compareTo( a[ middle ] ) < 0 )
          exch( a, middle, high );


      // Place pivot at position high 
      exch( a, middle, high  );
      Comparable pivot = a[ high  ];

      // int pivotIndex = randInt(low, high);
      // Comparable pivot = a[pivotIndex];
      // a[pivotIndex] = a[high];                       // this is worse than choosing high!!! apparently the extra function and swap really slows shit down
      // a[high] = pivot;

      //Comparable pivot = a[high];

      int i = low - 1;
      int j = high;
      do {
        do { i++; } while (a[i].compareTo(pivot) < 0);
        do { j--; } while ((a[j].compareTo(pivot) > 0) && (j > low));
        if (i < j) {
          exch(a, i, j);
        }
      } while (i < j);

      // a[high] = a[i];
      // a[i] = pivot;    // Put pivot in the middle where it belongs
      exch( a, i, high );     
      quicksort(a, low, i - 1);                     // Recursively sort left list
      quicksort(a, i + 1, high);                   // Recursively sort right list
    }
  }

  public static int randInt(int min, int max) {

      // Usually this can be a field rather than a method variable
      Random rand = new Random();

      // nextInt is normally exclusive of the top value,
      // so add 1 to make it inclusive
      int randomNum = rand.nextInt((max - min) + 1) + min;

      return randomNum;
  }

  public static void bubblesort(String[] s){
    int n = s.length;
    String swap = "";
    for (int c = 0; c < ( n - 1 ); c++) {
      for (int d = 0; d < n - c - 1; d++) {
        if (s[d].compareTo(s[d+1]) > 0) /* For descending order use < */
        {
          swap  = s[d];
          s[d]  = s[d+1];
          s[d+1] = swap;
        }
      }
    }
  }
  public static String toString(Comparable[] a){
    //StringBuilder sb = new StringBuilder();
       // for(int i = 0; i < a.length; i++){
    //   sb.append(", "+a[i]);
    // }
    //sb.deleteCharAt(0);
    //return sb.toString();

    return Arrays.toString(a);
    
  }
}