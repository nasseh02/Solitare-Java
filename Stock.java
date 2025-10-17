import java.util.ArrayList;
import java.util.Random;

public class Stock {

    private ArrayList<Cards> stock = new ArrayList<>();
    private Cards aRandCard;

    //Initiate Stock Pile
    public Stock(int cardStart, Cards[] deck) {
        for (int i = cardStart; i < 52; i++) {
            stock.add(deck[i]);
        }

    }

    //Get Stock Size
    public int getStockSize() {
        return stock.size();
    }

    //Get Stock cards
    public ArrayList<Cards> getStock() {
        return stock;
    }

    public Cards getARandCard() {
        return aRandCard;
    }

    //Get a Random Card
    public void getRandomCard() {
        int size = stock.size();
        Random rand = new Random();
        int randNo;
        randNo = rand.nextInt(size);
        Cards randCard;
        randCard = stock.get(randNo);

        aRandCard = randCard;
    }

    //Remove card from Stock Pile
    public Boolean removeCardFromStock(Cards cardToRemove) {
        if(stock.contains(cardToRemove)) {
            stock.remove(cardToRemove);
            cardToRemove.setFaceUp(true);
            return true;
        } else {
            return false;
        }

    }


}
