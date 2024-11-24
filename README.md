### **README: Control de Asistencias Backend**

---

## **Descripción**

Este proyecto es un sistema de **Control de Asistencias** diseñado para gestionar la asistencia, roles y certificaciones de practicantes en una organización. La aplicación utiliza **Java 17**, **Spring Boot 3.3.5**, **MySQL**, y **JWT** para manejar la autenticación y autorización.

---

## **Funcionalidades**

### **1. Gestión de Roles**
- **Roles Disponibles:**
    - **Admin:** Administrador con control total sobre el sistema.
    - **Supervisor:** Responsable de gestionar áreas y supervisar practicantes.
    - **Intern:** Practicante asociado a un área y una universidad, que registra asistencias.
- **Validaciones:**
    - Cada `Supervisor` debe ser único por área.
    - Las contraseñas se encriptan usando `BCrypt`.

---

### **2. Gestión de Usuarios**
- **Creación y Búsqueda:**
    - Crear usuarios con nombre, apellido, DNI, rol y estado.
    - Buscar usuarios por nombre completo, DNI o rol.
- **Validaciones:**
    - El DNI debe ser único.
    - Los usuarios menores de 18 años no pueden ser registrados.
    - El estado del usuario debe ser consistente con sus roles.

---

### **3. Gestión de Áreas**
- **Operaciones Disponibles:**
    - Crear, actualizar, listar y eliminar áreas.
    - Relacionar áreas con universidades mediante una tabla `AreaUniversity`.
- **Validaciones:**
    - No se puede eliminar un área si tiene practicantes asociados.
    - Las áreas no pueden tener el mismo nombre.

---

### **4. Gestión de Practicantes**
- **Asociación:**
    - Los practicantes están asociados a un `AreaUniversity` con un límite de vacantes y horas requeridas para certificación.
- **Validaciones:**
    - No pueden registrarse más practicantes que las vacantes disponibles en un área.

---

### **5. Gestión de Asistencias**
- **Operaciones Disponibles:**
    - Registro de entrada (`Check-In`) y salida (`Check-Out`) por parte de los practicantes.
    - Consulta de asistencias por fecha, área, universidad o nombre de practicante.
- **Validaciones:**
    - No se puede registrar una salida si no hay entrada previa.
    - Si un practicante no marca salida, se calcula un máximo de 10 horas.
    - No se permiten horarios superpuestos.
    - Las horas trabajadas se calculan automáticamente y se suman al total del practicante.

---

### **6. Gestión de Certificaciones**
- **Operaciones Disponibles:**
    - Generar certificaciones para practicantes que hayan cumplido las horas requeridas en su `AreaUniversity`.
    - Consultar certificaciones por nombre de practicante.
- **Validaciones:**
    - El estado del certificado solo se habilita si las horas requeridas han sido cumplidas.

---

### **7. Autenticación y Autorización**
- **JWT Token:**
    - Generación de un token JWT al autenticarse con DNI y contraseña.
    - Extracción del usuario actual a través del token.
- **Flujo de Autenticación:**
    - Usuarios con contraseña (`Admin`, `Supervisor`) ingresan con sus credenciales.
    - Practicantes (`Intern`) no tienen acceso al sistema.

---

## **Estructura del Proyecto**

- **`config`**: Configuración de Spring Security y manejo de JWT.
- **`controller`**: Controladores para manejar solicitudes HTTP.
- **`exception`**: Manejo de excepciones personalizadas.
- **`model`**: Entidades de la base de datos.
- **`repository`**: Interfaces de JPA para interacción con la base de datos.
- **`service`**: Interfaces que definen la lógica del negocio.
- **`serviceImpl`**: Implementaciones de los servicios.
- **`utils`**: Clases utilitarias como `ResponseUtils` para respuestas y `JwtUtils` para manejo de tokens.

---

## **Endpoints Principales**

### **1. Autenticación**
- **`POST /auth/login`**
    - Recibe un JSON con `dni` y `password`.
    - Devuelve un JWT token.

### **2. Gestión de Administradores**
- **`POST /admin/add`**
    - Crea un administrador.
- **`GET /admin/id/{idAdmin}`**
    - Obtiene un administrador por su ID.
- **`GET /admin`**
    - Lista paginada de administradores.
- **`DELETE /admin/delete/{idAdmin}`**
    - Elimina un administrador (no permite dejar el sistema sin administradores).

### **3. Gestión de Practicantes**
- **`POST /intern/add`**
    - Registra un practicante asociado a un área y una universidad.
- **`GET /intern/area/{areaName}`**
    - Lista practicantes de un área específica.
- **`PUT /intern/update/{idIntern}`**
    - Actualiza la información de un practicante.

### **4. Gestión de Asistencias**
- **`POST /assistance/checkin`**
    - Registra la entrada de un practicante.
- **`POST /assistance/checkout`**
    - Registra la salida de un practicante.
- **`GET /assistance/date/{date}`**
    - Obtiene asistencias por fecha.

---

## **Validaciones Clave**

1. **Roles:**
    - Contraseñas encriptadas al guardar usuarios con acceso.
    - Supervisores únicos por área.

2. **Áreas:**
    - No eliminar áreas en uso por practicantes.

3. **Asistencias:**
    - Validación de horarios superpuestos.
    - Cálculo automático de horas trabajadas.

4. **Certificaciones:**
    - Solo se generan certificados si las horas requeridas se cumplen.

---

## **Cómo Probar**

1. **Registrar Usuarios:**
    - Usa los endpoints de `Admin` o `Supervisor` para crear usuarios con acceso.
    - Asegúrate de enviar contraseñas en texto plano para que sean encriptadas.

2. **Autenticación:**
    - Realiza una solicitud `POST /auth/login` con DNI y contraseña.
    - Usa el token JWT en el encabezado `Authorization` para acceder a endpoints protegidos.

3. **Practicantes y Asistencias:**
    - Crea practicantes y registra sus asistencias.
    - Verifica que las horas trabajadas se calculen correctamente y que se reflejen en su total.

4. **Certificaciones:**
    - Genera un certificado al cumplir las horas requeridas.

---

## **Tecnologías Usadas**

- **Spring Boot 3.3.5**
- **Java 17**
- **MySQL**
- **Spring Security con JWT**
- **BCrypt para encriptación de contraseñas**

---