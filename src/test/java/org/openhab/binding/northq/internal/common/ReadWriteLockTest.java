/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.northq.internal.common;

import org.junit.Test;
import org.openhab.binding.northq.discovery.DiscoveryTest;

/**
 * The {@link DiscoveryTest} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Dan - Initial contribution.
 */

public class ReadWriteLockTest {
    /**
     * Description: Test the readWriteLock service for runtime errors
     * Input:
     * Expected result: No runtime Errors
     */

    @Test
    public void readWriteLockTest() {
        // Checking that concurrent locks and Read/Writes are functioning
        ReadWriteLock.getInstance().lockWrite();
        ReadWriteLock.getInstance().unlockWrite();
        ReadWriteLock.getInstance().lockRead();
        ReadWriteLock.getInstance().unlockRead();

    }
}
