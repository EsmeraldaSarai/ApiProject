package com.lopez.app.api.services;

import java.util.List;
import java.util.Optional;

public interface IService <T>{
    List<T> listar();
    Optional<T> getById(Long Id);
    void guardar (T t);



}
