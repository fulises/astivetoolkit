/*
 * Copyright (C) 2010-2013 by PhonyTive LLC (http://phonytive.com)
 * http://astivetoolkit.org
 *
 * This file is part of Astive Toolkit(ATK)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.astivetoolkit.server;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.astivetoolkit.astivlet.Astivlet;
import org.astivetoolkit.server.monitor.SimpleConnectionMonitor;

/**
 * SimpleAstiveServer is a convenient implementation of {@link AbstractAstiveServer}. 
 * The SimpleAstiveServer is useful for testing purposes, and is not recommended 
 * for production enviroments.
 *
 * @since 1.0.0
 * @see AbstractAstiveServer
 * @see AstiveServer
 */
public class SimpleAstiveServer extends AbstractAstiveServer {

    private Astivlet astivlet;
    private ExecutorService executorService;
    private int port;
    private int backlog;
    private InetAddress bindAddr;

   /**
    * Creates a new SimpleAstiveServer object with {@link Astivlet} as only parameter.
    * 
    * @param astivlet the entry point to the application. 
    */
    public SimpleAstiveServer(Astivlet astivlet) throws SystemException, IOException {
        super();
        this.port = DEFAULT_AGI_SERVER_PORT;
        this.astivlet = astivlet;
    }

    /**
     * Creates a new SimpleAstiveServer object with the specified port.
     * 
     * @param astivlet the entry point to the application.
     * @param port must be positive integer from 1 to 65535.
     */
    public SimpleAstiveServer(Astivlet astivlet, int port)
            throws SystemException, IOException {
        super(port);
        this.port = port;
        this.astivlet = astivlet;
    }

    /**
     *  Creates a new SimpleAstiveServer object with the specified port and listen backlog.
     * 
     *  @param astivlet the entry point to the application.
     *  @param port must be positive integer from 1 to 65535.
     *  @param backlog requested maximum length of the queue of incoming connections.
     */
    public SimpleAstiveServer(Astivlet astivlet, int port, int backlog) throws SystemException, IOException {
        super(port, backlog);
        this.port = port;
        this.backlog = backlog;
        this.astivlet = astivlet;
    }

    /**
     * Creates a new SimpleAstiveServer object with the specified port, listen backlog, and 
     * local IP address to bind to.
     * 
     * @param astivlet the entry point to the application.
     * @param port must be positive integer from 1 to 65535.
     * @param backlog requested maximum length of the queue of incoming connections.
     * @param bindAddr the local InetAddress the server will bind to.
     */
    public SimpleAstiveServer(Astivlet astivlet, int port, int backlog, InetAddress bindAddr)
            throws SystemException, IOException {
        super(port, backlog, bindAddr);
        this.port = port;
        this.backlog = backlog;
        this.bindAddr = bindAddr;
        this.astivlet = astivlet;
    }

    /**
     * Use to overwrite the Astivlet served by the SimpleAstiveServer.
     * 
     * @param astivlet the entry point the application.
     */ 
    public void setAstivlet(Astivlet astivlet) {
        this.astivlet = astivlet;
    }

    /**
     * Provide with access to the Astivlet served by the SimpleAstiveServer.
     * 
     * @return the entry point to the application.
     */
    public Astivlet getAstivlet() {
        return astivlet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPort() {
        return port;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBacklog() {
        return backlog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InetAddress getBindAddr() {
        return bindAddr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void launchConnectionMonitor() {
        SimpleConnectionMonitor monitor = new SimpleConnectionMonitor(this, astivlet);
        monitor.run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() throws SystemException {
        super.start();
        executorService = Executors.newSingleThreadExecutor();
        launchConnectionMonitor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() throws SystemException {
        executorService.shutdown();
        super.stop();
    }
}
