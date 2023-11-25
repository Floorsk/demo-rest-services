package demo.restservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping(value="/tarefaManager")
@CrossOrigin
public class TarefaController {
    
    @Autowired
    private TarefaService service;

    // Get all tarefas
    @GetMapping(value="/tarefa", produces={"application/json", "application/xml"})
    public ResponseEntity<Collection<Tarefa>> getTarefas() {
        Collection<Tarefa> result = service.getTarefas();
        return ResponseEntity.ok().body(result);
    }

    // Get a unique tarefa
    @GetMapping(value="/tarefa/{id}", produces={"application/json", "application/xml"})
    public ResponseEntity<Tarefa> getTarefa(@PathVariable int id) {
        Tarefa result = service.getTarefa(id);
        if (result == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(result);
    }

    // Insert a new tarefa
    @PostMapping(value="tarefa", consumes={"application/json","application/xml"}, produces = {"application/json","application/xml"})
    public ResponseEntity<Tarefa> addTarefa(@ResquestBody Tarefa tarefa) {
        service.insert(tarefa);
        URI uri = URI.create("/tarefa/" + tarefa.getId());
        return ResponseEntity.created(uri).body(tarefa);
    }

    // update an existing tarefa
    @PostMapping(value="/tarefa/{id}", consumes = {"application/json","application/xml"})
    public ResponseEntity<Void> modifyTarefa(@PathVariable int id, @ResquestBody Tarefa tarefa) {
        if (service.getTarefa(id) == null)
            return ResponseEntity.notFound().build();
        else
            service.update(tarefa);
            return ResponseEntity.ok().build();
    }

    // Delete an existing tarefa
    @DeleteMapping("/tarefa/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable int id) {
        if (service.getTarefa(id) == null)
            return ResponseEntity.notFound().build();
        else 
            service.delete(id);
            return ResponseEntity.ok().build();
    }
}
