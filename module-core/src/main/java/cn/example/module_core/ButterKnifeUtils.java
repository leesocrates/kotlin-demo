package cn.example.module_core;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ButterKnifeUtils {
    static final Map<Class<?>, Constructor<? extends UnBinder>> BINDINGS = new LinkedHashMap<>();

    @NonNull
    @UiThread
    public static UnBinder bind(@NonNull Activity target) {
        View sourceView = target.getWindow().getDecorView();
        return createBinding(target, sourceView);
    }

    private static UnBinder createBinding(Activity target, View sourceView) {
        Class<?> targetClass = target.getClass();
        Constructor<? extends UnBinder> constructor = findBindingConstructorForClass(targetClass);
        if (constructor == null) {
            return UnBinder.EMPTY;
        }
        try {
            return constructor.newInstance(target, sourceView);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new RuntimeException("Unable to create binding instance.", cause);
        }
    }

    private static Constructor<? extends UnBinder> findBindingConstructorForClass(Class<?> targetClass) {
        Constructor<? extends UnBinder> constructor = BINDINGS.get(targetClass);
        if (constructor != null) {
            return constructor;
        }

        String targetClassName = targetClass.getName();
        try {
            Class<?> viewBindingClass = Class.forName(targetClassName + "_ViewBinding");
            constructor = (Constructor<? extends UnBinder>) viewBindingClass.getConstructor(targetClass, View.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        BINDINGS.put(targetClass, constructor);

        return constructor;
    }
}
