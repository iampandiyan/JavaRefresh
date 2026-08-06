package SESSION4_DESIGNPATTERN.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransactionalInvocationHandler implements InvocationHandler {
    private final Object target;
    TransactionalInvocationHandler(Object target){
        this.target = target;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         System.out.println("BEGIN TX");
         try{
            Object r = method.invoke(target, args); 
            System.out.println("COMMIT"); 
            return r;
         } catch (Exception e) {
            System.out.println("ROLLBACK");
            throw e;
         }
    }

}
