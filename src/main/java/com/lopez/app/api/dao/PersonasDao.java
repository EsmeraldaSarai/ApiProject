package com.lopez.app.api.dao;

import com.lopez.app.api.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonasDao extends JpaRepository <Persona, Long> {
}
