package org.openhab.binding.northq.internal.common;

import org.junit.Test;

public class ReadWriteLockTest {
    @Test
    public void readWriteLockTest() {
        // Checking that concurrent locks and Read/Writes are functioning
        ReadWriteLock.getInstance().lockWrite();
        ReadWriteLock.getInstance().unlockWrite();
        ReadWriteLock.getInstance().lockRead();
        ReadWriteLock.getInstance().unlockRead();

    }
}
