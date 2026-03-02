package com.mycompany.galaxiajuego;
import java.util.Scanner;
import java.util.Random;

public class GalaxiaJuego {

    // Constantes del juego
    public static final int TOTAL_CASILLAS = 42;
    public static final int CASILLA_EXTRATERRESTRES = 31;
    public static final int CASILLA_AGUJERO_NEGRO = 33;
    public static final int RETROCESO_EXTRATERRESTRES = 13;
    public static final int CASILLA_INICIO = 1;

    // Scanner para leer la entrada del jugador
    public static final Scanner sc = new Scanner(System.in);
    // Generador de números aleatorios para simular los dados
    public static final Random rnd = new Random();

    public static void main(String[] args) {
        System.out.println("¡Bienvenido a LA GUÍA DEL VIAJERO INTERGALÁCTICO!\n");
        
        jugarPartida();
        
        System.out.println("¡Gracias por jugar! Hasta la próxima aventura cósmica 🚀");
        sc.close();
    }

    
     /* Método principal para controlar la partida*/
    public static void jugarPartida() {
        int posicionActual = CASILLA_INICIO;        
        
        
        int valorGalaxiaAnterior = 0;                

        System.out.println("Comienzas en la galaxia 000 (casilla 1)\n");

        while (true) {
            // Preguntamos si quiere lanzar los dados
            System.out.print("¿Lanzas los 3 dados intergalácticos? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();

            if (!respuesta.equals("s") && !respuesta.equals("si")) {
                System.out.println("¡Has abandonado la galaxia! Fin del viaje.");
                break;
            }

            // 1. Lanzamos los 3 dados de 0 a 8 (9 caras)
            int d1 = rnd.nextInt(9);   // 0..8
            int d2 = rnd.nextInt(9);
            int d3 = rnd.nextInt(9);

            // 2. Mostramos la galaxia obtenida (ejemplo: 184)
            System.out.println("\n¡Has obtenido la galaxia " + d1 + d2 + d3 + "!");

            // 3. Calculamos el "valor de la galaxia" según la regla del enunciado
            int suma = d1 + d2 + d3;
            int valorGalaxiaActual = sumaDigitos(suma);   // 13 → 1+3 = 4

            // 4. Calculamos cuántas casillas avanzamos (diferencia)
            int avance = Math.abs(valorGalaxiaActual - valorGalaxiaAnterior);

            System.out.println("Cálculo: " + d1 + "+" + d2 + "+" + d3 + " = " + suma
                    + " → " + (suma >= 10 ? suma / 10 + "+" + suma % 10 + " = " : "")
                    + valorGalaxiaActual);
            System.out.println("Diferencia con galaxia anterior: |" + valorGalaxiaActual
                    + " - " + valorGalaxiaAnterior + "| = " + avance + " casillas");

            // 5. Movemos al jugador
            posicionActual += avance;

            // 6. Comprobamos si se pasa de 42 → vuelve a 1
            if (posicionActual > TOTAL_CASILLAS) {
                System.out.println("¡Te has pasado de la casilla 42! Vuelves al inicio...");
                posicionActual = CASILLA_INICIO;
            }

            // 7. Mostramos nueva posición
            System.out.println("→ Te encuentras ahora en la casilla " + posicionActual);

            // 8. Comprobamos peligros especiales
            if (posicionActual == CASILLA_EXTRATERRESTRES) {
                System.out.println("¡Cuidado! Extraterrestres hostiles te capturan...");
                System.out.println("Retrocedes a la casilla " + RETROCESO_EXTRATERRESTRES + "!");
                posicionActual = RETROCESO_EXTRATERRESTRES;
            }

            if (posicionActual == CASILLA_AGUJERO_NEGRO) {
                System.out.println("¡HAS CAÍDO EN UN AGUJERO NEGRO!");
                System.out.println("Todo se vuelve oscuridad... Has perdido la partida.");
                break;
            }

            // 9. Comprobamos victoria
            if (posicionActual == TOTAL_CASILLAS) {
                System.out.println("¡ENHORABUENA! Has llegado exactamente a la casilla 42.");
                System.out.println("¡Has ganado el juego LA GUÍA DEL VIAJERO INTERGALÁCTICO!");
                break;
            }

            // 10. La galaxia actual pasa a ser la anterior para el siguiente turno
            valorGalaxiaAnterior = valorGalaxiaActual;

            System.out.println(); // línea en blanco para separar turnos
        }
    }

    /**
     * Función recursiva que suma los dígitos de un número hasta obtener un solo dígito.
     * Ejemplo: 13 → 1+3 = 4
     *          99 → 9+9 = 18 → 1+8 = 9
     */
    public static int sumaDigitos(int numero) {
        if (numero < 10) {
            return numero;                    // ya es un solo dígito → caso base
        } else {
            // Sumamos el dígito de las decenas y el de las unidades
            int suma = (numero / 10) + (numero % 10);
            return sumaDigitos(suma);         // llamada recursiva
        }
    }
}