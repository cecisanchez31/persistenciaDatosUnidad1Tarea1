package com.mycompany.clinicaodontologicapersistencia;

import com.clinicaOdontologica.ui.HistoricaClinicaUI;
import javax.swing.*;

public class ClinicaOdontologicaPersistencia {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HistoricaClinicaUI ui = new HistoricaClinicaUI();
            ui.setVisible(true);
        });
    }
}
