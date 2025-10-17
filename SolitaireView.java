import java.util.ArrayList;

public class SolitaireView {

    public static void displayCards( Stock stock, Tableau tableau, Foundation foundation, int gameScore) {


        System.out.println("DISPLAY TABLEAU LANES\n");
        //Display Tableau Cards
        int lCount = 1;
        for(ArrayList<Cards> lane : tableau.getTableauLanes()) {

            System.out.printf("Lane: %d ", lCount++);

            for(Cards tableauCard : lane) {

                if((tableauCard.getFaceUp())) {
                    System.out.print("[");
                    System.out.printf("%s",tableauCard);
                    System.out.print("]");
                } else {
                    System.out.print("[");
                    System.out.print("Face Down");
                    System.out.print("]");
                }
            }
            System.out.println();
        }

        System.out.println("\n\nDISPLAY FOUNDATION PILE CARDS\n");

        char[] suites = new char[]{'H', 'S', 'D', 'C'};

        for(int i=0; i <4; i++) {
            for(int j=0; j < foundation.getFoundationSuit(suites[i]).size(); j++) {
                System.out.printf("%s: [%s]",suites[i], foundation.getFoundationSuit(suites[i]).get(j));
            }
            System.out.println();
        }




//        System.out.println("\n\nDISPLAY STOCK PILE CARDS\n");
//
//        System.out.printf("Stock Pile Card Count: %d\n", stock.getStockSize());
//        for(Cards stockCard : stock.getStock()) {
//            if((stockCard.getFaceUp())) {
//                System.out.print("[");
//                System.out.printf("%s",stockCard);
//                System.out.print("]");
//            } else {
//                System.out.print("[");
//                System.out.print("Face Down");
//                System.out.print("]");
//            }
//
//        }

        System.out.printf("Current Score: %d\n", gameScore);
        System.out.printf("\nCurrent Random Card To Draw: %s",stock.getARandCard());

        System.out.println("\nType Q to quit");
        System.out.println("Type D to uncover a new card from the draw pile");
        System.out.println("To move 1 card between piles enter: <label1> <label2> E.g. 5 6");
        System.out.println("To move x cards between piles enter: <label1> <label2> <2-digit-number> E.g P 3 04");
    }
}
