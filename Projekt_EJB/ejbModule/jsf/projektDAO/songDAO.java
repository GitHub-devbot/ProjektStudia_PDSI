package jsf.projektDAO;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.projekt.Song;

@Stateless
public class songDAO {

@PersistenceContext	
EntityManager em;	

public void create(Song song) {
	em.persist(song);
}

public Song merge(Song song) {
	return em.merge(song);
}

public void remove(Song song) {
	em.remove(em.merge(song));
}

public Song find(Object id) {
	return em.find(Song.class, id);
}

public List<Song> getFullList() {
	List<Song> list = null;

	Query query = em.createQuery("select p from Song p");

	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}

public List<Song> getList(Map<String, Object> searchParams) {
	List<Song> list = null;

	// 1. Build query string with parameters
	String select = "select p ";
	String from = "from Song p ";
	String where = "";
	String orderby = "order by p.band asc, p.songName";

	// search for surname
	String band = (String) searchParams.get("band");
	if (band != null) {
		if (where.isEmpty()) {
			where = "where ";
		} else {
			where += "and ";
		}
		where += "p.band like :band ";
	}
	
	// ... other parameters ... 

	// 2. Create query object
	Query query = em.createQuery(select + from + where + orderby);

	// 3. Set configured parameters
	if (band != null) {
		query.setParameter("band", band+"%");
	}

	// ... other parameters ... 

	// 4. Execute query and retrieve list of Person objects
	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}

}