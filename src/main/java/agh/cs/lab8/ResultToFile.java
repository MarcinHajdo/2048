package agh.cs.lab8;


import java.io.*;
import java.util.Scanner;

public class ResultToFile {
    public void writeHighestResult(int result){
        try {
            FileWriter myWriter = new FileWriter("result.txt");
            myWriter.write(String.valueOf(result));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public int readHighestResult() throws FileNotFoundException {
            int result;
            File myObj = new File("result.txt");
            if(myObj.exists()){
                Scanner myReader = new Scanner(myObj);
                String data = myReader.nextLine();
                myReader.close();
                result = Integer.parseInt(data);
            }
            else{
                result = 0;
            }

        return result;
    }
}
