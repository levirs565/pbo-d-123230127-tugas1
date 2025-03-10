package id.my.levirs.prakpbotugas1;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 *
 * @author levir
 */
public class LoginFrame extends JFrame {
    private JLabel mUsernameLabel, mPasswordLabel, mMessageLabel; 
    private JTextField mUsernameTextField, mPasswordTextField;
    private JButton mLoginButton;
    
    public LoginFrame() {
        setLayout(new GridBagLayout());
         
        var constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.insets = new Insets(5, 5, 5, 5);
         
        mUsernameLabel = new JLabel("Username:");
        add(mUsernameLabel, constraint);
         
        constraint.gridy = 1;
        mPasswordLabel = new JLabel("Password:");
        add(mPasswordLabel, constraint);
         
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 2;

        mLoginButton = new JButton("Login");
        add(mLoginButton, constraint);
        
        constraint.gridy = 3;
        mMessageLabel = new JLabel();
        mMessageLabel.setPreferredSize(new Dimension(150, 25));
        mMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(mMessageLabel, constraint);
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.weightx = 1;
        constraint.insets = new Insets(0, 25, 0, 0);
         
        mUsernameTextField = new JTextField();
        mUsernameTextField.setPreferredSize(new Dimension(150, 25));
        add(mUsernameTextField, constraint);
         
        constraint.gridy = 1;
        mPasswordTextField = new JTextField();
        mPasswordTextField.setPreferredSize(new Dimension(150, 25));
        add(mPasswordTextField, constraint);
         
        pack();
    
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         
        mLoginButton.addActionListener((e) -> {
            if (!mUsernameTextField.getText().equalsIgnoreCase("pbo") &&
               !mPasswordTextField.getText().toLowerCase().equalsIgnoreCase("pbo")) {
               mMessageLabel.setText("Gagal Login");
               return;
            }
           
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            
            new MainFrame();
        });
    }

}
