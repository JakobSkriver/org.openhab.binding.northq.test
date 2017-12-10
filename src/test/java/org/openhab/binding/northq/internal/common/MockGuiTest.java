/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.northq.internal.common;

import org.junit.Before;
import org.junit.Test;
import org.openhab.binding.northq.internal.mock.gui.MockGui;

/**
 * The {@link MockGuiTest} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jakob / Philip - Initial contribution.
 */

public class MockGuiTest {

    private MockGui gui;

    @Before
    public void setup() throws Exception {
        // NorthQConfig.setMOCK_NETWORK(new NorthQMockNetwork());
        // gui = new MockGui();
        // gui.setVisible(false);
    }

    /**
     * Description: Testing the actionlistener for the button add
     * Input: Do click button
     * Expected result:
     */

    @Test
    public void addButtonTest() {
        // gui.getAddButton().doClick();
    }

    /**
     * Description: Testing the actionlistener for the button delete
     * Input: Do click button
     * Expected result:
     */

    @Test
    public void deleteButtonTest() {
        // gui.getDeleteButton().doClick();
    }

    /**
     * Description: Testing the actionlistener for the button submit
     * Input: Do click button
     * Expected result:
     */

    @Test
    public void submitButtonTest() {
        // gui.selectOnList();
        // gui.getSubmitButton().doClick();
    }

    /**
     * Description: Testing the listSelectlistener for the overviewList
     * Input: Do click select list
     * Expected result:
     */

    @Test
    public void listSelectionTest() {
        // gui.selectOnList();
    }
}
