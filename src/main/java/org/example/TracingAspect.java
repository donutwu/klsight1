package org.example;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {

    private final Tracer tracer;

    public TracingAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Around("@annotation(org.example.Traceable)") // 自定義的 Traceable 註解
    public Object traceMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        // 建立 Span，名稱為方法的簽名名稱
        String spanName = joinPoint.getSignature().toShortString();
        Span span = tracer.spanBuilder(spanName).startSpan();

        try (Scope scope = span.makeCurrent()) {
            // 記錄額外屬性（可選）
            span.setAttribute("class", joinPoint.getTarget().getClass().getSimpleName());
            span.setAttribute("method", joinPoint.getSignature().getName());
            span.addEvent("addAOP");
            // 執行目標方法
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            // 記錄異常
            span.recordException(throwable);
            throw throwable;
        } finally {
            // 結束 Span
            span.end();
        }
    }
}
