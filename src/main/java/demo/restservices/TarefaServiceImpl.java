package demo.restservices;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TarefaServiceImpl implements TarefaService {

    private static Map<Integer, Tarefa> tarefas = new HashMap<Integer, Tarefa>();
    private int nextId = 1;

    @Override
    public Tarefa getTarefa(int id) {
        return tarefas.get(id);
    }

    @Override
    public Collection<Tarefa> getTarefas() {
        return tarefas.values();
    }

    @Override
    public void insert(Tarefa tarefa) {
        tarefa.setId(nextId++);
        tarefas.put(tarefa.getId(), tarefa);
    }

    @Override
    public void update(Tarefa tarefa) {
        int id = tarefa.getId();
        if (tarefas.containsKey(id)) {
            tarefas.put(id, tarefa);
        }
    }

    @Override
    public void delete(int id) {
        Tarefa tarefa = tarefas.get(id);
        if (tarefa != null) {
            tarefas.remove(id);
        }
    }
}
