/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software License
 * version 1.1, a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.apache.commons.vfs.provider.local.test;

import java.io.File;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.test.AbstractWritableFileSystemTestCase;

/**
 * Tests for the local file system.
 *
 * @author <a href="mailto:adammurdoch@apache.org">Adam Murdoch</a>
 */
public class LocalFileSystemTestCase
    extends AbstractWritableFileSystemTestCase
{
    public LocalFileSystemTestCase( String name )
    {
        super( name );
    }

    /**
     * Returns the URI for the base folder.
     */
    protected FileObject getBaseFolder() throws Exception
    {
        final File testDir = getTestResource( "basedir" );
        final File emptyDir = new File( testDir, "emptydir" );
        emptyDir.mkdirs();
        return getManager().convert( testDir );
    }

    /**
     * Returns the URI for the area to do tests in.
     */
    protected FileObject getWriteFolder() throws Exception
    {
        final File testDir = getTestDirectory( "write-tests" );
        return getManager().convert( testDir );
    }

    /**
     * Tests resolution of an absolute file name.
     */
    public void testAbsoluteFileName() throws Exception
    {
        // Locate file by absolute file name
        String fileName = new File( "testdir" ).getAbsolutePath();
        FileObject absFile = getManager().resolveFile( fileName );

        // Locate file by URI
        String uri = "file://" + fileName.replace( File.separatorChar, '/' );
        FileObject uriFile = getManager().resolveFile( uri );

        assertSame( "file object", absFile, uriFile );
    }
}
