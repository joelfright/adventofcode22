package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        System.out.println(partOne());
        System.out.println(partTwo());
    }

    public static int partOne(){
        ArrayList<Character> letters = new ArrayList<>();
        try {
            File txtFile = new File("day3/input.txt");
            Scanner file = new Scanner(txtFile);
            while(file.hasNextLine()){
                String line = file.nextLine();
                char duplicate = getDuplicate(line.substring(0,line.length() / 2), line.substring(line.length() / 2, line.length()));
                letters.add(duplicate);
            }
            file.close();     
        }catch(FileNotFoundException e){
            e.getMessage();
        }

        int answer = 0;
        for(Character letter : letters){
            answer += getValue(letter.charValue());
        }
        return answer;
    }

    public static int partTwo(){
        int answer = 0;
        try {
            File txtFile = new File("day3/input.txt");
            Scanner file = new Scanner(txtFile);
            String[] lines = new String[3];
            int count = 0;
            while(file.hasNextLine()){
                String line = file.nextLine();
                lines[count] = line;
                
                if(count == 2){
                    answer += getValue(getMatching(lines[0], lines[1], lines[2]));
                    lines = new String[3];
                    count = -1;
                }  
                count++;
            }
            file.close();     
        }catch(FileNotFoundException e){
            e.getMessage();
        }

        return answer;
    }

    public static int getValue(char letter){
        int answer = 0;
        if(Character.isUpperCase(letter)){
            Character.toLowerCase(letter);
            answer += letter - 'A' + 27;
        }else{
            answer += letter - 'a' + 1;          
        }
        return answer;
    }

    public static char getDuplicate(String firstHalf, String secondHalf){
        for(char letterOne : firstHalf.toCharArray()){
            for(char letterTwo : secondHalf.toCharArray()){
                if(letterOne == letterTwo){
                    return letterOne;
                }
            }
        }
        return ' ';
    }

    public static char getMatching(String first, String second, String third){
        for(char letterOne : first.toCharArray()){
            for(char letterTwo : second.toCharArray()){
                for(char letterThree : third.toCharArray())
                if(letterOne == letterTwo && letterTwo == letterThree){
                    return letterOne;
                }
            }
        }
        return ' ';
    }

}