import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;

public class SudokuSolverController {

    JTextField[][] fields = new JTextField[9][9];
    public SudokuSolverController( SudokuSolver solver){
        SwingUtilities.invokeLater(() -> createWindow(solver,"BookReader", 100, 300));
    }

    private void createWindow(SudokuSolver solver, String title,int width, int height) {
        JFrame frame = new JFrame(title);
        JPanel panel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JButton solve = new JButton("Solve");
        JButton clear = new JButton("Clear");

        clear.addActionListener(x -> {
            solver.clear();
            for (Component field : panel.getComponents()) {
                ((JTextField)field).setText("");

            }
        });
        solve.addActionListener(x ->{

           Component[] complist =  panel.getComponents();
           int counter =0;
            int[][] data = new int[9][9];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    String temptext =((JTextField) complist[counter]).getText();
                    data[i][j] = Integer.parseInt((temptext.isEmpty() ? "0": temptext));
                    counter++;
                }
            }

            solver.init(data);  
            
            if(solver.solve()){
                
                data = solver.getBoard();
                counter = 0;
                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data.length; j++) {
                        ((JTextField) complist[counter]).setText(Integer.toString(data[i][j]));;
                        counter++;
                    }
                }
            }else {
                JOptionPane.showMessageDialog(frame,"Går ej att lösa","Alert",JOptionPane.WARNING_MESSAGE);
                clear.doClick();
            }
            
            

        });
        bottomPanel.add(solve);
        bottomPanel.add(clear);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout experimentLayout = new GridLayout(9,9);
        panel.setLayout(experimentLayout); 
        Font defaultFont = new Font("serif", Font.BOLD, 20);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField temp = new JTextField();
                temp.setFont(defaultFont);
                
                fields[i][j] = temp;
                temp.setHorizontalAlignment(JTextField.CENTER);
                DocumentListener documentListener = new DocumentListener() {
                    public void changedUpdate(DocumentEvent documentEvent) {
                      printIt(documentEvent);
                    }
                    public void insertUpdate(DocumentEvent documentEvent) {
                      printIt(documentEvent);
                    }
                    public void removeUpdate(DocumentEvent documentEvent) {
                      printIt(documentEvent);
                    }
                    private void printIt(DocumentEvent documentEvent) {
                        int x = 0;
                        try {
                           x = Integer.parseInt(temp.getText());
                        } catch (Exception e) {
                            temp.setForeground(Color.red);
                            solve.setEnabled(false);
                        }
                        if(temp.getText().isEmpty()) {
                            temp.setForeground(Color.black);
                            solve.setEnabled(true);
                        }
                        else if((x > 9 || x < 1)){
                            temp.setForeground(Color.red);
                            solve.setEnabled(false);
                            JOptionPane.showMessageDialog(frame,"Välj ett nummer mellan 1 - 9","Alert",JOptionPane.WARNING_MESSAGE);
    
                        }
                    
                    }
                  };
                  temp.getDocument().addDocumentListener(documentListener);
                panel.add(temp);
            }
        }
        Color tempcolor = Color.decode("#ff7f50");
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                fields[1+i][1+j].setBackground(tempcolor);
                fields[1+i][7+j].setBackground(tempcolor);
                fields[4+i][4+j].setBackground(tempcolor);
                fields[7+i][1+j].setBackground(tempcolor);
                fields[7+i][7+j].setBackground(tempcolor);
            }
        }
        
        
       
        

        Container pane = frame.getContentPane();
        pane.add(panel);
        pane.add(bottomPanel, BorderLayout.SOUTH);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}
