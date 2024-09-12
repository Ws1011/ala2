import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
//import 
public class Airplane{
    private char[][] seatMap;

    public Airplane(){
        seatMap = new char[9][8];
        for(int i=0; i<seatMap.length; i++){
            for(int j=0; j<seatMap[i].length; j++){
                seatMap[i][j] = '.';
            }
        }
    }
    public Airplane(String filename){
        seatMap = new char[9][8];
        readMap(filename);
    }
    private void readMap(String filename){
        File file = new File(filename);
        try{
            Scanner readFile = new Scanner(file); //open file for reading
            for(int i=0; i<seatMap.length; i++){
               for(int j=0; j<seatMap[i].length; j++){
                   seatMap[i][j] = readFile.next().charAt(0);  //read one char from file
               }
            }
            readFile.close();
        }
        catch(FileNotFoundException e){
            for(int i=0; i<seatMap.length; i++){
                for(int j=0; j<seatMap[i].length; j++){
                    seatMap[i][j] = '.';
                }
            }
        }
    }

    private boolean checkSeatNumber(String seatNumber) throws InvalidSeatException{
        if (seatNumber.matches("[1-9][A-H]")){
            return true;
        }
        throw new InvalidSeatException("Invalid Seat Number " + seatNumber + ". Must be [1-9]");
    }

    public boolean reserveSeat(String seatNumber) throws InvalidSeatException{
        if(checkSeatNumber(seatNumber)){
            int row = seatNumber.charAt(0) - '1';
            int col = seatNumber.charAt(1) - 'A';
            if(seatMap[row][col]=='.'){
                seatMap[row][col]='X';
                return true;
            }
        }
        return false;
    }

    public boolean freeSeat(String seatNumber) throws InvalidSeatException{
        if(checkSeatNumber(seatNumber)){
            int row = seatNumber.charAt(0) - '1';
            int col = seatNumber.charAt(1) - 'A';
            if(seatMap[row][col]=='X'){
                seatMap[row][col]='.';
                return true;
            }
        }
        return false;
    }

    public void saveMap(String filename){
        try{
            PrintWriter writeFile = new PrintWriter(new File(filename)); // open file
            for(int i=0; i<seatMap.length; i++){
                for(int j=0; j<seatMap[i].length; j++){
                    writeFile.print(seatMap[i][j] + " "); //write to file
                }
                writeFile.println();
            }
            writeFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot write to file");
        }
    }

    public String toString(){
        String out = "\tA\tB\tC\tD\tE\tF\tG\tH\n";
        for(int i=0; i<seatMap.length; i++){
            out += (i+1) + "\t";
            for(int j=0; j<seatMap[i].length; j++){
                out += seatMap[i][j] + "\t";
            }
            out += "\n";
        }
        return out;
    }
}