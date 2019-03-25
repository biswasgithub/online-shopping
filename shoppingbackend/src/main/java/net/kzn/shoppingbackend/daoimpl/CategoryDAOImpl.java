package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		String selectActiveCategory ="From Category where active=:active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		
		
		return query.getResultList();
	}

	/* Getting single category based on id */

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Category category) {
		try {

			sessionFactory.getCurrentSession().persist(category);
			return true;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Category category) {
		// TODO Auto-generated method stub
		try {

			sessionFactory.getCurrentSession().update(category);
			return true;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		// TODO Auto-generated method stub
		
		category.setActive(false);
		
		try {

			sessionFactory.getCurrentSession().update(category);
			return true;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * private static List<Category> categories = new ArrayList<>();
	 * 
	 * static {
	 * 
	 * Category category = new Category();
	 * 
	 * category.setId(1); category.setName("Television");
	 * category.setDescription("This is some description for television");
	 * category.setImageURL("CAT_1.png");
	 * 
	 * categories.add(category);
	 * 
	 * category = new Category();
	 * 
	 * category.setId(2); category.setName("Mobile");
	 * category.setDescription("This is some description for mobile");
	 * category.setImageURL("CAT_2.png");
	 * 
	 * categories.add(category);
	 * 
	 * category = new Category();
	 * 
	 * category.setId(3); category.setName("Laptop");
	 * category.setDescription("This is some description for l");
	 * category.setImageURL("CAT_3.png");
	 * 
	 * categories.add(category);
	 * 
	 * }
	 * 
	 * @Override public List<Category> list() { // TODO Auto-generated method stub
	 * return categories; }
	 * 
	 * @Override public Category get(int id) { // TODO Auto-generated method stub
	 * for (Category category : categories) { if (category.getId() == id) return
	 * category; }
	 * 
	 * return null; }
	 */

}
