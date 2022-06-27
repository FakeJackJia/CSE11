import java.util.*;
import java.nio.file.*;
import java.nio.*;
import java.io.IOException;
import java.util.Set;

class CSVTool {
    // Task 2: CSVTool Implementation
    // Your code here
    public static void main(String[] args) throws IOException{
        List<String> query = Files.readAllLines(Paths.get(args[0]));

        List<String> tempList = new ArrayList<String>();
        List<String> current = new ArrayList<String>();
        List<String> answer = new ArrayList<String>();
        
        String[] output = new String[query.size()];
        boolean flag = false;

        for(int i = 1; i < args.length; i++){
            tempList = Files.readAllLines(Paths.get(args[i]));
            // if (args[i].contains(Integer.toString(i)) == false){
            //     break;
            // }
            for(int j = 0; j < query.size(); j++){
                if(i == 1){
                    current.add(query.get(j));
                }
                else{
                    String[] s = output[j].split(",");
                    current.addAll(Arrays.asList(s));
                }
                
                for(int z = 0; z < tempList.size(); z++){
                    String[] tempArray = tempList.get(z).split(",");

                    for(int x = 0; x < tempArray.length; x++){
                        if(current.contains(tempArray[x])){
                            current.addAll(Arrays.asList(tempArray));
                            flag = true;
                            break;
                        }
                    }

                    if (flag){
                        break;
                    }

                }

                flag = false;
                String tempString = String.join(",", current);

                if(i == 1){
                    output[j] = tempString;
                }
                else{
                    output[j] = output[j].concat(",").concat(tempString);
                }
                
                current.clear();
            }
        }

        for(int a = 0; a < output.length; a++){
            String[] array = output[a].split(",");
            String first = array[0];
            Set<String> set = new HashSet<>(Arrays.asList(array));
            answer.addAll(set);
            Collections.sort(answer);

            answer.remove(first);

            String combine = first.concat(": [");
            for (int b = 0; b < answer.size(); b++){
                if (b == answer.size() - 1){
                    combine = combine.concat(answer.get(b));
                    break;
                }
                combine = combine.concat(answer.get(b)).concat(", ");
            }

            combine = combine.concat("]");
            System.out.println(combine);
            answer.clear();
        }

        
    }

}