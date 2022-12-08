package day7;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Main {

    static Tree<String[]> directory;
    static Tree<String[]> currentNode;
    static int answer = 0;
    static int currentMin = 70000000;
    static int used = 0;
    static int unused = 0;
    static int needed = 0;

    public static void main(String args[]){
        try {
            File txtFile = new File("day7/input.txt");
            Scanner file = new Scanner(txtFile);
            while(file.hasNextLine()){
                String line = file.nextLine();
                boolean command = line.charAt(0) == '$' ? true : false;
                if(command){
                    boolean cd = line.contains("cd") ? true : false;
                    if(cd){
                        String location = line.substring(5, line.length());
                        if(location.equals("/")){
                            directory = createTree();
                            currentNode = directory;
                        }else if(location.equals("..")){
                            currentNode = currentNode.getParent();
                        }else{
                            currentNode = getNodeByName(location);
                        }
                    }
                }else{
                    String[] operation = line.split(" ");
                    if(operation[0].equals("dir")){
                        addNode(currentNode, "dir", operation[1], "0");
                    }else{
                        addNode(currentNode, "file", operation[1], operation[0]);
                    }
                }
                
            }
            filterSizing(directory);
            System.out.println(partOne(directory));
            used = totalSize();
            unused = 70000000 - used;
            needed = 30000000 - unused;
            System.out.println(partTwo(directory));
            file.close();
        }catch(FileNotFoundException e){
            e.getMessage();
        }
    }

    public static <T> void printTree(Tree<String[]> node, String append) {
        System.out.println(append + node.getData()[0] + " " + node.getData()[1] + " | " + node.getData()[2]);
        node.getChildren().forEach(each ->  printTree(each, append + append));
    }

    public static <T> void filterSizing(Tree<String[]> root) {
        int size = 0;
        for(Tree<String[]> element : root.getChildren()){
            if(element.getData()[0].equals("dir")){
                filterSizing(element);
            }
            size += Integer.parseInt(element.getData()[2]);
        }
        if(root.getData()[0].equals("dir")){
            String[] data = {"dir", root.getData()[1], Integer.toString(size)};
            root.setData(data);
        }
    }

    public static int partOne(Tree<String[]> root) {
        for(Tree<String[]> element : root.getChildren()){
            if(element.getData()[0].equals("dir") && (Integer.parseInt(element.getData()[2]) <= 100000) ){
                answer = answer + Integer.parseInt(element.getData()[2]);
            }
            partOne(element);
        }
        return answer;
    }

    public static int totalSize(){
        return Integer.parseInt(directory.getRoot().getData()[2]);
    }

    public static int partTwo(Tree<String[]> root){
        for(Tree<String[]> element : root.getChildren()){
            int sizeOfDir = (Integer.parseInt(element.getData()[2]));
            if(element.getData()[0].equals("dir") && (needed < sizeOfDir) && (currentMin > sizeOfDir)){
                currentMin = (Integer.parseInt(element.getData()[2]));
            }
            partTwo(element);
        }
        return currentMin;
    }

    public static Tree<String[]> createTree(){
        String[] features = {"dir", "/", "0"};
        Tree<String[]> root = new Tree<>(features);
        return root;
    }

    public static void addNode(Tree<String[]> parent, String type, String name, String size){
        String[] features = {type, name, size};
        parent.addChild(new Tree<>(features));
    }

    public static String[] getNode(Tree<String[]> node){
        return node.getData();
    }

    public static Tree<String[]> getNodeByName(String fileName){
        for (Tree<String[]> element : currentNode.getChildren()) {
            if(element.getData()[1].equals(fileName)){
                return element;
            }
        }
        return null;
    }
    
}
