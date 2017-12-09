/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.northq.internal.mock;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatusInfo;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerCallback;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;

/**
 * The {@link MockCallback} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jakob / Philip - Initial contribution.
 */

public class MockCallback implements ThingHandlerCallback {

    @Override
    public void stateUpdated(@NonNull ChannelUID channelUID, @NonNull State state) {

    }

    @Override
    public void postCommand(@NonNull ChannelUID channelUID, Command command) {

    }

    @Override
    public void statusUpdated(@NonNull Thing thing, @NonNull ThingStatusInfo thingStatus) {

    }

    @Override
    public void thingUpdated(@NonNull Thing thing) {

    }

    @Override
    public void configurationUpdated(@NonNull Thing thing) {

    }

    @Override
    public void migrateThingType(@NonNull Thing thing, @NonNull ThingTypeUID thingTypeUID,
            Configuration configuration) {

    }

    @Override
    public void channelTriggered(@NonNull Thing thing, @NonNull ChannelUID channelUID, String event) {

    }

}
