import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class AddItemWindow extends JFrame implements ActionListener{
	JLabel itemNumberLabel, ppuLabel, quantityLabel, categoryLabel, productSpecsLabel, supplierLabel;
	JTextField enterItemNumber, enterPpu, enterQuantity, enterCategory, enterProductSpecs, enterSupplier;
	JButton ok, clear;

	public AddItemWindow(String title){
		super(title);

		itemNumberLabel = new JLabel("Item Number:");
		ppuLabel = new JLabel("Price Per Unit:");
		quantityLabel = new JLabel("Quantity in Stock:");
		categoryLabel = new JLabel("Category:");
		productSpecsLabel = new JLabel("Product Specifications:");
		supplierLabel = new JLabel("Supplier:");
		enterItemNumber = new JTextField(20);
		enterPpu = new JTextField(25);
		enterQuantity = new JTextField(20);
		enterCategory = new JTextField(25);
		enterProductSpecs = new JTextField(20);
		enterSupplier = new JTextField(25);
		ok = new JButton("OK");
		clear = new JButton("Clear");

		setBounds(400,250,400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//program doesn't end when "x" is pressed
		setLayout(new FlowLayout());

		add(itemNumberLabel);
		add(enterItemNumber);
		add(ppuLabel);
		add(enterPpu);
		add(quantityLabel);
		add(enterQuantity);
		add(categoryLabel);
		add(enterCategory);
		add(productSpecsLabel);
		add(enterProductSpecs);
		add(supplierLabel);
		add(enterSupplier);
		add(ok);
		add(clear);

		enterItemNumber.setActionCommand("enterItemNumber");
		enterPpu.setActionCommand("enterPpu");
		enterQuantity.setActionCommand("enterQuantity");
		enterCategory.setActionCommand("enterCategory");
		enterProductSpecs.setActionCommand("enterProductSpecs");
		enterSupplier.setActionCommand("enterSupplier");
		ok.setActionCommand("ok");
		clear.setActionCommand("clear");

		enterItemNumber.addActionListener(this);
		enterPpu.addActionListener(this);
		enterQuantity.addActionListener(this);
		enterCategory.addActionListener(this);
		enterProductSpecs.addActionListener(this);
		enterSupplier.addActionListener(this);
		ok.addActionListener(this);
		clear.addActionListener(this);
	}

	private int getItemNumber(){ //returns item number from GUI text field
		int itemNumber = 0;
		try{
			if(enterItemNumber.getText().length() <= 3) //the small business should not get over 1000 items
				itemNumber = Integer.parseInt(enterItemNumber.getText());
			else
				JOptionPane.showMessageDialog(null, "Item number must not exceed 3 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter appropriate item number.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return itemNumber;
	}

	private double getPpu(){ //returns price from GUI text field
		double ppu = 0.0;
		try
		{
			if(Integer.parseInt(enterPpu.getText()) <= 1000) //no one item should be over $1000
				ppu = Double.parseDouble(enterPpu.getText());
			else
				JOptionPane.showMessageDialog(null, "The price may be too high.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter appropriate price per unit.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return ppu;
	}
	private int getQuantity(){ //returns quantity from GUI text field
		int quantity = 0;
		try{
			if(Integer.parseInt(enterQuantity.getText()) <= 100) //the small business will not hold more than 100 of one item
				quantity = Integer.parseInt(enterQuantity.getText());
			else
				JOptionPane.showMessageDialog(null, "Quantity must not exceed 100.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter appropriate product quantity.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return quantity;
	}
	private String getCategory(){ //returns category from GUI text field
		String category = "";
		try{
			if(enterCategory.getText().length() >= 2&&enterCategory.getText().length() <= 20) //category of length less than 2 may be incomplete
				category = enterCategory.getText();
			else
				JOptionPane.showMessageDialog(null, "Please enter a product category of 2-20 characters.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter appropriate product category.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return category;
	}
	private String getProductSpecs(){ //returns product specifications from GUI text field
		String productSpecs = "";
		try{
			if(enterProductSpecs.getText().length() >= 2) //product specs of length less than 2 may be incomplete
				productSpecs = enterProductSpecs.getText();
			else
				JOptionPane.showMessageDialog(null, "Please enter product specifications of at least 2 characters.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter appropriate product specifications.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return productSpecs;
	}
	private String getSupplier(){ //returns supplier notes from GUI text field
		String supplier = "";
		try{
			if(enterSupplier.getText().length() >= 2) //supplier of length less than 2 may be incomplete
				supplier = enterSupplier.getText();
			else
				JOptionPane.showMessageDialog(null, "Please enter supplier of at least 2 characters.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter appropriate supplier notes.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return supplier;
	}

	private double getInvVal(){ //calculates and returns inventory value by multiplying price*quantity
		int quantity2 = getQuantity();
		double ppu2 = getPpu();
		double invVal = (double)quantity2*ppu2;

		return invVal;
	}

	public void actionPerformed(ActionEvent evt){
	  	if(evt.getActionCommand().equals("ok"))
	  	{
	  		//data must be entered into all fields
			if(getItemNumber()!=0&&getPpu()!=0.0&&getQuantity()!=0&&getCategory()!=""&&getProductSpecs()!=""&&getSupplier()!=""){
				int itemNumberJ = getItemNumber(); //J stands for the text gotten from JTextField
				double ppuJ = getPpu();
				int quantityJ = getQuantity();
				double invValJ = getInvVal();
				String categoryJ = getCategory();
				String productSpecsJ = getProductSpecs();
				String supplierJ = getSupplier();

				if(AccessInventory.searchItem(itemNumberJ)==-1){ //to eliminate chances of product duplicates
					AccessInventory.addItem(itemNumberJ, ppuJ, quantityJ, invValJ, categoryJ, productSpecsJ, supplierJ);
					setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "An item with specified item number already exists.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
	  	}
	  	else if(evt.getActionCommand().equals("clear")){ //clears all text fields in one click
	  		enterItemNumber.setText("");
	  		enterPpu.setText("");
	  		enterQuantity.setText("");
	  		enterCategory.setText("");
	  		enterProductSpecs.setText("");
	  		enterSupplier.setText("");
	  	}
	}
}