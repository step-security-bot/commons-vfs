/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software License
 * version 1.1, a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.apache.commons.vfs.provider.zip.test;

import java.io.File;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.provider.zip.ZipFileSystemProvider;
import org.apache.commons.vfs.test.AbstractReadOnlyFileSystemTestCase;

/**
 * Tests for the Zip file system.
 *
 * @author <a href="mailto:adammurdoch@apache.org">Adam Murdoch</a>
 */
public class ZipFileSystemTestCase extends AbstractReadOnlyFileSystemTestCase
{
    public ZipFileSystemTestCase( String name )
    {
        super( name );
    }

    /**
     * Returns the URI for the base folder.
     */
    protected FileObject getBaseFolder() throws Exception
    {
        File zipFile = getTestResource( "test.zip" );
        String uri = "zip:" + zipFile.getAbsolutePath() + "!basedir";
        getManager().addProvider( "zip", new ZipFileSystemProvider() );
        return getManager().resolveFile( uri );
    }
}
