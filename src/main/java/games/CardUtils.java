package games;

import org.apache.commons.math3.util.MathArrays;

public class CardUtils {

    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }

    enum Par {
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK, // Валет
        QUEEN, // Дама
        KING, // Король
        ACE // Туз
    }

    public static final int FIRST = 0;
    public static final int SECOND = 1;

    public static final int PARS_TOTAL_COUNT = Par.values().length;
    public static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

    public static String printCard(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    public static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    public static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    public static int[] getShuffleArray() {
        int[] cardArray = new int[CARDS_TOTAL_COUNT];

        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) cardArray[i] = i;

        MathArrays.shuffle(cardArray);

        return cardArray;
    }
}
