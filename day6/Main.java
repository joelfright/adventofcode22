package day6;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        try {
            File txtFile = new File("day6/input.txt");
            Scanner file = new Scanner(txtFile);
            String datastream = file.nextLine();
            boolean duplicate = true;
            int index = 0;
            int partOne = 4;
            int partTwo = 14;
            while(duplicate){
                index++;
                duplicate = checkDuplicate(datastream, index, partOne);
            }
            System.out.println(index + partOne);
            file.close();
        }catch(FileNotFoundException e){
            e.getMessage();
        }

    }

    public static boolean checkDuplicate(String datastream, int index, int part){
        char[] segment = datastream.substring(index, index + part).toCharArray();
        Arrays.sort(segment);
        char prev = ' ';
        for(char letter : segment){
            if(letter == prev){
                return true;
            }
            prev = letter;
        }
        return false;
    }
    
}
