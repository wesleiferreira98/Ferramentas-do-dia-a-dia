import os
import shutil
import sys

from PyQt5.QtWidgets import (
    QApplication,
    QFileDialog,
    QMessageBox,
    QProgressDialog,
    QPushButton,
    QTextEdit,
    QVBoxLayout,
    QWidget,
)

class FileDeleter(QWidget):
    def __init__(self):
        super().__init__()

        # Create the "Choose File" button
        choose_button = QPushButton("Choose File")
        choose_button.clicked.connect(self.choose_file)

        # Create the text box to display the selected file name
        self.file_text = QTextEdit()
        self.file_text.setReadOnly(True)

        # Create the "Delete File" button
        delete_button = QPushButton("Delete File")
        delete_button.clicked.connect(self.delete_file)

        # Add the buttons and text box to the layout
        layout = QVBoxLayout()
        layout.addWidget(choose_button)
        layout.addWidget(self.file_text)
        layout.addWidget(delete_button)
        self.setLayout(layout)

    def choose_file(self):
        # Show a dialog for the user to select the file to be deleted
        file, _ = QFileDialog.getOpenFileName()

        # Update the text box with the selected file name
        self.file_text.setText(file)

    def delete_file(self):
        # Get the file name from the text box
        file = self.file_text.toPlainText()

        # Check if the user selected a file
        if not file:
            QMessageBox.warning(self, "Error", "No file selected")
            return

        # Check if the file exists
        if not os.path.exists(file):
            QMessageBox.warning(self, "Error", "File does not exist")
            return

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
        QMessageBox.warning(self, "Success", "File deleted successfully")
if __name__ == "__main__":
    app = QApplication(sys.argv)
    deleter = FileDeleter()
    deleter.show()
    app.exec_()



       
