package exceptions;

public class InvalidFileNameException extends RuntimeException {
    public InvalidFileNameException(String fileName) {
        System.out.println("[ERROR] Invalid name of file: " + fileName +
                ". Check that the file name matches the template: *.txt");
    }
}
