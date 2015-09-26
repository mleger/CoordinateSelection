package mechanical.modeling.components;

import graph.components.BasicWeightedGraph;
import graph.elements.WeightedGraph;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class creates a mechanical system consisting of a set of mechanical
 * components connected to various body-fixed reference frames.
 * 
 * @author Mathieu LŽger
 * @since Mar 23, 2014
 */
public class MechanicalSystem {

    private final Set<MechanicalComponent> components;
    private final WeightedGraph<ReferenceFrame, MechanicalEdge> translationalGraph;
    private final WeightedGraph<ReferenceFrame, MechanicalEdge> rotationalGraph;
    private final ReferenceFrame ground;

    /**
     * Creates a mechanical system of mechanical components connected to reference frames.
     * 
     * @param ground is the ground reference system
     */
    public MechanicalSystem(ReferenceFrame ground){
        this.components = new LinkedHashSet<MechanicalComponent>();
        this.translationalGraph = new BasicWeightedGraph<ReferenceFrame, MechanicalEdge>();
        this.rotationalGraph = new BasicWeightedGraph<ReferenceFrame, MechanicalEdge>();
        this.ground = ground;
    }

    /**
     * @return a set of all the components in the system
     */
    public Set<MechanicalComponent> getAllComponents() {
        return Collections.unmodifiableSet(components);
    }

    /**
     * Adds a component to the mechanical system
     * 
     * @param component is the component to be added
     * 
     * @return a boolean indicating if the component was successfully added to the system
     * 
     * @throws IllegalArgumentException if the component to be added is null.
     */
    public boolean addComponent(MechanicalComponent component) {
        if(component == null) {
            throw new IllegalArgumentException("The component is null");
        }

        boolean success = true;
        success = success && components.add(component);
        success = success && translationalGraph.addEdge(component.getTranslationalEdge());
        success = success && rotationalGraph.addEdge(component.getRotationalEdge());
        return success;
    }

    /**
     * Removes a component to the mechanical system
     * 
     * @param component is the component to be removed
     * 
     * @return a boolean indicating if the component was successfully removed from the system
     * 
     * @throws IllegalArgumentException if the component to be added is null.
     */
    public boolean removeComponent(MechanicalComponent component) {
        if(component == null) {
            throw new IllegalArgumentException("The component is null");
        }

        boolean success = true;
        success = success && components.remove(component);
        success = success && translationalGraph.removeEdge(component.getTranslationalEdge());
        success = success && rotationalGraph.removeEdge(component.getRotationalEdge());
        return success;
    }

    /**
     * @return the ground reference frame of the mechanical system
     */
    public ReferenceFrame getGround() {
        return ground;
    }

    /**
     * @return the graph representing the topology of the mechanical system in the translation domain
     */
    public WeightedGraph<ReferenceFrame, MechanicalEdge> getTranslationalGraph(){
        return translationalGraph;
    }

    /**
     * @return the graph representing the topology of the mechanical system in the rotational domain
     */
    public WeightedGraph<ReferenceFrame, MechanicalEdge> getRotationalGraph(){
        return rotationalGraph;
    }

    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");

        StringBuilder sb = new StringBuilder();
        sb.append("MechanicalSystem");
        sb.append("[");
        boolean firstComponent = true;
        for(MechanicalComponent component : components){
            if(!firstComponent){
                sb.append("                ");
            }
            sb.append(component.toString());
            sb.append(newLine);
            firstComponent = false;
        }
        sb.append("]");

        return sb.toString();
    }
}
