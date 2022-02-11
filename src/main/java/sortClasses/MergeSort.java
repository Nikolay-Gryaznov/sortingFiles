package sortClasses;

public class MergeSort {
    /**
     * функция, реализующая сортировку слиянием целочисленного массива
     *
     * @param array      массив для сортировки
     * @param typeOfSort тип сортировки
     */
    public static int[] mergeSortInt(int[] array, String typeOfSort) {
        //ссылка для смены местами
        int[] buff;
        //изначальный массив
        int[] startArr = array;
        //результирующий массив
        int[] endArr = new int[array.length];
        //размер сливаемых массивов
        int size = 1;

        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                mergeInt(startArr, i, startArr, i + size, endArr, i, size, typeOfSort);
            }

            buff = startArr;
            startArr = endArr;
            endArr = buff;

            size *= 2;
        }
        return startArr;
    }


    /**
     * функция, реализующая слияние двух целочисленных массивов в один
     *
     * @param arr1        первый массив
     * @param arr1Start   элемент, с которого начинается слияние первый массив
     * @param arr2        второй массив
     * @param arr2Start   элемент, с которого начинается слияние второй массив
     * @param resArr      массив, который принимает слияние 1 и 2 массивов
     * @param resArrStart элемент, с которого происходит запись
     * @param size        размеры подмассива для слияния
     * @param typeOfSort  тип сортировки
     */
    private static void mergeInt(int[] arr1, int arr1Start, int[] arr2, int arr2Start, int[] resArr,
                                 int resArrStart, int size, String typeOfSort) {

        int index1 = arr1Start;
        int index2 = arr2Start;

        int src1End = Math.min(arr1Start + size, arr1.length);
        int src2End = Math.min(arr2Start + size, arr2.length);

        if (arr1Start + size > arr1.length) {
            if (src1End - arr1Start >= 0) {
                System.arraycopy(arr1, arr1Start, resArr, arr1Start, src1End - arr1Start);
            }
            return;
        }

        int count = src1End - arr1Start + src2End - arr2Start;

        if (typeOfSort.equals("a")) {
            for (int i = resArrStart; i < resArrStart + count; i++) {
                if (index1 < src1End && (index2 >= src2End || arr1[index1] < arr2[index2])) {
                    resArr[i] = arr1[index1];
                    index1++;
                } else {
                    resArr[i] = arr2[index2];
                    index2++;
                }
            }
        } else {
            for (int i = resArrStart; i < resArrStart + count; i++) {
                if (index1 < src1End && (index2 >= src2End || arr1[index1] > arr2[index2])) {
                    resArr[i] = arr1[index1];
                    index1++;
                } else {
                    resArr[i] = arr2[index2];
                    index2++;
                }
            }
        }
    }


    /**
     * функция, реализующая слияние двух строчных массивов в один
     *
     * @param arr1        первый массив
     * @param arr1Start   элемент, с которого начинается слияние первый массив
     * @param arr2        второй массив
     * @param arr2Start   элемент, с которого начинается слияние второй массив
     * @param resArr      массив, который принимает слияние 1 и 2 массивов
     * @param resArrStart элемент, с которого происходит запись
     * @param size        размеры подмассива для слияния
     * @param typeOfSort  тип сортировки
     */
    private static void mergeStr(String[] arr1, int arr1Start, String[] arr2, int arr2Start, String[] resArr,
                                 int resArrStart, int size, String typeOfSort) {

        int index1 = arr1Start;
        int index2 = arr2Start;

        int src1End = Math.min(arr1Start + size, arr1.length);
        int src2End = Math.min(arr2Start + size, arr2.length);

        if (arr1Start + size > arr1.length) {
            if (src1End - arr1Start < 0) return;
            System.arraycopy(arr1, arr1Start, resArr, arr1Start, src1End - arr1Start);
            return;
        }

        int count = src1End - arr1Start + src2End - arr2Start;

        if (typeOfSort.equals("a")) {
            for (int i = resArrStart; i < resArrStart + count; i++) {
                if (index1 < src1End && (index2 >= src2End || arr1[index1].compareTo(arr2[index2]) < 0)) {
                    resArr[i] = arr1[index1];
                    index1++;
                } else {
                    resArr[i] = arr2[index2];
                    index2++;
                }
            }
        } else {
            for (int i = resArrStart; i < resArrStart + count; i++) {
                if (index1 < src1End && (index2 >= src2End || arr1[index1].compareTo(arr2[index2]) > 0)) {
                    resArr[i] = arr1[index1];
                    index1++;
                } else {
                    resArr[i] = arr2[index2];
                    index2++;
                }
            }
        }
    }


    /**
     * функция, реализующая сортировку слиянием строчного массива (по аналогии с mergeSortInt)
     *
     * @param array      массив для сортировки
     * @param typeOfSort тип сортировки
     */
    public static String[] mergeSortString(String[] array, String typeOfSort) {

        String[] buff;
        String[] startArr = array;
        String[] endArr = new String[array.length];

        int size = 1;

        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                mergeStr(startArr, i, startArr, i + size, endArr, i, size, typeOfSort);
            }

            buff = startArr;
            startArr = endArr;
            endArr = buff;

            size = size * 2;
        }
        return startArr;
    }
}