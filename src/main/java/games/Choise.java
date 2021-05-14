package games;

import java.io.IOException;
import java.util.Scanner;

class Choiсe {

    static final String LINE_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) throws IOException {
        System.out.println("Выберите игру:\n1 - \"однорукий бандит\", 2 - \"пьяница\", 3 - \"21\"");
        switch (getCharacterFromUser()) {
            case '1':
                Slot.main();
                break;
            case '2':
                Drunkard.main();
                break;
            case '3':
                BlackJack.main();
                break;
            default:
                System.out.println("Игры с таким номером нет!");
        }
    }

    static char getCharacterFromUser() {

        return new Scanner(System.in).next().charAt(0);

//        byte[] input = new byte[1 + LINE_SEPARATOR.length()];
//        if (System.in.read(input) != input.length)
//            throw new RuntimeException("Пользователь ввёл недостаточное кол-во символов");
//        return (char) input[0];
    }

}
