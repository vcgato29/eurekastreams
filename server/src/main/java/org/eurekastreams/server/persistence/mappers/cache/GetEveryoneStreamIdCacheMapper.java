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
package org.eurekastreams.server.persistence.mappers.cache;

import org.eurekastreams.server.persistence.mappers.DomainMapper;
import org.eurekastreams.server.persistence.mappers.stream.CachedDomainMapper;

/**
 * Get the everyone stream ID.
 *
 */
public class GetEveryoneStreamIdCacheMapper extends CachedDomainMapper implements DomainMapper<Object, Long>
{

    /**
     * Get the everyone stream ID.
     * @param inRequest nothing.
     * @return the everyone stream ID.
     */
    @Override
    public Long execute(final Object inRequest)
    {
        return (Long) getCache().get(CacheKeys.CORE_STREAMVIEW_ID_EVERYONE);
    }

}
