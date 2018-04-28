import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by abdll on 12/21/2017.
 */
public class TweetoArrayList {
    ArrayList<Tweeto> TweetsArrayList;// the tweets from the read file will be  stored in this array list
    private int numberOfMatchedtweets;//the number of matched tweets that have the same tweet id and the same tweet text (substring)

    /*
       the default constructor of the class
    */
    public TweetoArrayList() {
        TweetsArrayList = new ArrayList<>();
    }
    //setters and getters
    public ArrayList<Tweeto> getTweetsArrayList() {
        return TweetsArrayList;
    }
    //not used
    public void setTweetsArrayList(ArrayList<Tweeto> tweetsArrayList) {
        this.TweetsArrayList = tweetsArrayList;
    }


    public int getNumberOfMatchedtweets() {
        return numberOfMatchedtweets;
    }
    //not used
    public void setNumberOfMatchedtweets(int numberOfMatchedtweets) {
        this.numberOfMatchedtweets = numberOfMatchedtweets;
    }

    /*
    this method is responsible of  reading the content of the tweets file
    then split the id and the text of the tweet and put them in the created array list
    */
    public void readTweetsFile(String filename) {
        try {
            //trys to read from
            Scanner Reader = new Scanner(new File(filename));
            while (Reader.hasNext()) {
                Reader.next();
                String fileContent = "";
                String id = Reader.next();
                String temp = Reader.next();
                while (true) {
                    if (temp.contains("@") || temp.contains("RT")) {
                        temp = Reader.next();
                    } else {
                        break;
                    }
                }
                fileContent = temp;
                String last = Reader.nextLine();
                while (!last.contains("2016")) {//2016 is the year of the tweet and indicate the end of the tweet fileContent
                    last = last + Reader.nextLine();
                }
                fileContent = fileContent + last.substring(0, last.indexOf("Wed Dec") - 1);
                getTweetsArrayList().add(new Tweeto(id, fileContent));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Tweets file not found");
        }
    }

    /* this method is responsible to search  give the number of the the matched tweets
      */
    public Tweeto search(String UserID, String KEYword) {
        UserID = "@" + UserID;
        Iterator<Tweeto> ALI = getTweetsArrayList().iterator();
        while (ALI.hasNext()) {
            Tweeto t = ALI.next();
            if (t.getTweet().toLowerCase().contains(KEYword.toLowerCase()) && t.getID().toLowerCase().startsWith(UserID.toLowerCase()))
                numberOfMatchedtweets++;
        }
        return null;
    }
}
