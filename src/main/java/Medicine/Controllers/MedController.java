package Medicine.Controllers;

import Medicine.DAO.MedDAOImpl;
import Medicine.Entities.Medicament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@ComponentScan("Medicine")
public class MedController {
    private final MedDAOImpl medDAO;

    @Autowired
    public MedController(MedDAOImpl medDAO) {
        this.medDAO = medDAO;
    }

    @GetMapping("/")
    public String mainMenu() {
        return "Menu";
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("meds", medDAO.getMedList());
        return "Show";
    }

    @GetMapping(value = "/show", headers = {"Accept=application/json"})
    public ResponseEntity<List<Medicament>> getMedList() {
        return new ResponseEntity<>(medDAO.getMedList(), HttpStatus.OK);
    }

    @GetMapping(value = "/show/{id}", headers = {"Accept=application/json"})
    public ResponseEntity<Medicament> find(@PathVariable("id") int id) {
        Medicament med = medDAO.getMedByID(id);
        if (med == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(med, HttpStatus.OK);
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("med", new Medicament());
        return "Add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("med") Medicament med, BindingResult result) {
        if (result.hasErrors()) {
            return "Add";
        }
        medDAO.addMed(med);
        return "redirect:/";
    }

    @PostMapping(value = "/add", headers = {"Accept=application/json"})
    public ResponseEntity<Medicament> add(@RequestBody Medicament med) {
        medDAO.addMed(med);
        return new ResponseEntity<>(med, HttpStatus.CREATED);
    }

    @GetMapping("/remove")
    public String remove() {
        return "Remove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam(value = "id") int id, Model model) {
        if (medDAO.getMedByID(id) == null) {
            String message = "There is no such medicament";
            model.addAttribute("message", message);
            return "Remove";
        }
        medDAO.removeMed(id);
        return "redirect:/";
    }

    @DeleteMapping(value = "/remove/{id}", headers = {"Accept=application/json"})
    public ResponseEntity<Medicament> delete(@PathVariable("id") int id) {
        Medicament med = medDAO.getMedByID(id);
        if (med == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        medDAO.removeMed(id);
        return new ResponseEntity<>(med, HttpStatus.OK);
    }

    @GetMapping("/edit")
    public String editID() {
        return "Edit";
    }

    @PostMapping("/edit")
    public String editID(@RequestParam(value = "id") int id, Model model) {
        if (medDAO.getMedByID(id) == null) {
            String message = "There is no such medicament";
            model.addAttribute("message", message);
            return "Edit";
        }
        model.addAttribute(medDAO.getMedByID(id));
        return "redirect:/edit/" + id;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("med", medDAO.getMedByID(Integer.parseInt(id)));
        return "EditMed";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable String id, @Valid @ModelAttribute("med") Medicament med, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/edit/" + id;
        }
        medDAO.updateMed(Integer.parseInt(id),
                med.getName(),
                med.getType(),
                med.getForm(),
                med.getPrice(),
                med.getCount());
        return "redirect:/";
    }

    @PutMapping(value = "/edit/{id}", headers = {"Accept=application/json"})
    public ResponseEntity<Medicament> edit(@PathVariable int id, @RequestBody Medicament med) {
        Medicament found = medDAO.getMedByID(id);
        if (found == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        medDAO.updateMed(med.getId(),
                med.getName(),
                med.getType(),
                med.getForm(),
                med.getPrice(),
                med.getCount());
        return new ResponseEntity<>(med, HttpStatus.OK);
    }

    @GetMapping("/info")
    public String showBiggestCount(Model model) {
        int count = medDAO.findBiggestCount();
        model.addAttribute("count", count);
        return "BiggestCount";
    }


}
