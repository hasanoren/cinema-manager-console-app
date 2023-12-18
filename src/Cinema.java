import java.util.Scanner;

public class Cinema {


    public static void main(String[] args) {





        boolean[] state= new boolean[]{true};
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scan.nextInt();
        String[][] twoDimArray = new String[numberOfRows][numberOfSeats];
        System.out.println(twoDimArray.length);



        createCinema(numberOfRows,numberOfSeats,twoDimArray);



        int totalSeats = (numberOfSeats) * (numberOfRows);
        int[] array=new int[4]; //total seats //totalincome/currentincome

        array[0]=totalSeats;
        array[1]=0;
        array[2]=0;
        array[3]=0;


        calcTotalIncome(numberOfRows,numberOfSeats,array);

        System.out.println(array[1]);

        while (state[0]){
            createMenu(numberOfRows,numberOfSeats,twoDimArray,state,array);
        }


    }

    public static void createCinema(int numberOfRows,int numberOfSeats,String[][] twoDimArray){
        System.out.println("Cinema:");
        for (int i = 1; i <numberOfSeats+1 ; i++) {
            System.out.print(" "+i);
        }
        System.out.println();

        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                twoDimArray[i][j] = "S";
            }
        }
        for (int i = 0; i < twoDimArray.length; i++) {
            System.out.print(i+1+" ");
            for (int j = 0; j < twoDimArray[i].length; j++) {

                System.out.print(twoDimArray[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void showSeats(String[][] twoDimArray, int numberOfSeats){
        System.out.println("Cinema:");
        for (int i = 0; i <numberOfSeats ; i++) {
            System.out.print(" "+(i+1));
        }
        System.out.println();
        for (int i =0; i < twoDimArray.length; i++) {
            System.out.print(i+1+" ");
            for (int j = 0; j < twoDimArray[i].length; j++) {

                System.out.print(twoDimArray[i][j] + " ");

            }
            System.out.println();
        }
    }

    public static void createMenu(int numberOfRows, int numberOfSeats, String[][] twoDimArray, boolean[] state,int[] array) {
        Scanner scan = new Scanner(System.in);





        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int selection = scan.nextInt();


        switch (selection) {

            case 1:
                showSeats(twoDimArray, numberOfSeats);
                break;
            case 2:
                buyATicket(numberOfRows,numberOfSeats, twoDimArray,array);
                break;
            case 3:
                showStatistics(twoDimArray,array);
                break;
            case 0:
                state[0]=false;
                break;


        }


    }

    public static void calcTotalIncome(int numberOfRows, int numberOfSeats, int[] array){
        if (array[0] <= 60) {
            array[1]=(numberOfRows)*(numberOfSeats)*10;




        } else {
            array[1]=(numberOfRows/2*10*numberOfSeats + (numberOfRows-(numberOfRows/2))*numberOfSeats*8);


        }
    }

    public static void showStatistics(String[][] twoDimArray, int[] array) {



        // Number of Tickets
        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                if ( twoDimArray[i][j].equals("B")){
                    array[3]+=1;
                }
            }

        }

        System.out.println("Number of purchased tickets: "+array[3]);

        if(array[3]!=0){
            float percentage=(float)array[3]/(float)array[0]*100;
            System.out.printf("Percentage: %.2f%%",percentage);
            System.out.println();
        }
        else{
            System.out.println("Percentage :0.00%");

        }

        System.out.printf("Current income: $%s",(int)array[2]);
        System.out.println();
        System.out.printf("Total income: $%s",(int)array[1]);
        System.out.println();
        array[3]=0;
    }

    private static void buyATicket(int numberOfRows, int numberOfSeats, String[][] twoDimArray, int[] array) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowNumber = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scan.nextInt();



        if (rowNumber>numberOfRows || seatNumber>numberOfSeats) {

            System.out.println("Wrong input!");
        }
       else if(twoDimArray[rowNumber-1][seatNumber-1].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            buyATicket(numberOfRows,numberOfSeats,twoDimArray,array);
        }
       else{
            twoDimArray[rowNumber-1][seatNumber-1]="B";
            if (array[0] <= 60) {

                System.out.println("Ticket price: $10");
                array[2] += 10;
            }
            else{
                    int ticketPrice = rowNumber <= (numberOfRows) / 2 ? 10 : 8;
                    System.out.println("Ticket price: $" + ticketPrice);
                    array[2]+=ticketPrice;
                }

            }
        }


        


        

    }








