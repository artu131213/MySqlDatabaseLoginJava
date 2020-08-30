package mysql;

import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.beans.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginMySql extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMySql frame = new LoginMySql();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginMySql() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPage.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		lblLoginPage.setBounds(31, 11, 224, 44);
		contentPane.add(lblLoginPage);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(20, 66, 96, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 125, 89, 14);
		contentPane.add(lblPassword);

		user = new JTextField();
		user.setBounds(10, 88, 255, 26);
		contentPane.add(user);
		user.setColumns(10);

		pass = new JPasswordField();
		pass.setBounds(10, 146, 255, 26);
		contentPane.add(pass);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
					Statement stmt = con.createStatement();
					String sql = "Select *  from tblogin where UserName = '" + user.getText() + "' and Password = '"
							+ pass.getText() + "'";

					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next())
						JOptionPane.showMessageDialog(null, "Login Sucessfully...");
					else
						JOptionPane.showMessageDialog(null, "Incorrect username and password...");
					con.close();
				} catch (Exception e) {
					System.out.print(e);
				}

			}

		});
		btnLogin.setBounds(10, 193, 89, 23);
		contentPane.add(btnLogin);

	}
}
