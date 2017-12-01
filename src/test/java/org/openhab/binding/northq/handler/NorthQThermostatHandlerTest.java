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

        thing.setProperty("thingID", "4"); // 4 for thermostat (node_id) TODO Get node_id in a different way!!
        thing.setProperty("BINDING_ID", "northq"); // Always northq for the Binding_id
        thing.setProperty("ThingUID", "qThermostat"); // Depends on the test (check NorthQBindingConstants)

        handler = new NorthQThermostatHandler(thing);
        handler.setCallback(callback);

    }

    @Test
    public void setTemperatureHandlerTest() throws InterruptedException {
        handler.initialize();
        TimeUnit.SECONDS.sleep(5);
        ChannelUID t = new ChannelUID("northq:qThermostat:4:channelthermostat");

        // Sending a command to handlecommand with a temp value of 21
        mockCommand.command = "21";
        handler.handleCommand(t, mockCommand);
        handler.getThermostat("4");
        handler.getThermostat("4").getTemp();
        assertTrue(handler.getThermostat("4").getTemp() == 21);
        // Sending a command to handlecommand with a temp value of 19
        mockCommand.command = "19";
        handler.handleCommand(t, mockCommand);
        assertTrue(handler.getThermostat("4").getTemp() == 19);

        handler.handleRemoval();
    }

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

        ChannelUID t = new ChannelUID("northq:qThermostat:4:channelthermostat");

        handler.handleRemoval();
    }

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

        ChannelUID t = new ChannelUID("northq:qThermostat:4:channelthermostat");

        handler.handleRemoval();
    }

}
