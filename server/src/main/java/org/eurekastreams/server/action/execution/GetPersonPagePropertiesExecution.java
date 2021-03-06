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
package org.eurekastreams.server.action.execution;

import org.apache.commons.logging.Log;
import org.eurekastreams.commons.actions.ExecutionStrategy;
import org.eurekastreams.commons.actions.context.PrincipalActionContext;
import org.eurekastreams.commons.logging.LogFactory;
import org.eurekastreams.server.persistence.mappers.DomainMapper;
import org.eurekastreams.server.persistence.mappers.cache.Transformer;
import org.eurekastreams.server.search.modelview.PersonPagePropertiesDTO;

/**
 * Returns {@link PersonPagePropertiesDTO} for current user.
 * 
 */
public class GetPersonPagePropertiesExecution implements ExecutionStrategy<PrincipalActionContext>
{
    /**
     * Logger.
     */
    private Log log = LogFactory.make();

    /**
     * PersonPagePropertiesByIdMapper.
     */
    private DomainMapper<Long, PersonPagePropertiesDTO> personPagePropertiesByIdMapper;

    /**
     * Transformer for converting PersonPagePropertiesDTO to theme css url.
     */
    private Transformer<PersonPagePropertiesDTO, String> personPagePropertiesDTOToCssUrlTransformer;

    /**
     * Constructor.
     * 
     * @param inPersonPagePropertiesByIdMapper
     *            PersonPagePropertiesByIdMapper.
     * @param inThemeCssUrlTransfomer
     *            Transformer for converting PersonPagePropertiesDTO to theme css url.
     */
    public GetPersonPagePropertiesExecution(
            final DomainMapper<Long, PersonPagePropertiesDTO> inPersonPagePropertiesByIdMapper,
            final Transformer<PersonPagePropertiesDTO, String> inThemeCssUrlTransfomer)
    {
        personPagePropertiesByIdMapper = inPersonPagePropertiesByIdMapper;
        personPagePropertiesDTOToCssUrlTransformer = inThemeCssUrlTransfomer;
    }

    /**
     * Return PersonPageProptertiesDTO for current user.
     * 
     * @param inActionContext
     *            {@link PrincipalActionContext}.
     * @return {@link PersonPagePropertiesDTO} for current user.
     */
    @Override
    public PersonPagePropertiesDTO execute(final PrincipalActionContext inActionContext)
    {
        long start = System.currentTimeMillis();
        PersonPagePropertiesDTO result = personPagePropertiesByIdMapper.execute(inActionContext.getPrincipal().getId());

        long startCssPathGen = System.currentTimeMillis();
        result.setThemeCssFile(personPagePropertiesDTOToCssUrlTransformer.transform(result));
        long end = System.currentTimeMillis();

        log.debug("Set theme css path time: " + (end - startCssPathGen) + "(ms)");
        log.debug("Get personPageProperties from DB/Cache: " + (end - start) + "(ms)");

        return result;
    }
}
