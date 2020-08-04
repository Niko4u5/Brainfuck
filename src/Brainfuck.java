import java.util.ArrayList;
import java.util.Scanner;

public class Brainfuck {
    ArrayList<Integer> data = new ArrayList<Integer>();
    ArrayList<Character> program = new ArrayList<Character>();
    int pointer = 0;
    int counter = 0;

    Brainfuck(String inputProgram) {
        for (int i = 0; i < inputProgram.length(); i++){
            program.add(inputProgram.charAt(i));
        }
        data.add(0);

    }
    
    public void run(){
        while (counter < program.toArray().length && counter >= 0) {
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

    private void counterUp(int i){
        if (counter < program.toArray().length){
            counter++;
        } else System.exit(i);
    }
    private void counterDown(int i){
        if (counter > 0){
            counter--;
        } else System.exit(i);
    }

    private void pointerUp() {
        pointer++;
        try {
            data.get(pointer);
        } catch (IndexOutOfBoundsException i) {
            data.add(pointer,0);
        }
    }
    private void pointerDown() {
        pointer--;
        try {
            data.get(pointer);
        } catch (IndexOutOfBoundsException i) {
            data.add(pointer,0);
        }
    }

    private void add() {
        data.set(pointer,((Integer) data.get(pointer)) + 1);
    }
    private void subtract() {
        data.set(pointer,((Integer) data.get(pointer)) - 1);
    }

    private void output() {
        System.out.print(Character.toChars(data.get(pointer)));
    }
    private void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nGeben sie ein Zeichen ein!");
        data.set(pointer, (int) scanner.next().toCharArray()[0]);
    }

    private void startLoop(){
        if (data.get(pointer) == 0){
            int i = 1;
            do {
                counterUp(1);
                if (program.get(counter).equals('[')){
                    i++;
                } else if (program.get(counter).equals(']')){
                    i--;
                }

            } while (i != 0);

        }
    }
    private void endLoop(){
        int i = 0;
        do {
            if (program.get(counter).equals('[')){
                i++;
            } else if (program.get(counter).equals(']')){
                i--;
            }
            counterDown(1);
        } while (i != 0);

    }
}
