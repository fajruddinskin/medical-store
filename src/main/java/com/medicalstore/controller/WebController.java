package com.medicalstore.controller;

import com.medicalstore.entity.*;
import com.medicalstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;
    @Autowired
    private LabTestService labTestService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EnumService enumService;


    @GetMapping("/")
    public String index(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        List<UserModel> user =  userService.getAllCustomers();
        List<PatientModel> patients = patientService.getAllPatients();

        List<LabTestModel> labTests= labTestService.searchTests("CBC");
        List<Category> catagory=categoryService.getAllCategories();
        System.out.println(medicines.get(0).getCategory().getId());
        System.out.println(medicines.get(0).getCategory().getName());
        System.out.println(medicines.get(0).getCategory().getDescription());
        System.out.println( "================");
        model.addAttribute("labTests", labTests.size());
        System.out.println( "================");

        System.out.println( "================");
        model.addAttribute("totalPatients", patients.size());

        System.out.println( "================");
        model.addAttribute("medicines", medicines);
        model.addAttribute("customers", user);
        model.addAttribute("totalMedicines", medicines.size());
        model.addAttribute("totalCustomers",user.size());
        model.addAttribute("lowStockCount", medicines.stream().filter(m -> m.getQuantity() < 10).count());

        // Add recent data for dashboard
        model.addAttribute("recentMedicines",
                medicines.size() > 5 ? medicines.subList(0, 5) : medicines);
        model.addAttribute("recentCustomers",
                user.size() > 5 ? user.subList(0, 5) : user);
        model.addAttribute("medicineType", enumService.getMedicineType());
        model.addAttribute("catagory",catagory);
        model.addAttribute("bloodGroups", enumService.getBloodGroup());
        return "index";
    }

    // Medicine Management
    @GetMapping("/medicines")
    public String medicineManagement(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        List<UserModel> user =  userService.getAllCustomers();
        List<PatientModel> patients = patientService.getAllPatients();

        List<LabTestModel> labTests= labTestService.searchTests("CBC");
        List<Category> catagory=categoryService.getAllCategories();
        System.out.println(medicines.get(0).getCategory().getId());
        System.out.println(medicines.get(0).getCategory().getName());
        System.out.println(medicines.get(0).getCategory().getDescription());
        System.out.println( "================");
        model.addAttribute("labTests", labTests.size());
        System.out.println( "================");

        System.out.println( "================");
        model.addAttribute("totalPatients", patients.size());

        System.out.println( "================");
        model.addAttribute("medicines", medicines);
        model.addAttribute("customers", user);
        model.addAttribute("totalMedicines", medicines.size());
        model.addAttribute("totalCustomers",user.size());
        model.addAttribute("lowStockCount", medicines.stream().filter(m -> m.getQuantity() < 10).count());

        // Add recent data for dashboard
        model.addAttribute("recentMedicines",
                medicines.size() > 5 ? medicines.subList(0, 5) : medicines);
        model.addAttribute("recentCustomers",
                user.size() > 5 ? user.subList(0, 5) : user);
        model.addAttribute("medicineType", enumService.getMedicineType());
        model.addAttribute("catagory",catagory);

        return "medicine-management";
    }

    @PostMapping("/medicines")
    public String addMedicine(@ModelAttribute Medicine medicine) {
        medicineService.saveMedicine(medicine);
        return "redirect:/medicines";
    }

    @GetMapping("/medicines/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return "redirect:/medicines";
    }

    // Customer Management
    @GetMapping("/customers")
    public String customerManagement(Model model) {
        List<UserModel> customers = userService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new UserModel());
        return "customer-management";
    }

    @PostMapping("/customers")
    public String addCustomer(@ModelAttribute UserModel customer) {
        userService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
        return "redirect:/customers";
    }

    // Reports
    @GetMapping("/reports")
    public String reports(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        List<UserModel> customers = userService.getAllCustomers();
        List<PatientModel> patients = patientService.getAllPatients();

        List<LabTestModel> labTests= labTestService.getAllLabTest();
        List<Category> catagory=categoryService.getAllCategories();
        System.out.println(medicines.get(0).getCategory().getId());
        System.out.println(medicines.get(0).getCategory().getName());
        System.out.println(medicines.get(0).getCategory().getDescription());
        System.out.println( "================");
        model.addAttribute("labTests", labTests.size());
        System.out.println( "================");

        System.out.println( "================");
        model.addAttribute("totalPatients", patients.size());

        System.out.println( "================");
        model.addAttribute("medicines", medicines);
        model.addAttribute("customers", customers);
        model.addAttribute("totalMedicines", medicines.size());
        model.addAttribute("totalCustomers", customers.size());
        model.addAttribute("lowStockCount", medicines.stream().filter(m -> m.getQuantity() < 10).count());

        // Add recent data for dashboard
        model.addAttribute("recentMedicines",
                medicines.size() > 5 ? medicines.subList(0, 5) : medicines);
        model.addAttribute("recentCustomers",
                customers.size() > 5 ? customers.subList(0, 5) : customers);
        model.addAttribute("medicineType", enumService.getMedicineType());
        model.addAttribute("catagory",catagory);
       // model.addAttribute("bloodGroup", enumService.getBloodGroups());
        model.addAttribute("bloodGroups", enumService.getBloodGroup());

        return "lab-reports";
    }
}