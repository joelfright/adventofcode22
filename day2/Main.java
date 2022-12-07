import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {

    static ArrayList<Integer[]> allStrategy = new ArrayList<>();
    static int[][] winLogic = {{1,2},{2,3},{3,1}};

    public static void main(String[] args){
        System.out.println(Integer.toString(partOne()));
        System.out.println(Integer.toString(partTwo()));
    }

    public static int partOne(){
        int answer = 0;
        for (Integer[] score : makeMapping(allStrategy)) {
            answer += score[1] + score(score[0], score[1]);
        }
        return answer;
    }
    
    public static int partTwo(){
        int total = 0;
        for (Integer[] score : makeMapping(allStrategy)) {
            if(score[1] == 1){ // Lose
                for(int[] lose : winLogic){
                    if(lose[1] == score[0]){
                        total += 0 + lose[0];
                    }
                }
            }else if(score[1] == 2){ // Draw
                total += 3 + score[0];
            }else if(score[1] == 3){ // Win
                for(int[] win : winLogic){
                    if(win[0] == score[0]){
                        total += 6 + win[1];
                    }
                }
            }
        }
        return total;
    }

    public static int score(Integer opponentChoice, Integer ownChoice){
        if((opponentChoice == 1 && ownChoice == 2) || (opponentChoice == 2 && ownChoice == 3) || (opponentChoice == 3 && ownChoice == 1)){
            return 6;
        }else if(opponentChoice == ownChoice){
            return 3;
        }else{
            return 0;
        }
    }

    public static ArrayList<Integer[]> makeMapping(ArrayList<Integer[]> allStrategy){
        try {
            File txtFile = new File("strategy.txt");
            Scanner file = new Scanner(txtFile);
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] strategyLine = line.split(" ", 2);
                Integer[] score = new Integer[2]; 
                for(String choice : strategyLine){   
                    if(choice.equals("A")){
                        score[0] = 1;
                    }else if(choice.equals("X")){
                        score[1] = 1;
                    }else if(choice.equals("B")){
                        score[0] = 2;
                    }else if(choice.equals("Y")){
                        score[1] = 2;
                    }else if(choice.equals("C")){
                        score[0] = 3;
                    }else if(choice.equals("Z")){
                        score[1] = 3;
                    }
                    
                }
                allStrategy.add(score);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return allStrategy;
    }


}

