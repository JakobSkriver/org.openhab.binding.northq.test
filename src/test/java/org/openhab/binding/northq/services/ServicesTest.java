package org.openhab.binding.northq.services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.openhab.binding.northq.handler.NorthQPhoneHandler;
import org.openhab.binding.northq.internal.model.NGateway;
import org.openhab.binding.northq.internal.model.NorthNetwork;
import org.openhab.binding.northq.internal.model.Qmotion;
import org.openhab.binding.northq.internal.model.Qplug;
import org.openhab.binding.northq.internal.model.Qthermostat;
import org.openhab.binding.northq.internal.model.Thing;
import org.openhab.binding.northq.internal.model.json.Notification;
import org.openhab.binding.northq.internal.model.json.UserNotification;
import org.openhab.binding.northq.internal.model.json.UserNotificationHolder;
import org.openhab.binding.northq.internal.services.CredentialsService;
import org.openhab.binding.northq.internal.services.NorthqServices;

public class ServicesTest {
    NorthqServices services;
    CredentialsService credentialsServices;
    ArrayList<String> user;
    NorthNetwork network;
    NorthQPhoneHandler phoneHandler;

    // --------------------------------------------------
    // Setup - Start
    @Before
    public void setUp() throws Exception {
        services = new NorthqServices();
        credentialsServices = new CredentialsService();
        user = credentialsServices.getUserCredentials();
        network = services.mapNorthQNetwork(user.get(0), user.get(1));
    }

    @Test
    public void mapGenerationTest() throws Exception {
        assertEquals(network.getUserId(), "2166");
        assertTrue(network.getHouses().length >= 1);
    }
    // Setup - End
    // --------------------------------------------------

    // --------------------------------------------------
    // Use Case 1 - Start
    @Test
    public void plugOnTest() {
        Qplug plug = null;
        for (Thing t : network.getGateways().get(0).getThings()) {
            if (t instanceof Qplug) {
                plug = (Qplug) t;
            }
        }
        try {
            if (plug != null) {
                boolean res1 = services.turnOffPlug(plug, network.getToken(), network.getUserId(),
                        network.getGateways().get(0).getGatewayId());
                boolean res2 = services.turnOnPlug(plug, network.getToken(), network.getUserId(),
                        network.getGateways().get(0).getGatewayId());
                assertTrue(res1);
                assertTrue(res2);

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            NorthNetwork newNetwork = services.mapNorthQNetwork(user.get(0), user.get(1));
            for (Thing t : newNetwork.getGateways().get(0).getThings()) {
                if (t instanceof Qplug) {
                    Qplug updatedPlug = (Qplug) t;
                    if (updatedPlug.getNodeID() == updatedPlug.getNodeID()) {
                        assertTrue(updatedPlug.getStatus());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void plugOffTest() throws IOException, Exception {
        Qplug plug = null;
        for (Thing t : network.getGateways().get(0).getThings()) {
            if (t instanceof Qplug) {
                plug = (Qplug) t;
            }
        }
        try {
            if (plug != null) {
                boolean res2 = services.turnOnPlug(plug, network.getToken(), network.getUserId(),
                        network.getGateways().get(0).getGatewayId());
                boolean res1 = services.turnOffPlug(plug, network.getToken(), network.getUserId(),
                        network.getGateways().get(0).getGatewayId());

                assertTrue(res1); // prone to false negatives - rerun code likely an http issue
                assertTrue(res2);

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            NorthNetwork newNetwork = services.mapNorthQNetwork(user.get(0), user.get(1));
            for (Thing t : newNetwork.getGateways().get(0).getThings()) {
                if (t instanceof Qplug) {
                    Qplug updatedPlug = (Qplug) t;
                    if (updatedPlug.getNodeID() == updatedPlug.getNodeID()) {
                        assertTrue(!updatedPlug.getStatus());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        // Plug is turned on for convenience
        services.turnOnPlug(plug, network.getToken(), network.getUserId(), network.getGateways().get(0).getGatewayId());
    }
    // Use Case 1 - End
    // --------------------------------------------------

    // --------------------------------------------------
    // Use Case 2 - Start
    @Test
    public void motionArmTest() throws IOException, Exception {
        // get devices find correct device and turn on:
        NGateway gateway = network.getGateways().get(0);
        ArrayList<Thing> testThings = network.getGateways().get(0).getThings();
        Qmotion mot = null;
        for (int i = 0; i < testThings.size(); i++) {
            if (testThings.get(i) instanceof Qmotion) {
                mot = (Qmotion) testThings.get(i);
            }
        }
        if (mot == null) {
            fail("no motion was detected in network!");
        }
        Response res1 = services.disarmMotion(network.getUserId(), network.getToken(), gateway.getGatewayId(), mot);
        Response res2 = services.armMotion(network.getUserId(), network.getToken(), gateway.getGatewayId(), mot);

        assertEquals(res1.getStatus(), 200);
        assertEquals(res2.getStatus(), 200);

        // fetch new status:
        network = services.mapNorthQNetwork(user.get(0), user.get(1));
        gateway = network.getGateways().get(0);
        testThings = network.getGateways().get(0).getThings();
        mot = null;
        for (int i = 0; i < testThings.size(); i++) {
            if (testThings.get(i) instanceof Qmotion) {
                mot = (Qmotion) testThings.get(i);
            }
        }
        if (mot == null) {
            fail("no motion was detected in network!");
        }

        assertEquals(mot.getStatus(), true);
    }

    @Test
    public void motionDisarmTest() throws IOException, Exception {
        // get devices find correct device and turn on:
        NGateway gateway = network.getGateways().get(0);
        ArrayList<Thing> testThings = network.getGateways().get(0).getThings();
        Qmotion mot = null;
        for (int i = 0; i < testThings.size(); i++) {
            if (testThings.get(i) instanceof Qmotion) {
                mot = (Qmotion) testThings.get(i);
            }
        }
        if (mot == null) {
            fail("no motion was detected in network!");
        }
        Response res2 = services.armMotion(network.getUserId(), network.getToken(), gateway.getGatewayId(), mot);
        Response res1 = services.disarmMotion(network.getUserId(), network.getToken(), gateway.getGatewayId(), mot);

        assertEquals(res1.getStatus(), 200);
        assertEquals(res2.getStatus(), 200);

        // fetch new status:
        network = services.mapNorthQNetwork(user.get(0), user.get(1));
        gateway = network.getGateways().get(0);
        testThings = network.getGateways().get(0).getThings();
        mot = null;
        for (int i = 0; i < testThings.size(); i++) {
            if (testThings.get(i) instanceof Qmotion) {
                mot = (Qmotion) testThings.get(i);
            }
        }
        if (mot == null) {
            fail("no motion was detected in network!");
        }

        assertEquals(mot.getStatus(), false);
    }

    @Test
    public void notificationTest() throws IOException, Exception {
        UserNotificationHolder res = services.getNotificationArray(network.getUserId(), network.getToken(),
                network.getHouses()[0].id + "", "1");
        assertTrue(res.UserNotifications.size() >= 1);
    }
    // Use Case 2 - End
    // --------------------------------------------------

    // --------------------------------------------------
    // Use Case 3 - Start
    @Test
    public void thermostatTest() throws IOException, Exception {
        NGateway gateway = network.getGateways().get(0);
        ArrayList<Thing> testThings = network.getGateways().get(0).getThings();
        Qthermostat ther = null;

        // Get thermostat object - Only the last thermostat
        for (int i = 0; i < testThings.size(); i++) {
            if (testThings.get(i) instanceof Qthermostat) {
                ther = (Qthermostat) testThings.get(i);
            }
        }

        if (ther == null) {
            fail("no thermostat was detected in network!");
        }

        boolean res = services.setTemperature(network.getToken(), network.getUserId(), gateway.getGatewayId(), "20",
                ther);
        assertTrue(res);

        // fetch new status: //TODO ASK DAN!!
        // network = services.mapNorthQNetwork(user.get(0), user.get(1));
        // gateway = network.getGateways().get(0);
        // testThings = network.getGateways().get(0).getThings();
        // ther = null;
        //
        // for (int i = 0; i < testThings.size(); i++) {
        // if (testThings.get(i) instanceof Qthermostat) {
        // ther = (Qthermostat) testThings.get(i);
        // }
        // }
        //
        // if (ther == null) {
        // fail("no thermostat was detected in network!");
        // }

    }
    // @Test
    // public void triggerTest() {
    // UserNotificationHolder mock = new UserNotificationHolder();
    // UserNotification mockNot = new UserNotification();
    // mockNot.notification = new Notification();
    // mockNot.notification.timestamp = System.currentTimeMillis() / 1000;
    // mock.UserNotifications = new ArrayList<UserNotification>();
    // mock.UserNotifications.add(mockNot);
    // assertTrue(services.isTriggered(mock));
    //
    // mockNot.notification.timestamp = 20000;
    // mock.UserNotifications.remove(0);
    // mock.UserNotifications.add(mockNot);
    // assertTrue(!services.isTriggered(mock));
    // }

    @Test
    public void isTriggeredTest() {
        // Test True:
        UserNotificationHolder un = new UserNotificationHolder();
        un.UserNotifications = new ArrayList<UserNotification>();
        UserNotification n = new UserNotification();
        n.notification = new Notification();
        n.notification.timestamp = System.currentTimeMillis() * 1000;
        un.UserNotifications.add(n);
        assertTrue(services.isTriggered(un));
        // Test false:
        un.UserNotifications.remove(0);
        n.notification.timestamp = (1000000);
        un.UserNotifications.add(n);
        assertTrue(!services.isTriggered(un));

    }
}
