import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener {
	
	JPanel header, body, footer;
	JLabel titleLabel, creditLabel;
	JButton viewButton;

	public Menu() {
		Frame();

		// Title
		header = new JPanel();
		header.setBackground(Color.decode("#7b8582"));

		titleLabel = new JLabel(" [PT Pudding] ");
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		titleLabel.setPreferredSize(new Dimension(175, 100));
		titleLabel.setForeground(Color.white);

		this.add(header, BorderLayout.NORTH);
		header.add(titleLabel);

		// View
		body = new JPanel();
		body.setBackground(Color.decode("#7b8582"));
		
		viewButton = new JButton("Menu");
		viewButton.setPreferredSize(new Dimension(250, 60));
		viewButton.addActionListener(this);
		viewButton.setBackground(Color.black);
		viewButton.setForeground(Color.white);
		
		this.add(body, BorderLayout.CENTER);
		body.add(viewButton);
		
		// Credit
		footer = new JPanel();
		footer.setBackground(Color.black);

		creditLabel = new JLabel("Made by Michael Wilson");
		creditLabel.setPreferredSize(new Dimension(130, 40));
		creditLabel.setFont(new Font("Calibri", Font.BOLD, 12));
		creditLabel.setForeground(Color.white);

		this.add(footer, BorderLayout.SOUTH);
		footer.add(creditLabel);
	}
	
	public void Frame() {
		this.setVisible(true);
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Menu Application");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Features();
	}

}