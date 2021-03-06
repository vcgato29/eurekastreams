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
package org.eurekastreams.server.persistence.mappers;

import org.eurekastreams.commons.model.DomainEntity;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;

/**
 * Mapper to index entity.
 * 
 * @param <T>
 *            The entity to index.
 */
public class IndexEntity<T extends DomainEntity> extends BaseArgDomainMapper<T, Boolean>
{
    /**
     * Reindex entity into search index.
     * 
     * @param inRequest
     *            The entity to index.
     * @return True if successful.
     */
    @Override
    public Boolean execute(final T inRequest)
    {
        getFullTextSession().index(inRequest);
        return Boolean.TRUE;
    }

    /**
     * Get the FullTextSession.
     * 
     * Protected to override in a child class just for unit testing - this wraps the unavoidable singleton call to
     * Search.getFullTextSession.
     * 
     * It's not safe to keep a FullTextSession around in a singleton object across requests - the connection will be
     * closed.
     * 
     * @return the FullTextSession
     */
    protected FullTextSession getFullTextSession()
    {
        return org.hibernate.search.Search.getFullTextSession((Session) getEntityManager().getDelegate());
    }

}
