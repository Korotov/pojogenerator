package by.mvn;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void translitTest(){
        final String str  = " а если счастье слово";
        String strTranslit = Translit.translit(str);
        String fileName = Translit.makeAlias(strTranslit);
        System.out.println(fileName);
    }
}
