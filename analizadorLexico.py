import re

# Define a lista de tokens que o analisador léxico irá reconhecer
tokens = [
    "NUMBER",
    "PLUS",
    "MINUS",
    "MULTIPLY",
    "DIVIDE",
    "LPAREN",
    "RPAREN",
]

# Cria um dicionário que mapeia os tokens para os padrões de expressão regular que os reconhecem
token_patterns = {
    "NUMBER": r"\d+",
    "PLUS": r"\+",
    "MINUS": r"-",
    "MULTIPLY": r"\*",
    "DIVIDE": r"/",
    "LPAREN": r"\(",
    "RPAREN": r"\)",
}

# Cria o analisador léxico
def lexer(text):
    # Cria um cursor para percorrer o texto
    cursor = 0
    # Enquanto o cursor estiver dentro do texto...
    while cursor < len(text):
        # Percorre cada token no dicionário
        for token in token_patterns:
            # Tenta aplicar o padrão de expressão regular para o token atual
            pattern = re.compile(token_patterns[token])
            match = pattern.match(text, cursor)
            # Se o padrão for encontrado...
            if match:
                # Avança o cursor para o final do padrão encontrado
                cursor = match.end()
                # Retorna o token e o valor do token
                yield token, match.group()
        # Se nenhum padrão foi encontrado, avança o cursor para o próximo caractere
        cursor += 1

# Testa o analisador léxico com uma expressão de teste
for token in lexer("2 + 3 * (4 - 1)"):
    print(token)
