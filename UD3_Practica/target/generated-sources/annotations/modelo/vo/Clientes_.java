package modelo.vo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.vo.Coches;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-01-08T20:51:19", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile SingularAttribute<Clientes, String> nomcli;
    public static volatile SingularAttribute<Clientes, Integer> codcli;
    public static volatile SingularAttribute<Clientes, String> tfno;
    public static volatile SingularAttribute<Clientes, String> direccion;
    public static volatile ListAttribute<Clientes, Coches> cochesList;
    public static volatile SingularAttribute<Clientes, String> email;

}