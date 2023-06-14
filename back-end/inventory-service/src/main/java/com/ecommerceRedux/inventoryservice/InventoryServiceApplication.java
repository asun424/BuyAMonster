package com.ecommerceRedux.inventoryservice;

import com.ecommerceRedux.inventoryservice.config.WebClientConfig;
import com.ecommerceRedux.inventoryservice.dto.SeedRequest;
import com.ecommerceRedux.inventoryservice.model.InventoryItem;
import com.ecommerceRedux.inventoryservice.repository.InventoryRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import com.ecommerceRedux.inventoryservice.seed.SeedPackage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceApplication {

	private final WebClient.Builder webClient;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			WebClient client = WebClient.create("https://botw-compendium.herokuapp.com/api/v2/category/monsters");

			SeedPackage seedPackage = client.get()
					.uri("https://botw-compendium.herokuapp.com/api/v2/category/monsters")
					.retrieve()
					.bodyToMono(SeedPackage.class)
					.block();

			Random rd = new Random();

			for(int i = 0; i < seedPackage.getData().size(); ++i){

				int randomInt = rd.nextInt(100);
				float randomFloat = rd.nextFloat();

				BigDecimal price = new BigDecimal(randomInt * randomFloat).setScale(2, RoundingMode.HALF_UP);

				SeedRequest seedRequest = SeedRequest.builder()
						.name(seedPackage.getData().get(i).getName())
						.description(seedPackage.getData().get(i).getDescription())
						.price(price)
						.image(seedPackage.getData().get(i).getImage())
						.common_locations(seedPackage.getData().get(i).getCommon_locations())
						.drops(seedPackage.getData().get(i).getDrops())
						.build();

				String productId = webClient.build().post()
						.uri("http://product-service/api/product")
						.bodyValue(seedRequest)
						.retrieve()
						.bodyToMono(String.class)
						.block();

				InventoryItem inventoryItem	= InventoryItem.builder()
						.productId(productId)
						.invQuantity(randomInt + 1)
						.build();

				inventoryRepository.save(inventoryItem);
			};

			};
	}

}
