# Microservices

## Microservicio de Negocio

Hay 4 servicios con logica de negocio, tenemos

_**Productos**_: Gestiona el stock y categorias de productos

_**Shopping**_: Se encarga del carrito de compras, cada item que encargan(productos), y los datos de los clientes.

_**Customers**_: Gestiona la direccion y la informacion del cliente.

_**Auth**_: Se encarga de auditar la seguridad peticion con JWT, el resto de microservicio luego de que autorizarse la peticion se comunican con BasiAuth proporciionado por Auth-service para resolver la peticion.

**Feig + Hystrix**
Los microservicios de negocio se comunican por HTTP para resolver peticiones cumpliendo con responsabilidad unica.
En caso de que un microservicio se caiga Hystrix me devuelve un dato ficticio, tambien es posible ver las graficas de comunicaciones.

**Dependencias**

1.  lombok
2.  spring-boot-starter-data-jpa(o mongodb)
3.  h2 o postgreSQL
4.  spring-boot-starter-validation
5.  spring-boot-starter-web
6.  spring-cloud-starter-config
7.  spring-cloud-starter-netflix-eureka-client
8.  spring-cloud-starter-sleuth
9.  spring-boot-admin-starter-client
10. spring-boot-starter-actuator(sirve para admin y hystrix)
11. spring-cloud-netflix-hystrix-dashboard
12. add bootstrap
13. spring-cloud-starter-openfeign

## Microservicio de Infraestructura

## Config

Es encargado de versionar y actualizar la configuracion de cada microservicio.
**Dependencias**

1. spring-cloud-config-server
2. spring-boot-starter-security ???

## Admin

Administra los microservicios de Negocio
**Dependencias**

1. spring-boot-admin-starter-server

## Eureka

Para que Gateway pueda comunicarse con el resto de microservicio los descubro con eureka
**Dependencias**

1. spring-cloud-starter-config
2. spring-cloud-starter-netflix-eureka-server
3. add bootstrap

## Gateway

Es necesario filtrar la comunicacion y redirigir la peticion adecuada al lugar adecuado por ende esta este microservicio.
Comunicandose por webSocket con Auth-service para que ese filtrado sea satisfactorio, devolviendo las credenciales para autorizar la comunicacion entre microservicios.

**Dependencias**

1. lombok
2. spring-cloud-starter-config
3. spring-cloud-starter-gateway
4. spring-cloud-starter-netflix-eureka-client
5. spring-cloud-starter-sleuth
6. add bootstrap

# Kubernete deployment

### Microservicios

Es en el archivo deployment, estan los yml de kubernete necesario para el despliegue, las imagenes docker se las puede crear con los siguientes pasaso.

```bash
docker build -t nombre-servicio:1.0
```

ahora solo es necesario ejecutar cada yaml

```bash
kubectl apply -t nombre_servicio.yml
```

### Base de datos

para la base de datos esta en el mismo archivo deployment/db el postgres_db.yml, los pasos para ejecutarlo

1. **Ejecuta yml**

```bash
   kubectl apply -t postgres_db.yml
```

2. **Llena la base de datos springpostgre**

```bash
   psql -U postgres -d springpostgre < springdb.sql
```
