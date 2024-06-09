package seminario;


import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Seminario {
//método main, se comienza a ejecutar el programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SalidaExtracurricular salida = new SalidaExtracurricular();
        ArrayList<Alumno> alumnos = new ArrayList<>();
        ArrayList<Chofer> choferes = new ArrayList<>();
//menú principal, se imprime en pantalla las distintas opciones y se almacena 
//en la variable opcion
        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Administrar Alumnos");
            System.out.println("2. Gestionar Salida Extracurricular");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();
//manejo de error, si se ingresa una opcion equivocada se indicará en pantalla
            try {
                switch (opcion) {
                    case 1 -> administrarAlumnos(scanner, alumnos);
                    case 2 -> gestionarSalidaExtracurricular(scanner, salida, choferes, alumnos);
                    case 3 -> {
                        System.out.println("¡Hasta luego!");
                        System.exit(0); // Salir del programa
                    }
                    default -> System.out.println("Opción no válida");
                }
                //al "atrapar" el error se indicará que ingrese una opción valida
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // limpiar el scanner
            }
        }
    }

    private static void administrarAlumnos(Scanner scanner, ArrayList<Alumno> alumnos) {
        System.out.println("\n--- Administrar Alumnos ---");
        System.out.println("1. Agregar Alumno");
        System.out.println("2. Modificar Datos Personales");
        System.out.println("3. Consultar Datos de Alumno");
        System.out.println("4. Eliminar Alumno");
        System.out.print("Ingrese la opción deseada: ");
        int opcionAlumnos = scanner.nextInt();

        try {
            switch (opcionAlumnos) {
                case 1 -> agregarAlumno(scanner, alumnos);
                case 2 -> modificarDatosPersonales(scanner, alumnos);
                case 3 -> consultarDatosAlumno(scanner, alumnos);
                case 4 -> eliminarAlumno(scanner, alumnos);
                default -> System.out.println("Opción no válida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void agregarAlumno(Scanner scanner, ArrayList<Alumno> alumnos) {
        try {
            System.out.print("Nombre del alumno: ");
            String nombre = scanner.next();
            System.out.print("Apellido del alumno: ");
            String apellido = scanner.next();
            System.out.print("DNI del alumno: ");
            int dni = scanner.nextInt();
            System.out.print("Aula asignada: ");
            String aula = scanner.next();

            FichaMedica fichaMedica = new FichaMedica();
            System.out.print("Historial médico: ");
            fichaMedica.setHistorial(scanner.next());
            System.out.print("Alergias: ");
            fichaMedica.setAlergias(scanner.next());
            System.out.print("Nombre de contacto de emergencia: ");
            fichaMedica.setEmergenciaNombre(scanner.next());
            System.out.print("Número de contacto de emergencia: ");
            int emergenciaNro = scanner.nextInt();
            fichaMedica.setEmergenciaNro(emergenciaNro);
            System.out.print("Tipo de sangre: ");
            fichaMedica.setTipoSangre(scanner.next());

            Alumno nuevoAlumno = new Alumno(aula, fichaMedica, nombre, apellido, dni);
            alumnos.add(nuevoAlumno);
            System.out.println("Alumno agregado correctamente");
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void modificarDatosPersonales(Scanner scanner, ArrayList<Alumno> alumnos) {
        try {
            System.out.print("Ingrese el DNI del alumno a modificar: ");
            int dni = scanner.nextInt();
            for (Alumno alumno : alumnos) {
                if (alumno.getDNI() == dni) {
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.next();
                    System.out.print("Nuevo apellido: ");
                    String nuevoApellido = scanner.next();
                    System.out.print("Nuevo DNI: ");
                    int nuevoDNI = scanner.nextInt();
                    alumno.modificarDatosPersonales(nuevoNombre, nuevoApellido, nuevoDNI);
                    return;
                }
            }
            System.out.println("Alumno no encontrado");
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void consultarDatosAlumno(Scanner scanner, ArrayList<Alumno> alumnos) {
        try {
            System.out.print("Ingrese el DNI del alumno a consultar: ");
            int dni = scanner.nextInt();
            for (Alumno alumno : alumnos) {
                if (alumno.getDNI() == dni) {
                    alumno.consultarDatosDelAlumno();
                    return;
                }
            }
            System.out.println("Alumno no encontrado");
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void eliminarAlumno(Scanner scanner, ArrayList<Alumno> alumnos) {
        try {
            System.out.print("Ingrese el DNI del alumno a eliminar: ");
            int dni = scanner.nextInt();
            alumnos.removeIf(alumno -> alumno.getDNI() == dni);
            System.out.println("Alumno eliminado correctamente");
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void gestionarSalidaExtracurricular(Scanner scanner, SalidaExtracurricular salida, ArrayList<Chofer> choferes, ArrayList<Alumno> alumnos) {
        try {
            System.out.println("\n--- Gestionar Salida Extracurricular ---");
            System.out.println("1. Crear Salida Extracurricular");
            System.out.println("2. Listar Alumnos en Salida");
            System.out.print("Ingrese la opción deseada: ");
            int opcionSalida = scanner.nextInt();

            switch (opcionSalida) {
                case 1 -> crearSalidaExtracurricular(scanner, salida, choferes, alumnos);
                case 2 -> salida.listarAlumnos();
                default -> System.out.println("Opción no válida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void crearSalidaExtracurricular(Scanner scanner, SalidaExtracurricular salida, ArrayList<Chofer> choferes, ArrayList<Alumno> alumnos) {
        try {
            System.out.print("Destino de la salida: ");
            String destino = scanner.next();
            System.out.print("Fecha de la salida (yyyy-mm-dd): ");
            String fechaStr = scanner.next();
            Date fecha = Date.valueOf(fechaStr);

            System.out.print("DNI del chofer: ");
            int dniChofer = scanner.nextInt();
            Chofer chofer = buscarChofer(choferes, dniChofer);
            if (chofer == null) {
                System.out.println("Chofer no encontrado");
                return;
            }

            System.out.print("Ingrese el DNI de los alumnos que participarán (separados por espacios, termine con -1): ");
            ArrayList<Alumno> alumnosSalida = new ArrayList<>();
            while (true) {
                int dniAlumno = scanner.nextInt();
                if (dniAlumno == -1) break;
                Alumno alumno = buscarAlumno(alumnos, dniAlumno);
                if (alumno != null) {
                    alumnosSalida.add(alumno);
                } else {
                    System.out.println("Alumno con DNI " + dniAlumno + " no encontrado.");
                }
            }

            salida.setDestino(destino);
            salida.setFecha(fecha);
            salida.setChofer(chofer);
            salida.setAlumnos(alumnosSalida);
            System.out.println("Salida extracurricular creada correctamente");
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        } catch (IllegalArgumentException e) {
            System.out.println("Fecha en formato incorrecto. Por favor, utilice el formato yyyy-mm-dd.");
        }
    }

    private static Chofer buscarChofer(ArrayList<Chofer> choferes, int dni) {
        for (Chofer chofer : choferes) {
            if (chofer.getDNI() == dni) {
                return chofer;
            }
        }
        return null;
    }

    private static Alumno buscarAlumno(ArrayList<Alumno> alumnos, int dni) {
        for (Alumno alumno : alumnos) {
            if (alumno.getDNI() == dni) {
                return alumno;
            }
        }
        return null;
    }
}
