package com.jimmysalazar.spring_metrics_push_gateway;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.PushGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringMetricsPushGatewayApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringMetricsPushGatewayApplication.class, args);

		PushGateway pg = new PushGateway("127.0.0.1:9091");

		CollectorRegistry registry = new CollectorRegistry();

		Counter students = Counter.build().name("students_total").help("Total students.").register(registry);
		// Proceso complejo que registra estudiantes
		students.inc(1985);

		pg.pushAdd(registry, "salazar_students_job");
	}

}
