package com.medicalstore.controller;

import com.medicalstore.entity.Category;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Customer;
import com.medicalstore.service.CategoryService;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.CustomerService;
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
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        List<Customer> customers = customerService.getAllCustomers();
        List<Category> catagory=categoryService.getAllCategories();
        //List<Medicine>
        System.out.println( "================");

        System.out.println(medicines.get(0).getCategory().getId());
        System.out.println(medicines.get(0).getCategory().getName());
        System.out.println(medicines.get(0).getCategory().getDescription());
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
        model.addAttribute("catagory",catagory);
       // model.addAttribute("mediciteType",medicines.get().getType());

        return "index";
    }

    // Medicine Management
    @GetMapping("/medicines")
    public String medicineManagement(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        model.addAttribute("medicines", medicines);
        model.addAttribute("medicine", new Medicine());
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
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        return "customer-management";
    }

    @PostMapping("/customers")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    // Reports
    @GetMapping("/reports")
    public String reports(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        List<Customer> customers = customerService.getAllCustomers();

        model.addAttribute("medicines", medicines);
        model.addAttribute("customers", customers);
        model.addAttribute("lowStockMedicines",
                medicines.stream().filter(m -> m.getQuantity() < 10).toList());

        return "lab-reports";
    }
}