import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Insert extends JFrame implements ActionListener {

	JPanel body, footer;
	JLabel LabelName, LabelPrice, LabelStock;
	JTextField nameTF, priceTF, stockTF;
	JButton addButton;
	
	DefaultTableModel dtm;
	
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	
	Connect db = new Connect();
	
	public Insert() {
		Frame();
		getData();
		
		// Body
		body = new JPanel();
		body.setBackground(Color.white);
		this.add(body, BorderLayout.CENTER);
		
		// Name
		LabelName = new JLabel("Name: ");
		LabelName.setForeground(Color.black);
		
		nameTF = new JTextField();
		nameTF.setPreferredSize(new Dimension(200, 30));
		nameTF.setForeground(Color.black);
		
		body.add(LabelName);
		body.add(nameTF);

		// Price
		LabelPrice = new JLabel("Price: ");
		LabelPrice.setForeground(Color.black);
		
		priceTF = new JTextField();
		priceTF.setPreferredSize(new Dimension(200, 30));
		priceTF.setForeground(Color.black);
		
		body.add(LabelPrice);
		body.add(priceTF);
		
		// Stock
		LabelStock = new JLabel("Stock: ");
		LabelStock.setForeground(Color.black);
		
		stockTF = new JTextField();
		stockTF.setPreferredSize(new Dimension(200, 30));
		stockTF.setForeground(Color.black);
		
		body.add(LabelStock);
		body.add(stockTF);
		
		
		// Footer
		footer = new JPanel();
		footer.setBackground(Color.white);
		this.add(footer, BorderLayout.SOUTH);
		
		// Submit
		addButton = new JButton("Add menu");
		addButton.setPreferredSize(new Dimension(175, 40));
		addButton.addActionListener(this);
		addButton.setBackground(Color.black);
		addButton.setForeground(Color.white);
		
		footer.add(addButton);
	}
	
	// Frame Insert
	public void Frame() {
		this.setVisible(true);
		this.setSize(250, 285);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Insert Menu");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			dtm = new DefaultTableModel();
			
			int numId = 0;
			while (numId < 100 || numId > 1000) {
				Random rand = new Random();
				numId = rand.nextInt();
			}
			
			String Name =nameTF.getText();
			
			String ID = Integer.toString(numId);
			ID = "PD-" + numId;
			
			String textHarga = priceTF.getText();
			int Price = Integer.parseInt(textHarga);
			
			String textStok = stockTF.getText();
			int Stock = Integer.parseInt(textStok);
			
			db.insertMenu(Stock, Price, Name, ID); 
			
			Object row[] = {Name, ID, Price, Stock};
			dtm.addRow(row);
			
			new Features();
		}
	}	
	
	public void getData() {
		db.rs = db.getMenu();
		
		try {
			while (db.rs.next()) {
				Vector<Object> newRow = new Vector<>();
				newRow.add(db.rs.getString("Stock"));
				newRow.add(db.rs.getString("Price"));
				newRow.add(db.rs.getString("Name"));
				newRow.add(db.rs.getString("ID"));
				
				data.add(newRow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}