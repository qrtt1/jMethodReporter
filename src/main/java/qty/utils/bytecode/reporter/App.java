package qty.utils.bytecode.reporter;

import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassReader;

public class App {
    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            String path = args[0];
            FileInputStream in = null;
            try {
                in = new FileInputStream(path);
                ClassPrinter cp = new ClassPrinter();
                ClassReader cr = new ClassReader(in);
                cr.accept(cp, 0);
                in.close();
            } catch (Exception e) {
            } finally {
                IOUtils.closeQuietly(in);
            }
        }
        System.exit(0);
    }
}
