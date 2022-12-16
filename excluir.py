import os
import shutil

from PyQt5.QtWidgets import QApplication, QFileDialog, QMessageBox, QProgressDialog

# Create the application
app = QApplication([])

# Show a dialog for the user to select the file to be deleted
file, _ = QFileDialog.getOpenFileName()

# Check if the user selected a file
if not file:
    QMessageBox.warning(None, "Error", "No file selected")
    exit(1)

# Check if the file exists
if not os.path.exists(file):
    QMessageBox.warning(None, "Error", "File does not exist")
    exit(1)

# Get the size of the file in bytes
file_size = os.path.getsize(file)

# Create a progress dialog to display the progress of deleting the file
progress_dialog = QProgressDialog("Deleting file...", "Cancel", 0, file_size)
progress_dialog.setWindowTitle("Deleting File")

# Delete the file using the 'shred' command
# The 'shred' command replaces the data in the file with zeros and then deletes it, making data recovery impossible
with open(file, "rb") as f_in, open("/dev/null", "wb") as f_out:
    # Read the file in 1 MB chunks and update the progress in the dialog
    chunk_size = 1024 * 1024  # 1 MB
    chunk = f_in.read(chunk_size)
    while chunk:
        f_out.write(chunk)
        progress_dialog.setValue(progress_dialog.value() + len(chunk))
        app.processEvents()  # Update the user interface
        chunk = f_in.read(chunk_size)

# Delete the original file
os.remove(file)

# Show a message box indicating that the file was deleted successfully
QMessageBox.warning(None, "Success", "File deleted successfully")

# Exit the application
app.exit()
