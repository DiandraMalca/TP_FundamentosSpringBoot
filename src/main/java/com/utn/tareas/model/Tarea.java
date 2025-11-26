package com.utn.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera Getters, Setters, toString, etc. automágicamente
@AllArgsConstructor // Genera un constructor con todos los argumentos
@NoArgsConstructor // Genera un constructor vacío
public class Tarea {

    private Long id;
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad;
}