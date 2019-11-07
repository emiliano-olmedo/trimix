package trimix.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import trimix.domain.Persona;

public class PersonaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoEmi");

	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();

	@Inject
	private Persona persona1 = new Persona();

	private Persona personaModificada = new Persona();

	private Persona registroSeleccionado = new Persona();

	private List<Persona> listaPersona = new ArrayList<Persona>();

	@PostConstruct
	public void inicializar() {

		this.listaPersona = agregarATabla();

		int max = 0;
		for (Persona persona : listaPersona) {
			if (persona.getId() > max) {
				max = persona.getId();
			}
		}

		this.persona1.setId(max + 1);
	}

	public List<Persona> agregarATabla() {

		tx.begin();

		listaPersona = em.createQuery("SELECT p FROM Persona p order by p.id").getResultList();

		tx.commit();

		// em.close();
		// emf.close();

		if (listaPersona != null) {
			System.out.println("Se encontro al menos un registro");
		} else {
			System.out.println("No se encontraron registros");
		}
		return listaPersona;
	}

	public String enviar() {

		tx.begin();

		em.persist(persona1);

		tx.commit();

		em.close();

		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(persona1.getNombre() + ", tu registro ha sido guardado!"));

		this.listaPersona.add(persona1);
		
		persona1 = new Persona();
	
		return "index";

	}

	public String eliminar() {

		if (registroSeleccionado != null) {
			System.out.println("Hay algo seleccionado");
			// registroSeleccionado = null;
			tx.begin();

			em.remove(registroSeleccionado);

			tx.commit();
			System.out.println("Objeto eliminado");
			em.close();
		} else {
			System.out.println("No se selecciono nada");
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro eliminado!"));

		return "index";

	}

	public void cancelar() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado!"));
	}

	public void actualizar(CellEditEvent event) {
		Object valorViejo = event.getOldValue();
		Object valorNuevo = event.getNewValue();

		if (valorNuevo != null && !valorNuevo.equals(valorViejo)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda modificada!",
					"Viejo: " + valorViejo + ", Nuevo:" + valorNuevo);
			FacesContext.getCurrentInstance().addMessage(null, msg);

			Persona persona = em.find(Persona.class, Integer.parseInt(event.getRowKey()));
			tx.begin();
			em.persist(persona);
			tx.commit();
		}
	}

	public Persona getPersona1() {
		return persona1;
	}

	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}

	public List<Persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public Persona getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(Persona registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public Persona getPersonaModificada() {
		return personaModificada;
	}

	public void setPersonaModificada(Persona personaModificada) {
		this.personaModificada = personaModificada;
	}

}
