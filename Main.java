import java.util.Scanner;

public class Main
{

    private static final int BOARD_SIZE = 3;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY_CELL = ' ';

    private char[][] board;
    private char currentPlayer;

    public Main() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYER_X;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY_CELL;
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");

        do {
            displayBoard();
            int row, col;
            do {
                System.out.println("Player " + currentPlayer + ", enter row (1-" + BOARD_SIZE + ") and column (1-" + BOARD_SIZE + "): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            } while (!isValidMove(row, col));

            board[row][col] = currentPlayer;

            if (isWinningMove(row, col)) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull()) {
                displayBoard();
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        } while (true);

        scanner.close();
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY_CELL;
    }

    private boolean isWinningMove(int row, int col) {
        char player = board[row][col];

        // Check row
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player)
            return true;

        // Check column
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player)
            return true;

        // Check diagonals
        if ((row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (row + col == BOARD_SIZE - 1 && board[0][2] == player && board[1][1] == player && board[2][0] == player))
            return true;

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL)
                    return false;
            }
        }
        return true;
    }

    private void displayBoard() {
        System.out.println("-------------");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print("| ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}