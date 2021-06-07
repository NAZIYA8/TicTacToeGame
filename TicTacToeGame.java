package com.bridgelabz;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    static Scanner scanner = new Scanner(System.in);
    static char[] board = new char[10];

    public static void main(String[] args){

        while(true){
            playGame();
            if(!confirmReplay()){
                System.out.println("Thank you!!!");
                break;
            }
        }
    }
    static boolean confirmReplay(){
        System.out.println("Do you want to play again? Press Y if you want to play. and N if you want to quit.");
        String userInput = scanner.next();
        userInput = userInput.toLowerCase(Locale.ROOT);
        if(userInput.indexOf(0) == 'y'){
            return true;
        }else if(userInput.indexOf(0) == 'n'){
            return false;
        }else{
            System.out.println("Please enter valid keyword. [Y/N]");
            return confirmReplay();
        }
    }
    static void playGame(){
        createBoard();
        char player = takeUserInput();
        System.out.println("You chose " + player);
        char computer;
        if((player == 'X')){
            computer = 'O';
        }else{
            computer = 'X';
        }
        System.out.println("Computer is assigned with " + computer);
        showBoard();
        boolean userWonTheToss = tossTheCoin();
        String[] playerNames = new String[]{"",""};
        if(userWonTheToss){
            playerNames[0] = "User";
            playerNames[1] = "Computer";
        }else{
            playerNames[0] = "Computer";
            playerNames[1] = "User";
        }

        boolean playStatus = false;
        while(!playStatus) {
            int position;
            for (int i=0; i<2; i++){
               // boolean playerNames;
                if(playerNames[i] == "Computer"){
                    position = setComputerPosition(computer); //Select computer position
                }else{
                    position = selectPosition(); //Select user position
                }
                playStatus = makeAMove(player,position, playerNames[i]); //Validate position
            }
        }
    }
    static void createBoard(){
        //Initialize board array
        for(int i=1; i<board.length; i++){
            board[i] =' ';
        }
    }

    static char takeUserInput(){
        System.out.println("Input letter X or O.\n" +
                "Please not that the letters are case sensitive.");
        char ipt = scanner.next().charAt(0);
        if(ipt != 'X' && ipt != 'O'){
            System.out.println("Invalid input.");
            return takeUserInput();
        }
        return ipt;
    }

    static void showBoard(){
        System.out.println("_______");
        for(int i=1; i< board.length; i+=3){
            System.out.print("|");
            for(int j=0; j < 3; j++){
                System.out.print(board[i+j] + "|");
            }
            System.out.println();
            System.out.println("_______");
        }
    }

    static int selectPosition(){
        System.out.println("Select position between 1 and 10.");
        int input = scanner.nextInt();
        if(input < 1 || input > 10){
            System.out.println("Invalid input. Must be between 1 and 10.");
            return selectPosition();
        }else if(board[input] != ' '){
            System.out.println("Cell at position " + input + " is not empty.");
            return selectPosition();
        }
        return input;
    }

    static boolean makeAMove(char value, int position, String name){
        if(position <= 0){ //No position left to play.
            System.out.println("Game Over");
            return true;
        }
        System.out.println(name + " chose " + position);
        board[position] = value; //Set value at selected cell
        boolean win = isWin(board, value); //Check if won
        if(win){
            System.out.println(name + " won!!!");
            return true;
        }
        showBoard();
        return false;
    }



    static boolean tossTheCoin(){
        System.out.println("1 means head, 0 means tail.");
        System.out  .println("Please enter 1 for head, 0 for tail.");
        int userValue = scanner.nextInt();
        if(userValue == 0){
            System.out.println("You chose tail.");
        }else if(userValue == 1){
            System.out.println("You chose head");
        }else{
            System.out.println("You must chose either 0 or 1.");
            return tossTheCoin();
        }
        System.out  .println("Tossing the coin.....");
        int toss  = new Random().nextInt() * 2;
        if(toss == 0){
            System.out.println("It's tail.");
        }else{
            System.out.println("It's head.");
        }
        if(userValue == toss){
            System.out.println("You won the toss.");
            return true;
        }else{
            System.out.println("Computer won the toss.");
        }
        return false;
    }

    static boolean isWin(char[] arr, char value){
        if ((arr[1] == value) &&                            //1st cell of 1st row
                ((arr[2] == value && arr[3] == value)           //2nd and 3rd cell of 1st row (Horizontal)
                        || (arr[4] == value && arr[7] == value) // 1st column (Vertical)
                        || (arr[5] == value && arr[9] == value) // Diagonal
                )) {
            return true;
        }
        if (arr[2] == value && arr[5] == value && arr[8] == value) {
            return true;
        }
        if (arr[3] == value && ((arr[6] == value && arr[9] == value)
                || arr[5] == value && arr[7] == value)) {
            return true;
        }
        if (arr[4] == value && arr[5] == value && arr[6] == value) {
            return true;
        }
        if (arr[7] == value && arr[8] == value && arr[9] == value) {
            return true;
        }
        return false;
    }

    static int setComputerPosition(char computer){
        //Clone board array
        char[] arr = new char[board.length];
        for(int i=1; i<board.length; i++){
            arr[i] = board[i];
        }
        //Put values into cloned board and check if computer can win.
        int idx = simulateWin(arr,computer);
        if((idx > 0)){ //Computer can win if value is assigned at idx position.
            board[idx] = computer;
            return idx;
        }
        char user;
        if(computer == 'X'){ //Guess the  user value
            user = 'O';
        }else{
            user = 'X';
        }
        //Find out the next position where user can win and block it
        idx = simulateWin(arr,user);
        if(idx > 0){
            board[idx] = computer;
            return idx;
        }

        // Check if corners are available:
        int[] corners =new int[] {1,3,4,6,7,9};
        for(int i=1; i<10; i++){
            if(Arrays.binarySearch(corners,i) >0 && board[i] == ' '){
                return i;
            }
        }
        if(board[5] == ' '){ //Check if center is available.
            return 5;
        }

        //Since no winning position is available, fill next empty cell
        for(int i=1; i<board.length; i++){
            if(board[i] == ' '){
                board[i] = computer;
                return i;
            }
        }
        return 0;
    }

    static int simulateWin(char[] arr, char value){
        //Iterate through all the cells and check if can be won.
        for(int i=1; i<10; i++){
            //Copy value to temporary buffer.
            char initialValue = arr[i];
            //Check if position is empty
            if(arr[i] == ' '){
                //Since position is empty, assign value.
                arr[i] = value;
            }
            //Check if we can win.
            if(isWin(arr,value)){
                return i;
            }
            //Restore value back
            arr[i] = initialValue;
        }
        //No win
        return -1;
    }
}
