package dev.lansdon.data.hibernate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import dev.lansdon.models.Editor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EditorHibernateTest {
    public static EditorHibernate eHibernate;

    @BeforeAll
    public static void setup() {
        eHibernate = new EditorHibernate();
    }

    @Test
    public void testGetByUsername() {
        Editor e = eHibernate.getByUsername("john");
        assertEquals("john",e.getUser().getUsername());
    }
}
