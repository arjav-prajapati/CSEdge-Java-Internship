import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class TicTacToe extends JFrame {
    final static int WIDTH = 450;
    final static int HEIGHT = 650;
    private int scoreX = 0;
    private int scoreDraw = 0;
    private int scoreY = 0;
    private boolean isXturn = true;
    private JButton[][] buttons = new JButton[3][3];
    private JLabel scoreXLabel = new JLabel("0", SwingConstants.CENTER);
    private JLabel scoreDrawLabel = new JLabel("0", SwingConstants.CENTER);
    private JLabel scoreYLabel = new JLabel("0", SwingConstants.CENTER);
    private JButton resetButton = new JButton("Reset");

    JPanel main = new JPanel();
    JPanel statsArea = new JPanel();
    JPanel matrix = new JPanel();
    RoundedPanel card1 = new RoundedPanel(20);
    RoundedPanel card2 = new RoundedPanel(20);
    RoundedPanel card3 = new RoundedPanel(20);

    // labels for the app
    JLabel playerXLable = new JLabel("PLAYER X");
    JLabel drawLabel = new JLabel("DRAW");
    JLabel playerYLable = new JLabel("PLAYER Y");

    // fonts
    Font normalBoldFont = new Font("arial", Font.BOLD, 28);
    Font bigBoldFont = new Font("arial", Font.BOLD, 40);

    TicTacToe() {
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0x250038));
        setVisible(true);
        setTitle("Tic-Tac-Toe");

        // structuring main body
        main.setBackground(new Color(0x250038));
        main.setLayout(null);
        main.setBounds(0, 0, WIDTH, HEIGHT);
        main.setVisible(true);

        // structuring stats area
        statsArea.setLayout(new BoxLayout(statsArea, BoxLayout.X_AXIS));
        // card, dimensions (width and height, bgColor, labels)
        statsCard(card1, 70, 70, 0x43ccfb, playerXLable, scoreXLabel);
        statsCard(card2, 70, 70, 0x43ccfb, drawLabel, scoreDrawLabel);
        statsCard(card3, 70, 70, 0x43ccfb, playerYLable, scoreYLabel);

        statsArea.setBackground(new Color(0x250038));
        statsArea.setBounds(60, 50, 320, 80);
        statsArea.setVisible(true);
        statsArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsArea.add(Box.createHorizontalStrut(35));
        statsArea.add(card1);
        statsArea.add(Box.createHorizontalStrut(20));
        statsArea.add(card2);
        statsArea.add(Box.createHorizontalStrut(20));
        statsArea.add(card3);

        // structuring matrix area
        matrix.setLayout(new GridLayout(3, 3, 10, 10)); // 3x3 grid with 10px gaps
        matrix.setBounds(60, 150, 320, 320); // Position and size of the matrix
        matrix.setBackground(new Color(0x250038));

        // add buttons to the matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(70, 70));
                button.setBackground(new Color(0x3b114f));
                button.setForeground(new Color(0xFFFFFF));
                button.setFont(bigBoldFont);
                button.addActionListener(e -> addLabel(e, button));
                buttons[i][j] = button;
                matrix.add(button);
            }
        }

        // structuring reset button
        resetButton.setBounds(40, 520, 360, 40);
        resetButton.setBackground(new Color(0xa584b6));
        resetButton.setForeground(Color.BLACK);
        resetButton.addActionListener(e -> resetGame());

        main.add(statsArea);
        main.add(matrix);
        main.add(resetButton);
        add(main);
    }

    private void statsCard(JPanel card, int width, int height, int bgColor, JLabel label1, JLabel label2) {
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setFont(normalBoldFont);
        card.add(Box.createVerticalStrut(8));
        card.add(label1);
        card.add(Box.createVerticalStrut(2));
        card.add(label2);
        card.setBackground(new Color(bgColor));
        card.setPreferredSize(new Dimension(width, height));
        card.setMinimumSize(new Dimension(width, height));
        card.setMaximumSize(new Dimension(width, height));
    }

    private void addLabel(ActionEvent e, JButton button) {
        if (button.getText().isEmpty()) {
            button.setText(isXturn ? "X" : "O");
            isXturn = !isXturn;
            checkGameState();
        }
    }

    private void checkGameState() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].isEmpty()) {
                handleWin(board[i][0]);
                return;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].isEmpty()) {
                handleWin(board[0][i]);
                return;
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].isEmpty()) {
            handleWin(board[0][0]);
            return;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].isEmpty()) {
            handleWin(board[0][2]);
            return;
        }

        // Check for draw
        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            handleDraw();
        }
    }

    private void handleWin(String winner) {
        JOptionPane.showMessageDialog(this, winner + " wins!");
        if (winner.equals("X")) {
            scoreX++;
            scoreXLabel.setText(String.valueOf(scoreX));
        } else {
            scoreY++;
            scoreYLabel.setText(String.valueOf(scoreY));
        }
        resetMatrix();
    }

    private void handleDraw() {
        JOptionPane.showMessageDialog(this, "It's a draw!");
        scoreDraw++;
        scoreDrawLabel.setText(String.valueOf(scoreDraw));
        resetMatrix();
    }

    private void resetMatrix() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        isXturn = true;
    }

    private void resetGame() {
        scoreX = 0;
        scoreDraw = 0;
        scoreY = 0;
        scoreXLabel.setText("0");
        scoreDrawLabel.setText("0");
        scoreYLabel.setText("0");
        resetMatrix();
    }
}

class RoundedPanel extends JPanel {
    private int radius;

    public RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
    }
}

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
