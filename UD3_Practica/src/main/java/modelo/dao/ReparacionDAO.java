/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import controlador.controladorPrincipal;
import static controlador.controladorPrincipal.ventana;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import modelo.vo.Empleados;
import modelo.vo.Reparaciones;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author hecto
 */
public class ReparacionDAO {

    public void insertar(Session session, Reparaciones repara) {

        session.save(repara);
        System.out.println("insertada reparacion");

    }

    public Reparaciones getReparacion(Session session, String matricula) {
        Reparaciones rep = null;

        Query q = session.createQuery("Select r From Reparaciones r WHERE r.reparacionesPK.matricula = :matricula AND r.fechaf is null");
        q.setParameter("matricula", matricula);
        rep = (Reparaciones) q.uniqueResult();

        return rep;

//        Reparaciones rep=null;
//        
//        Query q=session.createNamedQuery("Reparaciones.findByMatricula");
//        q.setParameter("matricula", matricula);
//        
//        List<Reparaciones> reparaciones=q.list();
//        
//         
//        for (Reparaciones r : reparaciones) {
//            if (r.getFechaf()==null){
//               return rep=r; 
//            }
//        }
//         return rep;
//        
    }

    public long comprobarNumeroReparaciones(Session session, Empleados emp) {

        Query q = session.createQuery("Select count(*) From Reparaciones r WHERE r.reparacionesPK.codemp = :codemp AND r.fechaf is null");
        q.setParameter("codemp", emp.getCodemp());
        return (long) q.uniqueResult();

        /*
        Query q=session.createNamedQuery("Reparaciones.findByCodemp");
        q.setParameter("codemp", emp.getCodemp());
        
        List<Reparaciones> reparaciones=q.list();
        
         int numRep = 0;
        for (Reparaciones r : reparaciones) {
            if (r.getFechaf() == null) {
                numRep++;
            }
        }
         return numRep;
        
         */
    }

    public void salida(Session session, Reparaciones rep, String importe, String fechaf) {

        rep.setImporte(Double.valueOf(importe));

        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaSalida = LocalDateTime.parse(fechaf, formateador);

        rep.setFechaf(Date.from(fechaSalida.toInstant(ZoneOffset.UTC)));

        session.update(rep);

    }

}
