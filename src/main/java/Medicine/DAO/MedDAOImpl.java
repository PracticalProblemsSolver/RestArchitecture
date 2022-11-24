package Medicine.DAO;

import Medicine.Entities.Medicament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MedDAOImpl implements MedDAO {
    static final int EMPTY_LIST = 0;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addMed(Medicament medicament) {
        Session newSession = this.sessionFactory.openSession();
        Transaction transaction = newSession.beginTransaction();
        newSession.save(medicament);
        transaction.commit();
        newSession.close();
    }

    @Override
    public void removeMed(int id) {
        Session newSession = this.sessionFactory.openSession();
        Transaction transaction = newSession.beginTransaction();
        Medicament med = newSession.get(Medicament.class, id);
        if (med == null) {
            return;
        }
        newSession.remove(med);
        transaction.commit();
        newSession.close();
    }

    @Override
    public void updateMed(int id, String name, String type, String form, double price, int count) {
        Session newSession = this.sessionFactory.openSession();
        Transaction transaction = newSession.beginTransaction();
        Medicament med = newSession.get(Medicament.class, id);
        if (med == null) {
            return;
        }
        med.setName(name);
        med.setType(type);
        med.setForm(form);
        med.setPrice(price);
        med.setCount(count);
        newSession.update(med);
        transaction.commit();
        newSession.close();
    }

    @Override
    public int findBiggestCount() {
        Session newSession = this.sessionFactory.openSession();
        Medicament med = (Medicament) newSession.
                createQuery("SELECT m FROM Medicament m ORDER BY m.count DESC").
                setMaxResults(1).uniqueResult();
        if (med != null) {
            return med.getCount();
        }
        return EMPTY_LIST;
    }

    @Override
    public List<Medicament> getMedList() {
        Session newSession = this.sessionFactory.openSession();
        List<Medicament> getList = (List<Medicament>) newSession.createQuery("From Medicament").list();
        return getList;
    }

    @Override
    public Medicament getMedByID(int id) {
        Session newSession = this.sessionFactory.openSession();
        Transaction transaction = newSession.beginTransaction();
        Medicament med = newSession.get(Medicament.class, id);
        if (med == null) {
            return null;
        }
        transaction.commit();
        newSession.close();
        return med;
    }


}
