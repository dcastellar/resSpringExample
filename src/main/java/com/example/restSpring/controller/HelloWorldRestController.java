package com.example.restSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.restSpring.model.Catalog;
import com.example.restSpring.model.User;
import com.example.restSpring.service.CatalogService;
import com.example.restSpring.service.UserService;

@RestController
public class HelloWorldRestController {

	@Autowired
	UserService userService; // Service which will do all data retrieval/manipulation work
	@Autowired
	CatalogService catalogService;
	
	@RequestMapping(value = "/catalog", method = RequestMethod.POST)
	public ResponseEntity<Void> createCatalog(@RequestBody Catalog catalog, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Catalog " + catalog.getDescCatalog());

		int idCatalog = catalogService.findById(catalog.getIdCatalog());
		if(idCatalog!=0) {
			catalog.setId(idCatalog);
			catalogService.updateCatalog(catalog);
		} else {
			catalogService.createCatalog(catalog);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/catalog/id={id}").buildAndExpand(catalog.getIdCatalog()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// -------------------Retrieve All Users--------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User--------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User--------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Updating User " + id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All User --------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// /**
	// * Supongo que el recorrido a realizar por el dron es un cuadrado
	// * (según la imagen que se proporciona)
	// * @param cordX int
	// * @param cordY int
	// * @param rango int
	// * @return List<String> Lista de ids de urbanizaciones
	// */
	// public List<String> obtenerUrbanizaciónes(int cordX,int cordY,int rango) {
	//
	// List<String> lista = new ArrayList<String>();
	// int origen = obtenerIdentificadorUrbanización(cordX,cordY);
	//
	// int urb = 0;
	// //Moverse al perímetro indicado por el rango
	// for(int i=0;i<rango;i++) {
	// urb = obtenerAdyacente(urb,"DERECHA");
	// }
	// //Empezamos el recorrido del cuadrado
	// //Hacia arriba
	// lista.add(urb+"");
	// for(int i=0;i<rango;i++) {
	// urb = obtenerAdyacente(urb,"ARRIBA");
	// lista.add(urb+"");
	// }
	// // Lado superior (la urbanización de la esquina ya se tiene)
	// for(int i=0;i<rango*2;i++) {
	// urb = obtenerAdyacente(urb,"IZQUIERDA");
	// lista.add(urb+"");
	//
	// }
	// //Lado izquierdo (la urbanización de la esquina ya se tiene)
	// for(int i=0;i<rango*2;i++) {
	// urb = obtenerAdyacente(urb,"ABAJO");
	// lista.add(urb+"");
	//
	// }
	// // Lado inferior (la urbanización de la esquina ya se tiene)
	// for(int i=0;i<rango*2;i++) {
	// urb = obtenerAdyacente(urb,"DERECHA");
	// lista.add(urb+"");
	//
	// }
	// //Queda parte del lado derecho por recorrer (concretamente n-1 urbanización)
	// for(int i=0;i<rango-1;i++) {
	// urb = obtenerAdyacente(urb,"ARRIBA");
	// lista.add(urb+"");
	// }
	// return lista;
	// }

}
