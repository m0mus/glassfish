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

package com.sun.ejb.devtest.client;

import java.io.*;
import java.util.*;
import javax.naming.*;
import javax.ejb.EJB;
import com.sun.ejb.devtest.Sless;
import com.sun.ejb.devtest.Sless30;
import com.sun.ejte.ccl.reporter.SimpleReporterAdapter;

@EJB(name="ejb/GG", beanInterface=Sless.class)
public class Client {

    private static SimpleReporterAdapter stat = 
        new SimpleReporterAdapter("appserv-tests");

    public static void main (String[] args) {

        stat.addDescription("ejb-ejb30-allowed-session");
        Client client = new Client(args);
        client.doTest();
        stat.printSummary("ejb-ejb30-allowed-sessionID");
    }  
    
    public Client (String[] args) {
    }
    
    public void doTest() {
        try {
            (new MyThread()).start();
            (new MyThread()).start();
            (new MyThread()).start();
            (new MyThread()).start();
            (new MyThread()).start();
            (new MyThread()).start();
            stat.addStatus("intro sayHello", stat.PASS);
	} catch (Exception ex) {
            stat.addStatus("intro sayHello", stat.FAIL);
        }

    }


    private static class MyThread extends Thread {
        MyThread() {}
        
        public void run() {
            try {
                (new InitialContext()).lookup("java:comp/env/ejb/GG");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}

