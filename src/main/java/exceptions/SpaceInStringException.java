package exceptions;

public class SpaceInStringException extends RuntimeException{
    public SpaceInStringException(String fileName, int numOfString) {
        System.out.println("[WARNING] The string#" + numOfString + " in file: " + fileName +
                " contains a character other than a digit");
    }
}
