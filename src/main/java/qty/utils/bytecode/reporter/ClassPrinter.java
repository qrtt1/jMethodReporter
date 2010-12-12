package qty.utils.bytecode.reporter;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassPrinter implements ClassVisitor {
    
    String clazz;

    @Override
    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        this.clazz = name;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    @Override
    public void visitAttribute(Attribute attr) {
    }

    @Override
    public void visitEnd() {
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc,
            String signature, Object value) {
        return null;
    }

    @Override
    public void visitInnerClass(String name, String outerName,
            String innerName, int access) {
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
            String signature, String[] exceptions) {
        return new MethodCallRecorder(clazz, name);
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
    }

    @Override
    public void visitSource(String source, String debug) {
    }

    public static void main(String[] args) throws IOException {
        InputStream in = App.class.getResourceAsStream("App.class");
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader(in);
        cr.accept(cp, 0);
        in.close();
    }

}
