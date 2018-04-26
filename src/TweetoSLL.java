/**
 * Created by abdll on 12/21/2017.
 */
public class TweetoSLL {
	
    public Tweeto head;//Marks the head of the Singly Linked List
    public int size;//Keeps track of the size of the Singly Linked List
    private int numOfMatchedTweets;//numOfMatchedTweetss the number of times the searched string was found.
	
    public TweetoSLL() {}
	
    public Tweeto getHead() {
        return head;
    }
	
    public void setHead(Tweeto head) {
        this.head = head;
    }
	
    public int getSize() {
        return size;
    }
	
    public void setSize(int size) {
        this.size = size;
    }
	
    public int getNumOfMatchedTweets() {
        return numOfMatchedTweets;
    }
	
    public void setNumOfMatchedTweets(int numOfMatchedTweets) {
        this.numOfMatchedTweets = numOfMatchedTweets;
    }
	
    public Tweeto Search(String User, String Keyword) {
        Tweeto temp = head;
	//Iterating through the SLL to find matching results.
        while (temp != null) {
            //Checks if the searched string is included in the tweet & if the Tweet user's ID starts with the specified letter
            if (temp.getTweet().toLowerCase().contains(Keyword.toLowerCase()) && temp.getID().toLowerCase().startsWith(User.toLowerCase()))
                numOfMatchedTweets++;
            temp = temp.getNext();
        }
        return temp;
    }

    //Inserts a tweet into the Singly Linked List
    public void insert(Tweeto tweet) {
        //if SLL is empty it adds the tweet as head and returns
        if (head == null) {
            head = tweet;
            tweet.setNext(null);
            size++;
            return;
        }
        //if it is not empty, it adds the the tweet to the beginning of the list as a head
        tweet.setNext(head);
        head = tweet;
        size++;
        return;
    }
	
    @Override
    public String toString() {
        String totalTweets = "";
        Tweeto temp = head;
        //iterates through the SLL adding each tweet to the string totalTweets
        while (temp != null) {
            totalTweets = totalTweets + temp;
            temp = temp.getNext();
        }
        //adds an empty line after collected tweets and doesn't add a line if list is empty
        if (head != null)
            totalTweets += "\n";
        return totalTweets;
    }
}
