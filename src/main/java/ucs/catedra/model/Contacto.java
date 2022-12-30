package ucs.catedra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad Contacto
 * ----------------------------------------------------
 * Bean con anotaciones JPA con implementación Hibernate 
 * 
 */
@Entity 
@Table(name="contacto")
public class Contacto implements IHasIntID {
	// Atributos
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre") 
	private String nombre;
	
	@Column(name="descripcion") 
	private String descripcion;

	//Constructor
	public Contacto() {}
	
	//Constructor con sobrecarga
	public Contacto(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}	

	//Getters Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// atribuito con Validaciones
	@NotNull
	@Size(min=2, max=100, message = "Cantidad de caracteres no es válido. Ingrese mínimo un character, máximo 100")	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre= nombre;
	}

	// atribuito con Validaciones
	@NotNull
	@Size(min=2, message = "Cantidad de caracteres no es válido. Ingrese más de un character")	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString(){
		return "id="+id+", nombre="+nombre+", descripcion="+descripcion;
	}
}