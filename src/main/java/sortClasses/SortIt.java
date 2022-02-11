package sortClasses;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortIt {
    public static void main(String[] args) {
        StringBuilder errorMessage = new StringBuilder();
        List<String> parameters = ParametersScanner.scan(args, errorMessage);
        if (parameters == null) {
            System.out.println(errorMessage);
            return;
        }
        final String path = "src/main/resources/";
        String typeOfVariable = parameters.get(0).substring(1);
        String typeOfSort = parameters.get(1).substring(1);
        String outputFileName = path+parameters.get(2);
        List<File> fileList = new ArrayList<>();
        try{
            for (int i = 3; i < parameters.size(); i++) {
                if (new File(path+parameters.get(i)).exists())
                    fileList.add(new File(path+parameters.get(i)));
                else errorMessage.append("File ").append(parameters.get(i)).append(" does not exist").append("\n");
            }
            if (fileList.size()==0){
                errorMessage.append("Incorrectly entered data for work");
                System.out.println(errorMessage);
                return;
            }
            ArrayList<String> buffList;
            if(FileWorker.checkSortFile(fileList.get(0), typeOfVariable, typeOfSort, errorMessage)==null) buffList = new ArrayList<>();
            else buffList = FileWorker.checkSortFile(fileList.get(0), typeOfVariable, typeOfSort, errorMessage);
            if (!(fileList.size()==1)){
                for (int i = 1; i < fileList.size(); i++) {
                    List<String> temp = FileWorker.checkSortFile(fileList.get(i), typeOfVariable, typeOfSort, errorMessage);
                    if (!(temp==null)) buffList.addAll(temp);
                }
                if (!(buffList.isEmpty())) {
                    if (typeOfVariable.equals("s")) {
                        String[] arr = new String[buffList.size()];
                        buffList.toArray(arr);
                        arr = MergeSort.mergeSortString(arr, typeOfSort);
                        buffList = new ArrayList<>(Arrays.asList(arr));
                    } else {
                        int[] arr = new int[buffList.size()];
                        for (int j = 0; j < buffList.size(); j++) {
                            arr[j] = Integer.parseInt(buffList.get(j));
                        }
                        arr = MergeSort.mergeSortInt(arr, typeOfSort);
                        for (int j = 0; j < arr.length; j++) {
                            buffList.set(j, String.valueOf(arr[j]));
                        }
                    }
                }
            }
            FileWorker.out(buffList, outputFileName, errorMessage);
        } catch (Exception e) {
            System.out.println("Memory overflow");
            return;
        }
        errorMessage.append("Completed successfully");
        System.out.println(errorMessage);
    }
}
