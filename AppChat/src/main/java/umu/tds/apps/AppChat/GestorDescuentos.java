package umu.tds.apps.AppChat;

import java.util.ArrayList;
import java.util.List;

public class GestorDescuentos {

	private List<Descuento> descuentos;
    
    public GestorDescuentos() {
        this.descuentos = new ArrayList<>();
    }
    
    // Añade una nueva estrategia de descuento al gestor
    public void agregarDescuento(Descuento descuento) {
        descuentos.add(descuento);
    }
    
    // Calcula el mejor precio aplicando el descuento más favorable para el usuario
    public double calcularMejorDescuento(Usuario usuario) {
        return descuentos.stream()
            .filter(descuento -> descuento.esAplicable(usuario))
            .map(descuento -> descuento.calcularDescuentoFinal())
            .min(Double::compareTo)
            .orElse(Descuento.PRECIO_BASE);
    }
}
