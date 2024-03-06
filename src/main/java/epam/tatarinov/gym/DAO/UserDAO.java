package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.models.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public String createUsername(String firstName, String lastName){
//        setUserID(getUserID()+1);
        String username = firstName + "." + lastName;
        long count = getUsernameCount(firstName, lastName);
        if (count >= 0)
            username = username + (count + 1);
        return username;
    }

    private int getUsernameCount(String firstName, String lastName){
        Session session = sessionFactory.getCurrentSession();
        String hql = "select max(id) from User u where u.firstName = :firstname and u.lastName = :lastname";
        Query query = session.createQuery(hql);
        query.setParameter("firstname", firstName);
        query.setParameter("lastname", lastName);
        Optional<Object> queryId = Optional.ofNullable(query.getSingleResult());
        if (!queryId.isPresent())
            return -1;

        hql = "select username from User u where u.id = :id";
        query = session.createQuery(hql);
        query.setParameter("id", (Integer)queryId.get());
        String queryUsername = (String)query.getSingleResult();

        if (queryUsername.length() == (firstName.length() + lastName.length() + 1))
            return 0;
        int count =  Integer.parseInt(queryUsername.substring(firstName.length() + lastName.length() + 1));
        return count;
    }

    public boolean usernamePasswordCheck(String username, String password){
        Session session = sessionFactory.getCurrentSession();
        String hql = "select count(*) as n from User u where u.username = :username and u.password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List l = query.getResultList();
        if ((Long) l.get(0) == 1)
            return true;
        return false;
    }

    public boolean userRequiredFieldValidation(User user){
        if (user.getFirstName() == null)
            return false;
        if (user.getLastName() == null)
            return false;
        if (user.getUsername() == null)
            return false;
        if (user.getPassword() == null)
            return false;
        return true;
    }
}
