package dal;

import java.util.List;

import bo.Plat;

public interface GenericDao<T> {
	
	public List<Plat> selectAll();
	public Plat selectById(int id);
	public void update(Plat plat);
	public void delete(int id);

}
