package sortClasses;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

public class ParametersScanner {
    /**
     * функция, обрабатывающая параметры
     *
     * @param inpParameters массив входных параметров
     * @param errorMessage сообщение об ошибке
     */
    public static List<String> scan(String[] inpParameters, StringBuilder errorMessage) {
        List<String> inpInfo = new ArrayList<>();
        String typeOfSort = "up";
        int countSortType = 0;
        String typeOfVariable = null;
        int countDataType = 0;
        String outputFile = null;
        StringBuilder inpStr = new StringBuilder();
        for (String arg: inpParameters) {
            inpStr.append(arg).append(" ");
        }

        Scanner scanner = new Scanner(inpStr.toString());
        outerloop:
        while (scanner.hasNext()){
            String select = scanner.next();
            switch (select) {
                case "-a":
                    typeOfSort = "-a";
                    countSortType++;
                    break;
                case "-d":
                    typeOfSort = "-d";
                    countSortType++;
                    break;
                case "-s":
                    typeOfVariable = "-s";
                    countDataType++;
                    break;
                case "-i":
                    typeOfVariable = "-i";
                    countDataType++;
                    break;
                default:
                    outputFile = select;
                    break outerloop;
            }
        }


        List<String> inputFilesName = new ArrayList<>();
        while (scanner.hasNext()){
            String select = scanner.next();
            inputFilesName.add(select);
        }

        try {
            if (countSortType > 1 || countDataType > 1){
                throw new RepeatFlagsException();
            }
            if (typeOfVariable == null) {
                throw new NullPointerException();
            }
            if (inputFilesName.isEmpty()) {
                throw new EmptyStackException();
            }
            if (inputFilesName.contains(outputFile)) {
                throw new Exception();
            }
        } catch (NullPointerException e) {
            errorMessage.append("Error: You have not entered the data type");
            return null;
        } catch (EmptyStackException e) {
            errorMessage.append("Error: You have not entered the names of the input files");
            return null;
        } catch (RepeatFlagsException e) {
            errorMessage.append("Error: You have entered the flags for the mode or data type several times");
            return null;
        } catch (Exception e) {
            errorMessage.append("Error: You have entered the output file in the incoming arguments");
            return null;
        }

        inpInfo.add(typeOfVariable);
        inpInfo.add(typeOfSort);
        inpInfo.add(outputFile);
        inpInfo.addAll(inputFilesName);
        return inpInfo;
    }

    //класс ошибки
    public static class RepeatFlagsException extends Exception {
        public RepeatFlagsException() {
            super("");
        }
    }
}
