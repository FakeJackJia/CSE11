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

    static String[] checkQueryNumber(String name, String[] content, String line, boolean not){
        if(name.equals("length")){
            return Query.length(content, Integer.parseInt(line), not);
        }
        else if(name.equals("greater")){
            return Query.greater(content, Integer.parseInt(line), not);
        }
        else if (name.equals("less")){
            return Query.less(content, Integer.parseInt(line), not);
        }
        else if(name.equals("contains")){
            return Query.contains(content, line, not);
        }
        else if(name.equals("starts")){
            return Query.starts(content, line, not);
        }
        else{
            return Query.ends(content, line, not);
        }


    }

    public static void main(String[] args){
        String[] content = FileHelper.getLines(args[0]);

        if(args.length == 1){
            printing(content);
        }
        else{    
            String[] query = args[1].split("&");
            for (int i = 0; i < query.length; i++){
        
                if(query[i].indexOf("not") == -1){
                    String temp = query[i].replace("'","");
                    String[] instructions = temp.split("=");
                    content = checkQueryNumber(instructions[0], content, instructions[1], false);
                }
                else{
                    String temp = query[i].substring(4,query[i].length()-1);
                    temp = temp.replace("'", "");
                    String[] instructions = temp.split("=");
                    content = checkQueryNumber(instructions[0], content, instructions[1], true);
                }
            }

            if (args.length == 2){
                printing(content);
            }
            else{
                String[] transform = args[2].split("&");
                for(int x = 0; x < transform.length; x++){
                    if(transform[x].indexOf("upper") != -1){
                        content = Transform.upper(content);
                    }
                    else if(transform[x].indexOf("lower") != -1){
                        content = Transform.lower(content);
                    }
                    else if (transform[x].indexOf("first") != -1){
                        String[] instructions = transform[x].split("=");
                        content = Transform.first(content, Integer.parseInt(instructions[1]));
                    }
                    else if (transform[x].indexOf("last") != -1){
                        String[] instructions = transform[x].split("=");
                        content = Transform.last(content, Integer.parseInt(instructions[1]));
                    }
                    else if (transform[x].indexOf("replace") != -1){
                        String temp = transform[x].replace("'","");
                        String[] instructions = temp.split("=");
                        String[] words = instructions[1].split(";");
                        content = Transform.replace(content, words[0], words[1]);
                    }

                }

                printing(content);
            }
            
        }

    }

}

class Query{
    static String[] length(String[] content, int number, boolean not){
        String[] tempNot = new String[content.length];
        String[] temp = new String[content.length];
        int count = 0;
        int countNot = 0;
        
        for (int i = 0; i < content.length; i++){
            if(content[i].length() == number){
                temp[count] = content[i];
                count++;
            }
            else{
                tempNot[countNot] = content[i];
                countNot++;
            }
        }

        if(not){
            String[] output = new String[countNot];
            System.arraycopy(tempNot, 0, output, 0, countNot);
            return output;
        }
        else{
            String[] output = new String[count];
            System.arraycopy(temp, 0, output, 0, count);
            return output;
        }

        
    }

    static String[] greater(String[] content, int number, boolean not){
        String[] tempNot = new String[content.length];
        String[] temp = new String[content.length];
        int count = 0;
        int countNot = 0;
        
        for (int i = 0; i < content.length; i++){
            if(content[i].length() > number){
                temp[count] = content[i];
                count++;
            }
            else{
                tempNot[countNot] = content[i];
                countNot++;
            }
        }
        

        if(not){
            String[] output = new String[countNot];
            System.arraycopy(tempNot, 0, output, 0, countNot);
            return output;
        }
        else{
            String[] output = new String[count];
            System.arraycopy(temp, 0, output, 0, count);
            return output;
        }

        
    }

    static String[] less(String[] content, int number, boolean not){
        String[] tempNot = new String[content.length];
        String[] temp = new String[content.length];
        int count = 0;
        int countNot = 0;
        
        for (int i = 0; i < content.length; i++){
            if(content[i].length() < number){
                temp[count] = content[i];
                count++;
            }
            else{
                tempNot[countNot] = content[i];
                countNot++;
            }
        }

        if(not){
            String[] output = new String[countNot];
            System.arraycopy(tempNot, 0, output, 0, countNot);
            return output;
        }
        else{
            String[] output = new String[count];
            System.arraycopy(temp, 0, output, 0, count);
            return output;
        }

        
    }

    static String[] contains(String[] content, String line, boolean not){
        String[] tempNot = new String[content.length];
        String[] temp = new String[content.length];
        int count = 0;
        int countNot = 0;
        
        for (int i = 0; i < content.length; i++){
            if(content[i].indexOf(line) != -1){
                temp[count] = content[i];
                count++;
            }
            else{
                tempNot[countNot] = content[i];
                countNot++;
            }
        }

        if(not){
            String[] output = new String[countNot];
            System.arraycopy(tempNot, 0, output, 0, countNot);
            return output;
        }
        else{
            String[] output = new String[count];
            System.arraycopy(temp, 0, output, 0, count);
            return output;
        }

        
    }

    static String[] starts(String[] content, String line, boolean not){
        String[] tempNot = new String[content.length];
        String[] temp = new String[content.length];
        int count = 0;
        int countNot = 0;
        
        for (int i = 0; i < content.length; i++){
            if(content[i].startsWith(line)){
                temp[count] = content[i];
                count++;
            }
            else{
                tempNot[countNot] = content[i];
                countNot++;
            }
        }

        if(not){
            String[] output = new String[countNot];
            System.arraycopy(tempNot, 0, output, 0, countNot);
            return output;
        }
        else{
            String[] output = new String[count];
            System.arraycopy(temp, 0, output, 0, count);
            return output;
        }
        
    }

    static String[] ends(String[] content, String line, boolean not){
        String[] tempNot = new String[content.length];
        String[] temp = new String[content.length];
        int count = 0;
        int countNot = 0;
        
        for (int i = 0; i < content.length; i++){
            if(content[i].endsWith(line)){
                temp[count] = content[i];
                count++;
            }
            else{
                tempNot[countNot] = content[i];
                countNot++;
            }
        }

        if(not){
            String[] output = new String[countNot];
            System.arraycopy(tempNot, 0, output, 0, countNot);
            return output;
        }
        else{
            String[] output = new String[count];
            System.arraycopy(temp, 0, output, 0, count);
            return output;
        }
    }


}

class Transform{
    static String[] upper(String[] content){
        String[] output = new String[content.length];

        for (int i = 0; i < content.length; i++){
            output[i] = content[i].toUpperCase();
        }

        return output;
    }

    static String[] lower(String[] content){
        String[] output = new String[content.length];

        for (int i = 0; i < content.length; i++){
            output[i] = content[i].toLowerCase();
        }

        return output;
    }

    static String[] first(String[] content, int number){
        String[] output = new String[content.length];

        for(int i = 0; i < content.length; i++){
            if(content[i].length() <= number){
                output[i] = content[i];
            }
            else{
                output[i] = content[i].substring(0,number);
            }
        }

        return output;

    }

    static String[] last(String[] content, int number){
        String[] output = new String[content.length];

        for(int i = 0; i < content.length; i++){
            if(content[i].length() <= number){
                output[i] = content[i];
            }
            else{
                output[i] = content[i].substring(content[i].length()-number,content[i].length());
            }
        }

        return output;

    }

    static String[] replace(String[] content, String origin, String change){
        String[] output = new String[content.length];

        for(int i = 0; i < content.length; i++){
            output[i] = content[i].replace(origin, change);
        }

        return output;
    }
}