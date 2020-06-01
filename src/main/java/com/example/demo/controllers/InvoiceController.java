package com.example.demo.controllers;

import com.example.demo.models.Invoice;
import com.example.demo.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/faktura/oversigt")
    public String overview(Model model) {
        model.addAttribute("invoices", invoiceService.fetchAllInvoices());
        return "/invoice/overview";
    }

    @GetMapping("/faktura/opret")
    public String createInvoice() {
        return "/invoice/create";
    }

    @PostMapping("/faktura/opret")
    public String createInvoiceNow(@ModelAttribute Invoice invoice) {
        invoiceService.createInvoice(invoice);
        return "redirect:/faktura/oversigt";
    }

    @GetMapping("/faktura/rediger/{id}")
    public String editInvoice(@PathVariable("id") int id, Model model) {
        model.addAttribute("invoice", invoiceService.fetchInvoiceById(id));
        return "/invoice/edit";
    }

    @PostMapping("/faktura/rediger")
    public String editInvoice(@ModelAttribute Invoice invoice) {
        invoiceService.updateInvoice(invoice);
        return "redirect:/faktura/oversigt";
    }

    @GetMapping("/faktura/slet/{id}")
    public String deleteInvoice(@PathVariable("id") int id) {
        invoiceService.deleteInvoice(id);
        return "redirect:/faktura/oversigt";
    }
}
