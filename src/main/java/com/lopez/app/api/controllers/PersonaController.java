package com.lopez.app.api.controllers;

import com.lopez.app.api.models.Persona;
import com.lopez.app.api.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/primos")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    private IService<Persona> personaIService;

    @PostMapping
    public Map<String, Object> guardar(@RequestBody Persona p){
        p.setEdad(p.getEdad() + 1);
        personaIService.guardar(p);
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("id: ", p.getId());
        respuesta.put("msg: ", "persona guardada correctamente");
        return respuesta;
    }

    @GetMapping
    public List<Persona> listar(){
        return personaIService.listar();
    }

    @GetMapping("/obtener/{id}")
    public Map<String, Object> obtenerPorId(@PathVariable (name = "id") Long id){
        Optional<Persona> persona = personaIService.getById(id);
        Map<String, Object> respuesta = new HashMap<String, Object>();
        if (persona.isPresent()) {
            Persona p = persona.get();
            respuesta.put("id", p.getId());
            respuesta.put("nombre", p.getNombre());
            respuesta.put("edad", p.getEdad());
            respuesta.put("edadFutura", p.getEdad()+1);
        }else {
            respuesta.put("msg", "Persona no encontrada");
        }
        return respuesta;
    }


    @PutMapping("/actualizar/{id}")
    public Map<String, String> actualizar(@RequestBody Persona p, @PathVariable(name = "id") Long id){
        Optional<Persona> persona = personaIService.getById(id);
        Map<String, String> respuesta = new HashMap<String, String>();
        if (persona.isPresent()) {
            p.setId(id);
            personaIService.guardar(p);
            respuesta.put("msg", "registro Actualizado");
        }else {
            respuesta.put("msg", "registro no actualizado");
        }
        return respuesta;
    }

}
