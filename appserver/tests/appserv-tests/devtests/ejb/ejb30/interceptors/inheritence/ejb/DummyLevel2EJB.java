/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.s1asdev.ejb.ejb30.interceptors.session;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.interceptor.AroundInvoke;
import javax.ejb.EJBException;
import javax.annotation.PostConstruct;


public class DummyLevel2EJB
	extends DummyBaseEJB
{
    protected int dummyLevel2AICount = 0;
	protected int dummyLevel2EJBPostConstructCount = 0;


	@PostConstruct
    protected void overridablePostConstruct() {
		postConstructList.add("DummyLevel2EJB");
		dummyLevel2EJBPostConstructCount++;
		System.out.println("GGGG: DummyLevel2EJB.postConstruct ");
	}

	@AroundInvoke
	protected Object overridableAroundInvoke(InvocationContext ctx)
		throws Exception {
			aroundInvokeList.add("DummyLevel2EJB");
			dummyLevel2AICount++;
			return ctx.proceed();
	}
}
