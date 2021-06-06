package com.bridgelabz;
import java.util.Scanner;

public class TicTacToeGame {
    static Scanner scanner = new Scanner(System.in);
    static char[] board = new char[10];

    public static void main(String[] args){
        createBoard();
        System.out.println("Board is created");
    }
    static void createBoard(){
        //Initialize board array
        for(int i=1; i<board.length; i++){
            board[i] =' ';
        }
    }

}
