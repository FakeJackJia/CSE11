interface Command{
    String[] execute(String[] data);
}

class CmdTool{
    public static void main(String[] args){
        if(args[0].equals("sum")){
            Command sum = new Sum();
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);
            
            System.out.println(sum.execute(copy)[0]);
        }

        if(args[0].equals("product")){
            Command product = new Product();
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);
            
            System.out.println(product.execute(copy)[0]);
        }

        if(args[0].equals("mean")){
            Command mean = new Mean();
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);
            
            System.out.println(mean.execute(copy)[0]);
        }

        if(args[0].equals("max")){
            Command max = new Max();
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);
            
            System.out.println(max.execute(copy)[0]);
        }

        if(args[0].equals("min")){
            Command min = new Min();
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);
            
            System.out.println(min.execute(copy)[0]);
        }

        if(args[0].equals("positive")){
            Command positive = new Positive();
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);
            
            String[] output = positive.execute(copy);
            for(String i : output){
                System.out.print(i + " ");
            }
        }

        if(args[0].equals("negative")){
            Command negative = new Negative();
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);
            
            String[] output = negative.execute(copy);
            for(String i : output){
                System.out.print(i + " ");
            }
        }

        if(args[0].equals("count")){
            Command count = new Count();
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);
            
            System.out.println(count.execute(copy)[0]);
        }

        if(args[0].contains("greater")){
            Command greater = new Greater(args[1]);

            String[] copy = new String[args.length-2];
            System.arraycopy(args, 2, copy, 0, copy.length);

            String[] output = greater.execute(copy);

            for(String i : output){
                System.out.print(i + " ");
            }
        }

        if(args[0].contains("lesser")){
            Command lesser = new Lesser(args[1]);
            String[] copy = new String[args.length-2];
            System.arraycopy(args, 2, copy, 0, copy.length);
            
            String[] output = lesser.execute(copy);

            for(String i : output){
                System.out.print(i + " ");
            }
        }

        if(args[0].contains("equal")){
            Command equal = new Equal(args[1]);
            String[] copy = new String[args.length-2];
            System.arraycopy(args, 2, copy, 0, copy.length);
            
            String[] output = equal.execute(copy);

            for(String i : output){
                System.out.print(i + " ");
            }
        }

        

        if(args[0].contains("-l") || args[0].contains("-list")){
            String[] copy = new String[args.length-1];
            System.arraycopy(args, 1, copy, 0, copy.length);

            int count = 0;
         
            for(int i = 0; i < copy.length; i++){
                if (copy[i].equals("greater") || copy[i].equals("lesser") || copy[i].equals( "equal")){
                    count = count + 2;
                    i++;
                }
                else if(copy[i].equals("sum") || copy[i].equals("product") || copy[i].equals("mean") || copy[i].equals("max") || copy[i].equals("min") || copy[i].equals("count") || copy[i].equals("positive") || copy[i].equals("negative" )){
                    count++;
                }
            }
            
            String[] instruction = new String[count];
            System.arraycopy(copy, 0, instruction, 0, count);

            String[] data = new String[copy.length-count];
            System.arraycopy(copy, count, data, 0, copy.length-count);

            count = 0;

            Command[] output = new Command[instruction.length];
    
            int loop = 0;
    
            for (int i = 0; i < instruction.length; i++){
                if (instruction[i].equals("sum")){
                    output[loop] = new Sum();
                    loop++;
                }
    
                if (instruction[i].equals("product")){
                    output[loop] = new Product();
                    loop++;
                }
    
                if (instruction[i].equals("mean")){
                    output[loop] = new Mean();
                    loop++;
                }
    
                if (instruction[i].equals("max")){
                    output[loop] = new Max();
                    loop++;
                }
    
                if (instruction[i].equals("min")){
                    output[loop] = new Min();
                    loop++;
                }
    
                if (instruction[i].equals("count")){
                    output[loop] = new Count();
                    loop++;
                }
    
                if (instruction[i].equals("positive")){
                    output[loop] = new Positive();
                    loop++;
                }
    
                if (instruction[i].equals("negative")){
                    output[loop] = new Negative();
                    loop++;
                }
    
                if (instruction[i].equals("lesser")){
                    output[loop] = new Lesser(instruction[i+1]);
                    count++;
                    loop++;
                }
    
                if (instruction[i].equals("greater")){
                    output[loop] = new Greater(instruction[i+1]);
                    count++;
                    loop++;
                }
    
                if (instruction[i].equals("equal")){
                    output[loop] = new Equal(instruction[i+1]);
                    count++;
                    loop++;
                }
            }
    
            Command[] answer = new Command[instruction.length-count];
            System.arraycopy(output, 0, answer, 0, instruction.length-count);


            Command out = new CmdList(answer);

            String[] ans = out.execute(data);
            
            for(String i : ans){
                System.out.print(i + " ");
            }
        }
    }

}

class Sum implements Command{
    public String[] execute(String[] data){
        String[] output = {"0"};
        int sum = 0;

        if (data.length == 0){
            return output;
        }

        for (String i : data){
            sum = sum + Integer.parseInt(i);
        }

        output[0] = Integer.toString(sum);

        return output;
    }
}

class Product implements Command{
    public String[] execute(String[] data){
        String[] output = {"1"};
        int product = 1;
        
        if (data.length == 0){
            return output;
        }

        for (String i : data){
            product = product * Integer.parseInt(i);
        }
        
        output[0] = Integer.toString(product);

        return output;
    }
}

class Mean implements Command{
    public String[] execute(String[] data){
        String[] output = {"0"};
        double mean;
        int total = 0;
        
        if (data.length == 0){
            return output;
        }

        for(String i : data){
            total = total + Integer.parseInt(i);
        }

        mean = (double)total / data.length;
        output[0] = Double.toString(mean);

        return output;

    }
}

class Max implements Command{
    public String[] execute(String[] data){
        String[] output = {""};
        int max = Integer.parseInt(data[0]);
        
        if (data.length == 0){
            return new String[]{};
        }

        for (int i = 1; i < data.length; i++){
            if (max < Integer.parseInt(data[i])){
                max = Integer.parseInt(data[i]);
            }
        }

        output[0] = Integer.toString(max);

        return output;

    }    
}

class Min implements Command{
    public String[] execute(String[] data){
        String[] output = {""};
        int min = Integer.parseInt(data[0]);
        
        if (data.length == 0){
            return new String[]{};
        }

        for (int i = 1; i < data.length; i++){
            if (min > Integer.parseInt(data[i])){
                min = Integer.parseInt(data[i]);
            }
        }

        output[0] = Integer.toString(min);

        return output;
    }
}

class Count implements Command{
    public String[] execute(String[] data){
        String[] output = {Integer.toString(data.length)};

        return output;
    }   
}

class Positive implements Command{
    public String[] execute(String[] data){
        int count = 0;

        for(String i : data){
            if (Integer.parseInt(i) > 0){
                count++;
            }
        }

        String[] output = new String[count];
        int temp = 0;

        for (String x : data){
            if (Integer.parseInt(x) > 0){
                output[temp] = x;
                temp++;
            }
        }

        return output;
    }  
}

class Negative implements Command{
    public String[] execute(String[] data){
        int count = 0;

        for(String i : data){
            if (Integer.parseInt(i) < 0){
                count++;
            }
        }

        String[] output = new String[count];
        int temp = 0;

        for (String x : data){
            if (Integer.parseInt(x) < 0){
                output[temp] = x;
                temp++;
            }
        }

        return output;
    }  
}

class Greater implements Command{
    int compare;

    Greater(String compare){
        this.compare = Integer.parseInt(compare);
    }

    public String[] execute(String[] data){
        int count = 0;

        for(int i = 0; i < data.length; i++){
            if (Integer.parseInt(data[i]) > compare){
                count++;
            }
        }

        String[] output = new String[count];
        int temp = 0;

        for (int x = 0; x < data.length; x++){
            if (Integer.parseInt(data[x]) > compare){
                output[temp] = data[x];
                temp++;
            }
        }

        return output;
        
    }
}

class Lesser implements Command{
    int compare;

    Lesser(String compare){
        this.compare = Integer.parseInt(compare);
    }

    public String[] execute(String[] data){
        int count = 0;

        for(int i = 0; i < data.length; i++){
            if (Integer.parseInt(data[i]) < compare){
                count++;
            }
        }

        String[] output = new String[count];
        int temp = 0;

        for (int x = 0; x < data.length; x++){
            if (Integer.parseInt(data[x]) < compare){
                output[temp] = data[x];
                temp++;
            }
        }

        return output;
    }
}

class Equal implements Command{
    int compare;

    Equal(String compare){
        this.compare = Integer.parseInt(compare);
    }

    public String[] execute(String[] data){
        int count = 0;

        for(int i = 0; i < data.length; i++){
            if (Integer.parseInt(data[i]) == compare){
                count++;
            }
        }

        String[] output = new String[count];
        int temp = 0;

        for (int x = 0; x < data.length; x++){
            if (Integer.parseInt(data[x]) == compare){
                output[temp] = data[x];
                temp++;
            }
        }

        return output;
    }
}

class CmdList implements Command{
    Command[] instruction;

    CmdList(Command[] instruction){
        this.instruction = instruction;
    }

    // Command[] processCmdlist(String[] instruction){
    //     int count = 0;

    //     Command[] output = new Command[instruction.length];

    //     int loop = 0;

    //     for (int i = 0; i < instruction.length; i++){
    //         if (instruction[i].equals("sum")){
    //             output[loop] = new Sum();
    //             loop++;
    //         }

    //         if (instruction[i].equals("product")){
    //             output[loop] = new Product();
    //             loop++;
    //         }

    //         if (instruction[i].equals("mean")){
    //             output[loop] = new Mean();
    //             loop++;
    //         }

    //         if (instruction[i].equals("max")){
    //             output[loop] = new Max();
    //             loop++;
    //         }

    //         if (instruction[i].equals("min")){
    //             output[loop] = new Min();
    //             loop++;
    //         }

    //         if (instruction[i].equals("count")){
    //             output[loop] = new Count();
    //             loop++;
    //         }

    //         if (instruction[i].equals("positive")){
    //             output[loop] = new Positive();
    //             loop++;
    //         }

    //         if (instruction[i].equals("negative")){
    //             output[loop] = new Negative();
    //             loop++;
    //         }

    //         if (instruction[i].equals("lesser")){
    //             output[loop] = new Lesser(instruction[i+1]);
    //             count++;
    //             loop++;
    //         }

    //         if (instruction[i].equals("greater")){
    //             output[loop] = new Greater(instruction[i+1]);
    //             count++;
    //             loop++;
    //         }

    //         if (instruction[i].equals("equal")){
    //             output[loop] = new Equal(instruction[i+1]);
    //             count++;
    //             loop++;
    //         }
    //     }

    //     Command[] answer = new Command[instruction.length-count];
    //     System.arraycopy(output, 0, answer, 0, instruction.length-count);

    //     return answer;
    // }

    public String[] execute(String[] data){
        String[] temp = data;

        for (Command i : instruction){
            temp = i.execute(temp);
        }

        return temp;
    }
}