package com.dh.clinica.controller;

import com.dh.clinica.entity.Paciente;
import com.dh.clinica.service.impl.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VistaController {
    private PacienteService pacienteService;

    public VistaController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/index")
    public String mostrarPacientePorId(Model model, @RequestParam Integer id){
        Paciente paciente = pacienteService.buscarPorId(id).get();
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "paciente";
    }

    @GetMapping("/index2/{id}")
    public String mostrarPacientePorId2(Model model, @PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPorId(id).get();
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "paciente";
    }
}
