/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;*/
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import controller.MongoConnection;
import excepciones.DniException;
import excepciones.PasswordException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import log.Logger;
import model.Boss;
import model.Employee;
import model.Gym;
import model.GymClass;
import model.Person;
import model.Product;
import model.Schedule;
import model.ShoppingHistory;
import model.Training;
import model.User;
import org.bson.Document;

/**
 * Implementación de acceso a datos de la aplicación. Gestiona las operaciones
 * CRUD sobre usuarios, empleados, clases y productos almacenados en MongoDB.
 *
 * Implementa el patrón Singleton para disponer de una única instancia de acceso
 * a la base de datos.
 *
 * @author ire22
 */
public class ImplementacionBD {

    private ResourceBundle configFile;
    private String urlBD;
    private String userBD;
    private String passwordBD;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private static ImplementacionBD instance;
    private final MongoCollection<Document> PROFILES;

    /**
     * Constructor privado que inicializa la conexión con la base de datos y la
     * colección de personas.
     */
    private ImplementacionBD() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("G2_Life");
        PROFILES = database.getCollection("people");

    }

    /**
     * Obtiene la única instancia de la clase.
     *
     * @return instancia de ImplementacionBD.
     */
    public static ImplementacionBD getInstance() {
        if (instance == null) {
            instance = new ImplementacionBD();

        }
        return instance;
    }

    /**
     * Busca un usuario en la base de datos a partir de su nombre de usuario y
     * contraseña.
     *
     * @param usuario nombre de usuario.
     * @param contrasena contraseña del usuario.
     * @return objeto Person correspondiente o null si no existe.
     */
    public Person buscar(String usuario, String contrasena) {
        Person persona = null;
        List<Integer> classes;
        List<Document> workoutsDoc = new ArrayList<>();
        List<Training> workouts = new ArrayList<>();
        try {
            Document doc = PROFILES.find(and(eq("username", usuario), eq("password", contrasena))).first();

            if (doc == null || !contrasena.equals(doc.getString("password"))) {
                Logger.log("FAILED LOGIN", "User=" + usuario);
                return null;
            } else {
                String type = doc.getString("role");
                Logger.log(
                        "LOGIN",
                        "User=" + usuario
                        + ", Role=" + type
                );
                Date fechaMongo = doc.getDate("start_date");
                LocalDate fecha = null;

                if (fechaMongo != null) {
                    fecha = fechaMongo.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                }

                if ("member".equals(type)) {
                    classes = doc.getList("classes", Integer.class);
                    workoutsDoc = doc.getList("workouts", Document.class);

                    for (Document s : workoutsDoc) {

                        Training Training = new Training(
                                s.getString("name"),
                                s.getInteger("reps"),
                                s.getInteger("weight"),
                                s.getInteger("sets")
                        );

                        workouts.add(Training);
                    }
                    return new User(workouts, classes, doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"), doc.getInteger("balance"), fecha);
                } else if ("manager".equals(type)) {
                    return new Boss(doc.getInteger("gym_id"), doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"), doc.getInteger("balance"), fecha);
                } else if ("employee".equals(type)) {
                    classes = doc.getList("classes", Integer.class);

                    return new Employee(classes, doc.getString("discount_code"), doc.getInteger("gym_id"), doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"), doc.getInteger("balance"), fecha);
                }
            }

        } catch (MongoException ex) {

        }
        return persona;

    }

    /**
     * Obtiene todos los usuarios registrados con rol member.
     *
     * @return lista de usuarios.
     */
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("people"); //Consigo la collecion de "Person" = "db.Persons.find()"

        // Document porue MongoDB devuelve BSON/JSON y en java estos documentos se representan con la clase Document
        for (Document doc : collection.find(Filters.eq("role", "member"))) {
            System.out.println(doc);
            User user = new User();

            user.setUserName(doc.getString("username"));
            try {
                user.setPassword(doc.getString("password"));
            } catch (PasswordException e) {
                System.out.println("Incorrect password: " + e.getMessage());
                continue; // pasa al siguiente usuario
            }

            user.setName(doc.getString("first_name"));
            user.setLastName(doc.getString("last_name"));

            try {
                user.setDni(doc.getString("national_id"));
            } catch (DniException e) {
                System.out.println("Incorrect DNI: " + e.getMessage());
                continue; // pasa al siguiente usuario
            }

            Date fechaMongo = doc.getDate("start_date");
            LocalDate fecha = fechaMongo.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            user.setStartDate(fecha);

            users.add(user);
        }
        return users;
    }

    /**
     * Obtiene todos los empleados registrados.
     *
     * @return lista de empleados.
     */
    public ArrayList<Employee> getEmployee() {
        ArrayList<Employee> aEmployee = new ArrayList<>();

        MongoCollection<Document> collection = database.getCollection("people"); //Consigo la collecion de "Person" = "db.Persons.find()"

        // Document porue MongoDB devuelve BSON/JSON y en java estos documentos se representan con la clase Document
        for (Document doc : collection.find(Filters.eq("role", "employee"))) {
            System.out.println(doc);
            Employee employee = new Employee();

            employee.setUserName(doc.getString("username"));

            try {
                String passwordMongo = doc.getString("password");

                System.out.println("Mongo: " + passwordMongo);

                employee.setPassword(passwordMongo);

                System.out.println("Objeto: " + employee.getPassword());

            } catch (PasswordException e) {
                System.out.println("Incorrect password: " + e.getMessage());
                continue;
            }

            employee.setName(doc.getString("first_name"));
            employee.setLastName(doc.getString("last_name"));

            try {
                employee.setDni(doc.getString("national_id"));
            } catch (DniException e) {
                System.out.println("Incorrect DNI: " + e.getMessage());
                continue; // pasa al siguiente usuario
            }

            Date fechaMongo = doc.getDate("start_date");
            LocalDate fecha = fechaMongo.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            employee.setStartDate(fecha);

            aEmployee.add(employee);
        }
        return aEmployee;
    }

    /**
     * Inserta una persona en la base de datos.
     *
     * @param p persona a insertar.
     * @return true si la inserción se realiza correctamente.
     */
    public boolean insertarPerson(Person p) {
        try {
            MongoCollection<Document> collection = database.getCollection("people");
            Document doc = new Document();
            doc.append("username", p.getUserName());
            doc.append("password", p.getPassword());
            doc.append("first_name", p.getName());
            doc.append("last_name", p.getLastName());
            doc.append("national_id", p.getDni());
            doc.append("start_date", p.getStartDate());

            if (p instanceof Employee) {
                doc.append("role", "employee");
                doc.append("classes", new ArrayList<Integer>());
                doc.append("balance", 0);

                Employee e = (Employee) p;
                doc.append("gym_id", e.getGymId());
            } else if (p instanceof User) {
                doc.append("role", "member");
                doc.append("classes", new ArrayList<Integer>());
                doc.append("workouts", new ArrayList<Document>());
                doc.append("balance", 0);
            }

            collection.insertOne(doc);
            Logger.log("INSERT PERSON",
                    "User=" + p.getUserName()
                    + ", Role=" + (p instanceof Employee ? "employee" : "member")
            );
            return true;

        } catch (Exception e) {
            Logger.log(
                    "ERROR",
                    "Metodo=insertarPerson, Error=" + e.getMessage()
            );
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza los datos de una persona existente.
     *
     * @param p persona con los datos modificados.
     * @return true si la modificación se realiza correctamente.
     */
    public boolean modificarPerson(Person p) {
        try {
            MongoCollection<Document> collection = database.getCollection("people");
            Document cambios = new Document();
            cambios.append("password", p.getPassword());
            cambios.append("first_name", p.getName());
            cambios.append("last_name", p.getLastName());
            cambios.append("national_id", p.getDni());
            collection.updateOne(
                    Filters.eq("username", p.getUserName()), //busca el que tenga ese userName 
                    new Document("$set", cambios) //y con el $set actualiza los campo que le digo pero que deje lo demas igual 
            );
            Logger.log("UPDATE PERSON", "User=" + p.getUserName());
            return true;
        } catch (Exception e) {
            Logger.log(
                    "ERROR",
                    "Metodo=modificarPerson, Error=" + e.getMessage()
            );
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina una persona de la base de datos.
     *
     * @param p persona a eliminar.
     * @return true si se elimina correctamente.
     */
    public boolean eliminarPerson(Person p) {
        try {
            MongoCollection<Document> collection = database.getCollection("people");
            System.out.println("Base de datos: " + database.getName());
            System.out.println("Usuario a eliminar: " + p.getUserName());

            DeleteResult resultado = collection.deleteOne(
                    Filters.eq("username", p.getUserName())
            );

            System.out.println("Documentos eliminados: "
                    + resultado.getDeletedCount());
            Logger.log("DELETE PERSON", "User=" + p.getUserName());
            return resultado.getDeletedCount() > 0;

        } catch (Exception e) {
            Logger.log(
                    "ERROR",
                    "Metodo=eliminarPerson, Error=" + e.getMessage()
            );
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene todos los productos almacenados en la colección de productos.
     *
     * @return lista de productos.
     */
    public ArrayList<Product> obtenerProductos() {
        MongoCollection<Document> productos = MongoConnection.getConnection().getCollection("products");
        ArrayList<Product> lista = new ArrayList<>();

        for (Document doc : productos.find()) {
            Product p = new Product(((Number) doc.get("_id")).intValue(),
                    doc.getString("name"),
                    ((Number) doc.get("price")).floatValue(),
                    ((Number) doc.get("stock")).intValue());
            lista.add(p);
        }

        return lista;
    }

    /**
     * Obtiene todos los gimnasios registrados en la base de datos.
     *
     * @return lista de gimnasios disponibles.
     */
    public ArrayList<Gym> obtenerGimnasios() {
        MongoCollection<Document> gyms = MongoConnection.getConnection().getCollection("gyms");
        ArrayList<Gym> lista = new ArrayList<>();

        for (Document doc : gyms.find()) {
            Gym g = new Gym(((Number) doc.get("_id")).intValue(),
                    doc.getString("address"),
                    doc.getString("postal_code"));
            lista.add(g);
        }

        return lista;
    }

    /**
     * Recupera todas las clases almacenadas en la colección de clases junto con
     * sus horarios, entrenador y miembros inscritos.
     *
     * @return lista de clases del gimnasio.
     */
    public ArrayList<GymClass> getGymClasses() {

        ArrayList<GymClass> clases = new ArrayList<>();

        MongoCollection<Document> collection
                = database.getCollection("classes");

        for (Document doc : collection.find()) {

            System.out.println("DOCUMENTO:");
            System.out.println(doc.toJson());

            int id = ((Number) doc.get("_id")).intValue();
            String className = doc.getString("class_name");
            String employeeUsername = doc.getString("employee_username");
            int gymId = ((Number) doc.get("gym_id")).intValue();

            ArrayList<String> members = new ArrayList<>();

            List<String> membersList
                    = doc.getList("members", String.class);

            if (membersList != null) {
                members.addAll(membersList);
            }

            ArrayList<Schedule> schedules = new ArrayList<>();

            List<Document> scheduleDocs
                    = doc.getList("schedule", Document.class);

            System.out.println("Schedule: " + scheduleDocs);

            if (scheduleDocs != null) {

                for (Document s : scheduleDocs) {

                    String day = s.getString("day");
                    String hour = s.getString("time");

                    System.out.println(day + " " + hour);

                    schedules.add(
                            new Schedule(day, hour)
                    );
                }
            }

            GymClass gymClass = new GymClass(
                    id,
                    className,
                    schedules,
                    employeeUsername,
                    members,
                    gymId
            );

            clases.add(gymClass);
        }

        System.out.println("TOTAL CLASES: " + clases.size());

        return clases;
    }

    /**
     * Obtiene los identificadores de las clases asignadas a un entrenador.
     *
     * @param username nombre de usuario del entrenador.
     * @return lista de identificadores de clases.
     */
    public ArrayList<Integer> getClassIdsEmployee(String username) {

        ArrayList<Integer> classIds = new ArrayList<>();

        MongoCollection<Document> collection
                = database.getCollection("employees");

        Document employee
                = collection.find(Filters.eq("username", username)).first();

        if (employee != null) {

            List<Document> classes
                    = (List<Document>) employee.get("classes");

            for (Document c : classes) {

                classIds.add(c.getInteger("class_id"));
            }
        }

        return classIds;
    }

    /**
     * Obtiene las clases asociadas a un usuario según su rol dentro de la
     * aplicación.
     *
     * Los miembros visualizan las clases en las que están inscritos y los
     * empleados las clases que imparten.
     *
     * @param username nombre de usuario.
     * @param role rol del usuario.
     * @return lista de clases asociadas.
     */
    public ArrayList<GymClass> getClassesForUser(
            String username,
            String role) {

        ArrayList<GymClass> resultado = new ArrayList<>();

        ArrayList<GymClass> todasLasClases
                = getGymClasses();

        System.out.println("USER = " + username);
        System.out.println("ROLE = " + role);

        if ("member".equals(role)) {

            for (GymClass gymClass : todasLasClases) {

                System.out.println(
                        gymClass.getClassName()
                        + " -> "
                        + gymClass.getMembers()
                );

                if (gymClass.getMembers().contains(username)) {

                    System.out.println(
                            "ENCONTRADA -> "
                            + gymClass.getClassName()
                    );

                    resultado.add(gymClass);
                }
            }

        } else if ("employee".equals(role)) {

            for (GymClass gymClass : todasLasClases) {

                if (gymClass.getEmployeeUsername()
                        .equals(username)) {

                    System.out.println(
                            "CLASE EMPLEADO -> "
                            + gymClass.getClassName()
                    );

                    resultado.add(gymClass);
                }
            }
        }

        System.out.println(
                "RESULTADO FINAL = "
                + resultado.size()
        );

        return resultado;
    }

    /**
     * Crea una nueva clase en la base de datos.
     *
     * @param gymClass clase que se desea crear.
     */
    public void createClass(GymClass gymClass) {

        MongoCollection<Document> collection
                = database.getCollection("classes");

        ArrayList<Document> schedules = new ArrayList<>();

        for (Schedule s : gymClass.getSchedule()) {

            schedules.add(
                    new Document("day", s.getDay())
                            .append("time", s.getHour())
            );
        }
        Document doc = new Document();

        doc.append("_id", gymClass.getId());
        doc.append("class_name", gymClass.getClassName());
        doc.append("employee_username",
                gymClass.getEmployeeUsername());
        doc.append("gym_id", gymClass.getGymId());
        doc.append("members", gymClass.getMembers());
        doc.append("schedule", schedules);

        collection.insertOne(doc);
        Logger.log("CREATE CLASS", "ID=" + gymClass.getId() + ", Name=" + gymClass.getClassName() + ", Trainer=" + gymClass.getEmployeeUsername());
    }

    /**
     * Elimina una clase existente a partir de su identificador.
     *
     * @param classId identificador de la clase.
     */
    public void deleteClass(int classId) {

        MongoCollection<Document> collection
                = database.getCollection("classes");

        collection.deleteOne(
                Filters.eq("_id", classId)
        );
        Logger.log(
                "DELETE CLASS",
                "ID=" + classId
        );
    }

    /**
     * Cambia el entrenador asignado a una clase actualizando tanto la colección
     * de clases como la información de los empleados.
     *
     * @param classId identificador de la clase.
     * @param newTrainer nombre del nuevo entrenador.
     */
    public void updateClassTrainer(
            int classId,
            String newTrainer) {

        MongoCollection<Document> classes
                = database.getCollection("classes");

        MongoCollection<Document> people
                = database.getCollection("people");

        Document clase = classes.find(
                Filters.eq("_id", classId)
        ).first();

        String oldTrainer
                = clase.getString("employee_username");

        people.updateOne(
                Filters.eq("username", oldTrainer),
                Updates.pull("classes", classId)
        );

        people.updateOne(
                Filters.eq("username", newTrainer),
                Updates.push("classes", classId)
        );

        classes.updateOne(
                Filters.eq("_id", classId),
                Updates.set("employee_username", newTrainer)
        );
    }

    /**
     * Inscribe a un usuario en una clase y actualiza su información en la base
     * de datos.
     *
     * @param classId identificador de la clase.
     * @param username nombre del usuario.
     */
    public void addMemberToClass(
            int classId,
            String username) {

        MongoCollection<Document> classes
                = database.getCollection("classes");

        MongoCollection<Document> people
                = database.getCollection("people");

        classes.updateOne(
                Filters.eq("_id", classId),
                Updates.addToSet("members", username)
        );

        people.updateOne(
                Filters.eq("username", username),
                Updates.addToSet("classes", classId)
        );

        Logger.log(
                "ADD MEMBER",
                "User=" + username
                + ", Class=" + classId
        );
    }

    /**
     * Elimina la inscripción de un usuario en una clase determinada.
     *
     * @param classId identificador de la clase.
     * @param username nombre del usuario.
     */
    public void removeMemberFromClass(
            int classId,
            String username) {

        MongoCollection<Document> classes
                = database.getCollection("classes");

        MongoCollection<Document> people
                = database.getCollection("people");

        classes.updateOne(
                Filters.eq("_id", classId),
                Updates.pull("members", username)
        );

        people.updateOne(
                Filters.eq("username", username),
                Updates.pull("classes", classId)
        );

        Logger.log(
                "REMOVE MEMBER",
                "User=" + username
                + ", Class=" + classId
        );
    }

    /**
     * Obtiene todos los productos almacenados en la colección de productos.
     *
     * @param cart el carrito que se va a comprar.
     * @param gymID el id del gym al que ha llegado la compra.
     * @param userName el usuario que ha realizado la compra y al que se le va a
     * asignar.
     * @param totalPrice precio de la compra.
     */
    public void comprar(Map<Product, Integer> cart, int gymID, String userName, float totalPrice) {
        MongoCollection<Document> purchases = MongoConnection.getConnection().getCollection("purchases");
        MongoCollection<Document> person = MongoConnection.getConnection().getCollection("people");

        Document ultimo = purchases.find()
                .sort(Sorts.descending("_id"))
                .first();

        List<Document> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> p : cart.entrySet()) {
            productList.add(new Document("product_id", p.getKey().getProductId())
                    .append("quantity", p.getValue()));
        }

        int newId = (ultimo != null) ? ((Number) ultimo.get("_id")).intValue() + 1 : 1;

        Document newPurchase = new Document();
        newPurchase.append("_id", newId);
        newPurchase.append("gym_id", gymID);
        newPurchase.append("username", userName);
        newPurchase.append("total_price", totalPrice);
        newPurchase.append("date", LocalDate.now().toString());
        newPurchase.append("products", productList);

        purchases.insertOne(newPurchase);

        person.updateOne(Filters.eq("username", userName),
                Updates.push("purchases", newId));
        Logger.log("PURCHASE", "User=" + userName + ", Gym=" + gymID + ", Total=" + totalPrice
        );

    }

    /**
     * Recupera el historial de compras realizado por un usuario determinado.
     *
     * @param username nombre de usuario.
     * @return lista de compras realizadas.
     */
    public List<ShoppingHistory> findPurchasesByUser(String username) {
        MongoCollection<Document> purchases = MongoConnection.getConnection().getCollection("purchases");

        List<ShoppingHistory> result = new ArrayList<>();

        for (Document d : purchases.find(Filters.eq("username", username))
                .sort(Sorts.descending("_id"))) {

            HashMap<Integer, Integer> products = new HashMap<>();
            for (Document p : d.getList("products", Document.class)) {
                products.put(((Number) p.get("product_id")).intValue(),
                        ((Number) p.get("quantity")).intValue());
            }

            result.add(new ShoppingHistory(
                    ((Number) d.get("_id")).intValue(),
                    ((Number) d.get("gym_id")).intValue(),
                    d.getString("username"),
                    ((Number) d.get("total_price")).floatValue(),
                    LocalDate.parse(d.getString("date")),
                    products));
        }
        return result;
    }

    /**
     * Carga todos los productos almacenados en la base de datos y los organiza
     * por su identificador.
     *
     * @return mapa de productos indexados por ID.
     */
    public Map<Integer, Product> loadProducts() {
        MongoCollection<Document> products = MongoConnection.getConnection().getCollection("products");

        Map<Integer, Product> map = new HashMap<>();
        for (Document d : products.find()) {
            int id = ((Number) d.get("_id")).intValue();
            map.put(id, new Product(id,
                    d.getString("name"),
                    ((Number) d.get("price")).floatValue(),
                    ((Number) d.get("stock")).intValue()));
        }
        return map;
    }

    /**
     * Comprueba si existe un empleado con el nombre de usuario indicado.
     *
     * @param username nombre de usuario.
     * @return true si existe, false en caso contrario.
     */
    public boolean employeeExists(String username) {

        MongoCollection<Document> collection
                = database.getCollection("people");

        Document empleado = collection.find(
                Filters.and(
                        Filters.eq("username", username),
                        Filters.eq("role", "employee")
                )
        ).first();

        return empleado != null;
    }

    /**
     * Comprueba si existe una clase con el identificador especificado.
     *
     * @param classId identificador de la clase.
     * @return true si existe, false en caso contrario.
     */
    public boolean classExists(int classId) {

        MongoCollection<Document> collection
                = database.getCollection("classes");

        Document doc = collection.find(
                Filters.eq("_id", classId)
        ).first();

        return doc != null;
    }

    /**
     * Comprueba si existe un gimnasio con el identificador indicado.
     *
     * @param gymId identificador del gimnasio.
     * @return true si existe, false en caso contrario.
     */
    public boolean gymExists(int gymId) {

        MongoCollection<Document> collection
                = database.getCollection("gyms");

        Document doc = collection.find(
                Filters.eq("_id", gymId)
        ).first();

        return doc != null;
    }

    /**
     * Comprueba si ya existe una clase con el nombre indicado.
     *
     * @param className nombre de la clase.
     * @return true si existe, false en caso contrario.
     */
    public boolean classNameExists(String className) {

        MongoCollection<Document> collection
                = database.getCollection("classes");

        Document doc = collection.find(
                Filters.eq("class_name", className)
        ).first();

        return doc != null;
    }

    /**
     * Lee el contenido completo del fichero de registro de la aplicación.
     *
     * @return texto con todas las entradas del log.
     */
    public String leerLog() {

        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br
                = new BufferedReader(
                        new FileReader("gym_log.txt"))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contenido.toString();
    }

    /**
     * Reduce el stock disponible de un producto únicamente si existe cantidad
     * suficiente.
     *
     * @param productId identificador del producto.
     * @param cantidad cantidad a descontar.
     * @return true si el stock se actualiza correctamente, false en caso
     * contrario.
     */
    public boolean decreaseProductStock(int productId, int cantidad) {

        MongoCollection<Document> productos = MongoConnection.getConnection().getCollection("products");

        UpdateResult resultado = productos.updateOne(
                Filters.and(
                        Filters.eq("_id", productId),
                        Filters.gte("stock", cantidad)
                ),
                Updates.inc("stock", -cantidad)
        );

        return resultado.getModifiedCount() == 1; //Si se ha modificado esto da true
    }
}
