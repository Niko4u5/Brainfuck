import java.util.ArrayList;
import java.util.Scanner;

public class Brainfuck {
    ArrayList<Integer> data = new ArrayList<Integer>();
    ArrayList<Character> program = new ArrayList<Character>();
    int pointer = 0; //the pointer for the data
    int counter = 0; //the pointer for the program

    Brainfuck(String inputProgram) {
        for (int i = 0; i < inputProgram.length(); i++){
            program.add(inputProgram.charAt(i)); //goes through the input String and saves it in the program ArrayList
        }
        data.add(0); //sets the first cell in the data ArrayList to 0

    }

    /** runs the program*/
    public void run(){
        while (counter < program.toArray().length && counter >= 0) { //while counter is inside the program parse the next character.
            if (program.get(counter).equals('>')) {
                pointerUp();
                counterUp(0);
            }else if (program.get(counter).equals('<')) {
                pointerDown();
                counterUp(0);
            }else if (program.get(counter).equals('+')) {
                add();
                counterUp(0);
            }else if (program.get(counter).equals('-')) {
                subtract();
                counterUp(0);
            }else if (program.get(counter).equals('.')) {
                output();
                counterUp(0);
            }else if (program.get(counter).equals(',')) {
                input();
                counterUp(0);
            }else if (program.get(counter).equals('[')) {
                startLoop();
                counterUp(0);
            }else if (program.get(counter).equals(']')) {
                endLoop();
                counterUp(0);
            }else counterUp(0);
        }

    }

    /** raises the counter by 1 if the program isn't over jet*/
    private void counterUp(int i){
        if (counter < program.toArray().length){
            counter++;
        } else System.exit(i);
    }

    /** decreases the counter by 1 if the program isn't over jet, otherwise exits the program*/
    private void counterDown(int i){
        if (counter > 0){
            counter--;
        } else System.exit(i);
    }

    /** raises the pointer by 1*/
    private void pointerUp() {
        pointer++;
        try { //tries reading the current cell in data and sets it to 0 if it doesn't jet exist.
            data.get(pointer);
        } catch (IndexOutOfBoundsException i) {
            data.add(pointer,0);
        }
    }

    /** decreases  the pointer by 1*/
    private void pointerDown() {
        pointer--;
        try { //tries reading the current cell in data and sets it to 0 if it doesn't jet exist.
            data.get(pointer);
        } catch (IndexOutOfBoundsException i) {
            data.add(pointer,0);
        }
    }

    /** adds one to the current cell in data*/
    private void add() {
        data.set(pointer,data.get(pointer) + 1);
    }

    /** subtracts one from the current cell in data*/
    private void subtract() {
        data.set(pointer,data.get(pointer) - 1);
    }

    /** parses the current cell in data as Char and prints it*/
    private void output() {
        System.out.print(Character.toChars(data.get(pointer)));
    }

    /** reads the first char and writes it to the current cell in data*/
    private void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nGeben sie ein Zeichen ein!");
        data.set(pointer, (int) scanner.next().toCharArray()[0]);
    }

    /** goes to the end of the loop if the current cell in data is 0*/
    private void startLoop(){
        if (data.get(pointer) == 0){
            int i = 0;
            do {
                /*counts up with each starting loop and down with each ending loop
                so it is at 0 when the loop and all inner loops are over.*/
                if (program.get(counter).equals('[')){
                    i++;
                } else if (program.get(counter).equals(']')){
                    i--;
                }
                counterUp(1);
            } while (i != 0); //ends when i is 0

        }
    }

    /** goes to the beginning of the loop*/
    private void endLoop(){
        int i = 0;
        do {
            /*counts up with each starting loop and down with each ending loop
            so it is at 0 when the loop and all inner loops are over.*/
            if (program.get(counter).equals('[')){
                i++;
            } else if (program.get(counter).equals(']')){
                i--;
            }
            counterDown(1);
        } while (i != 0); //ends when i is 0

    }
}
