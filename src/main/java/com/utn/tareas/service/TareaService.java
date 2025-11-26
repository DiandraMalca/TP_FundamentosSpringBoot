package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    // Inyectamos los valores del application.properties
    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    @Value("${app.nombre}")
    private String nombreAplicacion;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        // Validación: No permitir más tareas si superamos el límite configurado
        if (tareaRepository.listarTodas().size() >= maxTareas) {
            throw new RuntimeException("No se pueden agregar más tareas. Límite alcanzado.");
        }

        Tarea nuevaTarea = new Tarea(null, descripcion, false, prioridad);
        return tareaRepository.guardar(nuevaTarea);
    }

    public List<Tarea> listarTodas() {
        return tareaRepository.listarTodas();
    }

    public List<Tarea> listarPendientes() {
        return tareaRepository.listarTodas().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarCompletadas() {
        return tareaRepository.listarTodas().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public void marcarComoCompletada(Long id) {
        tareaRepository.buscarPorId(id).ifPresent(tarea -> {
            tarea.setCompletada(true);
            tareaRepository.guardar(tarea);
        });
    }

    public String obtenerEstadisticas() {
        long total = tareaRepository.listarTodas().size();
        long completadas = listarCompletadas().size();
        long pendientes = listarPendientes().size();

        return String.format("Total: %d | Completadas: %d | Pendientes: %d",
                total, completadas, pendientes);
    }

    // Nuevo método para verificar que la configuración se cargó bien
    public void imprimirConfiguracion() {
        System.out.println("--- Configuración Cargada ---");
        System.out.println("App: " + nombreAplicacion);
        System.out.println("Máximo de tareas: " + maxTareas);
        System.out.println("Mostrar estadísticas: " + mostrarEstadisticas);
        System.out.println("-----------------------------");
    }
}