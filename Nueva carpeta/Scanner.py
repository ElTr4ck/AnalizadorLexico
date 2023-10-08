from claseToken import TipoToken #Importar los tipos de tokens

#Diccionario de palabras reservadas
palabrasReservadas = {
    "and" : TipoToken.AND,
    "else" : TipoToken.ELSE,
    "false" : TipoToken.FALSE,
    "fun": TipoToken.FUN,
    "for" : TipoToken.FOR,
    "if" : TipoToken.IF,
    "null" : TipoToken.NULL,
    "or" : TipoToken.OR,
    "print" : TipoToken.PRINT,
    "return" : TipoToken.RETURN,
    "true" : TipoToken.TRUE,
    "var" : TipoToken.VAR,
    "while" : TipoToken.WHILE
}

tokens = TipoToken[]

def Scanner(source):
    source = source + " "

def analizar(cadena):
# Inicio de la declaracion del AFD
    estado = 0
    error = False
    lexema = ""
    for i in range(len(cadena)):
        if estado == 0:
            if cadena[i] == '>':
                estado = 1
                lexema += cadena[i]
            elif cadena[i] == '<':
                estado = 4
                lexema += cadena[i]
            elif cadena[i] == '=':
                estado = 7
                lexema += cadena[i]
            elif cadena[i] == '!':
                estado = 10
                lexema += cadena[i]
            else:
                error = True
                break
        elif estado == 1:
            if cadena[i] == '=': #No madnamos al estado 2 porque ya es de aceptacion
                lexema += cadena[i]

            else:
                estado = 3
                
