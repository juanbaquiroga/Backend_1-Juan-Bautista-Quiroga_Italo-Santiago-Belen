package com.dh.clinica.controller;

import com.dh.clinica.dto.request.TurnoModificarDto;
import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
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
    public ResponseEntity<?> guardar(@RequestBody TurnoRequestDto turnoRequestDto){

        TurnoResponseDto turnoGuardado = turnoService.guardarTurno(turnoRequestDto);
        if (turnoGuardado != null){
            return ResponseEntity.ok(turnoGuardado);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El paciente o el odontologo no fueron encontrados");
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoResponseDto> buscarPorId(@PathVariable Integer id){
        Optional<TurnoResponseDto> turno = turnoService.buscarPorId(id);
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
    public ResponseEntity<?> modificar(@RequestBody TurnoModificarDto turnoModificarDto){
        turnoService.modificarTurno(turnoModificarDto);
        return ResponseEntity.ok("{\"mensaje\": \"El paciente fue modificado\"}");
    }

    @DeleteMapping("/eliminar</{id}")
    public ResponseEntity<?> modificar(@PathVariable Integer id){
        turnoService.eliminarTurno(id);
        String jsonResponse = "{\"mensaje\": \"El turno fue eliminado\"}";
        return ResponseEntity.ok(jsonResponse);
    }
}
