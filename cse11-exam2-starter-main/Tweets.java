// EXAM INSTRUCTIONS:
// All of your code for Task 2 goes in this file.
// Add new method headers and implementations as appropriate to these classes
// Add examples to the ExamplesTweets class.
import tester.*;

interface Tweet {
    // Task 2.1: latestTweetOnThread
    // Your code here
    Tweet latestTweetOnThread();

    // Task 2.2: longestUsernameOnThread
    // Your code here
    User longestUsernameOnThread();

    // Provided methods, DO NOT MODIFY
    User getAuthor();
    String getContents();
    int getLikes();
    Tweet getReplyTo();
    String getDate();
}

// User class, DO NOT MODIFY
class User {
    String username, displayName;
    int followers;
    User(String username, String displayName, int followers) {
        this.username = username;
        this.displayName = displayName;
        this.followers = followers;
    }

    String toText() {
        return username + " @" + displayName;
    }
}

class TextTweet implements Tweet {
    User author;
    String contents;
    String date;
    int likes;

    TextTweet(String contents, User author, int likes, String date) {
        this.author = author;
        this.contents = contents;
        this.likes = likes;
        this.date = date;
        
    }

    // Task 2.1: latestTweetOnThread
    // Your code here
    public Tweet latestTweetOnThread(){
        return this;
    }

    // Task 2.2: longestUsernameOnThread
    // Your code here
    public User longestUsernameOnThread(){
        return this.author;
    }

    // Provided methods, DO NOT MODIFY
    public User getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public int getLikes() {
        return likes;
    }

    public Tweet getReplyTo() {
        return null;
    }
    public String getDate(){
        return date;
    }

}

class ReplyTweet implements Tweet {
    User author;
    String contents;
    int likes;
    String date; 
    Tweet replyTo;

    ReplyTweet(String contents, User author, int likes, String date, Tweet replyTo) {
        this.author = author;
        this.contents = contents;
        this.likes = likes;
        this.date = date;
        this.replyTo = replyTo;
    }

    // Task 2.1: latestTweetOnThread
    // Your code here
    public Tweet latestTweetOnThread(){
        Tweet output = this;
        Tweet next = this.getReplyTo();

        while (true){
            if(next instanceof TextTweet){
                String[] ownDate = output.getDate().split("-");
                String[] replyDate = next.getDate().split("-");

                if (Integer.parseInt(ownDate[1]) > Integer.parseInt(replyDate[1])){
                    return output;
                }
                else if (Integer.parseInt(ownDate[1]) < Integer.parseInt(replyDate[1])){
                    return next;
                }
                else{
                    if(Integer.parseInt(ownDate[0]) >= Integer.parseInt(replyDate[0])){
                        return output;
                    }
                    else{
                        return next;
                    }
                }
            }
            else if (output instanceof ReplyTweet){
                String[] ownDate = output.getDate().split("-");
                String[] replyDate = next.getDate().split("-");

                if (Integer.parseInt(ownDate[1]) > Integer.parseInt(replyDate[1])){
                    next = next.getReplyTo();
                }
                else if (Integer.parseInt(ownDate[1]) < Integer.parseInt(replyDate[1])){
                    output = next;
                    next = next.getReplyTo();
                }
                else{
                    if(Integer.parseInt(ownDate[0]) >= Integer.parseInt(replyDate[0])){
                        next = next.getReplyTo();
                    }
                    else{
                        output = next;
                        next = next.getReplyTo();
                    }
                }
            }
            
        }
    }

    // Task 2.2: longestUsernameOnThread
    // Your code here
    public User longestUsernameOnThread(){
        Tweet output = this;
        Tweet next = this.getReplyTo();

        while(true){
            if (next instanceof TextTweet){
                if (output.getAuthor().username.length() >= next.getAuthor().username.length()){
                    return output.getAuthor();
                }
                else{
                    return next.getAuthor();
                }
            }
            else if (next instanceof ReplyTweet){
                if (output.getAuthor().username.length() >= next.getAuthor().username.length()){
                    next = next.getReplyTo();
                }
                else{
                    output = next;
                    next = next.getReplyTo();
                }
            }
        }
    }

    // Provided methods, DO NOT MODIFY
    public User getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public int getLikes() {
        return likes;
    }

    public Tweet getReplyTo() {
        return replyTo;
    }

    public String getDate(){
        return date;
    }
}

class ExampleTweets {
    // Video Task
    // Your code here
    Tweet t1 = new TextTweet("I am inevitable.", new User("jack", "Jack", 3200), 230, "01-2022");
    Tweet t2 = new ReplyTweet("No, you are not.", new User("jason", "Jason", 1), 3, "09-2021", t1);
    Tweet t3 = new ReplyTweet("You from the past?", new User("tom", "Tom", 0), 99, "10-2022", t2);

    Tweet test1 = t1.latestTweetOnThread();
    Tweet test2 = t2.latestTweetOnThread(); 
    Tweet test3 = t3.latestTweetOnThread();

    /*
    Class            Method                      reference                    return value  
    TextTweet        latestTweetOnThread()         :1                              :1  
    ReplyTweet       latestTweetOnThread()         :2                              :1      
    ReplyTweet        latestTweetOnThread()        :3                              :3

    
    */


}
    
