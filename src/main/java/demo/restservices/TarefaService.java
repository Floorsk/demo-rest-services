package demo.restservices;

import java.util.Collection;

public interface TarefaService {
    
    Tarefa getTarefa(int id);
    Collection<Tarefa> getTarefas();

    void insert(Tarefa tarefa);
    void update(Tarefa tarefa);
    void delete(int id);

}
