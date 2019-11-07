package trimix.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;

@ViewScoped
@Entity
@Table(name = "TRIMIX")
public class Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="ID")
	private int id;
	
	@Column (name="NOMBRE")
	private String nombre;
	
	@Column (name="APELLIDO")
	private String apellido;
	
	
	@Column (name="FECHA_NACIMIENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	
	@Column (name="NUMERO_DNI")
	private int numeroDni;
	
	@Column (name="TIPO_DNI")
	private String tipoDni;
	
	

	public Persona(int id, String nombre, String apellido, Date fechaNacimiento, int numeroDni, String tipoDni) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.numeroDni = numeroDni;
		this.tipoDni = tipoDni;
	}

	public Persona() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getNumeroDni() {
		return numeroDni;
	}

	public void setNumeroDni(int numeroDni) {
		this.numeroDni = numeroDni;
	}

	public String getTipoDni() {
		return tipoDni;
	}

	public void setTipoDni(String tipoDni) {
		this.tipoDni = tipoDni;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", numeroDni=" + numeroDni + ", tipoDni=" + tipoDni + "]";
	}
	
}
