package kuantic.vista;

import kuantic.modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private Usuario usuarioValido;
    private JTextField textousuario;
    private JPasswordField textocontrasena;

    public LoginFrame() {
        usuarioValido = new Usuario("administrador", "1234");
        setTitle("Empresa Kuantic");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
 
         Color amarilloCalido = new Color(255, 239, 150);
        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
         panel.setBackground(amarilloCalido);
        
        JLabel labelusuario = new JLabel("Usuario:");
        JLabel labelcontrasena = new JLabel("Contraseña:");
        textousuario = new JTextField();
        textocontrasena = new JPasswordField();
        JButton botoningresar = new JButton("Ingresar");

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(labelusuario);
        panel.add(textousuario);
        panel.add(labelcontrasena);
        panel.add(textocontrasena);
        panel.add(new JLabel());
        panel.add(botoningresar);

        add(panel);

        botoningresar.addActionListener(e -> verificariniciodesesion());
    }

    private void verificariniciodesesion() {
        String u = textousuario.getText();
        String p = new String(textocontrasena.getPassword());

        if(usuarioValido.validar(u,p)) {
            JOptionPane.showMessageDialog(this, "Bienvenido a Kuantic " );
            this.dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, " Vueva a ingresar sus datos correctos ");
        }
    }
}
