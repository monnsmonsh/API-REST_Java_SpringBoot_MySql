
# COMO CREAR UNA API REST SPRING BOOT

configuracion basica

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)


## Roadmap

- Additional browser support

- Add more integrations


## Usage/Examples

```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos
    spring.datasource.username=root
    spring.datasource.password=********
    spring.jpa.hibernate.ddl-auto=update

```


## 🛠 Skills
Javascript, HTML, CSS...


## Configuracion de package
creamos los siguientes `package` para nuestro proyecto
  - controller
  - models
  - services
  - repositories

Despues de configurados los repocitorios agregamos a:
| package | class     | nombre                |
| :-------- | :------- | :------------------------- |
| controller | clase | UserController |
| models | clase | UserModel |
| repositories | interface | IUserService |
| services | clase | UserService |

## API Reference

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.

