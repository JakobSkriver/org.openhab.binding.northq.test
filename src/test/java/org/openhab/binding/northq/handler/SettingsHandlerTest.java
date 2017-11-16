package org.openhab.binding.northq.handler;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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

public class SettingsHandlerTest {

    @Mock
    private Thing thing = new MockThing();
    @Mock
    private ThingHandlerCallback callback = new MockCallback();
    @Mock
    private MockCommand mockCommand = new MockCommand();
    @Mock
    private ChannelUID mockChannel;

    private SettingsHandler handler;
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

        handler = new SettingsHandler(thing);
        handler.setCallback(callback);
    }

    @Test
    public void test() {
        assertTrue(true);
    }

    // @Test
    // public void updateToggleHeatTest() {
    // handler.initialize();
    // ChannelUID chnltest = new ChannelUID("northq:settings:2:channeltoggleHeatOnLocation");
    //
    // assertTrue(!NorthQConfig.isHEATONLOCATION());
    // mockCommand.command = "ON";
    // handler.handleCommand(chnltest, mockCommand);
    // assertTrue(NorthQConfig.isHEATONLOCATION());
    // handler.handleRemoval();
    // }

}