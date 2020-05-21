package br.com.marvel.sistema;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MarvelApplicationTests
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MarvelApplicationTests(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MarvelApplicationTests.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
