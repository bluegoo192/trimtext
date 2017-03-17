TrimText
A fast, extensible text editor for programmers on all platforms

1. Instructions on building and running TrimText.
- Download project from https://github.com/bluegoo192/trimtext
- Open IntelliJ and select "Import Project"
- Navigate to and select the trimtext project folder, click OK
- Create project from existing sources, click Next
- Accept the default name, click Next, click Yes if IntelliJ asks to overwrite the .idea file.
- Accept source files from \src directory, select Next
- The next screen will show that all libraries necessary are provided in our project folder, select Next
- Accept the suggested module structure, select Next
- On this next screen you should select the path to the latest version of your JDK (we chose 1.8 for reference, but any recent version should work), then select Next
- Select Finish and IntelliJ should open the project
- Select File > Project Structure and make sure the Project SDK has a valid module (we used 1.8 but any recent JDK version should work), and for Project Language Level, set it to "8 - Lambdas, type annotations etc" then select OK
- Select Build > Build Project, should take at most a minute
- Once IntelliJ is finished building the project, navigate to trimtext-master/src/com/cssquids/trimtext/Main.java on either the directory bar at the top or from the project view on the left
- Select Run > Run, if IntelliJ asks which configuration to use, select Main
- Congratulations, TrimText should be open and running. Enjoy!

2. No external data files are required for this project. TrimText can open (as far as we know) any file type out there. Just navigate to whatever file you wish to open and it will open it. Files to be opened do not need to be placed in the same location as the TrimText source code or runnable.

3. Known Bugs: We have fixed all known bugs.
   
4. Instructions: Once the text editor is open, just type away into the interface and text will show up. As you type, our autocomplete feature will suggest keywords in a textbox next to the cursor. If you press tab, the top suggestion will autocomplete, saving you the time to type the whole keyword out. You can open many file types and the editor will automatically import the text with basic formatting. You can open very large files (100mb+, depending on your computer) and scroll through them with very little lag. To keep track of what line you are on, line numbers are automatically generated on the left side of the program. You can save modifications done to files as well. You can live preview the text inside the editor on a webpage as well through the Web Page button under the View button (HTML only). The "Toggle Text Wrapping" button under the view menu allows you to specify whether you want a long line of text to continue into the next line of the screen or not. The New button will generate an empty tab and the Save and Exit button will do exactly what they say. Save will save your tabs and Exit will save and close the entire interface. You can also freely swap between tabs by clicking through the tabs and close whichever ones you want by clicking the X button in each tab.
