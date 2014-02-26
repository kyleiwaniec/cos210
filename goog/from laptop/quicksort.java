import java.util.Random;


public static void quicksort(Comparable[] a, int low, int high) {
  // If there's fewer than two items, do nothing.
  if (low < high) {
    int pivotIndex = randInt(low, high);
    Comparable pivot = a[pivotIndex];
    a[pivotIndex] = a[high];                       // Swap pivot with last item
    a[high] = pivot;

    int i = low - 1;
    int j = high;
    do {
      do { i++; } while (a[i].compareTo(pivot) < 0);
      do { j--; } while ((a[j].compareTo(pivot) > 0) && (j > low));
      if (i < j) {
        swap a[i] and a[j];
      }
    } while (i < j);

    a[high] = a[i];
    a[i] = pivot;                   // Put pivot in the middle where it belongs
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
