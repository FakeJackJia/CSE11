import java.nio.file.*;
import java.io.IOException;

class FileHelper {
    /*
      Takes a path to a file and returns all of the lines in the file as an
      array of strings, printing an error if it failed.
    */
    static String[] getLines(String path) {
      try {
        return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
      }
      catch(IOException e) {
        System.err.println("Error reading file " + path + ": " + e);
        return new String[]{"Error reading file " + path + ": " + e};
      }
    }
}

class StringSearch{
    static void printing(String[] content){
        for(String i : content){
            System.out.println(i);
        }
    }


    public static void main(String[] args){
        String[] content = FileHelper.getLines(args[0]);

        if(args.length == 1){
            printing(content);
        }
    }
}