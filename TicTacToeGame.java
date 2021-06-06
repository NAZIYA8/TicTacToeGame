package com.bridgelabz;

import java.util.Scanner;

public class TicTacToeGame {
    static Scanner scanner = new Scanner(System.in);
    static char[] board = new char[10];

    public static void main(String[] args) {
        createBoard();
        char player = takeUserInput();
        System.out.println("You chose " + player);
        char computer;
        if ((player == 'X')) {
            computer = 'O';
        } else {
            computer = 'X';
        }
        System.out.println("Computer chose " + computer);
        while (true) {
            int position = selectPosition(); //Select user position
            boolean playStatus = makeAMove(player, position, "User"); //Validate user position
            if (playStatus == true) { //Exit if user won or game over.
                break;
            }
            position = setComputerPosition(computer); //Select computer position
            playStatus = makeAMove(computer, position, "Computer"); //Validate computer position
            if (playStatus == true) { //Exit if computer won or game over.
                break;
            }
        }
    }

    /**
     * check if free space to make a move
     *
     * @param value    entered by user or computer
     * @param position position on board
     * @param name     user or computer
     */
    static boolean makeAMove(char value, int position, String name) {
        if (position <= 0) { //No position left to play.
            System.out.println("Game Over");
            return true;
        }
        System.out.println(name + " chose " + position);
        board[position] = value; //Set value at selected cell
        boolean win = isWin(board, value); //Check if won
        if (win) {
            System.out.println(name + " won!!!");
            return true;
        }
        showBoard();
        return false;
    }

    static boolean isWin(char[] arr, char value) {
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

    /**
     * A board is created of char[] size 10
     * Empty spaces are assigned
     * 0Th index is ignored
     */
    static void createBoard() {
        //Initialize board array
        for (int i = 1; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    static int setComputerPosition(char computer) {
        //Clone board array
        char[] arr = new char[board.length];
        for (int i = 1; i < board.length; i++) {
            arr[i] = board[i];
        }
        //Put values into cloned board and check if computer can win.
        int index = simulateWin(arr, computer);
        if ((index > 0)) { //Computer can win if value is assigned at idx position.
            board[index] = computer;
            return index;
        }
        char user;
        if (computer == 'X') { //Guess the  user value
            user = 'O';
        } else {
            user = 'X';
        }
        //Find out the next position where user can win and block it
        index = simulateWin(arr, user);
        if (index > 0) {
            board[index] = computer;
            return index;
        }
        //Since no winning position is available, fill next empty cell
        for (int i = 1; i < board.length; i++) {
            if (board[i] == ' ') {
                board[i] = computer;
                return i;
            }
        }
        return 0;
    }

    static int simulateWin(char[] arr, char value) {
        //Iterate through all the cells and check if can be won.
        for (int i = 1; i < 10; i++) {
            //Copy value to temporary buffer.
            char initialValue = arr[i];
            //Check if position is empty
            if (arr[i] == ' ') {
                //Since position is empty, assign value.
                arr[i] = value;
            }
            //Check if we can win.
            if (isWin(arr, value)) {
                return i;
            }
            //Restore value back
            arr[i] = initialValue;
        }
        //No win
        return -1;
    }

    /**
     * choose X OR O
     * Player and Computer letter is determined
     *
     * @return Input from the user
     */
    static char takeUserInput() {
        System.out.println("Input letter X or O.\n" +
                "Please not that the letters are case sensitive.");
        char input = scanner.next().charAt(0);
        if (input != 'X' && input != 'O') {
            System.out.println("Invalid input.");
            return takeUserInput();
        }
        return input;
    }

    /**
     * select position between 1 and 10
     * Ensure the index is free to select
     *
     * @return Selected position
     */
    static int selectPosition() {
        System.out.println("Select position between 1 and 10.");
        int input = scanner.nextInt();
        if (input < 1 || input > 10) {
            System.out.println("Invalid input. Must be between 1 and 10.");
            return selectPosition();
        } else if (board[input] != ' ') {
            System.out.println("Cell at position " + input + " is not empty.");
            return selectPosition();
        }
        return input;
    }

    /**
     * Prints a current board
     */
    static void showBoard() {
        System.out.println("_______");
        for (int i = 1; i < board.length; i += 3) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i + j] + "|");
            }
            System.out.println();
            System.out.println("_______");
        }
    }
}

