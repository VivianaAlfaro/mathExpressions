import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

public class calcu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Panel para la información del usuario
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel nameLabel = new JLabel("Nombre: ");
        JTextField nameField = new JTextField(20);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));

        userInfoPanel.add(nameLabel);
        userInfoPanel.add(nameField);

        frame.add(userInfoPanel, BorderLayout.NORTH);

        JPanel expressionPanel = new JPanel();
        expressionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JTextField expressionField = new JTextField(20);
        expressionField.setFont(new Font("Arial", Font.PLAIN, 20));
        expressionPanel.add(expressionField);

        frame.add(expressionPanel, BorderLayout.CENTER);

        JLabel dateLabel = new JLabel("Fecha actual: ");
        JLabel dateValueLabel = new JLabel(getCurrentDate());
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateValueLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        datePanel.add(dateLabel);
        datePanel.add(dateValueLabel);

        frame.add(datePanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 5, 5, 5));

        String[] buttonLabels = {
            "7", "8", "9", "C", "|",
            "4", "5", "6", "*", "^",
            "1", "2", "3", "-", "~",
            "0", "(", ")", "+", "&",
            "=", "**", "^", "/","%", 
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            buttonPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (label.equals("C")) {
                        expressionField.setText("");
                    } else if (label.equals("=")) {
                        evaluateExpression(expressionField.getText());
                    } else {
                        expressionField.setText(expressionField.getText() + label);
                    }
                }
            });
        }

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Utilizamos un Timer para actualizar la fecha cada segundo
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateValueLabel.setText(getCurrentDate());
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    private static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }

    private static void evaluateExpression(String expression) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try {
            Object result = engine.eval(expression);
            JOptionPane.showMessageDialog(null, "Resultado: " + result, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (ScriptException e) {
            JOptionPane.showMessageDialog(null, "Error en la expresión matemática.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
