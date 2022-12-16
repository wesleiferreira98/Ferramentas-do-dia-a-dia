#!/bin/bash

# Solicita o nome do arquivo a ser excluído usando o Zenity
file=$(zenity --entry --title "Delete File" --text "Enter the name of the file to delete:")

# Verifica se o usuário digitou o nome do arquivo
if [ -z "$file" ]; then
    zenity --error --text "Error: no file name provided"
    exit 1
fi

# Verifica se o arquivo existe
if [ ! -f "$file" ]; then
    zenity --error --text "Error: file does not exist"
    exit 1
fi

# Exclui o arquivo usando o comando 'shred'
# O comando 'shred' substitui os dados do arquivo com zeros e depois o exclui, tornando a recuperação dos dados impossível
shred -u "$file"

# Exibe uma caixa de diálogo de aviso informando que o arquivo foi excluído com sucesso
zenity --warning --text "File deleted successfully"
