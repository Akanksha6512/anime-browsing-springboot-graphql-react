package com.product.productreviewservice;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Paths;

@SpringBootApplication
public class ProductReviewServiceApplication {


	@Value("${spring.data.cassandra.cloud.secure-connect-bundle}")
	private String secureConnectBundlePath;

	@Value("${astra.db.keyspace}")
	private String astraKeySpace;

	@Value("${astra.db.application.token}")
	private String astraToken;

	public static void main(String[] args) {
		SpringApplication.run(ProductReviewServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		// Initialize the Java driver
		CqlSessionBuilder builder = CqlSession.builder();
		builder.withCloudSecureConnectBundle(Paths.get(secureConnectBundlePath));
		builder.withAuthCredentials("token", astraToken);
		builder.withKeyspace(astraKeySpace);

		try (CqlSession session = builder.build()) {
			// ...
			// Select the release_version from the system.local table:
			ResultSet rs = session.execute("select * from review_keyspace.reviews");
			Row row = rs.one();
			//Print the results of the CQL query to the console:
			if (row != null) {
				System.out.println(row.getString("comment"));
			} else {
				System.out.println("An error occurred.");
			}
		}
		return args -> {
			System.out.println("Secure Connect Bundle Path: " + secureConnectBundlePath);
		};
	}

}
