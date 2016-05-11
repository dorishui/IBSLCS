import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class SortSupplierWindowDes extends JFrame implements ActionListener{
	JTable table;
    JScrollPane scrollPane;
	JPanel tablePanel = new JPanel();
	JPanel panel = new JPanel();
	String[][] display;
    Object headings[] = {"Item Number", "Price Per Unit", "Quantity in Stock", "Total Inventory Value",
    "Category", "Product Specifications", "Supplier Information"};
    static ArrayList<Items> productsD = new ArrayList();

	public SortSupplierWindowDes(String title){
		super(title);

		productsD = AccessInventory.getItemArraySupplierDes();
		display = new String[productsD.size()][7];
		for(int i = 0; i<productsD.size(); i++){ //puts sorted ArrayList into 2 dimensional array
			Items disp = productsD.get(i);
			display[i][0] = "" + disp.itemNumber;
			display[i][1] = "" + disp.ppu;
			display[i][2] = "" + disp.quantity;
			display[i][3] = "" + disp.invVal;
			display[i][4]= "" + disp.category;
			display[i][5]= "" + disp.productSpecs;
			display[i][6]= "" + disp.supplier;
		}

		table = new JTable(display,headings);//puts display[][] into a readable JTable with appropriate headings
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);

		setBounds(50,70,1300,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//program doesn't end when "x" is pressed
		setLayout(new FlowLayout());

		add(panel);

		scrollPane = new JScrollPane(table);
	    scrollPane.setPreferredSize(new Dimension(1200,500));
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(tablePanel);
	}

	public void actionPerformed(ActionEvent evt){
	}
}