package org.apache.maven.wagon.providers.http;

/*
 * Copyright 2001-2004 The Apache Software Foundation.
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

import org.apache.maven.wagon.ConnectionException;
import org.apache.maven.wagon.ResourceDoesNotExistException;
import org.apache.maven.wagon.StreamWagon;
import org.apache.maven.wagon.TransferFailedException;
import org.apache.maven.wagon.authentication.AuthenticationException;
import org.apache.maven.wagon.repository.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author <a href="michal.maczka@dimatics.com">Michal Maczka</a>
 * @version $Id$
 */
public class LightweightHttpWagon extends StreamWagon
{

    public InputStream getInputStream( String resource ) throws TransferFailedException, ResourceDoesNotExistException
    {
        Repository repository = getRepository();

        String repositoryUrl = repository.getUrl();

        try
        {
            URL url;

            if ( repositoryUrl.endsWith( "/" ) )
            {
                url = new URL( repositoryUrl + resource );
            }
            else
            {
                url = new URL( repositoryUrl + "/" + resource );
            }

            return url.openStream();
        }
        catch ( MalformedURLException e )
        {
            throw new ResourceDoesNotExistException( e.getMessage() );

        }
        catch( FileNotFoundException e )
        {
           throw new ResourceDoesNotExistException( e.getMessage() );
        }
        catch ( IOException e )
        {

            throw new TransferFailedException( e.getMessage() );
        }


    }

    public OutputStream getOutputStream( String resource ) throws TransferFailedException
    {
        throw new  UnsupportedOperationException( "PUT operation is not supported by Light Weight  HTTP wagon" );
    }

    public void openConnection() throws ConnectionException, AuthenticationException
    {

    }

    public void closeConnection() throws ConnectionException
    {

    }
}
