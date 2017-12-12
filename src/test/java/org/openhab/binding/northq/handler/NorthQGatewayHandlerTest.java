/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.northq.handler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
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
 * The {@link NorthQGatewayHandlerTest} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jakob / Philip - Initial contribution.
 */

public class NorthQGatewayHandlerTest {

    @Mock
    private Thing thing = new MockThing();
    @Mock
    private ThingHandlerCallback callback = new MockCallback();
    @Mock
    private MockCommand mockCommand = new MockCommand();
    @Mock
    private ChannelUID mockChannel;

    private NorthQGatewayHandler handler;
    NorthqServices services;
    CredentialsService credentialsServices;
    ArrayList<String> user;
    NorthNetwork network;

    @Before
    public void setup() throws Exception {
        services = new NorthqServices();
        credentialsServices = new CredentialsService();
        user = credentialsServices.getUserCredentials();
        network = services.mapNorthQNetwork(user.get(0), user.get(1));

        NorthQConfig.setNETWORK(network);
        thing.setProperty("thingID", "0000003652");
        thing.setProperty("BINDING_ID", "northq");
        thing.setProperty("ThingUID", "settings");

        handler = new NorthQGatewayHandler(thing);
        handler.setCallback(callback);
    }

    /**
     * Description: Turning Heat on location On and Off respectively
     * Input: On/Off command
     * Expected result: IsHeatOnLocation is set to true and then to false.
     */

    @Test
    public void updateToggleHeatTest() {
        handler.initialize();
        ChannelUID chnltest = new ChannelUID("northq:settings:0:channeltoggleHeatOnLocation");

        // Checking that HeatOnLocation is set to false
        assertFalse(NorthQConfig.isHEATONLOCATION());

        // Sending a command to handlecommand to turn on HeatOnLocation feature
        mockCommand.command = "ON";
        handler.handleCommand(chnltest, mockCommand);

        // Checking that HeatOnLocation is set to true
        assertTrue(NorthQConfig.isHEATONLOCATION());

        // Sending a command to handlecommand to turn off HeatOnLocation feature
        mockCommand.command = "OFF";
        handler.handleCommand(chnltest, mockCommand);

        // Checking that HeatOnLocation is set to false
        assertFalse(NorthQConfig.isHEATONLOCATION());

        handler.handleRemoval();
    }

    /**
     * Description: Setting the temperature when some one is home to 27 from the handler
     * Input: Number command
     * Expected result: The IsHomeTemp in NorthQConfig is set to 27
     */

    @Test
    public void setIsHomeTempTest() {
        handler.initialize();
        ChannelUID chnltest = new ChannelUID("northq:settings:0:channelisHomeTemp");
        mockCommand.command = "27";

        // Setting the IsHomeTemp via handlecommand
        handler.handleCommand(chnltest, mockCommand);

        // Checking if the IsHomeTemp is set in northQconfig
        assertTrue(NorthQConfig.getISHOMETEMP() == 27);

        handler.handleRemoval();
    }

    /**
     * Description: Setting the temperature when no one is home to 12 from the handler
     * Input: Number command
     * Expected result: The NotHomeTemp in NorthQConfig is set to 12
     */

    @Test
    public void setNotHomeTempTest() {
        handler.initialize();
        ChannelUID chnltest = new ChannelUID("northq:settings:0:channelnotHomeTemp");
        mockCommand.command = "12";

        // Setting the NotHomeTemp via handlecommand
        handler.handleCommand(chnltest, mockCommand);

        // Checking if the IsHomeTemp is set in northQconfig
        assertTrue(NorthQConfig.getNOTHOMETEMP() == 12);
        handler.handleRemoval();
    }

    /**
     * Description: Enabling Power on motion
     * Input: On/Off command
     * Expected result: Power on motion is enabled
     */

    @Test
    public void enablePowerOnMotionTest() {
        handler.initialize();
        ChannelUID chnltest = new ChannelUID("northq:settings:0:channelgpspoweroff");
        mockCommand.command = "ON";

        // Enabling Power on motion handlecommand
        handler.handleCommand(chnltest, mockCommand);

        // Checking power on motion is set to true in northQconfig
        assertTrue(NorthQConfig.isPOWERONLOCATION());
        handler.handleRemoval();
    }

    /**
     * Description: Disabling Power on motion
     * Input: On/Off command
     * Expected result: Power on motion is disable
     */

    @Test
    public void disablePowerOnMotionTest() {
        handler.initialize();
        ChannelUID chnltest = new ChannelUID("northq:settings:0:channelgpspoweroff");
        mockCommand.command = "OFF";

        // Disabling Power on motion handlecommand
        handler.handleCommand(chnltest, mockCommand);

        // Checking power on motion is set to false in northQconfig
        assertFalse(NorthQConfig.isPOWERONLOCATION());
        handler.handleRemoval();
    }

    /**
     * Description: Enabling Temperature Scheduler
     * Input: On/Off command
     * Expected result: Temperature Scheduler is enabled
     */

    @Test
    public void enableTempSchedulerTest() {
        handler.initialize();
        ChannelUID chnltest = new ChannelUID("northq:settings:0:channelScheduleTemp");
        mockCommand.command = "ON";

        // Enabling temperature scheduler
        handler.handleCommand(chnltest, mockCommand);

        // Checking temperature scheduler is set to true in northQconfig
        assertTrue(NorthQConfig.isTEMP_SCHEDULER());
        handler.handleRemoval();
    }

    /**
     * Description: Disabling Temperature Scheduler
     * Input: On/Off command
     * Expected result: Temperature Scheduler is disable
     */

    @Test
    public void disableTempSchedulerTest() {
        handler.initialize();
        ChannelUID chnltest = new ChannelUID("northq:settings:0:channelScheduleTemp");
        mockCommand.command = "OFF";

        // Disabling temperature scheduler
        handler.handleCommand(chnltest, mockCommand);

        // Checking temperature scheduler is set to false in northQconfig
        assertFalse(NorthQConfig.isTEMP_SCHEDULER());
        handler.handleRemoval();
    }

    /**
     * Description: Testing schedule code
     * Input:
     * Expected result:
     *
     * @throws InterruptedException
     */

    @Test
    public void scheduleCodeTest() throws InterruptedException {
        handler.initialize();

        TimeUnit.SECONDS.sleep(11);

        NorthQConfig.setHEATONLOCATION(true);
        NorthQConfig.setPOWERONLOCATION(true);

        TimeUnit.SECONDS.sleep(11);

        HashMap<String, Boolean> phone_map = new HashMap<String, Boolean>();
        phone_map.put("TestPhone", false);

        NorthQConfig.setPHONE_MAP(phone_map);

        TimeUnit.SECONDS.sleep(11);

        phone_map.put("TestPhone2", true);

        handler.handleRemoval();
    }
}