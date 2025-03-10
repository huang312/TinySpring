package com.tiny.springframework.aop;

import com.tiny.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.tiny.springframework.aop.framework.Cglib2AopProxy;
import com.tiny.springframework.aop.framework.JdkDynamicAopProxy;
import com.tiny.springframework.aop.framework.ReflectiveMethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Descrpition
 * @Date 2024/10/5
 */
public class AopTest {

    @Test
    public void test_proxy_method(){
        Object targetObj = new UserService();
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                targetObj.getClass().getInterfaces(), new InvocationHandler() {
                    MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.tiny.springframework.aop.IUserService.*(..))");
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (methodMatcher.matches(method, targetObj.getClass())) {
                            MethodInterceptor methodInterceptor = invocation -> {
                                long start = System.currentTimeMillis();
                                try {
                                    return invocation.proceed();
                                } finally {
                                    System.out.println("监控 - By AOP");
                                    System.out.println("方法名称 - " + invocation.getMethod().getName());
                                    System.out.println("方法耗时 - " + (System.currentTimeMillis() - start) + "ms");
                                    System.out.println("监控 - End\r\n");
                                }
                            };
                            return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                        }
                        return method.invoke(targetObj, args);
                    }
                });
        String s = userService.queryUserInfo();
        System.out.println(s);
//        new AspectJExpressionPointcut();
    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.tiny.springframework.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));

    }

    @Test
    public void test_dynamic() {
        UserService userService = new UserService();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.tiny.springframework.aop.IUserService.*(..))"));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());

        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果： " + proxy_jdk.queryUserInfo());

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果： " + proxy_cglib.queryUserInfo());

    }
}
