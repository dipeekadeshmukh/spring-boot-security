package personal.dipeeka.spring_boot_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	
	// should be accessed by all even user is not logged in
	@GetMapping("/")
	public String home(){
		return("<h1>Welcome</h1>");
	}
	
	// should be accessed by all logged in user like user and admin
	@GetMapping("/user")
	public String user(){
		return("<h1>Welcome User</h1>");
	}

	// should be accessed by all logged in user like admin
	@GetMapping("/admin")
	public String admin(){
		return("<h1>Welcome Admin</h1>");
	}


}
