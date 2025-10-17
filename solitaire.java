import java.util.*;

public class solitaire {
    public static void main(String[] args) {

        //Game Status
        Boolean isGameFinished = false;
        int gameScore =0;

        //Suites, ranks and pile labels
        char[] suites = new char[]{'H', 'S', 'D', 'C'};
        //String[] ranks = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        ArrayList<String> ranks = new ArrayList<>();
        ranks.add("A");
        ranks.add("2");
        ranks.add("3");
        ranks.add("4");
        ranks.add("5");
        ranks.add("6");
        ranks.add("7");
        ranks.add("8");
        ranks.add("9");
        ranks.add("10");
        ranks.add("J");
        ranks.add("Q");
        ranks.add("K");


        String[] piles = new String[]{"Foundation", "Tableau", "Stock"};

        Cards[] deck = new Cards[52]; //Instantiate Card Deck.
        Tableau tableauPile = new Tableau(); //Instantiate Tableau Pile.
        Foundation foundationPile = new Foundation(); //Instantiate Foundation Pile




        //Define a deck of 52 cards
        int id=0;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 13; k++) {
                    if(suites[j] == 'C' || suites[j] == 'S') {
                        deck[id] = new Cards(suites[j], ranks.get(k), "Stock", 0, false,false);
                    } else {
                        deck[id] = new Cards(suites[j], ranks.get(k), "Stock", 0, false,true);
                    }
                    //System.out.println(deck[id].toString());
                    id++;
                }
            }


        int lastCardDealt = 0; //keep track of cards dealt in deck or tableau

        //Set up Tableau lanes
        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 7; j++) {

                if (j < i) { //skip over lanes with face up cards
                    continue;
                } else if (j == i) { //add a face up card to after a lane with a face up card
                    tableauPile.setCard(j, deck[lastCardDealt++], true);
                } else if (j > i) { //add a face down card to other lanes
                    tableauPile.setCard(j, deck[lastCardDealt++], false);
                }


            }
        }

        Stock stockPile = new Stock(lastCardDealt, deck);

//        System.out.println("\n\nDISPLAY TABLEAU\n\n");
//
//        //Display Tableau Cards
//        for(ArrayList<Cards> lane : tableauPile.getTableauLanes()) {
//            for(Cards tableauCard : lane) {
//                System.out.printf("[");
//                System.out.printf(" %s,",tableauCard.toString());
//                System.out.printf("(%s)",tableauCard.getFaceUp());
//                System.out.printf(" ]");
//            }
//            System.out.println();
//        }

        stockPile.getRandomCard(); //to generate a random card
        Cards randomStock = stockPile.getARandCard(); // to access it
        char randomStockSuit = randomStock.getSuite();

        //Prompt to enter a command
        while(!isGameFinished) {

            SolitaireView.displayCards(stockPile, tableauPile, foundationPile, gameScore);

            //P = stock pile
            //1 = 1st Tableau lane, etc.
            //D = diamond suite pile
            //H = heart suite pile
            //C = clubs suite pile
            //S = spades suite pile



            Scanner userInput = new Scanner(System.in);
            System.out.print("Enter in a move: ");

            String userInputRx = userInput.nextLine();

            String singleCard = "[pP1234567] [dhcsDHCS1234567]";
            String manyCards = "[1234567] [1234567] [0-9][0-9]";



            if (userInputRx.matches("[qQ]")) {

                isGameFinished = true;
                break;

            } else if (userInputRx.matches("[dD]")) {

                 stockPile.getRandomCard();
                 randomStock = stockPile.getARandCard();
                 randomStockSuit = randomStock.getSuite();
                System.out.printf("You drew a %s from the stock pile!\n\n",randomStock);

            } else if(userInputRx.matches(singleCard)) {

                //System.out.println("Regex was detected!");
                char pile1 = Character.toUpperCase(userInputRx.charAt(0));
                char pile2 = Character.toUpperCase(userInputRx.charAt(2));


                if(userInputRx.substring(0,1).matches("[pP]")) { //Check if Drawing from Stock Pile

                    //Get a random card from stock pile, and check if card fits in lane or suite
                    //Cards selectedCard = stockPile.getRandomCard();
                    //char selectedSuite = selectedCard.getSuite(); //random card suite letter
                    //System.out.println("P detected");

                    //Check if pile2 is foundation
                    if(userInputRx.substring(2,3).matches("[dhcsDHCS]")) { //Check if Moving To Foundation Pile

                        if(pile2 == randomStockSuit) { //Check if designated suit matches selected card

                            Cards topCardSuite = foundationPile.getTopCard(pile2);
//                            System.out.println(pile2);
//                            System.out.println(foundationPile.getSuiteCount(pile2));
//                            System.out.println(randomStockSuit);
//                            System.out.println(topCardSuite.getRank());

                            if(foundationPile.getSuiteCount(pile2) == 0 && randomStock.getRank().equals("A")) {

                                //Update Stock
                                stockPile.removeCardFromStock(randomStock);
                                //Update Foundation
                                foundationPile.addCardToSuite(randomStock, pile2);
                                stockPile.getRandomCard();
                                gameScore += 10;

                            } else if(ranks.indexOf(randomStock.getRank()) == 1+ranks.indexOf(topCardSuite.getRank())) {

                                //Update Stock
                                stockPile.removeCardFromStock(randomStock);
                                //Update Foundation
                                foundationPile.addCardToSuite(randomStock, pile2);
                                stockPile.getRandomCard();
                                gameScore += 10;

                            } else {
                                System.out.println("Illegal Move");
                            }
                        }

                    } else if(userInputRx.substring(2,3).matches("[1234567]")) { //Check if moving to tableau

                        int tab2 = Integer.parseInt(userInputRx.substring(2, 3));
                        Cards tableauCard = tableauPile.getLastCard(tab2);
                        //System.out.println("Moving to tab");
                        System.out.println(randomStock);
                        System.out.println(ranks.indexOf(randomStock.getRank()));
                        //System.out.println(ranks.indexOf(tableauCard.getRank()));
                        System.out.println(randomStock.getIsRed());
                        //System.out.println(tableauCard.getIsRed());

                        if (tableauCard == null) {

                            if(randomStock.getRank().equals("K")) {
                                stockPile.removeCardFromStock(randomStock);
                                tableauPile.addCard(tab2, randomStock);
                                stockPile.getRandomCard();
                            } else {
                                System.out.println("Illegal move: Must Transfer King to empty lane.");
                            }

                        }else if(ranks.indexOf(randomStock.getRank()) == ranks.indexOf(tableauCard.getRank())-1) {
                            if(randomStock.getIsRed() != tableauCard.getIsRed()) {
                                stockPile.removeCardFromStock(randomStock);
                                tableauPile.addCard(tab2, randomStock);
                                stockPile.getRandomCard();
                            } else {
                                System.out.println("Illegal Move: Card are both the same colour");

                            }

                        } else if(ranks.indexOf(randomStock.getRank())+1 == ranks.indexOf(tableauCard.getRank())) {
                            if(randomStock.getIsRed() != tableauCard.getIsRed()) {
                                stockPile.removeCardFromStock(randomStock);
                                tableauPile.addCard(tab2, randomStock);
                                stockPile.getRandomCard();
                            } else {
                                System.out.println("Illegal Move: Card are both the same colour");

                            }

                        } else {
                            System.out.println("Illegal Move: Card rank is not consecutively lower or higher.");
                        }


                    }

                } else if(userInputRx.substring(0,1).matches("[1234567]")) { //Check if Drawing From Tableau

                    int tab1 = Integer.parseInt(userInputRx.substring(0,1));

                    if(userInputRx.substring(2,3).matches("[dhcsDHCS]")) { //Check if moving to Foundation

                        Cards cardToMove = tableauPile.getLastCard(tab1);
                        char selectedSuite = cardToMove.getSuite();
                        String selectedRank = cardToMove.getRank();

                        if(pile2 == selectedSuite) { //Check if designated suit matches selected card

                            Cards topCardSuite = foundationPile.getTopCard(pile2);

                            System.out.println(foundationPile.getSuiteCount(pile2));
                            System.out.println(selectedSuite);

                            //Check if card can be placed in foundation pile
                            if(foundationPile.getSuiteCount(pile2) == 0 && selectedRank.equals("A")) {

                                //Update Tableau
                                tableauPile.removeCardFromTableau(tab1, cardToMove);
                                if(tableauPile.getLastCard(tab1) != null) {
                                    if (!tableauPile.getLastCard(tab1).getFaceUp()) {
                                        tableauPile.getLastCard(tab1).setFaceUp(true);
                                    }
                                }
                                //Update Foundation
                                foundationPile.addCardToSuite(cardToMove, pile2);
                                gameScore += 20;


                            } else if(ranks.indexOf(cardToMove.getRank()) == 1+ranks.indexOf(topCardSuite.getRank())) {

                                //Update Stock
                                stockPile.removeCardFromStock(cardToMove);
                                //Update Foundation
                                foundationPile.addCardToSuite(cardToMove, pile2);
                                gameScore += 20;

                            } else {
                                System.out.println("Illegal Move: Card rank is not consecutively higher.");
                            }

                        } else {
                            System.out.println("Illegal Move: Card is not of the same suit as foundation pile.");
                        }


                    } else if(userInputRx.substring(2,3).matches("[1234567]")) { //Check if Moving To Tableau

                        int tab2 = Integer.parseInt(userInputRx.substring(2,3));

                        Cards cardToMove = tableauPile.getLastCard(tab1);
                        Cards tableauCard = tableauPile.getLastCard(tab2);

                        if(tableauCard == null) {

                            if(cardToMove.getRank().equals("K")) {
                                tableauPile.removeCardFromTableau(tab1, cardToMove);
                                tableauPile.addCard(tab2, cardToMove);
                                gameScore += 5;
                                if(tableauPile.getLastCard(tab1) != null) {
                                    if (!tableauPile.getLastCard(tab1).getFaceUp()) {
                                        tableauPile.getLastCard(tab1).setFaceUp(true);
                                    }
                                }
                            } else {
                                System.out.println("Illegal move: Must Transfer King to empty lane.");
                            }

//                            tableauPile.removeCardFromTableau(tab1, cardToMove);
//
//                            if(tableauPile.getLastCard(tab1) != null) {
//                                if (!tableauPile.getLastCard(tab1).getFaceUp()) {
//                                    tableauPile.getLastCard(tab1).setFaceUp(true);
//                                }
//                            }
//                            tableauPile.addCard(tab2, cardToMove);

                        } else if(ranks.indexOf(cardToMove.getRank()) == ranks.indexOf(tableauCard.getRank())-1) {
                            if(cardToMove.getIsRed() != tableauCard.getIsRed()) {
                                tableauPile.removeCardFromTableau(tab1, cardToMove);
                                if(tableauPile.getLastCard(tab1) != null) {
                                    if (!tableauPile.getLastCard(tab1).getFaceUp()) {
                                        tableauPile.getLastCard(tab1).setFaceUp(true);
                                    }
                                }
                                tableauPile.addCard(tab2, cardToMove);
                                gameScore += 5;
                            } else {
                                System.out.println("Illegal Move: Both Cards are the same color.");
                            }

                        } else if(ranks.indexOf(cardToMove.getRank()) == ranks.indexOf(tableauCard.getRank())-1) {
                            if(cardToMove.getIsRed() != tableauCard.getIsRed()) {
                                tableauPile.removeCardFromTableau(tab1, cardToMove);
                                if(tableauPile.getLastCard(tab1) != null) {
                                    if (!tableauPile.getLastCard(tab1).getFaceUp()) {
                                        tableauPile.getLastCard(tab1).setFaceUp(true);
                                    }
                                }
                                tableauPile.addCard(tab2, cardToMove);
                                gameScore += 5;
                            } else {
                                System.out.println("Illegal Move: Card are the same colour.");

                            }
                        } else {
                            System.out.println("Illegal Move: Card rank is not consecutively higher or lower.");
                        }

                    } else {
                        System.out.println("Illegal Input");
                    }


                }


            } else if(userInputRx.matches(manyCards)) {

                //Parsing user input
                //String manyCards = "[1234567] [1234567] [0-9][0-9]";
                int numberOfCards = Integer.parseInt(userInputRx.substring(4,6));
                int tab1 = Integer.parseInt(userInputRx.substring(0,1));
                int tab2 = Integer.parseInt(userInputRx.substring(2,3));
                int sizeTab1 = tableauPile.getTableauLane(tab1).size();
                Cards tableauCard = tableauPile.getLastCard(tab2);
                int indexOfTopTransferCard = sizeTab1-numberOfCards;
                ArrayList<Cards> transferCards = new ArrayList<>();

                for(int i=indexOfTopTransferCard; i < tableauPile.getTableauLane(tab1).size(); i++ ) {
                    transferCards.add(tableauPile.getTableauLane(tab1).get(i));
                }

                Cards tfCard1 = transferCards.getFirst();

                System.out.println(tfCard1);
                System.out.println(numberOfCards);
                System.out.println(tfCard1);
                System.out.println(transferCards);

                if(tableauCard == null) {

                    if(tfCard1.getRank().equals("K")) {

                        for(int i=numberOfCards; i > 0; i--) {
                            tableauPile.getTableauLane(tab1).remove(indexOfTopTransferCard);
                        }

                        if(tableauPile.getLastCard(tab1) != null) {
                            if (!tableauPile.getLastCard(tab1).getFaceUp()) {
                                tableauPile.getLastCard(tab1).setFaceUp(true);
                            }
                        }

                        for(Cards addedCard: transferCards) {
                            tableauPile.addCard(tab2, addedCard);
                            gameScore += 5;
                        }

                    } else {
                        System.out.println("Illegal move: Must Transfer King to empty lane.");
                    }

                } else if(ranks.indexOf(tfCard1.getRank()) == ranks.indexOf(tableauCard.getRank())-1) {
                    if(tfCard1.getIsRed() != tableauCard.getIsRed()) {

                        for(int i=numberOfCards; i > 0; i--) {
                            tableauPile.getTableauLane(tab1).remove(indexOfTopTransferCard);
                        }

                        if(tableauPile.getLastCard(tab1) != null) {
                            if (!tableauPile.getLastCard(tab1).getFaceUp()) {
                                tableauPile.getLastCard(tab1).setFaceUp(true);
                            }
                        }

                        for(Cards addedCard: transferCards) {
                            tableauPile.addCard(tab2, addedCard);
                            gameScore += 5;
                        }

                    } else {

                        System.out.println("Illegal Move: Card is not of the same suit as foundation pile.");

                    }

                } else if(ranks.indexOf(tfCard1.getRank()) == ranks.indexOf(tableauCard.getRank())-1) {
                    if(tfCard1.getIsRed() != tableauCard.getIsRed()) {
                        for(int i=numberOfCards; i > 0; i--) {
                            tableauPile.getTableauLane(tab1).remove(indexOfTopTransferCard);
                        }

                        if(tableauPile.getLastCard(tab1) != null) {
                            if (!tableauPile.getLastCard(tab1).getFaceUp()) {
                                tableauPile.getLastCard(tab1).setFaceUp(true);
                            }
                        }

                        for(Cards addedCard: transferCards) {
                            tableauPile.addCard(tab2, addedCard);
                            gameScore += 5;
                        }
                    } else {
                        System.out.println("Illegal Move: Card is not of the same suit as foundation pile.");

                    }
                } else {
                    System.out.println("Illegal Move: Card rank is not consecutively higher.");
                }

            } else {
                System.out.println("\nInvalid Input\n");
            }

            for(int i=0; i < suites.length; i++) {
                if(foundationPile.getSuiteCount(suites[i]) != 13) {
                    isGameFinished = false;
                    break;
                } else {
                    isGameFinished = true;
                }

            }

            if(isGameFinished) {
                System.out.printf("\nYour Score is: %d !\n", gameScore);
            }

        }

    }

}