package day5;

import java.util.ArrayList;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    static ArrayList<Stack<Character>> allStacks = new ArrayList<>();

    public static void main(String args[]){
        boolean partOne = true;
        initStacks(startInit());
        for(int[] instruction : getInstructions()){
            handleStack(instruction[0], instruction[1], instruction[2], partOne);
        }
        getTop();
    }

    public static char[][] startInit(){
        char[][] initialArray = new char[9][8];
        int elementHeight = 0; 
        try {
            File txtFile = new File("day5/input.txt");
            Scanner file = new Scanner(txtFile);
            while(file.hasNextLine() && elementHeight < 8){
                String line = file.nextLine();
                for(int i = 0; i < initialArray.length; i++){
                    if(i == 0){
                        initialArray[i][7 - elementHeight] = line.charAt(1);
                    }else{
                        int position = 1 + (i *4);
                        initialArray[i][7 - elementHeight] = line.charAt(position);
                    }
                }
                elementHeight++;
            }
            file.close();
        }catch(FileNotFoundException e){
            e.getMessage();
        }
        return initialArray;
    }

    public static void initStacks(char[][] initStacks){
        for(char[] initStack : initStacks){
            Stack<Character> stack = new Stack<>();
            allStacks.add(stack);
            for(char initBox : initStack){
                if(initBox != ' '){
                    stack.push(initBox);
                }
            }
        }
    } 

    public static ArrayList<int[]> getInstructions(){
        ArrayList<int[]> instructions = new ArrayList<int[]>();
        try {
            File txtFile = new File("day5/input.txt");
            Scanner file = new Scanner(txtFile);
            boolean startInstructions = false;
            while(file.hasNextLine()){
                String line = file.nextLine();
                if(startInstructions){
                    int[] order = new int[3];
                    order[0] = Integer.parseInt(line.substring(line.indexOf("move ") + 5,line.indexOf("from") - 1));
                    order[1] = Integer.parseInt(line.substring(line.indexOf("from ") + 5,line.indexOf("to") - 1));
                    order[2] = Integer.parseInt(line.substring(line.indexOf("to ") + 3,line.length()));
                    instructions.add(order);
                }
                if(line.isEmpty()){
                    startInstructions = true;
                }
            }
            file.close();
        }catch(FileNotFoundException e){
            e.getMessage();
        }
        return instructions;
    }

    public static void handleStack(int numBoxes, int oldStack, int newStack, boolean partOne){
        if(partOne){
            for(int i = 0; i < numBoxes; i++){
                Character toPush = allStacks.get(oldStack - 1).pop();
                allStacks.get(newStack - 1).push(toPush);
            }
        }else{
            if(numBoxes == 1){
                Character toPush = allStacks.get(oldStack - 1).pop();
                allStacks.get(newStack - 1).push(toPush);
            }else{
                Character[] movedCharacter = new Character[numBoxes];
                for(int i = 0; i < numBoxes; i++){
                    movedCharacter[i] = allStacks.get(oldStack - 1).pop();
                }
                for(int i = 0; i < numBoxes; i++){
                    allStacks.get(newStack - 1).push(movedCharacter[numBoxes - i - 1]);
                }
            }    
        }
    }

    public static void getTop(){
        for(int i = 0; i < 9; i++){
            System.out.println(allStacks.get(i).peek());
        }
    }

}
