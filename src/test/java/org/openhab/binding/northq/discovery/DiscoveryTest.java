/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.northq.discovery;

import org.junit.Test;
import org.openhab.binding.northq.internal.common.NorthQConfig;
import org.openhab.binding.northq.internal.discovery.NorthQDiscoveryService;
import org.openhab.binding.northq.internal.mock.NorthQMockNetwork;
import org.openhab.binding.northq.internal.model.NorthNetwork;

/**
 * The {@link DiscoveryTest} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Dan - Initial contribution.
 */

public class DiscoveryTest {

    /**
     * Description: Test the discovery service for runtime errors
     * Input:
     * Expected result: No runtime Errors
     */

    @Test
    public void discoveryTest() {

        NorthQDiscoveryService ds = new NorthQDiscoveryService();
        // Setting up the network to a mock network
        NorthQMockNetwork mocknetwork = new NorthQMockNetwork();
        NorthNetwork network = mocknetwork.getNetwork();
        NorthQConfig.setNETWORK(network);
        // Scan all devices for the network
        ds.discoverAlldevices(network);

    }

}
