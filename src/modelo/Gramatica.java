package modelo;

public class Gramatica implements Data {
    
        private String cadena;
        private String[] terminales;
        private String[] noTerminales;
        private String[] producciones;
        private String inicial;
    
        public Gramatica(String cadena, String[] terminales, String[] noTerminales, String[] producciones, String inicial) {
            this.cadena = cadena;
            this.terminales = terminales;
            this.noTerminales = noTerminales;
            this.producciones = producciones;
            this.inicial = inicial;
        }
    
        public Gramatica() {
            this.cadena = "";
            this.terminales = new String[0];
            this.noTerminales = new String[0];
            this.producciones = new String[0];
            this.inicial = "";
        }
    
        public String getCadena() {
            return cadena;
        }
    
        public String[] getTerminales() {
            return terminales;
        }
    
        public String[] getNoTerminales() {
            return noTerminales;
        }
    
        public String[] getProducciones() {
            return producciones;
        }
    
        public String getInicial() {
            return inicial;
        }
    
        public void setCadena(String cadena) {
            this.cadena = cadena;
        }
    
        public void setTerminales(String[] terminales) {
            this.terminales = terminales;
        }
    
        public void setNoTerminales(String[] noTerminales) {
            this.noTerminales = noTerminales;
        }
    
        public void setProducciones(String[] producciones) {
            this.producciones = producciones;
        }
    
        public void setInicial(String inicial) {
            this.inicial = inicial;
        }
    
        @Override
        public String toString() {
            return "Gramatica{" + "nombre=" + cadena + ", terminales=" + terminales + ", noTerminales=" + noTerminales + ", producciones=" + producciones + ", inicial=" + inicial + '}';
        }
    
}
