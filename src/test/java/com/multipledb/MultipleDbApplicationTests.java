package com.multipledb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multipledb.mysql.entities.Users;
import com.multipledb.mysql.repo.UserRepo;
import com.multipledb.postgres.entities.Products;
import com.multipledb.postgres.repo.ProductsRepo;

import jakarta.transaction.Transactional;

@SpringBootTest
class MultipleDbApplicationTests {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProductsRepo productsRepo;
	
	Users users = new Users(1, "sushant", "pune", "30");
	Products products = new Products(1, "mobile", "30000");
	
	@Test
	@Transactional
	void checkinsertion() {
		
		userRepo.save(users);
		productsRepo.save(products);
	}

}
