// import tester.*;
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

class Tweet{
    String content;
    User author;
    int likes;
    String id;

    Tweet(String content, User author, int likes, String id){
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.id = id;
    }

    boolean longerThan(Tweet other){
        if (this.content.length() > other.content.length()){
            return true;
        }
        return false;
    }

    boolean moreLikes(Tweet other){
        if (this.likes > other.likes){
            return true;
        }
        return false;
    }

    String toText(){
        return this.author.toText() + " : " + this.content + " : " + Integer.toString(likes) + " Likes";
    }

    String toLink(){
        return "https://twitter.com/" + this.author.username + "/status/" + this.id;
    }
}

class ExampleTweets{

    User UCSDEngineering = new User("UCSDJacobs", "UCSD Engineering", 13000);
    User cse = new User("ucsd_cse", "UCSD CSE", 2143);
    User Prather = new User("kprather88", "Kimberly Prather, Ph.D.", 69000);

    //Tweet link: https://twitter.com/UCSDJacobs/status/1503513964893724674
    //the content related to What's happening could not be represented
    Tweet UCSDEngineeringTweet1 = new Tweet("Happy Pi Day everyone!", this.UCSDEngineering, 3, "1503513964893724674");

    //Tweet link: https://twitter.com/UCSDJacobs/status/1493313971150938113
    //the picture of the user could not be represented
    Tweet UCSDEngineeringTweet2 = new Tweet("And if you really want to wow your Valentine (or your friends and family), Janelle talks your through how to make holographic chocolate:", this.UCSDEngineering, 4, "1493313971150938113");

    //Tweet link: https://twitter.com/ucsd_cse/status/1484639576496885762
    //the tweet content that is mentioned by other tweets could not be represented
    Tweet cseTweet = new Tweet("Great to have you as our guest!", this.cse, 5, "1484639576496885762");

    //Tweet link: https://twitter.com/kprather88/status/1511120831212007425
    //the list of user name that likes the tweet could be not be represented
    Tweet PratherTweet = new Tweet("Who knew?", this.Prather, 19, "1511120831212007425");

    //User toText test
    String UserToTextTest1 = this.UCSDEngineering.toText();
    String UserToTextTest2 = this.cse.toText();

    //longerThan test
    boolean longerThanTest1 = cseTweet.longerThan(PratherTweet);
    boolean longerThanTest2 = UCSDEngineeringTweet1.longerThan(PratherTweet);

    //moreLikes test
    boolean moreLikesTest1 = PratherTweet.moreLikes(cseTweet);
    boolean moreLikesTest2 = UCSDEngineeringTweet2.moreLikes(UCSDEngineeringTweet1);

    //toText test
    String toTextTest1 = UCSDEngineeringTweet1.toText();
    String toTextTest2 = cseTweet.toText();

    //toLink test
    String toLinkTest1 = UCSDEngineeringTweet2.toLink();
    String toLinkTest2 = PratherTweet.toLink();

    // boolean test(Tester t){
    //     return t.checkExpect(UCSDEngineeringTweet1.toText(), "UCSD Engineering @UCSDJacobs : Happy Pi Day everyone! : 3 Likes");
    // }

}
