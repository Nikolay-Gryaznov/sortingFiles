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
        String typeOfVariable = parameters.get(0).substring(1);
        String typeOfSort = parameters.get(1).substring(1);
        String outputFileName = "resources/"+parameters.get(2);
        List<File> fileList = new ArrayList<>();
        for (int i = 3; i < parameters.size(); i++) {
            if (new File("resources/"+parameters.get(i)).exists())
                fileList.add(new File("resources/"+parameters.get(i)));
            else errorMessage.append("Файла ").append(parameters.get(i)).append(" не существует").append("\n");
        }
        if (fileList.size()==0){
            errorMessage.append("Не коректно введены данные для работы");
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
                    buffList = new ArrayList<String>(Arrays.asList(arr));
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
        errorMessage.append("Выполненно успешно");
        System.out.println(errorMessage);
    }
}
