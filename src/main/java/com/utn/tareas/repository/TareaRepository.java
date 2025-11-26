package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    // Usamos AtomicLong para generar IDs únicos automáticamente [cite: 59]
    private final AtomicLong generadorId = new AtomicLong();

    public TareaRepository() {
        // Inicializamos con algunas tareas de ejemplo como pide el TP [cite: 50]
        guardar(new Tarea(null, "Estudiar Spring Boot", false, Prioridad.ALTA));
        guardar(new Tarea(null, "Hacer las compras", false, Prioridad.MEDIA));
        guardar(new Tarea(null, "Ir al gimnasio", true, Prioridad.BAJA));
    }

    public Tarea guardar(Tarea tarea) {
        if (tarea.getId() == null) {
            // Es nueva
            tarea.setId(generadorId.incrementAndGet());
            tareas.add(tarea);
        } else {
            // Es actualización: Si ya existe en la lista, no hacemos nada
            // (porque al ser objetos en memoria, la referencia ya se actualizó sola)
            // Solo la agregamos si por alguna razón no estaba.
            if (buscarPorId(tarea.getId()).isEmpty()) {
                tareas.add(tarea);
            }
        }
        return tarea;
    }

    public List<Tarea> listarTodas() {
        return new ArrayList<>(tareas);
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public void eliminar(Long id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }
}