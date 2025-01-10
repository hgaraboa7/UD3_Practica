package modelo.vo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.vo.Coches;
import modelo.vo.Empleados;
import modelo.vo.ReparacionesPK;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-01-10T19:25:00", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Reparaciones.class)
public class Reparaciones_ { 

    public static volatile SingularAttribute<Reparaciones, ReparacionesPK> reparacionesPK;
    public static volatile SingularAttribute<Reparaciones, Date> fechaf;
    public static volatile SingularAttribute<Reparaciones, Coches> coches;
    public static volatile SingularAttribute<Reparaciones, Empleados> empleados;
    public static volatile SingularAttribute<Reparaciones, Double> importe;

}