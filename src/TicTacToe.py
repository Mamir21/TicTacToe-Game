import random

PLAYER_SYMBOL = 'X'
CPU_SYMBOL = 'O'
player_positions = []
cpu_positions = []

def print_game_board(game_board):
    for row in game_board:
        print(''.join(row))
    print()

def place_symbol(game_board, pos, symbol):
    if pos == 1:
        game_board[0][0] = symbol
    elif pos == 2:
        game_board[0][2] = symbol
    elif pos == 3:
        game_board[0][4] = symbol
    elif pos == 4:
        game_board[2][0] = symbol
    elif pos == 5:
        game_board[2][2] = symbol
    elif pos == 6:
        game_board[2][4] = symbol
    elif pos == 7:
        game_board[4][0] = symbol
    elif pos == 8:
        game_board[4][2] = symbol
    elif pos == 9:
        game_board[4][4] = symbol

def check_game_over():
    winning_combinations = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9],
        [1, 4, 7],
        [2, 5, 8],
        [3, 6, 9],
        [1, 5, 9],
        [7, 5, 3]
    ]
    
    for combo in winning_combinations:
        if all(pos in player_positions for pos in combo):
            print("Congratulations, you won!")
            return True
        elif all(pos in cpu_positions for pos in combo):
            print("CPU won!")
            return True

    if len(player_positions) + len(cpu_positions) == 9:
        print("Tie Game")
        return True

    return False

def player_turn(game_board):
    while True:
        try:
            player_pos = int(input("Enter which position to play (1-9): "))
            if player_pos < 1 or player_pos > 9 or player_pos in player_positions or player_pos in cpu_positions:
                print("Invalid position or already taken, enter a correct position.")
            else:
                break
        except ValueError:
            print("Invalid input. Please enter a number (1-9):")

    player_positions.append(player_pos)
    place_symbol(game_board, player_pos, PLAYER_SYMBOL)

def cpu_turn(game_board):
    available_positions = [pos for pos in range(1, 10) if pos not in player_positions and pos not in cpu_positions]
    cpu_pos = random.choice(available_positions)
    
    cpu_positions.append(cpu_pos)
    place_symbol(game_board, cpu_pos, CPU_SYMBOL)
    print_game_board(game_board)

def play_game():
    global player_positions, cpu_positions
    player_positions = []
    cpu_positions = []
    
    game_board = [
        [' ', '|', ' ', '|', ' '],
        ['-', '+', '-', '+', '-'],
        [' ', '|', ' ', '|', ' '],
        ['-', '+', '-', '+', '-'],
        [' ', '|', ' ', '|', ' ']
    ]

    print_game_board(game_board)

    while True:
        player_turn(game_board)
        if check_game_over():
            break

        cpu_turn(game_board)
        if check_game_over():
            break

if __name__ == "__main__":
    while True:
        play_game()
        play_again = input("Do you want to play again? (yes/no): ").strip().lower()
        if play_again != "yes":
            print("Thanks for playing. Goodbye!")
            break
