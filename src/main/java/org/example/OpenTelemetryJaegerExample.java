package org.example;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;

public class OpenTelemetryJaegerExample {
    public static void main(String[] args) {
        // 1. 設置 Jaeger Exporter
        JaegerGrpcSpanExporter jaegerExporter = JaegerGrpcSpanExporter.builder()
                .setEndpoint("http://localhost:14250") // 替換為您的 Jaeger gRPC endpoint
                .build();

        // 2. 配置 Span Processor
        BatchSpanProcessor spanProcessor = BatchSpanProcessor.builder(jaegerExporter).build();

        // 3. 創建 Tracer Provider
        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(spanProcessor)
                .build();

        // 4. 設置 Global OpenTelemetry
        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .buildAndRegisterGlobal();

        // 5. 使用 Tracer
        Tracer tracer = GlobalOpenTelemetry.getTracer("example-tracer");

        Span span = tracer.spanBuilder("Tracer-span")
                .startSpan();
        span.setAttribute("example-attribute", "value");
        span.setAttribute("service.name", "my-test-tracer");
        span.addEvent("example-event");
        span.end();

        // 關閉 Tracer Provider 以確保數據發送到 Jaeger
        tracerProvider.close();
    }
}
