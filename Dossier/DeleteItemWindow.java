import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class DeleteItemWindow extends JFrame implements ActionListener{
	JLabel itemNumber;
	JTextField enterItemNumber;
	JButton delete;

	public DeleteItemWindow(String title){

		super(title);

		itemNumber = new JLabel("Please enter item number of item to be deleted:");
		enterItemNumber = new JTextField(4);
		delete = new JButton("Delete");

		setBounds(300,300,500,100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//program doesn't end when "x" is pressed
		setLayout(new FlowLayout());

		add(itemNumber);
		add(enterItemNumber);
		add(delete);

	    enterItemNumber.setActionCommand("enterItemNumber");
	    delete.setActionCommand("delete");

	    enterItemNumber.addActionListener(this);
	    delete.addActionListener(this);
	}

	private int getItemNumber(){ //returns item number from GUI text field
		int itemNumber = 0;
		try{
			itemNumber = Integer.parseInt(enterItemNumber.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter the item number of the item to be deleted.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return itemNumber;
	}

	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("delete")){
	  		int itemNumberJ = getItemNumber();
	  		if(AccessInventory.searchItem(itemNumberJ)!=-1){ //prevents from deleting a nonexisting item
				AccessInventory.deleteItem(itemNumberJ);
				setVisible(false);
	  		}
	  		else
	  			JOptionPane.showMessageDialog(null, "An item with specified item number does not exist.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}