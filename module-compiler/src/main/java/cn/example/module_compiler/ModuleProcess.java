package cn.example.module_compiler;

//import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import cn.example.module_annotation.Modules;

//@AutoService(Processor.class) //自动注册
@SupportedAnnotationTypes("cn.example.module_annotation.Modules") //指名要处理的注解
@SupportedSourceVersion(SourceVersion.RELEASE_8) //指名支持的java版本
public class ModuleProcess extends AbstractProcessor {

    private Messager messager;
    private Filer filer;
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        elementUtils = processingEnvironment.getElementUtils();
        messager.printMessage(Diagnostic.Kind.NOTE, "=====ModuleProcess init finished=====");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.NOTE, "===== ModuleProcess process start==== ");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Modules.class);
        for(Element element: elements){
            TypeElement typeElement = (TypeElement) element;

            String qualifiedName = typeElement.getQualifiedName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, "==== ModuleProcess process qualifiedName "+qualifiedName);
        }
        return false;
    }
}
