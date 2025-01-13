/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.vo.Bonificaciones;
import modelo.vo.BonificacionesPK;
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

    public void insertar(Session session, int codemp, Date fechaf, double num) {
   
        
        
        String mes=String.valueOf(fechaf.getMonth()+1);
        
       if (num >= 1000.00) {
        
        Bonificaciones bon = session.get(Bonificaciones.class, new BonificacionesPK(codemp, mes));
        
        if (bon == null) {
            
            bon = new Bonificaciones(codemp, mes, num * 0.05);
            session.save(bon);
        } else {
            
            bon.setImportebonificado(num * 0.05); 
            session.update(bon);
        }
    }
    }
    
}
