package org.openhab.binding.northq.internal.common;

import org.junit.Test;

public class ReadWriteLockTest {
    @Test
    public void readWriteLockTest() {
        // Test no errors occur
        ReadWriteLock.getInstance().lockWrite();
        ReadWriteLock.getInstance().unlockWrite();
        ReadWriteLock.getInstance().lockRead();
        ReadWriteLock.getInstance().unlockRead();

    }
}
