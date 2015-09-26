package mechanical.modeling.components;


/**
 * This class implements a mechanical component that can be connected
 * to various reference frames in the graph. Mechanical components
 * consists of joints, mechanical bodies, force/torque drivers, motion
 * drivers, etc.
 * 
 * <p>
 * Note: The enum listing all the different types of components
 * currently supported can be found in the ComponentInfo class.
 * </p>
 * 
 * @author Mathieu LŽger
 * @since Mar 23, 2014
 */
public class MechanicalComponent {

    private final String name;
    private final ReferenceFrame source;
    private final ReferenceFrame target;
    private final ComponentType type;
    private final MechanicalEdge translationalEdge;
    private final MechanicalEdge rotationalEdge;

    /**
     * Creates a mechanical component linking two of the mechanical system's reference frames
     * 
     * @param name is the name given to the component
     * @param source is the source reference frame to which the component is attached
     * @param target is the target reference frame to which the component is attached
     * @param type is the type of component being implemented
     */
    public MechanicalComponent(String name, ReferenceFrame source, ReferenceFrame target, ComponentType type) {
        this.name = name;
        this.source = source;
        this.target = target;
        this.type = type;
        this.translationalEdge = new MechanicalEdge(name, source, target, type, Domain.TRANSLATIONAL);
        this.rotationalEdge = new MechanicalEdge(name, source, target, type, Domain.ROTATIONAL);
    }

    /**
     * @return the name of the mechanical component
     */
    public String getName() {
        return name;
    }

    /**
     * @return the source reference frame of the mechanical component
     */
    public ReferenceFrame getSourceReferenceFrame() {
        return source;
    }

    /**
     * @return the target reference frame of the mechanical component
     */
    public ReferenceFrame getTargetReferenceFrame() {
        return target;
    }

    /**
     * @return the type of mechanical component modeled
     */
    public ComponentType getType() {
        return type;
    }

    /**
     * @return the translational graph edge associated with the mechanical component.
     */
    protected MechanicalEdge getTranslationalEdge() {
        return translationalEdge;
    }

    /**
     * @return the rotational graph edge associated with the mechanical component.
     */
    protected MechanicalEdge getRotationalEdge() {
        return rotationalEdge;
    }

    @Override
    public String toString() {
        return "MechanicalComponent[Name: " + name + ", ReferenceFrames: " + source.getName() +
                " -> " + target.getName() + ", Type: " + type.toString() + "]";
    }

}
