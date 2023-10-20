package org.todeschini.tarefas.service;

import org.todeschini.tarefas.model.Tarefa;

import java.util.List;
import java.util.Optional;

public interface ITarefaService {

    public List<Tarefa> findAll();

    public Optional<Tarefa> findById(Integer id);

    public Tarefa salarTarefa(Tarefa t);

    public void deleteTarefaById(Integer id);
}
