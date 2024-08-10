
public class A2 {
    private A2() {
        // Private constructor to prevent object instantiation
    }

    public static void mergeSortIterative(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int n = a.length;

        for (int size = 1; size < n; size *= 2) {
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(a, a, left, mid, right);
            }
        }
    }

    public static void mergeSortRecursive(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int n = a.length;
        int[] aux = new int[n];
        mergeSortRecursive(a, aux, 0, n - 1);
    }

    private static void mergeSortRecursive(int[] a, int[] aux, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        mergeSortRecursive(a, aux, low, mid);
        mergeSortRecursive(a, aux, mid + 1, high);
        merge(a, aux, low, mid, high);
    }

    private static void merge(int[] a, int[] aux, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = a[i];
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high) {
            if (aux[i] <= aux[j]) {
                a[k] = aux[i];
                i++;
            } else {
                a[k] = aux[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            a[k] = aux[i];
            i++;
            k++;
        }
    }
}
