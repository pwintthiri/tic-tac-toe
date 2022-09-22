import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe extends JPanel {
    boolean restart = false;
    char entry = 'x';
    int totalCells = 9;
    int totalRows = 3;
    int totalCols = 3;
    static JFrame jFrame = new JFrame("Tic Tac Toe!");
    JButton[] jButtons = new JButton[totalCells];
    
    

    public TicTacToe() {
        // Grid Layout with num of rows and cols
        GridLayout grid = new GridLayout(totalRows, totalCols);
        setLayout(grid);

        createButtons();
    }

    public void createButtons() {
        for (int i = 0; i <= 8; i++) {
            // create a new button with nothing inside (no x or o filled in yet)
            jButtons[i] = new JButton("");
            // actions that occur when a button is clicked
            jButtons[i].addActionListener(e -> {
                JButton clickedBtn = (JButton) e.getSource();
                // convert char into String, then set the text in the button clicked to the entry (x or o)
                clickedBtn.setText(String.valueOf(entry));
                // false - button cannot be clicked on anymore
                clickedBtn.setEnabled(false);

                // if last entry was 'x', the new symbol for entry will be o (another player's turn)
                if (entry =='x') {
                    entry = 'o';
                } else {
                    entry = 'x';
                }

                showWinner();
            });
            if (restart == false) {
                add(jButtons[i]);
            }
        }
    }

    public void showWinner() {
        if (checkForWinner()) {
            if (entry =='x') {
                entry = 'o';
            } else {
                entry = 'x';
            }
            JOptionPane jOptionPane = new JOptionPane();
            // options on the pop up JOptionPane
            Object[] options = {"EXIT", "RESTART"};
            // options[1] sets default option - that option will be highlighted
            int dialog = JOptionPane.showOptionDialog(jOptionPane, "Game Over. The winner is " + entry + " ", 
            "Result", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

            // YES_OPTION is the first option in the options list, NO_OPTION is the second option
            // this is determined by JOptionPane.YES_NO_OPTION above
            if (dialog == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else if (dialog == JOptionPane.NO_OPTION) {
                restart = true;
                restartGame();
            }
            
        } else if (checkforDraw()) {
            JOptionPane jOptionPane = new JOptionPane();
            // options on the pop up JOptionPane
            Object[] options = {"EXIT", "RESTART"};
            int dialog = JOptionPane.showOptionDialog(jOptionPane, "Game Draw.", 
            "Result", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

            if (dialog == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else if (dialog == JOptionPane.NO_OPTION) {
                restart = true;
                restartGame();
            }
        }
    }

    public boolean checkforDraw()  {
        boolean gridFull = true;
        // if any cell is still empty, set gridFull to false
        for (int i = 0; i < totalCells; i++) {
            if (jButtons[i].getText().equals("")) {
                gridFull = false;
            }
        }
        return gridFull;
    }

    public boolean checkForWinner() {
        return checkAllRows() || checkAllCols() || checkDiagonals();
    }

    public boolean checkAllRows() {
        // indices of first columns (i) is 0, 3, 6
        // check row by row whether the row has all the same entries (x or o)
        for (int i = 0; i <= 6; i += 3) {
            String leftCell = jButtons[i].getText();
            String centerCell = jButtons[i + 1].getText();
            String rightCell = jButtons[i + 2].getText();

            if (leftCell.equals(centerCell) && leftCell.equals(rightCell) &&!leftCell.equals("")) {
                return true; // there is a winner
            }
        }
        return false;
    }

    public boolean checkAllCols() {
        // iterate through the top row of columns (indices 0, 1, 2)
        for (int i = 0; i <= 2; i++) {
            String topCell = jButtons[i].getText();
            String centerCell = jButtons[i + 3].getText();
            String bottomCell = jButtons[i + 6].getText();

            if (topCell.equals(centerCell) && topCell.equals(bottomCell) && !topCell.equals("")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonals() {
        String centerCell = jButtons[4].getText();
        // check 0, 4, 8
        if (centerCell.equals(jButtons[0].getText()) && centerCell.equals(jButtons[8].getText()) && !centerCell.equals("")) {
            return true;
        } else if
            // check 2, 4, 6
            (centerCell.equals(jButtons[2].getText()) && centerCell.equals(jButtons[6].getText()) && !centerCell.equals("")) {
                return true;
        }
        return false;
    }

    public static void setJFrame(JFrame jFrame) {
        Color bgColor = new Color(231, 231, 253);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 600, 550);
        jFrame.setContentPane(new TicTacToe());
        jFrame.getContentPane().setBackground(bgColor);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);  
    }

    public static void restartGame() {
        // remove JFrame from previous game
        jFrame.dispose();

        jFrame = new JFrame("Tic Tac Toe!");
        setJFrame(jFrame);
    }

    
    public static void main(String[] args) {
        setJFrame(jFrame);
    }
    
}
