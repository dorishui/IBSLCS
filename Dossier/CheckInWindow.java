import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class CheckInWindow extends JFrame implements ActionListener{
	JLabel itemNumber, quantityAdded;
	JTextField enterItemNumber, enterQuantityAdded;
	JButton ok;

	public CheckInWindow(String title){
		super(title);

		itemNumber = new JLabel("Item Number:");
		quantityAdded =new JLabel("Quantity to Add:");
		enterItemNumber = new JTextField(10);
		enterQuantityAdded = new JTextField(10);
		ok = new JButton("OK");

		setBounds(400,300,250,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//program doesn't end when "x" is pressed
		setLayout(new FlowLayout());

		add(itemNumber);
		add(enterItemNumber);
		add(quantityAdded);
		add(enterQuantityAdded);
		add(ok);

	    enterItemNumber.setActionCommand("enterItemNumber");
	    enterQuantityAdded.setActionCommand("enterQuantityAdded");
	    ok.setActionCommand("ok");

	    enterItemNumber.addActionListener(this);
	    enterQuantityAdded.addActionListener(this);
	    ok.addActionListener(this);
	}

	private int getItemNumber(){ //returns item number from GUI textfield
		int itemNumber = 0;
		try{
			itemNumber = Integer.parseInt(enterItemNumber.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter the item number.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return itemNumber;
	}

	private int getQuantityAdded(){ //returns quantity added from GUI text field
		int quantityAdded = 0;
		try{
			quantityAdded = Integer.parseInt(enterQuantityAdded.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter quantity added.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return quantityAdded;
	}

	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("ok")){
			int itemNumberJ = getItemNumber();

			if(AccessInventory.searchItem(itemNumberJ)!=-1){ //searchItem() is used to prevent checking in a nonexisting item
				int quantityAddedJ = getQuantityAdded();

				Items reset = AccessInventory.getProduct(itemNumberJ);
				reset.checkInExisting(quantityAddedJ);
				reset.getNewInvVal();
				int index = AccessInventory.searchItem(itemNumberJ);
				AccessInventory.products.set(index, reset);

				setVisible(false);
			}
			else
				JOptionPane.showMessageDialog(null, "An item with specified item number does not exist.", "Error Message", JOptionPane.ERROR_MESSAGE);
	  	}
	}
}
