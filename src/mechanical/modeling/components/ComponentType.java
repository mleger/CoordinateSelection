package mechanical.modeling.components;

/**
 * Enumerates the types of mechanical components and defines their modeling variables
 * in each domain.
 * 
 * @author Mathieu LŽger
 * @since Mar 23, 2014
 */
public enum ComponentType {
    REVOLUTE_JOINT			(new String[]{}, new String[]{"phi"}),
    UNIVERSAL_JOINT			(new String[]{}, new String[]{"phi", "theta"}),
    SPHERICAL_JOINT			(new String[]{}, new String[]{"phi", "theta", "psi"}),
    PRISMATIC_JOINT			(new String[]{"x"} , new String[]{}),
    PLANAR_JOINT			(new String[]{"x", "y"}, new String[]{}),
    XYZ_TRANSLATIONAL_JOINT	(new String[]{"x", "y", "z"} ,new String[]{}),
    REVOLUTE_PRISMATIC_JOINT(new String[]{"x"}, new String[]{"phi"}),
    RIGID_BODY				(new String[]{"x", "y", "z"}, new String[]{"phi", "theta", "psi"}),
    ARM						(new String[]{}, new String[]{}),
    FORCE_DRIVER			(new String[]{"x", "y", "z"}, new String[]{"phi", "theta", "psi"}),
    MOMENT_DRIVER			(new String[]{"x", "y", "z"}, new String[]{"phi", "theta", "psi"}),
    MOTION_DRIVER			(new String[]{}, new String[]{});

    private String[] transationalVariables;
    private String[] rotationalVariables;
    ComponentType(String[] transationalVariables, String[] rotationalVariables) {
        this.transationalVariables = new String[transationalVariables.length];
        this.rotationalVariables = new String[rotationalVariables.length];
        System.arraycopy(transationalVariables, 0, this.transationalVariables, 0, transationalVariables.length);
        System.arraycopy(rotationalVariables, 0, this.rotationalVariables, 0, rotationalVariables.length);
    }

    public String[] getTranslationalVariables(){
        return transationalVariables;
    }

    public String[] getRotationalVariables(){
        return rotationalVariables;
    }
}
