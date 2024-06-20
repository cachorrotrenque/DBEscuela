package seminario;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Seminario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SalidaExtracurricular salida = new SalidaExtracurricular();
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        AlumnoController alumnoController = new AlumnoController(alumnoDAO);
        AlumnoView alumnoView = new AlumnoView();
        ArrayList<Chofer> choferes = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Administrar Alumnos");
            System.out.println("2. Gestionar Salida Extracurricular");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();

            try {
                switch (opcion) {
                    case 1 -> administrarAlumnos(scanner, alumnoController, alumnoView);
                    case 2 -> gestionarSalidaExtracurricular(scanner, salida, choferes, alumnoController, alumnoView);
                    case 3 -> {
                        System.out.println("¡Hasta luego!");
                        System.exit(0); // Salir del programa
                    }
                    default -> System.out.println("Opción no válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // limpiar el scanner
            }
        }
    }

    private static void administrarAlumnos(Scanner scanner, AlumnoController alumnoController, AlumnoView alumnoView) {
        System.out.println("\n--- Administrar Alumnos ---");
        System.out.println("1. Agregar Alumno");
        System.out.println("2. Modificar Datos Personales");
        System.out.println("3. Consultar Datos de Alumno");
        System.out.println("4. Eliminar Alumno");
        System.out.println("5. Listar Todos los Alumnos");
        System.out.print("Ingrese la opción deseada: ");
        int opcionAlumnos = scanner.nextInt();

        try {
            switch (opcionAlumnos) {
                case 1 -> agregarAlumno(scanner, alumnoController);
                case 2 -> modificarDatosPersonales(scanner, alumnoController);
                case 3 -> consultarDatosAlumno(scanner, alumnoController, alumnoView);
                case 4 -> eliminarAlumno(scanner, alumnoController);
                case 5 -> listarTodosLosAlumnos(alumnoController, alumnoView);
                default -> System.out.println("Opción no válida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // limpiar el scanner
        }
    }

 private static void agregarAlumno(Scanner scanner, AlumnoController alumnoController) {
    try {
        System.out.print("Nombre del alumno: ");
        String nombre = scanner.next();
        System.out.print("Apellido del alumno: ");
        String apellido = scanner.next();
        System.out.print("DNI del alumno: ");
        int dni = scanner.nextInt();
        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        String fechaNacimientoStr = scanner.next();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
        System.out.print("Curso: ");
        String curso = scanner.next();

        // Capturar datos de la ficha médica
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

        Alumno nuevoAlumno = new Alumno(dni, nombre, apellido, fechaNacimiento, curso);
        nuevoAlumno.setFichaMedica(fichaMedica);

        alumnoController.addAlumno(nuevoAlumno);
        System.out.println("Alumno agregado correctamente");
    } catch (InputMismatchException e) {
        System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
        scanner.next(); // limpiar el scanner
    }
}


    private static void modificarDatosPersonales(Scanner scanner, AlumnoController alumnoController) {
        try {
            System.out.print("Ingrese el DNI del alumno a modificar: ");
            int dni = scanner.nextInt();
            Alumno alumno = alumnoController.getAlumnoByDNI(dni);
            if (alumno != null) {
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.next();
                System.out.print("Nuevo apellido: ");
                String nuevoApellido = scanner.next();
                System.out.print("Nuevo DNI: ");
                int nuevoDNI = scanner.nextInt();
                System.out.print("Nueva fecha de nacimiento (AAAA-MM-DD): ");
                String fechaNacimientoStr = scanner.next();
                LocalDate nuevaFechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                System.out.print("Nuevo curso: ");
                String nuevoCurso = scanner.next();
                alumno.setNombre(nuevoNombre);
                alumno.setApellido(nuevoApellido);
                alumno.setDNI(nuevoDNI);
                alumno.setFechaNacimiento(nuevaFechaNacimiento);
                alumno.setCurso(nuevoCurso);
                alumnoController.updateAlumno(alumno);
                System.out.println("Datos del alumno actualizados correctamente");
            } else {
                System.out.println("Alumno no encontrado");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void consultarDatosAlumno(Scanner scanner, AlumnoController alumnoController, AlumnoView alumnoView) {
        try {
            System.out.print("Ingrese el DNI del alumno a consultar: ");
            int dni = scanner.nextInt();
            Alumno alumno = alumnoController.getAlumnoByDNI(dni);
            if (alumno != null) {
                alumnoView.printAlumnoDetails(alumno);
            } else {
                System.out.println("Alumno no encontrado");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void eliminarAlumno(Scanner scanner, AlumnoController alumnoController) {
        try {
            System.out.print("Ingrese el DNI del alumno a eliminar: ");
            int dni = scanner.nextInt();
            alumnoController.deleteAlumno(dni);
            System.out.println("Alumno eliminado correctamente");
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void listarTodosLosAlumnos(AlumnoController alumnoController, AlumnoView alumnoView) {
        List<Alumno> alumnos = alumnoController.getAllAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados");
        } else {
            for (Alumno alumno : alumnos) {
                alumnoView.printAlumnoDetails(alumno);
            }
        }
    }

    private static void gestionarSalidaExtracurricular(Scanner scanner, SalidaExtracurricular salida, ArrayList<Chofer> choferes, AlumnoController alumnoController, AlumnoView alumnoView) {
        try {
            System.out.println("\n--- Gestionar Salida Extracurricular ---");
            System.out.println("1. Crear Salida Extracurricular");
            System.out.println("2. Listar Alumnos en Salida");
            System.out.println("3. Agregar Alumno a Salida");
            System.out.println("4. Cambiar Destino");
            System.out.println("5. Cambiar Fecha");
            System.out.println("6. Modificar Salida Extracurricular");
            System.out.println("7. Eliminar Salida Extracurricular");
            System.out.print("Ingrese la opción deseada: ");
            int opcionSalida = scanner.nextInt();

            switch (opcionSalida) {
                case 1 -> crearSalidaExtracurricular(scanner, salida, choferes, alumnoController, alumnoView);
                case 2 -> salida.listarAlumnos();
                case 3 -> agregarAlumnoASalida(scanner, salida, alumnoController);
                case 4 -> cambiarDestino(scanner, salida);
                case 5 -> cambiarFecha(scanner, salida);
                case 6 -> modificarSalidaExtracurricular(scanner, salida);
                case 7 -> eliminarSalidaExtracurricular(salida);
                default -> System.out.println("Opción no válida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void crearSalidaExtracurricular(Scanner scanner, SalidaExtracurricular salida, ArrayList<Chofer> choferes, AlumnoController alumnoController, AlumnoView alumnoView) {
        try {
            System.out.print("Destino de la salida: ");
            String destino = scanner.next();
            System.out.print("Fecha de la salida (yyyy-mm-dd): ");
            String fechaStr = scanner.next();
            Date fecha = null;
            try {
                fecha = Date.valueOf(fechaStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Fecha en formato incorrecto. Por favor, utilice el formato yyyy-mm-dd.");
                return;
            }

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
                Alumno alumno = alumnoController.getAlumnoByDNI(dniAlumno);
                if (alumno != null) {
                    alumnosSalida.add(alumno);
                } else {
                    System.out.println("Alumno con DNI " + dniAlumno + " no encontrado.");
                }
            }

            salida.crearSalidaExtracurricular(destino, fecha, chofer, null, alumnosSalida); // Asume que el docente se establece más adelante
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void agregarAlumnoASalida(Scanner scanner, SalidaExtracurricular salida, AlumnoController alumnoController) {
        try {
            System.out.print("Ingrese el DNI del alumno a agregar: ");
            int dni = scanner.nextInt();
            Alumno alumno = alumnoController.getAlumnoByDNI(dni);
            if (alumno != null) {
                salida.agregarAlumno(alumno);
            } else {
                System.out.println("Alumno no encontrado");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos. Por favor, intente de nuevo.");
            scanner.next(); // limpiar el scanner
        }
    }

    private static void cambiarDestino(Scanner scanner, SalidaExtracurricular salida) {
        System.out.print("Ingrese el nuevo destino: ");
        String nuevoDestino = scanner.next();
        salida.cambiarDestino(nuevoDestino);
    }

    private static void cambiarFecha(Scanner scanner, SalidaExtracurricular salida) {
        System.out.print("Ingrese la nueva fecha (yyyy-mm-dd): ");
        String fechaStr = scanner.next();
        try {
            Date nuevaFecha = Date.valueOf(fechaStr);
            salida.cambiarFecha(nuevaFecha);
        } catch (IllegalArgumentException e) {
            System.out.println("Fecha en formato incorrecto. Por favor, utilice el formato yyyy-mm-dd.");
        }
    }

    private static void modificarSalidaExtracurricular(Scanner scanner, SalidaExtracurricular salida) {
        System.out.print("Ingrese el nuevo destino: ");
        String nuevoDestino = scanner.next();
        System.out.print("Ingrese la nueva fecha (yyyy-mm-dd): ");
        String fechaStr = scanner.next();
        try {
            Date nuevaFecha = Date.valueOf(fechaStr);
            salida.modificarSalidaExtracurricular(nuevoDestino, nuevaFecha);
        } catch (IllegalArgumentException e) {
            System.out.println("Fecha en formato incorrecto. Por favor, utilice el formato yyyy-mm-dd.");
        }
    }

    private static void eliminarSalidaExtracurricular(SalidaExtracurricular salida) {
        salida.eliminarSalidaExtracurricular();
    }

    private static Chofer buscarChofer(ArrayList<Chofer> choferes, int dni) {
        for (Chofer chofer : choferes) {
            if (chofer.getDNI() == dni) {
                return chofer;
            }
        }
        return null;
    }
}
