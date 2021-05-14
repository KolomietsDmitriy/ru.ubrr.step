package games;

import org.slf4j.Logger;

import static java.lang.Boolean.FALSE;
import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);
    
    public static void main(String... __) {
        int cash = 100;
        int bet = 10;
        int prize = 1000;
        int range = 7;
        int countSlot = 3;
        int[] valueSlot = new int[countSlot];
        int prevSlot = 0;
        boolean win = FALSE;

        for (int i = 0; i < countSlot; i++) valueSlot[i] = 1;

        while (cash > 0) {
            log.info("У Вас " + cash + "$, ставка - " + bet + "$");

            cash -= bet;

            log.info("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            for (int i = 0; i < countSlot; i++) {

                valueSlot[i] = (valueSlot[i] + (int) round(random() * 100)) % range + 1;

                win = ((prevSlot == valueSlot[i]) && win) || i == 0;

                prevSlot = valueSlot[i];

                System.out.print(valueSlot[i] + " ");
            }
            prevSlot = 0;

            log.info(" ");

            if (win) {
                cash += prize;
                log.info("Победа! Ваш капитал теперь составляет: " + cash + "$");
                break;
            } else {
                log.info("Проигрыш " + bet + "$, ваш капитал теперь составляет: " + cash + "$");
            }
        }

        log.info("Игра закончена");
    }
}
