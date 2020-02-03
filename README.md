# DailyLogAppProject
Welcome to the Daily Log App!
This app is basically a journal entry for your daily thoughts, custom-tailored to your thoughts on a page!

The Daily Log App is a project programmatically designed to invoke the use of OOD principles on MVC architecture

Update vAlpha 1.2.0:
- Bug fixes
- Users can now choose where to save their log file in any directory. 

Update vAlpha 1.2.1:
- Bug fixes
- Users can now select the directory of their choice to create a new log or open an existing log in that directory.

Update vAlpha 1.2.2:
- Deleted the ability to choose a directory as soon as the program runs for any of the users hard coded into the program.

Update vAlpha 1.2.3:
- Added a "saved" message popup after users save their daily log.

Update vAlpha 1.2.4:
- Bug fixes
- Users can now open and save files on any drive they wish. No longer are they restrained to the shackles of grief and 
  separation.
  
Update vAlpha 1.2.5:
- Bug fixes
- Menu option "New" is fully operational.
- Shortcut keys for "Send" and "Cancel" have been created.

Update vAlpha 1.5.0:
- All menu options are now fully operational with the addition of shortcut keys.

Update vAlpha 1.5.1:
- Added features to the "About" menu item under the "Help" menu option.
  
When hitting the "Send" button, you are always prompted to choose a directory in which to save the file.
To continue opening the previous the log, be sure to only save the file in the same directory it was created.
To create a new daily log chain, just save the log file in a different directory.

DO NOT CHANGE THE NAME OF THE FILE WHEN SAVING!

DO NOT DUPLICATE THE FILE WITH THE SAME NAME AS THE OTHER LOG FILES!

DISCLAIMER: 

When changing your java runtime environment, check to make sure in "regedit" that the appropriate jre bin folder is selected in "Computer\HKEY_LOCAL_MACHINE\SOFTWARE\Classes\jarfile\shell\open\command" and "Computer\HKEY_CLASSES_ROOT\jarfile\shell\open\command" for "C:\Program Files\Java\jre1.8.0_231\bin\javaw.exe" -jar "%1" %*

If you intend to open files beyond "Entry_DATE.txt" files, the program may not use the content.

~~You need at least 2 storage drives to make this work.~~
~~Select any hard drive except C:, because access to the C: drive is denied.~~

~~To run, type java -jar daily_log_app_vALPHA_X.jar in your command prompt or terminal and *not* by double-clicking the app (X means~~ ~~version).~~


Enjoy!
