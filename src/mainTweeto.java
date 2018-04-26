/**
 * Created by abdll on 12/21/2017.
 */
import java.util.Scanner;
public class mainTweeto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);// scanner to read the input from the user
        String filename = scanner.next();// the file name that the user want to read
        int dataStructureType = scanner.nextInt();// used determine the data type
        String userID = scanner.next();// user tweeter id
        String keyString = scanner.next();// the string which is wanted to be found
        double timeI = System.currentTimeMillis();// calculate the time needed to find the keyString
        /*
        * this switch case is used to determine the wanted data structure
        * */
        switch (dataStructureType) {
            case 1:// this case will be entered if the user have chosen the array list data structure
                TweetoArrayList tweetoArrayList = new TweetoArrayList();
                tweetoArrayList.readTweetsFile(filename);
                tweetoArrayList.search(userID, keyString);
                System.out.println("Total Tweets: " + tweetoArrayList.getNumberOfMatchedtweets());
                break;
            case 2:// this case will be entered if the user have chosen the hash table data structure
                TweetoHashTable tweetoHashTable = new TweetoHashTable();
                tweetoHashTable.readSLL(filename);
                tweetoHashTable.search(userID, keyString);
                int position  = tweetoHashTable.hash("@" + userID);
                System.out.println("Total Tweets: " + tweetoHashTable.getHT()[position].getCount());
                break;
           }
        double time = System.currentTimeMillis() - timeI;
        System.out.println("Total Time: " + time/1000.0 + " seconds");


}
