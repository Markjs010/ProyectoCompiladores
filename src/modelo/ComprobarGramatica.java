package modelo;

public class ComprobarGramatica {

    // TODO Comprobar si es gramatica regular

    public static boolean esGramaticaRegular(Gramatica g) {
        String reglas = g.getCadena();
        String[] reglasSeparadas = reglas.split("->");
        for (int i = 0; i < reglasSeparadas.length; i++) {
            if (i % 2 == 0) {
                if (reglasSeparadas[i].length() > 2 || reglasSeparadas[i].length() < 1
                        || !Character.isUpperCase(reglasSeparadas[i].charAt(0))) {
                    return false;
                }
            }
        }
        return true;
    }

    // TODO Separar terminales y no terminales

    // TODO Quitar recursividad a la izquierda

    // TODO Obtener primeros y siguientes

}
