/*
 * Copyright (C) 2005-2013 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.repo.cache;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base registry implementation 
 * (Should be genericised somewhere..)
 * 
 * @author Andy
 */
public class DefaultAsynchronouslyRefreshedCacheRegistry implements AsynchronouslyRefreshedCacheRegistry
{
    private static Log logger = LogFactory.getLog(DefaultAsynchronouslyRefreshedCacheRegistry.class);
    
    private List<RefreshableCacheListener> listeners = new LinkedList<RefreshableCacheListener>();

    /*
     * (non-Javadoc)
     * @see org.alfresco.repo.cache.AsynchronouslyRefreshedCacheRegistry#register(org.alfresco.repo.cache.
     * RefreshableCacheListener)
     */
    @Override
    public void register(RefreshableCacheListener listener)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("Listener added for "+listener.getCacheId());
        }
        listeners.add(listener);
    }

    public void broadcastEvent(RefreshableCacheEvent event, boolean toAll)
    {
        // If the system is up and running, broadcast the event immediately

        for (RefreshableCacheListener listener : this.listeners)
        {
            if (toAll)
            {
                if(logger.isDebugEnabled())
                {
                    logger.debug("Delivering to "+listener);
                }
                listener.onRefreshableCacheEvent(event);
            }
            else
            {
                if (listener.getCacheId().equals(event.getCacheId()))
                {
                    if(logger.isDebugEnabled())
                    {
                        logger.debug("Specific Delivery to "+listener);
                    }
                    listener.onRefreshableCacheEvent(event);
                }
            }
        }

    }

}