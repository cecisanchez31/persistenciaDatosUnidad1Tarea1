package com.clinicaOdontologica.ui;

import com.clinicaOdontologica.dao.HistoriaClinicaDAO;
import com.clinicaOdontologica.entities.historiaClinica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoricaClinicaUI extends JFrame {
    private JTextField txtId = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextField txtDireccion = new JTextField();
    private JTextField txtMotivo = new JTextField();

    private JButton btnGuardar = new JButton("Guardar");
    private JButton btnBuscar = new JButton("Buscar");
    private JButton btnActualizar = new JButton("Actualizar");
    private JButton btnEliminar = new JButton("Eliminar");
    private JButton btnListar = new JButton("Listar todos");
    private JButton btnLimpiar = new JButton("Limpiar campos");

    private DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"ID","Nombre","Dirección","Motivo"},0);
    private JTable tabla = new JTable(modeloTabla);

    private HistoriaClinicaDAO dao = new HistoriaClinicaDAO();

    public HistoricaClinicaUI() {
        setTitle("CRUD Historia Clínica");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(7,2));
        panelForm.add(new JLabel("ID (solo para buscar/actualizar/eliminar):"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Dirección:"));
        panelForm.add(txtDireccion);
        panelForm.add(new JLabel("Motivo Consulta:"));
        panelForm.add(txtMotivo);
        panelForm.add(btnGuardar);
        panelForm.add(btnBuscar);
        panelForm.add(btnActualizar);
        panelForm.add(btnEliminar);
        panelForm.add(btnListar);
        panelForm.add(btnLimpiar);

        add(panelForm, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Eventos CRUD
        btnGuardar.addActionListener(e -> guardar());
        btnBuscar.addActionListener(e -> buscar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Al seleccionar fila en la tabla, cargar datos en los campos
        tabla.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int fila = tabla.getSelectedRow();
                txtId.setText(modeloTabla.getValueAt(fila,0).toString());
                txtNombre.setText(modeloTabla.getValueAt(fila,1).toString());
                txtDireccion.setText(modeloTabla.getValueAt(fila,2).toString());
                txtMotivo.setText(modeloTabla.getValueAt(fila,3).toString());
            }
        });
    }

    private void guardar() {
        historiaClinica hc = new historiaClinica(txtNombre.getText(), txtDireccion.getText(), txtMotivo.getText());
        dao.guardar(hc);
        JOptionPane.showMessageDialog(this,"Guardado exitoso");
        listar();
        limpiarCampos();
    }

    private void buscar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Ingrese un ID");
            return;
        }
        historiaClinica hc = dao.buscar(Long.parseLong(txtId.getText()));
        if (hc != null) {
            txtNombre.setText(hc.getNombre());
            txtDireccion.setText(hc.getDireccion());
            txtMotivo.setText(hc.getMotivoConsulta());
        } else {
            JOptionPane.showMessageDialog(this,"No encontrado");
        }
    }

    private void actualizar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Seleccione un registro de la tabla o ingrese un ID");
            return;
        }
        historiaClinica hc = dao.buscar(Long.parseLong(txtId.getText()));
        if (hc != null) {
            hc.setNombre(txtNombre.getText());
            hc.setDireccion(txtDireccion.getText());
            hc.setMotivoConsulta(txtMotivo.getText());
            dao.actualizar(hc);
            JOptionPane.showMessageDialog(this,"Actualizado exitoso");
        } else {
            JOptionPane.showMessageDialog(this,"No encontrado para actualizar");
        }
        listar();
        limpiarCampos();
    }

    private void eliminar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Seleccione un registro de la tabla o ingrese un ID");
            return;
        }
        dao.eliminar(Long.parseLong(txtId.getText()));
        JOptionPane.showMessageDialog(this,"Eliminado exitoso");
        listar();
        limpiarCampos();
    }

    private void listar() {
        modeloTabla.setRowCount(0);
        for (historiaClinica hc : dao.listar()) {
            modeloTabla.addRow(new Object[]{hc.getId(), hc.getNombre(), hc.getDireccion(), hc.getMotivoConsulta()});
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtMotivo.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HistoricaClinicaUI().setVisible(true));
    }
}
