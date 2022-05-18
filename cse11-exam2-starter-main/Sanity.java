// Provided Sanity Tests

import tester.*;

class ProvidedArrayTests {
    ExampleArrays ea = new ExampleArrays();

    void testAverageWithThreshold(Tester t) {

        Number[] test1 = { new WholeNumber(1) , new Fraction(4, 2), new WholeNumber(3) };
        Number threshold = new WholeNumber(1);

        t.checkExpect(ea.averageWithThreshold(test1,threshold).toDouble(), 2.5);
	}

    // Uncomment when you finished implementing the methods/classes 
    void testFindGoodPairs(Tester t) {
        Pair[] p1 = {new Pair(1,2), new Pair(4,3), new Pair(5,6)};
        Pair[] p2 = {new Pair(2,2), new Pair(4,3), new Pair(7,6)};

        Pair[] expect_p1= {new Pair(1, 2), new Pair(5, 6)};
        Pair[] result_p1 = ea.findGoodPairs(p1);

        Pair[] expect_p2= {};
        Pair[] result_p2 = ea.findGoodPairs(p2);
        
        t.checkExpect(result_p1, expect_p1); // Basic test
        t.checkExpect(result_p2, expect_p2); // Empty test

    }

    void testMergePairs(Tester t) {
        Pair[] p1 = {new Pair(1,4), new Pair(4,6)};
        Pair[] p2 = {new Pair(2,3), new Pair(1,5)};        

        Pair[] expect_p1_p2 = {new Pair(1, 4), new Pair(1, 6)};
        Pair[] result_p1_p2 = ea.mergePairs(p1, p2);

        t.checkExpect(result_p1_p2, expect_p1_p2);
    }

    void testMergePairs2(Tester t) {
        Pair[] p1 = {new Pair(1,4), new Pair(10,15)};
        Pair[] p2 = {new Pair(5,6), new Pair(4,5)};        

        Pair[] expect_p1_p2 = {new Pair(1, 6), new Pair(4, 15)};
        Pair[] result_p1_p2 = ea.mergePairs(p1, p2);

        t.checkExpect(result_p1_p2, expect_p1_p2);
    }  
}

// Uncomment when you finished implementing the methods/classes
class ProvidedTweetTests {
    User u1 = new User("greg", "Greg", 12);
    User u2 = new User("greg2", "Greg2", 12);

    Tweet t1 = new TextTweet("We're already halfway through with the quarter", u1, 12, "05-2022");
    Tweet t2 = new ReplyTweet("Yeah, can you believe it. It still feel like the beginning of the quarter", u2, 13, "04-2021", t1);

    void testLatestTweetOnThread(Tester t) {
        t.checkExpect(t2.latestTweetOnThread(), t1);
    }

    void testLongestUsernameOnThread(Tester t) {
        t.checkExpect(t2.longestUsernameOnThread(), u2);
    }
}