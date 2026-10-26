package pe.edu.upeu.crudproductosh.model;

public enum Perspectiva {
    FINANCIERA("Financiera", "💰", "Rentabilidad, ingresos sostenibles y control de costos unitarios"),
    CLIENTES("Clientes", "👥", "Experiencia de pasajero, lealtad y cobertura de mercado"),
    PROCESOS_INTERNOS("Procesos internos", "🔧", "Puntualidad, fiabilidad operacional y productividad"),
    APRENDIZAJE_Y_CRECIMIENTO("Aprendizaje y crecimiento", "📚", "Capacitación, cultura, seguridad y digitalización");

    private final String nombre;
    private final String icono;
    private final String descripcion;

    Perspectiva(String nombre, String icono, String descripcion) {
        this.nombre = nombre;
        this.icono = icono;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIcono() {
        return icono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Perspectiva fromString(String text) {
        if (text == null) return FINANCIERA;
        for (Perspectiva p : Perspectiva.values()) {
            if (p.nombre.equalsIgnoreCase(text.trim()) || p.name().equalsIgnoreCase(text.trim())) {
                return p;
            }
        }
        return FINANCIERA;
    }
}
