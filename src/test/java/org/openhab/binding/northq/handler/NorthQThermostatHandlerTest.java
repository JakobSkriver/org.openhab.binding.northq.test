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
import org.openhab.binding.northq.internal.model.NorthNetwork;
import org.openhab.binding.northq.internal.services.CredentialsService;
import org.openhab.binding.northq.internal.services.NorthqServices;

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

    // 90.1% Coverage appear to be the best we can do, without properly emulating the northq network
    @Before
    public void setUp() throws Exception {

        services = new NorthqServices();
        credentialsServices = new CredentialsService();
        user = credentialsServices.getUserCredentials();
        network = services.mapNorthQNetwork(user.get(0), user.get(1));

        NorthQConfig.setNETWORK(network);
        System.out.println(NorthQConfig.getNETWORK().getGateways() != null);
        thing.setProperty("thingID", "4"); // 4 for thermostat (node_id) TODO Get node_id in a different way!!
        thing.setProperty("BINDING_ID", "northq"); // Always northq for the Binding_id
        thing.setProperty("ThingUID", "qThermostat"); // Depends on the test (check NorthQBindingConstants)

        handler = new NorthQThermostatHandler(thing);
        handler.setCallback(callback);

    }

    @Test
    public void initializeShouldCallTheCallback() throws InterruptedException {
        handler.initialize();
        TimeUnit.SECONDS.sleep(5); // maybe not needed? yea it's kinda needed (or just test scheduled-stuff)
        ChannelUID t = new ChannelUID("northq:qThermostat:4:channelthermostat");
        mockCommand.command = "21";
        handler.handleCommand(t, mockCommand);

        mockCommand.command = "19";
        handler.handleCommand(t, mockCommand);

        try {

        } catch (Exception e) {

        }
        handler.handleRemoval();
    }

}
