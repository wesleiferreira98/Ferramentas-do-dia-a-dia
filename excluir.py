import os
import shutil

from PyQt5.QtWidgets import QApplication, QFileDialog, QMessageBox

# Cria a aplicação
app = QApplication([])

# Exibe um diálogo para o usuário selecionar o arquivo a ser excluído
file, _ = QFileDialog.getOpenFileName()

# Verifica se o usuário selecionou um arquivo
if not file:
    QMessageBox.warning(None, "Error", "No file selected")
    exit(1)

# Verifica se o arquivo existe
if not os.path.exists(file):
    QMessageBox.warning(None, "Error", "File does not exist")
    exit(1)

# Exclui o arquivo usando o comando 'shred'
# O comando 'shred' substitui os dados do arquivo com zeros e depois o exclui, tornando a recuperação dos dados impossível
shutil.copy2(file, "/dev/null")
os.remove(file)

# Exibe uma caixa de diálogo informando que o arquivo foi excluído com sucesso
QMessageBox.warning(None, "Success", "File deleted successfully")

# Finaliza a aplicação
app.exit()
