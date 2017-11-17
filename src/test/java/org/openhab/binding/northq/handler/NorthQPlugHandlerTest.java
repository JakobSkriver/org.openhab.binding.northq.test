package org.openhab.binding.northq.handler;

import static org.junit.Assert.*;

import java.util.ArrayList;

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

public class NorthQPlugHandlerTest {
    NorthqServices services;
    CredentialsService credentialsServices;
    ArrayList<String> user;
    NorthNetwork network;

    private NorthQPlugHandler handler;

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

        thing.setProperty("thingID", "2"); // 2 for plug (node_id)
        thing.setProperty("BINDING_ID", "northq"); // Always northq for the Binding_id
        thing.setProperty("ThingUID", "qPlug"); // Depends on the test (check NorthQBindingConstants)

        handler = new NorthQPlugHandler(thing);
        handler.setCallback(callback);

    }

    @Test
    public void qPlugHandlerOnOffTest() {
        handler.initialize();
        ChannelUID t = new ChannelUID("northq:qPlug:2:channelPlug");

        // Sending an On command to the handlecommand
        handler.handleCommand(t, mockCommand);
        // Checking if the status has changed to On
        assertTrue(handler.getPlug("2").getStatus());

        // Sending an Off command to the handlecommand
        mockCommand.command = "OFF";
        handler.handleCommand(t, mockCommand);
        // Checking if the status has changed to On
        assertFalse(handler.getPlug("2").getStatus());

        handler.handleRemoval();
    }

}
