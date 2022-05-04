import tester.*;

interface Tweet{
    public boolean isReplyTo(Tweet other);
    public int totalLikes();
    public String allAuthors();
    public boolean textAppearsOnThread(String Text);
    public int countPopularTweets(int minNumLikes);
}

class User{
    String username;
    String displayName;
    int numberOfFollowers;

    User(String username, String displayName, int numberOfFollowers){
        this.username = username;
        this.displayName = displayName;
        this.numberOfFollowers = numberOfFollowers;
    }

    String toText(){
        return this.displayName + " @" + this.username;
    }
}

class TextTweet implements Tweet{
    String contents;
    User author;
    int likes;

    TextTweet(String contents, User author, int likes){
        this.contents = contents;
        this.author = author;
        this.likes = likes;
    }

    public boolean isReplyTo(Tweet other){
        return false;
    }

    public int totalLikes(){
        return this.likes;
    }

    public String allAuthors(){
        return this.author.username;
    }

    public boolean textAppearsOnThread(String Text){
        if (this.contents.contains(Text)){
            return true;
        }
        return false;
    }

    public int countPopularTweets(int minNumLikes){
        if (this.likes > minNumLikes){
            return 1;
        }
        return 0;
    }


}

class ReplyTweet implements Tweet{
    String contents;
    User author;
    int likes;
    Tweet replyTo;

    ReplyTweet(String contents, User author, int likes, Tweet replyTo){
        this.contents = contents;
        this.author = author;
        this.likes = likes;
        this.replyTo = replyTo;
    }

    public boolean isReplyTo(Tweet other){
        if (replyTo == other){
            return true;
        }
        return false;
    }

    public int totalLikes(){
        return this.likes + replyTo.totalLikes();
    }

    public String allAuthors(){
        return this.author.username + ";" + replyTo.allAuthors();
    }

    public boolean textAppearsOnThread(String Text){
        if (this.contents.contains(Text) || this.replyTo.textAppearsOnThread(Text)){
            return true;
        }
        return false;
    }

    public int countPopularTweets(int minNumLikes){
        if (this.likes > minNumLikes){
            return 1 + this.replyTo.countPopularTweets(minNumLikes);
        }
        return 0 + this.replyTo.countPopularTweets(minNumLikes);
    }

}

class Tweets{
    User greg = new User("gregory_miranda", "Greg Miranda", 9999);
    User sanmi = new User("sanmi_adeleye", "Sanmi Adeleye", 999);
    User sangeetha = new User("sangeetha_viswanathan_sakthivel", "Sangeetha Viswanathan Sakthivel", 1000000);
    Tweet t1 = new TextTweet("Java 17 has a cool feature called records", this.sanmi, 77);
    Tweet t2 = new ReplyTweet("Hmm I wonder if we could use it for CSE11", this.greg, 12, this.t1);
    Tweet t3 = new ReplyTweet("Thought about this more, probably not yet, too new.", this.greg, 73, this.t2);
    Tweet t4 = new ReplyTweet("Yeah, good point. Maybe in 2022.", this.sanmi, 10, this.t3);
    Tweet t5 = new ReplyTweet("Yeah... I don't want to rewrite the book right this minute", this.sangeetha, 1005, this.t2);

    void testIsReplyTo(Tester t) {
        t.checkExpect(this.t1.isReplyTo(this.t2), false);
        t.checkExpect(this.t2.isReplyTo(this.t1), true);
        t.checkExpect(this.t5.isReplyTo(this.t2), true);
        t.checkExpect(this.t2.isReplyTo(this.t2), false);
        t.checkExpect(this.t4.isReplyTo(this.t3), true);
    }

    void testTotalLikes(Tester t) {
        t.checkExpect(this.t5.totalLikes(), 1005 + 12 + 77);
        t.checkExpect(this.t4.totalLikes(), 10 + 73 + 12 + 77);
        t.checkExpect(this.t1.totalLikes(), 77);
    }

    void testAllAuthors(Tester t) {
        t.checkExpect(this.t1.allAuthors(), "sanmi_adeleye");
        t.checkExpect(this.t2.allAuthors(), "gregory_miranda;sanmi_adeleye");
        t.checkExpect(this.t3.allAuthors(), "gregory_miranda;gregory_miranda;sanmi_adeleye");
        t.checkExpect(this.t5.allAuthors(), "sangeetha_viswanathan_sakthivel;gregory_miranda;sanmi_adeleye");
    }

    void testTextAppearsOnThread(Tester t) {
        t.checkExpect(this.t1.textAppearsOnThread("sanmi_adeleye"), false);
        t.checkExpect(this.t1.textAppearsOnThread("2022"), false);
        t.checkExpect(this.t1.textAppearsOnThread("cool"), true);
        t.checkExpect(this.t4.textAppearsOnThread("wonder"), true);
        t.checkExpect(this.t4.textAppearsOnThread("Java"), true);
        t.checkExpect(this.t4.textAppearsOnThread("rewrite"), false);
        t.checkExpect(this.t4.textAppearsOnThread("2022"), true);
    }

    void testCountPopularTweets(Tester t) {
        t.checkExpect(this.t1.countPopularTweets(100), 0);
        t.checkExpect(this.t2.countPopularTweets(10), 2);
        t.checkExpect(this.t3.countPopularTweets(70), 2);
        t.checkExpect(this.t5.countPopularTweets(0), 3);
    }

   //Own Testing
   User pluto = new User("pluto_young", "Pluto Young", 10);
   User jason = new User("jason_gu", "Jason Gu", 0);
   User jack = new User("jack_jia", "Jack Jia", 999999999);

   Tweet tw1 = new TextTweet("Java the best programming langauge in the world!", this.jason, 64);
   Tweet tw2 = new ReplyTweet("Hmm, it is not true! It should be python", this.pluto, 99, this.tw1);
   Tweet tw3 = new ReplyTweet("Not really, bro. Java is not that powerful.", this.jack, 1000, this.tw1);
   Tweet tw4 = new ReplyTweet("Well, why not?????", this.jason, 20, this.tw3);
   Tweet tw5 = new ReplyTweet("Because it is java.", this.jack, 10000, this.tw4);

   void testIsReplyTo1(Tester t) {
       t.checkExpect(this.tw3.isReplyTo(this.tw4), false);
       t.checkExpect(this.tw5.isReplyTo(this.tw4), true);
   }

   void testTotalLikes1(Tester t) {
       t.checkExpect(this.tw5.totalLikes(), 10000 + 20 + 1000 + 64);
       t.checkExpect(this.tw2.totalLikes(), 99 + 64);
   }

   void testAllAuthors1(Tester t) {
       t.checkExpect(this.tw2.allAuthors(), "pluto_young;jason_gu");
       t.checkExpect(this.tw5.allAuthors(), "jack_jia;jason_gu;jack_jia;jason_gu");
   }

   void testTextAppearsOnThread1(Tester t) {
       t.checkExpect(this.tw4.textAppearsOnThread("why not"), true);
       t.checkExpect(this.tw2.textAppearsOnThread("Hmmm"), false);
   }

   void testCountPopularTweets1(Tester t) {
       t.checkExpect(this.tw2.countPopularTweets(99), 0);
       t.checkExpect(this.tw5.countPopularTweets(20), 3);
   }

}