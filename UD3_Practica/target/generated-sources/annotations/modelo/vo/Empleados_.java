package modelo.vo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.vo.Bonificaciones;
import modelo.vo.Reparaciones;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-01-10T19:25:00", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Empleados.class)
public class Empleados_ { 

    public static volatile ListAttribute<Empleados, Reparaciones> reparacionesList;
    public static volatile ListAttribute<Empleados, Bonificaciones> bonificacionesList;
    public static volatile SingularAttribute<Empleados, Double> salario;
    public static volatile SingularAttribute<Empleados, String> nomemp;
    public static volatile SingularAttribute<Empleados, Integer> codemp;

}