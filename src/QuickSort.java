import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

    private List<E> elements;
    private Order   order;
    private StringBuilder steps; // add on to this for every iteration of sort

    public QuickSort(List<E> elements, Order order) {
        this.elements = elements;
        this.order    = order;
        steps = new StringBuilder();
    }

    @Override
    public List<E> getList() {
        return elements;
    }

    @Override
    public void sort() {
        // No splitting a 0 or 1-length list and is already sorted, so hardcode it out
        if (elements.size() <= 1) return;
        sort(0, elements.size() - 1);
    }

    // Need a helper function with extra arguments
    private void sort(int lo, int hi) {
        if (hi > lo) {
            int pivot = partition(lo, hi); // pivot is already sorted, do not perform any operations on it
            sort(lo, pivot - 1);
            sort(pivot + 1, hi);
        }
    }

    // Helper function to take the place of Collections.swap
    private void swap(int i, int j) {
        E temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }

    private int partition(int lo, int hi) {
        E pivot = elements.get(lo); // use first element as pivot
        int i = lo + 1; // LtR counter
        int j = hi; // RtL counter

        while (j > i) {
            steps.append(pivot);
            steps.append(" :: ");
            steps.append(elements);
            steps.append("\n");

            while (i <= j && elements.get(i).compareTo(pivot) <= 0) i++; // look for larger than pivot
            while (i <= j && elements.get(j).compareTo(pivot) > 0) j--; // look for smaller than pivot

            if (j > i) swap(i, j); // don't want to swap if the indices have crossed
        }

        // Don't want to swap in the case that the lo-index element is already in the correct spot (smallest), so hide behind conditional
        if (pivot.compareTo(elements.get(j)) > 0) {
            swap(j, lo);
            steps.append("  :: ");
            steps.append(elements);
            steps.append("\n");

            return j; // new pivot's index is now j
        }

        return lo; // lo-index element is in the correct spot, is the pivot still
    }

    private void sortIncreasing() {

    }

    private void sortDecreasing() {

    }

    /**
     * The method displays the original input (unsorted) list, and then, each step is shown in a new line. For example,
     * if the original list to be sorted in increasing order is [6, 4, 9, 5, 1, 8, 2, 7, 3], calling this method should
     * string as follows, exactly in the format shown:
     * <p>
     * 6 :: [6, 4, 9, 5, 1, 8, 2, 7, 3]
     * 6 :: [6, 4, 3, 5, 1, 8, 2, 7, 9]
     * 6 :: [6, 4, 3, 5, 1, 2, 8, 7, 9]
     *   :: [2, 4, 3, 5, 1, 6, 8, 7, 9]
     * <p>
     * Only the steps with the first pivot are shown above, and NOT the entire output. The last step with a specific
     * pivot does not show the pivot separately in front of the :: separator, to indicate that a different pivot will
     * be used in the next step. At each stage, you must use the first element as the pivot.
     *
     * @return the string representation of the step-wise transformation of the input list, as done with quick sort
     */
    @Override
    public String show() {
        return steps.toString();
    }

    public static void main(String... args) {
        Sorter<Integer> intsorter = new QuickSort<>(Arrays.asList(6, 4, 9, 5, 1, 8, 2, 7, 3), Order.INCREASING);
        //Sorter<Integer> intsorter = new QuickSort<>(Arrays.asList(66, 44, 99, 55, 11, 88, 22, 77, 33), Order.INCREASING);
        //Sorter<Integer> intsorter = new QuickSort<>(Arrays.asList(6, 4, 9, 5, 1, 8, 2, 7, 3, 15, 11, 87, 76, 61, 19, 6471, 69, 42, 614, 761, 6189, 618, 919, 618, 511, 71, 32, 131, 64, 89, 444, 10, 77, 293), Order.INCREASING);
        //Sorter<Integer> intsorter = new QuickSort<>(Arrays.asList(4, 87, 70, 6), Order.INCREASING);
        //Sorter<Integer> intsorter = new QuickSort<>(Arrays.asList(5, 2, 9, 3, 8, 4, 0, 1, 6, 7), Order.INCREASING);
        intsorter.sort();
        System.out.println(intsorter.show());
        // NOTE: the list shown at the end of the string printed after calling show() MUST be identical to the result
        // calling getList() after calling sort(). That is, the backing list must actually be changed as is shown by the
        // result of the show() method.
    }
}
