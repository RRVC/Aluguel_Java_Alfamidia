package repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RepositoryImpl<Id, Tipo> implements Repository<Id, Tipo> {

    Map<Id, Tipo> repository = new TreeMap<>();

    public List<Tipo> buscarTodos() {
        return repository.values().stream().collect(Collectors.toList());
    }

    public Tipo buscaPorId(Id id) {
        return repository.get(id);
    }

    public void salvar(Id chave, Tipo objeto) {
        repository.put(chave, objeto);
    }

    public void excluir(Id id) {
        repository.remove(id);
    }

}