package reactivefx.infra;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@BindingAnnotation
/**
 * @author mrbraz
 * This annotation determines if a Java class is a JavaFX Controller. 
 * The use of this annotation implies on auto FXML loading using a convention over configuration. 
 * Convetion: Use the same package structure and the same class and fxml name, for example:
 *    src/main/package/ControllerClass.java
 *    src/resources/package/ControllerClass.fxml
 */
public @interface FXView {

}
