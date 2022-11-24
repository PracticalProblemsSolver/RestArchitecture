package Medicine.DAO;

import Medicine.Entities.Medicament;

import java.util.List;

public interface MedDAO {
    public void addMed(Medicament medicament);

    public void removeMed(int id);

    public void updateMed(int id, String name, String type, String form, double price, int count);

    public int findBiggestCount();

    public List<Medicament> getMedList();

    public Medicament getMedByID(int id);
}
