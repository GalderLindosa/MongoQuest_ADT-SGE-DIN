/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module MongoQuest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;

    opens com.mycompany.mongoquest to javafx.fxml;
    exports com.mycompany.mongoquest;
}