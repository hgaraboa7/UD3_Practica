/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.factory.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.dao.BonificacionesDAO;
import modelo.dao.ClienteDAO;
import modelo.dao.CocheDAO;
import modelo.dao.EmpleadoDAO;
import modelo.dao.ReparacionDAO;
import modelo.vo.Bonificaciones;
import modelo.vo.Clientes;
import modelo.vo.Coches;
import modelo.vo.Empleados;
import modelo.vo.Reparaciones;
import modelo.vo.ReparacionesPK;
import org.hibernate.Session;
import vista.Principal;

/**
 *
 * @author acceso a datos
 */
public class controladorPrincipal {

    public static Session session;
//declara los objetos DAO
    public static ClienteDAO cliente;
    public static CocheDAO coche;
    public static ReparacionDAO reparacion;
    public static EmpleadoDAO empleado;

    public static BonificacionesDAO bonificacion;

    public static Principal ventana = new Principal();

    //cambiar lo de las tablas
    public static String[] cabeceraCoches = {"Matricula", "Modelo", "Marca"};
    public static DefaultTableModel modeloTablaCocche = new DefaultTableModel(cabeceraCoches, 0);

    public static String[] cabeceraEmpleados = {"Nombre", "Salario"};
    public static DefaultTableModel modeloTablaEmpleado = new DefaultTableModel(cabeceraEmpleados, 0);

    public static DefaultComboBoxModel modelocomboEmpleados = new DefaultComboBoxModel();

    public static void iniciar() {
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        ventana.getTablaCoches().setModel(modeloTablaCocche);
        ventana.getTablaEmpleados().setModel(modeloTablaEmpleado);

        ventana.getCbEmpleados().setModel(modelocomboEmpleados);

    }

    public static void iniciaSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        //crear los objetos DAO     
        cliente = HibernateUtil.getClienteDAO();
        coche = HibernateUtil.getCocheDAO();
        reparacion = HibernateUtil.getReparacionDAO();
        empleado = HibernateUtil.getEmpleadoDAO();
        bonificacion = HibernateUtil.getBonificacionesDAO();
    }

    public static void cerrarSession() {
        session.close();
    }

    public static void insertarCliente() {

        comprobarCamposCliente();
        try {

            HibernateUtil.beginTx(session);

            cliente.insertar(session, ventana.getTxtCodCli().getText(),
                    ventana.getTxtNomCli().getText(),
                    ventana.getTxtDireccion().getText(),
                    ventana.getTxtEmail().getText(),
                    ventana.getTxtTfno().getText());
            HibernateUtil.commitTx(session);
            JOptionPane.showMessageDialog(null, "cliente insertado");
        } catch (NoResultException nre) {
            JOptionPane.showMessageDialog(null, "No existe el cliente");
            HibernateUtil.rollbackTx(session);
            return;

        } catch (Exception ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;
        }

    }

    public static void comprobarCliente() {
        try {
            Clientes cli;
            HibernateUtil.beginTx(session);
            cli = cliente.comprobar(session, ventana.getTxtNomCli().getText());
            System.out.println("existe");

            ventana.getTxtCodCli().setText(String.valueOf(cli.getCodcli()));
            ventana.getTxtNomCli().setText(cli.getNomcli());
            ventana.getTxtDireccion().setText(cli.getDireccion());
            ventana.getTxtEmail().setText(cli.getEmail());
            ventana.getTxtTfno().setText(cli.getTfno());

            ventana.getBtnAltaCli().setEnabled(false);
            ventana.getBtnBajaCli().setEnabled(true);
            ventana.getBtnModifCli().setEnabled(true);
            HibernateUtil.commitTx(session);
        } catch (NoResultException nre) {
            System.out.println("no existe");
            ventana.getBtnAltaCli().setEnabled(true);
            ventana.getBtnBajaCli().setEnabled(false);
            ventana.getBtnModifCli().setEnabled(false);
            return;

        } catch (Exception ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;
        }
    }

    /////
    public static Clientes obtenerCliente() {
        Clientes cli;
        HibernateUtil.beginTx(session);
        cli = cliente.comprobar(session, ventana.getTxtNomCli().getText());
        HibernateUtil.commitTx(session);

        return cli;
    }
////

    public static void modificarCliente() {
        comprobarCamposCliente();

        try {

            Clientes cli = cliente.comprobar(session, ventana.getTxtNomCli().getText());
            HibernateUtil.beginTx(session);
            cliente.modificar(session, cli,
                    ventana.getTxtDireccion().getText(),
                    ventana.getTxtEmail().getText(),
                    ventana.getTxtTfno().getText());

            HibernateUtil.commitTx(session);
        } catch (Exception ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;
        }

    }

    public static void comprobarCamposCliente() {
        if (ventana.getTxtCodCli().getText().isBlank() || ventana.getTxtDireccion().getText().isBlank()
                || ventana.getTxtEmail().getText().isBlank() || ventana.getTxtNomCli().getText().isBlank()
                || ventana.getTxtTfno().getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Faltan datos cliente");
            return;
        }
    }

    public static void mostrarCoches() {

        try {
            List<Coches> listaCoches = new ArrayList();
            HibernateUtil.beginTx(session);

            listaCoches = coche.getCoches(session, ventana.getTxtCodCli().getText());
            modeloTablaCocche.setRowCount(0);
            for (Coches co : listaCoches) {
                Object[] fila = {co.getMatricula(), co.getModelo(), co.getMarca()};
                modeloTablaCocche.addRow(fila);
            }

            ventana.getTablaCoches().setModel(modeloTablaCocche);

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTablaCocche);
            ventana.getTablaCoches().setRowSorter(sorter);

            HibernateUtil.commitTx(session);
        } catch (Exception ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;
        }

    }

    public static void altaCoche() {

        if (ventana.getTxtMatricula().getText().isBlank() || ventana.getTxtModelo().getText().isBlank()
                || ventana.getTxtMarca().getText().isBlank() || ventana.getTxtPropietario().getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Faltan datos coche");
            return;
        }
        try {
            Coches co;

            HibernateUtil.beginTx(session);

            co = new Coches(ventana.getTxtMatricula().getText(),
                    ventana.getTxtMarca().getText(),
                    ventana.getTxtModelo().getText(),
                    cliente.comprobar(session, ventana.getTxtPropietario().getText()));

            coche.insertar(session, co);

            JOptionPane.showMessageDialog(null, "coche insertado");
            HibernateUtil.commitTx(session);
        } catch (NoResultException nre) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, nre);

            JOptionPane.showMessageDialog(null, "el cliente no existe");
            HibernateUtil.rollbackTx(session);
            return;
        } catch (Exception ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;
        }

    }

    public static void bajaCoche() {

    }

    public static void comprobarCoche() {

        try {
            Coches co;
            HibernateUtil.beginTx(session);

            co = coche.getCoche(session, ventana.getTxtMatricula().getText());

            ventana.getBtnInsertCoche().setEnabled(false);
            ventana.getBtnBajaCoche().setEnabled(true);
            HibernateUtil.commitTx(session);
        } catch (NoResultException nre) {
            System.out.println("no existe");

            ventana.getBtnInsertCoche().setEnabled(true);
            ventana.getBtnBajaCoche().setEnabled(false);

            return;

        } catch (Exception ex) {

            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;
        }

    }

    public static void entradaReparacion() {
        try {
            ReparacionesPK pkReparacion;
            Reparaciones repara;
            Empleados emp = null;
            

            if (ventana.getTxtRepMatricula().getText().isBlank()
                    || ventana.getTxtRepFechaEnt().getText().isBlank()
                    || ventana.getTxtImporte().getText().isBlank()) {

                JOptionPane.showMessageDialog(null, "Faltan datos para crear Reparacion");
                return;
            }
            HibernateUtil.beginTx(session);

           
            emp = (Empleados) ventana.getCbEmpleados().getSelectedItem();

            if (reparacion.comprobarNumeroReparaciones(session, emp) >= 3) {

                JOptionPane.showMessageDialog(null, "el empleado ya ha alcanzado el numero de reparaciones activas maxima");
                return;
            }
            

            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime fechaEntrada = LocalDateTime.parse(ventana.getTxtRepFechaEnt().getText(), formateador);

            pkReparacion = new ReparacionesPK(emp.getCodemp(), ventana.getTxtRepMatricula().getText(),
                    Date.from(fechaEntrada.toInstant(ZoneOffset.UTC)));

            if (ventana.getTxtRepFechaSal().getText().isBlank()) {

                repara = new Reparaciones(pkReparacion, Double.parseDouble(ventana.getTxtImporte().getText()), null);

                reparacion.insertar(session, repara);

            } else {

                LocalDateTime fechaSalida = LocalDateTime.parse(ventana.getTxtRepFechaSal().getText(), formateador);

                repara = new Reparaciones(pkReparacion, Double.parseDouble(ventana.getTxtImporte().getText()),
                        Date.from(fechaSalida.toInstant(ZoneOffset.UTC)));

                reparacion.insertar(session, repara);
            }

            HibernateUtil.commitTx(session);

        } catch (Exception ex) {

            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;

        }

    }

    public static void cargarTablaBonificaciones() {

        try {
            List<Bonificaciones> listaBonificaciones = new ArrayList();
            HibernateUtil.beginTx(session);

            listaBonificaciones = bonificacion.getBonificacion(session, String.valueOf(ventana.getCbMesEmpleados().getSelectedIndex() + 1));
            modeloTablaEmpleado.setRowCount(0);
            for (Bonificaciones bon : listaBonificaciones) {
                Object[] fila = {bon.getEmpleados().getNomemp(), bon.getEmpleados().getSalario() + bon.getImportebonificado()};
                modeloTablaEmpleado.addRow(fila);
            }

            ventana.getTablaEmpleados().setModel(modeloTablaEmpleado);

            HibernateUtil.commitTx(session);
        } catch (Exception ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;
        }

    }

    public static void comprobarReparacion() {

        Reparaciones rep;
        Coches co = null;

        try {
            HibernateUtil.beginTx(session);

             try {
                if ((co = coche.getCoche(session, ventana.getTxtRepMatricula().getText())) == null);
            } catch (NoResultException nre) {
                JOptionPane.showMessageDialog(null, "No existe el coche");
                return;
            }
            
            if ((rep = reparacion.getReparacion(session, ventana.getTxtRepMatricula().getText())) != null) {

                JOptionPane.showMessageDialog(null, "el coche ya tiene una reparacion activa");
                ////joptionpane confirmation de si desea modificar el estado de la reparacion activa
                return;

            }

            JOptionPane.showMessageDialog(null, "el coche esta disponible para reparar");

            HibernateUtil.commitTx(session);

        } catch (Exception ex) {

            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;

        }

    }

    public static void llenarComboEmpleados() {

        modelocomboEmpleados.removeAllElements();

        HibernateUtil.beginTx(session);

        try {

            empleado.cargarCombo(session, modelocomboEmpleados);

            HibernateUtil.commitTx(session);

        } catch (Exception ex) {

            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            HibernateUtil.rollbackTx(session);
            return;

        }
    }

}
