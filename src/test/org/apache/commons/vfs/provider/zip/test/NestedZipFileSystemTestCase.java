/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software License
 * version 1.1, a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.apache.commons.vfs.provider.zip.test;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.provider.zip.ZipFileSystemProvider;
import org.apache.commons.vfs.test.AbstractReadOnlyFileSystemTestCase;

/**
 * Tests for the Zip file system, using a zip file nested inside another zip file.
 *
 * @author <a href="mailto:adammurdoch@apache.org">Adam Murdoch</a>
 */
public class NestedZipFileSystemTestCase
    extends AbstractReadOnlyFileSystemTestCase
{
    public NestedZipFileSystemTestCase( String name )
    {
        super( name );
    }

    /**
     * Returns the URI for the base folder.
     */
    protected FileObject getBaseFolder() throws Exception
    {
        getManager().addProvider( "zip", new ZipFileSystemProvider() );

        // Locate the base Zip file
        final String zipFilePath = getTestResource( "nested.zip" ).getAbsolutePath();
        String uri = "zip:" + zipFilePath + "!/test.zip";
        final FileObject zipFile = getManager().resolveFile( uri );

        // Now build the nested file system
        final FileObject nestedFS = getManager().createFileSystem( "zip", zipFile );
        return nestedFS.resolveFile( "/basedir" );
    }
}
