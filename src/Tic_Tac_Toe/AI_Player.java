/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

/**
 *
 * @author arpit
 */
public class AI_Player {

    public static class Move {

        int row, col;
    }

    static int player = 1, opponent = -1;

    private static boolean isMoveLeft(int[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int evaluate(int[][] board) {
        for (int i = 0; i < 5; i++) {
            if (board[i][0] + board[i][1] + board[i][2]+ board[i][3]+ board[i][4] == 5) {
                return 10;
            } else if (board[i][0] + board[i][1] + board[i][2]+ board[i][3]+ board[i][4] == -5) {
                return -10;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] + board[1][i] + board[2][i]+ board[3][i] + board[4][i] == 5) {
                return 10;
            }
            if (board[0][i] + board[1][i] + board[2][i]+ board[3][i] + board[4][i] == -5) {
                return -10;
            }
        }

        if (board[0][0] + board[1][1] + board[2][2]+ board[3][3] + board[4][4] == 5) {
            return 10;
        }
        if (board[0][0] + board[1][1] + board[2][2]+ board[3][3] + board[4][4] == -5) {
            return -10;
        }

       if (board[0][4] + board[1][3] + board[2][2] + board[3][1] + board[4][0] == 5) {
            return 10;
        }
        if (board[0][4] + board[1][3] + board[2][2] + board[3][1] + board[4][0] == 5) {
            return -10;
        }
        return 0;
    }
    // This is the minimax function. It considers all
// the possible ways the game can go and returns
// the value of the board

    private static int minimax(int[][] board, int depth, boolean isMax){
    int score = evaluate(board);

        // If Maximizer has won the game return his/her
        // evaluated score
        if (score == 10) {
            return score;
        }

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -10) {
            return score;
        }

        // If there are no more moves and no winner then
        // it is a tie
        if (isMoveLeft(board) == false) {
            return 0;
        }

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == 0) {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best,minimax(board, depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        } // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == 0) {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best,minimax(board, depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        }
    }

// This will return the best possible move for the player
    public static Move findBestMove(int[][] board){
    int bestVal = -1000;
        Move bestMove= new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evalutae minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Check if celll is empty
                if (board[i][j] == 0) {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = 0;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.println("The value of the best Move is:"+ bestVal);
        System.out.println("row = "+bestMove.row + " col = "+bestMove.col);

        return bestMove;
    }
}
