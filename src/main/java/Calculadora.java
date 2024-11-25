
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame {
    
    private final JTextField pantalla;
    private double resultado;
    private String operador;
    private boolean nuevoNumero;
    
    public Calculadora() {
        // Configuración de la ventana
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicialización de variables
        resultado = 0;
        operador = "";
        nuevoNumero = true;
        
        // Crear el layout
        setLayout(new BorderLayout());
        
        // Crear la pantalla de la calculadora
        pantalla = new JTextField("0");
        pantalla.setFont(new Font("Arial", Font.PLAIN, 30));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);
        add(pantalla, BorderLayout.NORTH);
        
        // Crear el panel de botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        
        // Definir los botones
        String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "√", "%", "^2", "C"
        };
        
        // Agregar los botones al panel
        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.setFont(new Font("Arial", Font.PLAIN, 20));
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    botonPresionado(texto);
                }
            });
            panel.add(boton);
        }
        
        add(panel, BorderLayout.CENTER);
    }
    
    private void botonPresionado(String texto) {
        if (texto.equals("C")) {
            pantalla.setText("0");
            resultado = 0;
            operador = "";
            nuevoNumero = true;
        } else if (texto.equals("=")) {
            calcular();
            operador = "";
            nuevoNumero = true;
        } else if (texto.equals("+") || texto.equals("-") || texto.equals("*") || texto.equals("/") || texto.equals("√") || texto.equals("%") || texto.equals("^2")) {
            if (!operador.isEmpty()) {
                calcular();
            }
            operador = texto;
            resultado = Double.parseDouble(pantalla.getText());
            nuevoNumero = true;
        } else if (texto.equals(".")) {
            if (nuevoNumero) {
                pantalla.setText("0.");
                nuevoNumero = false;
            } else if (!pantalla.getText().contains(".")) {
                pantalla.setText(pantalla.getText() + ".");
            }
        } else {
            if (nuevoNumero) {
                pantalla.setText(texto);
                nuevoNumero = false;
            } else {
                pantalla.setText(pantalla.getText() + texto);
            }
        }
    }
    
    private void calcular() {
        double numero = Double.parseDouble(pantalla.getText());
        switch (operador) {
            case "+":
                resultado += numero;
                break;
            case "-":
                resultado -= numero;
                break;
            case "*":
                resultado *= numero;
                break;
            case "/":
                if (numero != 0) {
                    resultado /= numero;
                } else {
                    pantalla.setText("Error");
                    return;
                }
                break;
            case "√":
                resultado = Math.sqrt(numero);
                break;
            case "%":
                resultado = resultado * (numero / 100);
                break;
            case "^2":
                resultado = Math.pow(numero, 2);
                break;
        }
        pantalla.setText(String.valueOf(resultado));
        operador = "";
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora().setVisible(true);
            }
        });
    }
}

