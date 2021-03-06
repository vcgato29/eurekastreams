/*
 * Copyright (c) 2010-2012 Lockheed Martin Corporation
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
package org.eurekastreams.web.client.ui.connect.support;

import java.util.HashMap;
import java.util.Map;

import org.eurekastreams.web.client.events.EventBus;
import org.eurekastreams.web.client.events.Observer;
import org.eurekastreams.web.client.events.UpdateHistoryEvent;
import org.eurekastreams.web.client.events.UpdatedHistoryParametersEvent;
import org.eurekastreams.web.client.history.CreateUrlRequest;
import org.eurekastreams.web.client.ui.Session;
import org.eurekastreams.web.client.ui.pages.master.StaticResourceBundle;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Stream sort panel.
 */
public class StreamSortPanel extends Composite
{
    /** The sort type. */
    private String sort = "date";

    /** The active sort. */
    private Anchor activeSort = null;

    /** Map of the links to the sorts. */
    final Map<String, Anchor> linkMap = new HashMap<String, Anchor>();

    /**
     * Constructor.
     */
    public StreamSortPanel()
    {
        FlowPanel options = new FlowPanel();
        options.addStyleName(StaticResourceBundle.INSTANCE.coreCss().options());

        initWidget(options);

        Label sortLabel = new Label("Sort:");
        sortLabel.addStyleName(StaticResourceBundle.INSTANCE.coreCss().gwtLabel());
        options.add(sortLabel);

        final Anchor dateSort = new Anchor("Recent");
        dateSort.setTitle("Sorted by activity post date.");
        dateSort.addStyleName(StaticResourceBundle.INSTANCE.coreCss().sortOption());
        options.add(dateSort);
        linkMap.put("date", dateSort);

        final Anchor interestingSort = new Anchor("Popular");
        interestingSort.addStyleName(StaticResourceBundle.INSTANCE.coreCss().sortOption());
        options.add(interestingSort);
        linkMap.put("interesting", interestingSort);

        final Anchor commentSort = new Anchor("Active");
        commentSort.setTitle("Sorted by last comment date");
        commentSort.addStyleName(StaticResourceBundle.INSTANCE.coreCss().sortOption());
        options.add(commentSort);
        linkMap.put("commentdate", commentSort);

        EventBus.getInstance().addObserver(UpdatedHistoryParametersEvent.class,
                new Observer<UpdatedHistoryParametersEvent>()
                {
                    public void update(final UpdatedHistoryParametersEvent event)
                    {
                        if (event.getParameters().containsKey("sort"))
                        {
                            sort = event.getParameters().get("sort");
                        }
                        else
                        {
                            sort = "date";
                        }

                        updateSelected(sort, false);
                    }
                }, true);

        dateSort.addClickHandler(new ClickHandler()
        {
            public void onClick(final ClickEvent arg0)
            {
                updateSelected("date", true);
            }
        });

        interestingSort.addClickHandler(new ClickHandler()
        {
            public void onClick(final ClickEvent arg0)
            {
                updateSelected("interesting", true);
            }
        });

        commentSort.addClickHandler(new ClickHandler()
        {
            public void onClick(final ClickEvent arg0)
            {
                updateSelected("commentdate", true);
            }
        });
    }

    /**
     * Update the selected sort.
     *
     * @param updatedSort
     *            the new sort.
     * @param setHistory
     *            if the history should be set.
     */
    public void updateSelected(final String updatedSort, final boolean setHistory)
    {
        sort = updatedSort;
        if (null != activeSort)
        {
            activeSort.removeStyleName(StaticResourceBundle.INSTANCE.coreCss().active());
        }

        activeSort = linkMap.get(sort);
        activeSort.addStyleName(StaticResourceBundle.INSTANCE.coreCss().active());

        if (setHistory)
        {
            Session.getInstance().getEventBus().notifyObservers(
                    new UpdateHistoryEvent(new CreateUrlRequest("sort", String.valueOf(sort), false)));
        }
    }

    /**
     * Get the sort.
     *
     * @return the sort.
     */
    public String getSort()
    {
        return sort;
    }
}
