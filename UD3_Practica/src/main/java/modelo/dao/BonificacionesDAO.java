/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.vo.Bonificaciones;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author hector.garaboacasas
 */
public class BonificacionesDAO {

    public List<Bonificaciones> getBonificacion(Session session, String mes) {
   
        Query q=session.createNamedQuery("Bonificaciones.findByMes");
        q.setParameter("mes", mes);
        return (ArrayList<Bonificaciones>)q.list();
    
    }
    
}
