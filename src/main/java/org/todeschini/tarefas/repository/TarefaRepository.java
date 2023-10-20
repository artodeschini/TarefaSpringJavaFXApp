package org.todeschini.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.todeschini.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}
