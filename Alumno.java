package seminario;

public class Alumno extends Persona {
    private String aula;
    private FichaMedica fichamedica;

    public Alumno(String aula, FichaMedica fichamedica, String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
        this.aula = aula;
        this.fichamedica = fichamedica;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public FichaMedica getFichamedica() {
        return fichamedica;
    }

    public void setFichamedica(FichaMedica fichamedica) {
        this.fichamedica = fichamedica;
    }

    // Método para modificar datos personales del alumno
    public void modificarDatosPersonales(String nuevoNombre, String nuevoApellido, int nuevoDNI) {
        this.setNombre(nuevoNombre);
        this.setApellido(nuevoApellido);
        this.setDNI(nuevoDNI);
        System.out.println("Datos personales modificados correctamente");
    }

    // Método para cambiar de aula
    public void cambiarDeAula(String nuevaAula) {
        this.aula = nuevaAula;
        System.out.println("El alumno ha sido cambiado al aula: " + nuevaAula);
    }

    // Método para consultar datos del alumno
    public void consultarDatosDelAlumno() {
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Apellido: " + this.getApellido());
        System.out.println("DNI: " + this.getDNI());
        System.out.println("Aula: " + this.getAula());
    }

    // Método para eliminar alumno
    public void eliminarAlumno() {
        // Implementa la lógica para eliminar al alumno (por ejemplo, establecer atributos a null)
        System.out.println("Alumno eliminado correctamente");
    }
}
