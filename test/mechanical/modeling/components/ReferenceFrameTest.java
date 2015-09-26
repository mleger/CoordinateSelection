package mechanical.modeling.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReferenceFrameTest {

    @Test
    public void createAReferenceFrame_allItsMethodsShouldWork() {
        ReferenceFrame f1 = new ReferenceFrame("f1");

        assertEquals("The reference frame is not returning the right name", "f1", f1.getName());
    }
}
