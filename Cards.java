public class Cards {

    private char suite;
    private String rank;
    private String pile;
    private int pileLane;
    private int laneOrder;
    private Boolean faceUp;
    private Boolean isRed;

    public Cards(char suite, String rank, String pile, int pileLane, Boolean faceUp, Boolean isRed) {
        this.suite = suite;
        this.rank = rank;
        this.pile = pile;
        this.pileLane = pileLane;
        this.faceUp = faceUp;
        this.isRed = isRed;
    }

    public String getRank() {
        return rank;
    }

    public char getSuite() {
        return suite;
    }

    public Boolean getIsRed() {
        return isRed;
    }

    public Boolean getFaceUp() { return faceUp;}

    public String toString() {
        String cardId = rank + "-of-" + suite;
        return cardId;
    }

    public void setPile(String pile) {
        this.pile = pile;
    }

    public void setFaceUp(Boolean faceUp) {
        this.faceUp = faceUp;
    }

    public void setPileLane(int pileLane) {
        this.pileLane = pileLane;
    }

}
