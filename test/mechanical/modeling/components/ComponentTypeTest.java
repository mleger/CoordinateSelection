package mechanical.modeling.components;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ComponentTypeTest {

    @Test
    public void getModelingVariablesFromVariousComponents_shouldReturnTheCorrectStringArray() {
        String[] revoluteJointTrans = ComponentType.REVOLUTE_JOINT.getTranslationalVariables();
        assertArrayEquals("", new String[]{}, revoluteJointTrans);

        String[] revoluteJointRot = ComponentType.REVOLUTE_JOINT.getRotationalVariables();
        assertArrayEquals("", new String[]{"phi"}, revoluteJointRot);

        String[] rigidBodyTrans = ComponentType.RIGID_BODY.getTranslationalVariables();
        assertArrayEquals("", new String[]{"x", "y", "z"}, rigidBodyTrans);

        String[] rigidBodyRot = ComponentType.RIGID_BODY.getRotationalVariables();
        assertArrayEquals("", new String[]{"phi", "theta", "psi"}, rigidBodyRot);
    }
}
