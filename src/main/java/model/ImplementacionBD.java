/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;*/
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.Date;
import static com.mongodb.client.model.Filters.and;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import excepciones.DniException;
import excepciones.PasswordException;
import java.time.LocalDate;
import java.time.ZoneId;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.or;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import controller.MongoConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.bson.conversions.Bson;

/**
 * Implementación de acceso a datos de la aplicación.
 * Gestiona las operaciones CRUD sobre usuarios,
 * empleados, clases y productos almacenados en MongoDB.
 *
 * Implementa el patrón Singleton para disponer de
 * una única instancia de acceso a la base de datos.
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
 * Constructor privado que inicializa la conexión
 * con la base de datos y la colección de personas.
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
 * Busca un usuario en la base de datos a partir
 * de su nombre de usuario y contraseña.
 *
 * @param usuario nombre de usuario.
 * @param contrasena contraseña del usuario.
 * @return objeto Person correspondiente o null si no existe.
 */
public Person buscar(String usuario, String contrasena)
 {
        Person persona = null;
        
            try {
                Document doc = PROFILES.find(and(eq("username", usuario), eq("password", contrasena))).first();

                if (doc == null || !contrasena.equals(doc.getString("password"))) {
                    return null;
                } else {
                    String type = doc.getString("role");
                    
                    Date fechaMongo = doc.getDate("start_date");
                    LocalDate fecha = null;

                    if (fechaMongo != null) {
                        fecha = fechaMongo.toInstant()
                          .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                    }
                    
                    if ("member".equals(type)) {
                        return new User(doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"),doc.getInteger("balance"),fecha);
                    } else if ("manager".equals(type)) {
                        return new Boss(doc.getInteger("gym_id"), doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"),doc.getInteger("balance"),fecha);
                    } else if ("employee".equals(type)) {
                        return new Employee(doc.getString("discount_code"), doc.getInteger("gym_id"), doc.getString("username"), doc.getString("password"), doc.getString("first_name"), doc.getString("last_name"), doc.getString("national_id"),doc.getInteger("balance"),fecha);
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
public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        MongoCollection<Document> collection =  database.getCollection("people"); //Consigo la collecion de "Person" = "db.Persons.find()"

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
public ArrayList<Employee> getEmployee()
 {
        ArrayList<Employee> aEmployee = new ArrayList<>();

        MongoCollection<Document> collection =  database.getCollection("people"); //Consigo la collecion de "Person" = "db.Persons.find()"

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
            } else if (p instanceof User) {
                doc.append("role", "member");
            }
            
            collection.insertOne(doc);
            return true;

        } catch (Exception e) {
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
            MongoCollection<Document> collection =database.getCollection("people");
            Document cambios = new Document();
            cambios.append("password", p.getPassword());
            cambios.append("first_name", p.getName());
            cambios.append("last_name", p.getLastName());
            cambios.append("national_id", p.getDni());
            collection.updateOne(
                    Filters.eq("username", p.getUserName()), //busca el que tenga ese userName 
                    new Document("$set", cambios) //y con el $set actualiza los campo que le digo pero que deje lo demas igual 
            );
            return true;
        } catch (Exception e) {
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

        return resultado.getDeletedCount() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
/**
 * Obtiene todos los productos almacenados
 * en la colección de productos.
 *
 * @return lista de productos.
 */
public ArrayList<Product> obtenerProductos(){
        MongoCollection<Document> productos = MongoConnection.getConnection().getCollection("Productos");
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

public ArrayList<GymClass> getGymClasses() { //en general no mira el user 

    ArrayList<GymClass> clases = new ArrayList<>();

    MongoCollection<Document> collection = database.getCollection("classes");

    for (Document doc : collection.find()) {

        int id = doc.getInteger("_id");
        String className = doc.getString("class_name");
        String employeeUsername = doc.getString("employee_username");
        int gymId = doc.getInteger("gym_id");

        ArrayList<String> members = new ArrayList<>();

        List<String> membersList =
                (List<String>) doc.get("members");

        if (membersList != null) {
            members.addAll(membersList);
        }

        ArrayList<Schedule> schedules = new ArrayList<>();

        List<Document> scheduleDocs =
                (List<Document>) doc.get("schedule");

        for (Document s : scheduleDocs) {
           String day = s.getString("day");
           String hour = s.getString("time");
           schedules.add(new Schedule(day, hour));
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

    return clases;
}
public ArrayList<Integer> getClassIdsEmployee(String username) {

    ArrayList<Integer> classIds = new ArrayList<>();

    MongoCollection<Document> collection =
            database.getCollection("employees");

    Document employee =
            collection.find(Filters.eq("username", username)).first();

    if (employee != null) {

        List<Document> classes =
                (List<Document>) employee.get("classes");

        for (Document c : classes) {

            classIds.add(c.getInteger("class_id"));
        }
    }

    return classIds;
}

public ArrayList<GymClass> getClassesForUser(String username, String role) {

    ArrayList<GymClass> resultado = new ArrayList<>();

    ArrayList<GymClass> todasLasClases = getGymClasses();

    if (role.equals("member")) {

        for (GymClass gymClass : todasLasClases) {

            if (gymClass.getMembers().contains(username)) {

                resultado.add(gymClass);
            }
        }
    }

    else if (role.equals("employee")) {

        for (GymClass gymClass : todasLasClases) {

            if (gymClass.getEmployeeUsername().equals(username)) {

                resultado.add(gymClass);
            }
        }
    }

    return resultado;
}

public void createClass(GymClass gymClass) {
    
    MongoCollection<Document> collection =
            database.getCollection("classes");

    Document doc = new Document();

    doc.append("_id", gymClass.getId());
    doc.append("class_name", gymClass.getClassName());
    doc.append("employee_username", gymClass.getEmployeeUsername());
    doc.append("gym_id", gymClass.getGymId());

    collection.insertOne(doc);
}

public void deleteClass(int classId) {

    MongoCollection<Document> collection =
            database.getCollection("classes");

    collection.deleteOne(
            Filters.eq("_id", classId)
    );
}

public void updateClassTrainer(int classId,
                               String employeeUsername) {

    MongoCollection<Document> collection =
            database.getCollection("classes");

    collection.updateOne(
            Filters.eq("_id", classId),
            Updates.set(
                    "employee_username",
                    employeeUsername
            )
    );
}
public void addMemberToClass(
        int classId,
        String username) {

    MongoCollection<Document> collection =
            database.getCollection("classes");

    collection.updateOne(
            Filters.eq("_id", classId),
            Updates.push("members", username)
    );
}

public void removeMemberFromClass(
        int classId,
        String username) {

    MongoCollection<Document> collection =
            database.getCollection("classes");

    collection.updateOne(
            Filters.eq("_id", classId),
            Updates.pull("members", username)
    );
}
}
