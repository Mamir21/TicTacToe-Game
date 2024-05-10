import java.util.*;

public class TicTacToe {
    private static final char PLAYER_SYMBOL = 'X';
    private static final char CPU_SYMBOL = 'O';

    private enum Player {
        PLAYER, CPU
    } 

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        while (true) { 
            playGame();

            System.out.println("Do you want to play again? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            String playAgain = scanner.nextLine().toLowerCase();

            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing. Goodbye!");
                break; 
            }

            playerPositions.clear();
            cpuPositions.clear();
        }
    }

    private static void playGame() {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printGameBoard(gameBoard);

        while (true) {
            playerTurn(gameBoard);
            if (checkGameOver()) break;

            cpuTurn(gameBoard);
            if (checkGameOver()) break;
        }
    }


    private static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void playerTurn(char[][] gameBoard) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter which position to play (1-9):");
        int playerPos = getPlayerInput(scan);

        while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
            System.out.println("Position taken, Enter a correct Position");
            playerPos = getPlayerInput(scan);
        }

        placeSymbol(gameBoard, playerPos, Player.PLAYER);
    }

    private static int getPlayerInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Invalid input. Please enter a number (1-9):");
            }
        }
    }

    private static void cpuTurn(char[][] gameBoard) {
        Random rand = new Random();
        int cpuPos = rand.nextInt(9) + 1;

        while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
            cpuPos = rand.nextInt(9) + 1;
        }

        placeSymbol(gameBoard, cpuPos, Player.CPU);
        printGameBoard(gameBoard);
    }
    
    private static void placeSymbol(char[][] gameBoard, int pos, Player player) {
        char symbol = (player == Player.PLAYER) ? PLAYER_SYMBOL : CPU_SYMBOL;

        if (player == Player.PLAYER) {
            playerPositions.add(pos);
        } else if (player == Player.CPU) {
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

    private static boolean checkGameOver() {
        List<List<Integer>> winningWays = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9),
                Arrays.asList(1, 4, 7),
                Arrays.asList(2, 5, 8),
                Arrays.asList(3, 6, 9),
                Arrays.asList(1, 5, 9),
                Arrays.asList(7, 5, 3)
        );

        for (List<Integer> l : winningWays) {
            if (playerPositions.containsAll(l)) {
                System.out.println("Congratulations, you won!");
                return true;
            } else if (cpuPositions.containsAll(l)) {
                System.out.println("CPU won!");
                return true;
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                System.out.println("Tie Game");
                return true;
            }
        } 

        return false;
    }
}
