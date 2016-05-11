import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class ViewInventoryWindow extends JFrame implements ActionListener{
	JButton ppuSortA, quantitySortA, invValSortA, supplierSortA, categorySortA, itemNumSortA;
	JButton ppuSortD, quantitySortD, invValSortD, supplierSortD, categorySortD, itemNumSortD;
	JLabel sortByA;
	JTable table;
    JScrollPane scrollPane;
	JPanel tablePanel = new JPanel();
	JPanel panel = new JPanel();
	String[][] display;
    Object headings[] = {"Item Number", "Price Per Unit", "Quantity in Stock", "Total Inventory Value",
    "Category", "Product Specifications", "Supplier Information"};
    static ArrayList<Items> productsD = new ArrayList();

	public ViewInventoryWindow(String title){
		super(title);

		productsD = AccessInventory.getItemArray();
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

		sortByA = new JLabel("Sort by:");
		ppuSortA = new JButton("Price Per Unit Ascending");
		quantitySortA = new JButton("Quantity in Stock Ascending");
		invValSortA = new JButton("Total Inventory Value Ascending");
		supplierSortA = new JButton("Supplier Ascending");
		categorySortA = new JButton("Category Ascending");
		ppuSortD = new JButton("Price Per Unit Descending");
		quantitySortD = new JButton("Quantity in Stock Descending");
		invValSortD = new JButton("Total Inventory Value Descending");
		supplierSortD = new JButton("Supplier Descending");
		categorySortD = new JButton("Category Descending");
		itemNumSortA = new JButton("Item Number Ascending");
		itemNumSortD = new JButton("Item Number Descending");

		setBounds(50,70,1300,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//program doesn't end when "x" is pressed
		setLayout(new FlowLayout());

		add(sortByA);
		add(itemNumSortA);
		add(ppuSortA);
		add(quantitySortA);
		add(invValSortA);
		add(categorySortA);
		add(supplierSortA);
		add(itemNumSortD);
		add(ppuSortD);
		add(quantitySortD);
		add(invValSortD);
		add(categorySortD);
		add(supplierSortD);
		add(panel);

		scrollPane = new JScrollPane(table);
	    scrollPane.setPreferredSize(new Dimension(1200,500));
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(tablePanel);

	    ppuSortA.setActionCommand("ppuSortA");
	    quantitySortA.setActionCommand("quantitySortA");
	    invValSortA.setActionCommand("invValSortA");
	    supplierSortA.setActionCommand("supplierSortA");
	    categorySortA.setActionCommand("categorySortA");
	    itemNumSortA.setActionCommand("itemNumSortA");
	    ppuSortD.setActionCommand("ppuSortD");
	    quantitySortD.setActionCommand("quantitySortD");
	    invValSortD.setActionCommand("invValSortD");
	    supplierSortD.setActionCommand("supplierSortD");
	    categorySortD.setActionCommand("categorySortD");
	    itemNumSortD.setActionCommand("itemNumSortD");

	    ppuSortA.addActionListener(this);
	    quantitySortA.addActionListener(this);
	    invValSortA.addActionListener(this);
	    supplierSortA.addActionListener(this);
	    categorySortA.addActionListener(this);
	    ppuSortD.addActionListener(this);
	    quantitySortD.addActionListener(this);
	    invValSortD.addActionListener(this);
	    supplierSortD.addActionListener(this);
	    categorySortD.addActionListener(this);
	    itemNumSortA.addActionListener(this);
	    itemNumSortD.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt){ //the user has 12 different sorting options
		if(evt.getActionCommand().equals("ppuSortA")){
			SortPpuWindowAsc PpuSortAsc = new SortPpuWindowAsc("Inventory Sorted by Price Per Unit in Ascending Numerical Order");
		}
		else if(evt.getActionCommand().equals("quantitySortA")){
			SortQuantityWindowAsc QuanSortAsc = new SortQuantityWindowAsc("Inventory Sorted by Quantity in Ascending Numerical Order");
		}
		else if(evt.getActionCommand().equals("invValSortA")){
			SortInvValWindowAsc InvValSortAsc = new SortInvValWindowAsc("Inventory Sorted by Total Inventory Value in Ascending Numerical Order");
		}
		else if(evt.getActionCommand().equals("supplierSortA")){
			SortSupplierWindowAsc SupSortAsc = new SortSupplierWindowAsc("Inventory Sorted by Supplier Information in Ascending Alphabetical Order");
		}
		else if(evt.getActionCommand().equals("categorySortA")){
			SortCategoryWindowAsc CatSortAsc = new SortCategoryWindowAsc("Inventory Sorted by Category in Ascending Alphabetical Order");
		}
		else if(evt.getActionCommand().equals("ppuSortD")){
			SortPpuWindowDes PpuSortDes = new SortPpuWindowDes("Inventory Sorted by Price Per Unit in Descending Numerical Order");
		}
		else if(evt.getActionCommand().equals("quantitySortD")){
			SortQuantityWindowDes QuanSortDes = new SortQuantityWindowDes("Inventory Sorted by Quantity in Descending Numerical Order");
		}
		else if(evt.getActionCommand().equals("invValSortD")){
			SortInvValWindowDes InvValSortDes = new SortInvValWindowDes("Inventory Sorted by Total Inventory Value in Descending Numerical Order");
		}
		else if(evt.getActionCommand().equals("supplierSortD")){
			SortSupplierWindowDes SupSortDes = new SortSupplierWindowDes("Inventory Sorted by Supplier Information in Descending Alphabetical Order");
		}
		else if(evt.getActionCommand().equals("categorySortD")){
			SortCategoryWindowDes CatSortDes = new SortCategoryWindowDes("Inventory Sorted by Category in Descending Alphabetical Order");
		}
		else if(evt.getActionCommand().equals("itemNumSortA")){
			SortItemNumWindowAsc itemNumSortAsc = new SortItemNumWindowAsc("Inventory Sorted by Item Number in Ascending Numerical Order");
		}
		else if(evt.getActionCommand().equals("itemNumSortD")){
			SortItemNumWindowDes itemNumSortDes = new SortItemNumWindowDes("Inventory Sorted by Item Number in Descending Numerical Order");
		}
	}
}