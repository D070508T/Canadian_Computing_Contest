import java.util.Scanner;

public class J5_2023 {
    public static int count = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String W = scanner.nextLine();
        int R = scanner.nextInt();
        int C = scanner.nextInt();
        char[][] board = new char[R][C];
        scanner.nextLine();
        for (int r = 0; r < R; r++) {
            load(scanner.nextLine(), board[r], 0);
        }

        for (int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if (board[i][j] == W.charAt(0)) {
                    for (int x = -1; x < 2; x++) {
                        for (int y = -1; y < 2; y++) {
                            if ((x != 0 || y != 0) && i+x >= 0 && i+x < R && j+y >= 0 && j+y < C && board[i+x][j+y] == W.charAt(1)) {
                                if (W.length() > 2) {
                                    foundWord(board, W, 2, i+x, j+y, x, y, true, R, C);
                                } else {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static boolean foundWord(char[][] board, String str, int index, int x, int y, int dx, int dy, boolean canTurn, int R, int C) {
        if (canTurn) {
            int[][] d;
            if (dx == 0 || dy == 0) {
                d = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            } else {
                d = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            }

            for (int i = 0; i < 4; i++) { // Go around in 90 degree intervals
                if (d[i][0] != -1*dx || d[i][1] != -1*dy) { // if you didn't make U-turn
                    if (x+d[i][0] >= 0 && x+d[i][0] < R && y+d[i][1] >= 0 && y+d[i][1] < C) { // if new coordinate is in bound
                        if (board[x+d[i][0]][y+d[i][1]] == str.charAt(index)) { // if new coordinate is next index
                            if (index == str.length() - 1) { // if index is the last
                                count++; // FINISHED WORD
                            } else { // if index is not last
                                boolean notTurn = d[i][0] == dx && d[i][1] == dy;
                                foundWord(board, str, index+1, x+d[i][0], y+d[i][1], d[i][0], d[i][1], notTurn, R, C); // search for next character
                            }
                        }
                    }
                }
            }
        } else {
            if (x+dx >= 0 && x+dx < R && y+dy >= 0 && y+dy < C && board[x+dx][y+dy] == str.charAt(index)) { // if new coordinate is in bound and new coordinate is next index

                if (index == str.length() - 1) { // if index is the last
                    count++; // FINISHED WORD
                } else { // if index is not last
                    return foundWord(board, str, index+1, x + dx, y + dy, dx, dy, false, R, C); // search for next character
                }

            }
        }
        return false;
    }

    public static String load(String str, char[] row, int index) {
        row[index] = str.charAt(0);
        if (str.length() == 1) {return "";}
        return load(str.substring(2), row, index+1);
    }
}