import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean isPlayerOne;
    private boolean isGameOver;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[3][3];
        isPlayerOne = true;
        isGameOver = false;

        setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 100));
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (!isGameOver && buttonClicked.getText().isEmpty()) {
            if (isPlayerOne) {
                buttonClicked.setText("X");
            } else {
                buttonClicked.setText("O");
            }
            isPlayerOne = !isPlayerOne;

            if (checkForWin()) {
                JOptionPane.showMessageDialog(this, (isPlayerOne ? "Player 2 (O)" : "Player 1 (X)") + " wins!");
                isGameOver = true;
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                isGameOver = true;
            }
        }
    }

    private boolean checkForWin() {
        String[][] board = new String[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = buttons[row][col].getText();
            }
        }

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (!board[row][0].isEmpty() && board[row][0].equals(board[row][1])
                    && board[row][0].equals(board[row][2])) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!board[0][col].isEmpty() && board[0][col].equals(board[1][col])
                    && board[0][col].equals(board[2][col])) {
                return true;
            }
        }

        // Check diagonals
        if (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            return true;
        }
        if (!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
            return true;
        }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
