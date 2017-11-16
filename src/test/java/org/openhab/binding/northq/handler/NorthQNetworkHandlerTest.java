package org.openhab.binding.northq.handler;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.eclipse.smarthome.core.thing.Bridge;
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

public class NorthQNetworkHandlerTest {

    @Mock
    private Bridge bridge = new MockBridge();
    @Mock
    private Thing thing = new MockThing();
    @Mock
    private ThingHandlerCallback callback = new MockCallback();
    @Mock
    private MockCommand mockCommand = new MockCommand();
    @Mock
    private ChannelUID mockChannel;

    private NorthQNetworkHandler handler;
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

        bridge.setProperty("username", user.get(0));
        bridge.setProperty("password", user.get(1));

        handler = new NorthQNetworkHandler(bridge);
        handler.setCallback(callback);
    }

    @Test
    public void liveNetworkHandlerTest() {
        NorthQConfig.setMOCK(false);
        handler.initialize();

        handler.handleCommand(mockChannel, mockCommand);

        handler.handleRemoval();
    }

    @Test
    public void mockNetworkHandlerTest() throws InterruptedException {
        NorthQConfig.setMOCK(true);
        handler.initialize();
        NorthQConfig.setMOCK_NETWORK(null);
        TimeUnit.SECONDS.sleep(10);
        handler.handleRemoval();
    }
}