
import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        boolean band;
        int option;
        System.out.println("\033[32m♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠\n" +
                            "♠								                                ♠\n" +
                            "♠								                                ♠\n" +
                            "♠	BIENVENIDO AL CASINO					                    ♠\n" +
                            "♠	CREDITOS: 10						                        ♠\n" +
                            "♠								                                ♠\n" +
                            "♠							                                	♠\n" +
                            "♠								                                ♠\n" +
                            "♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠\n\033[30m" );
        do {
            Player player1 = new Player();
            System.out.println(player1.toString());
            boolean win = false;
            Main.Menu();
            option = scan.nextInt();

            switch (option) {
                case 1:
                    Coin coin1 = new Coin();
                    do {
                        System.out.println("Choose: 1 = [Head]/2 = [Tail]");
                        int chos = scan.nextInt();
                        coin1.TossCoin();
                        System.out.println("Coin Toss Game: " + coin1.isWinner());
                        if (chos == 1)
                            win = coin1.ChooseCoinFace("HEAD");
                        else if (chos == 2)
                            win = coin1.ChooseCoinFace("TAIL");
                        if (win)
                            System.out.println("YOU WIN!!!!");
                        else
                            System.out.println("YOU LOSE!!!");
                        band = Main.PlayAgain(scan);
                    } while (band);
                    break;
                case 2:
                    do {
                        int punt = 0, punt2 = 0;
                        Dice dice1 = new Dice();
                        Dice dice2 = new Dice();
                        dice1.RollDice();
                        dice2.RollDice();
                        punt = dice1.getValue() + dice2.getValue();
                        System.out.println("Your turn: ");
                        System.out.println("Dice 1: " + dice1.toString() +
                                "\n" + "Dice 2: " + dice2.toString());
                        System.out.println("Computer turn: ");
                        Dice dice3 = new Dice();
                        Dice dice4 = new Dice();
                        dice3.RollDice();
                        dice4.RollDice();
                        punt2 = dice3.getValue() + dice4.getValue();
                        System.out.println("Dice 1: " + dice3.toString() +
                                "\n" + "Dice 2: " + dice4.toString());
                        if (punt2 > punt)
                            System.out.println("COMPUTER WINS!!");
                        else if (punt > punt2)
                            System.out.println("YOU WIN!!");
                        else
                            System.out.println("DRAW!!");
                        band = Main.PlayAgain(scan);
                    } while (band);
                    break;
                case 3:
                    do {
                        int points, points2 = 0;
                        Deck newDeck = new Deck();
                        newDeck.InitDeck();
                        newDeck.ShuffleDeck();
                        ArrayList<Card> PlayerHand = newDeck.DrawCards();
                        ArrayList<Card> CompHand = newDeck.DrawCards();
                        System.out.println("\nPlayer Hand:");
                        Comparation.PrintMaze(PlayerHand);
                        points = Comparation.Points(PlayerHand);
                        System.out.println("Player: " + points);
                        System.out.println("\nComputer Hand:");
                        Comparation.PrintMaze(CompHand);
                        points2 = Comparation.Points(CompHand);
                        if (points2 > points)
                            System.out.println("COMPUTER WINS!!");
                        else if (points > points2)
                            System.out.println("YOU WIN!!");
                        else
                            System.out.println("DRAW!!");
                        band = Main.PlayAgain(scan);
                    }while (band);
                   break;
            }
        }while (option != 4);
    }

    public static void Menu()
    {
        System.out.println("Bienvenido, elija el juego que guste jugar:\n" +
                "1. Toss Coin\n" +
                "2. Roll Dice\n" +
                "3. Póker");
    }

    public static boolean PlayAgain(Scanner scan)
    {
        boolean band = false;
        System.out.println("\nWould you like play again? [Y]/[N]");
        String ans = scan.next();
        if ("Y".equals(ans) || "y".equals(ans))
            band = true;
        else if ("N".equals(ans) || "n".equals(ans))
            band = false;
        return band;
    }

    public static void MenuPoker()
    {
        System.out.println("Player");
    }
}

class Coin
{
    private boolean head;
    private boolean tail;


    public Coin() {
    }

    public boolean isTail()
    {
        return this.tail;
    }

    public boolean isHead()
    {
        return this.head;
    }

    public void TossCoin()
    {
        Random toss = new Random();
        //int spin = toss.nextInt(10000);

        this.head = toss.nextBoolean();

        if (this.head) {
            //this.head = true;
            this.tail = false;
        }
        else {
            this.tail = true;
            //this.head = false;
        }
    }

    public String isWinner()
    {
        if (isHead())
            return "HEAD";
        else
            return "TAIL";
    }

    public boolean ChooseCoinFace(String face)
    {
        return isWinner().equals(face);
    }
}

class Dice
{
    private int value;

    public Dice() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void RollDice()
    {
        Random rand = new Random();
        this.value = rand.nextInt(6) + 1;
    }

    @Override
    public String toString() {
        return "[" + this.value + "]";
    }
}

class Card
{
    private String suit;
    private int value;
    private String royalSuit;
    private String color;

    public Card(String suit, int value, String royalSuit) {
        this.suit = suit;
        this.value = value;
        this.royalSuit = royalSuit;
    }

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public Card() {
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getRoyalSuit() {
        return royalSuit;
    }

    public void setRoyalSuit(String royalSuit) {
        this.royalSuit = royalSuit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean RoyalStatus()
    {
        boolean res = false;
        if (this.value > 9 || this.value == 0)
        {
            switch (value)
            {
                case 10:
                    this.royalSuit = "J";
                    break;
                case 11:
                    this.royalSuit = "Q";
                    break;
                case 12:
                    this.royalSuit = "K";
                    break;
                case 0:
                    this.royalSuit = "A";
                    this.setValue(14);
            }
            res = true;
        }
        else
            this.royalSuit = Integer.toString(this.value + 1);
        return res;
    }

    @Override
    public String toString() {
        String fig = "";
        String colorCard = "";

        if ("spades".equals(suit)) {
            fig = "♠";
            colorCard = "\033[37m";
        }
        else if ("clubes".equals(suit)) {
            fig = "♣";
            colorCard = "\033[37m";
        }
        else if ("hearts".equals(suit)) {
            fig = "♥";
            colorCard = "\033[31m";
        }
        else if ("diamonds".equals(suit)) {
            fig = "♦";
            colorCard = "\033[31m";
        }

        return  colorCard + "\n________" + "\n" +
                "|" + this.royalSuit + "      |\n" +
                "|       |\n" +
                "|   "+ fig + "   |\n" +
                "|       |\n" +
                "|      " + this.royalSuit + "|\n" +
                " --------\033[30m";

    }
}

class Deck {
    private ArrayList<Card> CardDeck;
    private int numberofCards;

    public Deck() {
        CardDeck = new ArrayList<>();
        numberofCards = 51;
    }

    public void InitDeck() {
        for (int i = 0; i < 13; i++) {
            Card newCard = new Card("hearts", i);
            newCard.setColor("red");
            newCard.RoyalStatus();
            this.CardDeck.add(newCard);
        }

        for (int i = 13; i < 26; i++) {
            Card newCard = new Card("diamonds", i - 13);
            newCard.setColor("red");
            newCard.RoyalStatus();
            this.CardDeck.add(newCard);
        }

        for (int i = 26; i < 39; i++) {
            Card newCard = new Card("clubes", i - 26);
            newCard.setColor("black");
            newCard.RoyalStatus();
            this.CardDeck.add(newCard);
        }

        for (int i = 39; i < 52; i++) {
            Card newCard = new Card("spades", i - 39);
            newCard.setColor("black");
            newCard.RoyalStatus();
            this.CardDeck.add(newCard);
        }
    }

    public void ShuffleDeck() {
        for (int x = 0; x < 1000; x++) {
            Random rand = new Random();
            int i = rand.nextInt(51);
            //System.out.println(i);
            int j = rand.nextInt(50) + 1;
            //System.out.println(j);
            if (i != j) {
                Card temp = this.CardDeck.get(i);
                Card temp2 = this.CardDeck.get(j);
                this.CardDeck.remove(i);
                this.CardDeck.add(i, temp2);
                this.CardDeck.remove(j);
                this.CardDeck.add(j, temp);
            }
        }
    }

    //@Override
    public String ToString(int i) {
        return CardDeck.get(i).toString() + "\t" +
                CardDeck.get(i - 1).toString() + "\t" +
                CardDeck.get(i - 2).toString() + "\t" +
                CardDeck.get(i - 3).toString() + "\t" +
                CardDeck.get(i - 4).toString() + "\t";

    }

    public String ShowMaze() {
        String str = "";
        int i = numberofCards;
        if (numberofCards >= 5) {
            str = this.ToString(i);
            numberofCards -= 5;
        }
        return str;
    }

    public void prueba() {
        for (int i = 0; i < 52; i++) {
            System.out.println(CardDeck.get(i).toString() + "\n");
        }
    }

    public ArrayList<Card> DrawCards() {
        ArrayList<Card> Hand = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Hand.add(this.CardDeck.get(1));
            Collections.sort(Hand, new Comparator<Card>() {
                @Override
                public int compare(Card o1, Card o2) {
                    return new Integer(o1.getValue()).compareTo(new Integer(o2.getValue()));
                }
            });
            this.CardDeck.remove(1);
        }
        return Hand;
    }
}


class Comparation
{
    public static int BiggerValue(ArrayList<Card> Maze)
    {
        int bigger = -1;
        for (Card card: Maze)
        {
            if (card.getValue() >= bigger)
                bigger = card.getValue();
            else
                bigger = -1;
        }


        return bigger;
    }

    public static boolean isSameSuit(Card card1, Card card2)
    {
        return card1.getSuit().equals(card2.getSuit());
    }

    public static boolean isSameColor(Card card1, Card card2)
    {
        return card1.getColor().equals(card2.getColor());
    }

    public static boolean isSameValue(Card card1, Card card2)
    {
        return card1.getValue() == card2.getValue();
    }

    public static int Color(ArrayList<Card> Maze)
    {
        int res = -1;
        String color = Maze.get(0).getColor();
        for (Card card: Maze)
        {
            if (Comparation.isSameColor(Maze.get(0), card))
                res = Comparation.BiggerValue(Maze);
            else
                return -1;
        }
        return res;
    }

    public static int Stair(ArrayList<Card> Maze)
    {
        int res = -1;
        int val2 = 0;
        int val = Maze.get(0).getValue();
        for (int i = 0; i < Maze.size()-1; i++)
        {
            val2 = Maze.get(i+1).getValue();
            if (val2 == (val + 1)) {
                val = val2;
                res = Comparation.BiggerValue(Maze);
            }
            else
                return -1;
        }
        return res;
    }

    public static int Poker(ArrayList<Card> Maze)
    {
        int res = -1;
        boolean flag = false;
        int i = 1;
        int j = 0;
        do {
            flag = Comparation.isSameValue(Maze.get(i), Maze.get(j));
            i++;
            j++;
        }while (i < Maze.size() && Comparation.isSameValue(Maze.get(i), Maze.get(j)));
        if (flag && i == 5)
            res = BiggerValue(Maze);
        return res;
    }

    public static void PrintMaze(ArrayList<Card> Maze)
    {
        for (Card card : Maze)
        {
            System.out.println(card.toString());
        }
    }

    public static int Points(ArrayList<Card> player1)
    {
        int points = 0;
        if (Comparation.BiggerValue(player1) != -1) {
            points += Comparation.BiggerValue(player1);
            System.out.println("Bigger Value +" + Comparation.BiggerValue(player1));
        }
        if (Comparation.Stair(player1) != -1) {
            points += Comparation.Stair(player1);
            System.out.println("Stair! +" + Comparation.Stair(player1));
        }

        if (Comparation.Color(player1) != -1){
            points += Comparation.Color(player1);
            System.out.println("Color!! +" + Comparation.Color(player1));
        }

        if (Comparation.Poker(player1) != -1){
            points += Comparation.Poker(player1);
            System.out.println("Poker!!! + " + Comparation.Poker(player1));
        }

        if (Comparation.Stair(player1) != -1 && Comparation.Color(player1) != -1){
            points += Comparation.Stair(player1) + Comparation.Color(player1);
            System.out.println("Stair Color!!!! +" + (Comparation.Stair(player1) + Comparation.Color(player1)));
        }
        return points;
    }
}


class Player
{
    private float credits;
    private float debt;
    private float winnings;

    public Player(float credits, float debt, float winnings) {
        this.credits = credits;
        this.debt = debt;
        this.winnings = winnings;
    }

    public Player() {
        credits = 10.0f;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public float getWinnings() {
        return winnings;
    }

    public void setWinnings(float winnings) {
        this.winnings = winnings;
    }

    @Override
    public String toString() {
        return "Player 1 [" +
                "Credits: " + credits +
                ", Debt: " + debt +
                ", Winnings: " + winnings +
                ']';
    }
}