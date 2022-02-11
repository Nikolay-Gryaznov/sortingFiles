package sortClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWorker {

    /**
     * функция проверки равильности типа для одного элемента
     *
     * @param str            данные для проверки
     * @param typeOfVariable тип переменной
     */
    public static boolean checkTypeStr(String str, String typeOfVariable) {
        boolean resultInt = true;
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            resultInt = false;
        }
        if (typeOfVariable.equals("i") && resultInt) return true;
        else return typeOfVariable.equals("s") && !str.contains(" ");
    }

    /**
     * функция проверки правильности сортировки
     *
     * @param file           файл для проверки
     * @param typeOfVariable тип данных
     * @param typeOfSort     тип сортировки
     * @param errorMessage   сообщение об ошибках
     */
    public static ArrayList<String> checkSortFile(File file, String typeOfVariable, String typeOfSort, StringBuilder errorMessage) {
        ArrayList<String> list;
        boolean result = true;
        String buff;
        ArrayList<Integer> integerList = new ArrayList<>();
        ArrayList<String> stringList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            if (typeOfVariable.equals("i")) {
                while (bufferedReader.ready()) {
                    buff = bufferedReader.readLine();
                    if (checkTypeStr(buff, typeOfVariable)) integerList.add(Integer.parseInt(buff));
                }
            } else {
                while (bufferedReader.ready()) {
                    buff = bufferedReader.readLine();
                    if (checkTypeStr(buff, typeOfVariable)) stringList.add(buff);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            errorMessage.append("File ").append(file.getName()).append(" not found\n");
        }

        if (integerList.size() != 0) {
            if (integerList.size() != 1) {
                for (int i = 0; i < integerList.size() - 1; i++) {
                    if (typeOfSort.equals("a")) {
                        if (!(integerList.get(i) <= integerList.get(i + 1))) {
                            result = false;
                        }
                    } else {
                        if (!(integerList.get(i) >= integerList.get(i + 1))) {
                            result = false;
                        }
                    }
                }
            }
            ArrayList<String> intToStr = new ArrayList<>();
            for (int o : integerList) {
                intToStr.add(String.valueOf(o));
            }
            list = intToStr;
        } else if (stringList.size() != 0) {
            if (stringList.size() != 1) {
                int bool;
                for (int i = 0; i < stringList.size() - 1; i++) {
                    bool = stringList.get(i).compareTo(stringList.get(i + 1));
                    if (typeOfSort.equals("a")) {
                        if (!(bool <= 0)) {
                            result = false;
                        }
                    } else {
                        if (!(bool >= 0)) {
                            result = false;
                        }
                    }
                }
            }
            list = stringList;
        } else {
            return null;
        }
        if (result) {
            return list;
        } else {
            if (typeOfVariable.equals("s")) {
                String[] array = new String[list.size()];
                list.toArray(array);
                array = MergeSort.mergeSortString(array, typeOfSort);
                return new ArrayList<>(Arrays.asList(array));

            } else {
                int[] array = new int[list.size()];
                for (int i = 0; i < array.length; i++) {
                    array[i] = Integer.parseInt(list.get(i));
                }
                array = MergeSort.mergeSortInt(array, typeOfSort);
                for (int i = 0; i < array.length; i++) {
                    list.set(i, String.valueOf(array[i]));
                }
                return list;
            }
        }
    }

    /**
     * функция выдода отсортированных данных в файл
     *
     * @param outData      отсортированные данные
     * @param outFileName  название файла с отсортированными данными
     * @param errorMessage сообщение об ошибках
     */
    public static void out(ArrayList<String> outData, String outFileName, StringBuilder errorMessage) {
        File outFile = new File(outFileName);
        if (outFile.exists()) {
            try {
                Files.delete(Paths.get(outFileName));
            } catch (IOException x) {
                errorMessage.append("Error writing output");
                return;
            }
        }
        try {
            boolean a = outFile.createNewFile();
        } catch (Exception e) {
            errorMessage.append("Error creating the file");
            return;
        }
        try {
            FileWriter fw = new FileWriter(outFileName);
            for (String outString : outData) {
                fw.write(outString + "\n");
            }
            fw.close();
        } catch (Exception e) {
            errorMessage.append("Error writing output");
        }
    }

}

