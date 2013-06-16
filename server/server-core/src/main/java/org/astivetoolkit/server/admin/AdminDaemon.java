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
package org.astivetoolkit.server.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import org.apache.log4j.Logger;
import org.astivetoolkit.AstiveException;
import org.astivetoolkit.server.AbstractAstiveServer;
import org.astivetoolkit.server.SystemException;
import org.astivetoolkit.server.appmanager.Deployer;
import org.astivetoolkit.server.appmanager.DeployerManager;
import org.astivetoolkit.server.monitor.ConnectionMonitor;
import org.astivetoolkit.server.security.AstPolicy;
import org.astivetoolkit.server.security.AstPolicyUtil;
import org.astivetoolkit.util.AppLocale;
import org.astivetoolkit.util.NetUtil;

/**
 * Provide remote access to the (@link DeployerManager) functionalities.
 *
 * @since 1.0.0
 * @see ConnectionMonitor
 * @see DeployerManager
 */
public class AdminDaemon extends ServerSocket implements Deployer, Runnable {
  private static final Logger LOG = Logger.getLogger(AdminDaemon.class);
  private AbstractAstiveServer server;
  private InetAddress bindAddr;
  private int backlog;
  private int port;

  public AdminDaemon(int port, int backlog, InetAddress bindAddr, AbstractAstiveServer server)
              throws IOException {
    super();
    this.port = port;
    this.backlog = backlog;
    this.bindAddr = bindAddr;
    this.server = server;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deploy(String app) throws AstiveException {
    DeployerManager.getInstance().deploy(app);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    try {
      if (!NetUtil.isPortAvailable(port)) {
        throw new RuntimeException(AppLocale.getI18n("unableToOpenPort", new Object[] { port }));
      }

      InetSocketAddress inet = new InetSocketAddress(bindAddr, port);
      bind(inet, backlog);

      while (true) {
        Socket client = accept();

        StringBuilder sbr = new StringBuilder();
        sbr.append(client.getInetAddress().getHostAddress());
        sbr.append(":");
        sbr.append(port);

        SocketPermission sp = new SocketPermission(sbr.toString(), AstPolicy.DEFAULT_ACTION);

        if (!AstPolicyUtil.hasPermission(sp)) {
          client.close();

          continue;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String commandStr = reader.readLine();

        AdminCommand command = AdminCommand.valueOf(commandStr);
        String arg = null;

        if (command.equals(AdminCommand.DEPLOY) || command.equals(AdminCommand.UNDEPLOY)) {
          arg = reader.readLine();
        }

        if (command.equals(AdminCommand.DEPLOY)) {
          deploy(arg);

          continue;
        }

        if (command.equals(AdminCommand.UNDEPLOY)) {
          undeploy(arg);

          continue;
        }

        if (command.equals(AdminCommand.STOP)) {
          try {
            server.stop();
          } catch (SystemException ex) {
            LOG.error(AppLocale.getI18n("unexpectedError", new Object[] { ex.getMessage() }));
          }

          break;
        }

        if (command.equals(AdminCommand.HELP)) {
          // XXX: no-yet-implemented
          break;
        }

        if (command.equals(AdminCommand.VERSION)) {
          // XXX: no-yet-implemented
          break;
        }

        client.close();
      }
    } catch (IOException ex) {
      LOG.error(AppLocale.getI18n("unableToPerformIOWithAdminDaemon",
                                  new Object[] { ex.getMessage() }));
    } catch (AstiveException ex) {
      LOG.error(AppLocale.getI18n("unexpectedError", new Object[] { ex.getMessage() }));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void undeploy(String app) throws AstiveException {
    DeployerManager.getInstance().undeploy(app);
  }
}
