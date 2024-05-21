public class GameOfLife {
    public static void main(String[] args) {
        int[][] board = {{0,1,0,0,1,0,0,1,0,0,1,0},{0,1,1,1,1,1,1,1,0,1,1,1},{1,1,1,0,0,1,0,0,1,0,0,0,},{0,0,0,0,0,1,1,1,1,1,1,1}};
        int numOfGeneration = 10;

        for (int i = 0; i < numOfGeneration; i++) {
            System.out.println("Board before generation" + i);
            print(board);
            computeNextGeneration(board);
            System.out.println("Board after generation" + i);
            print(board);
            System.out.println("xxxxxxxxxxxxxxxxxx");
        }

    }

    /*  Time Complexity: O(row*Col)
        Space Complexity: O(1)  
    */
    public static void print(int[][] board) {
        int r = board.length, c = board[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    /*  Time Complexity: O(row*Col*8) ~ O(row*Col)
        Space Complexity: O(row*Col)  
    */
    public static void computeNextGeneration(int[][] board) {
        int r = board.length, c = board[0].length;
        // making a copy of existing board to preserve old values to refer back
        int[][] boardCopy = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) 
                boardCopy[i][j] = board[i][j];
        // storing postion coordinates for all possible directions 
        int[][] moves = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};
        int m, n, z=0, o=0;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                // reset counters
                z = 0; o = 0;
                for (int k = 0; k < moves.length; k++) {
                    m = i+moves[k][0];
                    n = j+moves[k][1];
                    // if valid move then inc counters
                    if (m >= 0 && m < r && n >= 0 && n < c) {
                        z = (boardCopy[m][n] == 0)?z+1:z;
                        o = (boardCopy[m][n] == 1)?o+1:o;
                    }
                }
                // updating board according to rules of game
                if (board[i][j] == 1) {
                    // dies if less than 2 (underpopulation) or more than 3 (overpopulation) live neighbors 
                    if (o < 2 || o > 3) board[i][j] = 0;
                } else board[i][j] = (o == 3)?1:0; // lives in case of exactly 3 live neighbors (reproduction)
            }
    }
}
