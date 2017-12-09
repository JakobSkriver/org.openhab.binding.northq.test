/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.northq.handler;

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
 * The {@link NorthQPhoneHandlerTest} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jakob / Philip - Initial contribution.
 */

public class NorthQPhoneHandlerTest {
    NorthqServices services;
    CredentialsService credentialsServices;
    ArrayList<String> user;
    NorthNetwork network;

    private NorthQPhoneHandler handler;
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

        thing.setProperty("thingID", "0"); // Test id for phone
        thing.setProperty("BINDING_ID", "northq"); // Always northq for the Binding_id
        thing.setProperty("ThingUID", "qPhone"); // Depends on the test (check NorthQBindingConstants)
        thing.setProperty("name", "Nicolaj");

        handler = new NorthQPhoneHandler(thing);

        handler.setCallback(callback);

    }

    /**
     * Description: Turning the Phone location service On and Off for the phone from the Handler.
     * Input: On/Off Command
     * Expected result: The Phone is set to enabled, the scheduled code will then change the phone to isHome or not. The
     * phone is set to disabled and removed from the phone map.
     */

    @Test
    public void qPhoneChangeStatusTest() throws InterruptedException {
        handler.initialize();
        ChannelUID t = new ChannelUID("northq:qPhone:0:channelgps");
        TimeUnit.SECONDS.sleep(5);

        // Sending an On command to the phone
        handler.handleCommand(t, mockCommand);
        TimeUnit.SECONDS.sleep(5);

        // Sending an Off command to the phone
        mockCommand.command = "OFF";
        handler.handleCommand(t, mockCommand);
        TimeUnit.SECONDS.sleep(5);

        handler.handleRemoval();
    }

}
