package mechanical.modeling.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import graph.components.BasicWeightedGraph;
import graph.elements.WeightedGraph;

import java.util.LinkedHashSet;
import java.util.Set;

import mechanical.modeling.components.ComponentType;
import mechanical.modeling.components.Domain;
import mechanical.modeling.components.MechanicalComponent;
import mechanical.modeling.components.MechanicalEdge;
import mechanical.modeling.components.MechanicalSystem;
import mechanical.modeling.components.ReferenceFrame;

import org.junit.Test;

public class OptimalModelingVariablesTest {

    @Test
    public void findingTheOptimalModelingVariablesOfAnExampleSystem_theRightSetOfVariablesShouldBeReturned() {
        ReferenceFrame ground = new ReferenceFrame("Ground");
        ReferenceFrame cs1 = new ReferenceFrame("CS1");
        ReferenceFrame cs2 = new ReferenceFrame("CS2");
        ReferenceFrame cs3 = new ReferenceFrame("CS3");
        ReferenceFrame cs4 = new ReferenceFrame("CS4");
        ReferenceFrame cs5 = new ReferenceFrame("CS5");
        ReferenceFrame cs6 = new ReferenceFrame("CS6");
        ReferenceFrame cs7 = new ReferenceFrame("CS7");
        ReferenceFrame cs8 = new ReferenceFrame("CS8");
        ReferenceFrame cs9 = new ReferenceFrame("CS9");
        ReferenceFrame cs10 = new ReferenceFrame("CS10");
        ReferenceFrame cs11 = new ReferenceFrame("CS11");

        MechanicalComponent m1 = new MechanicalComponent("m1", ground, cs2, ComponentType.RIGID_BODY);
        MechanicalComponent m2 = new MechanicalComponent("m2", ground, cs5, ComponentType.RIGID_BODY);
        MechanicalComponent m3 = new MechanicalComponent("m3", ground, cs8, ComponentType.RIGID_BODY);
        MechanicalComponent m4 = new MechanicalComponent("m4", ground, cs11, ComponentType.RIGID_BODY);
        MechanicalComponent r5 = new MechanicalComponent("r5",cs2, cs1, ComponentType.ARM);
        MechanicalComponent r6 = new MechanicalComponent("r6",cs2, cs3, ComponentType.ARM);
        MechanicalComponent r7 = new MechanicalComponent("r7",cs5, cs4, ComponentType.ARM);
        MechanicalComponent r8 = new MechanicalComponent("r8",cs5, cs6, ComponentType.ARM);
        MechanicalComponent r9 = new MechanicalComponent("r9",cs8, cs7, ComponentType.ARM);
        MechanicalComponent r10 = new MechanicalComponent("r10",cs8, cs9, ComponentType.ARM);
        MechanicalComponent r11 = new MechanicalComponent("r11",cs11, cs10, ComponentType.ARM);
        MechanicalComponent h12 = new MechanicalComponent("h12", ground, cs1, ComponentType.REVOLUTE_JOINT);
        MechanicalComponent h13 = new MechanicalComponent("h13",cs3, cs4, ComponentType.REVOLUTE_JOINT);
        MechanicalComponent h14 = new MechanicalComponent("h14",cs6, cs7, ComponentType.REVOLUTE_JOINT);
        MechanicalComponent b15 = new MechanicalComponent("b15",cs9, cs10, ComponentType.SPHERICAL_JOINT);

        MechanicalSystem spatialSerialManipulator = new MechanicalSystem(ground);
        spatialSerialManipulator.addComponent(m1);
        spatialSerialManipulator.addComponent(m2);
        spatialSerialManipulator.addComponent(m3);
        spatialSerialManipulator.addComponent(m4);
        spatialSerialManipulator.addComponent(r5);
        spatialSerialManipulator.addComponent(r6);
        spatialSerialManipulator.addComponent(r7);
        spatialSerialManipulator.addComponent(r8);
        spatialSerialManipulator.addComponent(r9);
        spatialSerialManipulator.addComponent(r10);
        spatialSerialManipulator.addComponent(r11);
        spatialSerialManipulator.addComponent(h12);
        spatialSerialManipulator.addComponent(h13);
        spatialSerialManipulator.addComponent(h14);
        spatialSerialManipulator.addComponent(b15);

        Set<String> optimalVariables = OptimalModelingVariables.findVariables(spatialSerialManipulator);
        assertEquals("The wrong number of modeling variables were found", 6 ,optimalVariables.size());
        assertTrue("The wrong modeling variable was found", optimalVariables.contains("phi_h12"));
        assertTrue("The wrong modeling variable was found", optimalVariables.contains("phi_h13"));
        assertTrue("The wrong modeling variable was found", optimalVariables.contains("phi_h14"));
        assertTrue("The wrong modeling variable was found", optimalVariables.contains("phi_m4"));
        assertTrue("The wrong modeling variable was found", optimalVariables.contains("theta_m4"));
        assertTrue("The wrong modeling variable was found", optimalVariables.contains("psi_m4"));
    }

    @Test
    public void assignTheTreeWeightsAndFindTheOptimalTree_theRightTreeShouldBeReturned() {
        ReferenceFrame nO = new ReferenceFrame("O");
        ReferenceFrame nA = new ReferenceFrame("A");
        ReferenceFrame nB = new ReferenceFrame("B");
        ReferenceFrame nC = new ReferenceFrame("C");
        ReferenceFrame nD = new ReferenceFrame("D");

        MechanicalEdge m1 = new MechanicalEdge("m1", nO, nA, ComponentType.RIGID_BODY, Domain.ROTATIONAL);
        MechanicalEdge m2 = new MechanicalEdge("m2", nO, nB, ComponentType.RIGID_BODY, Domain.ROTATIONAL);
        MechanicalEdge r3 = new MechanicalEdge("r3", nA, nC, ComponentType.ARM, Domain.ROTATIONAL);
        MechanicalEdge r4 = new MechanicalEdge("r4", nB, nD, ComponentType.ARM, Domain.ROTATIONAL);
        MechanicalEdge h5 = new MechanicalEdge("h5", nO, nA, ComponentType.REVOLUTE_JOINT, Domain.ROTATIONAL);
        MechanicalEdge h6 = new MechanicalEdge("h6", nC, nD, ComponentType.REVOLUTE_JOINT, Domain.ROTATIONAL);

        WeightedGraph<ReferenceFrame, MechanicalEdge> graph = new BasicWeightedGraph<ReferenceFrame, MechanicalEdge>();
        graph.addEdge(m1);
        graph.addEdge(m2);
        graph.addEdge(r3);
        graph.addEdge(r4);
        graph.addEdge(h5);
        graph.addEdge(h6);

        Set<MechanicalEdge> tree = OptimalModelingVariables.getTree(graph, nO);
        assertEquals("The wrong number of tree edges were found", 4, tree.size());
        assertTrue("The tree does not contain an expected edge", tree.contains(r3));
        assertTrue("The tree does not contain an expected edge", tree.contains(r4));
        assertTrue("The tree does not contain an expected edge", tree.contains(h5));
        assertTrue("The tree does not contain an expected edge", tree.contains(h6));
    }

    @Test
    public void extractTheVariableNamesFromATree_theRightNamesShouldBeReturned() {
        ReferenceFrame nO = new ReferenceFrame("O");
        ReferenceFrame nA = new ReferenceFrame("A");
        ReferenceFrame nB = new ReferenceFrame("B");
        ReferenceFrame nC = new ReferenceFrame("C");
        ReferenceFrame nD = new ReferenceFrame("D");

        Set<MechanicalEdge> tree = new LinkedHashSet<MechanicalEdge>();
        tree.add(new MechanicalEdge("r3", nA, nC, ComponentType.ARM, Domain.ROTATIONAL));
        tree.add(new MechanicalEdge("r4", nB, nD, ComponentType.ARM, Domain.ROTATIONAL));
        tree.add(new MechanicalEdge("h5", nO, nA, ComponentType.REVOLUTE_JOINT, Domain.ROTATIONAL));
        tree.add(new MechanicalEdge("h6", nC, nD, ComponentType.REVOLUTE_JOINT, Domain.ROTATIONAL));

        Set<String> variables = OptimalModelingVariables.getTreeEdgeVariables(tree);
        assertEquals("The wrong number of modeling variables were found", 2 ,variables.size());
        assertTrue("The wrong modeling variable was found", variables.contains("phi_h5"));
        assertTrue("The wrong modeling variable was found", variables.contains("phi_h6"));

    }

    @Test(expected=IllegalArgumentException.class)
    public void findTheOptimalVarialbesOfAnEmptySystem_shouldReturnAnException() {
        ReferenceFrame ground = new ReferenceFrame("Ground");
        MechanicalSystem spatialSerialManipulator = new MechanicalSystem(ground);

        Set<String> optimalVariables = OptimalModelingVariables.findVariables(spatialSerialManipulator);
        fail();
        assertEquals("The wrong number of modeling variables were found", 0 ,optimalVariables.size());
    }
}
