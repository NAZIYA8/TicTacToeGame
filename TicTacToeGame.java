package com.bridgelabz;

import java.util.Scanner;

public class TicTacToeGame {
    static Scanner scanner = new Scanner(System.in);
    static char[] board = new char[10];

    public static void main(String[] args){
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
        int position = selectPosition(); //Select user position
        makeAMove(player,position, "User"); //Validate user position
    }
    /**
     * A board is created of char[] size 10
     * Empty spaces are assigned
     * 0Th index is ignored
     */
    static void createBoard(){
        //Initialize board array
        for(int i=1; i<board.length; i++){
            board[i] =' ';
        }
    }
    /**
     *choose X OR O
     * Player and Computer letter is determined
     * @return Input from the user
     */
    static char takeUserInput(){
        System.out.println("Input letter X or O.\n" +
                "Please not that the letters are case sensitive.");
        char input = scanner.next().charAt(0);
        if(input != 'X' && input != 'O'){
            System.out.println("Invalid input.");
            return takeUserInput();
        }
        return input;
    }

    /**
     * Prints a current board
     */
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
    /**
     * select position between 1 and 10
     * Ensure the index is free to select
     * @return Selected position
     */
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

    /**
     * check if free space to make a move
     *
     * @param value    entered by user or computer
     * @param position position on board
     * @param name     user or computer
     */
    static void makeAMove(char value, int position, String name){
        if(position <= 0){ //No position left to play.
            System.out.println("Game Over");
            return;
        }
        System.out.println(name + " chose " + position);
        board[position] = value; //Set value at selected cell

    }

}
