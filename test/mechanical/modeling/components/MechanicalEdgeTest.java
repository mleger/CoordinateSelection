package mechanical.modeling.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MechanicalEdgeTest {

    @Test
    public void createAMechanicalEdge_allMethodsShouldWorkCorrectly() {
        ReferenceFrame cs1 = new ReferenceFrame("cs1");
        ReferenceFrame cs2 = new ReferenceFrame("cs2");

        MechanicalEdge m1 = new MechanicalEdge("m1", cs1, cs2, ComponentType.RIGID_BODY, Domain.ROTATIONAL);

        assertEquals("The mechanical edge is not returning the domain properly",Domain.ROTATIONAL,m1.getDomain());

        assertEquals("The mechanical edge variables were not proprely formatted","phi_m1",m1.getVariables()[0]);
        assertEquals("The mechanical edge variables were not proprely formatted","theta_m1",m1.getVariables()[1]);
        assertEquals("The mechanical edge variables were not proprely formatted","psi_m1",m1.getVariables()[2]);

        assertEquals("The mechanical edge is not reteruning the right number of variables",3,m1.getNumberOfVariables());

        MechanicalEdge a1 = new MechanicalEdge("a1", cs1, cs2, ComponentType.ARM, Domain.ROTATIONAL);
        assertEquals("The mechanical edge is not reteruning the right number of variables",0,a1.getNumberOfVariables());

        assertEquals("The mechanical edge is not returning the type properly",ComponentType.RIGID_BODY,m1.getType());
    }
}
