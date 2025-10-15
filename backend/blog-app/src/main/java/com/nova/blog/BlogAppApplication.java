package com.nova.blog;

import com.nova.blog.config.AppConstants;
import com.nova.blog.model.Role;
import com.nova.blog.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) {
		seedRole(AppConstants.ROLE_ADMIN);
		seedRole(AppConstants.ROLE_NORMAL);
	}

	private void seedRole(String roleName) {
		if (roleRepository.findByName(roleName).isEmpty()) {
			roleRepository.save(new Role(roleName));
		}
	}
}
