package online.jadg13.rifa.controller;

import online.jadg13.rifa.model.Participante;
import online.jadg13.rifa.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/participante")
public class ParticipanteController {
    @Autowired
    private ParticipanteService service;

    @GetMapping
    public Map<String, Object> getAllParticipantes() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Participante> participantes = service.showAll();
            json.put("message", "Lista de participantes");
            json.put("participantes", participantes);
        } catch (Exception e) {
            json.put("message", "Error al obtener la lista de participantes: " + e.getMessage());
        }
        return json;
    }
   /* public ResponseEntity<List<Participante>> getAllParticipantes() {
        List<Participante> participantes = service.showAll();
        return ResponseEntity.ok(participantes);
    }
*/
    @PostMapping
    public Map<String, String> createParticipante(@RequestBody Map<String, String> request) {
        Map<String, String> json = new HashMap<>();
        try {
            Participante participante = new Participante();
            participante.setNombre(request.get("nombre"));
            participante.setApellido(request.get("apellido"));
            participante.setCedula(request.get("cedula"));
            participante.setCiudad(request.get("ciudad"));
            participante.setCelular(request.get("celular"));

            Participante savedParticipante = service.save(participante);
            json.put("message", "Participante creado con éxito");
            json.put("data", savedParticipante.toString());
        } catch (Exception e) {
            json.put("message", "Error al crear el participante: " + e.getMessage());
        }
        return json;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getParticipanteById(@PathVariable("id") String id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Long idLong = Long.parseLong(id);
            Participante participante = service.showById(idLong).orElse(null);
            if (participante != null) {
                json.put("message", "Participante encontrado");
                json.put("participante", participante);
            } else {
                json.put("message", "Participante no encontrado");
            }
        } catch (Exception e) {
            json.put("message", "Error al obtener el participante: " + e.getMessage());
        }
        return json;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteParticipante(@PathVariable("id") String id) {
        Map<String, String> json = new HashMap<>();
        try {
            Long idLong = Long.parseLong(id);
            service.delete(idLong);
            json.put("message", "Participante eliminado con éxito");
        } catch (Exception e) {
            json.put("message", "Error al eliminar el participante: " + e.getMessage());
        }
        return json;
    }

    @PutMapping("/{id}")
    public Map<String, String> updateParticipante(@PathVariable("id") String id, @RequestBody Map<String, String> request) {
        Map<String, String> json = new HashMap<>();
        try {
            Long idLong = Long.parseLong(id);
            Participante participante = service.showById(idLong).orElse(null);
            if (participante != null) {
                participante.setNombre(request.get("nombre"));
                participante.setApellido(request.get("apellido"));
                participante.setCedula(request.get("cedula"));
                participante.setCiudad(request.get("ciudad"));
                participante.setCelular(request.get("celular"));

                Participante updatedParticipante = service.update(participante);
                json.put("message", "Participante actualizado con éxito");
                json.put("data", updatedParticipante.toString());
            } else {
                json.put("message", "Participante no encontrado");
            }
        } catch (Exception e) {
            json.put("message", "Error al actualizar el participante: " + e.getMessage());
        }
        return json;
    }

    //Agrupar por ciudad
    @GetMapping("/ciudad")
    public Map<String, Object> getParticipantesByCiudad() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Participante> participantes = service.showAll();
            Map<String, List<Participante>> groupedByCiudad = participantes.stream()
                    .collect(Collectors.groupingBy(Participante::getCiudad));
            json.put("message", "Lista de participantes agrupados por ciudad");
            json.put("participantes", groupedByCiudad);
        } catch (Exception e) {
            json.put("message", "Error al obtener la lista de participantes agrupados por ciudad: " + e.getMessage());
        }
        return json;
    }

}
