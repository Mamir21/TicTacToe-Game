import java.sql.SQLOutput;
import java.util.*;

public class TicTacToe1 {
    static ArrayList<Integer> playerPostitions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter which position to play (1-9):");
            int playerPos = scan.nextInt();
            while(playerPostitions.contains(playerPos)  || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken, Enter a correct Position");
                playerPos = scan.nextInt();
            }

            placeSymbol(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
//            printGameBoard(gameBoard);
//
//            System.out.println();

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPostitions.contains(cpuPos)  || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            placeSymbol(gameBoard, cpuPos, "cpu");

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            printGameBoard(gameBoard);
        }
    }
    public static void printGameBoard(char[][] gameBoard){
        for(char[] row: gameBoard) {
            for(char c: row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void placeSymbol(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';

        if(user.equals("player")) {
            symbol = 'X';
            playerPostitions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winningWays = new ArrayList<List>();
        winningWays.add(topRow);
        winningWays.add(midRow);
        winningWays.add(bottomRow);
        winningWays.add(leftCol);
        winningWays.add(midCol);
        winningWays.add(rightCol);
        winningWays.add(cross1);
        winningWays.add(cross2);

        for(List l : winningWays) {
            if(playerPostitions.containsAll(l)) {
                return "Congratulations you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU won!";
            } else if (playerPostitions.size() + cpuPositions.size() == 9) {
                return "Tie Game";
            }
        }

        return "";
    }
}
