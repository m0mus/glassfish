/*
 * Copyright (c) 2017, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.glassfish.devtest.ejb31.singleton.servlet;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Mahesh Kannan
 */
@Singleton
@Startup
@Local(LocalInitTracker.class)
@Remote(RemoteInitTracker.class)
public class InitOrderTrackerBean {

    private List<String> initOrder = new ArrayList<String>();

    @PostConstruct
    public void afterInit() {
        add(this.getClass().getName());
    }

    public List getInitializedNames() {
        return initOrder;
    }

    public void add(String name) {
        initOrder.add(name);
    }
}
