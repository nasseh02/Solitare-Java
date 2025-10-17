import java.util.ArrayList;

public class Tableau {

    private ArrayList<Cards>[] tableau = new ArrayList[7];

//    private ArrayList<Cards> lane1 = new ArrayList<Cards>();
//    private ArrayList<Cards> lane2 = new ArrayList<Cards>();
//    private ArrayList<Cards> lane3 = new ArrayList<Cards>();
//    private ArrayList<Cards> lane4 = new ArrayList<Cards>();
//    private ArrayList<Cards> lane5 = new ArrayList<Cards>();
//    private ArrayList<Cards> lane6 = new ArrayList<Cards>();
//    private ArrayList<Cards> lane7 = new ArrayList<Cards>();


   // private Cards[][] tableau = new Cards[7][52];

    //Instantiate Tableau piles
    public Tableau() {
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = new ArrayList<>();
        }
    }

    public Cards getLastCard(int laneNo) {
        ArrayList<Cards> cardLane = tableau[laneNo-1];
        int laneSize = cardLane.size();

        if(laneSize == 0) {
            return null;
        } else {
            Cards lastCard;
            lastCard = cardLane.get(laneSize-1);
            return lastCard;
        }

    }

    public ArrayList<Cards>[] getTableauLanes() {
        return tableau;
    }

    public ArrayList<Cards> getTableauLane(int laneNo) {
        return tableau[laneNo-1];
    }


    public void setCard(int laneNo, Cards addedCard, Boolean faceUp) {

        tableau[laneNo].add(addedCard);
        addedCard.setFaceUp(faceUp);

    }

    public void addCard(int toLane, Cards addedCard) {
        ArrayList<Cards> laneSelected = tableau[toLane-1];
        laneSelected.add(addedCard);
        addedCard.setFaceUp(true);
    }

    public void removeCardFromTableau(int fromLane, Cards cardToRemove) {
        ArrayList<Cards> laneSelected = tableau[fromLane-1];
        laneSelected.remove(cardToRemove);
    }





}
