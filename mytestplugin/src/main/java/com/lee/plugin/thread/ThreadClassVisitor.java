package com.lee.plugin.thread;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author gavin
 * @date 2019/2/18
 * lifecycle class visitor
 */
public class ThreadClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;

    public ThreadClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //System.out.println("LifecycleClassVisitor : visit -----> started " + name);
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        //System.out.println("LifecycleClassVisitor : visitMethod : " + name);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        //匹配FragmentActivity
        if ("java/lang/Thread".equals(this.mClassName)) {
            System.out.println("ThreadClassVisitor : current method ----> " + name);
            if ("start".equals(name) ) {
                //处理onCreate
                System.out.println("ThreadClassVisitor : change method ----> " + name);
                return new ThreadStartMethodVisitor(mv);
            } else if ("init".equals(name)) {
                //处理onDestroy
                System.out.println("ThreadClassVisitor : change method ----> " + name);
                return new ThreadConstructorMethodVisitor(mv);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        //System.out.println("LifecycleClassVisitor : visit -----> end");
        super.visitEnd();
    }
}
