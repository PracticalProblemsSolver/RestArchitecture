package Medicine;

import Medicine.Entities.Medicament;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Client {

    static final int FIRST = 1;
    static final int SECOND = 2;
    static final int THIRD = 3;
    static final int FOURTH = 4;
    static final int FIFTH = 5;
    static final int SIXTH = 6;

    public static String SHOW_URL = "http://localhost:8080/show";
    public static String FIND_ID = "http://localhost:8080/show/";
    public static String ADD_URL = "http://localhost:8080/add";
    public static String REMOVE_URL = "http://localhost:8080/remove/";
    public static String EDIT_URL = "http://localhost:8080/edit/";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        boolean flag = true;
        int userChoice;
        while (flag) {
            System.out.println("1. Get a list of medicaments");
            System.out.println("2. Get a medicament by ID");
            System.out.println("3. Add a new medicament");
            System.out.println("4. Remove a medicament by ID");
            System.out.println("5. Edit a medicament by ID");
            System.out.println("6. Exit");
            userChoice = InputCheck.getInt(0, 7);
            switch (userChoice) {
                case FIRST: {
                    List meds = restTemplate.getForEntity(SHOW_URL, List.class).getBody();
                    System.out.println();
                    for (Object med : meds) {
                        System.out.println(med.toString());
                    }
                    break;
                }
                case SECOND: {
                    System.out.println("Enter the medicine ID you want to get:");
                    int id = InputCheck.getInt();
                    try {
                        Medicament med = restTemplate.getForEntity(FIND_ID + id,
                                Medicament.class).getBody();
                        System.out.println();
                        System.out.println(med.toString());
                    } catch (HttpClientErrorException httpClientErrorException) {
                        System.out.println("There is no medicine with given ID");
                    }
                    break;
                }
                case THIRD: {
                    System.out.println("Enter the name of the medicine");
                    String name = InputCheck.getString();
                    System.out.println("Enter the form of the medicine");
                    String form = InputCheck.getString();
                    System.out.println("Enter the type of the medicine");
                    String type = InputCheck.getString();
                    System.out.println("Enter the price of the medicine");
                    double price = InputCheck.getDouble(0);
                    System.out.println("Enter the count of the medicine");
                    int count = InputCheck.getInt();
                    Medicament addMed = new Medicament(name, form, type, price, count);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<Medicament> requestEntity = new HttpEntity<>(addMed, headers);
                    restTemplate.postForEntity(ADD_URL, requestEntity, Medicament.class);
                    System.out.println("\nMedicament added");
                    break;
                }
                case FOURTH: {
                    System.out.println("Enter the medicine ID to remove:");
                    int id = InputCheck.getInt();
                    try {
                        restTemplate.delete(REMOVE_URL + id);
                        System.out.println("\nMedicament removed");
                    } catch (HttpClientErrorException httpClientErrorException) {
                        System.out.println("There is no medicine with given ID");
                    }
                    break;
                }
                case FIFTH: {
                    System.out.println("Enter the medicine ID to edit");
                    int id = InputCheck.getInt();
                    Medicament med;
                    try {
                        med = restTemplate.getForEntity(FIND_ID + id,
                                Medicament.class).getBody();
                    } catch (HttpClientErrorException httpClientErrorException) {
                        System.out.println("There is no medicine with given ID");
                        break;
                    }
                    System.out.println("Choose a field to update:");
                    System.out.println("1. Name");
                    System.out.println("2. Type");
                    System.out.println("3. Form");
                    System.out.println("4. Price");
                    System.out.println("5. Count");
                    switch (InputCheck.getInt(0, 6)) {
                        case FIRST: {
                            System.out.println("Enter the name of the medicine to update");
                            String name = InputCheck.getString();
                            med.setName(name);
                            restTemplate.put(EDIT_URL + med.getId(), med);
                            break;
                        }
                        case SECOND: {
                            System.out.println("Enter the type of the medicine to update");
                            String type = InputCheck.getString();
                            med.setType(type);
                            restTemplate.put(EDIT_URL + med.getId(), med);
                            break;
                        }
                        case THIRD: {
                            System.out.println("Enter the form of the medicine to update");
                            String form = InputCheck.getString();
                            med.setForm(form);
                            restTemplate.put(EDIT_URL + med.getId(), med);
                            break;
                        }
                        case FOURTH: {
                            System.out.println("Enter the price of the medicine to update");
                            double price = InputCheck.getDouble(0);
                            med.setPrice(price);
                            restTemplate.put(EDIT_URL + med.getId(), med);
                            break;
                        }
                        case FIFTH: {
                            System.out.println("Enter the count of the medicine to update");
                            int count = InputCheck.getInt();
                            med.setCount(count);
                            restTemplate.put(EDIT_URL + med.getId(), med);
                            break;
                        }
                    }
                    break;
                }
                case SIXTH: {
                    flag = false;
                    break;
                }
            }
        }
    }
}
