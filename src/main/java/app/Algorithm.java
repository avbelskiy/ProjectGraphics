package app;

import java.util.Collections;
import java.util.Vector;

import static panels.PanelInput.array;

public class Algorithm {
    int number_alg;

    public static void bubble_sort (int[] array) {
        boolean isSorted = false;
        int buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    isSorted = false;
                    buf = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = buf;
                }
            }
        }
    }

    public static void choose_sort (int [] array) {
        for (int min = 0; min < array.length - 1; min++) {
            int least = min;
            for (int j = min + 1; j < array.length; j++) {
                if (array[j] < array[least]) {
                    least = j;
                }
            }
            int buf = array[min];
            array[min] = array[least];
            array[least] = buf;
        }
    }

    public static void paste_sort (int [] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

    public static void shuttle_sort (int [] array) {
        int left = 0;
        int right = array.length - 1;
        do {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    array[i] ^= array[i + 1];
                    array[i + 1] ^= array[i];
                    array[i] ^= array[i + 1];
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (array[i] < array[i - 1]) {
                    array[i] ^= array[i - 1];
                    array[i - 1] ^= array[i];
                    array[i] ^= array[i - 1];
                }
            }
            left++;
        } while (left <= right);
    }

    public static void shell_sort (int [] array) {
        int gap = array.length / 2;
        while (gap >= 1) {
            for (int rightt = 0; rightt < array.length; rightt++) {
                for (int c = rightt - gap; c >= 0; c -= gap) {
                    if (array[c] > array[c + gap]) {
                        int buf = array[c];
                        array[c] = array[c + 1];
                        array[c + 1] = buf;
                    }
                }
            }
            gap = gap / 2;
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int opora = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    public static void swap_sort (int [] array) {
        int left = 0; // ?????????? ??????????????
        int right = array.length - 1; // ???????????? ??????????????

        do
        {
            //???????????????? ?? ?????????? ?????????????? "?????????????? ????????????????"
            for (int i = left; i < right; i++)
            {
                if(array[i] > array[i+1])
                {
                    array[i] ^= array[i+1];
                    array[i+1] ^= array[i];
                    array[i] ^= array[i+1];
                }
            }
            right--; // ?????????????????? ???????????? ??????????????
            //???????????????? ?? ???????????? ?????????????? "???????????? ????????????????"
            for (int i = right; i > left ; i--)
            {
                if(array[i] < array[i-1])
                {
                    array[i] ^= array[i-1];
                    array[i-1] ^= array[i];
                    array[i] ^= array[i-1];
                }
            }
            left++; // ?????????????????????? ?????????? ??????????????
        } while (left <= right);
    }

    public static void count_sort(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int element : array)
        {
            if (element < min)
            {
                min = element;
            }
            if (element > max)
            {
                max = element;
            }
        }
        int[] buckets = new int[max - min + 1];
        for (int element : array)
        {
            buckets[element - min]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < buckets.length; i++)
        {
            for (int j = buckets[i]; j > 0; j--)
            {
                array[arrayIndex++] = i + min;
            }
        }
    }

    void sort_array() {
        switch (number_alg) {
            case (1) ->   //?????????? ????????????????
                    bubble_sort(array);
            case (2) -> //?????????? ????????????
                    choose_sort(array);
            case (3) ->   //?????????? ??????????????????
                    paste_sort(array);
            case (4) ->   //?????????? ????????????
                    shuttle_sort(array);
            case (5) ->   //?????????? ??????????
                    shell_sort(array);
            case (6) ->   //?????????? ?????????????? ????????????????????
                    quickSort(array, 0, array.length - 1);
            case (7) ->   //?????????? ??????????????????????????
                    swap_sort(array);
            case (8) ->   //?????????? ????????????????
                    count_sort(array);
            default -> {
            }
        }
    }
}
