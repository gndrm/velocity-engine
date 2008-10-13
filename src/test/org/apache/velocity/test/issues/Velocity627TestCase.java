package org.apache.velocity.test.issues;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */

import org.apache.velocity.test.BaseEvalTestCase;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * This class tests VELOCITY-627.  Make sure Foreach
 * Error message reports correct line numbers.
 */

public class Velocity627TestCase extends BaseEvalTestCase
{
    public Velocity627TestCase(String name)
    {
        super(name);
    }
  
    public void setUp() throws Exception
    {
        super.setUp();
        engine.setProperty(RuntimeConstants.SKIP_INVALID_ITERATOR, false);
    }
  
    public void test627()
    {
        try
        {
            evaluate("##\n##\n#foreach($i in \"junk\")blaa#end");
        }
        catch(RuntimeException e)
        {
            // Make sure the error ouput contains "line 3" if not throw
            if (e.getMessage().indexOf("[line 3, column") == -1)
                throw e;
        }      
    }
}
