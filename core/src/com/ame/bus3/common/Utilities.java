package com.ame.bus3.common;

import javax.swing.*;
import java.awt.*;

/**
 * Common utilities for various different uses.
 * @author Amelorate
 *
 */
public class Utilities {
	/**
	 * Opens a popup window you can enter any text into and get a response.
	 * @param defaultText The text that starts inside the box.
	 * @return Returns the text the user entered.
	 */
	public static String popupPrompt(String title, String message, String defaultText) {
		return popupPrompt(title, message, null, defaultText);
	}
	
	/**
	 * Allows the user to choose from a series of choices.
	 * @param choices An array of all the possible choices.
	 * @param defaultOption The option that is selected when the popup opens.
	 * @return Returns the choice the user selected or null if the user canceled.
	 */
	public static String popupPrompt(String title, String message, String[] choices, String defaultOption) {
		ImageIcon icon = new ImageIcon();
		Canvas frame = new Canvas();
		
		return (String)JOptionPane.showInputDialog(frame,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                choices,
                defaultOption);
	}
	
	/**
	 * Gives the user a popup message with no icon.
	 */
	public static void popupMessage(String title, String message) {
		popupMessage(title, message, JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Gives a popup to the user.
	 * @param icon The icon. You can use some of the constants in JOptionPane for this.
	 */
	public static void popupMessage(String title, String message, int icon) {
		Canvas frame = new Canvas();
		JOptionPane.showMessageDialog(frame,
			    message,
			    title,
			    icon);
	}
	
	/**
	 * Checks if an IP is valid or not.
	 * @param ip The ip in question.
	 * @return True if it is valid, else false.
	 */
	public static boolean validIP (String ip) {		// Shamelessly stolen from  https://stackoverflow.com/questions/4581877/validating-ipv4-string-in-java
	    try {	// Although I did slightly modify it.
	        String[] parts = ip.split( "\\." );
	        if (parts.length != 4) {
	            return false;
	        }

	        for (String s : parts) {
	            int i = Integer.parseInt( s );
	            if ( (i < 0) || (i > 255) ) {
	                return false;
	            }
	        }
	        if(ip.endsWith(".")) {
	                return false;
	        }

	        return true;
	    } 
	    catch (NumberFormatException nfe) {
	        return false;
	    }
	}
}
