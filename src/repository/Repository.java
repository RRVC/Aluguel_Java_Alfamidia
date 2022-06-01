package repository;

import java.util.List;

public interface Repository<Id, Tipo> {

	public List<Tipo> buscarTodos();

	public Tipo buscaPorId(Id id);

	public void salvar(Id chave, Tipo objeto);

	public void excluir(Id id);

}
