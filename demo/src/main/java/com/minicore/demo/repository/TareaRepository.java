package com.minicore.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minicore.demo.models.Tarea;


@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
