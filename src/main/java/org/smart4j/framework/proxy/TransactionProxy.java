package org.smart4j.framework.proxy;

import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;
import org.smart4j.framework.util.Logger;

import java.lang.reflect.Method;

@Aspect(Transaction.class)
public class TransactionProxy extends AspectProxy {
    private static final ThreadLocal<Boolean> FLAG_HOLDER = ThreadLocal.withInitial(() -> false);

    @Override
    public boolean intercept(Class<?> cls, Method method, Object[] params) {
        return !FLAG_HOLDER.get() && method.isAnnotationPresent(Transaction.class);
    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        FLAG_HOLDER.set(true);
        DatabaseHelper.beginTransaction();
        Logger.info(this, "begin transaction");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        DatabaseHelper.commitTransaction();
        Logger.info(this, "commit transaction");
    }

    @Override
    public void error(Class<?> cls, Method method, Object[] params, Exception e) {
        DatabaseHelper.rollbackTransaction();
        Logger.error(this, "rollback transaction", e);
    }

    @Override
    public void end() {
        FLAG_HOLDER.remove();
    }
}
