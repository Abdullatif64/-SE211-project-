/**
 * Created by abdll on 12/21/2017.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TweetoHashTable {

   private TweetoSLL[] HashTable; //An array holding the hash table
    private int sizeOfTheHashTable; //stores the current number of Tweeto objects

    public TweetoHashTable() {
        HashTable = new TweetoSLL[37]; // we chose 37 to guarantee that we contain all the numbers and the letters 
        int length = HashTable.length; // initialize the length of the hash table
        for (int i = 0; i < length; i++) {
            HashTable[i] = new TweetoSLL();
        }
    }

    public TweetoSLL[] getHT() {
        return HashTable;
    }

    public void setHT(TweetoSLL[] HT) {
        this.HashTable = HT;
    }

    public int getSize() {
        return sizeOfTheHashTable;
    }

    public void setSize(int size) {
        this.sizeOfTheHashTable = size;
    }

    /**
     * Appropriate information to be returned
     */
    public String toString() {
        String HashTableText = "";
        for (TweetoSLL t : HashTable) {
            HashTableText = HashTableText + t.toString();
        }
        return HashTableText;
    }

    /**
     * this method is responsible of reading the content of the tweets file
     * then split the id and the text of the tweet and put them in the created hash table of SLLs
     */
    public void readSLL(String filename) {
        try {
            Scanner reader = new Scanner(new File(filename));
            while (reader.hasNext()) {
                reader.next();//skips the unused line
                String content = "";//saves the resulted tweet
                String id = reader.next();//saves the tweeter id
                String temp = reader.next();//temporary object to format the tweet
                //processes the tweet according to the specefies criteria by ignoring tweets including "@" or "RT"
                while (true) {
                    if (temp.contains("@") || temp.contains("RT")) {
                        temp = reader.next();
                    } else {
                        break;
                    }
                }
                content = temp;
                //some tweets had extra spaces, this code filters these spaces
                String last = reader.nextLine();
                while (!last.contains("2016")) {
                    last = last + reader.nextLine();
                }
                //after filtering the space, the tweet is added to content
                content = content + last.substring(0, last.indexOf("Wed Dec") - 1);
                int posistion = hash(id);
                insert(new Tweeto(id, content), posistion);
            }
        } catch (FileNotFoundException FNFE) {
            System.out.println("Tweets file not found");
        }
    }


    /**
     * This hash method provided to hash the input value to the appropriate position in the hash table
     * The function is f(x) = x mod 37 for the letters and numbers, but we had a problem that there is a
     * conflict between numbers and letters, so we added one in case we have numbers f(x) = x mod 37+1.
     */
    public static int hash(String ID) {
        int PositionInTable; // position in the table to be returned
        int FirstLetterOrNumber = ID.toLowerCase().charAt(1);
        int HashedPosition = (FirstLetterOrNumber % 37); // using the function
        if (FirstLetterOrNumber > 96) // if it is bigger than 96 it is a letter according to ascii code
            PositionInTable = HashedPosition;
        else // if it is less than 96 it is a number according to ascii code
            PositionInTable = HashedPosition + 1;

        return PositionInTable;
    }

    /**
     * This method Insert, it inserts Tweeto in appropriate Position
     */
    public void insert(Tweeto Tweeto, int Position) {
        this.sizeOfTheHashTable++;
        getHT()[Position].insert(Tweeto);
    }

    /**
     * Searches for keyword in a Tweet for any user.
     * The user starts with @
     */
    public Tweeto search(String User, String Kewword) {
        User = "@" + User;
        int p = hash(User);
        return getHT()[p].Search(User, Kewword);
    }
}
