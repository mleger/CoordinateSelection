package mechanical.modeling.components;

import graph.components.NamedWeightedEdge;

/**
 * This class implements a graph theoretical edge associated to a
 * mechanical component's properties for a given domain (translational
 * or rotational).
 * 
 * @author Mathieu LŽger
 * @since Mar 23, 2014
 *
 */
public class MechanicalEdge extends NamedWeightedEdge<ReferenceFrame> {

    private final ComponentType type;
    private final Domain domain;
    private final String[] variables;

    /**
     * Creates a graph theoretical edge that represents a components properties
     * in a certain domain (translational or rotational).
     * 
     * @param name is the name of the component being represented
     * @param source is the source reference frame of the component being represented
     * @param target is the source reference frame of the component being represented
     * @param type is the type of component being represented
     * @param domain is the domain in which this edge represents the component's properties
     */
    public MechanicalEdge(String name, ReferenceFrame source, ReferenceFrame target, ComponentType type ,Domain domain) {
        super(name, source, target);
        this.type = type;
        this.domain = domain;

        String[] defaultVariabless;
        if(domain.equals(Domain.TRANSLATIONAL)) {
            defaultVariabless = type.getTranslationalVariables();
        } else {
            defaultVariabless = type.getRotationalVariables();
        }

        variables = new String[defaultVariabless.length];
        for(int i =0; i < defaultVariabless.length; i++) {
            variables[i] = defaultVariabless[i] + "_" + name;
        }
    }

    /**
     * @return the domain to which the edge is associated
     */
    public Domain getDomain() {
        return domain;
    }

    /**
     * @return a string array of variable names associated to the edge's component and
     *         domain. The variable names are appended with an underscore and the component name.
     */
    public String[] getVariables() {
        return variables;
    }

    /**
     * @return the number of variables associated with the component type in the given
     *         domain.
     */
    public int getNumberOfVariables() {
        return variables.length;
    }

    /**
     * @return the component type being represented.
     */
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MechanicalEdge[Name: " + getName() + ", ReferenceFrames: " + getSourceNode().getName() +
                " -> " + getTargetNode().getName() + ", Type: " + type.toString() + ", Domain: " +
                domain.toString() + ", Weight: " + getWeight() + "]";
    }

}