package org.openhab.binding.northq.handler;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.openhab.binding.northq.internal.model.NorthNetwork;
import org.openhab.binding.northq.internal.services.CredentialsService;
import org.openhab.binding.northq.internal.services.NorthqServices;

public class NorthQPlugHandlerTest {
    NorthqServices services;
    CredentialsService credentialsServices;
    ArrayList<String> user;
    NorthNetwork network;

    @Before
    public void setUp() throws Exception {
        services = new NorthqServices();
        credentialsServices = new CredentialsService();
        user = credentialsServices.getUserCredentials();
        network = services.mapNorthQNetwork(user.get(0), user.get(1));

    }

    @Test
    public void plugOnHandlerTest() {

    }

    @Test
    public void plugOffHandlerTest() {

    }

}
