/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.northq.handler;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openhab.binding.northq.internal.common.NorthQConfig;
import org.openhab.binding.northq.internal.mock.MockCallback;
import org.openhab.binding.northq.internal.mock.MockCommand;
import org.openhab.binding.northq.internal.mock.MockThing;
import org.openhab.binding.northq.internal.model.NorthNetwork;
import org.openhab.binding.northq.internal.services.CredentialsService;
import org.openhab.binding.northq.internal.services.NorthqServices;

/**
 * The {@link NorthQThermostatHandlerTest} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jakob / Philip - Initial contribution.
 */

public class NorthQThermostatHandlerTest {
    NorthqServices services;
    CredentialsService credentialsServices;
    ArrayList<String> user;
    NorthNetwork network;

    private NorthQThermostatHandler handler;

    @Mock
    private Thing thing = new MockThing();

    @Mock
    private ThingHandlerCallback callback = new MockCallback();
    @Mock
    private MockCommand mockCommand = new MockCommand();
    @Mock
    private ChannelUID mockChannel;

    @Before
    public void setUp() throws Exception {

        services = new NorthqServices();
        credentialsServices = new CredentialsService();
        user = credentialsServices.getUserCredentials();
        network = services.mapNorthQNetwork(user.get(0), user.get(1));

        NorthQConfig.setNETWORK(network);

        thing.setProperty("thingID", "4"); // 4 for thermostat (node_id)
        thing.setProperty("BINDING_ID", "northq"); // Always northq for the Binding_id
        thing.setProperty("ThingUID", "qThermostat"); // Depends on the test (check NorthQBindingConstants)

        handler = new NorthQThermostatHandler(thing);
        handler.setCallback(callback);

    }

    /**
     * Description: Setting the temperature of the thermostat to 21 and then to 29
     * Input: Number command
     * Expected result: The thermostat is set to first 21 and then to 29
     */

    @Test
    public void setTemperatureHandlerTest() throws InterruptedException {
        handler.initialize();
        TimeUnit.SECONDS.sleep(5);
        ChannelUID t = new ChannelUID("northq:qThermostat:4:channelthermostat");

        // Sending a command to handlecommand with a temp value of 21
        mockCommand.command = "21";
        handler.handleCommand(t, mockCommand);
        assertTrue(handler.getThermostat("4").getTemp() == 21);

        // Sending a command to handlecommand with a temp value of 19
        mockCommand.command = "19";
        handler.handleCommand(t, mockCommand);
        assertTrue(handler.getThermostat("4").getTemp() == 19);

        handler.handleRemoval();
    }

    /**
     * Description: When no one is home, the temperature is set to the temperature in the variable NotHomeTemp
     * Input: Set config to no one home, and set no one home temp to 32
     * Expected result: Checking the not home temperature is set to 32
     */

    @Test
    public void above30NotHomeTest() throws InterruptedException {
        handler.initialize();
        // Configurating that noone is home
        NorthQConfig.setISHOME(false);

        // Setting the temperature for when noone is home to 32
        NorthQConfig.setNOTHOMETEMP(32);
        TimeUnit.SECONDS.sleep(5);

        // Checking that NotHomeTemp is set to 32
        assertTrue(NorthQConfig.getNOTHOMETEMP() == 32);

        handler.handleRemoval();
    }

    /**
     * Description: When some one is home, the temperature is set to the temperature in the variable IsHomeTemp
     * Input: Set config to is home, and set is home temp to 3
     * Expected result: Checking the is home temperature is set to 3
     */

    @Test
    public void below5IsHomeTest() throws InterruptedException {
        handler.initialize();
        // Configurating that noone is home
        NorthQConfig.setISHOME(true);

        // Setting the temperature for when someone is home to 3
        NorthQConfig.setISHOMETEMP(3);
        TimeUnit.SECONDS.sleep(5);

        // Checking that the temperature when someone is home to 3
        assertTrue(NorthQConfig.getISHOMETEMP() == 3);

        handler.handleRemoval();
    }

}
