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

    private NorthQMotionHandler nm;
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

        nm = new NorthQMotionHandler(thing);
        nm.setCallback(callback);
    }

    @Test
    public void qMotionHandlerArmDisArmTest() throws InterruptedException {
        nm.initialize();
        TimeUnit.SECONDS.sleep(5);
        ChannelUID t = new ChannelUID("northq:qMotion:5:channelmotion");

        nm.handleCommand(t, mockCommand);
        assertTrue(nm.getQmotion("5").getStatus());
        TimeUnit.SECONDS.sleep(5);

        mockCommand.command = "OFF";
        nm.handleCommand(t, mockCommand);
        assertFalse(nm.getQmotion("5").getStatus());
        TimeUnit.SECONDS.sleep(5);

        nm.handleRemoval();

    }

}
