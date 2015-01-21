package org.olid16.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

public class Assert {

    public static void assertThrows(Class ex, Runnable runnable) {
        Exception exThrown = null;
        try {
            runnable.run();
        } catch (Exception exThrownActual) {
            exThrown = exThrownActual;
        }
        if (null == exThrown){
            fail("No exception thrown");
        } else {
            assertThat(ex).isEqualTo(exThrown.getClass());
        }
    }

    public static void assertNotThrows(Runnable runnable) {
        Exception exThrown = null;
        try {
            runnable.run();
        } catch (Exception exThrownActual) {
            exThrown = exThrownActual;
        }
        
        assertThat(exThrown).isNull();
    }
}
