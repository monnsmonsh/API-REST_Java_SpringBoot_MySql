
# Como crear una API REST en JAVA con SpringBoot y MySql

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

<!--[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)
-->

## 🛠 Skills
Java, sprintboot, mysql

Para poder crear una api en java con sprint, lo primero que necesitamos es configurar nuestro `spring` como en la siguiente imagen se muestra.

<image src="https://github.com/monnsmonsh/JAVA/blob/main/Assets/Config_Spring.png" alt="Descripción de la imagen">

## Software utilizado
- IntelliJ IDEA 
- workbench
- Postman

No es necesario utilizar `IntelliJ IDEA` puedes utilizar cualquier otro IDE que soporte java.


## Configuracion con la Base de datos
Para poder conectarnos a nuestra base de datos tenemos que configurar la conexion, en el archivo `application.properties` que esta en la ruta ./src/resources/  

```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos
    spring.datasource.username=root
    spring.datasource.password=********
    spring.jpa.hibernate.ddl-auto=update
```
Si contamos con varios proyecto podemos agragar la ruta de nuestro servidor
```properties
   server.port=4000
```

> **Note:** Recuerda ques es **Importante** ya tener tu base de datos creada para poder realizar la conexion
```sql
   create database nombre_de_la_base_de_datos
```

## Configuracion de package
creamos los siguientes `package` para nuestro proyecto
  - controller
  - models
  - services
  - repositories

Despues de configurados los repocitorios agregamos a:
| package | TYPE     | nombre                |
| :-------- | :------- | :------------------------- |
| controller | clase | UserController |
| models | clase | UserModel |
| repositories | interface | IUserService |
| services | clase | UserService |

### Configurar nuestro modelo
 
 Primero creamos definimos nuestro modelo
 ```java
    package com.api.crud.models;

    import jakarta.persistence.*;

    @Entity
    @Table(name ="user")
    public class UserModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fistName;
        private String lastName;
        private String email;

    }

```

Luego de tener nuestro medelo listo les generamos los getter y setters

### Configurar nuestro controller
para empezar a configurar nuestro controlador tenemos que ponerle a anotacion `@RestController`, `@RequestMapping`.

una ves terminada definimos la peticiones HTTP y las rutas
 ```java
package com.api.crud.controllers;


import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional <UserModel> getUserById(@PathVariable("id") Long id){
        return this.userService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request,@PathVariable("id") Long id){
        return  this.userService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok= this.userService.deleteUser(id);
        if(ok) {
            return "Usuario eliminado con el id"+id+"eliminado";
        }else{
            return  "Usuario no eliminado ubo un eeror";
        }

    }

}


```


### Configurar nuestro repositorio
primero indicamos que es un repocitorio con la anotacion  `@Repository`


### Configurar nuestro Service

le aggregamos la anotacion de `@Service`.

los primero que tenemos que agrefar es @autowire que esto srive para inmyeccion de dependencias y la que susamos es IUserRepository.

para obtner nuestros usuario generamos el siguiente metodo
 ```java
public ArrayList<UserModel> getUsers(){
        return  (ArrayList<UserModel>) userRepository.findAll();
    }
```


 ```java
package com.api.crud.services;


import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;


    //Metodo para listar por medio de un arra
    public ArrayList<UserModel> getUsers(){
        return  (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }

    public UserModel updateById(UserModel request, Long id){
        UserModel user = userRepository.findById(id).get();

        user.setFistName(request.getFistName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        return user;
    }

    public Boolean deleteUser (Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
```



## API Reference
| Metodo | Type     | URL                |
| :-------- | :------- | :------------------------- |
| `GET` | `Listado` | localhost:4000/user |
| `GET` | `Detallas` | localhost:4000/user/{id} |
| `PUT` | `Editar` | localhost:4000/user/{id} |
| `Post` | `Crear` | localhost:4000/user |
| `DELETE` | `Eliminar` | localhost:4000/user/{id} |

### GET
<image src="https://github.com/monnsmonsh/JAVA/blob/main/Assets/GET_All.png" alt="Descripción de la imagen">
<image src="https://github.com/monnsmonsh/JAVA/blob/main/Assets/GET_ID.png" alt="Descripción de la imagen">

### PUT
<image src="https://github.com/monnsmonsh/JAVA/blob/main/Assets/PUT.png" alt="Descripción de la imagen">
    
### POST
<image src="https://github.com/monnsmonsh/JAVA/blob/main/Assets/POST.png" alt="Descripción de la imagen">
    
### DELETE
<image src="https://github.com/monnsmonsh/JAVA/blob/main/Assets/DELETE.png" alt="Descripción de la imagen">
