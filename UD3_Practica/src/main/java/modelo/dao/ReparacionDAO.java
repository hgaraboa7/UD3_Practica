/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import controlador.controladorPrincipal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.vo.Empleados;
import modelo.vo.Reparaciones;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author hecto
 */
public class ReparacionDAO {
    
    public void insertar(Session session, Reparaciones repara){
        try{
        session.save(repara);
        System.out.println("insertada reparacion");
        }catch(Exception ex){
            Logger.getLogger(ReparacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
    }

    public Reparaciones getReparacion(Session session, String matricula) {
 
        Reparaciones rep=null;
        
        Query q=session.createNamedQuery("Reparaciones.findByMatricula");
        q.setParameter("matricula", matricula);
        
        List<Reparaciones> reparaciones=q.list();
        
         
        for (Reparaciones r : reparaciones) {
            if (r.getFechaf()==null){
               return rep=r; 
            }
        }
         return rep;
        
        
    }

    public int comprobarNumeroReparaciones(Session session, Empleados emp) {
  
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
        
        
    }
    
}
