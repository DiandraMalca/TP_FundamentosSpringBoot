package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

	private final TareaService tareaService;
	private final MensajeService mensajeService;

	// Inyección de dependencias por constructor
	public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
		this.tareaService = tareaService;
		this.mensajeService = mensajeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("----------------------------------------");

		// 1. Mensaje de bienvenida (cambia según el perfil dev/prod)
		System.out.println(mensajeService.mostrarBienvenida());

		// 2. Mostrar configuración actual
		tareaService.imprimirConfiguracion();

		// 3. Listar tareas iniciales (cargadas en el repositorio)
		System.out.println("\n--- Tareas Iniciales ---");
		tareaService.listarTodas().forEach(System.out::println);

		// 4. Agregar nueva tarea
		System.out.println("\n--- Agregando Tarea Nueva ---");
		try {
			tareaService.agregarTarea("Preparar el TP de Spring Boot", Prioridad.ALTA);
			System.out.println("Tarea agregada con éxito.");
		} catch (Exception e) {
			System.out.println("Error al agregar: " + e.getMessage());
		}

		// 5. Listar pendientes
		System.out.println("\n--- Tareas Pendientes ---");
		tareaService.listarPendientes().forEach(System.out::println);

		// 6. Completar una tarea (usamos ID 1 por ejemplo)
		System.out.println("\n--- Completando Tarea ID 1 ---");
		tareaService.marcarComoCompletada(1L);

		// 7. Mostrar estadísticas
		System.out.println("\n--- Estadísticas ---");
		System.out.println(tareaService.obtenerEstadisticas());

		// 8. Listar completadas
		System.out.println("\n--- Tareas Completadas ---");
		tareaService.listarCompletadas().forEach(System.out::println);

		// 9. Despedida
		System.out.println("\n" + mensajeService.mostrarDespedida());
		System.out.println("----------------------------------------");
	}
}