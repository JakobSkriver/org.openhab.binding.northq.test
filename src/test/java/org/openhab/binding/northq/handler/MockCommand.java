package org.openhab.binding.northq.handler;

import org.eclipse.smarthome.core.types.Command;

public class MockCommand implements Command {
    public String command = "ON";

    @Override
    public String toFullString() {
        return command;
    }

    @Override
    public String format(String pattern) {
        return command;
    }

    @Override
    public String toString() {
        return command;
    }
}
