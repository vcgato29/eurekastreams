/*
 * Copyright (c) 2010 Lockheed Martin Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eurekastreams.server.action.request.opensocial;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.gadgets.oauth.OAuthStore.ConsumerInfo;

/**
 * Request object that contains the parameters for calling the RemoveConsumerTokenExecution class.
 */
public class RemoveConsumerTokenRequest extends OAuthConsumerTokenRequest
{
    /**
     * Serialization id.
     */
    private static final long serialVersionUID = 6633642115686057467L;

    /**
     * Constructor.
     * 
     * @param inSecurityToken
     *            security token for this request.
     * @param inConsumerInfo
     *            consumer info for this request.
     * @param inServiceName
     *            service name for this request.
     * @param inTokenName
     *            token name for this request.
     */
    public RemoveConsumerTokenRequest(final SecurityToken inSecurityToken, final ConsumerInfo inConsumerInfo,
            final String inServiceName, final String inTokenName)
    {
        super(inSecurityToken, inConsumerInfo, inServiceName, inTokenName);
    }

    /**
     * Empty constructor for serialization.
     */
    protected RemoveConsumerTokenRequest()
    {
    }
}
