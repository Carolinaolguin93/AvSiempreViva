package persistence.commons;


public interface GenericDAO<T> {

	public T find(Integer id);
	public int countAll();
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
}
