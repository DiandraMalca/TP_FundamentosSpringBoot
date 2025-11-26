package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {

    @Override
    public String mostrarBienvenida() {
        return "✅ [PROD] Sistema de Gestión de Tareas Iniciado.";
    }

    @Override
    public String mostrarDespedida() {
        return "🛑 [PROD] Sistema Finalizado.";
    }
}