import java.util.Scanner;
import java.util.InputMismatchException;
public class SeatReservation{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        Airplane myAirplane = new Airplane("seatsmap.txt");
        int operation=0;
        do{
            System.out.println(myAirplane);
            System.out.println("Select an operation");
            System.out.println("1: reserve a seat");
            System.out.println("2: free a seat");
            System.out.println("3: quit");
            try{
                operation = keyboard.nextInt();
                String seatNumber;

                switch(operation){
                    case 1: //reserve
                        System.out.println("Enter a seat number:");
                        seatNumber = keyboard.next();
                        if(myAirplane.reserveSeat(seatNumber)){
                            System.out.println(seatNumber + " successfully reserved");
                        }
                        else{ // already reserved
                            System.out.println(seatNumber + " already reserved");
                        }
                        break;
                    case 2: //free
                        System.out.println("Enter a seat number:");
                        seatNumber = keyboard.next();
                        if(myAirplane.freeSeat(seatNumber)){
                            System.out.println(seatNumber + " successfully freed");
                        }
                        else{ // already free
                            System.out.println(seatNumber + " already free");
                        }
                        break;
                    case 3: //quit
                        myAirplane.saveMap("seatsmap.txt");
                        break;
                    default:
                        System.out.println("Invalid operation (1 to 3)");
                }
            }
            catch(InvalidSeatException e){
                System.out.println(e.getMessage());
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input operation");
                keyboard.next();
            }
        } while(operation != 3);
    }
}