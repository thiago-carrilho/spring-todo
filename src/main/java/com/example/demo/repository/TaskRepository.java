package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
