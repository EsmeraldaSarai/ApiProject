package com.lopez.app.api.services;

import com.lopez.app.api.dao.PersonasDao;
import com.lopez.app.api.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements IService <Persona>{

    @Autowired
    private PersonasDao personasDao;


    @Override
    public List<Persona> listar() {
        // TODO Auto-generated method stub
        List<Persona> personas = new ArrayList<Persona>();
        personas = (List<Persona>) personasDao.findAll();
        return personas;
    }

    @Override
    public Optional<Persona> getById(Long id) {
        // TODO Auto-generated method stub
        Optional<Persona> personas = personasDao.findById(id);
        return personas;
    }

    @Override
    public void guardar(Persona t) {
        // TODO Auto-generated method stub
        this.personasDao.save(t);

    }
}
