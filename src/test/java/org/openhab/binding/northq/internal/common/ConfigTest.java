/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.northq.internal.common;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.openhab.binding.northq.internal.mock.NorthQMockNetwork;
import org.openhab.binding.northq.internal.model.NorthNetwork;

/**
 * The {@link ConfigTest} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jakob / Philip - Initial contribution.
 */

public class ConfigTest {

    /**
     * Description: Test the username and password getters and setters
     * Input: Set Username and Password
     * Expected result: Get Username and Password
     */

    @Test
    public void credentialsTest() {
        // Test setters and getters for credentials:
        NorthQConfig.setUSERNAME("hello");
        NorthQConfig.setPASSWORD("hello");
        assertEquals(NorthQConfig.getUSERNAME(), "hello");
        assertEquals(NorthQConfig.getPASSWORD(), "hello");
    }

    /**
     * Description: Test the HeatOnLocation and IsHome getters and setters
     * Input: Set HeatOnLocation and IsHome
     * Expected result: Get HeatOnLocation and IsHome
     */

    @Test
    public void heatAndIsHomeTest() {
        // Test setters and getters for heat functionality:
        NorthQConfig.setISHOME(false);
        NorthQConfig.setHEATONLOCATION(true);
        NorthQConfig.setISHOMETEMP(20);
        NorthQConfig.setNOTHOMETEMP(10);

        assertTrue(!NorthQConfig.ISHOME());
        assertTrue(NorthQConfig.isHEATONLOCATION());
        assertTrue(NorthQConfig.getNOTHOMETEMP() == 10);
        assertTrue(NorthQConfig.getISHOMETEMP() == 20);
    }

    /**
     * Description: Test Network getters and setters
     * Input: Set Network
     * Expected result: Get Network
     */

    @Test
    public void networkTest() {
        // Checking that network is set in NorthQConfig
        NorthNetwork testNetwork = new NorthNetwork(null, null, null, null);
        NorthQConfig.setNETWORK(testNetwork);
        assertTrue(NorthQConfig.getNETWORK().equals(testNetwork));
    }

    /**
     * Description: Test IsMock and Mock Network getters and setters
     * Input: Set IsMock and Mock Network
     * Expected result: Get IsMock and Mock Network
     */

    @Test
    public void mockActivationTest() {
        // Checking that the Mock is activated in NorthQConfig
        NorthQConfig.setMOCK(true);
        assertTrue(NorthQConfig.isMOCK());

        NorthQMockNetwork mn = new NorthQMockNetwork();
        NorthQConfig.setMOCK_NETWORK(mn);
        assertTrue(mn.equals(NorthQConfig.getMOCK_NETWORK()));

        NorthQConfig.setMOCK(false);
    }

    /**
     * Description: Test Phone Map getters and setters
     * Input: Set Phone Map
     * Expected result: Get Phone Map
     */

    @Test
    public void phoneConfigTest() {
        // Checking PhoneMap is set in NorthQConfigs
        NorthQConfig.setPHONE_MAP(new HashMap<>());
        NorthQConfig.getPHONE_MAP().put("test", new Boolean(true));

    }
}
