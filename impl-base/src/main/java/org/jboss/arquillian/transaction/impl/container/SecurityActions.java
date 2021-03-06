/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.arquillian.transaction.impl.container;

import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Defines a set of operations that are meant to be executed within security context.
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public final class SecurityActions {

    /**
     * Creates new instance of {@link SecurityActions}.
     * <p/>
     * Private constructor prevents from instantiation outside this class.
     */
    private SecurityActions() {
        // empty constructor
    }

    /**
     * Loads the resources using the executing thread class loader.
     *
     * @param resourceName
     *     the resource name
     *
     * @return the loaded resource as stream
     */
    public static InputStream getResource(String resourceName) {

        return getThreadContextClassLoader().getResourceAsStream(resourceName);
    }

    /**
     * Retrieves current thread class loader.
     *
     * @return the class loader
     */
    private static ClassLoader getThreadContextClassLoader() {
        return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {

            public ClassLoader run() {
                return Thread.currentThread().getContextClassLoader();
            }
        });
    }
}

