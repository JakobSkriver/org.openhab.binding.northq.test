package org.openhab.binding.northq.internal.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigTest {
    @Test
    public void configTest() {
        NorthQConfig.setISHOME(false);
        assertTrue(!NorthQConfig.ISHOME());
        NorthQConfig.setUSERNAME("hello");
        NorthQConfig.setPASSWORD("hello");
        assertEquals(NorthQConfig.getUSERNAME(), "hello");
        assertEquals(NorthQConfig.getPASSWORD(), "hello");

    }
}
