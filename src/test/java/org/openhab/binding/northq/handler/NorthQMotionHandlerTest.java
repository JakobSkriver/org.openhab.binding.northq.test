package org.openhab.binding.northq.handler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openhab.binding.northq.internal.common.NorthQConfig;
import org.openhab.binding.northq.internal.model.NorthNetwork;
import org.openhab.binding.northq.internal.model.Qmotion;
import org.openhab.binding.northq.internal.services.CredentialsService;
import org.openhab.binding.northq.internal.services.NorthqServices;
import org.openhab.binding.northq.handler.MockCallback;
import org.openhab.binding.northq.handler.MockThing;
import org.openhab.binding.northq.handler.MockCommand;

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
        System.out.println(NorthQConfig.getNETWORK().getGateways() != null);
        thing.setProperty("thingID", "5"); // 5 for motion
        // initMocks(this);
        nm = new NorthQMotionHandler(thing);
        nm.setCallback(callback);
    }

    @Test
    public void testArm() throws IOException, Exception {
        NorthQConfig.setNETWORK(network);
        System.out.println(NorthQConfig.getNETWORK().getGateways().size());
        System.out.println(NorthQConfig.getNETWORK() != null);
        System.out.println(NorthQConfig.getNETWORK().getGateways() != null);
        Qmotion qm = nm.getQmotion("5");
        if (qm != null) {
            nm.arm(network.getGateways().get(0).getGatewayId(), qm);
            assertTrue(qm.getBs().armed == 1);
        } else {
            fail("q motion = null");
        }
    }

    @Test
    public void testDisarm() throws IOException, Exception {
        NorthQConfig.setNETWORK(network);
        Qmotion qm = nm.getQmotion("5");
        if (qm != null) {
            nm.disarm(network.getGateways().get(0).getGatewayId(), qm);
            assertTrue(qm.getBs().armed == 0);
        } else {
            fail("q motion = null");
        }

    }

    @Test
    public void testSchedule() {
        NorthQConfig.setNETWORK(network);
        nm.ScheduledCode();
    }

    @Test
    public void initializeShouldCallTheCallback() {
        nm.initialize();
        ChannelUID t = new ChannelUID("northq:qMotion:5:channelmotion");
        nm.handleCommand(t, mockCommand);
        mockCommand.command = "OFF";
        nm.handleCommand(t, mockCommand);
        try {

        } catch (Exception e) {

        }

        nm.handleRemoval();

    }
}
