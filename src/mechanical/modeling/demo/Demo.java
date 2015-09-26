package mechanical.modeling.demo;

import java.util.Set;

import mechanical.modeling.algorithms.OptimalModelingVariables;
import mechanical.modeling.components.ComponentType;
import mechanical.modeling.components.MechanicalComponent;
import mechanical.modeling.components.MechanicalSystem;
import mechanical.modeling.components.ReferenceFrame;

/**
 * This class contains the main class, which runs a series of Demos
 * that demonstrate how to use the code base.
 * 
 * @author Mathieu LŽger
 * @since Mar 23, 2014
 */
public final class Demo {

    public static void main(String[] args) {
        String newLine = System.getProperty("line.separator");

        /*
         * This project is an example of how the GraphTheoryAlgorthms
         * project can be used by other projects that require graph theoretical
         * algorithms to analyze the topology of certain systems.
         * 
         * Unlike the GraphTheoryAlgorthms project, this project is a
         * simple implementation that has only limited extensibility in mind.
         * 
         * Mechanical systems are defined here as a series of mechanical
         * components (such as joints, rigid bodies, force/torque drivers, etc.) which
         * are connected to body-fixed reference frames. Each component defines the
         * type of motion that is allowed between the two reference frames to which it connects.
         * 
         * An example mechanical system (illustrated in the SpatialSerialManipulator.pdf
         * file found in the resources folder of this project) is defined below.
         */
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
        MechanicalComponent vp16 = new MechanicalComponent("vp16",cs3, cs7, ComponentType.REVOLUTE_PRISMATIC_JOINT);

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
        spatialSerialManipulator.addComponent(vp16);

        System.out.println("The Spacial Serial Manipulator's mechanical system topology is: " + newLine +
                spatialSerialManipulator.toString() + newLine);

        /*
         * For each mechanical system, two graphs are created. One graph representing
         * the translational motions of the system, and another representing
         * the rotational motions of the system. Having two graphs enables
         * the optimal variable selection algorithm to select the modeling variables from
         * different mechanical components in each domain (translational and rotational).
         * 
         * The current implementation of the optimal modeling variable algorithm
         * assigns the number of variables associated to the given component
         * in the given domain as the weight of that components edge (for example,
         * a revolute joint component has one variable in the rotational domain and
         * none in the translational domain). The ShortestPathToNode algorithm in
         * the GraphTheoryAlgorithms project is then used to find a tree that
         * results in the fewest amount of variables relating each reference frame
         * to the ground node in each domain.
         * 
         * Once the trees are found, the modeling variable names of each edge in both
         * the rotational and translational trees are found and returned as a set of
         * strings.
         */
        Set<String> optimalManipulatorVariables = OptimalModelingVariables.findVariables(spatialSerialManipulator);

        System.out.println("The Spacial Serial Manipulator's optimal modeling variables are: " + newLine +
                optimalManipulatorVariables.toString() + newLine);

        /*
         * Future Improvements:
         * 
         * The current project focuses on finding a mechanical system's optimal
         * modeling coordinates. However, this is only the first step in the derivation
         * of a mechanical system's modeling equations. In the future, the mechanical
         * components could be extended to contain equations defining the allowable
         * motions, forces and torques between its two reference frames. This information
         * could then be used by graph theoretical algorithms to derive a mechanical
         * system's modeling equations based on the optimal modeling coordinates
         * found by the algorithm in this project.
         */
    }
}
