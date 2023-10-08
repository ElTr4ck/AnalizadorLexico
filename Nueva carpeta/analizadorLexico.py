import sys #Libreria para poder abrir archivos
from token import Token

existeError = False # variable para definir si existen errores


def ejecutaDesdePrograma():
    while(leido == ""):
        print(">>> ")
        leido = input()
        compruebaLinea(leido)

def main():
    #Comprobar como se esta corriendo el programa
    if len(sys.argv) > 2:
        print("Uso: python analizadorLexico.py nombre_de_archivo")
        sys.exit(1)
    # Abrir archivo desde la linea de comandos
    elif len(sys.argv) == 2:
        ejecutarDesdeArchivo()
    else:
        tokencito = Token("a", "b", "c")
        resultado = tokencito.toString()
        print(resultado)
        #ejecutaDesdePrograma()

if __name__ == "__main__":
    main()