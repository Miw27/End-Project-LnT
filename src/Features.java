import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Features extends JFrame implements ActionListener {
	
	JPanel header, body, footer;
	JLabel PriceLabel, StockLabel;
	JTextField updatePriceTF, updateStockTF;
	JButton insertButton, updateButton, deleteButton;
	
	JTable menuTable = new JTable();
	DefaultTableModel dtm;
	JScrollPane jsp;
	
	Vector<String> columnName = new Vector<>();
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	
	Connect db = new Connect();

	public Features() {
		setFrame();
		getData();
		
		//Update
		header = new JPanel();
		header.setBackground(Color.black);
		
		PriceLabel = new JLabel("Price: ");
		PriceLabel.setForeground(Color.white);

		updatePriceTF = new JTextField();
		updatePriceTF.setPreferredSize(new Dimension(150, 30));
		updatePriceTF.setBackground(Color.white);
		updatePriceTF.setForeground(Color.black);

		StockLabel = new JLabel("Stock: ");
		StockLabel.setForeground(Color.white);

		updateStockTF = new JTextField();
		updateStockTF.setPreferredSize(new Dimension(150, 30));
		updateStockTF.setBackground(Color.white);
		updateStockTF.setForeground(Color.black);

		header.add(PriceLabel);
		header.add(updatePriceTF);
		header.add(StockLabel);
		header.add(updateStockTF);
		
		updateButton = new JButton("Update");
		updateButton.setPreferredSize(new Dimension(300, 40));
		updateButton.setBackground(Color.white);
		updateButton.setForeground(Color.black);
		updateButton.addActionListener(this);
		this.add(header, BorderLayout.NORTH);
		header.add(updateButton);
		
		//Table
		body = new JPanel();
		body.setBackground(Color.white);
		
		columnName.add("Stock");
		columnName.add("Price");
		columnName.add("Name");
		columnName.add("ID");

		dtm = new DefaultTableModel(data, columnName);
		menuTable = new JTable(dtm);
		jsp = new JScrollPane(menuTable);
		jsp.setPreferredSize(new Dimension(780, 100));
		this.add(body, BorderLayout.CENTER);
		body.add(jsp);
		
		//Insert & Delete
		footer = new JPanel();
		footer.setPreferredSize(new Dimension(0, 60));
		footer.setBackground(Color.black);
		
		insertButton = new JButton("Insert");
		insertButton.setPreferredSize(new Dimension(175, 40));
		insertButton.setBackground(Color.white);
		insertButton.setForeground(Color.black);
		insertButton.addActionListener(this);
		this.add(insertButton, BorderLayout.WEST);
		
		deleteButton = new JButton("Delete");
		deleteButton.setPreferredSize(new Dimension(175, 40));
		deleteButton.setBackground(Color.white);;
		deleteButton.setForeground(Color.black);
		deleteButton.addActionListener(this);
		this.add(deleteButton, BorderLayout.EAST);
		
		
		this.add(footer, BorderLayout.SOUTH);
		footer.add(insertButton);
		footer.add(deleteButton);
	}
	
	
	public void setFrame() {
		this.setVisible(true);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Menu");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == insertButton) {
			new Insert();
		}
		else if (e.getSource() == updateButton) {

			int index = menuTable.getSelectedRow();

			String ID = String.valueOf(dtm.getValueAt(index, 3));
			
			String textHarga = updatePriceTF.getText();
			int Price = Integer.parseInt(textHarga);
			
			String textStok = updateStockTF.getText();
			int Stock = Integer.parseInt(textStok);
			
			db.updateMenu(Stock, Price, ID);
		}
		else {
			int index = menuTable.getSelectedRow();

			String ID = String.valueOf(dtm.getValueAt(index, 3));

			db.deleteMenu(ID);
			
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
