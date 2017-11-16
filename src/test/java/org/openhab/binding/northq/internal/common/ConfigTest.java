package org.openhab.binding.northq.internal.common;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.openhab.binding.northq.internal.mock.NorthQMockNetwork;
import org.openhab.binding.northq.internal.model.NorthNetwork;

public class ConfigTest {
    @Test
    public void credentialsTest() {
        // Test setters and getters:
        NorthQConfig.setUSERNAME("hello");
        NorthQConfig.setPASSWORD("hello");
        assertEquals(NorthQConfig.getUSERNAME(), "hello");
        assertEquals(NorthQConfig.getPASSWORD(), "hello");
    }

    @Test
    public void heatAndIsHomeTest() {
        // Test setters and getters:
        NorthQConfig.setISHOME(false);
        NorthQConfig.setHEATONLOCATION(true);
        NorthQConfig.setISHOMETEMP(20);
        NorthQConfig.setNOTHOMETEMP(10);

        assertTrue(!NorthQConfig.ISHOME());
        assertTrue(NorthQConfig.isHEATONLOCATION());
        assertTrue(NorthQConfig.getNOTHOMETEMP() == 10);
        assertTrue(NorthQConfig.getISHOMETEMP() == 20);
    }

    @Test
    public void networkTest() {
        NorthNetwork testNetwork = new NorthNetwork(null, null, null, null);
        NorthQConfig.setNETWORK(testNetwork);
        assertTrue(NorthQConfig.getNETWORK().equals(testNetwork));
    }

    @Test
    public void mockActivationTest() {
        NorthQConfig.setMOCK(true);
        assertTrue(NorthQConfig.isMOCK());

        NorthQMockNetwork mn = new NorthQMockNetwork();
        NorthQConfig.setMOCK_NETWORK(mn);
        assertTrue(mn.equals(NorthQConfig.getMOCK_NETWORK()));
    }

    @Test
    public void phoneConfigTest() {
        NorthQConfig.setPHONE_MAP(new HashMap<>());
        NorthQConfig.getPHONE_MAP().put("test", new Boolean(true));

    }
}
