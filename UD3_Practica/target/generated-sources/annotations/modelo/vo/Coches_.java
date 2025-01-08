package modelo.vo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.vo.Clientes;
import modelo.vo.Reparaciones;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-01-08T20:51:19", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Coches.class)
public class Coches_ { 

    public static volatile SingularAttribute<Coches, String> marca;
    public static volatile SingularAttribute<Coches, Clientes> codcli;
    public static volatile ListAttribute<Coches, Reparaciones> reparacionesList;
    public static volatile SingularAttribute<Coches, String> matricula;
    public static volatile SingularAttribute<Coches, String> modelo;

}