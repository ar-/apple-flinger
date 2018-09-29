/*******************************************************************************
 * Copyright (C) 2015-2018 Andreas Redmer <ar-appleflinger@abga.be>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.gitlab.ardash.appleflinger.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class GdxFileControl extends Control {  
    
    private String encoding;  
    private FileType fileType;  
  
    public GdxFileControl(String encoding, FileType fileType) {  
        this.encoding = encoding;  
        this.fileType = fileType;  
    }  
      
    @Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)   
            throws IllegalAccessException, InstantiationException, IOException {  
        // The below is a copy of the default implementation.  
        String bundleName = toBundleName(baseName, locale);  
        String resourceName = toResourceName(bundleName, "properties");  
        ResourceBundle bundle = null;  
        FileHandle fileHandle = Gdx.files.getFileHandle(resourceName, fileType);  
        if (fileHandle.exists()) {  
            InputStream stream = null;  
            try {  
                stream = fileHandle.read();  
                // Only this line is changed to make it to read properties files as UTF-8.  
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, encoding));  
            } finally {  
                if (stream != null)  
                    stream.close();  
            }  
        }  
        return bundle;  
    }  
}  