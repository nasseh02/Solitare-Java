import java.lang.reflect.Array;
import java.util.ArrayList;

public class Foundation {

    private ArrayList<Cards> hearts = new ArrayList<>();
    private ArrayList<Cards> diamonds = new ArrayList<>();
    private ArrayList<Cards> clubs = new ArrayList<>();
    private ArrayList<Cards> spades = new ArrayList<>();

    private int hCount = hearts.size();;
    private int dCount= clubs.size();
    private int cCount = diamonds.size();
    private int sCount = spades.size();

    public Foundation() {

    }

    public Cards getTopCard(char suite) {

        if(suite == 'H') {
            if(hCount != 0) {
                return hearts.get(hCount-1);
            } else {
                return null;
            }

        } else if(suite == 'S') {
            if(sCount != 0) {
                return spades.get(sCount-1);
            } else {
                return null;
            }

        } else if(suite == 'D') {
            if(dCount != 0) {
                return diamonds.get(dCount-1);

            } else {
                return null;
            }

        } else if(suite == 'C') {
            if(cCount != 0) {
                return clubs.get(cCount-1);

            } else {
                return null;
            }

        }

        return null;

    }

    public int getSuiteCount(char suite) {

        if(suite == 'H') {
            return hCount;
        } else if (suite == 'D') {
            return dCount;
        } else if (suite == 'C') {
            return cCount;
        } else if(suite == 'S') {
            return sCount;
        }

        return 0;
    }

    public void addCardToSuite(Cards addedCard, char suite) {

        if(suite == 'H') {
            hearts.add(addedCard);
        } else if(suite == 'S') {
            spades.add(addedCard);
        } else if(suite == 'D') {
            diamonds.add(addedCard);
        } else if(suite == 'C') {
            clubs.add(addedCard);
        }
        addedCard.setPile("Foundation");
    }

    public ArrayList<Cards> getFoundationSuit(char suit) {
        if(suit == 'H') {
            return hearts;

        } else if(suit == 'C') {
            return  clubs;

        } else if (suit == 'D') {
            return diamonds;

        } else if (suit == 'S') {
            return spades;

        } else {

            return null;

        }

        }

    }





