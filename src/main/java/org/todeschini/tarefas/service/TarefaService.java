package org.todeschini.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todeschini.tarefas.model.Tarefa;
import org.todeschini.tarefas.repository.TarefaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService implements ITarefaService {

    @Autowired
    private TarefaRepository repository;
    @Override
    public List<Tarefa> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Tarefa> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Tarefa salarTarefa(Tarefa t) {
        return repository.save(t);
    }

    @Override
    public void deleteTarefaById(Integer id) {
        repository.deleteById(id);
    }
}
