package com.example.odontologo.repository;

import org.springframework.context.annotation.Bean;

import java.util.List;

public interface IDao <T>{
    public T save(T data);
    public long delete(long id);
    public T update(T data);
    public T read(long id);
    public List<T> showAll();
}
