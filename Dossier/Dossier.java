/*
Name: Doris Hui
Teacher: Mr. Rakhmatulin
Date of Completion: 2nd April, 2013
Program Title: Inventory Database
School: Mirabeau B. Lamar High School
OS: Windows 7
IDE: JCreator
*/

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Dossier{
	public static void main(String args[]){
		MainMenu welcome = new MainMenu("Welcome to the Inventory"); //pull up main menu
		AccessInventory.readCSV(); //read in inventoryDatabase.csv and put data into ArrayList named products
	}
}