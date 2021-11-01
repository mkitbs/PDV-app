package rs.mkgroup.pdvapp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PDV1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String bukrs;

	private String last_changed_by;

	private String lastChangedOn;

	private String year;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PDV1VALUES")
	@MapKeyColumn(name = "MONTH")
	@Column(name = "VALUE")
	private Map<String, Long> listOfValues = new HashMap<String, Long>();

}
