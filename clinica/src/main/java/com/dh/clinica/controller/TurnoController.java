package com.dh.clinica.controller;

import com.dh.clinica.entity.Turno;
import com.dh.clinica.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Turno turno){
        Turno turnoGuardado = turnoService.guardarTurno(turno);
        if (turnoGuardado != null){
            return ResponseEntity.ok(turnoGuardado);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El paciente o el odontologo no fueron encontrados");
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id){
        Optional<Turno> turno = turnoService.buscarPorId(id);
        if (turno.isPresent()){
            return ResponseEntity.ok(turno.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificar(@RequestBody Turno turno){
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(turno.getId());
        if (turnoEncontrado.isPresent()){
            turnoService.modificarTurno(turno);
            String jsonResponse = "{\"mensaje\": \"El turno fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> modificar(@PathVariable Integer id){
        Optional<Turno> turno = turnoService.buscarPorId(id);
        if (turno.isPresent()){
            turnoService.eliminarTurno(id);
            String jsonResponse = "{\"mensaje\": \"El turno fue eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
