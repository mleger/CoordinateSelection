package mechanical.modeling.components;

import graph.components.BasicNode;

/**
 * This class defines a body-fixed reference frame in a mechanical system.
 * It is also a type of node that can be use by graphs and graph
 * theoretical algorithms.
 * 
 * @author Mathieu LŽger
 * @since Mar 23, 2014
 *
 */
public class ReferenceFrame extends BasicNode {

    /**
     * Creates a reference frame used in mechanical systems
     * 
     * @param name is the name of the reference frame
     */
    public ReferenceFrame(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "ReferenceFrame[" + getName() + "]";
    }

}
