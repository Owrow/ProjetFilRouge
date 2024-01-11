package dal;

import java.util.List;

public interface GenericDao<T> {
	
	public List<T> selectAll();
	public T selectById(int T);
	public void update(T donnee);
	public void insert(T donnee);
	public void delete(int T);
	

}
