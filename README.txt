TrimText
A fast, extensible text editor for programmers on all platforms

1. Instructions on building and running TrimText.
   We suggest downloading IntelliJ (https://www.jetbrains.com/idea/download/) and install to desired location. Download GitHub repo from this github(https://github.com/bluegoo192/trimtext.git) and open it with IntelliJ. Once the file is open in IntelliJ go to "File" and then "Project Structure". Once in Project Structure make sure the Project SDK has a valid module (we used 1.8 but anything should work) and for Project Langauge level set it to "8 - Lambdas, type annotations etc". Once these settings are in place hit "Build" and "Build Project" and then "Run" and "Run 'Main'" to run the project. This should bring it up.
    Required Libraries: JavaFX, Kotlin, javac2
    Required Operating System: N/A

2. No external data files are required for this project. TrimText can open (as far as we know) any file type out there. Just navigate to whatever file you wish to open and it will open it. Files to be opened do not need to be placed in the same location as the TrimText source code or runnable.

3. Known Bugs: We have fixed all known bugs.
   
4. Instructions: Once the text editor is open, just type away into the interface and text will show up. As you type, our autocomplete feature will suggest keywords in a textbox next to the cursor. If you press tab, the top suggestion will autocomplete, saving you the time to type the whole keyword out. You can open many file types and the editor will automatically import the text with basic formatting. You can open very large files (100mb+, depending on your computer) and scroll through them with very little lag. To keep track of what line you are on, line numbers are automatically generated on the left side of the program. You can save modifications done to files as well. You can change the size and type of font used in the text through our menu bar under the Font button under the View button. You can live preview the text inside the editor on a webpage as well through the Web Page button under the View button (HTML only). The "Toggle Text Wrapping" button under the view menu allows you to specify whether you want a long line of text to continue into the next line of the screen or not. The New button will generate an empty tab and the Save and Exit button will do exactly what they say. Save will save your tabs and Exit will save and close the entire interface. You can also freely swap between tabs by clicking through the tabs and close whichever ones you want by clicking the X button in each tab.
