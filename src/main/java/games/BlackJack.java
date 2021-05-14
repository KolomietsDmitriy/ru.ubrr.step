package games;

import org.slf4j.Logger;

import java.io.IOException;

import static games.CardUtils.*;
import static games.Choiсe.getCharacterFromUser;
import static java.lang.String.format;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    private static int[] allCards; // Основная колода
    private static int allCardIndex; // Счётчик карт основной колоды

    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCardIndex; // курсоры карт игроков. Индекс - номер игрока

    private static int[] playersCash = {100, 100};

    private static final int WIN_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 9;

    private static int getCardValue(int card) {
        switch (getPar(card)) {
            case JACK:
                return 2;
            case QUEEN:
                return 3;
            case KING:
                return 4;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case ACE:
                return 11;
            default:
                return 0;
        }
    }

    private static void initRound() {
        log.info("\nУ Вас " + playersCash[0] + "$, у компьютера - " + playersCash[1] + "$. Начинаем новый раунд!");
        allCards = getShuffleArray();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCardIndex = new int[]{0, 0};
        allCardIndex = 0;
    }

    private static int addCard(int player) {

        playersCards[player][playersCardIndex[player]] = allCards[allCardIndex];
        allCards[allCardIndex] = -1;
        allCardIndex++;
        playersCardIndex[player]++;

        return playersCards[player][playersCardIndex[player] - 1];
    }

    private static int calcSumScore(int player) {

        int sumScore = 0;

        for (int i = 0; i < playersCardIndex[player]; i++) {
            sumScore += getCardValue(playersCards[player][i]);
        }

        return sumScore;
    }

    private static boolean checkLose(int player) {
        return calcSumScore(player) > WIN_VALUE;
    }

    static boolean confirm(String message) throws IOException {
        log.info(message + " \"Y\" - Да, {любой другой символ} - нет (Чтобы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y':
                return true;
            default:
                return false;
        }
    }

    private static void addCardPlayer() {
        int currentCard = 0;

        currentCard = addCard(FIRST);
        log.info(format("Вам выпала карта %s", printCard(currentCard)));
        log.info(format("У вас %d", calcSumScore(FIRST)));
    }

    private static void addCardDiler() {
        int currentCard = 0;

        currentCard = addCard(SECOND);
        log.info(format("У дилера выпала карта %s", printCard(currentCard)));
        log.info(format("У дилера %d", calcSumScore(SECOND)));
    }

    private static void winPlayer() {
        log.info("Вы выиграли раунд! Получаете 10$");
        playersCash[FIRST] += 10;
        playersCash[SECOND] -= 10;
    }

    private static void winDiler() {
        log.info("Вы проиграли раунд! Теряете 10$");
        playersCash[SECOND] += 10;
        playersCash[FIRST] -= 10;
    }

    private static void noWinner() {
        log.info("Ничья");
    }

    public static void main(String... __) throws IOException {

        boolean finishRound = false;

        while (playersCash[FIRST] != 0 && playersCash[SECOND] != 0) {
            initRound();

            finishRound = false;

            addCardPlayer();

            addCardDiler();

            addCardPlayer();

            addCardDiler();

            while (confirm("Еще карту?")) {
                addCardPlayer();

                if (checkLose(FIRST)) {
                    finishRound = true;
                    break;
                }
            }

            if (!finishRound) {
                while (calcSumScore(SECOND) < 17) {
                    addCardDiler();
                }
            }

            if (checkLose(FIRST)) {
                winDiler();
            } else if (checkLose(SECOND)) {
                winPlayer();
            } else if (calcSumScore(FIRST) == calcSumScore(SECOND)) {
                noWinner();
            } else if (calcSumScore(FIRST) > calcSumScore(SECOND)) {
                winPlayer();
            } else winDiler();

        }
    }
}
