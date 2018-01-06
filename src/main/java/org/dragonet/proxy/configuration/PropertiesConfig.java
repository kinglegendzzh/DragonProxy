/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.proxy.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesConfig {

    private final Properties config;

    public PropertiesConfig(String defaultResourcePath, String fileName, boolean saveDefault) throws IOException {
        Properties defaultConfig = new Properties();
        defaultConfig.load(PropertiesConfig.class.getResourceAsStream(defaultResourcePath));
        config = new Properties(defaultConfig);
        File file = new File(fileName);
        if (file.exists()) {
            FileInputStream input = new FileInputStream(file);
            config.load(new InputStreamReader(input, Charset.forName("UTF-8")));
        } else if (saveDefault) {
            FileOutputStream fos = new FileOutputStream(fileName);
            InputStream ris = PropertiesConfig.class.getResourceAsStream(defaultResourcePath);
            int d = -1;
            while ((d = ris.read()) != -1) {
                fos.write(d);
            }
            fos.close();
            ris.close();
        }
    }

    public Properties getConfig() {
        return config;
    }
}
