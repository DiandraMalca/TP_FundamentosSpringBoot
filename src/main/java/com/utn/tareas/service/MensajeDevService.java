package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public String mostrarBienvenida() {
        return "👋 [MODO DEV] ¡Hola Desarrollador! Listo para codear.";
    }

    @Override
    public String mostrarDespedida() {
        return "👋 [MODO DEV] Cerrando sesión. No olvides hacer commit.";
    }
}