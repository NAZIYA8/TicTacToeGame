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
        System.out.println("Computer is assigned with " + computer);
        showBoard();
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

