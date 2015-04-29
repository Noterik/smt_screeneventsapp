/* 
* ScreeneventsApplication.java
* 
* Copyright (c) 2012 Noterik B.V.
* 
* This file is part of Lou, related to the Noterik Springfield project.
* It was created as a example of how to use the multiscreen toolkit
*
* Screenevents app is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Helloworld app is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Screenevents app.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.springfield.lou.application.types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springfield.lou.application.*;
import org.springfield.lou.screen.*;

public class ScreeneventsApplication extends Html5Application{
	
	private ArrayList<String> colors = new ArrayList<String>( Arrays.asList("maroon", "red", "orange","yellow","olive","purple","fuchsia","lime","green","navy","blue","aqua","teal","silver","gray"));
	private Random rnd;
	
 	public ScreeneventsApplication(String id) {
		super(id); 
	}
	
 	/*
 	 * This method is called when a browser window opens the application
 	 * @see org.springfield.lou.application.Html5Application#onNewScreen(org.springfield.lou.screen.Screen)
 	 */
    public void onNewScreen(Screen s) {
        loadStyleSheet(s, "generic"); //Loading the genereic style from css folder
        loadContent(s, "titlepart"); //Loading the content of the titlepart component (html and js if any)
        
        // We set some HTML content on the screen in a div with ID "form"
        s.setContent("form", "Type some text and press Enter:<br/><br/> <input id=\"textinput\"> <input id=\"textinput2\"> <br/><br/>");
        // Bind a change event on an element with ID "textinput"
        s.setDiv("textinput","bind:change");
        
        // Bind a change event on an element with ID "textinput2" and specify the callback function to be getInputValue, which is part of this (the current class)
        s.setDiv("textinput2","bind:change", "getInputValue", this);
        
        // We set some HTML content on the screen in a div with ID "form2"
        s.setContent("form2", "Click on the div to change its color:<br/><br/> <div style=\"cursor: pointer; border: 1px solid #000; width: 50px; height: 50px;\" id=\"divinput\">");
        // Bind a mousedown event on an element with ID divinput and specify a callback function to be changeColor, which is part of this (the current class)
        s.setDiv("divinput","bind:mousedown","changeColor", this);
    }
    
    /*
     * Callback function for a bind event, following the naming convention: action{ElementID}{EventName}
     * When we bind the change event on an element, without providing a callback function, the MST checks
     * for a method with the naming convention described above.
     */
    public void actionTextinputChange(Screen s,String c) {
    	s.setProperties(c); // Convert the incoming data c into screen properties
    	
    	// Get the value of the input field from the screen properties.
    	// The property name for the value is buildup by the {elementId}.value
    	String text = s.getProperty("textinput.value").toString();
    	
    	// We set HTML content on the screen in a div with ID "output" that contains the value of the element.
    	s.setContent("output", "Your text is: " + text);
    }
    
    /*
     * Callback function for a bind event
     */
    public void getInputValue(Screen s, String c) {
    	s.setProperties(c); // Convert the incoming data c into screen properties
    	
    	// Get the value of the input field from the screen properties.
    	// The property name for the value is buildup by the {elementId}.value
    	String text = s.getProperty("textinput2.value").toString();
    	
    	// We set HTML content on the screen in a div with ID "output2" that contains the value of the element.
    	s.setContent("output2", "Your second text is: " + text);
    }
    
    /*
     * Callback function for a bind event
     */
    public void changeColor(Screen s, String c) {
    	rnd = new Random();
		String selectedcolor = colors.get(rnd.nextInt(colors.size()));
		
		// We set a style of an element with ID "divinput"
		s.setDiv("divinput","style:background-color: "+selectedcolor);
    }
}