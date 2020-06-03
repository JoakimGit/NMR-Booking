package com.example.demo.repositories;

import com.example.demo.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Lavet af Joakim
@Repository
public class InvoiceRepo {

    @Autowired
    JdbcTemplate template;

    public List<Invoice> fetchAllInvoices() {
        String sql = "SELECT * FROM invoice";
        RowMapper<Invoice> rowMapper = new BeanPropertyRowMapper<>(Invoice.class);
        return template.query(sql, rowMapper);
    }

    public Invoice fetchInvoiceById(int id) {
        String sql = "SELECT * FROM invoice WHERE invoice_id = ?";
        RowMapper<Invoice> rowMapper = new BeanPropertyRowMapper<>(Invoice.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public Invoice fetchInvoiceByReservationId(int id) {
        String sql ="SELECT * FROM invoice WHERE reservation_id=?";
        RowMapper<Invoice> rowMapper = new BeanPropertyRowMapper<>(Invoice.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void createInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoice VALUES (?, ?, ?, ?)";
        template.update(sql, invoice.getInvoice_id(), invoice.getInvoice_type(), invoice.getTotal(), invoice.getReservation_id());
    }

    public void updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoice SET invoice_type=?, total=?, reservation_id=? WHERE invoice_id=?";
        template.update(sql, invoice.getInvoice_type(), invoice.getTotal(), invoice.getReservation_id(), invoice.getInvoice_id());
    }

    public void deleteInvoice(int id) {
        String sql = "DELETE FROM invoice WHERE invoice_id=?";
        template.update(sql, id);
    }
}
