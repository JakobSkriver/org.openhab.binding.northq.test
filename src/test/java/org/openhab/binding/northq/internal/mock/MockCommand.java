/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.northq.internal.mock;

import org.eclipse.smarthome.core.types.Command;

/**
 * The {@link MockCommand} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jakob / Philip - Initial contribution.
 */

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
