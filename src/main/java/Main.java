import exceptions.InvalidFileNameException;
import exceptions.SpaceInStringException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String sortingFlag = "";
    public static String dataType;
    public static String nameOutputFile;

    public static void main(String[] args) {
        int[] mergeResult = parsingInputStringAndGoMerge(args);
        writeSortedData(nameOutputFile, mergeResult);
    }

    public static int[] merge(int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        int[] src = new int[leftLength + rightLength];
        int k = 0, i = 0, j = 0;

        if (sortingFlag.equals("-a") || sortingFlag.equals("")) {
            while (i < leftLength && j < rightLength) {
                if (left[i] <= right[j])
                    src[k++] = left[i++];
                else src[k++] = right[j++];
            }
        } else if (sortingFlag.equals("-d")) {
            while (i < leftLength && j < rightLength) {
                if (left[i] >= right[j])
                    src[k++] = left[i++];
                else src[k++] = right[j++];
            }
        }

        while (i < leftLength) {
            src[k++] = left[i++];
        }

        while (j < rightLength) {
            src[k++] = right[j++];
        }
        return src;
    }

    public static int[] parsingInputStringAndGoMerge(String[] args) {
        int counter = 1;

        // FIRST INPUT PARAMETER
        if (args[counter].equals("-a") || args[counter].equals("-d")) {
            sortingFlag = args[counter++];
        }

        // SECOND INPUT PARAMETER
        dataType = args[counter++];

        // THIRD INPUT PARAMETER
        nameOutputFile = args[counter++];
        checkFileName(nameOutputFile);

        int[] mergeResult = new int[0];
        for (int i = counter; i < args.length; i++) {
            int[] left;
            int[] right;

            if (i == counter && i + 1 != args.length) {
                left = readData(args[i]);
                right = readData(args[i + 1]);
                mergeResult = merge(left, right);
                i++;
            } else if (args.length - counter == 1) {
                //IF ONLY 1 INPUT FILE
                return readData(args[i]);
            } else {
                left = readData(args[i]);
                right = mergeResult;
                mergeResult = merge(left, right);
            }
        }
        return mergeResult;
    }

    public static int[] readData(String nameInputFile) {
        List<Integer> numbersFromFile = new ArrayList<>();

        checkFileName(nameInputFile);
        try (BufferedReader readerOfFiles = new BufferedReader(new FileReader(nameInputFile))) {
            String line;
            int value, previous = 0, counter = 0;
            int counterLine = 0;

            // CHECK: INVALID DATA TYPE
            if (dataType.equals("-s") || dataType.equals("-i")) {
                while ((line = readerOfFiles.readLine()) != null) {
                    counterLine++;
                    // CHECK: SPACE IN THE LINE
                    if (line.matches("\\d+")) {
                        value = Integer.parseInt(line);
                        //CHECK: IF ARRAY IS NOT SORTED
                        if (counter == 0) {
                            numbersFromFile.add(value);
                        } else {
                            if (((previous <= value && (sortingFlag.equals("") || sortingFlag.equals("-a"))))) {
                                numbersFromFile.add(value);
                            } else if ((previous >= value && sortingFlag.equals("-d"))) {
                                numbersFromFile.add(value);
                            }
                        }
                        counter++;
                        previous = value;
                    } else {
                        try {
                            throw new SpaceInStringException(nameInputFile, counterLine);
                        } catch (SpaceInStringException e) {
                            System.out.println();
                        }
                    }
                }
            } else
                System.out.println("[WARNING] Invalid data type");
        } catch (FileNotFoundException e) {
            // CHECK: INPUT FILE NOT FOUND
            System.out.println("[ERROR] The input file: " + nameInputFile + " wasn`t found. " +
                    "Please check the path and run the program again");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbersFromFile.stream().mapToInt(i -> i).toArray();
    }

    // CHECK: INVALID FILE NAME [*.txt]
    public static void checkFileName(String fileName) {
        try {
            if (!fileName.matches("[0-9a-z_]+\\.txt$"))
                throw new InvalidFileNameException(fileName);
        } catch (InvalidFileNameException e) {
            System.out.println();
        }
    }

    public static void writeSortedData(String fileName, int[] sortedArray) {
        try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < sortedArray.length; i++) {
                writerToFile.write(sortedArray[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
