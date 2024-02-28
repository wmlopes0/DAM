def pedirEntero(texto):
    return int(input(texto))


if __name__ == '__main__':
    lado = pedirEntero("Introduce en cm el lado de un cuadrado:")
    perimetro = lado * 4
    print(f"El perímetro del cuadrado es: {perimetro} cm")
