package commons.usuarios.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable{

	private static final long serialVersionUID = 8002202019413072109L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMBRE", unique = true, length = 30) //La idea es que los nombres de los roles sean únicos un rol puede tener muchos usuarios
	private String nombre;
	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles") //roles nombre del atributo roles de la clase Usuario
//	private List<Usuario> usuarios; //Para nuestro caso no es necesario hacer la relación bidireccional, solo se pone como ejemplo. 
//	No tiene sentido mostrar cuales son los usuarios que están asignados a un role
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
