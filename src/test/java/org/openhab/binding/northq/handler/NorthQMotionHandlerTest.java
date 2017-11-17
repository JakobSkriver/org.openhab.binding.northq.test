package org.openhab.binding.northq.handler;

import static org.junit.Assert.*;

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

public class NorthQMotionHandlerTest {
    @Mock
    private Thing thing = new MockThing();
    @Mock
    private ThingHandlerCallback callback = new MockCallback();
    @Mock
    private MockCommand mockCommand = new MockCommand();
    @Mock
    private ChannelUID mockChannel;

    private NorthQMotionHandler handler;
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

        thing.setProperty("thingID", "5"); // 5 for motion
        thing.setProperty("BINDING_ID", "northq"); // Always northq for the Binding_id
        thing.setProperty("ThingUID", "qMotion"); // Depends on the test (check NorthQBindingConstants)

        handler = new NorthQMotionHandler(thing);
        handler.setCallback(callback);
    }

    @Test
    public void qMotionHandlerArmDisArmTest() throws InterruptedException {
        handler.initialize();
        TimeUnit.SECONDS.sleep(5);
        ChannelUID t = new ChannelUID("northq:qMotion:5:channelmotion");

        // Sending an arm command to the handleCommand
        handler.handleCommand(t, mockCommand);
        // Check if status of qmotion is armed
        assertTrue(handler.getQmotion("5").getStatus());
        TimeUnit.SECONDS.sleep(5);

        // Sending an disarm command to the handleCommand
        mockCommand.command = "OFF";
        handler.handleCommand(t, mockCommand);
        // Check if status of qmotion is disarmed
        assertFalse(handler.getQmotion("5").getStatus());
        TimeUnit.SECONDS.sleep(5);

        handler.handleRemoval();

    }

}
