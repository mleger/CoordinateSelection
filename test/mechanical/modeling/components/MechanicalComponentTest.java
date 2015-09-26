package mechanical.modeling.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MechanicalComponentTest {

    @Test
    public void createMechanicalComponent_allThePublicGetterMethodsShouldWork() {
        ReferenceFrame cs1 = new ReferenceFrame("cs1");
        ReferenceFrame cs2 = new ReferenceFrame("cs2");

        MechanicalComponent c1 = new MechanicalComponent ("c1", cs1, cs2, ComponentType.RIGID_BODY);

        assertEquals("The mechanical edge is not returning its name properly","c1",c1.getName());

        assertEquals("The mechanical edge is not returning its source referene frame properly",cs1,c1.getSourceReferenceFrame());

        assertEquals("The mechanical edge is not returning its target referene frame properly",cs2,c1.getTargetReferenceFrame());

        assertEquals("The mechanical component is not returning the type properly",ComponentType.RIGID_BODY,c1.getType());
    }

    @Test
    public void createMechanicalComponent_allProtectedGetterMethodsShouldWork() {
        ReferenceFrame cs1 = new ReferenceFrame("cs1");
        ReferenceFrame cs2 = new ReferenceFrame("cs2");

        MechanicalComponent c1 = new MechanicalComponent ("c1", cs1, cs2, ComponentType.RIGID_BODY);

        MechanicalEdge rotEdge = c1.getRotationalEdge();

        assertEquals("The mechanical edge is not returning the domain properly",Domain.ROTATIONAL,rotEdge.getDomain());

        assertEquals("The mechanical edge variables were not proprely formatted","phi_c1",rotEdge.getVariables()[0]);
        assertEquals("The mechanical edge variables were not proprely formatted","theta_c1",rotEdge.getVariables()[1]);
        assertEquals("The mechanical edge variables were not proprely formatted","psi_c1",rotEdge.getVariables()[2]);

        assertEquals("The mechanical edge is not reteruning the right number of variables",3,rotEdge.getNumberOfVariables());

        assertEquals("The mechanical edge is not returning the type properly",ComponentType.RIGID_BODY,rotEdge.getType());

        MechanicalEdge transEdge = c1.getTranslationalEdge();

        assertEquals("The mechanical edge is not returning the domain properly",Domain.TRANSLATIONAL,transEdge.getDomain());

        assertEquals("The mechanical edge variables were not proprely formatted","x_c1",transEdge.getVariables()[0]);
        assertEquals("The mechanical edge variables were not proprely formatted","y_c1",transEdge.getVariables()[1]);
        assertEquals("The mechanical edge variables were not proprely formatted","z_c1",transEdge.getVariables()[2]);

        assertEquals("The mechanical edge is not reteruning the right number of variables",3,transEdge.getNumberOfVariables());

        assertEquals("The mechanical edge is not returning the type properly",ComponentType.RIGID_BODY,transEdge.getType());
    }


}
