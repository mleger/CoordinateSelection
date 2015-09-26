package mechanical.modeling.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import graph.elements.WeightedGraph;

import java.util.Set;

import org.junit.Test;

public class MechanicalSystemTest {

    @Test
    public void createSystemAndAddTwoComponents_bothComponentsShouldBeInTheSystem() {
        ReferenceFrame ground = new ReferenceFrame("Ground");
        ReferenceFrame cs1 = new ReferenceFrame("CS1");
        ReferenceFrame cs2 = new ReferenceFrame("CS2");

        MechanicalComponent m1 = new MechanicalComponent("m1", ground, cs1, ComponentType.RIGID_BODY);
        MechanicalComponent m2 = new MechanicalComponent("m2", ground, cs2, ComponentType.RIGID_BODY);

        MechanicalSystem mSystem = new MechanicalSystem(ground);
        boolean m1Added = mSystem.addComponent(m1);
        boolean m2Added = mSystem.addComponent(m2);

        Set<MechanicalComponent> components = mSystem.getAllComponents();
        assertEquals("One of the mechanical system does not contain the right number of componets",2 ,components.size());
        assertTrue("One of the mechanical system's components was not added properly",m1Added);
        assertTrue("One of the mechanical system's components was not added properly",components.contains(m1));
        assertTrue("One of the mechanical system's components was not added properly",m2Added);
        assertTrue("One of the mechanical system's components was not added properly",components.contains(m2));
    }

    @Test
    public void createSystemOfTwoComponentsAndRemoveAComponent_theComponentShouldBeProperlyRemoved() {
        ReferenceFrame ground = new ReferenceFrame("Ground");
        ReferenceFrame cs1 = new ReferenceFrame("CS1");
        ReferenceFrame cs2 = new ReferenceFrame("CS2");

        MechanicalComponent m1 = new MechanicalComponent("m1", ground, cs1, ComponentType.RIGID_BODY);
        MechanicalComponent m2 = new MechanicalComponent("m2", ground, cs2, ComponentType.RIGID_BODY);

        MechanicalSystem mSystem = new MechanicalSystem(ground);
        mSystem.addComponent(m1);
        mSystem.addComponent(m2);
        boolean m2Removed = mSystem.removeComponent(m2);

        Set<MechanicalComponent> components = mSystem.getAllComponents();
        assertEquals("One of the mechanical system does not contain the right number of componets",1 ,components.size());
        assertTrue("One of the mechanical system's components was not removed properly",m2Removed);
        assertTrue("One of the mechanical system's components was not removed properly",!components.contains(m2));
    }

    @Test
    public void createSystemOfTwoComponentsAndGetTranslationalGraph_theExpectedGraphsShouldBeReturned() {
        ReferenceFrame ground = new ReferenceFrame("Ground");
        ReferenceFrame cs1 = new ReferenceFrame("CS1");
        ReferenceFrame cs2 = new ReferenceFrame("CS2");

        MechanicalComponent m1 = new MechanicalComponent("m1", ground, cs1, ComponentType.RIGID_BODY);
        MechanicalComponent m2 = new MechanicalComponent("m2", ground, cs2, ComponentType.RIGID_BODY);

        MechanicalSystem mSystem = new MechanicalSystem(ground);
        mSystem.addComponent(m1);
        mSystem.addComponent(m2);

        WeightedGraph<ReferenceFrame,MechanicalEdge> transGraph = mSystem.getTranslationalGraph();

        Set<ReferenceFrame> graphNodes = transGraph.getAllNodes();
        assertTrue("One of the mechanical system's translational graph does not contain the right nodes", graphNodes.contains(ground));
        assertTrue("One of the mechanical system's translational graph does not contain the right nodes", graphNodes.contains(cs1));
        assertTrue("One of the mechanical system's translational graph does not contain the right nodes", graphNodes.contains(cs2));

        Set<MechanicalEdge> graphEdges = transGraph.getAllEdges();
        for(MechanicalEdge edge : graphEdges) {
            assertEquals("An edge in the graph is not related to the proper domain", edge.getDomain(), Domain.TRANSLATIONAL);
            assertEquals("An edge in the graph is not related to the proper domain", edge.getType(), ComponentType.RIGID_BODY);
        }

    }

    @Test
    public void createSystemOfTwoComponentsAndGetRotationalGraph_theExpectedGraphsShouldBeReturned() {
        ReferenceFrame ground = new ReferenceFrame("Ground");
        ReferenceFrame cs1 = new ReferenceFrame("CS1");
        ReferenceFrame cs2 = new ReferenceFrame("CS2");

        MechanicalComponent m1 = new MechanicalComponent("m1", ground, cs1, ComponentType.RIGID_BODY);
        MechanicalComponent m2 = new MechanicalComponent("m2", ground, cs2, ComponentType.RIGID_BODY);

        MechanicalSystem mSystem = new MechanicalSystem(ground);
        mSystem.addComponent(m1);
        mSystem.addComponent(m2);

        WeightedGraph<ReferenceFrame,MechanicalEdge> rotGraph = mSystem.getRotationalGraph();

        Set<ReferenceFrame> graphNodes = rotGraph.getAllNodes();
        assertTrue("One of the mechanical system's translational graph does not contain the right nodes", graphNodes.contains(ground));
        assertTrue("One of the mechanical system's translational graph does not contain the right nodes", graphNodes.contains(cs1));
        assertTrue("One of the mechanical system's translational graph does not contain the right nodes", graphNodes.contains(cs2));

        Set<MechanicalEdge> graphEdges = rotGraph.getAllEdges();
        for(MechanicalEdge edge : graphEdges) {
            assertEquals("An edge in the graph is not related to the proper domain", edge.getDomain(),Domain.ROTATIONAL);
            assertEquals("An edge in the graph is not related to the proper domain", edge.getType(),ComponentType.RIGID_BODY);
        }

    }

}
