package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        try {
            File txtFile = new File("day4/input.txt");
            Scanner file = new Scanner(txtFile);
            int answer1 = 0;
            int answer2 = 0;
            while(file.hasNextLine()){
                String line = file.nextLine();
                int elfs = 2;
                int time = 2;
                int[][] assignment = new int[elfs][time];
                for(int i = 0; i < elfs; i++){
                    for(int j = 0; j < time; j++){
                        assignment[i][j] = Integer.parseInt(line.split(",")[i].split("-")[j]);
                    }
                }
                if((assignment[0][0] <= assignment[1][0] && assignment[0][1] >= assignment[1][1])
                    || (assignment[0][0] >= assignment[1][0] && assignment[0][1] <= assignment[1][1])){
                    answer1++;
                }   
                if(!(assignment[0][1] < assignment[1][0] || assignment[0][0] > assignment[1][1])){
                    answer2++;
                }
            }
            System.out.println(answer1);
            System.out.println(answer2);
            file.close();
        }catch(FileNotFoundException e){
            e.getMessage();
        }
    }

}
