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

public class SettingsHandlerTest {

    @Mock
    private Thing thing = new MockThing();
    @Mock
    private ThingHandlerCallback callback = new MockCallback();
    @Mock
    private MockCommand mockCommand = new MockCommand();
    @Mock
    private ChannelUID mockChannel;

    private GatewayHandler handler;
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
        thing.setProperty("BINDING_ID", "northq");
        thing.setProperty("ThingUID", "settings");

        handler = new GatewayHandler(thing);
        handler.setCallback(callback);
    }

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

}