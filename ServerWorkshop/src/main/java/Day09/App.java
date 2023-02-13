package Day09;

import java.util.Random;
import java.util.Scanner;

public class App {
    private App(){
    }
    public static void main( String[] args){

        // need the random class to carry out randomise operation
        Random random = new Random();

        // generate random number between o and 99
        Integer randomNumber = random.nextInt(100);

        // store my guess
        Integer myGuess = 0;
        
        // expect input from keyboard
        // convert to expect from inputStream if its a socket app
        Scanner scanner = new Scanner(System.in);

        // allow user to guess until they got the random number correct
        while (myGuess !=randomNumber){
            myGuess = scanner.nextInt();

            if (myGuess < randomNumber) {
                System.out.println("your guess is lower");
            } else if (myGuess > randomNumber){
                System.out.println("your guess is higher");
            } else {
                System.out.println("Bingo!");
                scanner.close();
            }
        }
    }
}
