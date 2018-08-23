package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RunnableBlackJack {

    public static void main(String[] args) {

        BlackJack blackjack = new BlackJack();

        blackjack.play();
    }
}

class BlackJack {
    Scanner scanner = new Scanner(System.in);
    Deck deck = new Deck();
    boolean play = false;

    Player player1 = new Player("Fabian");

    int handCards;


    public BlackJack() {

        System.out.println("Hoeveel kaarten wil je starten?");
        Integer inputHandCards = scanner.nextInt();
        this.handCards = inputHandCards;

    }

    public void play() {

        play = true;

        deck.suffle();

        for(int i = 0; i < handCards;i++) {
            this.getCard();
        }

        while(play) {

            System.out.println("Voer actie in:");

            String action = scanner.next();

            if(action.equals("k")) this.getCard();
            if(action.equals("p")) this.checkWin();
            if(action.equals("q")) play = false;

        }

    }

    private void checkWin(){
        if(player1.getPoints() <= 21) isWin();

        for(int i = 0; i < player1.cards.size(); i++) {
            Card card = player1.cards.get(i);

            if(card.type.equals('a')) {
                System.out.println(player1.name + " heeft een aas.");
                card.points = 1;
                if(player1.getPoints() <= 21) isWin();

            }
        }

        if(player1.getPoints() > 21) {
            System.out.println(player1.name + " heeft verloren.");
            play = false;
        }
    }

    private void isWin(){

        System.out.println(player1.name + " heeft gewonnen!");
        play=false;
    }

    private void getCard(){

        player1.getCard(deck.cards.get(0));
        deck.cards.remove(0);
        player1.showCards();

        System.out.println(player1.name+ " heeft "+ player1.getPoints()+ " punten.");
    }

}

class Deck {

    String[] figureCards = new String[] {"Schobbe", "Ruiten", "Harten", "Klaver"};
    Short[][] typeCards = new Short[][] {{2,2}, {3,3}, {4,4}, {5,5}, {6,6}, {7,7}, {8,8}, {9,9}, {10,10},{'b',10}, {'v',10}, {'k',10}, {'a',11}};

    ArrayList<Card> cards = new ArrayList<Card>();

    public Deck(){

        for(int i = 0; i < this.figureCards.length; i++){
            for(int j = 0; j < this.typeCards.length; j++) {
                Card card = new Card();
                card.figure = this.figureCards[i];
                card.type = this.typeCards[12][0];
                card.points = this.typeCards[12][1];
                this.cards.add(card);
            }
        }
    }

    public void suffle(){
        Collections.shuffle(this.cards);
    }

    public void getCard() {
        System.out.println("Card figure: "+cards.get(0).figure);
        System.out.println("Card type: "+cards.get(0).type);
    }

    public void printResult() {
        String result = "";

        for(int i = 1; i < this.cards.size(); i++){

            result += cards.get(i).type + ",";
        }

        System.out.println(result);
    }
}

class Card {
    String figure = "";
    Short type;
    int points;
}

class Player {
    String name = "";
    ArrayList<Card> cards = new ArrayList<Card>();

    public Player(String name) {
        this.name = name;
    }

    public void getCard(Card card) {
        cards.add(card);
    }

    public int getPoints() {

        int points = calcPoints();

        if(points > 21) {

            Card aas = getAas();

            while(aas != null && aas.points != 1 && points > 21) {
                System.out.println(this.name + " heeft een aas.");
                aas.points = 1;
                points = calcPoints();
            }

        }

        return points;
    }

    private int calcPoints(){
        int points = 0;

        for(int i=0; i < cards.size();i++) {
            points += cards.get(i).points;
        }
        return points;
    }

    private Card getAas() {
        for(int i=0; i < cards.size();i++) {
            if(cards.get(i).type == 'a') {
                return cards.get(i);
            }
        }
        return null;
    }

    public void showCards() {
        int points = 0;

        for (int i = 0; i < cards.size(); i++) {


            System.out.println("card figuur: " + cards.get(i).figure);
            if((int)cards.get(i).type > 10 ) {
                System.out.println("card type: " + (char)(int)cards.get(i).type);
            }else {
                System.out.println("card type: " + cards.get(i).type);
            }

            System.out.println("card points: " + cards.get(i).points);
            System.out.println("k");
        }
    }

}


