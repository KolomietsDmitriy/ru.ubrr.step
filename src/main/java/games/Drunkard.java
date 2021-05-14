package games;

import static games.CardUtils.*;
import static java.lang.String.format;

public class Drunkard {

    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static int[] allCards = new int[CARDS_TOTAL_COUNT];
    private static int[] headCard = new int[2];
    private static int[] tailCard = {(CARDS_TOTAL_COUNT / 2) - 1, (CARDS_TOTAL_COUNT / 2) - 1};
    private static int[] countCard = {(CARDS_TOTAL_COUNT / 2), (CARDS_TOTAL_COUNT / 2)};

    public static void main(String... __) {

        boolean win = false;
        int countIteration = 0;

        allCards = getShuffleArray();

//        playersCards[0] = Arrays.copyOfRange(allCards, 0, allCards.length / 2);
//        playersCards[1] = Arrays.copyOfRange(allCards, allCards.length / 2, allCards.length);
        System.arraycopy(allCards, 0, playersCards[0], 0, (CARDS_TOTAL_COUNT / 2));
        System.arraycopy(allCards, (CARDS_TOTAL_COUNT / 2), playersCards[1], 0, (CARDS_TOTAL_COUNT / 2));

        while (!win) {
            countIteration++;
            int firstCard = getHeadCard(FIRST);
            int secondCard = getHeadCard(SECOND);

            System.out.println(format("Итерация №%d; игрок №1 карта: %s; игрок №2 карта: %s.",
                    countIteration,
                    printCard(firstCard),
                    printCard(secondCard)));

            int resultCompare = compareCard(getValueCard(firstCard), getValueCard(secondCard));

            if (resultCompare == 1) {
                System.out.println("Выиграл игрок 1");
                addCard(FIRST, firstCard);
                addCard(FIRST, secondCard);
            } else if (resultCompare == 2) {
                System.out.println("Выиграл игрок 2");
                addCard(SECOND, firstCard);
                addCard(SECOND, secondCard);
            } else {
                System.out.println("Ничья");
                addCard(FIRST, firstCard);
                addCard(SECOND, secondCard);
            }

            System.out.println(format("У игрока 1 %d карт. У игрока 2 %d карт",
                    countCard[FIRST],
                    countCard[SECOND]));

            if (countCard[FIRST] == 0) {
                win = true;
                System.out.println("Победил второй игрок!");
            } else if (countCard[SECOND] == 0) {
                win = true;
                System.out.println("Победил первый игрок!");
            }

        }

//        System.out.println("1 array");
//        System.out.println(Arrays.toString(playersCards[0]));
//        System.out.println("2 array");
//        System.out.println(Arrays.toString(playersCards[1]));

    }


    private static int getHeadCard(int playerInd) {
        int card = playersCards[playerInd][headCard[playerInd]];//взяли карту
        playersCards[playerInd][headCard[playerInd]] = -1;      //пометили, что тут карты нет
        headCard[playerInd] = increment(headCard[playerInd]);
        countCard[playerInd]--;

        return card;
    }

    private static int increment(int index) {
        return (index + 1) % CARDS_TOTAL_COUNT;
    }

    private static int getValueCard(int cardNumber) {
        return cardNumber % PARS_TOTAL_COUNT;
    }

    private static int compareCard(int firstCardNumber, int secondCardNumber) {
        //проверка на 6 и туз
        if (firstCardNumber == 0 && secondCardNumber == 8) {
            return 1;
        } else if (secondCardNumber == 0 && firstCardNumber == 8) {
            return 2;
        }

        if (firstCardNumber > secondCardNumber) {
            return 1;
        } else if (firstCardNumber == secondCardNumber) {
            return 0;
        } else return 2;
    }

    private static void addCard(int numberPlayer, int numberCard) {
        tailCard[numberPlayer] = increment(tailCard[numberPlayer]);
        playersCards[numberPlayer][tailCard[numberPlayer]] = numberCard;

        countCard[numberPlayer]++;
    }
}
