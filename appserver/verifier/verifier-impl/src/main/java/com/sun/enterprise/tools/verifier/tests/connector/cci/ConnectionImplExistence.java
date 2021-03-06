/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
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
 * ConnectionImplExistence.java
 *
 * Created on October 2, 2000, 10:32 AM
 */

package com.sun.enterprise.tools.verifier.tests.connector.cci;

import com.sun.enterprise.tools.verifier.tests.connector.ConnectorCheck;
import com.sun.enterprise.tools.verifier.Result;
import com.sun.enterprise.deployment.ConnectorDescriptor;
import com.sun.enterprise.tools.verifier.tests.*;

/**
 * test for the existence of the class defined in the deployment descriptors
 * connection-impl-class
 *
 * @author  Jerome Dochez
 * @version 
 */
public class ConnectionImplExistence extends ConnectionTest implements ConnectorCheck {


  /** <p>
   * test for the existence of the class defined in the deployment descriptors
   * connectionfactory-impl-class
   * </p>
   *
   * @paramm descriptor deployment descriptor for the rar file
   * @return result object containing the result of the individual test
   * performed
   */
  public Result check(ConnectorDescriptor descriptor) {

    Result result = getInitializedResult();
    ComponentNameConstructor compName = getVerifierContext().getComponentNameConstructor();
    if(isCCIImplemented(descriptor, result))
    {
      Class c = testConnectionImpl(descriptor, result);
      // failure are handled in the superclass
      if (c!=null) 
      {
        result.addGoodDetails(smh.getLocalString
            ("tests.componentNameConstructor",
             "For [ {0} ]",
             new Object[] {compName.toString()}));	
        result.passed(smh.getLocalString
            (getClass().getName() + ".passed",
             "The class define in the connection-impl-class tag [ {0} ] is contained in the archive.",
             new Object[] {c.getName()} ));        
      }
    }
    else
    {
      // test is NA
      result.addNaDetails(smh.getLocalString
          ("tests.componentNameConstructor",
           "For [ {0} ]",
           new Object[] {compName.toString()}));
      result.notApplicable(smh.getLocalString
          ("com.sun.enterprise.tools.verifier.tests.connector.cci.notApp",
           "The CCI interfaces do not seem to be implemented by this resource adapter"));
    }
    return result;        
  }
}
