import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.io.*;

public class RadixSort
{
    /*
     * Radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all have same length
     */
    public static void radixSortA( String [ ] arr, int stringLen )
    {
        final int BUCKETS = 256;
        
        ArrayList<String> [ ] buckets = new ArrayList[ BUCKETS ];
        
        for( int i = 0; i < BUCKETS; i++ )
            buckets[ i ] = new ArrayList( );
        
        for( int pos = stringLen - 1; pos >= 0; pos-- )
        {
            for( String s : arr )
                buckets[ s.charAt( pos ) ].add( s );
            
            int idx = 0;
            for( ArrayList<String> thisBucket : buckets )
            {
                for( String s : thisBucket )
                    arr[ idx++ ] = s;
                
                thisBucket.clear( );
            }
        }
    }
       
    /*
     * Counting radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all have same length
     */
    public static void countingRadixSort( String [ ] arr, int stringLen )
    {
        final int BUCKETS = 256;
        
        int N = arr.length;
        String [ ] buffer = new String[ N ];

        String [ ] in = arr;
        String [ ] out = buffer;
        
        for( int pos = stringLen - 1; pos >= 0; pos-- )
        {
            int[ ] count = new int [ BUCKETS + 1 ];
            
            for( int i = 0; i < N; i++ )
                count[ in[ i ].charAt( pos ) + 1 ]++;

            for( int b = 1; b <= BUCKETS; b++ )
                count[ b ] += count[ b - 1 ];

            for( int i = 0; i < N; i++ )
                out[ count[ in[ i ].charAt( pos ) ]++ ] = in[ i ];
            
              // swap in and out roles
            String [ ] tmp = in;
            in = out;
            out = tmp;
        }
        
           // if odd number of passes, in is buffer, out is arr; so copy back
        if( stringLen % 2 == 1 )
            for( int i = 0; i < arr.length; i++ )
                out[ i ] = in[ i ];
    }
    
    /*
     * Radix sort an array of Strings
     * Assume all are all ASCII
     * Assume all have length bounded by maxLen
     */
    public static void radixSort( String [ ] arr, int maxLen )
    {
        final int BUCKETS = 256;
        
        ArrayList<String> [ ] wordsByLength = new ArrayList[ maxLen + 1 ];  // 5 
        ArrayList<String> [ ] buckets = new ArrayList[ BUCKETS ]; // 256
        
        for( int i = 0; i < wordsByLength.length; i++ )
            wordsByLength[ i ] = new ArrayList( ); // a new arralist for each letter in the word
        
        for( int i = 0; i < BUCKETS; i++ )
            buckets[ i ] = new ArrayList( ); // a new arralist for each buccket in bucket array
        
        for( String s : arr ) {
        // for every string in the inout array
            wordsByLength[ s.length( ) ].add( s ); //add the string to arraylist in position of size of string
            //System.out.println("s.length( ):  "+s.length( ));
        }
        int idx = 0;
        for( ArrayList<String> wordList : wordsByLength )
            for( String s : wordList ){
                //System.out.println("s:  "+s);
                 arr[ idx++ ] = s;
            }
               
        
        int startingIndex = arr.length;  
        //System.out.println("arr.length:  "+arr.length);  

        for( int pos = maxLen - 1; pos >= 0; pos-- )
        {
            startingIndex -= wordsByLength[ pos + 1 ].size( );
            //System.out.println("startingIndex:  "+startingIndex); 

            for( int i = startingIndex; i < arr.length; i++ ) {// input array length.
                buckets[ arr[ i ].charAt( pos ) ].add( arr[ i ] );
                //System.out.println("arr[ i ]:  "+arr[ i ]); 
            }
            idx = startingIndex;
            for( ArrayList<String> thisBucket : buckets )
            {
                for( String s : thisBucket )
                    arr[ idx++ ] = s;
                
                thisBucket.clear( );
            }
        }
    }

    public static void main( String [ ] args )throws IOException
    {
        


        // File file = new File("crimeandpunishment/all.txt");
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        // String line = null;
        // String[] words = new String[220000];
        // int j = 0;




        // while( (line = br.readLine())!= null ){
        //         // \\s+ means any number of whitespaces between tokens
        //     String [] tokens = line.split("\\s+");

        //     for(int i = 0; i < tokens.length; i++, j++){
        //       words[j] = tokens[i];
        //     }
        // }
        // // a little hackery shmuckery to fill the array
        // for(int i = j; i < 220000; i++){
        //   words[i] = "null";
        // }   





        List<String> lst = new ArrayList( );
        
        Random r = new Random( );

        final int LEN = 5;
        
        for( int i = 0; i < 1000000; i++ )
        {
            String str = "";
            int len = LEN; // 3 + r.nextInt( 7 ); // between 3 and 9 characters

            for( int j = 0; j < len; j++ )
                str += (char) ( 'a' + r.nextInt( 26 ) );

            lst.add( str );
        }

        String [ ] arr1 = new String[ lst.size( ) ];
        String [ ] arr2 = new String[ lst.size( ) ];

        lst.toArray( arr1 );
        lst.toArray( arr2 );

        long start, end;

        start = System.currentTimeMillis( );
        countingRadixSort( arr1, LEN );
        end = System.currentTimeMillis( );
        System.out.println( "Elapsed: " + ( end - start ) );


        start = System.currentTimeMillis( );
        radixSort( arr2, LEN );
        end = System.currentTimeMillis( );
        System.out.println( "Elapsed: " + ( end - start ) );

        start = System.currentTimeMillis( );
        Arrays.sort( arr1);
        end = System.currentTimeMillis( );
        System.out.println( "Elapsed: " + ( end - start ) );

        for( int i = 0; i < arr1.length; i++ ){
            //System.out.println("sorted array: "+arr1[i]);
            if( !arr1[ i ].equals( arr2[ i ]  ) )
                System.out.println( "OOPS!!" );
        }
            
    }
    
}
