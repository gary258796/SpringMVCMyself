package com.gary.dao.imp;

import com.gary.dao.UserDao;
import com.gary.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

	// set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Override
	public User findByUserEmail(String theUserEmail) {

		logger.info("\n >>> Finding User by Email..... \n");


		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where email=:uEmail", User.class);
		theQuery.setParameter("uEmail", theUserEmail);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		if( theUser != null )
			logger.info("\n >>> User Name : " +  theUser.getUserName() +" Found! \n");
		else
			logger.info("\n >>> User Not Found! \n");

		return theUser;
	}

	@Override
	public String retNameById(int id) {
		User theUser = findById( id ) ;
		return theUser.getUserName() ;
	}

	@Override
	public User findByUserName(String theUserName) {

		logger.info("\n >>> Finding User by Name..... \n");

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}


		if( theUser != null )
			logger.info("\n >>> User Name : " +  theUser.getUserName() +" Found! \n");
		else
			logger.info("\n >>> User Not Found! \n");



		return theUser;
	}

	// ----------------------------------------------------


	@Override
	public User findById(int id) {
		logger.info("\n >>> Finding User by Id..... \n");

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where id=:uId", User.class);
		theQuery.setParameter("uId", id);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		if( theUser != null )
			logger.info("\n >>> User id : " +  theUser.getId() +" Found! \n");
		else
			logger.info("\n >>> User Not Found! \n");

		return theUser;
	}

	@Override
	public List<User> findByName(String name) {
		return null;
	}

	@Override
	public List<User> findByEmail(String email) {
		return null;
	}

	@Override
	public void saveUser(User user) {

		logger.info("\n >>> Saving User : " + user.getUserName() + "to DataBase..... \n");

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create the user ... finally LOL
		currentSession.saveOrUpdate(user);

		logger.info("\n >>> Success saving!!");
	}

	@Override
	public void updateUser(User user) { // 給編輯修改基本信息呼叫用
		logger.info("\n >>> Updating User to DataBase..... \n");


		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create the user ... finally LOL
		currentSession.update(user);

		logger.info("\n >>> Success Update!!");
	}

	@Override
	public void updateUserImage(User user) { // 編輯修改圖片時呼叫用
		logger.info("\n >>> Finding User by Email..... \n");


		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username

		String hql = "update User u set u.imgUrl=:uimgUrl where u.id=:userId" ;
		Query theQuery = currentSession.createQuery(hql);
		theQuery.setParameter("uimgUrl", user.getImgUrl() );
		theQuery.setParameter("userId", user.getId());

		theQuery.executeUpdate();
	}

	@Override
	public void deleteUser(User user) {

	}

	@Override
	public Long findUserCount() {
		return null;
	}

	@Override
	public List<User> findAllUser() {
		logger.info("\n >>> Finding All User..... \n");

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User", User.class);

		List<User> allUser = null;
		try {
			allUser = theQuery.getResultList() ;
		} catch (Exception e) {
			allUser = null;
		}

		logger.info("\n >>> Success Retrieve All User ..... \n");
		return allUser;
	}

	@Override
	public List<User> findUserByPage(int pageNo, int pageSize) {
		return null;
	}
}
