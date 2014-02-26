public class MergePU {
  private static void merge(Comparable[] a, Comparable[] aux, int l, int m, int r) {
    for (int i = l; i < m; i++) aux[i] = a[i];
    for (int j = m; j < r; j++) aux[j] = a[m + r - j - 1];
    int i = l, j = r - 1;
    for (int k = l; k < r; k++)
      if (less(aux[j], aux[i])) a[k] = aux[j--];
      else a[k] = aux[i++];
  }
  public static void sort(Comparable[] a) {
    int N = a.length;
    Comparable[] aux = new Comparable[N];
    for (int m = 1; m < N; m = m + m)
      for (int i = 0; i < N - m; i += m + m)
        merge(a, aux, i, i + m, Math.min(i + m + m, N));
  }
}